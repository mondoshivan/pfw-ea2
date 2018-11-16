package de.imut.oop.talkv2;

import de.imut.oop.talkv2.command.RemoteCommand;
import de.imut.oop.talkv2.server.command.set.ExitCommand;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

    public void sendMessage(String message) {
        System.out.println(message);
    }

    /*
     * Receiver thread activation
     *
     * @see java.lang.Runnable#run()
     */
    public final void run()
    {
        try {
            ObjectInputStream in = new ObjectInputStream(new DataInputStream(this.socket.getInputStream()));
            RemoteCommand command = (RemoteCommand) in.readObject();
            command.execute();

            do
            {
                command = (RemoteCommand) in.readObject();
                command.execute();
            }
            while (!(command instanceof ExitCommand));

            this.socket.close();
        }
        catch (IOException e) {
            System.out.println("IO-Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Client has quit the connection.");
        System.exit(1);
    }
}
