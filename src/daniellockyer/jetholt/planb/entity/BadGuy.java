package daniellockyer.jetholt.planb.entity;

import java.util.Stack;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.*;

public class BadGuy extends Entity {
	private String name;
	private Stack<Command> commands = new Stack<Command>();
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
			primary = new Image("Neo.png");
			secondary = new Image("Neo2.png");
			break;
		case "jet":
			primary = new Image("Jet.png");
			secondary = new Image("Jet2.png");
			break;
		case "trex":
			primary = new Image("trex2.png");
			secondary = new Image("trex.png");
			break;
		}
		drawable = r.nextBoolean() ? primary : secondary;
	}

	@Override
	public void update() {
		if (commands.size() > 0) {
			Command c = commands.peek();

			switch (c.getCommand()) {
			case "set":
				this.position = new Vector2f(Float.parseFloat(c.getArguments()[0]), Float.parseFloat(c.getArguments()[1]));
				break;
			case "move":
				move(Float.parseFloat(c.getArguments()[0]), Float.parseFloat(c.getArguments()[1]));
				moveCounter++;
			default:
				break;
			}

			if (c.getTime() == 0) {
				if (c.getCommand().equals("move")) {
					drawable = primary;
				}
				commands.pop();
			}
			c.decr();
		}

		if (moveCounter == MAX_MOVE) {
			drawable = (drawable == primary ? secondary : primary);
			moveCounter = 0;
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
			g.drawImage(drawable, getX(), getY() + main.yOffset);
			break;
		case 4:
			break;
		case 5:
		case 6:
			g.drawImage(drawable.getFlippedCopy(true, false), getX(), getY() + main.yOffset);
			break;
		case 7:
			break;
		}
	}
}
