package com.craigtec.leakyshulkerbox.block;

import com.craigtec.leakyshulkerbox.block.custom.LeakyShulkerBoxBlock;
import com.craigtec.leakyshulkerbox.block.custom.entity.custom.LeakyShulkerBoxBlockEntity;
import com.craigtec.leakyshulkerbox.item.custom.LeakyShulkerBoxBlockItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.ShulkerBoxDispenseBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

import static com.craigtec.leakyshulkerbox.LeakyShulkerBox.id;

public class ModBlocks {
    private static final BlockBehaviour.StatePredicate NOT_CLOSED_LEAKY_SHULKER = (_, level, pos) -> {
        BlockEntity patt0$temp = level.getBlockEntity(pos);
        if (patt0$temp instanceof LeakyShulkerBoxBlockEntity leakyShulkerBoxBlockEntity) {
            return leakyShulkerBoxBlockEntity.isClosed();
        } else {
            return true;
        }
    };

    private static BlockBehaviour.Properties leakyShulkerBoxProperties(final MapColor mapColor) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).forceSolidOn().strength(2.0F).dynamicShape().noOcclusion().isSuffocating(NOT_CLOSED_LEAKY_SHULKER).isViewBlocking(NOT_CLOSED_LEAKY_SHULKER).pushReaction(PushReaction.DESTROY);
    }

    public static final Block LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(null, p),
            leakyShulkerBoxProperties(MapColor.COLOR_PURPLE)
    );

    public static final Block WHITE_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "white_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.WHITE, p),
            leakyShulkerBoxProperties(MapColor.SNOW)
    );

    public static final Block ORANGE_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "orange_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.ORANGE, p),
            leakyShulkerBoxProperties(MapColor.COLOR_ORANGE)
    );

    public static final Block MAGENTA_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "magenta_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.MAGENTA, p),
            leakyShulkerBoxProperties(MapColor.COLOR_MAGENTA)
    );

    public static final Block LIGHT_BLUE_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "light_blue_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.LIGHT_BLUE, p),
            leakyShulkerBoxProperties(MapColor.COLOR_LIGHT_BLUE)
    );

    public static final Block YELLOW_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "yellow_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.YELLOW, p),
            leakyShulkerBoxProperties(MapColor.COLOR_YELLOW)
    );

    public static final Block LIME_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "lime_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.LIME, p),
            leakyShulkerBoxProperties(MapColor.COLOR_LIGHT_GREEN)
    );

    public static final Block PINK_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "pink_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.PINK, p),
            leakyShulkerBoxProperties(MapColor.COLOR_PINK)
    );

    public static final Block GRAY_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "gray_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.GRAY, p),
            leakyShulkerBoxProperties(MapColor.COLOR_GRAY)
    );

    public static final Block LIGHT_GRAY_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "light_gray_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.LIGHT_GRAY, p),
            leakyShulkerBoxProperties(MapColor.COLOR_LIGHT_GRAY)
    );

    public static final Block CYAN_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "cyan_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.CYAN, p),
            leakyShulkerBoxProperties(MapColor.COLOR_CYAN)
    );

    public static final Block PURPLE_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "purple_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.PURPLE, p),
            leakyShulkerBoxProperties(MapColor.TERRACOTTA_PURPLE)
    );

    public static final Block BLUE_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "blue_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.BLUE, p),
            leakyShulkerBoxProperties(MapColor.COLOR_BLUE)
    );

    public static final Block BROWN_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "brown_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.BROWN, p),
            leakyShulkerBoxProperties(MapColor.COLOR_BROWN)
    );

    public static final Block GREEN_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "green_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.GREEN, p),
            leakyShulkerBoxProperties(MapColor.COLOR_GREEN)
    );

    public static final Block RED_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "red_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.RED, p),
            leakyShulkerBoxProperties(MapColor.COLOR_RED)
    );

    public static final Block BLACK_LEAKY_SHULKER_BOX = registerLeakyShulkerBox(
            "black_leaky_shulker_box",
            (p) -> new LeakyShulkerBoxBlock(DyeColor.BLACK, p),
            leakyShulkerBoxProperties(MapColor.COLOR_BLACK)
    );

    public static ResourceKey<Block> getResourceKey(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    public static ResourceKey<Item> getResourceKey(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).get();
    }

    private static Block registerLeakyShulkerBox(String name, final Function<BlockBehaviour.Properties, Block> factory, final BlockBehaviour.Properties properties) {
        Block leakyShulkerBoxBlock = factory.apply(
                properties.setId(
                        ResourceKey.create(
                                Registries.BLOCK,
                                id(name)
                        )
                )
        );
        Registry.register(
                BuiltInRegistries.ITEM,
                id(name),
                new LeakyShulkerBoxBlockItem(
                        leakyShulkerBoxBlock,
                        new Item.Properties()
                                .stacksTo(1)
                                .component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
                                .useBlockDescriptionPrefix()
                                .requiredFeatures(leakyShulkerBoxBlock.requiredFeatures())
                                .setId(
                                ResourceKey.create(
                                        Registries.ITEM,
                                        id(name)
                                )
                        )
                )
        );
        return Registry.register(
                BuiltInRegistries.BLOCK,
                id(name),
                leakyShulkerBoxBlock
        );
    }

    public static void registerModBlocks() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COLORED_BLOCKS).register(output -> {
            output.accept(LEAKY_SHULKER_BOX);
            output.accept(WHITE_LEAKY_SHULKER_BOX);
            output.accept(ORANGE_LEAKY_SHULKER_BOX);
            output.accept(MAGENTA_LEAKY_SHULKER_BOX);
            output.accept(LIGHT_BLUE_LEAKY_SHULKER_BOX);
            output.accept(YELLOW_LEAKY_SHULKER_BOX);
            output.accept(LIME_LEAKY_SHULKER_BOX);
            output.accept(PINK_LEAKY_SHULKER_BOX);
            output.accept(GRAY_LEAKY_SHULKER_BOX);
            output.accept(LIGHT_GRAY_LEAKY_SHULKER_BOX);
            output.accept(CYAN_LEAKY_SHULKER_BOX);
            output.accept(PURPLE_LEAKY_SHULKER_BOX);
            output.accept(BLUE_LEAKY_SHULKER_BOX);
            output.accept(BROWN_LEAKY_SHULKER_BOX);
            output.accept(GREEN_LEAKY_SHULKER_BOX);
            output.accept(RED_LEAKY_SHULKER_BOX);
            output.accept(BLACK_LEAKY_SHULKER_BOX);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> {
            output.accept(LEAKY_SHULKER_BOX);
            output.accept(WHITE_LEAKY_SHULKER_BOX);
            output.accept(ORANGE_LEAKY_SHULKER_BOX);
            output.accept(MAGENTA_LEAKY_SHULKER_BOX);
            output.accept(LIGHT_BLUE_LEAKY_SHULKER_BOX);
            output.accept(YELLOW_LEAKY_SHULKER_BOX);
            output.accept(LIME_LEAKY_SHULKER_BOX);
            output.accept(PINK_LEAKY_SHULKER_BOX);
            output.accept(GRAY_LEAKY_SHULKER_BOX);
            output.accept(LIGHT_GRAY_LEAKY_SHULKER_BOX);
            output.accept(CYAN_LEAKY_SHULKER_BOX);
            output.accept(PURPLE_LEAKY_SHULKER_BOX);
            output.accept(BLUE_LEAKY_SHULKER_BOX);
            output.accept(BROWN_LEAKY_SHULKER_BOX);
            output.accept(GREEN_LEAKY_SHULKER_BOX);
            output.accept(RED_LEAKY_SHULKER_BOX);
            output.accept(BLACK_LEAKY_SHULKER_BOX);
        });

        ShulkerBoxDispenseBehavior shulkerBoxDispenseBehavior = new ShulkerBoxDispenseBehavior();
        DispenserBlock.registerBehavior(Item.byBlock(LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(WHITE_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(ORANGE_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(MAGENTA_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(LIGHT_BLUE_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(YELLOW_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(LIME_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(PINK_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(GRAY_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(LIGHT_GRAY_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(CYAN_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(PURPLE_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(BLUE_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(GREEN_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(BROWN_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(RED_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
        DispenserBlock.registerBehavior(Item.byBlock(BLACK_LEAKY_SHULKER_BOX), shulkerBoxDispenseBehavior);
    }
}
