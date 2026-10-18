import Inventory.Product;
import vendingmachine.VendingMachine;

public class Driver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        Product hersheys = new Product("hersheys", 1, 5.0);
        for (int i = 1; i <= 4; i++) {
            vendingMachine.addProduct(hersheys);
        }

        Product biskfarm = new Product("biskfarm", 2, 2.0);
        for (int i = 1; i <= 3; i++) {
            vendingMachine.addProduct(biskfarm);
        }

        vendingMachine.displayInventory();
        System.out.println();

        vendingMachine.insertCoin(5.0);
        vendingMachine.insertCoin(3.0);
        vendingMachine.pressButton(1);

        vendingMachine.insertCoin(5.0);
        vendingMachine.pressButton(1);

        vendingMachine.insertCoin(7.0);
        vendingMachine.pressButton(2);

        System.out.println();
        vendingMachine.displayInventory();
    }
}
