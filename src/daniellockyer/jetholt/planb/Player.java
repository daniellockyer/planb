package daniellockyer.jetholt.planb;

import org.newdawn.slick.*;

public class Player extends Entity {
	private Image image;
	private Input input;

	public Player(Input input) {
		this.input = input;
	}

	public void init() throws SlickException {
		image = new Image("player.png");
	}

	@Override
	public void update() {
		if (input.isKeyDown(Input.KEY_W)) {
			setY(getY() - 1);
		}

		if (input.isKeyDown(Input.KEY_A)) {
			setX(getX() - 1);
		}

		if (input.isKeyDown(Input.KEY_S)) {
			setY(getY() + 1);
		}

		if (input.isKeyDown(Input.KEY_D)) {
			setX(getX() + 1);
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY());
	}

}
