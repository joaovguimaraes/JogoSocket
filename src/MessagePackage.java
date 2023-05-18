import java.io.Serializable;
import java.util.Arrays;

public class MessagePackage implements Serializable {
    private String message;
    private int currentPlayerTurn;

    private int[][] board;

    private Boolean hit;

    private int playerLife;
    public MessagePackage() {
    }

    public MessagePackage(String message, int currentPlayerTurn, int[][] board, boolean hit, int playerLife) {
        this.message = message;
        this.currentPlayerTurn = currentPlayerTurn;
        this.board = board;
        this.hit = hit;
        this.playerLife = playerLife;
    }

    public String getMessage() {
        return message;
    }

    public int getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public void setCurrentPlayerTurn(int currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public Boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public int getPlayerLife() {
        return playerLife;
    }

    public void setPlayerLife(int playerLife) {
        this.playerLife = playerLife;
    }
    @Override
    public String toString() {
        return "MessagePackage{" +
                ", message='" + message + '\'' +
                ", currentPlayerTurn=" + currentPlayerTurn +
                ", Board"+ Arrays.deepToString(board) +
                '}';
    }
}