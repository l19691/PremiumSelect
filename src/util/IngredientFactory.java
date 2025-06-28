package util;

import domain.CompositeIngredient;
import domain.Ingredient;
import exception.InvalidInputException;

public class IngredientFactory {

    public static Ingredient create(String input) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException("Ingredient name cannot be empty.");
        }

        input = input.trim().toLowerCase();

        if (input.contains(",")) {
            CompositeIngredient composite = new CompositeIngredient("grouped-" + input.hashCode());
            for (String name : input.split(",")) {
                if (!name.trim().isEmpty()) {
                    composite.addIngredient(new Ingredient(name.trim()));
                }
            }
            return composite;
        }

        return new Ingredient(input);
    }
}

