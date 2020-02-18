package engine.pieces;

import engine.board.*;
import engine.Alliance;


public class Rook extends SlidingPiece {

    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATE = {-1, 1, 8, -8};

    public Rook(Alliance pieceAlliance, int piecePosition) {
        super(CANDIDATE_MOVE_VECTOR_COORDINATE, PieceType.ROOK, piecePosition, pieceAlliance);
    }

    @Override
    public Rook movePiece(Move move) {                 //return a new updated piece - placed in the new spot after being moved
        return new Rook(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.ROOK.toString();
    }

    @Override
    public boolean isFirstColumnExclusion(int currentCandidateOffset, int piecePosition){
        return BoardUtils.FIRST_COLUMN[piecePosition] && (currentCandidateOffset == -1);
    }

    @Override
    public boolean isEighthColumnExclusion(int currentCandidateOffset, int piecePosition){
        return BoardUtils.EIGHTH_COLUMN[piecePosition] && (currentCandidateOffset == 1);
    }
}
