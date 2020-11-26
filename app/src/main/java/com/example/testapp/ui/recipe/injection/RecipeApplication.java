package com.example.testapp.ui.recipe.injection;

import android.app.Application;

import com.example.testapp.data.local.Favorites;
import com.example.testapp.data.local.SharedPreferencesFavorites;

public class RecipeApplication extends Application {

    private Favorites favorites  = null;

    public Favorites getFavorites() {
        if (favorites == null) {
            favorites = new SharedPreferencesFavorites(this);
        }
        return favorites;
    }
}
