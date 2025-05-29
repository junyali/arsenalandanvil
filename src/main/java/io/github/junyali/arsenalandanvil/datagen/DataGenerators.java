package io.github.junyali.arsenalandanvil.datagen;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = ArsenalandAnvil.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Server
        generator.addProvider(event.includeServer(), new ArsenalandAnvilRecipeProvider(packOutput, lookupProvider));
        BlockTagsProvider blockTagsProvider = new ArsenalandAnvilBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeClient(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new ArsenalandAnvilItemTagProvider(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));

        // Client
        generator.addProvider(event.includeClient(), new ArsenalandAnvilItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ArsenalandAnvilLanguageProvider(packOutput, "en_us"));
    }
}
