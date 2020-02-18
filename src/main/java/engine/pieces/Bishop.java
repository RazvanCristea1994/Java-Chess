package engine.pieces;

import engine.board.*;
import engine.Alliance;


public class Bishop extends SlidingPiece {

    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATE = {-9, -7, 7, 9};

    public Bishop(Alliance pieceAlliance, int piecePosition) {
        super(CANDIDATE_MOVE_VECTOR_COORDINATE, PieceType.BISHOP, piecePosition, pieceAlliance);
    }

    @Override
    public Bishop movePiece(Move move) {                 //return a new updated piece - placed in the new spot after being moved
        return new Bishop(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.BISHOP.toString();
    }

    @Override
    public boolean isFirstColumnExclusion(int currentCandidateOffset, int piecePosition){
        return BoardUtils.FIRST_COLUMN[piecePosition] && (currentCandidateOffset == -9 || currentCandidateOffset == 7);
    }

    @Override
    public boolean isEighthColumnExclusion(int currentCandidateOffset, int piecePosition){
        return BoardUtils.EIGHTH_COLUMN[piecePosition] && (currentCandidateOffset == -7 || currentCandidateOffset == 9);
    }
}
