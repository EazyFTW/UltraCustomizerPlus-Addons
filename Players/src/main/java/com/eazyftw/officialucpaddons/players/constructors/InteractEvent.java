package com.eazyftw.officialucpaddons.players.constructors;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent extends ConstructorUCP {

    public InteractEvent(UltraCustomizer ultraCustomizer) {
        super(ultraCustomizer);
    }

    @Override
    public String getName() {
        return "Interact Event";
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
        return "interact-event";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.ITEM_FRAME;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when player", "interacts with the world" };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("event", "Event", DataType.CANCELLABLE_EVENT, elementInfo), new OutcomingVariable("material", "Clicked Material", DataType.MATERIAL, elementInfo), new OutcomingVariable("type", "Type", DataType.STRING, elementInfo), new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerInteract(final PlayerInteractEvent e) {
        try {
            if(e.getAction() != Action.PHYSICAL && (e.getHand() != null && !e.getHand().name().contains("OFF_"))) {
                call(e);
            }
        } catch(NoSuchMethodError ex) {
            if(e.getAction() != Action.PHYSICAL) {
                call(e);
            }
        }
    }

    public void call(final PlayerInteractEvent e) {
        this.call(elementInfo -> {
            final ScriptInstance instance = new ScriptInstance();
            InteractEvent.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e;
                }
            });
            InteractEvent.this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return (e.getClickedBlock() == null ? XMaterial.AIR : XMaterial.fromMaterial(e.getClickedBlock().getType()));
                }
            });
            InteractEvent.this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e.getAction().name();
                }
            });
            InteractEvent.this.getOutcomingVariables(elementInfo)[3].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e.getPlayer();
                }
            });
            return instance;
        });
    }

    @Override
    public boolean isUnlisted() {
        return false;
    }
}
