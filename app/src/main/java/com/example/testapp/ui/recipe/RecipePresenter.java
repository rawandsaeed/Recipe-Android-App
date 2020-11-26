package com.example.testapp.ui.recipe;

import android.view.View;

import com.example.testapp.R;
import com.example.testapp.data.local.Favorites;
import com.example.testapp.data.local.RecipeStore;
import com.example.testapp.data.model.Recipe;

public class RecipePresenter implements RecipeContract.Listener{
    private final RecipeStore store;
    private final RecipeContract.View view;
    private final Favorites favorites;
    private Recipe recipe;

    public RecipePresenter(RecipeStore store, RecipeContract.View view, Favorites favorites) {
        this.store = store;
        this.view = view;
        this.favorites = favorites;
    }

    public void loadRecipe(String id) {
        recipe = store.getRecipe(id);
        if (recipe == null) {
            view.showRecipeNotFoundError();
        } else {
            view.setTitle(recipe.title);
            view.setDescription(recipe.description);
            view.setFavorites(favorites.get(recipe.id));
        }
    }

    public void toggleFavorite() {
        if (recipe == null) {
            throw new IllegalStateException();
        }
        boolean result = favorites.toggle(recipe.id);
        view.setFavorites(result);
    }
}

