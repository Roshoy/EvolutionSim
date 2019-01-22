package Main;

public class Plant {
    private char representation = 'P';
    private Vector position;
    private int energyValue;

    public Plant (int x, int y){
        this.position = new Vector(x,y);
        this.energyValue = 1;//???
    }

    public Plant (Vector position){
        this.position = position;
        this.energyValue = 1;//???
    }

    public int getEnergyValue() {
        return this.energyValue;
    }

    public Vector getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "Pl";
    }
}
