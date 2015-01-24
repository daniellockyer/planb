package daniellockyer.jetholt.planb;

import java.util.Stack;

import org.newdawn.slick.Graphics;

import daniellockyer.jetholt.planb.commands.Command;
import daniellockyer.jetholt.planb.entity.Entity;

public class BadGuy extends Entity {
	private Stack<Command> commands = new Stack<Command>();
	
	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
	}

}
