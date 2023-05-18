import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerThread implements Runnable {
    private final String EXIT_MSG = "exit";
    private Main server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int playerId;
    private String playerName;

    public ServerThread(Socket socket, Main server, int playerId) throws IOException {
        this.server = server;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            server.PlayerIdManager();
            playerName = (String) this.in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendId(int id) throws IOException {
        this.out.writeObject(id);
    }

    public String getAction() throws IOException {
        try {
            return (String) this.in.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(MessagePackage outMessage) throws IOException {
        System.out.println("Board do " + getPlayerName() + ":");
        System.out.println(Arrays.deepToString(outMessage.getBoard()));
        this.out.writeObject(outMessage);
    } // fim do método send(MessagePacket)

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


} // fim da classe ServerThread