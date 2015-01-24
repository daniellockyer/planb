package daniellockyer.jetholt.planb;

import java.util.Stack;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.commands.Command;
import daniellockyer.jetholt.planb.entity.Entity;

public class BadGuy extends Entity {
	private String name;
	private Image image;
	private Stack<Command> commands = new Stack<Command>();

	public BadGuy(String name, Stack<Command> commands) {
		this.name = name;
		this.commands = commands;
	}

	@Override
	public void init(Level level) throws SlickException {
		super.init(level);
		image = new Image("badguy.png");
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
				this.position.add(new Vector2f(Float.parseFloat(c.getArguments()[0]), Float
						.parseFloat(c.getArguments()[1])));
			default:
				break;
			}

			if (c.getTime() == 0) commands.pop();
			c.decr();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY());
		g.setColor(Color.green);
		g.drawString(name, (float) (position.x + 5), (float) (position.y + 5));
	}

}
