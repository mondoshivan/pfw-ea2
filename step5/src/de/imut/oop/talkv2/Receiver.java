package de.imut.oop.talkv2;

import de.imut.oop.talkv2.command.CommandListener;
import de.imut.oop.talkv2.command.RemoteCommand;
import de.imut.oop.talkv2.server.command.set.ExitCommand;
import de.imut.oop.talkv2.server.command.set.ServerCommand;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple receiver of network traffic.
 * @author Ralf Buschermoehle
 * @version 1.00
 */
public class Receiver implements Runnable
{
    private Socket socket;
    private List<CommandListener> listeners;

    public Receiver(Socket socket)
    {
        this.socket = socket;
        this.listeners = new ArrayList<>();
    }

    public void addListener(CommandListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(RemoteCommand command) {
        for (CommandListener listener : listeners) {
            listener.call(
                    command,
                    socket.getInetAddress().toString(),
                    socket.getPort()
            );
        }
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
            RemoteCommand command;

            do
            {
                command = (RemoteCommand) in.readObject();
                if (command instanceof ServerCommand) {
                    notifyListeners(command);
                } else {
                    command.execute();
                }
                
            } while(!(command instanceof ExitCommand)); 
        }
        catch (IOException e) {
            System.out.println("IO-Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
