import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Player {
    private final String HOST = "localhost";
    private final int PORT = 5000;

    private Socket socket;
    private final Scanner scanner;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int playerId;
    private String name;
    private int[][] currentBoard;
    public Player() {
        this.scanner = new Scanner(System.in);
        this.name = askName();
    }

    public static void main(String[] args) {
        new Player().start();
    }

    private void start() {
        connect();
        run();
        disconnect();
    }

    private void connect() {
        System.out.println("Conectando ao servidor...");
        try {
            this.socket = new Socket(HOST, PORT);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Cliente conectado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() {
        try {
            int id = (int) this.in.readObject();
            System.out.println("PlayerId:" + id);
            setPlayerId(id);
            this.out.writeObject(name);
            while (true) {
                MessagePackage data = attackIn();
                setCurrentBoard(data.getBoard()); // Atualiza o tabuleiro atualizado
                showBoard(currentBoard); // Exibe o tabuleiro atualizado
                if (data.getCurrentPlayerTurn() == playerId) {
                    send(askAction());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MessagePackage attackIn() throws IOException, ClassNotFoundException {
        MessagePackage obj = (MessagePackage) this.in.readObject();
        System.out.println("AttackIn: " + obj);
        return obj;
    }

    private String askAction(){
        String playerAttack;
        do {
            System.out.println("Qual sua jogada: ");
            playerAttack = scanner.nextLine();
        } while (!BatalhaNaval.validarTiroDoJogador(playerAttack));
        return playerAttack;
    }

    private void disconnect() {
        try {
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // fim do método disconnect()

    private void send(String message) throws IOException {
        this.out.writeObject(message);
    } // fim do método send(String)

    private String askName() {
        boolean valid = true;
        String name;
        do {
            System.out.print("Informe seu nome: ");
            name = scanner.nextLine();
            valid = (!name.isBlank());
            if (!valid) {
                System.out.println("Nome inválido!");
            }
        } while (!valid);
        return name;
    } // fim do método askName()

    public static void showBoardNumbers() {
        int numeroDaColuna = 1;
        StringBuilder numerosDoTabuleiro = new StringBuilder("   ");

        for(int i = 0; i < BatalhaNaval.tamanhoY; i++) {
            numerosDoTabuleiro.append((numeroDaColuna++)).append(" ");
        }
        System.out.println(numerosDoTabuleiro);
    }

    public void showBoard(int[][] tabuleiro) {
        System.out.println("|----- " + this.name + " -----|");
        showBoardNumbers();
        StringBuilder linhaDoTabuleiro = new StringBuilder();
        char letraDaLinha = 65;

        for(int[] linha : tabuleiro) {
            linhaDoTabuleiro = new StringBuilder((letraDaLinha++) + " |");
            for (int coluna : linha) {
                switch (coluna) {
                    case BatalhaNaval.VAZIO -> linhaDoTabuleiro.append(" |");
                    case BatalhaNaval.NAVIO -> linhaDoTabuleiro.append("N|");
                    case BatalhaNaval.ERROU_TIRO -> linhaDoTabuleiro.append("X|");
                    case BatalhaNaval.ACERTOU_TIRO -> linhaDoTabuleiro.append("D|");
                }
            }
            System.out.println(linhaDoTabuleiro);
        }
    }

    public int getPlayerId() {
        return playerId;
    }
    public void setCurrentBoard(int[][] board) {
        this.currentBoard = board;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
