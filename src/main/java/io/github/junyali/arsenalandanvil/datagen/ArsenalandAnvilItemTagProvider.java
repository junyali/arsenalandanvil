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

    // COMMON TAGS - RODS
    public static final TagKey<Item> WEAPON_HANDLES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "rods/weapon"));
    public static final TagKey<Item> REINFORCED_HANDLES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "rods/reinforced"));

    // COMMON TAGS - TOOLS
    public static final TagKey<Item> COMBAT_EQUIPMENT = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/weapons"));

    public static final TagKey<Item> PICKAXE_TOOL = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/pickaxes"));
    public static final TagKey<Item> AXE_TOOL = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/axes"));
    public static final TagKey<Item> SHOVEL_TOOL = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/shovels"));

    public static final TagKey<Item> HAMMER_TOOL = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/hammers"));
    public static final TagKey<Item> SICKLE_TOOL = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/sickles"));
    public static final TagKey<Item> PAXEL_TOOL = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "tools/paxels"));

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
            } else if (isTool(itemName)) {
                tag(Tags.Items.MINING_TOOL_TOOLS)
                        .add(item);
                tag(COMBAT_EQUIPMENT)
                        .add(item);

                if (itemName.contains("hammer")) {
                    tag(HAMMER_TOOL)
                        .add(item);
                } else if (itemName.contains("sickle")) {
                    tag(SICKLE_TOOL)
                        .add(item);
                } else if (itemName.contains("paxel")) {
                    tag(PAXEL_TOOL)
                            .add(item);
                    tag(PICKAXE_TOOL)
                            .add(item);
                    tag(AXE_TOOL)
                            .add(item);
                    tag(SHOVEL_TOOL)
                            .add(item);
                }
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
                itemName.contains("halberd") || itemName.contains("glaive");
    }

    private boolean isTool(String itemName) {
        return itemName.contains("hammer") || itemName.contains("sickle") || itemName.contains("paxel");
    }
}
