package de.imut.oop.talkv2.server.command.set;

import de.imut.oop.talkv2.command.RemoteCommand;

public class BroadcastCommand extends RemoteCommand {

    private String message;

    /**
     * Constructs the broadcast command.
     */
    public BroadcastCommand(String message) {
        this.message = message;
    }

    public void execute() {
        System.out.print(this.message);
    }
}
