package com.craigtec.leakyshulkerbox.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class TestBlockItem extends BlockItem {
    public TestBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        level.playSound(player, player.getOnPos(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
        return InteractionResult.SUCCESS;
    }
}
