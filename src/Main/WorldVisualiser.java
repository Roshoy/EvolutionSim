package Main;

import org.fusesource.jansi.Ansi;

public class WorldVisualiser {
    private static final char emptyTail = ' ';


    public StringBuilder draw(World world){




        StringBuilder string = new StringBuilder();
        Ansi ansiStr = new Ansi(string);
        Vector mapSize = world.getMapSize();
        //string.append('+');
        ansiStr.a('+');
        for(int x=0; x<mapSize.getX()*2; x++){
            //string.append('-');
            ansiStr.a('-');
        }
        //string.append("+\n");
        ansiStr.a("+\n");
        for(int y=0; y<mapSize.getY(); y++){
            //string.append('|');
            ansiStr.a('|');
            for(int x=0; x<mapSize.getX(); x++){
                Vector pos = new Vector(x,y);
                String test = world.isOccupiedBy(pos);
                if(test == "P "){
                    ansiStr.fg(Ansi.Color.GREEN).a(test).reset();
                }else {
                    ansiStr.fg(Ansi.Color.MAGENTA).a(test).reset();
                }
                //string.append(world.isOccupiedBy(pos));
            }
            //string.append("|\n");
            ansiStr.a("|\n");
        }
        //string.append('+');
        ansiStr.a("+");
        for(int x=0; x<mapSize.getX()*2; x++){
            //string.append('-');
            ansiStr.a('-');
        }
        //string.append("+\n");
        ansiStr.a("+\n");
        //System.out.println(ansiStr);
        //System.out.println(string);
        return string;
    }

}
