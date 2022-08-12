package com.kyanite.deeperdarker.datagen.recipes;

import com.kyanite.deeperdarker.DeeperAndDarker;
import com.kyanite.deeperdarker.registry.blocks.DDBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class StonecuttingRecipesProvider extends RecipeProvider implements IConditionBuilder {
    public StonecuttingRecipesProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.SCULK_STONE_SLAB.get(), 2).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_slab_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.SCULK_STONE_STAIRS.get(), 1).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_stairs_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.SCULK_STONE_WALL.get(), 1).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_wall_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.POLISHED_SCULK_STONE.get(), 1).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "polished_sculk_stone_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.POLISHED_SCULK_STONE_SLAB.get(), 2).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "polished_sculk_stone_slab_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.POLISHED_SCULK_STONE_STAIRS.get(), 1).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "polished_sculk_stone_stairs_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.POLISHED_SCULK_STONE_WALL.get(), 1).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "polished_sculk_stone_wall_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.SCULK_STONE_BRICKS.get(), 1).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_bricks_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.SCULK_STONE_BRICK_SLAB.get(), 2).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_slab_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.SCULK_STONE_BRICK_STAIRS.get(), 1).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_stairs_from_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE.get()), DDBlocks.SCULK_STONE_BRICK_WALL.get(), 1).unlockedBy("has_sculk_stone", has(DDBlocks.SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_wall_from_sculk_stone_stonecutting"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.COBBLED_SCULK_STONE.get()), DDBlocks.COBBLED_SCULK_STONE_SLAB.get(), 2).unlockedBy("has_cobbled_sculk_stone", has(DDBlocks.COBBLED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "cobbled_sculk_stone_slab_from_cobbled_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.COBBLED_SCULK_STONE.get()), DDBlocks.COBBLED_SCULK_STONE_STAIRS.get(), 1).unlockedBy("has_cobbled_sculk_stone", has(DDBlocks.COBBLED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "cobbled_sculk_stone_stairs_from_cobbled_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.COBBLED_SCULK_STONE.get()), DDBlocks.COBBLED_SCULK_STONE_WALL.get(), 1).unlockedBy("has_cobbled_sculk_stone", has(DDBlocks.COBBLED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "cobbled_sculk_stone_wall_from_cobbled_sculk_stone_stonecutting"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.POLISHED_SCULK_STONE.get()), DDBlocks.POLISHED_SCULK_STONE_SLAB.get(), 2).unlockedBy("has_polished_sculk_stone", has(DDBlocks.POLISHED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "polished_sculk_stone_slab_from_polished_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.POLISHED_SCULK_STONE.get()), DDBlocks.POLISHED_SCULK_STONE_STAIRS.get(), 1).unlockedBy("has_polished_sculk_stone", has(DDBlocks.POLISHED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "polished_sculk_stone_stairs_from_polished_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.POLISHED_SCULK_STONE.get()), DDBlocks.POLISHED_SCULK_STONE_WALL.get(), 1).unlockedBy("has_polished_sculk_stone", has(DDBlocks.POLISHED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "polished_sculk_stone_wall_from_polished_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.POLISHED_SCULK_STONE.get()), DDBlocks.SCULK_STONE_BRICKS.get(), 1).unlockedBy("has_polished_sculk_stone", has(DDBlocks.POLISHED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_bricks_from_polished_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.POLISHED_SCULK_STONE.get()), DDBlocks.SCULK_STONE_BRICK_SLAB.get(), 2).unlockedBy("has_polished_sculk_stone", has(DDBlocks.POLISHED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_slab_from_polished_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.POLISHED_SCULK_STONE.get()), DDBlocks.SCULK_STONE_BRICK_STAIRS.get(), 1).unlockedBy("has_polished_sculk_stone", has(DDBlocks.POLISHED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_stairs_from_polished_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.POLISHED_SCULK_STONE.get()), DDBlocks.SCULK_STONE_BRICK_WALL.get(), 1).unlockedBy("has_polished_sculk_stone", has(DDBlocks.POLISHED_SCULK_STONE.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_wall_from_polished_sculk_stone_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE_BRICKS.get()), DDBlocks.SCULK_STONE_BRICK_SLAB.get(), 2).unlockedBy("has_sculk_stone_bricks", has(DDBlocks.SCULK_STONE_BRICKS.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_slab_from_sculk_stone_bricks_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE_BRICKS.get()), DDBlocks.SCULK_STONE_BRICK_STAIRS.get(), 1).unlockedBy("has_sculk_stone_bricks", has(DDBlocks.SCULK_STONE_BRICKS.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_stairs_from_sculk_stone_bricks_stonecutting"));
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(DDBlocks.SCULK_STONE_BRICKS.get()), DDBlocks.SCULK_STONE_BRICK_WALL.get(), 1).unlockedBy("has_sculk_stone_bricks", has(DDBlocks.SCULK_STONE_BRICKS.get())).save(consumer, new ResourceLocation(DeeperAndDarker.MOD_ID, "sculk_stone_brick_wall_from_sculk_stone_bricks_stonecutting"));
    }

    @NotNull
    @Override
    public String getName() {
        return "Stonecutting Recipes";
    }
}