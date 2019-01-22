package Main;

public class Animal {
    private Vector position;
    private AnimalStatus status;
    private float[] directionProbability = new float[Direction.values().length];
    private float[] probabilitySum = new float[1+Direction.values().length];
    private int lifePoints = 10;
    private static int MaxLifePoints = 10;
    private static int LifeLoss = 1;
    private static int MatingCap = 5;
    private int age = 0;
    private static int AdultAge = 2;
    private World world; // only one

    public Animal(Vector position, World world, int lifePoints){
        this.position = position;
        this.lifePoints = lifePoints;
        this.probabilitySum[0] = 0;

        this.status = AnimalStatus.KID;

        this.world = world;

        showProbability();
        showProbabilitySums();
    }

    public void setRandomGenes(){

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
    }

    public void setGenes(Animal parent1, Animal parent2){

        for(int i=0; i<this.directionProbability.length; i++){
            this.directionProbability[i] = Random.randF(parent1.getGenes()[i],parent2.getGenes()[i]);
            this.probabilitySum[i+1] = this.probabilitySum[i] + this.directionProbability[i];
        }

    }

    public void showProbabilitySums(){
        StringBuilder string = new StringBuilder();
        string.append("[ ");
        for(float i: this.probabilitySum){
            string.append(i + " ");
        }
        string.append(" ]");
        System.out.println(string);
    }

    public void showProbability(){
        StringBuilder string = new StringBuilder();
        string.append("[ ");
        for(float i: this.directionProbability){
            string.append(i + " ");
        }
        string.append(" ]");
        System.out.println(string);
    }

    public float[] getGenes() {
        return this.directionProbability;
    }

    public Vector getPosition() {
        return this.position;
    }

    private Vector randomDirection(){
        float random = Random.randF(this.probabilitySum[0],this.probabilitySum[this.probabilitySum.length-1]);

        int i;
        for(i=0 ;i<this.directionProbability.length; i++){
            if(random>=this.probabilitySum[i] && random<this.probabilitySum[i+1]){
                System.out.println("Losowe: " + random + "    i: "+ i);
                break;
            }
        }
        if(i==this.directionProbability.length){
            System.out.println(i);
            i--;
        }

        return Direction.values()[i].getVector();
    }

    public AnimalStatus getStatus() {
        return this.status;
    }

    public void move(){
        Vector newPosition;
        do{
            newPosition = this.position.add(randomDirection());
        }while(!this.world.canMoveTo(newPosition,this.status));
        this.position = newPosition;
    }

    public void update(){
        move();
        this.lifePoints -= this.LifeLoss;
        this.world.eatPlant(this.position);
    }

    public boolean canMate() {
        return this.lifePoints>this.MatingCap;
    }

    public Animal mate(Animal other){

        if(!other.canMate() || !this.canMate()){
            return null;
        }
        Animal kid = new Animal(this.position, this.world, );

        return kid;
    }

    private boolean damageSelf(int dmg){
        this.lifePoints -= dmg;
        if(this.lifePoints < 0)return false;
        return true;
    }

    @Override
    public String toString() {
        return this.status.getSymbol();
    }
}
