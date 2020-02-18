package engine.board;

public final class BoardUtils {                                             //final - this class can't be extended

    public static final int TOTAL_TILES = 64;
    public static final int TILES_PER_ROW = 8;

    public static final boolean[] FIRST_COLUMN = initColumn(0);             // 0 = the first tile on the FIRST_COLUMN
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] FIRST_ROW = initColumn(0);
    public static final boolean[] SECOND_ROW = initColumn(8);
    public static final boolean[] THIRD_ROW = initColumn(16);
    public static final boolean[] FOURTH_ROW = initColumn(24);
    public static final boolean[] FIFTH_ROW = initColumn(32);
    public static final boolean[] SIXTH_ROW = initColumn(40);
    public static final boolean[] SEVENTH_ROW = initColumn(48);
    public static final boolean[] EIGTH_ROW = initColumn(56);


    private BoardUtils(){
        throw new RuntimeException("BoardUtils is a utility class and cannot be instantiated.");
    }

    public static boolean isValidTileCoordinate(int candidateDestinationOffset){
        return candidateDestinationOffset >= 0 && candidateDestinationOffset < TOTAL_TILES;
    }

    private static boolean[] initColumn(int columnNumber){              //initialize all the tiles from a given column with true
        boolean[] column = new boolean[TOTAL_TILES];                    //create a table with all the position false except for
                                                                        //the given column
        while (columnNumber < TOTAL_TILES) {
            column[columnNumber] = true;
            columnNumber += TILES_PER_ROW;                              //FIRST_COLUMN means tiles 0, 8, 16, 24, 32, 40, 48, 56
        }
        return column;
    }

    //   \/   \/                       \/   \/
    //   T    T    F    F    F    F    T    T
    // -----------------------------------------
    // | 56 | 57 | 58 | 59 | 60 | 61 | 62 | 63 |
    // -----+----+----+----+----+----+----+-----
    // | 48 | 49 | 50 | 51 | 52 | 53 | 54 | 55 |
    // -----+----+----+----+----+----+----+-----
    // | 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 |
    // -----+----+----+----+----+----+----+-----
    // | 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 |
    // -----+----+----+----+----+----+----+-----
    // | 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 |
    // -----+----+----+----+----+----+----+-----
    // | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |
    // -----+----+----+----+----+----+----+-----
    // | 8  | 9  | 10 | 11 | 12 | 13 | 14 | 15 |
    // -----+----+----+----+----+----+----+-----
    // | 0  | 1  | 2  | 3  | 4  | 5  | 6  | 7  |
    // -----------------------------------------

    private static boolean[] initRow(int rowNumber){
        boolean[] row = new boolean[TOTAL_TILES];

        while(rowNumber % TILES_PER_ROW != 0){
            row[rowNumber] = true;
            rowNumber++;
        }
        return row;
    }
}
