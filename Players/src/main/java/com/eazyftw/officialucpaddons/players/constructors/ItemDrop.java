package com.eazyftw.officialucpaddons.players.constructors;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrop extends ConstructorUCP {

    public ItemDrop(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Item Drop";
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
        return "player-item-drop";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.HOPPER;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when a player", "drops an item" };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("event", "Event", DataType.CANCELLABLE_EVENT, elementInfo), new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo), new OutcomingVariable("item", "Item", DataType.ITEM, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onExplosionPrimeE(final PlayerDropItemEvent e) {
        this.call(elementInfo -> {
            final ScriptInstance instance = new ScriptInstance();
            ItemDrop.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e;
                }
            });
            ItemDrop.this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e.getPlayer();
                }
            });
            ItemDrop.this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e.getItemDrop();
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
