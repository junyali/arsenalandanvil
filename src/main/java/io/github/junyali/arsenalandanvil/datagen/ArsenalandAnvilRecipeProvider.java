package io.github.junyali.arsenalandanvil.datagen;

import io.github.junyali.arsenalandanvil.item.ArsenalandAnvilItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ArsenalandAnvilRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ArsenalandAnvilRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        // recipes go here or smthin :3
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ArsenalandAnvilItems.HARDENED_HANDLE.get(), 1)
                .pattern(" # ")
                .pattern("###")
                .pattern(" # ")
                .define('#', Ingredient.of(Items.STICK))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        // the most bandaid way for generating recipes :c
        for (var entry : ArsenalandAnvilItems.ITEMS.getEntries()) {
            String itemName = entry.getId().getPath();

            if (itemName.equals("hardened_handle")) {
                continue;
            }

            if (itemName.contains("dagger")) {
                generateDaggerRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("rapier")) {
                generateRapierRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("katana")) {
                generateKatanaRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("scimitar")) {
                generateScimitarRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("longsword")) {
                generateLongswordRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("claymore")) {
                generateClaymoreRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("kanabo")) {
                generateKanaboRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("battleaxe")) {
                generateBattleaxeRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("spear")) {
                generateSpearRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("pike")) {
                generatePikeRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("halberd")) {
                generateHalberdRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("glaive")) {
                generateGlaiveRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("hammer")) {
                generateHammerRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("sickle")) {
                generateSickleRecipe(entry.get(), itemName, recipeOutput);
            }
            else if (itemName.contains("paxel")) {
                generatePaxelRecipe(entry.get(), itemName, recipeOutput);
            }
        }
    }

    private Ingredient getTierMaterial(String tier) {
        return switch (tier) {
            case "wooden" -> Ingredient.of(ItemTags.PLANKS);
            case "stone" -> Ingredient.of(Tags.Items.COBBLESTONES);
            case "iron" -> Ingredient.of(Tags.Items.INGOTS_IRON);
            case "golden" -> Ingredient.of(Tags.Items.INGOTS_GOLD);
            case "diamond" -> Ingredient.of(Tags.Items.GEMS_DIAMOND);
            default -> Ingredient.of(Items.STICK);
        };
    }

    private Item getTierItem(String tier) {
        return switch (tier) {
            case "wooden" -> Items.OAK_PLANKS;
            case "stone" -> Items.COBBLESTONE;
            case "iron" -> Items.IRON_INGOT;
            case "golden" -> Items.GOLD_INGOT;
            case "diamond" -> Items.DIAMOND;
            case "netherite" -> Items.NETHERITE_INGOT;
            default -> Items.STICK;
        };
    }

    private Item getTierPickaxe(String tier) {
        return switch (tier) {
            case "wooden" -> Items.WOODEN_PICKAXE;
            case "stone" -> Items.STONE_PICKAXE;
            case "iron" -> Items.IRON_PICKAXE;
            case "golden" -> Items.GOLDEN_PICKAXE;
            case "diamond" -> Items.DIAMOND_PICKAXE;
            case "netherite" -> Items.NETHERITE_PICKAXE;
            default -> Items.WOODEN_PICKAXE;
        };
    }

    private Item getTierAxe(String tier) {
        return switch (tier) {
            case "wooden" -> Items.WOODEN_AXE;
            case "stone" -> Items.STONE_AXE;
            case "iron" -> Items.IRON_AXE;
            case "golden" -> Items.GOLDEN_AXE;
            case "diamond" -> Items.DIAMOND_AXE;
            case "netherite" -> Items.NETHERITE_AXE;
            default -> Items.WOODEN_AXE;
        };
    }

    private Item getTierShovel(String tier) {
        return switch (tier) {
            case "wooden" -> Items.WOODEN_SHOVEL;
            case "stone" -> Items.STONE_SHOVEL;
            case "iron" -> Items.IRON_SHOVEL;
            case "golden" -> Items.GOLDEN_SHOVEL;
            case "diamond" -> Items.DIAMOND_SHOVEL;
            case "netherite" -> Items.NETHERITE_SHOVEL;
            default -> Items.WOODEN_SHOVEL;
        };
    }

    private String extractTier(String itemName) {
        return itemName.split("_")[0];
    }

    // recipe spam T-T
    private void generateNetheriteSmithingRecipe(Item netheriteItem, String itemName, RecipeOutput recipeOutput) {
        String diamondItemName = itemName.replace("netherite_", "diamond_");

        Item diamondItem = null;
        for (var entry : ArsenalandAnvilItems.ITEMS.getEntries()) {
            if (entry.getId().getPath().equals(diamondItemName)) {
                diamondItem = entry.get();
                break;
            }
        }

        if (diamondItem != null) {
            SmithingTransformRecipeBuilder.smithing(
                    Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                    Ingredient.of(diamondItem),
                    Ingredient.of(Items.NETHERITE_INGOT),
                    RecipeCategory.COMBAT,
                    netheriteItem
            ).unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT)).save(recipeOutput, itemName + "_smithing");
        }
    }

    private void generateDaggerRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern("   ")
                .pattern(" # ")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateRapierRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" ||")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateKatanaRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern(" # ")
                .pattern(" # ")
                .pattern("  |")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateScimitarRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern("  #")
                .pattern(" # ")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateLongswordRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateClaymoreRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern(" # ")
                .pattern("###")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateKanaboRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern("###")
                .pattern(" # ")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateBattleaxeRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern("###")
                .pattern("## ")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateSpearRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern(" # ")
                .pattern(" | ")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generatePikeRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern("  #")
                .pattern(" | ")
                .pattern("|  ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateHalberdRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern("  #")
                .pattern(" |#")
                .pattern("|  ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateGlaiveRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, item)
                .pattern(" # ")
                .pattern(" |#")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateHammerRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, item)
                .pattern("###")
                .pattern("###")
                .pattern(" | ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generateSickleRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
            return;
        }

        Ingredient material = getTierMaterial(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, item)
                .pattern(" ##")
                .pattern("  #")
                .pattern("|  ")
                .define('#', material)
                .define('|', ArsenalandAnvilItems.HARDENED_HANDLE.get())
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .unlockedBy("has_hardened_handle", has(ArsenalandAnvilItems.HARDENED_HANDLE.get()))
                .save(recipeOutput);
    }

    private void generatePaxelRecipe(Item item, String itemName, RecipeOutput recipeOutput) {
        String tier = extractTier(itemName);

        if (tier.equals("netherite")) {
            generateNetheriteSmithingRecipe(item, itemName, recipeOutput);
        }

        Item pickaxe = getTierPickaxe(tier);
        Item axe = getTierAxe(tier);
        Item shovel = getTierShovel(tier);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, item)
                .pattern("PAS")
                .pattern(" | ")
                .pattern(" | ")
                .define('P', Ingredient.of(pickaxe))
                .define('A', Ingredient.of(axe))
                .define('S', Ingredient.of(shovel))
                .define('|', Ingredient.of(Items.STICK))
                .unlockedBy("has_" + tier + "_pickaxe", has(pickaxe))
                .unlockedBy("has_" + tier + "_axe", has(axe))
                .unlockedBy("has_" + tier + "_shovel", has(shovel))
                .unlockedBy("has_" + tier, has(getTierItem(tier)))
                .save(recipeOutput);
    }
}
