package engine.player;

import com.google.common.collect.ImmutableList;
import engine.Alliance;
import engine.board.Board;
import engine.board.Move;
import engine.board.Piece;
import engine.pieces.King;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {

    protected final Board board;
    private final King playerKing;
    private final Collection<Move> legalMoves;
    private final boolean isInCheck;

    public Player(Board board, Collection<Move> legalMoves, Collection<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
        this.isInCheck = !Player.calculateAttacksOnKing(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }

    private static Collection<Move> calculateAttacksOnKing(int kingPosition, Collection<Move> opponentMoves) {          //check whether the king is attacked
        List<Move> attackMovesOnKing = new ArrayList<Move>();
        for(Move move :  opponentMoves){
            if(kingPosition == move.getDestinationCoordinate()){                                                        //are there any opponent possible move on the king's location?
                attackMovesOnKing.add(move);
            }
        }
        return ImmutableList.copyOf(attackMovesOnKing);
    }

    private King establishKing(){                       //find the king
        for(Piece piece : getActivePieces()){
            if(piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! Not a valid board!");
    }

    public boolean isMoveLegal(Move move){
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
        return this.isInCheck;
    }

    public boolean isInCheckMate(){
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isInStaleMate(){
        return !this.isInCheck && !hasEscapeMoves();
    }

    private boolean hasEscapeMoves(){
        for (Move move : this.legalMoves){                  //try all the possible moves of the current player
            MoveTransition transition = makeMove(move);     //on an imaginary board
            if(transition.getMoveStatus().isDone()){        //is the king not in check anymore?
                return true;                                //true if there is an escape
            }
        }
        return false;                                       //false if the move can't escape the king
    }

    public boolean isCastled(){
        return false;
    }

    public MoveTransition makeMove(Move move){
        if(!isMoveLegal(move)){                                                                 //if the move is illegal (not part of the Collection<Move> legalMoves that the player has)
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);               //return the same board (this), we don't make the transition to an other one because there was no move done
        }

        Board transitionBoard = move.execute();                                                 //if the move is legal, execute it on a transition board
                                                                                                //(this will generate a new board with the current positions)
        //ToDo:I modified bellow a little bit
        Collection<Move> kingAttacks = Player.calculateAttacksOnKing(transitionBoard.currentPlayer().getOpponent().playerKing.getPiecePosition(),       //getOpponent because the currentPlayer changes already on the transitionBoard
                                                                     transitionBoard.currentPlayer().legalMoves);                                       //check if now there are attacks on the
                                                                                                                                                        //player's king that has just made the move
        if(!kingAttacks.isEmpty()){                                                             //if the player wants to do a move that exposes in check its own king, the move is not possible
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);     // => return the same board (this). We don't make the transition to an other one because there was no move done
        }
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
}
