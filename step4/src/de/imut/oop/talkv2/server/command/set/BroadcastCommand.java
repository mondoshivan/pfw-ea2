package de.imut.oop.talkv2.server.command.set;

public class BroadcastCommand extends ServerCommand {

    private String message;
    private String user;

    /**
     * Constructs the message command.
     */
    public BroadcastCommand(String user, String message) {
        this.user = user;
        this.message = message;
    }

    /**
     * Prints the message.
     */
    public void execute() {
        System.out.print(this.getUser() + ": ");
        System.out.println(this.getMessage());
    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}