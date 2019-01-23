package Main;

import org.fusesource.jansi.AnsiConsole;
import java.util.concurrent.TimeUnit;


public class Engine {
    public static void main (String[] args) {

        WorldVisualiser visualiser = new WorldVisualiser();

        AnsiConsole.systemInstall();

        try {
            int maxDays=0;

            for(int j=0; j< 100000; j++) {
                //System.out.println("Pętla");
                String previousState="";
                String actualState;
                World world = new World(40, 40, 10, 10);
                for (int i = 0; i < 3000; i++) {
                    //System.out.println("Day: " + i);
                    int lifeStock = world.newDay();
                    actualState = visualiser.draw(world).toString();
                    if (lifeStock == 0 || actualState.equals(previousState)){
                        if(i>maxDays){
                            maxDays = i;
                            System.out.println("MaxDays: " + maxDays);
                        }
                        break;
                    }
                    previousState = actualState;

                    //if (i % 1 == 0) {

                        //
                        //TimeUnit.MILLISECONDS.sleep(5);
                    //}
                }
                //visualiser.draw(world);
            }
            System.out.println("MaxDays: " + maxDays);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        AnsiConsole.systemUninstall();
    }



}
