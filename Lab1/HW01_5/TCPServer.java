import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class TCPServer {
    private ServerSocket server;
    public TCPServer() throws Exception {
        this.server = new ServerSocket(9876);
    }

    private void listen() throws IOException {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from: " + clientAddress);

        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        while((data = br.readLine())!=null) {
            System.out.println("\r\nMessage from: "+clientAddress+": "+data);
        }

    }


    public static void main(String[] args) throws Exception {
        TCPServer app = new TCPServer();
        System.out.println("\r\nRunning server: " +
                "Host: "+ app.server.getInetAddress() +
                "Port: "+ app.server.getLocalPort());
        app.listen();
    }
}
