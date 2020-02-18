package engine.pieces;

import engine.board.*;

import engine.Alliance;


public class King extends NonSlidingPiece{

    private static final int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};             //all the possible moves
                                                                                                      //in a perfect situation
    public King(Alliance pieceAlliance, int piecePosition) {
        super(CANDIDATE_MOVE_COORDINATES, PieceType.KING, piecePosition, pieceAlliance);
    }

    @Override
    public King movePiece(Move move) {                 //return a new updated piece - placed in the new spot after being moved
        return new King(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.KING.toString();
    }

    @Override
    public boolean isFirstColumnExclusion(int currentCandidateOffset, int piecePosition) {
        return BoardUtils.FIRST_COLUMN[piecePosition] && (currentCandidateOffset == -9 || currentCandidateOffset == -1 ||
                currentCandidateOffset == 7);
    }

    @Override
    public boolean isSecondColumnExclusion(int currentCandidateOffset, int piecePosition){
        return false;
    }

    @Override
    public boolean isSeventhColumnExclusion(int currentCandidateOffset, int piecePosition){
        return false;
    }

    @Override
    public boolean isEighthColumnExclusion(int currentCandidateOffset, int piecePosition) {
        return BoardUtils.EIGHTH_COLUMN[piecePosition] && (currentCandidateOffset == -7 || currentCandidateOffset == 1 ||
                currentCandidateOffset == 9);
    }
}

