package engine.player;

import engine.Alliance;
import engine.board.Board;
import engine.board.Move;
import engine.board.Piece;

import java.util.Collection;

public class BlackPlayer extends Player{
    public BlackPlayer(Board board, Collection<Move> whiteStandardLegalMoves, Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    public Player getOpponent() {
        return this.board.whitePlayer();
    }
}
