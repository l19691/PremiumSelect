package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecipeBook {
    private final List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public List<Recipe> filterByCalories(int maxCalories) {
        return recipes.stream()
            .filter(r -> r.getCalories() <= maxCalories)
            .collect(Collectors.toList());
    }

    public List<Recipe> filterByTags(Set<String> tags) {
        return recipes.stream()
            .filter(r -> r.getTags().containsAll(tags))
            .collect(Collectors.toList());
    }

    public RecipeIterator getIterator() {
        return new RecipeIterator(recipes);
    }
}
