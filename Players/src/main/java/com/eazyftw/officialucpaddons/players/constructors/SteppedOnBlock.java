package com.eazyftw.officialucpaddons.players.constructors;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.*;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class SteppedOnBlock extends ConstructorUCP {

    public SteppedOnBlock(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Player Stepped on Block";
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
        return "player-stopped-on-block";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.OAK_TRAPDOOR;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when a player", "stops on a specific block" };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("block", "Block", DataType.BLOCK, elementInfo), new OutcomingVariable("player", "Player", DataType.PLAYER, elementInfo), new OutcomingVariable("event", "Event", DataType.CANCELLABLE_EVENT, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(final PlayerMoveEvent e) {
        this.call(elementInfo -> {
            final ScriptInstance instance = new ScriptInstance();
            SteppedOnBlock.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return Objects.requireNonNull(e.getTo()).getBlock().getRelative(BlockFace.DOWN);
                }
            });
            SteppedOnBlock.this.getOutcomingVariables(elementInfo)[1].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e.getPlayer();
                }
            });
            SteppedOnBlock.this.getOutcomingVariables(elementInfo)[2].register(instance, new DataRequester() {
                @Override
                public Object request() {
                    return e;
                }
            });
            return instance;
        });
    }

    @Override
    public boolean isUnlisted() {
        return false;
    }
}
