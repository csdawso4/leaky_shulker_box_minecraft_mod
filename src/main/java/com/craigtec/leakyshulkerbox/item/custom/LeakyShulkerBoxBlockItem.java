package com.craigtec.leakyshulkerbox.item.custom;

import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.item.component.ItemContainerContents.fromItems;

public class LeakyShulkerBoxBlockItem extends BlockItem {
    public LeakyShulkerBoxBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    RandomSource random = RandomSource.create();

    @Override
    public @NonNull InteractionResult useOn(UseOnContext context) {
        // if player is shifting, place the leaky shulker in the world
        if (context.getPlayer().isShiftKeyDown()) {
            return super.useOn(context);
        }

        // since the placement that will follow is randomized, it cannot happen on both the client and server
        if (context.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        // get the container of the leaky shulker
        ItemStack leakyShulkerItemStack = context.getItemInHand();
        ItemContainerContents container = leakyShulkerItemStack.getComponents().get(DataComponents.CONTAINER);

        if (container == null) {
            return InteractionResult.FAIL;
        }

        // get the items in the container and randomly select one
        List<ItemStack> itemStackList = container.allItemsCopyStream().toList();
        Integer indexOfRandomStack = getIndexOfRandomNonEmptyStack(itemStackList);

        if (indexOfRandomStack == -1) {
            return InteractionResult.FAIL;
        }

        ItemStack itemStackToPlace = itemStackList.get(indexOfRandomStack);
        if (itemStackToPlace.getItem() instanceof BlockItem) {

            // try to place the block in the world
            BlockHitResult blockHitResult = new BlockHitResult(context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside());
            InteractionResult result = place(new BlockPlaceContext(context.getPlayer(), context.getHand(), itemStackToPlace, blockHitResult));

            if (result == InteractionResult.FAIL) {
                return InteractionResult.FAIL;
            }

            // update the leaky shulker with the item decremented by one
            DataComponentPatch dataComponentPatch = DataComponentPatch.builder().set(DataComponents.CONTAINER, fromItems(itemStackList)).build();
            leakyShulkerItemStack.applyComponents(dataComponentPatch);

            return result;
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    protected @Nullable BlockState getPlacementState(BlockPlaceContext context) {
        if (context.getItemInHand().getItem() instanceof BlockItem blockItem) {
            BlockState stateForPlacement = blockItem.getBlock().getStateForPlacement(context);
            return stateForPlacement != null && this.canPlace(context, stateForPlacement) ? stateForPlacement : null;
        }
        return null;
    }

    // get the index of a random item stack which has BlockItems
    // weighted by the amount of items in that stack
    protected Integer getIndexOfRandomNonEmptyStack(List<ItemStack> itemStacks) {
        record IndexToProb(int index, int prob) {}
        ArrayList<IndexToProb> indexesToProbabilities = new ArrayList<>();

        // for each itemstack that is not empty and contains blockitems,
        // add its index and the 'running probability' to the list
        int runningProbability = 0;
        for (int i = 0; i < itemStacks.size(); i++) {
            ItemStack itemStack = itemStacks.get(i);
            if (itemStack.count() != 0 && itemStack.getItem() instanceof BlockItem) {
                runningProbability += itemStack.count();
                indexesToProbabilities.add(new IndexToProb(i, runningProbability));
            }
        }

        if (indexesToProbabilities.isEmpty()) {
            return -1;
        }

        // generate a random number from 0 to (number of eligible items)
        // step through our list; if the number is less than the 'running 'probability' value then we pick than index
        int rand = random.nextInt(runningProbability);
        for (IndexToProb indexToProb : indexesToProbabilities) {
            if (rand < indexToProb.prob) {
                return indexToProb.index;
            }
        }

        return -1;
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }
}
