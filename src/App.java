import domain.*;
import engine.*;
import data.*;
import util.*;
import exception.*;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        ProductCatalog catalog = new ProductCatalog();
        catalog.preloadSampleProducts();

        System.out.println("=== Welcome to PremiumSelect ===");

        while (true) {
            System.out.print("\nEnter username (or 'exit' to quit): ");
            String username = scanner.nextLine().trim().toLowerCase();
            if (username.equals("exit")) break;

            System.out.print("Role? [admin/customer]: ");
            String role = scanner.nextLine().trim().toLowerCase();

            userManager.login(username, role);
            User user = userManager.getCurrentUser();

            if (user.getRole().equals("customer")) {
                boolean customerSession = true;
                while (customerSession) {
                    System.out.println("\n--- CUSTOMER MENU ---");
                    System.out.println("1. View pantry");
                    System.out.println("2. Add ingredient");
                    System.out.println("3. Recommend recipes");
                    System.out.println("4. Add product to cart");
                    System.out.println("5. View cart total");
                    System.out.println("6. Checkout");
                    System.out.println("7. Add wallet funds");
                    System.out.println("8. Logout");

                    System.out.print("Choice: ");
                    String input = scanner.nextLine().trim();

                    switch (input) {
                        case "1" -> System.out.println(user.getPantry());

                        case "2" -> {
                            System.out.print("Enter ingredient(s): ");
                            String ing = scanner.nextLine();
                            try {
                                user.getPantry().addIngredient(IngredientFactory.create(ing));
                                LoggerUtility.log("Added ingredient: " + ing);
                            } catch (InvalidInputException e) {
                                System.out.println("❌ Invalid: " + e.getMessage());
                            }
                        }

                        case "3" -> {
                            System.out.print("Max calories: ");
                            int cal = Integer.parseInt(scanner.nextLine());
                            System.out.print("Tags (comma-separated): ");
                            Set<String> tags = Set.of(scanner.nextLine().toLowerCase().split(","));

                            RecipeBook book = new RecipeBook(); // optional: preload recipes
                            List<Recipe> matches = RecipeEngine.recommend(user.getPantry(), book, cal, tags);

                            if (matches.isEmpty()) System.out.println("No matches.");
                            else for (Recipe r : matches) {
                                double match = RecipeEngine.match(user.getPantry(), r);
                                System.out.printf("✓ %s (%.1f%% match, %d kcal, tags: %s)%n",
                                        r.getName(), match, r.getCalories(), r.getTags());
                            }
                        }

                        case "4" -> {
                            System.out.print("Enter product name: ");
                            String name = scanner.nextLine();
                            Product p = catalog.getProductByName(name);
                            if (p != null) {
                                user.getCart().addProduct(p);
                                System.out.println("Added: " + p.getName());
                            } else {
                                System.out.println("❌ Product not found.");
                            }
                        }

                        case "5" -> {
                            System.out.print("Enter group size: ");
                            int size = Integer.parseInt(scanner.nextLine());
                            user.getCart().setGroupSize(size);
                            double total = user.getCart().calculateTotal();
                            System.out.printf("Total for %d users: €%.2f%n", size, total);
                        }

                        case "6" -> {
                            List<Product> items = user.getCart().getProducts();
                            double total = user.getCart().calculateTotal();

                            if (user.deduct(total)) {
                                Order order = new Order(user.getUsername(), items, total);
                                order.saveToFile();
                                System.out.printf("✅ Order placed. Remaining: €%.2f\n", user.getWallet());
                                user.getCart().getProducts().clear();
                            } else {
                                System.out.println("❌ Not enough funds.");
                            }
                        }

                        case "7" -> {
                            System.out.print("Amount to add: ");
                            double amount = Double.parseDouble(scanner.nextLine());
                            user.addFunds(amount);
                            System.out.printf("💰 Wallet updated: €%.2f\n", user.getWallet());
                        }

                        case "8" -> {
                            userManager.logout();
                            customerSession = false;
                            System.out.println("🔒 Logged out.");
                        }

                        default -> System.out.println("❌ Invalid option.");
                    }
                }
            } else if (user.getRole().equals("admin")) {
                boolean adminSession = true;
                while (adminSession) {
                    System.out.println("\n--- ADMIN MENU ---");
                    System.out.println("1. View all users");
                    System.out.println("2. Add new product");
                    System.out.println("3. Remove product");
                    System.out.println("4. Add recipe");
                    System.out.println("5. View product catalog");
                    System.out.println("6. Logout");

                    System.out.print("Choice: ");
                    String input = scanner.nextLine().trim();

                    switch (input) {
                        case "1" -> userManager.getAllUsers().values().forEach(System.out::println);

                        case "2" -> {
                            System.out.print("Product name: ");
                            String name = scanner.nextLine();
                            System.out.print("Price: ");
                            double price = Double.parseDouble(scanner.nextLine());
                            System.out.print("Tags (comma-separated): ");
                            Set<String> tags = Set.of(scanner.nextLine().split(","));

                            Product product = new Product(name, price, tags);
                            catalog.addProduct(product);
                            System.out.println("✅ Added: " + product);
                        }

                        case "3" -> {
                            System.out.print("Product to remove: ");
                            String name = scanner.nextLine();
                            catalog.removeProduct(name);
                            System.out.println("✅ Removed (if existed).");
                        }

                        case "4" -> {
                            RecipeBuilder builder = new RecipeBuilder();
                            System.out.print("Recipe name: ");
                            builder.setName(scanner.nextLine());

                            System.out.print("Ingredients (comma-separated): ");
                            for (String s : scanner.nextLine().split(",")) {
                                builder.addIngredient(new Ingredient(s.trim()));
                            }

                            System.out.print("Calories: ");
                            builder.setCalories(Integer.parseInt(scanner.nextLine()));

                            System.out.print("Tags (comma-separated): ");
                            for (String tag : scanner.nextLine().split(",")) {
                                builder.addTag(tag.trim());
                            }

                            Recipe r = builder.build();
                            System.out.println("✅ Recipe created: " + r);
                        }

                        case "5" -> catalog.getAll().forEach(System.out::println);

                        case "6" -> {
                            userManager.logout();
                            adminSession = false;
                            System.out.println("🔒 Logged out.");
                        }

                        default -> System.out.println("❌ Invalid option.");
                    }
                }
            }
        }

        System.out.println("👋 Goodbye.");
        scanner.close();
    }
}
