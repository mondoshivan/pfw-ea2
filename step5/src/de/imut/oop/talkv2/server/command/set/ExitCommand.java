package de.imut.oop.talkv2.server.command.set;
import de.imut.oop.talkv2.common.SystemExitCode;

public class ExitCommand extends ServerCommand {

	private static final long serialVersionUID = 1L;
	private SystemExitCode code;
	
	public ExitCommand(SystemExitCode code) {
		this.code = code;
	}

	/**
	 * Executes stored exit command,
	 * by executing an explicit System.exit().
	 */
	@Override
	public void execute() {
		switch(code) {
			case NORMAL:
				System.exit(SystemExitCode.NORMAL.getCode());
			default: 
				System.exit(SystemExitCode.ABORT.getCode());
		}
	}
}
