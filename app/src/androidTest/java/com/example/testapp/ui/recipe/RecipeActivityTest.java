package com.example.testapp.ui.recipe;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.testapp.R;
import com.example.testapp.data.local.InMemoryFavorites;
import com.example.testapp.injection.TestRecipeApplication;
import com.example.testapp.test.RecipeRobot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class RecipeActivityTest {

    private static final String CARROTS_ID = "creamed_carrots";
    @Rule
    public ActivityTestRule<RecipeActivity> rule = new ActivityTestRule<>(RecipeActivity.class, true, false);

    @Test
    public void recipeNotFound() {
        new RecipeRobot()
                .launch(rule)
                .noTitle()
                .description(R.string.recipe_not_found);
    }
    @Test
    public void clickToFavorite(){
        launchRecipe(CARROTS_ID);
        onView(withId(R.id.recipe_title)).check(matches(withText("Creamed Carrots")))
                .check(matches(not(isSelected())))
                .perform(click())
                .check(matches(isSelected()));
    }
    @Test
    public void alreadyFavorite(){
        new RecipeRobot()
                .setFavorite(CARROTS_ID)
                .launch(rule, CARROTS_ID)
                .isFavorite();
    }

    private void launchRecipe(String id) {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, id);
        rule.launchActivity(intent);
    }

}