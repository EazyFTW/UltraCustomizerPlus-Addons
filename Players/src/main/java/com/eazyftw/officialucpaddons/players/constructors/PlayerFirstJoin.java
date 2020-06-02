package com.eazyftw.officialucpaddons.players.constructors;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerFirstJoin extends ConstructorUCP {

    public PlayerFirstJoin(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Player First Join";
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
        return "player-first-join";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.PLAYER_HEAD;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when a player", "joins the server for the first time." };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo), new OutcomingVariable("join-msg", "Join Message", DataType.STRING, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(final PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()) {
            this.call(elementInfo -> {
                final ScriptInstance instance = new ScriptInstance();
                PlayerFirstJoin.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                        return e.getPlayer();
                    }
                });
                PlayerFirstJoin.this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                        return e.getJoinMessage();
                    }
                });
                return instance;
            });
        }
    }

    @Override
    public boolean isUnlisted() {
        return false;
    }
}
