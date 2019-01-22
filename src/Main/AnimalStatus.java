package Main;

public enum AnimalStatus {
    MALE,
    FEMALE,
    KID;

    @Override
    public String toString() {
        return this.name();
    }

    public String getSymbol(){
        switch(this){
            case KID:
                return "AK";
            case MALE:
                return "AM";
            case FEMALE:
                return "AF";
        }
        return "!!";
    }
}
