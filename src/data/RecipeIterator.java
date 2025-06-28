package data;

import java.util.List;

public class RecipeIterator {
    private final List<Recipe> recipes;
    private int index = 0;

    public RecipeIterator(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public boolean hasNext() {
        return index < recipes.size();
    }

    public Recipe next() {
        return recipes.get(index++);
    }

    public void reset() {
        index = 0;
    }
}
