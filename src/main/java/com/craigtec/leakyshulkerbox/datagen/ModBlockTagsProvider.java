package com.craigtec.leakyshulkerbox.datagen;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        tag(ModTags.Blocks.LEAKY_SHULKER_BOXES)
                .add(ModBlocks.getResourceKey(ModBlocks.LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.WHITE_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.ORANGE_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.YELLOW_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.LIME_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.PINK_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.GRAY_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.CYAN_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.PURPLE_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.BLUE_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.BROWN_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.GREEN_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.RED_LEAKY_SHULKER_BOX))
                .add(ModBlocks.getResourceKey(ModBlocks.BLACK_LEAKY_SHULKER_BOX));

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(ModTags.Blocks.LEAKY_SHULKER_BOXES);

        tag(BlockTags.GUARDED_BY_PIGLINS)
                .addTag(ModTags.Blocks.LEAKY_SHULKER_BOXES);
    }
}
