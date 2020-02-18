package engine.pieces;

import engine.board.*;
import com.google.common.collect.ImmutableList;
import engine.Alliance;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends NonSlidingPiece {

    private static final int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};           //all the possible moves
                                                                                    //in a perfect situation
    public Pawn(Alliance pieceAlliance, int piecePosition) {
        super(CANDIDATE_MOVE_COORDINATE, PieceType.PAWN, piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board){
        Set<Move> legalMoves = new HashSet<Move>();
        for(int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
            int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);

            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) ||
                isFirstColumnExclusion(currentCandidateOffset, piecePosition) ||
                isEighthColumnExclusion(currentCandidateOffset, piecePosition)){
                continue;
            }

            Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
            if(currentCandidateOffset == 8 && !candidateDestinationTile.isTileOccupied()){                  //if the tile in front of the pawn is empty
                //ToDo: Deal with promotions
                //legalMoves.add(new Move.PawnNormalMove(engine.board, this, candidateDestinationCoordinate));       //add move

            } else if (currentCandidateOffset == 16 && isJumpPossible(currentCandidateOffset, piecePosition, candidateDestinationTile, board)){         //if jump is possible
               // legalMoves.add(new Move.PawnJump(engine.board, this, candidateDestinationCoordinate));                                                         //add move

            } else if (currentCandidateOffset == 7 &&                                                                   //white pawn from eighth column can't attack to the right
                    !(BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceAlliance().isWhite()) ||             //and the other way round
                    !(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceAlliance().isBlack())){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {                                    //if the tile on diagonal is occupied (on 7)
                    Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();

                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){                                      //and the piece is an enemy
                        //legalMoves.add(new Move.PawnAttackMove(engine.board, this, candidateDestinationCoordinate, pieceOnCandidate));         //add move
                    }
                }
            } else if (currentCandidateOffset == 9 &&                                                                   //white pawn from eighth column can't attack to the right
                    !(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceAlliance().isWhite()) ||             //and the other way round
                    !(BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceAlliance().isBlack())){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {                                    //if the tile on diagonal is occupied (on 7)
                    Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();

                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance()){                                      //and the piece is an enemy
                        //legalMoves.add(new Move.PawnAttackMove(engine.board, this, candidateDestinationCoordinate, pieceOnCandidate));         //add move
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public Pawn movePiece(Move move) {                     //return a new updated piece - placed in the new spot after being moved
        return new Pawn(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }

    @Override
    public boolean isFirstColumnExclusion(int currentCandidateOffset, int piecePosition){
        return BoardUtils.FIRST_COLUMN[piecePosition] &&
                ((this.getPieceAlliance().getDirection() == -1 && currentCandidateOffset == -9) ||
                 (this.getPieceAlliance().getDirection() == 1 && currentCandidateOffset == 7));
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
    public boolean isEighthColumnExclusion(int currentCandidateOffset, int piecePosition){
        return BoardUtils.FIRST_COLUMN[piecePosition] &&
                ((this.getPieceAlliance().getDirection() == -1 && currentCandidateOffset == -9) ||
                        (this.getPieceAlliance().getDirection() == 1 && currentCandidateOffset == 7));
    }

    private boolean isJumpPossible(int currentCandidateOffset, int piecePosition, Tile candidateDestinationTile, Board board){

        Tile tileInBetweenPieceAndDestination = board.getTile( this.piecePosition +
                                                (this.getPieceAlliance().getDirection() * 8));
                                                                                                                    //The jump is possible if
        return   isFirstMove && currentCandidateOffset == 16 &&                                               //it is the first move and the offset is 16,
                ((BoardUtils.SECOND_ROW[piecePosition] && this.getPieceAlliance().isBlack()) ||                     //the piece is on the second row and it is black or
                 BoardUtils.SEVENTH_ROW[piecePosition] && this.getPieceAlliance().isWhite()) &&                     //the piece is on the seventh row and it is white and
                 !tileInBetweenPieceAndDestination.isTileOccupied() && !candidateDestinationTile.isTileOccupied();  //the tile in between is empty and the destination tile is empty
    }
}
