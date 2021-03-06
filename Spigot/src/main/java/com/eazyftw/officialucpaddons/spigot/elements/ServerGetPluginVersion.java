package com.eazyftw.officialucpaddons.spigot.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ServerGetPluginVersion extends ElementUCP {

    public ServerGetPluginVersion(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Get Server Plugin Version";
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
        return "server-plugin-version";
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
        return new String[] { "Retrieve the version of a plugin", "that's on the server" };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("name", "Plugin Name", DataType.STRING, elementInfo) };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("version", "Version", DataType.STRING, elementInfo) };
    }

    @Override
    public Child[] getConnectors(final ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    @Override
    public void run(final ElementInfo info, final ScriptInstance instance) {
        final String name = (String)this.getArguments(info)[0].getValue(instance);
        this.getOutcomingVariables(info)[0].register(instance, new DataRequester() {
            @Override
            public Object request() {
                Plugin plugin = Bukkit.getPluginManager().getPlugin(name);
                if(plugin == null) return "Unknown Plugin Name";
                return plugin.getDescription().getVersion();
            }
        });
        this.getConnectors(info)[0].run(instance);
    }
}
