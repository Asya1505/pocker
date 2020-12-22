public enum HandValues {

    STRAIGHTFLUSH(1),
    FOUROFKIND(2),
    FULLHOUSE(3),
    FLUSH(4),
    STRAIGHT(5),
    THREEOFKIND(6),
    TWOPAIRS(7),
    PAIRS(8),
    HIGHTCARD(9);


    public final int i;

    HandValues(int i) {
        this.i =i;
    }
}
