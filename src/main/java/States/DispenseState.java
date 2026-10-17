package States;

import Inventory.Inventory;
import Inventory.Product;
import vendingmachine.VendingMachine;

public class DispenseState implements State {
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        throw new IllegalStateException("Please collect your current purchase first.");
    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalStateException("Product is already being dispensed.");
    }

    @Override
    public void dispense(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);
        if (product == null || !inventory.checkIfProductAvailable(product.getId())) {
            throw new IllegalStateException("Product is no longer available.");
        }

        double change = vendingMachine.getAmount() - product.getPrice();
        inventory.deductProductCount(aisleNumber);
        System.out.println("Product " + product.getName() + " (ID " + product.getId()
                + ") is being dispensed. Please collect the change of " + String.format("%.2f", change) + "!");

        vendingMachine.setAmount(0.0);
        vendingMachine.setCurrentVendingMachineState(vendingMachine.getNoCoinInsertedState());
    }
}
