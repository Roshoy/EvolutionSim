package Main;

import java.util.HashMap;

public class World {

    private Vector mapSize;
    private Vector jungleSize;
    private Vector leftUpperCornerOfJungle;

    private HashMap<Vector,Plant> plants = new HashMap<>();
    private HashMap<Vector, Animal> animals = new HashMap<>();

    public World(int width, int height, int jungleWidth, int jungleHeight){
        plants.put(new Vector(0,0),new Plant(0,0));
        if(width < jungleWidth) width = jungleWidth;
        if(height < jungleHeight) height = jungleHeight;

        this.mapSize = new Vector(width,height);
        this.jungleSize = new Vector(jungleWidth, jungleHeight);
        this.leftUpperCornerOfJungle = new Vector((width - jungleWidth)/2,(height - jungleHeight)/2);
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

    public HashMap<Vector, Animal> getAnimals() {
        return this.animals;
    }

    public void update(){
        growPlants();
    }

}
