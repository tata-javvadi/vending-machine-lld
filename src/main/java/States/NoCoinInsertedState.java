package States;

import vendingmachine.VendingMachine;

public class NoCoinInsertedState implements State {
    private final VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Coin amount must be greater than zero.");
        }
        vendingMachine.setAmount(vendingMachine.getAmount() + amount);
        vendingMachine.setCurrentVendingMachineState(vendingMachine.getCoinInsertedState());
    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalStateException("Please insert coin before selecting a product.");
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalStateException("Please insert coin before dispensing a product.");
    }
}
