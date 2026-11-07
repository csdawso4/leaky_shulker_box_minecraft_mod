package com.craigtec.leakyshulkerbox.datagen;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.block.custom.entity.renderer.LeakyShulkerBoxBlockEntityRenderer;
import com.craigtec.leakyshulkerbox.block.custom.entity.renderer.LeakyShulkerBoxSpecialRenderer;
import com.mojang.math.Transformation;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.LEAKY_SHULKER_BOX, null);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.WHITE_LEAKY_SHULKER_BOX, DyeColor.WHITE);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.ORANGE_LEAKY_SHULKER_BOX, DyeColor.ORANGE);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.MAGENTA_LEAKY_SHULKER_BOX, DyeColor.MAGENTA);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX, DyeColor.LIGHT_BLUE);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.YELLOW_LEAKY_SHULKER_BOX, DyeColor.YELLOW);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.LIME_LEAKY_SHULKER_BOX, DyeColor.LIME);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.PINK_LEAKY_SHULKER_BOX, DyeColor.PINK);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.GRAY_LEAKY_SHULKER_BOX, DyeColor.GRAY);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX, DyeColor.LIGHT_GRAY);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.CYAN_LEAKY_SHULKER_BOX, DyeColor.CYAN);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.PURPLE_LEAKY_SHULKER_BOX, DyeColor.PURPLE);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.BLUE_LEAKY_SHULKER_BOX, DyeColor.BLUE);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.BROWN_LEAKY_SHULKER_BOX, DyeColor.BROWN);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.GREEN_LEAKY_SHULKER_BOX, DyeColor.GREEN);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.RED_LEAKY_SHULKER_BOX, DyeColor.RED);
        createLeakyShulkerBox(blockModelGenerators, ModBlocks.BLACK_LEAKY_SHULKER_BOX, DyeColor.BLACK);
    }

    public final void createLeakyShulkerBox(BlockModelGenerators blockModelGenerators, Block block, final @Nullable DyeColor color) {
        blockModelGenerators.createParticleOnlyBlock(block);
        Item item = block.asItem();
        Identifier baseModel = ModelTemplates.SHULKER_BOX_INVENTORY.create(item, TextureMapping.particle(block), blockModelGenerators.modelOutput);
        Transformation transformation = LeakyShulkerBoxBlockEntityRenderer.modelTransform(Direction.UP);
        ItemModel.Unbaked itemModel = ItemModelUtils.specialModel(baseModel, transformation, new LeakyShulkerBoxSpecialRenderer.Unbaked(color));
        blockModelGenerators.itemModelOutput.accept(item, itemModel);
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerators) {
    }
}
