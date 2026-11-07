package com.craigtec.leakyshulkerbox.block.custom.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import org.jspecify.annotations.Nullable;

public class LeakyShulkerBoxBlockEntityRenderState extends BlockEntityRenderState {
    public Direction direction = Direction.NORTH;
    public @Nullable DyeColor color;
    public float progress;
}
