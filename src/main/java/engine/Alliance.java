package engine;

import engine.player.BlackPlayer;
import engine.player.Player;
import engine.player.WhitePlayer;

public enum Alliance {
    WHITE{

        @Override
        public int getDirection(){
            return 1;
        }

        public boolean isBlack() {
            return false;
        }

        public boolean isWhite() {
            return true;
        }

        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            return whitePlayer;
        }
    },
    BLACK{

        @Override
        public int getDirection(){
            return -1;
        }

        public boolean isBlack() {
            return true;
        }

        public boolean isWhite() {
            return false;
        }

        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    };

    public abstract int getDirection();
    public abstract boolean isBlack();
    public abstract boolean isWhite();

    public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
