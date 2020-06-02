package com.eazyftw.officialucpaddons.vault.elements;

import com.eazyftw.ultracustomizerplus.addon.ElementUCP;
import com.eazyftw.ultracustomizerplus.color.EZMessage;
import com.eazyftw.ultracustomizerplus.util.VaultUtil;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.entity.Player;

public class PlayerRemoveFromBalance extends ElementUCP {

    public PlayerRemoveFromBalance(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Player Remove from Balance";
    }

    @Override
    public String getInternalName() {
        return "player-remove-balance";
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
    public boolean isHidingIfNotCompatible() {
        return false;
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.GOLD_INGOT;
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Remove money from a player's balance" };
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[] { new Argument("player", "Player", DataType.PLAYER, elementInfo), new Argument("amount", "Amount", DataType.NUMBER, elementInfo),
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
        final int amount = (int)(long)this.getArguments(info)[1].getValue(instance);
        if(!VaultUtil.hasVault()) {
            EZMessage.text("%prefix% &cVault is needed to use the PlayerRemoveFromBalance element.").console();
            return;
        }
        if(VaultUtil.getEconomy() == null) {
            EZMessage.text("%prefix% &cThe Economy has to be setup to use the PlayerRemoveFromBalance element. Do you have an Economy plugin installed?").console();
            this.getConnectors(info)[1].run(instance);
            return;
        }
        VaultUtil.getEconomy().withdrawPlayer(player, amount);
        this.getConnectors(info)[0].run(instance);
    }
}
