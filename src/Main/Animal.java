package Main;

public class Animal {
    private Vector position;
    private Gender gender;
    private boolean adult = false;
    private int[] directionProbability = new int[Direction.values().length];
    private int[] probabilitySum = new int[1+Direction.values().length];
    private int lifePoints;
    private  int MaxLifePoints = 100;
    private  int LifeLoss = 1;
    private  int MatingCap = 10;
    private  int AdultAge = 3;
    private int age = 0;
    private World world; // only one

    public Animal(Vector position, World world, int lifePoints ) {
        this.position = position;
        this.lifePoints = this.MaxLifePoints;
        if(lifePoints != -1)this.lifePoints = lifePoints;
        this.probabilitySum[0] = 0;

        this.world = world;

        if(Random.rand(0,2) == 0)
            this.gender = Gender.MALE;
        else
            this.gender = Gender.FEMALE;

    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setRandomGenes(){

        for(int i=0; i<this.directionProbability.length; i++){
            this.directionProbability[i] = Random.rand(1,100);
            this.probabilitySum[i+1] = this.probabilitySum[i] + this.directionProbability[i];
        }
    }

    public void setGenes(Animal parent1, Animal parent2){
        for(int i=0; i<this.directionProbability.length; i++){
            int left = Math.min(parent1.getGenes()[i],parent2.getGenes()[i]);
            int right = Math.max(parent1.getGenes()[i],parent2.getGenes()[i])+1;
            if(left > 1)left--;
            this.directionProbability[i] = Random.rand(left,right);
            this.probabilitySum[i+1] = this.probabilitySum[i] + this.directionProbability[i];
        }
        //showGenes();
    }

    public int[] getGenes() {
        return this.directionProbability;
    }

    public Vector getPosition() {
        return this.position;
    }

    private Vector randomDirection(){
        float random = Random.rand(this.probabilitySum[0],this.probabilitySum[this.probabilitySum.length-1]);
        boolean surrounded = true;
        for(int i=0; i<directionProbability.length; i++){
            if(world.canMoveTo(this.position.add(Direction.values()[i].getVector()),this.gender)){
                surrounded = false;
            }
        }
        if(surrounded)return new Vector(0,0);
        int i;

        for(i=0 ;i<this.directionProbability.length; i++){
            if(random>=this.probabilitySum[i] && random<this.probabilitySum[i+1]){
                break;
            }
        }

        return Direction.values()[i].getVector();
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void move(){
        Vector newPosition;
        do{
            newPosition = this.position.add(randomDirection());
        }while(!this.world.canMoveTo(newPosition,this.gender));

        this.position = newPosition;
    }

    public int increaseAge(){
        this.age++;
        if(age>=AdultAge){
            this.adult = true;
        }
        return this.age;
    }

    public int updateLife(){
        this.lifePoints -= this.LifeLoss;
        this.lifePoints += this.world.getPlantEnergy(this.position);

        return this.lifePoints;
    }

    public boolean canMate() {
        return this.adult && this.lifePoints>this.MatingCap;
    }

    public Animal mate(Animal other){

        Animal kid = new Animal(this.position, this.world, this.lifePoints/2 + other.lifePoints/2);
        kid.setGenes(this,other);
        Vector possiblePlacement = kid.randomDirection();
        if(!this.canMate() || !other.canMate() || possiblePlacement.equals(new Vector(0,0))
            || !this.world.canMoveTo(possiblePlacement,kid.getGender())){
            return null;
        }

        this.lifePoints -= this.lifePoints/2;
        other.lifePoints -= other.lifePoints/2;
        return kid;
    }

    @Override
    public String toString() {
        return this.gender.getSymbol();
    }
}
