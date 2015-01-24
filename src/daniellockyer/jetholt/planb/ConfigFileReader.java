package daniellockyer.jetholt.planb;

import java.io.*;
import java.util.*;

import daniellockyer.jetholt.planb.commands.Command;
import daniellockyer.jetholt.planb.entity.BadGuy;

public class ConfigFileReader {
	public ArrayList<BadGuy> read() {
		try {
			ArrayList<BadGuy> temp = new ArrayList<BadGuy>();
			BufferedReader reader = new BufferedReader(new FileReader(new File("res/move.txt")));

			String line = "";

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("[")) {
					String name = line.split("\\[")[1].split("\\]")[0];
					String com;
					Stack<Command> commands = new Stack<>();

					while ((com = reader.readLine()) != null) {
						if (com.length() == 0) break;
						String[] parts = com.split(" ");

						int time = Integer.parseInt(parts[0]);

						Command c = new Command(time, parts[1], Arrays.copyOfRange(parts, 2,
								parts.length));
						commands.add(c);
					}

					Stack<Command> reversedStack = new Stack<Command>();
					while (!commands.empty()) {
						reversedStack.push(commands.pop());
					}
					temp.add(new BadGuy(name, reversedStack));
				}
			}

			reader.close();

			return temp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
