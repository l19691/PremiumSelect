# PremiumSelect - Java Final Project

## Overview

PremiumSelect is a CLI-based e-commerce simulation platform developed in Java SE.  
It allows multi-user login (admin or customer), shopping with group-based pricing, recipe recommendation based on pantry ingredients, and order/invoice tracking.

---

## Key Features

- Multi-user login system (admin/customer)
- Product catalog with tag and category filtering
- Recipe recommendation engine based on pantry + dietary filters
- GroupCart with pricing deals using Strategy pattern
- Order placement with receipt generation
- Wallet management with low-balance alert (Observer)
- Admin tools: add/remove products and recipes
- JUnit test for core logic (RecipeEngineTest.java)

---

## Folder Structure
PremiumSelect/
├── src/ # Java source files (packages: domain, data, engine, etc.)
├── test/ # JUnit test class: RecipeEngineTest.java
├── lib/ # JUnit 5 + dependencies (.jar files)
├── .vscode/ # settings.json (for VS Code classpath config)
├── premiumselect.log
└── README.txt 

---

## Test Instructions

Run tests from VS Code or CLI. Make sure JUnit jars are in `lib/`.

Example: 
javac -cp "lib/;out" -d testbin test/.java
java -cp "lib/*;testbin;out" org.junit.platform.console.ConsoleLauncher --scan-classpath

---

## Project Highlights

| Pattern        | Implementation                               |
|----------------|----------------------------------------------|
| Factory        | IngredientFactory                            |
| Composite      | CompositeIngredient, CompositeProduct        |
| Iterator       | RecipeIterator, CartIterator                 |
| Strategy       | DealEngine, FlatDiscountDeal, ThresholdDeal |
| Builder        | RecipeBuilder                                |
| Memento        | PantryMemento                                |
| Observer       | EmailNotifier + UserObserver                 |
| Exception Handling | InvalidInputException + shielding logic |
| Logging        | LoggerUtility → premiumselect.log            |
| Java I/O       | Save/load pantry, recipes, and order receipts

-
