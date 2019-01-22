package Main;

public class WorldVisualiser {
    private static final char emptyTail = ' ';


    public void draw(World world){

        StringBuilder string = new StringBuilder();
        Vector mapSize = world.getMapSize();
//        Vector temp = world.getPlants().keySet().iterator().next();
//        System.out.println(temp);
//        System.out.println(world.getPlants().containsKey(temp));
        string.append('+');
        for(int x=0; x<mapSize.getX()*2; x++){
            string.append('-');
        }
        string.append("+\n");
        for(int y=0; y<mapSize.getY(); y++){
            string.append('|');
            for(int x=0; x<mapSize.getX(); x++){
                Vector pos = new Vector(x,y);
//                if(pos.equals(temp) && temp.equals(pos)) {
//                    System.out.println("no równe");
//                    System.out.println(world.getPlants().containsKey(temp));
//                    System.out.println(world.getPlants().containsKey(pos));
//                }
                if(world.getPlants().containsKey(pos)){
                    string.append("Pl");
                }else {
                    string.append("  ");
                }
            }
            string.append("|\n");
        }
        string.append('+');
        for(int x=0; x<mapSize.getX()*2; x++){
            string.append('-');
        }
        string.append("+\n");
        System.out.println(string);

    }

}
