package States;

import Inventory.Inventory;
import Inventory.Product;
import vendingmachine.VendingMachine;

public class CoinInsertedState implements State {
    private final VendingMachine vendingMachine;

    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Coin amount must be greater than zero.");
        }
        vendingMachine.setAmount(vendingMachine.getAmount() + amount);
    }

    @Override
    public void pressButton(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);

        if (product == null) {
            throw new IllegalStateException("No product available in aisle " + aisleNumber + ".");
        }
        if (!vendingMachine.hasSufficientAmount(product.getPrice())) {
            throw new IllegalStateException("Insufficient amount. Product price is " + product.getPrice()
                    + ", inserted amount is " + vendingMachine.getAmount() + ".");
        }
        if (!inventory.checkIfProductAvailable(product.getId())) {
            throw new IllegalStateException("Product is out of stock.");
        }

        vendingMachine.setCurrentVendingMachineState(vendingMachine.getDispenseState());
        vendingMachine.getCurrentVendingMachineState().dispense(aisleNumber);
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalStateException("Select a product before dispensing.");
    }
}
