package com.eazyftw.officialucpaddons.players.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import com.eazyftw.ultracustomizerplus.color.EZMessage;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class BroadcastCenteredMessage extends ElementUCP {

    public BroadcastCenteredMessage(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Broadcast Centered Message";
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
        return "broadcast-centered-message";
    }

    @Override
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.PAPER;
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Broadcast a centered message" };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("message", "Message", DataType.STRING, elementInfo) };
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
        final String message = (String)this.getArguments(info)[0].getValue(instance);
        EZMessage.text(message).broadcast(true, true);
        this.getConnectors(info)[0].run(instance);
    }
}
