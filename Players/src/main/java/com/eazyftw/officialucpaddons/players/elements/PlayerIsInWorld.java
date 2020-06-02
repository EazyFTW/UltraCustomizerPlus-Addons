package com.eazyftw.officialucpaddons.players.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.entity.Player;

public class PlayerIsInWorld extends ElementUCP {

    public PlayerIsInWorld(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Is in World";
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
        return "player-is-in-world";
    }

    @Override
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.FILLED_MAP;
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Runs one of two actions depending", "if a player is in a certain world" };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo), new Argument("world", "World", DataType.STRING, elementInfo) };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    @Override
    public Child[] getConnectors(final ElementInfo elementInfo) {
        return new Child[] { new Child(elementInfo, "yes") {
            @Override
            public String getName() {
                return "Is in the World";
            }

            @Override
            public String[] getDescription() {
                return new String[] { "Will be executed if a player", "is in the world" };
            }

            @Override
            public XMaterial getIcon() {
                return XMaterial.LIME_STAINED_GLASS_PANE;
            }
        }, new Child(elementInfo, "no") {
            @Override
            public String getName() {
                return "Is Not in the World";
            }

            @Override
            public String[] getDescription() {
                return new String[] { "Will be executed if a player", "is not in the world" };
            }

            @Override
            public XMaterial getIcon() {
                return XMaterial.RED_STAINED_GLASS_PANE;
            }
        } };
    }

    @Override
    public void run(final ElementInfo info, final ScriptInstance instance) {
        final Player player = (Player)this.getArguments(info)[0].getValue(instance);
        final String world = (String)this.getArguments(info)[1].getValue(instance);
        if (player.getWorld().getName().equals(world)) {
            this.getConnectors(info)[0].run(instance);
        } else {
            this.getConnectors(info)[1].run(instance);
        }
    }
}
