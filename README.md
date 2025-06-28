# PremiumSelect — Java Final Project

PremiumSelect is a CLI-based Java SE application designed to simulate a small-scale marketplace platform.  
It supports group pricing deals, personalized recipe recommendations, wallet management, and admin-level control over inventory and users.

---

## Features

- Multi-user login (admin / customer)
- Product catalog with tag/category filtering
- Smart recipe engine (by pantry, calorie, tags)
- GroupCart with strategy-based discounting
- Order tracking + wallet deduction
- Observer pattern to notify low wallet balance
- Clean object-oriented architecture (OOP)
- Save/load pantry, recipes, and receipts

---

## Design Patterns Used

| Pattern                 | Used For                                                |
| ----------------------- | ------------------------------------------------------- |
| **Factory**             | `IngredientFactory`                                     |
| **Composite**           | `CompositeIngredient`, `CompositeProduct`               |
| **Iterator**            | `RecipeIterator`, `CartIterator`                        |
| **Strategy**            | `DealEngine`, `FlatDiscountDeal`, `ThresholdUnlockDeal` |
| **Builder**             | `RecipeBuilder`                                         |
| **Memento**             | `PantryMemento` for pantry snapshots                    |
| **Observer**            | `UserObserver` + `EmailNotifier`                        |
| **Exception Shielding** | Input validation, graceful errors                       |
| **Logging**             | `LoggerUtility` → `premiumselect.log`                   |

---

## Project Structure

PremiumSelect/
├── src/ → Main Java classes
├── test/ → JUnit tests (RecipeEngineTest)
├── lib/ → JUnit 5 & dependencies
├── .vscode/ → VS Code classpath config
├── premiumselect.log → Log file
└── README.md

---

## How to Run Tests

javac -cp "lib/_;out" -d testbin test/_.java
java -cp "lib/\*;testbin;out" org.junit.platform.console.ConsoleLauncher --scan-classpath

## UML Diagram

![UML](image.png)
