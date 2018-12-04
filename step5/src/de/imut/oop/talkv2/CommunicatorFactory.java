package de.imut.oop.talkv2;

import java.net.Socket;
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

    public Communicator createCommunicator(Socket socket) {
        Communicator c = new Communicator(socket);
        this.communicators.add(c);
        return c;
    }

    public void removeCommunicator(Communicator communicator) {
        communicators.remove(communicator);
    }

    public List<Communicator> getCommunicators() {
        return communicators;
    }
}
