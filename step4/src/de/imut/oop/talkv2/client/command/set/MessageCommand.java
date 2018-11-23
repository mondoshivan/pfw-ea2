package de.imut.oop.talkv2.client.command.set;

import de.imut.oop.talkv2.command.RemoteCommand;

public class MessageCommand extends ClientCommand {

    private String message;
    private String user;

    /**
     * Constructs the message command.
     */
    public MessageCommand(String user, String message) {
        this.user = user;
        this.message = message;
    }

    /**
     * Prints the message.
     */
    public void execute() {
        System.out.print(this.user + ": ");
        System.out.println(this.message);
    }

}
