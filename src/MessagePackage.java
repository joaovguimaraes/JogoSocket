import java.io.Serializable;
import java.util.Arrays;

public class MessagePackage implements Serializable {
    private String message;
    private int currentPlayerTurn;

    private int[][] board;

    private Boolean hit;

    private boolean matchEnded = false;
    public MessagePackage() {
    }

    public MessagePackage(String message, int currentPlayerTurn, int[][] board, boolean hit, boolean matchEnded) {
        this.message = message;
        this.currentPlayerTurn = currentPlayerTurn;
        this.board = board;
        this.hit = hit;
        this.matchEnded = matchEnded;
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

    public boolean hasMatchEnded() {
        return matchEnded;
    }

    public void setMatchEnded(boolean matchEnded) {
        matchEnded = matchEnded;
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