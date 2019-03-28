package HW02_4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class TCPServer {

    private ServerSocket server;
    private static ConcurrentHashMap<Integer, Socket> activeConnections;

    class ServerWorkerThread extends Thread {
        Socket client;
        DataInputStream reader;

        ServerWorkerThread(Socket client) throws IOException {
            this.client = client;
            reader = new DataInputStream(client.getInputStream());
        }

        @Override
        public void run() {
            // todo: Handle listening to messages
            String line = null;
            try {
                while (!(line = reader.readUTF()).isEmpty()) {
                    if(line.startsWith("END:")) {
                        // izvadi go END: od porakata za da se dobie ID-to
                        endConnection(Integer.parseInt(line.substring(4)));
                    }else {
                        String[] split = line.split(":");
                        String targetSubstring = split[split.length - 1];
                        Integer target = Integer.parseInt(targetSubstring);
                        String message = line.substring(0, line.length() - (targetSubstring.length() + 1));
                        // pisi na target-ot so dataoutputstream
                        new DataOutputStream(getConnection(target).getOutputStream()).writeUTF(message);
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // todo: Get the required connection
    public Socket getConnection(int id) {
        return activeConnections.get(id);
    }

    // todo: Add connected client to the hash map
    void addConnection(int id, Socket connection) {
        activeConnections.put(id, connection);
    }

    synchronized void endConnection(int id){
        activeConnections.remove(id);
    }

    //todo: Initialize server
    TCPServer(int port) throws IOException {
        server = new ServerSocket(port);
    }

    // todo: Handle server listening
    // todo: For each connection, start a separate
    // todo: thread (ServerWorkerThread) to handle the communication
    void listen() throws IOException {
        Socket client;
        while(true) {
            client = server.accept();
            String firstMessage = new DataInputStream(client.getInputStream()).readUTF();
            // Izvadi go CONNECT: od prvata poraka za da se dobie id-to
            addConnection(Integer.parseInt(firstMessage.substring(8)), client);
            ServerWorkerThread workerThread = new ServerWorkerThread(client);
            workerThread.start();
        }
    }

    public static void main(String[] args) throws IOException {
        // todo: Start server
        activeConnections = new ConcurrentHashMap<>();
        TCPServer serverSocket = new TCPServer(8080);
        serverSocket.listen();
    }
}
