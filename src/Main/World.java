package Main;

import java.util.*;

public class World {
    private HashMap<Gender, HashMap<Vector, Animal>> animals = new HashMap<>();
    private int leftAlive = 0;
    private int born = 0;
    private WorldMap map;

    public World(int width, int height, int jungleWidth, int jungleHeight){
        this.map = new WorldMap(new Vector(width,height), new Vector(jungleWidth,jungleHeight));
        for(Gender status: Gender.values()){
            this.animals.put(status,new HashMap<>());
        }

        if(width < jungleWidth) width = jungleWidth;
        if(height < jungleHeight) height = jungleHeight;

        generateAdamAndEve(14);
    }

    private void generateAdamAndEve(int n){
        Vector position;
        for(int i=0; i<n; i++){
            do{
                position = new Vector(Random.rand(0,this.map.getMapSize().getX()),
                        Random.rand(0,this.map.getMapSize().getY()));
            }while(isOccupiedBy(position).startsWith("A"));
            Animal newAnimal = new Animal(position, this, -1);
            newAnimal.setRandomGenes();
            addNewAnimal(newAnimal);
        }
    }

    private void addNewAnimal(Animal newAnimal){
        newAnimal.move();
        this.animals.get(newAnimal.getGender()).put(newAnimal.getPosition(), newAnimal);
        //System.out.println("New happy kid!");
        this.leftAlive++;
        this.born++;
    }

    private void animalDies(Animal body){
        this.animals.get(body.getGender()).remove(body.getPosition());
        this.leftAlive--;
        //System.out.println("Died: " + body.getPosition());
    }

    public Vector getMapSize() {
        return this.map.getMapSize();
    }

    public boolean canMoveTo(Vector position, Gender status){
        if(!map.onMap(position)) return false;

        if(this.animals.get(status).containsKey(position))return false;
        return true;
    }

    public void showAllAnimalsPositions(){
        StringBuilder string = new StringBuilder();
        int count=0;
        for(Gender status: Gender.values()){
            for(Animal animal: this.animals.get(status).values()){
                string.append(animal.getPosition() + "\n");
                count++;
            }
        }
       // System.out.println("Liczba pozycji: " + count);
        //System.out.println(string);
    }

    public String isOccupiedBy(Vector position){
        for(Gender status: Gender.values()){

            if(this.animals.get(status).containsKey(position)){

                return status.getSymbol();
            }
        }
        if(this.map.plantOn(position)){
            return "P ";
        }
        return "  "; // two spaces
    }

    public int newDay(){
        this.map.growPlants();

        for(HashMap<Vector,Animal> animals: this.animals.values()){
            List<Animal> animalCollection = new ArrayList<>(animals.values());
            for(Animal animal: animalCollection){

                updateAnimalPosition(animal);


                    animal.increaseAge();

                if(animal.updateLife()<0){
                    animalDies(animal);
                }
            }
        }
        List<Animal> maleList = new ArrayList<>(this.animals.get(Gender.MALE).values());
        for(Animal male: maleList){
            mate(male);
        }
        showAllAnimalsPositions();
        //System.out.println("Born: " + this.born);
        //System.out.println("Left alive: " + this.leftAlive);
        return this.leftAlive;

    }

    public void updateAnimalPosition(Animal animal){
        this.animals.get(animal.getGender()).remove(animal.getPosition());
        animal.move();
        this.animals.get(animal.getGender()).put(animal.getPosition(),animal);

    }

    public int getPlantEnergy(Vector position){
        return map.getPlantEnergy(position);
    }

    public void mate(Animal male){
        if(animals.get(Gender.FEMALE).containsKey(male.getPosition())){
            Animal female = animals.get(Gender.FEMALE).get(male.getPosition());
            Animal newAnimal = male.mate(female);
            if(newAnimal != null) addNewAnimal(newAnimal);

        }
    }

}
