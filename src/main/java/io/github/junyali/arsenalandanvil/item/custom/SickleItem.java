package io.github.junyali.arsenalandanvil.item.custom;

import io.github.junyali.arsenalandanvil.util.ItemAttributeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SickleItem extends DiggerItem {
    public SickleItem(Tier tier, Properties properties) {
        super(tier, BlockTags.MINEABLE_WITH_HOE, properties.attributes(createAttributes(tier)));
        // alternatively
        // super(tier, BlockTags.CROPS, properties.attributes(createAttributes(tier)));
    }

    private static ItemAttributeModifiers createAttributes(Tier tier) {
        return ItemAttributeBuilder.weapon()
                .attackDamage(tier.getAttackDamageBonus() + 1.0f + -0.5f)
                .attackSpeed(-2.6f)
                .miningSpeed(0.2f)
                .build();
    }

    @Override
    public boolean isCorrectToolForDrops(@NotNull ItemStack stack, BlockState state) {
        if (!state.is(BlockTags.MINEABLE_WITH_HOE)) return false;
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

    // Method for 3x3 mining (thanks Kaupenjoe)
    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();

        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
        if(traceResult.getType() == HitResult.Type.MISS) {
            return positions;
        }

        if(traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
                }
            }
        }

        if(traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                }
            }
        }

        if(traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for(int x = -range; x <= range; x++) {
                for(int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
                }
            }
        }

        return positions;
    }
}
