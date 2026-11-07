package com.craigtec.leakyshulkerbox.datagen;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider{
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        valueLookupBuilder(ModTags.Items.LEAKY_SHULKER_BOXES)
                .add(Item.byBlock(ModBlocks.LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.WHITE_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.ORANGE_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.YELLOW_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.LIME_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.PINK_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.GRAY_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.CYAN_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.PURPLE_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.BLUE_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.BROWN_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.GREEN_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.RED_LEAKY_SHULKER_BOX))
                .add(Item.byBlock(ModBlocks.BLACK_LEAKY_SHULKER_BOX));
    }
}
