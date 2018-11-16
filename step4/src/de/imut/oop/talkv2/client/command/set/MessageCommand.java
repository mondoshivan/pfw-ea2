package de.imut.oop.talkv2.client.command.set;

import de.imut.oop.talkv2.command.RemoteCommand;

public class MessageCommand extends RemoteCommand {

    private String message;

    /**
     * Constructs the message command.
     */
    public MessageCommand(String message) {
        this.message = message;
    }

    /**
     * Prints the message.
     */
    public void execute() {
        System.out.print(this.message);
    }

}
