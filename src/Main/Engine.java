package Main;

import org.fusesource.jansi.AnsiConsole;
import java.util.concurrent.TimeUnit;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;


public class Engine {
    public static void main (String[] args) {
        System.out.println("Hello there!");
        World world = new World(20, 20, 5, 5);
        WorldVisualiser visualiser = new WorldVisualiser();

        AnsiConsole.systemInstall();
        System.out.println( ansi().eraseScreen().fg(RED).a("Hello").fg(GREEN).a(" World").reset() );
        try {

            for (int i = 0; i < 3000; i++) {
                if (world.newDay() == 0) break;


                if (i % 20 == 0) {
                    System.out.println( ansi().eraseScreen().a( visualiser.draw(world)));

                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }
            visualiser.draw(world);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        //visualiser.draw(world);
    }
    public void render(){

    }


}
