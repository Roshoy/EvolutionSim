package Main;

public class Random {
    public static int rand(int from, int to){
        return from + (int)Math.floor(Math.random() * (double)(to-from));
    }
}
