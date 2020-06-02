package com.eazyftw.officialucpaddons.players.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.entity.Player;

public class PlayerGetDisplayName extends ElementUCP {

    public PlayerGetDisplayName(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Get Player Display Name";
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
        return "player-get-display-name";
    }

    @Override
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.NAME_TAG;
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Retrieve the Display Name of a player" };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo) };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("displayName", "Display Name", DataType.STRING, elementInfo) };
    }

    @Override
    public Child[] getConnectors(final ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    @Override
    public void run(final ElementInfo info, final ScriptInstance instance) {
        final Player player = (Player)this.getArguments(info)[0].getValue(instance);
        this.getOutcomingVariables(info)[0].register(instance, new DataRequester() {
            @Override
            public Object request() {
                return player.getDisplayName();
            }
        });
        this.getConnectors(info)[0].run(instance);
    }
}
