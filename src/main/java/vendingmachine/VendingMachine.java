package vendingmachine;

import Inventory.Inventory;
import Inventory.Product;
import States.CoinInsertedState;
import States.DispenseState;
import States.NoCoinInsertedState;
import States.State;
import Payment.PaymentStrategy;

public class VendingMachine {
    private final NoCoinInsertedState noCoinInsertedState;
    private final CoinInsertedState coinInsertedState;
    private final DispenseState dispenseState;
    private State currentVendingMachineState;
    private Inventory inventory;
    private double amount;
    private PaymentStrategy paymentStrategy;
    private static final int AISLE_COUNT = 10;

    public VendingMachine() {
        noCoinInsertedState = new NoCoinInsertedState(this);
        coinInsertedState = new CoinInsertedState(this);
        dispenseState = new DispenseState(this);
        currentVendingMachineState = noCoinInsertedState;
        amount = 0.0;
        inventory = new Inventory(AISLE_COUNT);
    }

    public boolean hasSufficientAmount(double expectedAmount) {
        return expectedAmount <= amount;
    }

    public NoCoinInsertedState getNoCoinInsertedState() { return noCoinInsertedState; }
    public CoinInsertedState getCoinInsertedState() { return coinInsertedState; }
    public DispenseState getDispenseState() { return dispenseState; }
    public State getCurrentVendingMachineState() { return currentVendingMachineState; }
    public void setCurrentVendingMachineState(State state) { this.currentVendingMachineState = state; }
    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public void insertCoin(double amount) {
        currentVendingMachineState.insertCoin(amount);
        System.out.println(String.format("%.2f", amount) + " coin is inserted.");
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null.");
        }
        this.paymentStrategy = paymentStrategy;
    }

    public PaymentStrategy getPaymentStrategy() { return paymentStrategy; }

    /** Charges the selected payment mode for the product in the aisle. */
    public void payAndSelect(int aisleNumber) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Select a payment mode before checkout.");
        }
        if (currentVendingMachineState != noCoinInsertedState) {
            throw new IllegalStateException("Complete or cancel the current purchase before checkout.");
        }
        Product product = inventory.getProductAt(aisleNumber);
        if (product == null || !inventory.checkIfProductAvailable(product.getId())) {
            throw new IllegalStateException("No product available in aisle " + aisleNumber + ".");
        }
        paymentStrategy.pay(product.getPrice());
        System.out.println("Payment successful using " + paymentStrategy.getName() + ".");
        inventory.deductProductCount(aisleNumber);
        System.out.println("Product " + product.getName() + " (ID " + product.getId() + ") is dispensed.");
    }

    public void pressButton(int aisleNumber) {
        currentVendingMachineState.pressButton(aisleNumber);
    }

    public void addProduct(Product product) {
        inventory.addProduct(product);
    }

    public void displayInventory() {
        System.out.println("Current inventory:");
        for (int aisle = 1; aisle <= AISLE_COUNT; aisle++) {
            Product product = inventory.getProductAt(aisle);
            if (product != null) {
                System.out.println("Aisle " + aisle + ": " + product
                        + ", stock=" + inventory.getStockCount(product.getId()));
            }
        }
    }
}
