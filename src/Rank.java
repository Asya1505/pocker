public enum Rank {
    RANK_A('A',0),
    RANK_K('K',1),
    RANK_Q('Q',2),
    RANK_J('J',3),
    RANK_T('T',4),
    RANK_9('9',5),
    RANK_8('8',6),
    RANK_7('7',7),
    RANK_6('6',8),
    RANK_5('5',9),
    RANK_4('4',10),
    RANK_3('3',11),
    RANK_2('2',12);

    public final int i;
    public final char h;

    Rank(char h, int i) {
        this.h = h;
        this.i = i;
    }
}
