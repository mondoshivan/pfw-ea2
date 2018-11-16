package de.imut.oop.talkv2;

import de.imut.oop.talkv2.common.Common;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TalkClient {

    private static final int DEFAULT_SERVER_PORT = 2048;
    private static final String DEFAULT_SERVER_IP = "localhost";
    public static String userName = "";

    //------------------------------------------
    private Socket getSocket(String serverIP, int serverPort) {
        System.out.println("Trying to connect to remote " + serverIP + ":" + serverPort);
        Socket socket = null;
        do {
            try {
                socket = new Socket(serverIP, serverPort);
            }catch (UnknownHostException e) {
                System.err.println(e);
                System.exit(-1);
            } catch (IOException e) {
                Thread.yield();
            }
        } while (socket == null);

        System.out.println("End communication with line = \"exit.\"");
        System.out.println("Connection established to " + serverIP + ":" + serverPort);
        return socket;
    }

    public static void main(final String[] args) {
        String serverIP = DEFAULT_SERVER_IP;
        int serverPort = DEFAULT_SERVER_PORT;
        TalkClient.userName = Common.getUserName();

        switch (args.length)
        {
            case 2:
                serverIP = args[1];
            case 1:
                serverPort = Common.parsePart(args[0], DEFAULT_SERVER_PORT);
        }

        TalkClient client = new TalkClient();
        Socket socket = client.getSocket(serverIP, serverPort);
        CommunicatorFactory factory = CommunicatorFactory.getInstance();
        factory.createCommunicator(socket);
    }

}
