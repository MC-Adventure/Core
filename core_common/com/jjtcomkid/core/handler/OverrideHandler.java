package com.jjtcomkid.core.handler;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.item.crafting.RecipesMapCloning;
import net.minecraft.item.crafting.RecipesMapExtending;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.jjtcomkid.core.Core;

/**
 * Core
 * 
 * @author jjtcomkid
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class OverrideHandler {
    public static String getBlockName(Block block) {
        try {
            for (Field blockField : Block.class.getDeclaredFields())
                if (blockField.getType() == Block.class && blockField.get(blockField).equals(block))
                    return blockField.getName();
        } catch (Exception e) {
        }
        Core.logger.severe("Unable to retrive object name of " + block.toString());
        return "";
    }

    public static int removeRecipesWithResult(ItemStack removeRecipeResult) {
        @SuppressWarnings("rawtypes")
        List recipes = CraftingManager.getInstance().getRecipeList();
        ItemStack recipeResult = null;
        int count = 0;

        for (int i = 0; i < recipes.size(); i++) {
            IRecipe recipe = (IRecipe) recipes.get(i);

            if (recipe instanceof ShapedRecipes) {
                ShapedRecipes shapedRecipe = (ShapedRecipes) recipe;
                recipeResult = shapedRecipe.getRecipeOutput();
            } else if (recipe instanceof ShapelessRecipes) {
                ShapelessRecipes shapelessRecipe = (ShapelessRecipes) recipe;
                recipeResult = shapelessRecipe.getRecipeOutput();
            } else if (recipe instanceof RecipesArmorDyes) {
                RecipesArmorDyes armorDyeRecipe = (RecipesArmorDyes) recipe;
                recipeResult = armorDyeRecipe.getRecipeOutput();
            } else if (recipe instanceof RecipesMapCloning) {
                RecipesMapCloning mapCloningRecipe = (RecipesMapCloning) recipe;
                recipeResult = mapCloningRecipe.getRecipeOutput();
            } else if (recipe instanceof RecipesMapExtending) {
                RecipesMapExtending mapExtendingRecipe = (RecipesMapExtending) recipe;
                recipeResult = mapExtendingRecipe.getRecipeOutput();
            } else if (recipe instanceof RecipeFireworks) {
                RecipeFireworks fireworkRecipe = (RecipeFireworks) recipe;
                recipeResult = fireworkRecipe.getRecipeOutput();
            } else if (recipe instanceof ShapedOreRecipe) {
                ShapedOreRecipe shapedOreRecipe = (ShapedOreRecipe) recipe;
                recipeResult = shapedOreRecipe.getRecipeOutput();
            } else if (recipe instanceof ShapelessOreRecipe) {
                ShapelessOreRecipe shapelessOreRecipe = (ShapelessOreRecipe) recipe;
                recipeResult = shapelessOreRecipe.getRecipeOutput();
            } else
                Core.logger.warning("Found unhandled recipe: " + recipe.toString());

            if (ItemStack.areItemStacksEqual(recipeResult, removeRecipeResult)) {
                recipes.remove(i);
                i--;
                count++;
            }
        }
        return count;
    }

    public static boolean removeRecipeWithResult(ItemStack removeRecipeResult) {
        int x = removeRecipesWithResult(removeRecipeResult);
        if (x == 1)
            return true;
        else if (x > 0) {
            Core.logger.warning("Removed more than the intended 1 recipe with result of " + removeRecipeResult.toString());
            return true;
        } else
            return false;

    }

    public static boolean replaceBlock(Block oldBlock, Block newBlock) {
        String oldBlockName = getBlockName(oldBlock);
        try {
            Field blockField = Block.class.getDeclaredField(oldBlockName);
            blockField.setAccessible(true);
            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(blockField, blockField.getModifiers() & 0xFFFFFFEF);
            blockField.set(Block.class, newBlock);
            return true;
        } catch (Exception e) {
        }
        Core.logger.severe("Unable to replace " + oldBlock.toString() + " with " + newBlock.toString());
        return false;
    }
}
