package engine.pieces;

import engine.board.*;

import engine.Alliance;


public class Knight extends NonSlidingPiece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17}; //all the possible moves
                                                                                                //in a perfect situation
    public Knight(Alliance pieceAlliance, int piecePosition) {
        super(CANDIDATE_MOVE_COORDINATES, PieceType.KNIGHT, piecePosition, pieceAlliance);
    }

    @Override
    public Knight movePiece(Move move) {                     //return a new updated piece - placed in the new spot after being moved
        return new Knight(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.KNIGHT.toString();
    }

    @Override
    public boolean isFirstColumnExclusion(int currentCandidateOffset, int piecePosition) {
        return BoardUtils.FIRST_COLUMN[piecePosition] && (currentCandidateOffset == -15 || currentCandidateOffset == -6 ||
                                                           currentCandidateOffset == 10 || currentCandidateOffset == 17);
    }

    @Override
    public boolean isSecondColumnExclusion(int currentCandidateOffset, int piecePosition) {
        return BoardUtils.SECOND_COLUMN[piecePosition] && (currentCandidateOffset == -6 || currentCandidateOffset == 10);
    }

    @Override
    public boolean isSeventhColumnExclusion(int currentCandidateOffset, int piecePosition) {
        return BoardUtils.SEVENTH_COLUMN[piecePosition] && (currentCandidateOffset == -10 || currentCandidateOffset == 6);
    }

    @Override
    public boolean isEighthColumnExclusion(int currentCandidateOffset, int piecePosition) {
        return BoardUtils.EIGHTH_COLUMN[piecePosition] && (currentCandidateOffset == -17 || currentCandidateOffset == -10 ||
                                                           currentCandidateOffset == 6 || currentCandidateOffset == 15);
    }
}
