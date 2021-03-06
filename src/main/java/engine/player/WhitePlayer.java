package engine.player;

import engine.Alliance;
import engine.board.Board;
import engine.board.Move;
import engine.board.Piece;

import java.util.Collection;

public class WhitePlayer extends Player {
    public WhitePlayer(Board board, Collection<Move> whiteStandardLegalMoves, Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    public Player getOpponent() {
        return this.board.blackPlayer();
    }
}
