import java.io.IOException;
import java.net.ServerSocket;

public class ClientServerCommunication {
    public static final int PORT_NUMBER = 8081;

    public static void main(String[] args) {
        System.out.println("SocketServer Example");

        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */

                new Server(server.accept());

            }
        } catch (IOException ex) {
            System.out.println("Kein Start des Servers möglich.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
