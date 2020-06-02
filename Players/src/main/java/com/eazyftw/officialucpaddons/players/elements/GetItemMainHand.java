package com.eazyftw.officialucpaddons.players.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.entity.Player;

public class GetItemMainHand extends ElementUCP {

    public GetItemMainHand(UltraCustomizer ultraCustomizer) {
        super(ultraCustomizer);
    }

    @Override
    public String getName() {
        return "Get Item In (Main) Hand";
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
        return "player-get-item-main-hand";
    }

    @Override
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.IRON_SWORD;
    }

    @Override
    public String[] getDescription() {
        return new String[]{"Get the item in a player's main hand", "defaults to air"};
    }

    @Override
    public Child[] getConnectors(ElementInfo elementInfo) {
        return new Child[] { new DefaultChild(elementInfo, "next") };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo) };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("handItem", "Item", DataType.ITEM, elementInfo) };
    }

    @Override
    public void run(ElementInfo info, ScriptInstance instance) {
        final Player p = (Player)this.getArguments(info)[0].getValue(instance);
        this.getOutcomingVariables(info)[0].register(instance, new DataRequester() {
            @Override
            public Object request() {
                return p.getInventory().getItemInHand();
            }
        });
        this.getConnectors(info)[0].run(instance);
    }
}
