package com.eazyftw.officialucpaddons.players.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import com.eazyftw.ultracustomizerplus.color.EZMessage;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PlayerTeleport extends ElementUCP {

    public PlayerTeleport(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Teleport Player";
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
        return "player-teleport";
    }

    @Override
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.ENDER_PEARL;
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Teleport a player to a certain location" };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo),
                new Argument("world", "World", DataType.STRING, elementInfo),
                new Argument("x", "X", DataType.STRING, elementInfo),
                new Argument("y", "Y", DataType.STRING, elementInfo),
                new Argument("z", "Z", DataType.STRING, elementInfo),
                new Argument("yaw", "Yaw", DataType.STRING, elementInfo),
                new Argument("pitch", "Pitch", DataType.STRING, elementInfo)
        };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    @Override
    public Child[] getConnectors(final ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    @Override
    public void run(final ElementInfo info, final ScriptInstance instance) {
        final Player player = (Player)this.getArguments(info)[0].getValue(instance);
        try {
            final String world = (String) this.getArguments(info)[1].getValue(instance);
            final double x = Double.parseDouble((String) this.getArguments(info)[2].getValue(instance));
            final double y = Double.parseDouble((String) this.getArguments(info)[3].getValue(instance));
            final double z = Double.parseDouble((String) this.getArguments(info)[4].getValue(instance));
            final float yaw = Float.parseFloat((String) this.getArguments(info)[5].getValue(instance));
            final float pitch = Float.parseFloat((String) this.getArguments(info)[6].getValue(instance));
            Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        } catch (Exception ex) {
            EZMessage.text("%prefix% &4&lERROR: &c" + ex.getMessage()).console();
        }
        this.getConnectors(info)[0].run(instance);
    }

}
