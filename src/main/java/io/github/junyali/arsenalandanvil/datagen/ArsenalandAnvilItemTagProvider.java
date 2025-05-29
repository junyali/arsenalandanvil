package io.github.junyali.arsenalandanvil.datagen;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import io.github.junyali.arsenalandanvil.item.ArsenalandAnvilItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ArsenalandAnvilItemTagProvider extends ItemTagsProvider {

    public static final TagKey<Item> WEAPON_HANDLES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "rods/weapon"));
    public static final TagKey<Item> REINFORCED_HANDLES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "rods/reinforced"));
    public static final TagKey<Item> COMBAT_EQUIPMENT = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/weapons"));

    public ArsenalandAnvilItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ArsenalandAnvil.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(WEAPON_HANDLES)
                .add(ArsenalandAnvilItems.HARDENED_HANDLE.get());
        tag(REINFORCED_HANDLES)
                .add(ArsenalandAnvilItems.HARDENED_HANDLE.get());

        addMaterialTierTags();

        for (var entry : ArsenalandAnvilItems.ITEMS.getEntries()) {
            String itemName = entry.getId().getPath();
            Item item = entry.get();

            if (isWeapon(itemName)) {
                tag(ItemTags.SWORDS)
                        .add(item);
                tag(COMBAT_EQUIPMENT)
                        .add(item);
                tag(Tags.Items.MELEE_WEAPON_TOOLS)
                        .add(item);
            }
        }
    }

    private void addMaterialTierTags() {
        String[] tiers = {"wooden", "stone", "iron", "golden", "diamond", "netherite"};

        for (String tier: tiers) {
            TagKey<Item> tierTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "weapons/" + tier));

            for (var entry : ArsenalandAnvilItems.ITEMS.getEntries()) {
                String itemName = entry.getId().getPath();
                if (itemName.startsWith(tier + "_") && isWeapon(itemName)) {
                    tag(tierTag).add(entry.get());
                }
            }
        }
    }

    private boolean isWeapon(String itemName) {
        // this TOOK ME SO LONG AHSHHDQSVAHDHHHH
        return itemName.contains("dagger") || itemName.contains("spear") ||
                itemName.contains("katana") || itemName.contains("rapier") ||
                itemName.contains("scimitar") || itemName.contains("longsword") ||
                itemName.contains("claymore") || itemName.contains("kanabo") ||
                itemName.contains("battleaxe") || itemName.contains("pike") ||
                itemName.contains("halberd") ||
                itemName.contains("glaive");
    }
}
