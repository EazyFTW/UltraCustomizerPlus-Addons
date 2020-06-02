package com.eazyftw.officialucpaddons.players.constructors;

import com.eazyftw.ultracustomizerplus.addon.ConstructorUCP;
import me.TechsCode.UltraCustomizer.UltraCustomizer;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.*;
import me.TechsCode.UltraCustomizer.scriptSystem.objects.datatypes.DataType;
import me.TechsCode.UltraCustomizer.tpl.XMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class ExplosionPrime extends ConstructorUCP {

    public ExplosionPrime(final UltraCustomizer plugin) {
        super(plugin);
    }

    @Override
    public String getName() {
        return "Explosion Prime";
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
        return "explosion-primed";
    }

    @Override
    public XMaterial getMaterial() {
        return XMaterial.TNT;
    }

    @Override
    public Argument[] getArguments(final ElementInfo elementInfo) {
        return new Argument[0];
    }

    @Override
    public String[] getDescription() {
        return new String[] { "Will be executed when an explosion", "is primed" };
    }

    @Override
    public OutcomingVariable[] getOutcomingVariables(final ElementInfo elementInfo) {
        return new OutcomingVariable[] { new OutcomingVariable("event", "Event", DataType.CANCELLABLE_EVENT, elementInfo) };
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onExplosionPrimeE(final ExplosionPrimeEvent e) {
        this.call(elementInfo -> {
            final ScriptInstance instance = new ScriptInstance();
            ExplosionPrime.this.getOutcomingVariables(elementInfo)[0].register(instance, new DataRequester() {
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
