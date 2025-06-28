package engine;

import data.Recipe;
import data.RecipeBook;
import data.UserPantry;
import domain.Ingredient;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeEngine {

    public static double match(UserPantry pantry, Recipe recipe) {
        Set<Ingredient> userIngredients = pantry.getIngredients();
        Set<Ingredient> required = recipe.getIngredients();

        if (required.isEmpty()) return 0;
        long matched = required.stream().filter(userIngredients::contains).count();

        return (matched * 100.0) / required.size(); // return % match
    }

    public static List<Recipe> recommend(UserPantry pantry, RecipeBook book, int maxCalories, Set<String> tags) {
        return book.getAllRecipes().stream()
            .filter(r -> r.getCalories() <= maxCalories)
            .filter(r -> r.getTags().containsAll(tags))
            .sorted((r1, r2) -> {
                double m1 = match(pantry, r1);
                double m2 = match(pantry, r2);
                return Double.compare(m2, m1); // descending match %
            })
            .collect(Collectors.toList());
    }
}
