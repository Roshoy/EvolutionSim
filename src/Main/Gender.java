package Main;

public enum Gender {
    MALE,
    FEMALE;

    @Override
    public String toString() {
        return this.name();
    }

    public String getSymbol(){
        switch(this){
            case MALE:
                return "A ";
            case FEMALE:
                return "A ";
        }
        return "!!";
    }
}
