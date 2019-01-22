package Main;

import java.util.HashMap;

public class World {

    private Vector mapSize;
    private Vector jungleSize;
    private Vector leftUpperCornerOfJungle;

    private HashMap<Vector, Plant> plants = new HashMap<>();
    private HashMap<AnimalStatus, HashMap<Vector,Animal>> animals = new HashMap<>();

    public World(int width, int height, int jungleWidth, int jungleHeight){
        for(AnimalStatus status: AnimalStatus.values()){
            this.animals.put(status,new HashMap<>());
        }

        if(width < jungleWidth) width = jungleWidth;
        if(height < jungleHeight) height = jungleHeight;

        this.mapSize = new Vector(width,height);
        this.jungleSize = new Vector(jungleWidth, jungleHeight);
        this.leftUpperCornerOfJungle = new Vector((width - jungleWidth)/2,(height - jungleHeight)/2);

        generateAdamAndEve(5);
    }

    private void generateAdamAndEve(int n){
        Vector position;


        for(int i=0; i<n; i++){
            do{
                position = new Vector(Random.rand(0,mapSize.getX()), Random.rand(0,mapSize.getY()));
            }while(isOccupiedBy(position).startsWith("A"));
            AnimalStatus newStatus;

            if(Random.rand(0,2) == 0)
                newStatus = AnimalStatus.MALE;
            else
                newStatus = AnimalStatus.FEMALE;

            this.animals.get(newStatus).put(position,new Animal(position,newStatus));
        }
    }

    private void growPlants(){
        Plant newPlant1, newPlant2;
        newPlant1 = new Plant(positionForPlantInJungle());
        this.plants.put(newPlant1.getPosition(),newPlant1);
        newPlant2 = new Plant(positionForPlantOutOfJungle());
        this.plants.put(newPlant2.getPosition(),newPlant2);
    }

    private Vector positionForPlantInJungle(){
        Vector position;
        do {
            position = new Vector(Random.rand(0,this.jungleSize.getX()), Random.rand(0,this.jungleSize.getY()));
            position = position.add(this.leftUpperCornerOfJungle);
        }while(this.plants.keySet().contains(position));
       return position;
    }

    private Vector positionForPlantOutOfJungle(){
        Vector position;
        do {
            position = new Vector(Random.rand(0,this.mapSize.getX()), Random.rand(0,this.mapSize.getY()));
        }while(inJungle(position) || this.plants.keySet().contains(position) );
        return position;
    }

    private boolean inJungle(Vector pos){
        return pos.biggerOrEqualThan(this.leftUpperCornerOfJungle)
                && pos.smallerThan(this.leftUpperCornerOfJungle.add(this.jungleSize));
    }

    public Vector getMapSize() {
        return this.mapSize;
    }

    public HashMap<Vector, Plant> getPlants() {
        return this.plants;
    }

    public HashMap<Vector, Animal> getAnimals(AnimalStatus status) {
        return this.animals.get(status);
    }

    public boolean canMoveTo(Vector position, AnimalStatus status){
        if(animals.get(status).containsKey(status))return false;
        if(position.biggerOrEqualThan(mapSize) || position.smallerThan(new Vector(0,0))) return false;
        return true;
    }

    public String isOccupiedBy(Vector position){
        for(AnimalStatus status: AnimalStatus.values()){
            if(this.animals.get(status).containsKey(position)){
                return status.getSymbol();
            }
        }
        if(plants.containsKey(position)){
            return "Pl";
        }
        return "  "; // two spaces
    }

    public void update(){
        growPlants();
    }

}
