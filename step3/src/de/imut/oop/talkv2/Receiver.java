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
    private Socket socket;

    public Receiver(Socket socket)
    {
        this.socket = socket;
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
            DataInputStream in = new DataInputStream(this.socket.getInputStream());
            String name = in.readUTF();
            String message;

            do
            {
                message = in.readUTF();
                System.out.println(name + ": " + message);
            }
            while (!"exit.".equals(message));

            this.socket.close();
        }
        catch (IOException e)
        {
            System.out.println("IO-Error: " + e.getMessage());
        }

        System.out.println("Client has quit the connection.");
        System.exit(1);
    }
}
