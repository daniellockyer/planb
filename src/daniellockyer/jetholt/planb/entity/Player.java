package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;

public class Player extends Entity {
	private Image image;
	private Input input;
	private double angle = 90;
	private float slowdown = 3.0f;

	public Player(Input input) {
		this.input = input;
		this.position.x = 30;
		this.position.y = 450;

		setSize(32, 32);
	}

	@Override
	public void init(Level level) throws SlickException {
		super.init(level);
		image = new Image("player.png");
	}

	@Override
	public void update() {
		float dx = 0, dy = 0;

		/* player */

		if (input.isKeyDown(Input.KEY_W)) {
			angle = 0;
			dy--;
		}

		if (input.isKeyDown(Input.KEY_A)) {
			angle = 270;
			dx--;
		}

		if (input.isKeyDown(Input.KEY_S)) {
			angle = 180;
			dy++;
		}

		if (input.isKeyDown(Input.KEY_D)) {
			angle = 90;
			dx++;
		}

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			level.add(new Bullet(position.copy(), angle));
		}

		if (position.x + dx > 0 && position.x + dy + width < Main.WIDTH - 1) {
			if (level.wall(this, dx * slowdown, 0)) {
				move(dx * slowdown, 0);
			}
			if (level.wall(this, 0, dy * slowdown)) {
				move(0, dy * slowdown);
			}
		}

		/* level */

		if (input.isKeyPressed(Input.KEY_UP)) {
			level.moveOffset(1);
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			level.moveOffset(-1);
		}

		if (input.isKeyPressed(Input.KEY_O)) {
			level.up();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, getX(), getY());
		g.setColor(Color.green);
		g.drawString(angle + "", (float) (position.x + 5), (float) (position.y + 5));
	}
}
