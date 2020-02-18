package engine.board;

import engine.Alliance;

import java.util.Collection;

public abstract class Piece {

    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    public Piece(PieceType pieceType, int piecePosition, Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;

        this.isFirstMove = false;
    }

   /* @Override
    public boolean equals(Object other){

    }

    @Override
    public boolean hashcode(){

    }*/

    public PieceType getPieceType(){
        return this.pieceType;
    }

    public int getPiecePosition(){
        return this.piecePosition;
    }

    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }

    public int getPieceValue(){
        return this.pieceType.pieceValue;
    }

    public abstract Collection<Move> calculateLegalMoves(Board board);      //return a collection of possible moves for each piece

    public abstract Piece movePiece(Move move);                             //return a new updated piece - placed in the new spot after being moved

    public enum PieceType{
        PAWN(100, "P"){
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT(300, "N") {
            public boolean isKing() {
                return false;
            }
        },
        BISHOP(300, "B") {
            public boolean isKing() {
                return false;
            }
        },
        ROOK(500, "R") {
            public boolean isKing() {
                return false;
            }
        },
        QUEEN(900, "Q") {
            public boolean isKing() {
                return false;
            }
        },
        KING(10000, "K") {
            public boolean isKing() {
                return true;
            }
        };


        private String pieceName;
        private int pieceValue;

        PieceType(int pieceValue, String pieceName){
            this.pieceName = pieceName;
            this.pieceValue = pieceValue;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }

        public abstract boolean isKing();
        public int getPieceValue(){
            return this.pieceValue;
        }
    }
}
