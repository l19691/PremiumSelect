package data;

import domain.Ingredient;

import java.util.HashSet;
import java.util.Set;

public class PantryMemento {
    private final Set<Ingredient> savedIngredients;

    public PantryMemento(Set<Ingredient> currentPantry) {
        this.savedIngredients = new HashSet<>(currentPantry);
    }

    public Set<Ingredient> getSavedIngredients() {
        return new HashSet<>(savedIngredients);
    }
}
