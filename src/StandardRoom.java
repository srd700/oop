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
