package de.imut.oop.talkv2;

import de.imut.oop.talkv2.common.Common;

public class TalkServer {

    private static final int DEFAULT_LISTEN = 2048;

    public static void main(final String[] args) {
        int port = DEFAULT_LISTEN;
        if (args.length == 1)
            port = Common.parsePart(args[0], DEFAULT_LISTEN);
        System.out.println("Server started. Listening for incoming connection requests on port: " + port);
        Dispatcher dispatcher = new Dispatcher(port);
        Thread dispatcherThread = new Thread(dispatcher, "Dispatcher");
        dispatcherThread.start();
    }
}
