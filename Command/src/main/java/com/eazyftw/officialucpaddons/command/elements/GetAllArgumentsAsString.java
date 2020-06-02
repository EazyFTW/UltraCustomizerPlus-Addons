package com.eazyftw.officialucpaddons.command.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;

public class GetAllArgumentsAsString extends ElementUCP {

    public GetAllArgumentsAsString(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Get All Arguments As String";
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
        return "get-all-args-string";
    }

    @Override
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.SLIME_BLOCK;
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Get all arguments as a string", "combined with a space", "", "Will return \"\" if there isn't an argument." };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("value", "Value", DataType.STRING, elementInfo) };
    }

    @Override
    public Child[] getConnectors(final ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("arguments", "Arguments", DataType.ARGUMENTS, elementInfo) };
    }

    @Override
    public void run(final ElementInfo info, final ScriptInstance instance) {
        final String[] string = (String[])this.getArguments(info)[0].getValue(instance);
        this.getOutcomingVariables(info)[0].register(instance, new DataRequester() {
            @Override
            public Object request() {
                return (string == null ? "" : String.join(" ", string));
            }
        });
        this.getConnectors(info)[0].run(instance);
    }
}