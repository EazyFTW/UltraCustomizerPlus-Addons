package com.eazyftw.officialucpaddons.players.constructors;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChange extends ConstructorUCP {

    public WorldChange(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "World Change";
    }

    @Override
    public String getAuthor() {
        return "EazyFTW";
    }

    @Override
    public String getVersion() {
        return "1.0.1";
    }

    @Override
    public String getInternalName() {
        return "world-change";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.GRASS_BLOCK;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when a player", "changes their world" };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("oldWorldName", "Old World Name", DataType.STRING, elementInfo), new OutcomingVariable("newWorldName", "New World Name", DataType.STRING, elementInfo), new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo), new OutcomingVariable("event", "Event", DataType.CANCELLABLE_EVENT, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(final PlayerChangedWorldEvent e) {
        this.call(elementInfo -> {
            final ScriptInstance instance = new ScriptInstance();
            WorldChange.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e.getFrom().getName();
                }
            });
            WorldChange.this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e.getPlayer().getLocation().getWorld().getName();
                }
            });
            WorldChange.this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e.getPlayer();
                }
            });
            WorldChange.this.getOutcomingVariables(elementInfo)[3].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e;
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
