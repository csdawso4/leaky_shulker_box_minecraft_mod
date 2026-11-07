package com.craigtec.leakyshulkerbox.datagen;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        valueLookupBuilder(ModTags.Blocks.LEAKY_SHULKER_BOXES)
                .add(ModBlocks.LEAKY_SHULKER_BOX)
                .add(ModBlocks.WHITE_LEAKY_SHULKER_BOX)
                .add(ModBlocks.ORANGE_LEAKY_SHULKER_BOX)
                .add(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX)
                .add(ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX)
                .add(ModBlocks.YELLOW_LEAKY_SHULKER_BOX)
                .add(ModBlocks.LIME_LEAKY_SHULKER_BOX)
                .add(ModBlocks.PINK_LEAKY_SHULKER_BOX)
                .add(ModBlocks.GRAY_LEAKY_SHULKER_BOX)
                .add(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX)
                .add(ModBlocks.CYAN_LEAKY_SHULKER_BOX)
                .add(ModBlocks.PURPLE_LEAKY_SHULKER_BOX)
                .add(ModBlocks.BLUE_LEAKY_SHULKER_BOX)
                .add(ModBlocks.BROWN_LEAKY_SHULKER_BOX)
                .add(ModBlocks.GREEN_LEAKY_SHULKER_BOX)
                .add(ModBlocks.RED_LEAKY_SHULKER_BOX)
                .add(ModBlocks.BLACK_LEAKY_SHULKER_BOX);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(ModTags.Blocks.LEAKY_SHULKER_BOXES);

        valueLookupBuilder(BlockTags.GUARDED_BY_PIGLINS)
                .addTag(ModTags.Blocks.LEAKY_SHULKER_BOXES);
    }
}
