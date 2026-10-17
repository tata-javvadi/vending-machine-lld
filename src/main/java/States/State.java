package States;

public interface State {
    void insertCoin(double amount);
    void pressButton(int aisleNumber);
    void dispense(int aisleNumber);
}
