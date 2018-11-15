package de.imut.oop.talkv2.common;

// enum tutorial:
// http://tutorials.jenkov.com/java/enums.html
public enum SystemExitCode {

    ABORT(1),
    NORMAL(0);

    private final int exitCode;

    SystemExitCode(int value) {
        this.exitCode = value;
    }

    public int getCode() {
        return exitCode;
    }
}
