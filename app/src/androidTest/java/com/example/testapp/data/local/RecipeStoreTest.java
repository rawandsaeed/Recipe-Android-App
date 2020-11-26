package com.example.testapp.data.local;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.testapp.data.model.Recipe;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeStoreTest {

    @Test
    public void nullDirectory() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore recipeStore = new RecipeStore(context, null);
        assertNotNull(recipeStore);
        assertNotNull(recipeStore.recipes);
        assertEquals(0, recipeStore.recipes.size());
    }
    @Test
    public void count() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore recipeStore = new RecipeStore(context, "recipes");
        assertNotNull(recipeStore);
        assertNotNull(recipeStore.recipes);
        assertEquals(4, recipeStore.recipes.size());
    }

    @Test
    public void getChocolatePudding() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        RecipeStore recipeStore = new RecipeStore(context, "recipes");
        Recipe recipe = recipeStore.getRecipe("chocolate_pudding");
        assertNotNull(recipe);
        assertNotNull("chocolate_pudding", recipe.id);
        assertNotNull("Chocolate Pudding", recipe.title);

    }



}