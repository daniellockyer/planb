package daniellockyer.jetholt.planb.commands;

public class Command {
	private String command;
	private String[] arguments;

	public Command(String command, String... arguments) {
		this.command = command;
		this.arguments = arguments;
	}
}
