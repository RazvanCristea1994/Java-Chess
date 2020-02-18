package engine.board;

public abstract class Move {

    protected final Board board;
    protected final Piece movedPiece;
    private final int destinationCoordinate;

    private Move(Board board, Piece movedPiece, int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate(){
        return this.destinationCoordinate;
    }

    public Piece getMovedPiece(){
        return this.movedPiece;
    }

    public abstract Board execute();                            //when a player makes a move that is legal,
                                                                //execute() will return a new board with the current positions and current player

    public static final class NormalMove extends Move{

        public NormalMove(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        public Board execute() {
            Board.Builder builder = new Board.Builder();                                        //this makes a new board

            //this is for the current player's pieces
            for(Piece piece : this.board.currentPlayer().getActivePieces()){                    //loop through all active pieces
                if(!movedPiece.equals(piece)){                                                  //except the moved one
                    builder.setPiece(piece);                                                    //and put them in their places
                }
            }

            //this is for the opponent's pieces
            for(Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){      //loop through all active pieces
                builder.setPiece(piece);                                                        //and put them in their places
            }

            builder.setPiece(this.movedPiece.movePiece(this));                                  //this will move the movedPiece
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());       //change the current player
            return builder.build();
        }
    }

    public static final class AttackMove extends Move{

        Piece attackedPiece;

        public AttackMove(Board board, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        public Board execute() {
            return null;
        }
    }

  /*  public static final class NullMove extends Move{

        public NullMove(Board engine.board, Piece movedPiece, int destinationCoordinate) {
            super(engine.board, movedPiece, destinationCoordinate);
        }
    }
*/

}
