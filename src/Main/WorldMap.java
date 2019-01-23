package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldMap {
    private HashMap<Vector,Plant> plants = new HashMap<>();
    private Vector mapSize;
    private Vector jungleSize;
    private Vector leftUpperCornerOfJungle;
    private int plantsInJungle = 0;
    private int plantsOutsideJungle = 0;

    public WorldMap(Vector map, Vector jungle){
        this.mapSize = map;
        if(map.getX() < jungle.getX()) jungle = new Vector(map.getX(),jungle.getY());
        if(map.getY() < jungle.getY()) jungle = new Vector(jungle.getX(),map.getY());
        this.jungleSize = jungle;

        this.leftUpperCornerOfJungle = new Vector((map.getX() - jungle.getX())/2,(map.getY() - jungle.getY())/2);
    }

    public void growPlants(){
        Plant newPlant1, newPlant2;
        if(this.plantsInJungle < this.jungleSize.getField()) {
            newPlant1 = new Plant(positionForPlantInJungle());
            this.plants.put(newPlant1.getPosition(), newPlant1);
            this.plantsInJungle++;
        }
        if(this.plantsOutsideJungle < this.mapSize.getField() - this.jungleSize.getField()) {
            newPlant2 = new Plant(positionForPlantOutOfJungle());
            this.plants.put(newPlant2.getPosition(), newPlant2);
            this.plantsOutsideJungle++;
        }

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

    public boolean onMap(Vector position){
        return position.biggerOrEqualThan(new Vector(0,0)) && position.smallerThan(this.mapSize);
    }

    public boolean plantOn(Vector position){
        return this.plants.containsKey(position);
    }


    public int getPlantEnergy(Vector position){
        if(!this.plants.containsKey(position))return 0;
        //System.out.println("EATS");
        if(this.inJungle(position)){
            plantsInJungle--;
        }else{
            plantsOutsideJungle--;
        }
        return this.plants.remove(position).getEnergyValue();
    }
}
