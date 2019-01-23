package Main;

import org.fusesource.jansi.AnsiConsole;
import java.util.concurrent.TimeUnit;

import static org.fusesource.jansi.Ansi.ansi;


public class Engine {
    public static void main (String[] args) {

        WorldVisualiser visualiser = new WorldVisualiser();

        AnsiConsole.systemInstall();

        try {
            int maxDays=0;

            for(int j=0; j< 1000; j++) {
                //System.out.println("Pętla");
                String previousState="";
                String actualState;
                World world = new World(25, 25, 10, 10);
                for (int i = 0; i < 300000; i++) {

                    int lifeStock = world.newDay();
                    actualState = visualiser.draw(world).toString();
                    if (lifeStock == 0 || actualState.equals(previousState)){
                        if(i>maxDays){
                            maxDays = i;
                            System.out.println("MaxDays: " + maxDays + "Life stock: " + lifeStock);
                        }
                        break;
                    }
                    previousState = actualState;

                    if (i % 100 == 0) {
                        System.out.println(ansi().eraseScreen());
                        System.out.println("Day: " + i);

                        System.out.println(actualState);
                        //
                        TimeUnit.MILLISECONDS.sleep(1000);
                    }
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
