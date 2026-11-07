package com.craigtec.leakyshulkerbox.block.custom;

import com.craigtec.leakyshulkerbox.block.custom.entity.ModBlockEntities;
import com.craigtec.leakyshulkerbox.block.custom.entity.custom.LeakyShulkerBoxBlockEntity;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LeakyShulkerBoxBlock extends BaseEntityBlock {
    public static final MapCodec<LeakyShulkerBoxBlock> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(DyeColor.CODEC.optionalFieldOf("color").forGetter((b) -> Optional.ofNullable(b.color)), propertiesCodec()).apply(i, (color, properties) -> new LeakyShulkerBoxBlock(color.orElse( null), properties)));
    public static final Map<Direction, VoxelShape> SHAPES_OPEN_SUPPORT = Shapes.rotateAll(Block.boxZ(16.0F, 0.0F, 1.0F));
    public static final EnumProperty<Direction> FACING;
    public static final Identifier CONTENTS;
    private final @Nullable DyeColor color;

    public @NonNull MapCodec<LeakyShulkerBoxBlock> codec() {
        return CODEC;
    }

    public LeakyShulkerBoxBlock(final @Nullable DyeColor color, final BlockBehaviour.Properties properties) {
        super(properties);
        this.color = color;
        this.registerDefaultState((this.stateDefinition.any()).setValue(FACING, Direction.UP));
    }

    public BlockEntity newBlockEntity(final @NonNull BlockPos worldPosition, final @NonNull BlockState blockState) {
        return new LeakyShulkerBoxBlockEntity(this.color, worldPosition, blockState);
    }

    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(final @NonNull Level level, final @NonNull BlockState blockState, final @NonNull BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.LEAKY_SHULKER_BOX, LeakyShulkerBoxBlockEntity::tick);
    }

    protected @NonNull InteractionResult useWithoutItem(final @NonNull BlockState state, final @NonNull Level level, final @NonNull BlockPos pos, final @NonNull Player player, final @NonNull BlockHitResult hitResult) {
        if (level instanceof ServerLevel serverLevel) {
            BlockEntity var8 = level.getBlockEntity(pos);
            if (var8 instanceof LeakyShulkerBoxBlockEntity leakyShulkerBoxBlockEntity) {
                if (canOpen(state, level, pos, leakyShulkerBoxBlockEntity)) {
                    player.openMenu(leakyShulkerBoxBlockEntity);
                    player.awardStat(Stats.OPEN_SHULKER_BOX);
                    PiglinAi.angerNearbyPiglins(serverLevel, player, true);
                }
            }
        }

        return InteractionResult.SUCCESS;
    }

    private static boolean canOpen(final BlockState state, final Level level, final BlockPos pos, final LeakyShulkerBoxBlockEntity blockEntity) {
        if (blockEntity.getAnimationStatus() != LeakyShulkerBoxBlockEntity.AnimationStatus.CLOSED) {
            return true;
        } else {
            AABB lidOpenBoundingBox = Shulker.getProgressDeltaAabb(1.0F, state.getValue(FACING), 0.0F, 0.5F, pos.getBottomCenter()).deflate(1.0E-6);
            return level.noCollision(lidOpenBoundingBox);
        }
    }

    public BlockState getStateForPlacement(final BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public @NonNull BlockState playerWillDestroy(final Level level, final @NonNull BlockPos pos, final @NonNull BlockState state, final @NonNull Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof LeakyShulkerBoxBlockEntity leakyShulkerBoxBlockEntity) {
            if (!level.isClientSide() && player.preventsBlockDrops() && !leakyShulkerBoxBlockEntity.isEmpty()) {
                ItemStack itemStack = new ItemStack(state.getBlock());
                itemStack.applyComponents(blockEntity.collectComponents());
                ItemEntity entity = new ItemEntity(level, (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, itemStack);
                entity.setDefaultPickUpDelay();
                level.addFreshEntity(entity);
            } else {
                leakyShulkerBoxBlockEntity.unpackLootTable(player);
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    protected @NonNull List<ItemStack> getDrops(final @NonNull BlockState state, LootParams.Builder params) {
        BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof LeakyShulkerBoxBlockEntity leakyShulkerBoxBlockEntity) {
            params = params.withDynamicDrop(CONTENTS, (output) -> {
                for(int i = 0; i < leakyShulkerBoxBlockEntity.getContainerSize(); ++i) {
                    output.accept(leakyShulkerBoxBlockEntity.getItem(i));
                }

            });
        }

        return super.getDrops(state, params);
    }

    protected void affectNeighborsAfterRemoval(final @NonNull BlockState state, final @NonNull ServerLevel level, final @NonNull BlockPos pos, final boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    protected @NonNull VoxelShape getBlockSupportShape(final @NonNull BlockState state, final BlockGetter level, final @NonNull BlockPos pos) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof LeakyShulkerBoxBlockEntity shulker) {
            if (!shulker.isClosed()) {
                return SHAPES_OPEN_SUPPORT.get(state.getValue(FACING).getOpposite());
            }
        }

        return Shapes.block();
    }

    protected @NonNull VoxelShape getShape(final @NonNull BlockState state, final BlockGetter level, final @NonNull BlockPos pos, final @NonNull CollisionContext context) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof LeakyShulkerBoxBlockEntity leakyShulkerBoxBlockEntity) {
            return Shapes.create(leakyShulkerBoxBlockEntity.getBoundingBox(state));
        } else {
            return Shapes.block();
        }
    }

    protected boolean propagatesSkylightDown(final @NonNull BlockState state) {
        return false;
    }

    protected boolean hasAnalogOutputSignal(final @NonNull BlockState state) {
        return true;
    }

    protected int getAnalogOutputSignal(final @NonNull BlockState state, final Level level, final @NonNull BlockPos pos, final @NonNull Direction direction) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    public @Nullable DyeColor getColor() {
        return this.color;
    }

    protected @NonNull BlockState rotate(final BlockState state, final Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    protected @NonNull BlockState mirror(final BlockState state, final Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    static {
        FACING = DirectionalBlock.FACING;
        CONTENTS = Identifier.withDefaultNamespace("contents");
    }
}
