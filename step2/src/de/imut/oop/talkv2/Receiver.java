package de.imut.oop.talkv2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple receiver of network traffic.
 * @author Ralf Buschermoehle
 * @version 1.00
 */
public class Receiver implements Runnable
{
    private int port;


    /**
     * A Receiver of information from the network.
     * @param port to listen to
     */
    public Receiver(final int port)
    {
        this.port = port;
    }


    /*
     * Receiver thread activation
     *
     * @see java.lang.Runnable#run()
     */
    public final void run()
    {
        try
        {
            ServerSocket server = new ServerSocket(port);
            Socket client = server.accept();										// wait for connection
            DataInputStream in = new DataInputStream(client.getInputStream());
            String name = in.readUTF();
            String message;

            do
            {
                message = in.readUTF();
                System.out.println(name + ": " + message);
            }
            while (!"exit.".equals(message));

            client.close();
        }
        catch (IOException e)
        {
            System.out.println("IO-Error: " + e.getMessage());
        }

        System.out.println("Receiver: Connection closed!");
        System.exit(1);
    }
}
