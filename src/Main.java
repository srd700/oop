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