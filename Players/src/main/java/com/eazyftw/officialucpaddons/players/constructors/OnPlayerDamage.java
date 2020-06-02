package com.eazyftw.officialucpaddons.players.constructors;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnPlayerDamage extends ConstructorUCP {

    public OnPlayerDamage(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "On Player Damage";
    }

    @Override
    public String getAuthor() {
        return "EazyFTW";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getInternalName() {
        return "on-player-damage";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.SKELETON_SKULL;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when a player", "is damaged by a player" };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("event", "Event", DataType.CANCELLABLE_EVENT, elementInfo), new OutcomingVariable("damage", "Damage", DataType.NUMBER, elementInfo), new OutcomingVariable("attacker", "Attacker", DataType.PLAYER, elementInfo), new OutcomingVariable("victim", "Victim", DataType.PLAYER, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMobDamage(final EntityDamageByEntityEvent e) {
        if((e.getDamager() instanceof Player || e.getDamager() instanceof Arrow) && e.getEntity() instanceof Player) {
            Player p;
            if(e.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow)e.getDamager();
                if(arrow.getShooter() instanceof Player) {
                    p = (Player)arrow.getShooter();
                } else {
                    return;
                }
            } else {
                p = (Player)e.getDamager();
            }
            this.call(elementInfo -> {
                final ScriptInstance instance = new ScriptInstance();
                OnPlayerDamage.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                    return e;
                    }
                });
                OnPlayerDamage.this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                    return (long)e.getFinalDamage();
                    }
                });
                OnPlayerDamage.this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                        return p;
                    }
                });
                OnPlayerDamage.this.getOutcomingVariables(elementInfo)[3].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                        return e.getEntity();
                    }
                });
                return instance;
            });
        }
    }

    @Override
    public boolean isUnlisted() {
        return false;
    }
}
