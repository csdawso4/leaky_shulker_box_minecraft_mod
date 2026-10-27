package com.craigtec.leakyshulkerbox.block;

import com.craigtec.leakyshulkerbox.block.custom.TestBlock;
import com.craigtec.leakyshulkerbox.item.custom.TestBlockItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

import static com.craigtec.leakyshulkerbox.LeakyShulkerBox.id;

public class ModBlocks {
    public static final Block TEST_BLOCK = registerTestBlock();

    private static Block registerTestBlock() {
        Block toRegister = new TestBlock(BlockBehaviour.Properties.of().setId(
                ResourceKey.create(
                        Registries.BLOCK,
                        id("test_block")
                )
        ));
        Registry.register(
                BuiltInRegistries.ITEM,
                id("test_block"),
                new TestBlockItem(
                        toRegister,
                        new Item.Properties().useBlockDescriptionPrefix().setId(
                                ResourceKey.create(
                                        Registries.ITEM,
                                        id("test_block")
                                )
                        )
                )
        );
        return Registry.register(
                BuiltInRegistries.BLOCK,
                id("test_block"),
                toRegister
        );
    }


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(
                BlockBehaviour.Properties.of().setId(
                        ResourceKey.create(
                                Registries.BLOCK,
                                id(name)
                        )
                )
        );
        registerBlockItem(name, toRegister);
        return Registry.register(
                BuiltInRegistries.BLOCK,
                id(name),
                toRegister
        );
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(
                BuiltInRegistries.ITEM,
                id(name),
                new BlockItem(
                        block,
                        new Item.Properties().useBlockDescriptionPrefix().setId(
                                ResourceKey.create(
                                        Registries.ITEM,
                                        id(name)
                                )
                        )
                )
        );
    }

    public static void registerModBlocks() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
            output.accept(TEST_BLOCK);
        });
    }
}
