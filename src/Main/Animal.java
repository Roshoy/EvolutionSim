package Main;

public class Animal {
    private Vector position;
    private AnimalStatus status;
    private float[] directionProbability = new float[Direction.values().length];
    private float[] probabilitySum = new float[1+Direction.values().length];
    private int lifePoints = 10;

    public Animal(Vector position, AnimalStatus status){
        this.position = position;

        this.probabilitySum[0] = 0;
        for(int i=0; i<this.directionProbability.length; i++){
            this.directionProbability[i] = Random.randF(0,10);
            this.probabilitySum[i+1] = this.probabilitySum[i] + this.directionProbability[i];
        }

        if(this.probabilitySum[this.probabilitySum.length-1] < 0.1){
            for(int i=0; i<this.directionProbability.length; i++) {
                this.directionProbability[i] *= 100;
                this.probabilitySum[i+1] *= 100;
            }
        }
        this.status = status;
    }

    public Vector getPosition() {
        return this.position;
    }

    public void move(){

    }

    @Override
    public String toString() {
        return this.status.getSymbol();
    }
}
