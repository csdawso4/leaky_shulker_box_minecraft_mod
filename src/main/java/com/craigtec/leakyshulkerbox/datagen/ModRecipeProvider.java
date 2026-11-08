package com.craigtec.leakyshulkerbox.datagen;

import com.craigtec.leakyshulkerbox.block.ModBlocks;
import com.craigtec.leakyshulkerbox.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.TransmuteRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                leakyShulkerBoxRecipe(Items.SHULKER_BOX, Item.byBlock(ModBlocks.LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.white(), Item.byBlock(ModBlocks.WHITE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.orange(), Item.byBlock(ModBlocks.ORANGE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.magenta(), Item.byBlock(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.lightBlue(), Item.byBlock(ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.yellow(), Item.byBlock(ModBlocks.YELLOW_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.lime(), Item.byBlock(ModBlocks.LIME_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.pink(), Item.byBlock(ModBlocks.PINK_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.gray(), Item.byBlock(ModBlocks.GRAY_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.lightGray(), Item.byBlock(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.cyan(), Item.byBlock(ModBlocks.CYAN_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.purple(), Item.byBlock(ModBlocks.PURPLE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.blue(), Item.byBlock(ModBlocks.BLUE_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.brown(), Item.byBlock(ModBlocks.BROWN_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.green(), Item.byBlock(ModBlocks.GREEN_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.red(), Item.byBlock(ModBlocks.RED_LEAKY_SHULKER_BOX));
                leakyShulkerBoxRecipe(Items.DYED_SHULKER_BOX.black(), Item.byBlock(ModBlocks.BLACK_LEAKY_SHULKER_BOX));

                dyedLeakyShulkerBoxRecipe(Items.DYE.white(), Item.byBlock(ModBlocks.WHITE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.orange(), Item.byBlock(ModBlocks.ORANGE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.magenta(), Item.byBlock(ModBlocks.MAGENTA_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.lightBlue(), Item.byBlock(ModBlocks.LIGHT_BLUE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.yellow(), Item.byBlock(ModBlocks.YELLOW_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.lime(), Item.byBlock(ModBlocks.LIME_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.pink(), Item.byBlock(ModBlocks.PINK_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.gray(), Item.byBlock(ModBlocks.GRAY_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.lightGray(), Item.byBlock(ModBlocks.LIGHT_GRAY_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.cyan(), Item.byBlock(ModBlocks.CYAN_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.purple(), Item.byBlock(ModBlocks.PURPLE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.blue(), Item.byBlock(ModBlocks.BLUE_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.brown(), Item.byBlock(ModBlocks.BROWN_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.green(), Item.byBlock(ModBlocks.GREEN_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.red(), Item.byBlock(ModBlocks.RED_LEAKY_SHULKER_BOX));
                dyedLeakyShulkerBoxRecipe(Items.DYE.black(), Item.byBlock(ModBlocks.BLACK_LEAKY_SHULKER_BOX));
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
