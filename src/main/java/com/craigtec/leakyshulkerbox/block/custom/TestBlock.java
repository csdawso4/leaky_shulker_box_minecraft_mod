package com.craigtec.leakyshulkerbox.block.custom;

import com.craigtec.leakyshulkerbox.block.custom.entity.custom.TestBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class TestBlock extends BaseEntityBlock {
    private static MapCodec<TestBlock> CODEC = simpleCodec(TestBlock::new);

    public TestBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new TestBlockEntity(worldPosition, blockState);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        if (level.getBlockEntity(pos) instanceof TestBlockEntity testBlockEntity) {
            testBlockEntity.drops();
            level.updateNeighbourForOutputSignal(pos, this);
        }
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof TestBlockEntity testBlockEntity) {
            boolean isTestEmpty = testBlockEntity.isEmpty();

            // INSERT
            if (isTestEmpty && !itemStack.isEmpty()) {
                testBlockEntity.setTheItem(itemStack);
                itemStack.shrink(1);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            }

            // EXTRACT
            else if (!isTestEmpty) {
                ItemStack stackOnTest = testBlockEntity.getTheItem();
                testBlockEntity.clearContent();

                if (!player.getInventory().add(stackOnTest)) {
                    player.drop(stackOnTest, true);
                }
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
