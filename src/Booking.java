import java.util.ArrayList;
import java.util.Locale;

public class Booking {
    private static final String HOTEL_NAME = "Registan Plaza Hotel";
    private static final double TOURIST_TAX_RATE = 0.12;

    private String bookingNumber;
    private Guest guest;
    private ArrayList<BookingItem> items = new ArrayList<>();

    public Booking(String bookingNumber, Guest guest) {
        this.bookingNumber = bookingNumber;
        this.guest = guest;
    }
    public void addItem(BookingItem item) {
        items.add(item);
    }
    public double calculateSubtotal() {
        double subtotal = 0;
        for (BookingItem item : items) {
            subtotal = subtotal + item.getLineTotal();
        }
        return subtotal;
    }
    public double calculateTouristTax() {
        return TOURIST_TAX_RATE * calculateSubtotal();
    }
    public double calculateTotal() {
        return calculateSubtotal() + calculateTouristTax();
    }
    public void printReceipt() {
        System.out.println();
        System.out.println("             " + HOTEL_NAME);
        System.out.println("==============================================");
        System.out.println("Booking No: " + bookingNumber);
        System.out.println("Guest: " + guest.getName() + " (" + guest.getPhoneNumber() + ")");
        System.out.println("----------------------------------------------");
        for (BookingItem item : items) {
            System.out.printf(Locale.US, "%-26s x%d nights  $%.2f%n",
                    item.getRoom().getDescription(), item.getNights(), item.getLineTotal());
        }
        System.out.println("----------------------------------------------");
        System.out.printf(Locale.US, "%-37s $%.2f%n", "Subtotal:", calculateSubtotal());
        System.out.printf(Locale.US, "%-37s $%.2f%n", "Tourist tax (12%):", calculateTouristTax());
        System.out.printf(Locale.US, "%-37s $%.2f%n", "TOTAL:", calculateTotal());
    }
}
