package com.craigtec.leakyshulkerbox.block.custom.entity.custom;

import com.craigtec.leakyshulkerbox.block.custom.LeakyShulkerBoxBlock;
import com.craigtec.leakyshulkerbox.block.custom.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.stream.IntStream;

public class LeakyShulkerBoxBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    public static final int COLUMNS = 9;
    public static final int ROWS = 3;
    private static final int[] SLOTS = IntStream.range(0, 27).toArray();
    private static final Component DEFAULT_NAME = Component.translatable("block.leaky-shulker-box.leaky_shulker_box");
    private NonNullList<ItemStack> itemStacks;
    private int openCount;
    private LeakyShulkerBoxBlockEntity.AnimationStatus animationStatus;
    private float progress;
    private float progressOld;
    private final @Nullable DyeColor color;

    public LeakyShulkerBoxBlockEntity(final @Nullable DyeColor color, final BlockPos worldPosition, final BlockState blockState) {
        super(ModBlockEntities.LEAKY_SHULKER_BOX, worldPosition, blockState);
        this.itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
        this.animationStatus = LeakyShulkerBoxBlockEntity.AnimationStatus.CLOSED;
        this.color = color;
    }

    public LeakyShulkerBoxBlockEntity(final BlockPos worldPosition, final BlockState blockState) {
        super(ModBlockEntities.LEAKY_SHULKER_BOX, worldPosition, blockState);
        this.itemStacks = NonNullList.withSize(27, ItemStack.EMPTY);
        this.animationStatus = LeakyShulkerBoxBlockEntity.AnimationStatus.CLOSED;
        Block var4 = blockState.getBlock();
        DyeColor var10001;
        if (var4 instanceof LeakyShulkerBoxBlock leakyShulkerBoxBlock) {
            var10001 = leakyShulkerBoxBlock.getColor();
        } else {
            var10001 = null;
        }

        this.color = var10001;
    }

    public static void tick(final Level level, final BlockPos pos, final BlockState state, final LeakyShulkerBoxBlockEntity entity) {
        entity.updateAnimation(level, pos, state);
    }

    private void updateAnimation(final Level level, final BlockPos pos, final BlockState blockState) {
        this.progressOld = this.progress;
        switch (this.animationStatus.ordinal()) {
            case 0:
                this.progress = 0.0F;
                break;
            case 1:
                this.progress += 0.1F;
                if (this.progressOld == 0.0F) {
                    doNeighborUpdates(level, pos, blockState);
                }

                if (this.progress >= 1.0F) {
                    this.animationStatus = LeakyShulkerBoxBlockEntity.AnimationStatus.OPENED;
                    this.progress = 1.0F;
                    doNeighborUpdates(level, pos, blockState);
                }

                this.moveCollidedEntities(level, pos, blockState);
                break;
            case 2:
                this.progress = 1.0F;
                break;
            case 3:
                this.progress -= 0.1F;
                if (this.progressOld == 1.0F) {
                    doNeighborUpdates(level, pos, blockState);
                }

                if (this.progress <= 0.0F) {
                    this.animationStatus = LeakyShulkerBoxBlockEntity.AnimationStatus.CLOSED;
                    this.progress = 0.0F;
                    doNeighborUpdates(level, pos, blockState);
                }
        }

    }

    public LeakyShulkerBoxBlockEntity.AnimationStatus getAnimationStatus() {
        return this.animationStatus;
    }

    public AABB getBoundingBox(final BlockState state) {
        Vec3 bottomCenter = new Vec3(0.5F, 0.0F, 0.5F);
        return Shulker.getProgressAabb(1.0F, state.getValue(LeakyShulkerBoxBlock.FACING), 0.5F * this.getProgress(1.0F), bottomCenter);
    }

    private void moveCollidedEntities(final Level level, final BlockPos pos, final BlockState state) {
        if (state.getBlock() instanceof LeakyShulkerBoxBlock) {
            Direction direction = state.getValue(LeakyShulkerBoxBlock.FACING);
            AABB aabb = Shulker.getProgressDeltaAabb(1.0F, direction, this.progressOld, this.progress, pos.getBottomCenter());
            List<Entity> entities = level.getEntities(null, aabb);
            if (!entities.isEmpty()) {
                for(Entity entity : entities) {
                    if (entity.getPistonPushReaction() != PushReaction.IGNORE) {
                        entity.move(MoverType.SHULKER_BOX, new Vec3((aabb.getXsize() + 0.01) * (double)direction.getStepX(), (aabb.getYsize() + 0.01) * (double)direction.getStepY(), (aabb.getZsize() + 0.01) * (double)direction.getStepZ()));
                    }
                }

            }
        }
    }

    public int getContainerSize() {
        return this.itemStacks.size();
    }

    public boolean triggerEvent(final int b0, final int b1) {
        if (b0 == 1) {
            this.openCount = b1;
            if (b1 == 0) {
                this.animationStatus = LeakyShulkerBoxBlockEntity.AnimationStatus.CLOSING;
            }

            if (b1 == 1) {
                this.animationStatus = LeakyShulkerBoxBlockEntity.AnimationStatus.OPENING;
            }

            return true;
        } else {
            return super.triggerEvent(b0, b1);
        }
    }

    private static void doNeighborUpdates(final Level level, final BlockPos pos, final BlockState blockState) {
        blockState.updateNeighbourShapes(level, pos, 3);
        level.updateNeighborsAt(pos, blockState.getBlock());
    }

    public void preRemoveSideEffects(final @NonNull BlockPos pos, final @NonNull BlockState state) {
    }

    public void startOpen(final @NonNull ContainerUser containerUser) {
        if (!this.remove && !containerUser.getLivingEntity().isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }

            ++this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount == 1) {
                this.level.gameEvent(containerUser.getLivingEntity(), GameEvent.CONTAINER_OPEN, this.worldPosition);
                this.level.playSound(null, this.worldPosition, SoundEvents.SHULKER_BOX_OPEN, SoundSource.BLOCKS, 0.5F, this.level.getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }

    }

    public void stopOpen(final @NonNull ContainerUser containerUser) {
        if (!this.remove && !containerUser.getLivingEntity().isSpectator()) {
            --this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount <= 0) {
                this.level.gameEvent(containerUser.getLivingEntity(), GameEvent.CONTAINER_CLOSE, this.worldPosition);
                this.level.playSound(null, this.worldPosition, SoundEvents.SHULKER_BOX_CLOSE, SoundSource.BLOCKS, 0.5F, this.level.getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }

    }

    protected @NonNull Component getDefaultName() {
        return DEFAULT_NAME;
    }

    protected void loadAdditional(final @NonNull ValueInput input) {
        super.loadAdditional(input);
        this.loadFromTag(input);
    }

    protected void saveAdditional(final @NonNull ValueOutput output) {
        super.saveAdditional(output);
        if (!this.trySaveLootTable(output)) {
            ContainerHelper.saveAllItems(output, this.itemStacks, false);
        }

    }

    public void loadFromTag(final ValueInput input) {
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(input)) {
            ContainerHelper.loadAllItems(input, this.itemStacks);
        }

    }

    protected @NonNull NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    protected void setItems(final @NonNull NonNullList<ItemStack> items) {
        this.itemStacks = items;
    }

    public int @NonNull [] getSlotsForFace(final @NonNull Direction direction) {
        return SLOTS;
    }

    public boolean canPlaceItemThroughFace(final int slot, final ItemStack itemStack, final @Nullable Direction direction) {
        return !(Block.byItem(itemStack.getItem()) instanceof LeakyShulkerBoxBlock);
    }

    public boolean canTakeItemThroughFace(final int slot, final @NonNull ItemStack itemStack, final @NonNull Direction direction) {
        return true;
    }

    public float getProgress(final float a) {
        return Mth.lerp(a, this.progressOld, this.progress);
    }

    public @Nullable DyeColor getColor() {
        return this.color;
    }

    protected @NonNull AbstractContainerMenu createMenu(final int containerId, final @NonNull Inventory inventory) {
        return new ShulkerBoxMenu(containerId, inventory, this);
    }

    public boolean isClosed() {
        return this.animationStatus == LeakyShulkerBoxBlockEntity.AnimationStatus.CLOSED;
    }

    public enum AnimationStatus {
        CLOSED,
        OPENING,
        OPENED,
        CLOSING;

        AnimationStatus() {
        }
    }
}
