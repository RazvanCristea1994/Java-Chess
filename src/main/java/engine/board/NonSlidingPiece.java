package engine.board;

import com.google.common.collect.ImmutableList;
import engine.Alliance;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class NonSlidingPiece extends Piece{

    private final int[] CANDIDATE_MOVE_COORDINATE;

    public NonSlidingPiece(int[] CANDIDATE_MOVE_COORDINATE, PieceType pieceType, int piecePosition, Alliance pieceAlliance) {
        super(pieceType, piecePosition, pieceAlliance);
        this.CANDIDATE_MOVE_COORDINATE = CANDIDATE_MOVE_COORDINATE;
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board){

        Set<Move> legalMoves = new HashSet<Move>();
        for(int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {                            //loop through all possible moves
            int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {              //is it on the engine.board?

                if (isFirstColumnExclusion(currentCandidateOffset, piecePosition) ||
                        isSecondColumnExclusion (currentCandidateOffset, piecePosition) ||                         //check the exceptions
                        isSeventhColumnExclusion(currentCandidateOffset, piecePosition) ||
                        isEighthColumnExclusion(currentCandidateOffset, piecePosition)) {
                    continue;
                }

                Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (candidateDestinationTile.isTileOccupied()) {                                  //if the tile is occupied
                    Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    if (pieceAtDestination.getPieceAlliance() != this.getPieceAlliance()) {           //is it an enemy? => attacking move
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                } else {                                                                          //the tile is empty?
                    legalMoves.add(new Move.NormalMove(board, this, candidateDestinationCoordinate));
                }                                                                               //add a non-attacking move
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    public abstract Piece movePiece(Move move);

    public abstract boolean isFirstColumnExclusion(int currentCandidateOffset, int piecePosition);
    public abstract boolean isSecondColumnExclusion(int currentCandidateOffset, int piecePosition);
    public abstract boolean isSeventhColumnExclusion(int currentCandidateOffset, int piecePosition);
    public abstract boolean isEighthColumnExclusion(int currentCandidateOffset, int piecePosition);
}
