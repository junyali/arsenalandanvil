package io.github.junyali.arsenalandanvil.item;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ArsenalandAnvilCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ArsenalandAnvil.MODID);

    public static final Supplier<CreativeModeTab> ARSENALANDANVIL_TAB = CREATIVE_MOD_TAB.register("arsenalandanvil_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.IRON_SWORD))
                    .title(Component.translatable("creativetab.arsenalandanvil.arsenalandanvil"))
                    .displayItems((itemDisplayParameters, output) -> {
                    // items goes here :p
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TAB.register(eventBus);
    }
}