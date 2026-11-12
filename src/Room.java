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