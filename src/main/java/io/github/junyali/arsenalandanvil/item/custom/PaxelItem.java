package io.github.junyali.arsenalandanvil.item.custom;

import io.github.junyali.arsenalandanvil.util.ItemAttributeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

public class PaxelItem extends DiggerItem {
    public PaxelItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_PICKAXE, properties.attributes(createAttributes(tier)));
    }

    private static ItemAttributeModifiers createAttributes(Tier tier) {
        return ItemAttributeBuilder.weapon()
                .attackDamage(tier.getAttackDamageBonus() + 1.0f + 1.5f)
                .build();
    }

    @Override
    public boolean isCorrectToolForDrops(@NotNull ItemStack stack, BlockState state) {
        boolean toolMineable = state.is(BlockTags.MINEABLE_WITH_PICKAXE) ||
                state.is(BlockTags.MINEABLE_WITH_AXE) ||
                state.is(BlockTags.MINEABLE_WITH_SHOVEL);

        if (!toolMineable) return false;
        if (!state.requiresCorrectToolForDrops()) return true;

        Tier toolTier = this.getTier();
        if (toolTier == Tiers.WOOD && state.is(BlockTags.INCORRECT_FOR_WOODEN_TOOL)) {
            return false;
        }
        if (toolTier == Tiers.STONE && state.is(BlockTags.INCORRECT_FOR_STONE_TOOL)) {
            return false;
        }
        if (toolTier == Tiers.IRON && state.is(BlockTags.INCORRECT_FOR_IRON_TOOL)) {
            return false;
        }
        if (toolTier == Tiers.DIAMOND && state.is(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)) {
            return false;
        }
        if (toolTier == Tiers.GOLD && state.is(BlockTags.INCORRECT_FOR_GOLD_TOOL)) {
            return false;
        }
        return toolTier != Tiers.NETHERITE || !state.is(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(itemAbility) ||
                ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility) ||
                ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) ||
            state.is(BlockTags.MINEABLE_WITH_AXE) ||
            state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            return this.getTier().getSpeed();
        }
        return super.getDestroySpeed(stack, state);
    }
}
