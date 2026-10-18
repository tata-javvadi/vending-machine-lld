package vendingmachine;

import Inventory.Inventory;
import States.CoinInsertedState;
import States.DispenseState;
import States.NoCoinInsertedState;
import States.State;

public class VendingMachine {
    private final NoCoinInsertedState noCoinInsertedState;
    private final CoinInsertedState coinInsertedState;
    private final DispenseState dispenseState;
    private State currentVendingMachineState;
    private Inventory inventory;
    private double amount;
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
