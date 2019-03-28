package HW01_5;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    private Socket socket;
    private Scanner scanner;

    public TCPClient(InetAddress serverAddress, int serverPort) throws IOException {
        this.socket = new Socket(serverAddress,serverPort);
        this.scanner = new Scanner(System.in);
    }


    private void start() throws IOException {

            double prv = 1.25;
            long vtor = 123584124;
            boolean tret = true;
            String cetvrt = "UTF String";

        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(prv);
            out.println(vtor);
            out.println(tret);
            out.println(cetvrt);

        out.flush();

        }



    public static void main(String[] args) throws IOException {
        TCPClient client = new TCPClient(InetAddress.getByName("localhost"),9876);
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();
        client.socket.close();
    }
}
