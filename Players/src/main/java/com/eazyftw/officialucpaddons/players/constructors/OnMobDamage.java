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

public class OnMobDamage extends ConstructorUCP {

    public OnMobDamage(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "On Mob Damage";
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
        return "on-mob-damage";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.COW_SPAWN_EGG;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when a mob", "is damaged by a player" };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("event", "Event", DataType.CANCELLABLE_EVENT, elementInfo), new OutcomingVariable("damage", "Damage", DataType.NUMBER, elementInfo), new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo), new OutcomingVariable("entity", "Entity", DataType.STRING, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMobDamage(final EntityDamageByEntityEvent e) {
        if((e.getDamager() instanceof Player || e.getDamager() instanceof Arrow) && e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof HumanEntity) && e.getEntityType() != EntityType.ARMOR_STAND) {
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
                OnMobDamage.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                    return e;
                    }
                });
                OnMobDamage.this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                    return (long)e.getFinalDamage();
                    }
                });
                OnMobDamage.this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                        return p;
                    }
                });
                OnMobDamage.this.getOutcomingVariables(elementInfo)[3].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                        return e.getEntityType().name();
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
