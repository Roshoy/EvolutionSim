package Main;

public class Random {
    public static int rand(int from, int to){
        return from + (int)(Math.random() * (to-from));
    }

    public static float randF(float from, float to){
        return from + (float)Math.random() * (to-from);
    }
}
