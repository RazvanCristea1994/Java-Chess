package engine.board;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
                                                                                                    //FACTORY PATTERN
public abstract class Tile {                                                                        //”Product” interface (abstract in this case)

    private final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILE_CACHE = createAllPossibleEmptyTiles();

    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public int getTileCoordinate(){
        return this.tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static Tile createTile(int tileCoordinate, Piece piece){                                 //Creator (Factory Pattern)
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILE_CACHE.get(tileCoordinate);
    }

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        Map<Integer, EmptyTile> emptyTileMap = new HashMap<Integer, EmptyTile>();
        for (int i = 0; i < BoardUtils.TOTAL_TILES; i++) {
                emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);              //emptyTileMap should be immutable so nobody can clear it, put a new tile or smth. else
    }

    //static nested class   |    final => can't be extended
    public static final class EmptyTile extends Tile{                                        //Concrete productA (Factory Pattern)

        EmptyTile(int TileCoordinate){
            super(TileCoordinate);
        }

        @Override
        public String toString(){
            return "-";
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{                                   //Concrete productB (Factory Pattern)

        private Piece pieceOnTile;

        OccupiedTile(int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public String toString(){
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
