package daniellockyer.jetholt.planb;

import org.newdawn.slick.*;

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
		if (input.isKeyDown(Input.KEY_W)) {
			move(0, getY() - 1);
		}

		if (input.isKeyDown(Input.KEY_A)) {
			move(getX() - 1, 0);
		}

		if (input.isKeyDown(Input.KEY_S)) {
			move(0, getY() + 1);
		}

		if (input.isKeyDown(Input.KEY_D)) {
			move(getX() + 1, 0);
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY());
	}
}
