package de.imut.oop.talkv2.command;

import java.io.Serializable;

public abstract class RemoteCommand implements Serializable {

    /**
     * Executes the stored command.
     * Concrete implementations of commands have to be provided in sub-classes.
     */
    public abstract void execute();

}
