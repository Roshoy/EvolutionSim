package Main;

public class Engine {
    public static void main (String[] args) {
        System.out.println("Hello there!");
        World world = new World(10, 10, 4, 4);
        WorldVisualiser visualiser = new WorldVisualiser();
        for(int i=0; i<3; i++){
            world.update();
            visualiser.draw(world);
        }

    }
}
