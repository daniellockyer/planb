package daniellockyer.jetholt.planb;

public class Command {
	private String command;
	private String[] arguments;
	private int time;

	public Command(int time, String command, String[] arguments) {
		this.time = time;
		this.command = command;
		this.arguments = arguments;
	}

	public String getCommand() {
		return command;
	}

	public String[] getArguments() {
		return arguments;
	}

	public int getTime() {
		return time;
	}

	public void decr() {
		time--;
	}

}
