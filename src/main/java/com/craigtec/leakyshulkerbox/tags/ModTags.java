package com.craigtec.leakyshulkerbox.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.craigtec.leakyshulkerbox.LeakyShulkerBox.id;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> LEAKY_SHULKER_BOXES = createTag("leaky_shulker_boxes");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, id(name));
        }
    }

    public static class Items {
        public static final TagKey<Item> LEAKY_SHULKER_BOXES = createTag("leaky_shulker_boxes");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, id(name));
        }
    }
}
