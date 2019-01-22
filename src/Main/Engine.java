package Main;

public class Engine {
    public static void main (String[] args) {
        System.out.println("Hello there!");
        World world = new World(100, 100, 20, 20);
        WorldVisualiser visualiser = new WorldVisualiser();
        for(int i=0; i<30; i++){
            world.update();
            visualiser.draw(world);

        }

    }
}
