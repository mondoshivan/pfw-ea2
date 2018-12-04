package de.imut.oop.talkv2;

import java.net.Socket;

public class Communicator {

    private static int instances = 0;
    private Sender sender;
    private Receiver receiver;
    private int id;

    public Communicator(Socket socket) {
        this.sender = new Sender(socket);
        Thread senderThread = new Thread(this.sender, "Sender");
        senderThread.start();

        this.receiver = new Receiver(socket);
        Thread receiverThread = new Thread(this.receiver, "Receiver");
        receiverThread.start();

        this.id = instances;
        Communicator.instances ++;
    }

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public int getId() {
        return id;
    }
}
