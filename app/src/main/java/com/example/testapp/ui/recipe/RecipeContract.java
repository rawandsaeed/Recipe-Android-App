package com.example.testapp.ui.recipe;

public interface RecipeContract {

    interface View {
        void showRecipeNotFoundError();
        void setTitle(String title);
        void setDescription(String description);
        void setFavorites(boolean favorite);
    }

    interface Listener {

    }
}
