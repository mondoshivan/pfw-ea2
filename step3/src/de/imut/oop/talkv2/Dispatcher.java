package de.imut.oop.talkv2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Dispatcher implements Runnable {

    private int listeningPort;

    public Dispatcher(int port) {
        this.listeningPort = port;
    }

    @Override
    public void run() {
        CommunicatorFactory factory = CommunicatorFactory.getInstance();

        while (true) {
            try {
                ServerSocket server = new ServerSocket(this.listeningPort);
                Socket socket = server.accept();
                String clientIP = socket.getInetAddress().toString();
                int clientPort = socket.getPort();
                System.out.println("Connection request from " + clientIP + ":" + clientPort);
                factory.createCommunicator(socket);
            } catch (IOException e) {
                Thread.yield();
            }
        }
    }
}
