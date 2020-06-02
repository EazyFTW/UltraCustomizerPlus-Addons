package com.eazyftw.officialucpaddons.citizens.elements;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.*;
import me.TechsCode.UltraCustomizer.tpl.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import org.bukkit.event.*;

public class NPCLeftClick extends ConstructorUCP {

    public NPCLeftClick(final UltraCustomizer plugin) {
        super(plugin);
    }

    public String getName() {
        return "NPC Left Click (Citizens)";
    }

    @Override
    public String getAuthor() {
        return "EazyFTW";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    public String getInternalName() {
        return "citizens-left-click";
    }

    public XMaterial getMaterial() {
        return XMaterial.VILLAGER_SPAWN_EGG;
    }

    @Override
    public String getRequiredPlugin() {
        return "Citizens";
    }

    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    public String[] getDescription() {
        return new String[] { "Will be executed when a player", "left clicks a Citizens NPC." };
    }

    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo), new OutcomingVariable("id", "Citizen's ID", DataType.STRING, elementInfo), new OutcomingVariable("npcName", "NPC Name", DataType.STRING, elementInfo) };
    }

    @EventHandler
    public void onClick(final NPCLeftClickEvent e) {
        this.call(elementInfo -> {
            final ScriptInstance instance = new ScriptInstance();
            this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                public Object request() {
                    return e.getClicker();
                }
            });
            this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                public Object request() {
                    return String.valueOf(e.getNPC().getId());
                }
            });
            this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                public Object request() {
                    return e.getNPC().getName();
                }
            });
            return instance;
        });
    }

    public boolean isUnlisted() {
        return false;
    }
}
