package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;

import daniellockyer.jetholt.planb.Level;

public class Player extends Entity {
	private Image image;
	private Input input;

	public Player(Input input) {
		this.input = input;
	}

	@Override
	public void init(Level level) throws SlickException {
		super.init(level);
		image = new Image("player.png");
	}

	@Override
	public void update() {

		/* player */

		if (input.isKeyPressed(Input.KEY_W)) {
			move(0, getY() - 1);
		}

		if (input.isKeyPressed(Input.KEY_A)) {
			move(getX() - 1, 0);
		}

		if (input.isKeyPressed(Input.KEY_S)) {
			move(0, getY() + 1);
		}

		if (input.isKeyPressed(Input.KEY_D)) {
			move(getX() + 1, 0);
		}

		/* level */

		if (input.isKeyPressed(Input.KEY_UP)) {
			level.moveOffset(0, 1);
		}

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			level.moveOffset(-1, 0);
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			level.moveOffset(0, -1);
		}

		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			level.moveOffset(1, 0);
		}

		/* layers */

		if (input.isKeyPressed(Input.KEY_O)) {
			level.up();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY());
	}
}
