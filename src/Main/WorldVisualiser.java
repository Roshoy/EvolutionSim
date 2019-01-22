package Main;

public class WorldVisualiser {
    private static final char emptyTail = ' ';


    public void draw(World world){

        StringBuilder string = new StringBuilder();
        Vector mapSize = world.getMapSize();
        Vector temp = world.getPlants().keySet().iterator().next();
        System.out.println(temp);
        System.out.println(world.getPlants().containsKey(temp));

        for(int x=0; x<mapSize.getX(); x++){
            for(int y=0; y<mapSize.getY(); y++){
                Vector pos = new Vector(x,y);
                if(pos.equals(temp) && temp.equals(pos)) {
                    System.out.println("no równe");
                    System.out.println(world.getPlants().containsKey(temp));
                    System.out.println(world.getPlants().containsKey(pos));
                }
                if(world.getPlants().containsKey(new Vector(0,0))){
                    string.append('P');
                    System.out.println("hehe: " + pos);
                }else {
                    string.append(' ');
                }
            }
            string.append('\n');
        }
        //System.out.println(string);
    }

}
