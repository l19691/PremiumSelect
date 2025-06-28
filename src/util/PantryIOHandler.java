package util;

import data.UserPantry;
import domain.Ingredient;
import exception.InvalidInputException;

import java.io.*;

public class PantryIOHandler {
    private static final String FILE_NAME = "pantry.txt";

    public static void save(UserPantry pantry) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Ingredient ingredient : pantry.getIngredients()) {
                writer.write(ingredient.getName());
                writer.newLine();
            }
        }
        LoggerUtility.log("Pantry saved to file.");
    }

    public static UserPantry load() throws IOException {
        UserPantry pantry = new UserPantry();
        File file = new File(FILE_NAME);
        if (!file.exists()) return pantry;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Ingredient i = IngredientFactory.create(line);
                    pantry.addIngredient(i);
                } catch (InvalidInputException e) {
                    LoggerUtility.error("Skipped invalid ingredient: " + line);
                }
            }
        }

        LoggerUtility.log("Pantry loaded from file.");
        return pantry;
    }
}

