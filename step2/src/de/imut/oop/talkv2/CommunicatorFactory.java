package de.imut.oop.talkv2;

import java.util.ArrayList;
import java.util.List;

public class CommunicatorFactory {

    // holds the singleton instance
    private static final CommunicatorFactory instance = new CommunicatorFactory();

    private List<Communicator> communicators;

    // private: to ensure that only one instance can be created
    private CommunicatorFactory() {
        this.communicators = new ArrayList<>();
    }

    // provides the singleton instance
    public static CommunicatorFactory getInstance() {
        return instance;
    }

    public Communicator createCommunicator(String remote, int senderPort, int receiverPort, String userName) {
        Communicator c = new Communicator(remote, senderPort, receiverPort, userName);
        this.communicators.add(c);
        return c;
    }
}
