package com.example.testapp.injection;

import com.example.testapp.data.local.Favorites;
import com.example.testapp.data.local.InMemoryFavorites;
import com.example.testapp.ui.recipe.injection.RecipeApplication;

public class TestRecipeApplication extends RecipeApplication {

    private final Favorites favorites = new InMemoryFavorites();

    @Override
    public Favorites getFavorites() {
        return favorites;
    }
}
