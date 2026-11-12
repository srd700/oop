import java.util.Locale;

public class CardPayment implements Payable {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    @Override
    public void pay(double amount) {
        System.out.printf(Locale.US, "Paid $%.2f by card %s%n", amount, cardNumber);
    }
}
