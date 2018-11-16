package de.imut.oop.talkv2;

import java.net.Socket;

public class Communicator {

    private Sender sender;
    private Receiver receiver;
    private Boolean isServer;

    public Communicator(Socket socket) {
        this.sender = new Sender(socket);
        Thread senderThread = new Thread(this.sender, "Sender");
        senderThread.start();

        this.receiver = new Receiver(socket);
        Thread receiverThread = new Thread(this.receiver, "Receiver");
        receiverThread.start();
    }

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public Boolean isServer() {
        return isServer;
    }

    public void isServer(Boolean server) {
        isServer = server;
    }
}
