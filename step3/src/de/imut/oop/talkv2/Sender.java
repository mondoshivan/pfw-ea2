package de.imut.oop.talkv2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A simple sender of network traffic.
 *
 * @author Ralf Buschermoehle
 * @version 1.00
 */
public class Sender implements Runnable
{
    private Socket socket;

    public Sender(Socket socket)
    {
        this.socket = socket;
    }

    /*
     * Sender thread activation
     *
     * @see java.lang.Runnable#run()
     */
    public final void run()
    {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outputStream = null;
        String line = null;

        try
        {
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(TalkClient.userName);

            do
            {
                line = inputReader.readLine();
                outputStream.writeUTF(line);
            } while (!"exit.".equals(line));

            socket.close();
        }
        catch (IOException e)
        {
            System.out.println("IO-Error: " + e.getMessage());
        }

        System.out.println("Communication ended.");
        System.exit(0);
    }
}