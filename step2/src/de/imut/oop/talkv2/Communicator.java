package de.imut.oop.talkv2;

public class Communicator {

    public Communicator(String remote, int senderPort, int receiverPort, String userName) {
        Sender sender = new Sender(remote, senderPort, userName);
        Thread senderThread = new Thread(sender, "Sender");
        senderThread.start();

        Receiver receiver = new Receiver(receiverPort);
        Thread receiverThread = new Thread(receiver, "Receiver");
        receiverThread.start();
    }
}
