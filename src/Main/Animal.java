package Main;

public class Animal {
    private Vector position;
    private AnimalStatus status;
    private float[] directionProbability = new float[Direction.values().length];
    private float probabilitySum;
    private int lifePoints;

    public Animal(Vector position, int lifePoints, AnimalStatus status){
        this.position = position;
        this.lifePoints = lifePoints;
        this.probabilitySum = 0;
        for(int i=0; i<this.directionProbability.length; i++){
            this.directionProbability[i] = Random.randF(0,10);
            this.probabilitySum += this.directionProbability[i];
        }

        if(this.probabilitySum < 0.1){
            for(int i=0; i<this.directionProbability.length; i++) {
                this.directionProbability[i] *= 100;
            }
            this.probabilitySum *= 100;
        }
        this.status = status;
    }

    public Vector getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return this.status.getSymbol();
    }
}
