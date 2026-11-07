package com.craigtec.leakyshulkerbox.mixin;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.block.custom.LeakyShulkerBoxBlock;
import com.craigtec.leakyshulkerbox.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractCauldronBlock.class)
public abstract class AbstractCauldronBlockMixin {
    @Inject(at = @At("HEAD"), method = "useItemOn", cancellable = true)
    private static void useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (itemStack.is(ModTags.Items.LEAKY_SHULKER_BOXES)) {
            Block block = Block.byItem(itemStack.getItem());
            if (!(block instanceof LeakyShulkerBoxBlock)) {
                cir.setReturnValue(InteractionResult.TRY_WITH_EMPTY_HAND);
            }

            if (!level.isClientSide()) {
                ItemStack cleanedLeakyShulkerBox = itemStack.transmuteCopy(ModBlocks.LEAKY_SHULKER_BOX, 1);
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemStack, player, cleanedLeakyShulkerBox, false));
                player.awardStat(Stats.CLEAN_SHULKER_BOX);
                LayeredCauldronBlock.lowerFillLevel(state, level, pos);
            }

            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
