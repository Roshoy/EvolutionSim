package Main;

import org.fusesource.jansi.Ansi;

public class WorldVisualiser {
    private static final char emptyTail = ' ';


    public StringBuilder draw(World world){
        StringBuilder string = new StringBuilder();
        Ansi ansiStr = new Ansi(string);
        Vector mapSize = world.getMapSize();

        ansiStr.a('+');
        for(int x=0; x<mapSize.getX()*3; x++){

            ansiStr.a('-');
        }

        ansiStr.a("+\n");
        for(int y=0; y<mapSize.getY(); y++){

            ansiStr.a('|');
            for(int x=0; x<mapSize.getX(); x++){
                Vector pos = new Vector(x,y);
                String test = world.isOccupiedBy(pos);
                if(test.equals(" P ")){
                    ansiStr.fg(Ansi.Color.GREEN).a(test).reset();
                }else if(test.equals(" M ")){
                    ansiStr.fg(Ansi.Color.MAGENTA).a(test).reset();
                }
                else{
                    ansiStr.fg(Ansi.Color.BLUE).a(test).reset();
                }

            }

            ansiStr.a("|\n");
        }

        ansiStr.a("+");
        for(int x=0; x<mapSize.getX()*3; x++){

            ansiStr.a('-');
        }

        ansiStr.a("+\n");
        ansiStr.a(world.showGenes());
        //System.out.println(ansiStr);

        return string;
    }

    public static String genesToString(int[] genes){
        StringBuilder string = new StringBuilder();
        string.append("[ ");
        for(int gene: genes){
            string.append(gene + " ");
        }
        string.append("]\n");
        return string.toString();
    }

}
