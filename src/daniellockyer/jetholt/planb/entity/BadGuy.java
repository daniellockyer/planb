package daniellockyer.jetholt.planb.entity;

import java.util.Stack;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.*;

public class BadGuy extends Entity {
	private String name;
	private Image face;
	private Stack<Command> commands = new Stack<Command>();
	private int direction = 2;
	public boolean isJet = false;

	public BadGuy(String name, Stack<Command> commands) {
		super();
		this.name = name;
		this.commands = commands;
		setSize(48, 96);
	}

	@Override
	public void remove() {
		if (deadImage == null) {
			super.remove();
		} else {
			dead = true;
		}
	}

	@Override
	public void init(Main main, Level level) throws SlickException {
		super.init(main, level);

		switch (name) {
		case "neo":
			primary = new Image("Neo.png");
			face = new Image("NeoHead.png");
			secondary = new Image("Neo2.png");
			deadImage = new Image("Neo_dead.png");
			break;
		case "jet":
			primary = new Image("Jet.png");
			secondary = new Image("Jet2.png");
			face = new Image("JetHead.png");
			deadImage = new Image("Jet_dead.png");
			isJet = true;
			break;
		case "twrecks":
			primary = new Image("trex2.png");
			deadImage = new Image("trex_dead.png");
			face = new Image("TrexHead.png");
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
			case "delay":
				break;
			case "set":
				this.position = new Vector2f(Float.parseFloat(c.getArguments()[0]), Float.parseFloat(c.getArguments()[1]));
				break;
			case "move":
				move(Float.parseFloat(c.getArguments()[0]), Float.parseFloat(c.getArguments()[1]));
				moveCounter++;
			case "say":
				this.main.gui.face(face);
				this.main.gui.setMessage(c.getArguments()[0].replace("_", " "));
			default:
				break;
			}

			if (c.getTime() < 0) {
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
		if (dead && deadImage != null) {
			g.drawImage(deadImage, getX(), getY() + main.yOffset + getHeight());
			return;
		}

		switch (direction) {
		case 0:
		case 1:
		case 2:
		case 3:
			g.drawImage(drawable, getX(), getY() + main.yOffset);
			break;
		case 4:
		case 5:
		case 6:
		case 7:
			g.drawImage(drawable.getFlippedCopy(true, false), getX(), getY() + main.yOffset);
			break;
		}
	}
}
