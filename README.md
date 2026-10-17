# Vending Machine LLD (Java)

A runnable console-based vending machine implementation based on the supplied screenshots.
It demonstrates the **State Design Pattern** with three states:

- `NoCoinInsertedState`
- `CoinInsertedState`
- `DispenseState`

It also demonstrates the **Strategy Design Pattern** for checkout, with cash, card,
and UPI payment strategies. Set a strategy on the machine and call `payAndSelect(aisle)`;
the strategy can be changed between purchases. The original coin insertion flow remains available.

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

The example driver adds four Hersheys and three Biskfarm products, then demonstrates coin, card, UPI, and cash purchases.

## Design notes
- `State` defines the operations that depend on the current machine state.
- `VendingMachine` owns the state instances and delegates user actions to its current state.
- `Inventory` maps aisle numbers to products and keeps counts by product ID.
- `Product` contains the product name, ID, and price.
- `PaymentStrategy` defines the payment operation; `CashPayment`, `CardPayment`, and `UpiPayment` implement it.
- Invalid operations throw `IllegalStateException` (and the driver demonstrates normal successful flows).
