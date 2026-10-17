package Payment;

public class CardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CardPayment(String cardNumber) {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Card number cannot be empty.");
        }
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Charged " + String.format("%.2f", amount)
                + " to card ending in " + lastFourDigits() + ".");
    }

    private String lastFourDigits() {
        String digits = cardNumber.replaceAll("\\D", "");
        return digits.length() <= 4 ? digits : digits.substring(digits.length() - 4);
    }

    @Override
    public String getName() { return "card"; }
}
