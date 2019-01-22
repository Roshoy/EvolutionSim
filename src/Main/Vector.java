package Main;

public class Vector {
    private int x;
    private int y;

    public Vector (int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector other){
       return new Vector(this.x + other.x, this.y + other.y) ;
    }

    public Vector multiplyByScalar(int k){
        return new Vector(this.x * k, this.y * k);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean biggerOrEqualThan(Vector other){
        return this.x>=other.x && this.y>=other.y;
    }

    public boolean smallerThan(Vector other){
        return this.x<other.x && this.y<other.y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vector){
            return this.x == ((Vector)obj).x && this.y == ((Vector)obj).y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + ", "+y+")";
    }
}
