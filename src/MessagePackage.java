import java.io.Serializable;
import java.util.Arrays;

public class MessagePackage implements Serializable {
    private String message;
    private int currentPlayerTurn;

    private int[][] board;

    public MessagePackage() {
    }


    public MessagePackage(String message, int currentPlayerTurn, int[][] board) {
        this.message = message;
        this.currentPlayerTurn = currentPlayerTurn;
        this.board = board;
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

    @Override
    public String toString() {
        return "MessagePackage{" +
                ", message='" + message + '\'' +
                ", currentPlayerTurn=" + currentPlayerTurn +
                ", Board"+ Arrays.deepToString(board) +
                '}';
    }
}