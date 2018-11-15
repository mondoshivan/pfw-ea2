package de.imut.oop.talkv2;

import java.net.Socket;

public class Communicator {

    public Communicator(Socket socket) {
        Sender sender = new Sender(socket);
        Thread senderThread = new Thread(sender, "Sender");
        senderThread.start();

        Receiver receiver = new Receiver(socket);
        Thread receiverThread = new Thread(receiver, "Receiver");
        receiverThread.start();
    }
}
