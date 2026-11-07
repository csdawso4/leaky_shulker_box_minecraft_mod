package com.craigtec.leakyshulkerbox.datagen;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.TransmuteRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                leakyShulkerBoxRecipe(Items.SHULKER_BOX, Item.byBlock(ModBlocks.LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.WHITE_SHULKER_BOX, Item.byBlock(ModBlocks.WHITE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.ORANGE_SHULKER_BOX, Item.byBlock(ModBlocks.ORANGE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.MAGENTA_SHULKER_BOX, Item.byBlock(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.LIGHT_BLUE_SHULKER_BOX, Item.byBlock(ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.YELLOW_SHULKER_BOX, Item.byBlock(ModBlocks.YELLOW_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.LIME_SHULKER_BOX, Item.byBlock(ModBlocks.LIME_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.PINK_SHULKER_BOX, Item.byBlock(ModBlocks.PINK_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.GRAY_SHULKER_BOX, Item.byBlock(ModBlocks.GRAY_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.LIGHT_GRAY_SHULKER_BOX, Item.byBlock(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.CYAN_SHULKER_BOX, Item.byBlock(ModBlocks.CYAN_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.PURPLE_SHULKER_BOX, Item.byBlock(ModBlocks.PURPLE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.BLUE_SHULKER_BOX, Item.byBlock(ModBlocks.BLUE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.BROWN_SHULKER_BOX, Item.byBlock(ModBlocks.BROWN_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.GREEN_SHULKER_BOX, Item.byBlock(ModBlocks.GREEN_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.RED_SHULKER_BOX, Item.byBlock(ModBlocks.RED_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.BLACK_SHULKER_BOX, Item.byBlock(ModBlocks.BLACK_LEAKY_SHULKER_BOX));

                dyedLeakyShulkerBoxRecipe(Items.WHITE_DYE, Item.byBlock(ModBlocks.WHITE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.ORANGE_DYE, Item.byBlock(ModBlocks.ORANGE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.MAGENTA_DYE, Item.byBlock(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.LIGHT_BLUE_DYE, Item.byBlock(ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.YELLOW_DYE, Item.byBlock(ModBlocks.YELLOW_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.LIME_DYE, Item.byBlock(ModBlocks.LIME_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.PINK_DYE, Item.byBlock(ModBlocks.PINK_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.GRAY_DYE, Item.byBlock(ModBlocks.GRAY_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.LIGHT_GRAY_DYE, Item.byBlock(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.CYAN_DYE, Item.byBlock(ModBlocks.CYAN_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.PURPLE_DYE, Item.byBlock(ModBlocks.PURPLE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.BLUE_DYE, Item.byBlock(ModBlocks.BLUE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.BROWN_DYE, Item.byBlock(ModBlocks.BROWN_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.GREEN_DYE, Item.byBlock(ModBlocks.GREEN_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.RED_DYE, Item.byBlock(ModBlocks.RED_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.BLACK_DYE, Item.byBlock(ModBlocks.BLACK_LEAKY_SHULKER_BOX));
            }

            public void leakyShulkerBoxRecipe(final Item shulkerBox, final Item leakyShulkerBox) {
                TransmuteRecipeBuilder.transmute(RecipeCategory.DECORATIONS, Ingredient.of(shulkerBox), Ingredient.of(Items.SHEARS), leakyShulkerBox)
                        .group("leaky_shulker_box")
                        .unlockedBy(getHasName(shulkerBox), has(shulkerBox))
                        .save(this.output);
            }

            public void dyedLeakyShulkerBoxRecipe(final Item dye, final Item dyedResult) {
                TransmuteRecipeBuilder.transmute(RecipeCategory.DECORATIONS, this.tag(ModTags.Items.LEAKY_SHULKER_BOXES), Ingredient.of(dye), dyedResult)
                        .group("leaky_shulker_box_dye")
                        .unlockedBy("has_leaky_shulker_box", this.has(ModTags.Items.LEAKY_SHULKER_BOXES))
                        .save(this.output, dyedResult + "_from_dye");
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "Leaky-Shulker-Box Recipes";
    }
}
