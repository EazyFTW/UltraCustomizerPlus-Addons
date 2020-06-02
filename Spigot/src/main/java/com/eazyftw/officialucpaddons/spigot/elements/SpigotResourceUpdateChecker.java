package com.eazyftw.officialucpaddons.spigot.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import com.eazyftw.ultracustomizerplus.update.SpigotUpdateChecker;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;

public class SpigotResourceUpdateChecker extends ElementUCP {

    public SpigotResourceUpdateChecker(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Check for a Spigot Update";
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
        return "spigot-resource-update-checker";
    }

    @Override
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.SEA_LANTERN;
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Runs one of two actions depending", "if the specified spigot resource", "has an update" };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("resourceId", "Resource ID", DataType.STRING, elementInfo),  new Argument("pluginName", "Plugin Name", DataType.STRING, elementInfo) };
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
                return "There's an Update";
            }

            @Override
            public String[] getDescription() {
                return new String[] { "Will be executed if there's", "an update available" };
            }

            @Override
            public XMaterial getIcon() {
                return XMaterial.LIME_STAINED_GLASS_PANE;
            }
        }, new Child(elementInfo, "no") {
            @Override
            public String getName() {
                return "There's Not an Update";
            }

            @Override
            public String[] getDescription() {
                return new String[] { "Will be executed if there's", "not an update available" };
            }

            @Override
            public XMaterial getIcon() {
                return XMaterial.RED_STAINED_GLASS_PANE;
            }
        } };
    }

    @Override
    public void run(final ElementInfo info, final ScriptInstance instance) {
        final String resourceId = (String)this.getArguments(info)[0].getValue(instance);
        final String pluginName = (String)this.getArguments(info)[1].getValue(instance);
        if (SpigotUpdateChecker.check(resourceId, pluginName)) {
            this.getConnectors(info)[0].run(instance);
        } else {
            this.getConnectors(info)[1].run(instance);
        }
    }
}
