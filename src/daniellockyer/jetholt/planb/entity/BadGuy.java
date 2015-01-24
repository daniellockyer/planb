package daniellockyer.jetholt.planb.entity;

import java.util.Stack;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;
import daniellockyer.jetholt.planb.commands.Command;

public class BadGuy extends Entity {
	private String name;
	private Stack<Command> commands = new Stack<Command>();
	private Image stop, walk;
	private int direction = 2;

	public BadGuy(String name, Stack<Command> commands) {
		super();
		this.name = name;
		this.commands = commands;
		setSize(48, 96);
	}

	@Override
	public void init(Main main, Level level) throws SlickException {
		super.init(main, level);

		switch (name) {
		case "neo":
			stop = new Image("Neo.png");
			walk = new Image("Neo2.png");
			break;
		case "jet":
			stop = new Image("Jet.png");
			walk = new Image("Jet2.png");
			break;
		case "trex":
			stop = new Image("trex2.png");
			walk = new Image("trex.png");
			break;
		}

		//
	}

	@Override
	public void update() {
		if (commands.size() > 0) {
			Command c = commands.peek();

			switch (c.getCommand()) {
			case "set":
				this.position = new Vector2f(Float.parseFloat(c.getArguments()[0]),
						Float.parseFloat(c.getArguments()[1]));
				break;
			case "move":
				move(Float.parseFloat(c.getArguments()[0]), Float.parseFloat(c.getArguments()[1]));
			default:
				break;
			}

			if (c.getTime() == 0) commands.pop();
			c.decr();
		}
	}

	@Override
	public void render(Graphics g) {
		switch (direction) {
		case 0:
			break;
		case 1:
			break;
		case 2:
		case 3:
			g.drawImage(stop, getX(), getY() + main.yOffset);
			break;
		case 4:
			break;
		case 5:
		case 6:
			g.drawImage(stop.getFlippedCopy(false, true), getX(), getY() + main.yOffset);
			break;
		case 7:
			break;
		}
		// g.drawImage(stop, getX(), getY());
	}
}
