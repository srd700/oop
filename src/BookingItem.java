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