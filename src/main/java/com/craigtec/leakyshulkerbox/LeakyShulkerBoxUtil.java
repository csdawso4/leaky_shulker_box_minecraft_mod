package com.craigtec.leakyshulkerbox;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import org.jspecify.annotations.Nullable;

import static com.craigtec.leakyshulkerbox.LeakyShulkerBox.MOD_ID;

public class LeakyShulkerBoxUtil {
    public static Identifier getLeakyShulkerBoxTextureLocation(@Nullable DyeColor color) {
        String colorPrefix = "";
        if (color != null) {
            colorPrefix = color.getName() + "_";
        }
        return Identifier.fromNamespaceAndPath(MOD_ID, "textures/entity/leaky_shulker_boxes/" + colorPrefix + "leaky_shulker_box.png");
    }
}
