package domain;

import java.util.ArrayList;
import java.util.List;

public class CompositeIngredient extends Ingredient {
    private final List<Ingredient> ingredients;

    public CompositeIngredient(String name) {
        super(name);
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getName() + ": [");
        for (int i = 0; i < ingredients.size(); i++) {
            sb.append(ingredients.get(i).getName());
            if (i < ingredients.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
