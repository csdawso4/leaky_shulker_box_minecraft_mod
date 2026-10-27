package com.craigtec.leakyshulkerbox.block.custom.entity;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.block.custom.TestBlock;
import com.craigtec.leakyshulkerbox.block.custom.entity.custom.TestBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.craigtec.leakyshulkerbox.LeakyShulkerBox.id;

public class ModBlockEntities {
    public static final BlockEntityType<TestBlockEntity> TEST_BE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id("test_e"),
                    FabricBlockEntityTypeBuilder.create(TestBlockEntity::new, ModBlocks.TEST_BLOCK).build());

    public static void registerBlockEntities() {

    }
}
