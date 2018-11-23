package de.imut.oop.talkv2;

import com.sun.tools.javah.Util;
import de.imut.oop.talkv2.client.command.set.MessageCommand;
import de.imut.oop.talkv2.command.RemoteCommand;
import de.imut.oop.talkv2.server.command.set.BroadcastCommand;
import de.imut.oop.talkv2.server.command.set.ExitCommand;

import java.io.*;
import java.net.Socket;

/**
 * A simple sender of network traffic.
 *
 * @author Ralf Buschermoehle
 * @version 1.00
 */
public class Sender implements Runnable
{
    private Socket socket;
    private ObjectOutputStream outputStream;
    private BufferedReader inputReader;
    private String userName;

    public Sender(Socket socket)
    {
        this.socket = socket;
        inputReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            outputStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RemoteCommand getCommand() {
        String line = null;
        try {
            line = inputReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RemoteCommand command;
        if (line.equals("exit.")) {
            command = new ExitCommand();
        } else {
            command = new BroadcastCommand(userName, line);
        }
        return command;
    }

    public void sendCommand(RemoteCommand command) {
        try {
            this.outputStream.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Sender thread activation
     *
     * @see java.lang.Runnable#run()
     */
    public final void run()
    {
        try {
            RemoteCommand command;
            do
            {
                command = getCommand();
                sendCommand(command);
            } while (!(command instanceof ExitCommand));

            this.socket.close();
        }
        catch (IOException e) {
            System.out.println("IO-Error: " + e.getMessage());
        }

        System.out.println("Communication ended.");
        System.exit(0);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}