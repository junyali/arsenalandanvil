package io.github.junyali.arsenalandanvil.event;

import io.github.junyali.arsenalandanvil.ArsenalandAnvil;
import io.github.junyali.arsenalandanvil.item.custom.HammerItem;
import io.github.junyali.arsenalandanvil.item.custom.SickleItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = ArsenalandAnvil.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ArsenalandAnvilEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Credits to https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java for the following methods
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            BlockState initialState = event.getLevel().getBlockState(initialBlockPos);

            // this check below prevents the mining of crops
            if(initialState.getBlock() instanceof CropBlock) {
                return;
            }

            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                BlockState blockState = event.getLevel().getBlockState(pos);

                if(blockState.getBlock() instanceof CropBlock || !hammer.isCorrectToolForDrops(mainHandItem, blockState)) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

    @SubscribeEvent
    public static void onSickleUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof SickleItem sickle && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            BlockState initialState = event.getLevel().getBlockState(initialBlockPos);

            // this check below only allows mining crops
            if(!(initialState.getBlock() instanceof CropBlock)) {
                return;
            }

            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : SickleItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos) {
                    continue;
                }

                BlockState blockState = event.getLevel().getBlockState(pos);

                if(!(blockState.getBlock() instanceof CropBlock cropBlock)) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);

                if(event.getLevel().getRandom().nextFloat() < 0.2f) {
                    List<ItemStack> drops = Block.getDrops(blockState, (ServerLevel) event.getLevel(), pos, null);
                    for (ItemStack drop : drops) {
                        Block.popResource((Level) event.getLevel(), pos, drop.copy());
                    }
                }

                serverPlayer.gameMode.destroyBlock(pos);
                event.getLevel().setBlock(pos, cropBlock.getStateForAge(0), 3);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }
}
