package io.github.junyali.arsenalandanvil.datagen;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import io.github.junyali.arsenalandanvil.item.ArsenalandAnvilItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ArsenalandAnvilItemModelProvider extends ItemModelProvider {
    public ArsenalandAnvilItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ArsenalandAnvil.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (var entry : ArsenalandAnvilItems.ITEMS.getEntries()) {
            String itemName = entry.getId().getPath();
            basicItem(entry.get());
        }
    }
}
