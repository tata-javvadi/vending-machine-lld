package Payment;

public class UpiPayment implements PaymentStrategy {
    private final String upiId;

    public UpiPayment(String upiId) {
        if (upiId == null || upiId.trim().isEmpty()) {
            throw new IllegalArgumentException("UPI ID cannot be empty.");
        }
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Charged " + String.format("%.2f", amount) + " via UPI " + upiId + ".");
    }

    @Override
    public String getName() { return "UPI"; }
}
