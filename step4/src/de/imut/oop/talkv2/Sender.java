package de.imut.oop.talkv2;

import com.sun.tools.javah.Util;
import de.imut.oop.talkv2.client.command.set.MessageCommand;
import de.imut.oop.talkv2.command.RemoteCommand;
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

    public Sender(Socket socket)
    {
        this.socket = socket;
        try {
            outputStream = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;

        try {

            RemoteCommand command;
            do
            {
                line = inputReader.readLine();

                if (line.equals("exit.")) {
                    command = new ExitCommand();
                } else {
                    command = new MessageCommand(TalkClient.userName, line);
                }

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
}