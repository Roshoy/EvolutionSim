package Main;

public class WorldVisualiser {
    private static final char emptyTail = ' ';


    public StringBuilder draw(World world){

        StringBuilder string = new StringBuilder();
        Vector mapSize = world.getMapSize();
        string.append('+');
        for(int x=0; x<mapSize.getX()*2; x++){
            string.append('-');
        }
        string.append("+\n");
        for(int y=0; y<mapSize.getY(); y++){
            string.append('|');
            for(int x=0; x<mapSize.getX(); x++){
                Vector pos = new Vector(x,y);
                string.append(world.isOccupiedBy(pos));
            }
            string.append("|\n");
        }
        string.append('+');
        for(int x=0; x<mapSize.getX()*2; x++){
            string.append('-');
        }
        string.append("+\n");
        //System.out.println(string);
        return string;
    }

}
