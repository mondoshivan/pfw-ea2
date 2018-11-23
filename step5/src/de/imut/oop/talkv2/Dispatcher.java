package de.imut.oop.talkv2;

import de.imut.oop.talkv2.client.command.set.MessageCommand;
import de.imut.oop.talkv2.command.CommandListener;
import de.imut.oop.talkv2.command.RemoteCommand;
import de.imut.oop.talkv2.common.SystemExitCode;
import de.imut.oop.talkv2.server.command.set.BroadcastCommand;
import de.imut.oop.talkv2.server.command.set.ExitCommand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Dispatcher implements Runnable, CommandListener {

    private int listeningPort;
    private CommunicatorFactory factory;

    public Dispatcher(int port) {
        this.listeningPort = port;
        this.factory = CommunicatorFactory.getInstance();
    }

    private void broadcast(BroadcastCommand broadcastCommand) {
        List<Communicator> communicators = factory.getCommunicators();
        String user = broadcastCommand.getUser();
        String message = broadcastCommand.getMessage();
        System.out.println("Message \"[" + user + "] " + message + "\" received:");
        MessageCommand messageCommand = new MessageCommand(user, message);

        for (Communicator communicator : communicators) {
            int id = communicator.getId();
            System.out.println(" -> redirecting to client " + id);
            communicator.getSender().sendCommand(messageCommand);
        }
    }

    private void exit(ExitCommand command, String ip, int port) {
        List<Communicator> communicators = factory.getCommunicators();
        for (Communicator communicator : communicators) {
            Socket socket = communicator.getSender().getSocket();
            String socketIp = socket.getInetAddress().toString();
            int socketPort = socket.getPort();
            if (ip.equals(socketIp) && port == socketPort) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                factory.removeCommunicator(communicator);
                break;
            }
        }
        if (communicators.size() == 0) quit();
    }

    private void quit() {
        System.out.println("No more clients available - shutting down server");
        System.exit(SystemExitCode.NORMAL.getCode());
    }

    @Override
    public void run() {
        while (true) {
            try {
                ServerSocket server = new ServerSocket(this.listeningPort);
                Socket socket = server.accept();
                String clientIP = socket.getInetAddress().toString();
                int clientPort = socket.getPort();
                System.out.println("Connection request from " + clientIP + ":" + clientPort);
                Communicator communicator = factory.createCommunicator(socket);
                communicator.getReceiver().addListener(this);
            } catch (IOException e) {
                Thread.yield();
            }
        }
    }

    @Override
    public void call(RemoteCommand command, String ip, int port) {
        if (command instanceof BroadcastCommand) broadcast((BroadcastCommand) command);
        if (command instanceof ExitCommand) exit((ExitCommand) command, ip, port);
    }
}
