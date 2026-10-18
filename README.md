# Vending Machine LLD (Java)

A runnable console-based vending machine implementation based on the supplied screenshots.
It demonstrates the **State Design Pattern** with three states:

- `NoCoinInsertedState`
- `CoinInsertedState`
- `DispenseState`

Purchases use the machine's coin insertion flow: insert coins, select an aisle, and collect
the product and any change. There is one payment flow and no selectable payment methods.

Products are stored in numbered aisles. The machine tracks inventory by product ID, accepts money, validates the selected aisle and product availability, dispenses one item, and returns change.

## Requirements
- JDK 7 or later
- No external dependencies

## Run
From the project root:

```bash
javac -d out src/main/java/Inventory/*.java src/main/java/States/*.java src/main/java/Payment/*.java src/main/java/vendingmachine/*.java src/main/java/Driver.java
java -cp out Driver
```

The example driver adds four Hersheys and three Biskfarm products, then demonstrates coin purchases.

## Design notes
- `State` defines the operations that depend on the current machine state.
- `VendingMachine` owns the state instances and delegates user actions to its current state.
- `Inventory` maps aisle numbers to products and keeps counts by product ID.
- `Product` contains the product name, ID, and price.
- Invalid operations throw `IllegalStateException` (and the driver demonstrates normal successful flows).
