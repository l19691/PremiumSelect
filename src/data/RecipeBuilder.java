package data;

import domain.Ingredient;

import java.util.HashSet;
import java.util.Set;

public class RecipeBuilder {
    private String name;
    private final Set<Ingredient> ingredients = new HashSet<>();
    private int calories;
    private final Set<String> tags = new HashSet<>();

    public RecipeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public RecipeBuilder addIngredient(Ingredient i) {
        ingredients.add(i);
        return this;
    }

    public RecipeBuilder setCalories(int cals) {
        this.calories = cals;
        return this;
    }

    public RecipeBuilder addTag(String tag) {
        tags.add(tag);
        return this;
    }

    public Recipe build() {
        return new Recipe(name, ingredients, calories, tags);
    }
}
