import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class Main {
    private final int PORT = 5000;
    private List<ServerThread> threads;
    private int[][] tabuleiroJogador1;
    private int[][] tabuleiroJogador2;
    static int currentPlayerTurn = 0;
    private boolean matchEnded = false;

    public Main() {
        threads = new ArrayList<>();
    }

    public static void main(String[] args) {
        new Main().start();
    }

    public synchronized void PlayerIdManager() throws IOException {
        threads.get(threads.size() - 1).sendId(threads.size() - 1);
    }

    private void start() {
        System.out.println("Iniciando o servidor...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado");
            listen(serverSocket);
            startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String messageString(int id) {
        return id == currentPlayerTurn ? "Sua vez"+threads.get(0).getPlayerName() : "Vez do outro";
    }

    public void startGame() throws IOException {
        BatalhaNaval.setNomeJogador1(threads.get(0).getPlayerName());
        BatalhaNaval.setNomeJogador2(threads.get(1).getPlayerName());
        this.startBoard();

        while (BatalhaNaval.naviosJogador1 > 0 || BatalhaNaval.naviosJogador2 > 0) {
            System.out.println("Turno do " + threads.get(currentPlayerTurn).getPlayerName());
            MessagePackage mp1 = new MessagePackage();
            mp1.setBoard(BatalhaNaval.parseStringToArray(Arrays.deepToString(tabuleiroJogador1)));
            mp1.setMessage(Arrays.deepToString(tabuleiroJogador1));
            mp1.setCurrentPlayerTurn(currentPlayerTurn);
            threads.get(0).play(mp1);

            MessagePackage mp2 = new MessagePackage();
            mp2.setBoard(BatalhaNaval.parseStringToArray(Arrays.deepToString(tabuleiroJogador2)));
            mp2.setMessage(Arrays.deepToString(tabuleiroJogador2));
            mp2.setCurrentPlayerTurn(currentPlayerTurn);
            threads.get(1).play(mp2);

            String playerAction = threads.get(currentPlayerTurn).getAction();
            playerAction(playerAction, currentPlayerTurn);
            setCurrentPlayerTurn(currentPlayerTurn == 0 ? 1 : 0);
        }
        System.out.println("Acabou");
    }

    private void listen(ServerSocket serverSocket) throws IOException {
        while (threads.size() < 2) {
            System.out.println("ThreadSize: " + threads.size());
            System.out.println("Aguardando conexão...");
            Socket socket = serverSocket.accept();
            System.out.println("Conexão aceita");

            ServerThread serverThread = new ServerThread(socket, this, threads.size());
            threads.add(serverThread);
            new Thread(serverThread).start();
        }
    }

    public void playerAction(String playerAttack, int playerId) {
        int[] positions = BatalhaNaval.retornarPosicoesDigitadasPeloJogador(playerAttack);
        if (BatalhaNaval.validarPosicoesInseridasPeloJogador(positions)) {
            setAnotherBoard(BatalhaNaval.inserirValoresDaAcaoNoTabuleiro(positions, playerId, getAnotherBoard()));
        }
    }

    public int getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public int [] [] getAnotherBoard() {
        return currentPlayerTurn == 0 ? this.tabuleiroJogador2 : this.tabuleiroJogador1;
    }

    public void setAnotherBoard(int [] []board) {
        if( currentPlayerTurn == 0 )
            this.tabuleiroJogador2 = board;
        else
            this.tabuleiroJogador1 = board;
    }

    public void setCurrentPlayerTurn(int currentPlayerTurn) {
        this.currentPlayerTurn = currentPlayerTurn;
    }

    public void startBoard() {
        this.tabuleiroJogador1 = BatalhaNaval.retornarNovoTabuleiroComOsNavios();
        this.tabuleiroJogador2 = BatalhaNaval.retornarNovoTabuleiroComOsNavios();
    }
}