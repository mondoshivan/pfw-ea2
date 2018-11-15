package de.imut.oop.talkv2;

import de.imut.oop.talkv2.common.Common;
import de.imut.oop.talkv2.common.SystemExitCode;

/**
 * A driver for a simple sender of network traffic.
 *
 * @author Ralf Buschermoehle
 * @version 1.00
 */
public class Talk {

    private static final int DEFAULT_LISTEN = 2048;
    private static final int DEFAULT_TALK = 2048;
    private static final String DEFAULT_IP = "localhost";


    /**
     * A simple talk/chat application.
     *
     * @param args
     *            arguments transferred from the operating system args[0]: the
     *            port to listen to (default: 2048) args[1]: the port to talk to
     *            (default: 2049) args[2]: remote machine to talk to (default:
     *            localhost)
     *
     */
    public static void main(final String[] args)
    {
        int talk = DEFAULT_TALK;
        int listen = DEFAULT_LISTEN;
        String remote = DEFAULT_IP;

        switch (args.length)
        {
            case 3:
                remote = args[2];
            case 2:
                talk = Common.parsePart(args[1], DEFAULT_TALK);
            case 1:
                listen = Common.parsePart(args[0], DEFAULT_LISTEN);
        }

        String userName = Common.getUserName();

        // Sender
        Sender sender = new Sender(remote, listen, userName);
        Thread senderThread = new Thread(sender, "Sender");
        senderThread.start();

        // Receiver
        Receiver receiver = new Receiver(talk);
        Thread receiverThread = new Thread(receiver, "Receiver");
        receiverThread.start();
    }
}