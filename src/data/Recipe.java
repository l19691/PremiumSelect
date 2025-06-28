package data;

import domain.Ingredient;

import java.util.Objects;
import java.util.Set;

public class Recipe {
    private final String name;
    private final Set<Ingredient> ingredients;
    private final int calories;
    private final Set<String> tags;

    public Recipe(String name, Set<Ingredient> ingredients, int calories, Set<String> tags) {
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getCalories() {
        return calories;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return name + " (" + calories + " kcal) " + tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe other)) return false;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}

