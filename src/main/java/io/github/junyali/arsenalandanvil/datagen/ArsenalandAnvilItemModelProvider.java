package io.github.junyali.arsenalandanvil.datagen;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import io.github.junyali.arsenalandanvil.item.ArsenalandAnvilItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.io.File;

public class ArsenalandAnvilItemModelProvider extends ItemModelProvider {
    public ArsenalandAnvilItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ArsenalandAnvil.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (var entry : ArsenalandAnvilItems.ITEMS.getEntries()) {
            String itemName = entry.getId().getPath();

            try {
                if (isWeapon(itemName)) {
                    handheldItem(entry.get());
                } else {
                    basicItem(entry.get());
                }
                continue;
            } catch (Exception e) {
                System.out.println("No texture found for: " + itemName);
            }
        }
    }

    private boolean isWeapon(String itemName) {
        return itemName.contains("dagger") || itemName.contains("spear") ||
                itemName.contains("katana") || itemName.contains("rapier") ||
                itemName.contains("scimitar") || itemName.contains("longsword") ||
                itemName.contains("claymore") || itemName.contains("warhammer") ||
                itemName.contains("battleaxe") || itemName.contains("pike") ||
                itemName.contains("halberd") || itemName.contains("glaive");
    }
}
