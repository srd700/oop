import java.util.Locale;

public class CashPayment implements Payable {
    private double cashGiven;

    public CashPayment(double cashGiven) {
        this.cashGiven = cashGiven;
    }
    @Override
    public void pay(double amount) {
        System.out.printf(Locale.US, "Paid $%.2f in cash, given $%.2f%n", amount, cashGiven);
    }
}
