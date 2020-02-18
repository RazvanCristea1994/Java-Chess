package engine.board;

import com.google.common.collect.ImmutableList;
import engine.Alliance;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class SlidingPiece extends Piece{
    private final int[] CANDIDATE_MOVE_VECTOR_COORDINATE;

    public SlidingPiece(int[] CANDIDATE_MOVE_VECTOR_COORDINATE, PieceType pieceType, int piecePosition, Alliance pieceAlliance) {
        super(pieceType, piecePosition, pieceAlliance);
        this.CANDIDATE_MOVE_VECTOR_COORDINATE = CANDIDATE_MOVE_VECTOR_COORDINATE;
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        Set<Move> legalMove = new HashSet<Move>();
        for(int currentCandidateOffset : CANDIDATE_MOVE_VECTOR_COORDINATE) {                            //loop through all possible moves

            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) { //loop through all the tiles on the way,
                //until the tile that is not available, or it isOccupied
                if (isFirstColumnExclusion(currentCandidateOffset, piecePosition) ||
                        isEighthColumnExclusion(currentCandidateOffset, piecePosition)) {            //check the exceptions
                    break;
                }

                candidateDestinationCoordinate += currentCandidateOffset;            //go to the next tile on the same direction
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (candidateDestinationTile.isTileOccupied()) {                       //if the tile is occupied
                        Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        if (pieceAtDestination.getPieceAlliance() != this.getPieceAlliance()) {//is it an enemy? => attacking move
                            legalMove.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;      //if there is a piece on the way, the Bishop is blocked on that path so we don't check the rest of the tiles behind the occupied tile
                    } else {                                                               //the tile is empty?
                        legalMove.add(new Move.NormalMove(board, this, candidateDestinationCoordinate));    //a non-attacking move is possible
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMove);
    }

    public abstract Piece movePiece(Move move);

    public abstract boolean isFirstColumnExclusion(int currentCandidateOffset, int piecePosition);
    public abstract boolean isEighthColumnExclusion(int currentCandidateOffset, int piecePosition);
}
