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
