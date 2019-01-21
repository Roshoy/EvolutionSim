package Main;

public enum Direction {
    /*UP,
    UP_RIGHT,
    RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT,
    LEFT,
    UP_LEFT,*/
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;

    public Vector getVector(){
        switch(this) {
            case N:
                return new Vector(0, 1);
            case NE:
                return new Vector(1,1);
            case E:
                return new Vector(1,0);
            case SE:
                return new Vector(1,-1);
            case S:
                return new Vector(0,-1);
            case SW:
                return new Vector(-1,-1);
            case W:
                return new Vector(-1,0);
            case NW:
                return new Vector(1,-1);
        }
        return new Vector(0,0);
    }

    public String toString(){
        return name();
    }
    /*
    0 0 0 -> 0 1   +1 001 -> 01
    0 0 1 -> 1 1   +1 010 -> 0 2(2)
    0 1 0 -> 1 0      011 -> 10
    0 1 1 -> 1 -1     100 -> 1 1
    1 0 0 -> 0 -1     101 -> 1 2
    1 0 1 -> -1 -1    110 -> 2 0
    1 1 0 -> -1 0     111 -> 2 1
    1 1 1 -> -1 -1   1000 -> 2 2
     */
}
