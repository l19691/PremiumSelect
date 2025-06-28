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

##  Known Limitations

- No GUI (CLI only): The app runs in terminal mode; no graphical interface is available yet.
-  No authentication: Users log in by typing a name, but there’s no password or user verification.
- Recipes are stored in-memory: They are not yet persisted to file or database.
- Product catalog is hardcoded: Products are preloaded at runtime and not editable between sessions.
- No database: All state is lost between runs unless manually saved via I/O.
- No advanced search: Tag/category filtering is basic and non-fuzzy.
- No REST API: Not yet deployable as a service (e.g., no Spring Boot or HTTP interface).
- Only one test class: The current test suite includes `RecipeEngineTest` but not full coverage (e.g., `Cart`, `Wallet`, `Observer`).

## Future Work 
- Build a JavaFX or Swing-based GUI interface
- Integrate with a file or database to persist recipes and user carts
- Add authentication & password management
- Expand test suite (JUnit) to cover all modules
- Expose functionality via a REST API (Spring Boot)
- Connect to real-world food APIs (e.g., Spoonacular)
- Export orders as PDFs or CSV invoices
- Add inventory/stock control and reporting for admin
- integrate Stripe or PayPal for real wallet top-ups

