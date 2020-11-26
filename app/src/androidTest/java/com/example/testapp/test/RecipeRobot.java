package com.example.testapp.test;

import android.content.Intent;

import androidx.annotation.StringRes;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.testapp.R;
import com.example.testapp.data.local.InMemoryFavorites;
import com.example.testapp.data.model.Recipe;
import com.example.testapp.injection.TestRecipeApplication;
import com.example.testapp.ui.recipe.RecipeActivity;

import org.junit.Before;

public class RecipeRobot extends ScreenRobot<RecipeRobot>{

    private final InMemoryFavorites favorites;

    public RecipeRobot(){
        TestRecipeApplication application = (TestRecipeApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
        favorites = (InMemoryFavorites) application.getFavorites();
        favorites.clear();
    }

    public RecipeRobot launch(ActivityTestRule rule){
        rule.launchActivity(null);
        return this;
    }


    public RecipeRobot launch(ActivityTestRule rule, String id){
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        rule.launchActivity(intent);
        return this;
    }

    public RecipeRobot noTitle() {
        return checkIsHidden(R.id.recipe_title);
    }

    public RecipeRobot description(@StringRes int stringId) {
        return checkViewHasText(R.id.recipe_description, stringId);
    }

    public RecipeRobot setFavorite(String id) {
        favorites.put(id, true);
        return this;
    }

    public RecipeRobot isFavorite(){
        return checkIsSelected(R.id.recipe_title);
    }
}
