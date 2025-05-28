package io.github.junyali.arsenalandanvil.item;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ArsenalandAnvilItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArsenalandAnvil.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
