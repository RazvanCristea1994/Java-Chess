package engine.pieces;

import engine.board.*;
import engine.Alliance;


public class Queen extends SlidingPiece {

    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(Alliance pieceAlliance,  int piecePosition) {
        super(CANDIDATE_MOVE_VECTOR_COORDINATE, PieceType.QUEEN, piecePosition, pieceAlliance);
    }

    @Override
    public Queen movePiece(Move move) {                     //return a new updated piece - placed in the new spot after being moved
        return new Queen(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }

    @Override
    public boolean isFirstColumnExclusion(int currentCandidateOffset, int piecePosition){
        return BoardUtils.FIRST_COLUMN[piecePosition] && (currentCandidateOffset == -9 || currentCandidateOffset == -1 ||
                                                          currentCandidateOffset == 7);
    }

    @Override
    public boolean isEighthColumnExclusion(int currentCandidateOffset, int piecePosition){
        return BoardUtils.EIGHTH_COLUMN[piecePosition] && (currentCandidateOffset == -7 || currentCandidateOffset == 1 ||
                                                           currentCandidateOffset == 9);
    }
}

