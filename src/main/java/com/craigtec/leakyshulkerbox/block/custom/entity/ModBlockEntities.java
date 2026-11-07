package com.craigtec.leakyshulkerbox.block.custom.entity;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.block.custom.entity.custom.LeakyShulkerBoxBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.craigtec.leakyshulkerbox.LeakyShulkerBox.id;

public class ModBlockEntities {
    public static final BlockEntityType<LeakyShulkerBoxBlockEntity> LEAKY_SHULKER_BOX =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id("leaky_shulker_box"),
                    FabricBlockEntityTypeBuilder.create(LeakyShulkerBoxBlockEntity::new,
                            ModBlocks.LEAKY_SHULKER_BOX,
                            ModBlocks.WHITE_LEAKY_SHULKER_BOX,
                            ModBlocks.ORANGE_LEAKY_SHULKER_BOX,
                            ModBlocks.MAGENTA_LEAKY_SHULKER_BOX,
                            ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX,
                            ModBlocks.YELLOW_LEAKY_SHULKER_BOX,
                            ModBlocks.LIME_LEAKY_SHULKER_BOX,
                            ModBlocks.PINK_LEAKY_SHULKER_BOX,
                            ModBlocks.GRAY_LEAKY_SHULKER_BOX,
                            ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX,
                            ModBlocks.CYAN_LEAKY_SHULKER_BOX,
                            ModBlocks.PURPLE_LEAKY_SHULKER_BOX,
                            ModBlocks.BLUE_LEAKY_SHULKER_BOX,
                            ModBlocks.BROWN_LEAKY_SHULKER_BOX,
                            ModBlocks.GREEN_LEAKY_SHULKER_BOX,
                            ModBlocks.RED_LEAKY_SHULKER_BOX,
                            ModBlocks.BLACK_LEAKY_SHULKER_BOX
                    ).build());

    public static void registerBlockEntities() {

    }
}
