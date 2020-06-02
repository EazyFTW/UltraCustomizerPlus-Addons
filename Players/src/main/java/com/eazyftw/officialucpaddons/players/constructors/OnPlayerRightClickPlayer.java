package com.eazyftw.officialucpaddons.players.constructors;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnPlayerRightClickPlayer extends ConstructorUCP {

    public OnPlayerRightClickPlayer(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "On Player Right Click Player";
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
        return "on-player-right-click-player";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.FEATHER;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when a player", "interacts (right clicks) a player" };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("event", "Event", DataType.CANCELLABLE_EVENT, elementInfo), new OutcomingVariable("isShift", "Is Shifting", DataType.BOOLEAN, elementInfo), new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo),  new OutcomingVariable("clickedPlayer", "Clicked Player", DataType.PLAYER, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void clickEvent(final PlayerInteractEntityEvent e) {
        if((e.getRightClicked() instanceof Player)) {
            this.call(elementInfo -> {
                final ScriptInstance instance = new ScriptInstance();
                OnPlayerRightClickPlayer.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                    return e;
                    }
                });
                OnPlayerRightClickPlayer.this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                    return e.getPlayer().isSneaking();
                    }
                });
                OnPlayerRightClickPlayer.this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                        return e.getPlayer();
                    }
                });
                OnPlayerRightClickPlayer.this.getOutcomingVariables(elementInfo)[3].register(instance, new DataRequester() {
                    @Override
                    public Object request() {
                        return e.getRightClicked();
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
