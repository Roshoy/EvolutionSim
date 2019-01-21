package Main;

import java.util.HashMap;

public class World {

    private Vector mapSize;
    private Vector jungleSize;
    private Vector leftUpperCornerOfJungle;

    private HashMap<Vector,Plant> plants = new HashMap<>();



    public World(int width, int height, int jungleWidth, int jungleHeight){
        if(width < jungleWidth) width = jungleWidth;
        if(height < jungleHeight) height = jungleHeight;

        this.mapSize = new Vector(width,height);
        this.jungleSize = new Vector(jungleWidth, jungleHeight);
        this.leftUpperCornerOfJungle = new Vector((width - jungleWidth)/2,(height - jungleHeight)/2);
    }

    public void growPlants(){
        Plant newPlant1, newPlant2;
        newPlant1 = new Plant(positionForPlantInJungle());
        plants.put(newPlant1.getPosition(),newPlant1);
        newPlant2 = new Plant(positionForPlantOutOfJungle());
        plants.put(newPlant2.getPosition(),newPlant2);
    }

    public Vector positionForPlantInJungle(){
        Vector position;
        do {
            position = new Vector((int) Math.random() * jungleSize.getX(), (int) Math.random() * jungleSize.getY());
            position = position.add(this.leftUpperCornerOfJungle);
        }while(plants.keySet().contains(position));
       return position;
    }

    public Vector positionForPlantOutOfJungle(){
        Vector position;
        do {
            position = new Vector((int) Math.random() * mapSize.getX(), (int) Math.random() * mapSize.getY());
        }while(inJungle(position) || plants.keySet().contains(position) );
        return position;
    }

    public boolean inJungle(Vector pos){
        return pos.biggerOrEqualThan(this.leftUpperCornerOfJungle)
                && pos.smallerThan(this.leftUpperCornerOfJungle.add(this.jungleSize));
    }

    public void update(){
        growPlants();
    }

}
