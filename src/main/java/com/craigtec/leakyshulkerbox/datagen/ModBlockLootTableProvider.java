package com.craigtec.leakyshulkerbox.datagen;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        add(ModBlocks.LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.LEAKY_SHULKER_BOX));
        add(ModBlocks.WHITE_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.WHITE_LEAKY_SHULKER_BOX));
        add(ModBlocks.ORANGE_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.ORANGE_LEAKY_SHULKER_BOX));
        add(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX));
        add(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX));
        add(ModBlocks.YELLOW_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.YELLOW_LEAKY_SHULKER_BOX));
        add(ModBlocks.LIME_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.LIME_LEAKY_SHULKER_BOX));
        add(ModBlocks.PINK_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.PINK_LEAKY_SHULKER_BOX));
        add(ModBlocks.GRAY_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.GRAY_LEAKY_SHULKER_BOX));
        add(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX));
        add(ModBlocks.CYAN_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.CYAN_LEAKY_SHULKER_BOX));
        add(ModBlocks.PURPLE_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.PURPLE_LEAKY_SHULKER_BOX));
        add(ModBlocks.BLUE_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.BLUE_LEAKY_SHULKER_BOX));
        add(ModBlocks.BROWN_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.BROWN_LEAKY_SHULKER_BOX));
        add(ModBlocks.GREEN_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.GREEN_LEAKY_SHULKER_BOX));
        add(ModBlocks.RED_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.RED_LEAKY_SHULKER_BOX));
        add(ModBlocks.BLACK_LEAKY_SHULKER_BOX, createShulkerBoxDrop(ModBlocks.BLACK_LEAKY_SHULKER_BOX));

    }
}
