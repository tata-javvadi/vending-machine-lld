package Payment;

/** Cash checkout; cash inserted through the existing state flow is applied to the price. */
public class CashPayment implements PaymentStrategy {
    private final double cashProvided;

    public CashPayment(double cashProvided) {
        if (cashProvided <= 0) {
            throw new IllegalArgumentException("Cash provided must be greater than zero.");
        }
        this.cashProvided = cashProvided;
    }

    @Override
    public void pay(double amount) {
        if (cashProvided < amount) {
            throw new IllegalStateException("Insufficient cash. Price is " + amount
                    + ", cash provided is " + cashProvided + ".");
        }
        System.out.println("Cash accepted: " + String.format("%.2f", cashProvided)
                + "; change: " + String.format("%.2f", cashProvided - amount) + ".");
    }

    @Override
    public String getName() { return "cash"; }
}
