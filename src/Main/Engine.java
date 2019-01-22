package Main;

public class Engine {
    public static void main (String[] args) {
        System.out.println("Hello there!");
        World world = new World(10, 10, 3, 3);
        WorldVisualiser visualiser = new WorldVisualiser();
        for(int i=0; i<30; i++){
            System.out.println("Day: " + i);
            world.newDay();
            visualiser.draw(world);

        }

    }
}
