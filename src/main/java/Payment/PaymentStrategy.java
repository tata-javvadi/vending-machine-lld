package Payment;

/** A payment method used to charge for a vending machine purchase. */
public interface PaymentStrategy {
    void pay(double amount);
    String getName();
}
