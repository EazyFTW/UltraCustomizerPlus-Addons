package com.eazyftw.officialucpaddons.players.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerSendSound extends ElementUCP {

    public PlayerSendSound(UltraCustomizer plugin) { super(plugin); }

    @Override
    public String getName() {
        return "Player Send Sound With Pitch/Volume";
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
        return "player-send-sound-pitch-volume";
    }

    @Override
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.MUSIC_DISC_11;
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Send a sound to a player with", "a certain pitch and volume"};
    }

    @Override
    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo), new Argument("sound", "Sound", DataType.SOUND, elementInfo), new Argument("volume", "Volume", DataType.NUMBER, elementInfo), new Argument("pitch", "Pitch", DataType.NUMBER, elementInfo) };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[0];
    }

    @Override
    public void run(ElementInfo info, ScriptInstance instance) {

        final Player p = (Player)this.getArguments(info)[0].getValue(instance);

        final Sound sound = (Sound)this.getArguments(info)[1].getValue(instance);

        final long volume = (long)this.getArguments(info)[2].getValue(instance);
        final long pitch = (long)this.getArguments(info)[3].getValue(instance);
        p.playSound(p.getLocation(), sound, volume, pitch);
        this.getConnectors(info)[0].run(instance);
    }
}
