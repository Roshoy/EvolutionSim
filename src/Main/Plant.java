package Main;

public class Plant {
    private String representation = " P ";
    private Vector position;
    private int energyValue = 15;


    public Plant (Vector position){
        this.position = position;

    }

    public int getEnergyValue() {
        return this.energyValue;
    }

    public Vector getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return representation;
    }
}
