# Hotel Booking (Java OOP)

Console program that prints a hotel booking receipt. Plain Java, default package, no libraries.

Run: open the folder in IntelliJ IDEA, open `src/Main.java`, press Run.

Files in the order they are written: [`Room`](#roomjava), [`StandardRoom`](#standardroomjava), [`SuiteRoom`](#suiteroomjava), [`Guest`](#guestjava), [`BookingItem`](#bookingitemjava), [`Booking`](#bookingjava), [`Payable`](#payablejava), [`CardPayment`](#cardpaymentjava), [`CashPayment`](#cashpaymentjava), [`Main`](#mainjava)

## Room.java

```java
public abstract class Room {
    private String name;
    private double pricePerNight;

    public Room(String name, double pricePerNight) {
        this.name = name;
        this.pricePerNight = pricePerNight;
    }

    public String getName() {
        return name;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public abstract String getDescription();

}
```

## StandardRoom.java

```java
public class StandardRoom extends Room {
    private int bedCount;

    public StandardRoom(String name, double pricePerNight, int bedCount) {
        super(name, pricePerNight);
        this.bedCount = bedCount;
    }

    @Override
    public String getDescription() {
        return getName() + " (" + bedCount + " beds)";
    }
}
```

## SuiteRoom.java

```java
public class SuiteRoom extends Room {
    private boolean hasBalcony;

    public SuiteRoom(String name, double pricePerNight, boolean hasBalcony) {
        super(name, pricePerNight);
        this.hasBalcony = hasBalcony;
    }
    @Override
    public String getDescription() {
        if (hasBalcony) {
            return getName() + "(balcony)";
        }
        return getName();
    }
}
```

## Guest.java

```java
public class Guest {
    private String name;
    private String phoneNumber;

    public Guest(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
```

## BookingItem.java

```java
public class BookingItem {
    private Room room;
    private int nights;

    public BookingItem(Room room, int nights) {
        this.room = room;
        this.nights = nights;
    }

    public Room getRoom() {
        return room;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        if (nights > 0) {
            this.nights = nights;
        } else {
            System.out.println("Nights can't be negative");
        }
    }
    public double getLineTotal() {
        return room.getPricePerNight() * nights;
    }
}
```

## Booking.java

```java
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
```

## Payable.java

```java
public interface Payable{
    void pay(double amount);
}
```

## CardPayment.java

```java
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
```

## CashPayment.java

```java
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
```

## Main.java

```java
public class Main {
    public static void main(String[] args) {
        StandardRoom standardRoom = new StandardRoom("Standard Room", 60.00, 2);
        SuiteRoom suiteRoom = new SuiteRoom("Suite Room", 120.00, true);
        StandardRoom familyRoom = new StandardRoom("Family Room", 80.00, 4);

        Guest timur = new Guest("Timur Aliev", "998909876543");
        Booking firstBooking = new Booking("BKG-0107", timur);

        BookingItem standardRoomLine = new BookingItem(standardRoom, 2);
        firstBooking.addItem(standardRoomLine);
        firstBooking.addItem(new BookingItem(suiteRoom, 2));

        standardRoomLine.setNights(3);

        firstBooking.printReceipt();

        Payable payment = new CardPayment("8600111122223333");
        payment.pay(firstBooking.calculateTotal());

        Guest kamila = new Guest("Kamila Usmanova", "998933334455");
        Booking secondBooking = new Booking("BKG-0108", kamila);
        secondBooking.addItem(new BookingItem(familyRoom, 1));
        secondBooking.printReceipt();

        payment = new CashPayment(100.00);
        payment.pay(secondBooking.calculateTotal());
    }
}
```

## Output

```

             Registan Plaza Hotel
==============================================
Booking No: BKG-0107
Guest: Timur Aliev (998909876543)
----------------------------------------------
Standard Room (2 beds)     x3 nights  $180.00
Suite Room(balcony)        x2 nights  $240.00
----------------------------------------------
Subtotal:                             $420.00
Tourist tax (12%):                    $50.40
TOTAL:                                $470.40
Paid $470.40 by card 8600111122223333

             Registan Plaza Hotel
==============================================
Booking No: BKG-0108
Guest: Kamila Usmanova (998933334455)
----------------------------------------------
Family Room (4 beds)       x1 nights  $80.00
----------------------------------------------
Subtotal:                             $80.00
Tourist tax (12%):                    $9.60
TOTAL:                                $89.60
Paid $89.60 in cash, given $100.00
```
