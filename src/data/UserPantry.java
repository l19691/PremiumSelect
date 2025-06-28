package data;

import domain.Ingredient;

import java.util.HashSet;
import java.util.Set;

public class UserPantry {
    private final Set<Ingredient> ingredients = new HashSet<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void clear() {
        ingredients.clear();
    }

    public PantryMemento saveState() {
        return new PantryMemento(this.ingredients);
    }

    public void restoreState(PantryMemento memento) {
        ingredients.clear();
        ingredients.addAll(memento.getSavedIngredients());
    }

    @Override
    public String toString() {
        if (ingredients.isEmpty()) return "(Pantry is empty)";
        StringBuilder sb = new StringBuilder("Pantry:\n");
        for (Ingredient i : ingredients) {
            sb.append(" - ").append(i).append("\n");
        }
        return sb.toString();
    }
}
