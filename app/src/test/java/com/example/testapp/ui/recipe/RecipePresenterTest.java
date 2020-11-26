package com.example.testapp.ui.recipe;

import com.example.testapp.data.local.Favorites;
import com.example.testapp.data.local.RecipeStore;
import com.example.testapp.data.model.Recipe;
import com.example.testapp.data.model.RecipeTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.InputStream;

import static org.junit.Assert.*;

public class RecipePresenterTest {

    private RecipeStore recipeStore;
    private Favorites favorites;
    private RecipeContract.View view;
    private RecipePresenter presenter;

    @Before
    public void setup(){
        recipeStore = Mockito.mock(RecipeStore.class);
        favorites = Mockito.mock(Favorites.class);
        view = Mockito.mock(RecipeContract.View.class);
        presenter = new RecipePresenter(recipeStore, view, favorites);
    }

    @Test
    public void recipeNotFound() {
        Mockito.when(recipeStore.getRecipe(Mockito.anyString())).thenReturn(null);
        presenter.loadRecipe("no_such_recipe");
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError();
    }

    @Test(expected = IllegalStateException.class)
    public void toggleWithoutLoad(){
        presenter.toggleFavorite();
    }

    @Test
    public void loadWaterAndFavorite(){
        InputStream stream = RecipePresenterTest.class.getResourceAsStream("/recipes/water.txt");
        Recipe recipe = Recipe.readFromStream(stream);
        Mockito.when(recipeStore.getRecipe(Mockito.anyString())).thenReturn(recipe);
        Mockito.when(favorites.toggle(Mockito.anyString())).thenReturn(true);

        presenter.loadRecipe("water");
        presenter.toggleFavorite();

        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(view, Mockito.times(2)).setFavorites(captor.capture());
        assertFalse(captor.getAllValues().get(0));
        assertTrue(captor.getAllValues().get(1));

    }
}