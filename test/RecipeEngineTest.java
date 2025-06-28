import data.Recipe;
import data.RecipeBook;
import data.UserPantry;
import domain.Ingredient;
import engine.RecipeEngine;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeEngineTest {

    @Test
    public void testMatchPercentage() {
        UserPantry pantry = new UserPantry();
        pantry.addIngredient(new Ingredient("egg"));
        pantry.addIngredient(new Ingredient("flour"));

        Recipe recipe = new Recipe("Pancakes",
                Set.of(new Ingredient("egg"), new Ingredient("flour"), new Ingredient("milk")),
                300,
                Set.of("breakfast"));

        double match = RecipeEngine.match(pantry, recipe);
        assertEquals(66.66, match, 1.0); // 2 out of 3 ingredients
    }

    @Test
    public void testRecommendationFilter() {
        UserPantry pantry = new UserPantry();
        pantry.addIngredient(new Ingredient("cheese"));
        pantry.addIngredient(new Ingredient("tomato"));
        pantry.addIngredient(new Ingredient("basil"));

        Recipe recipe = new Recipe("Caprese",
                Set.of(new Ingredient("cheese"), new Ingredient("tomato"), new Ingredient("basil")),
                250,
                Set.of("vegetarian"));

RecipeBook book = new RecipeBook();
book.addRecipe(recipe);
List<Recipe> recommended = RecipeEngine.recommend(pantry, book, 300, Set.of("vegetarian"));

        assertEquals(1, recommended.size());
        assertEquals("Caprese", recommended.get(0).getName());
    }
}
