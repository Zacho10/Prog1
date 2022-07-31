import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

    public static void main(String args[]) throws InterruptedException {
        String host = "127.0.0.1";
        int port = 8081;
        for (int i=0; i< 6; i++){
            Thread x =  new Client(host, port);
            x.start();
            sleep(1000);
            x.join();
        }
    }

    String host ;
    int port ;
    public void run(){
        try {
            String serverHostname = new String("127.0.0.1");

            System.out.println("Verbindung zu " + serverHostname + " an Port " + port + ".");

            Socket echoSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;

            try {
                echoSocket = new Socket(serverHostname, 8081);
                out = new PrintWriter(echoSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + serverHostname);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            /** {@link UnknownHost} object used to read from console */
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Bitte Ziffer eingeben ('x' für Exit): ");
                String userInput = stdIn.readLine();
                /** Exit on 'x' char sent */
                if ("x".equals(userInput)) {
                    break;
                }
                out.println(userInput);
                System.out.println("server: " + in.readLine());
            }

            /** Closing all the resources */
            out.close();
            in.close();
            // stdIn.close();
            echoSocket.close();
        } catch (Exception e) {
            System.err.println(this);
            e.printStackTrace();
        }
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }


}