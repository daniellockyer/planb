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

		if (input.isKeyDown(Input.KEY_W)||input.isKeyDown(Input.KEY_UP)) {
			dy--;
		}

		if (input.isKeyDown(Input.KEY_A)||input.isKeyDown(Input.KEY_LEFT)) {
			dx--;
		}

		if (input.isKeyDown(Input.KEY_S)||input.isKeyDown(Input.KEY_DOWN)) {
			dy++;
		}

		if (input.isKeyDown(Input.KEY_D)||input.isKeyDown(Input.KEY_RIGHT)) {
			dx++;
		}

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			level.add(new Bullet(position.copy(), angle));
		}

		if (dy == -1) angle = 0;
		if (dx == 1) angle = 90;
		if (dy == 1) angle = 180;
		if (dx == -1) angle = 270;

		if (position.x + dx >= 0 && position.x + dy + width < Main.WIDTH - 1) {
			if (!level.wall(this, dx * slowdown, 0)) {
				move(dx * slowdown, 0);
			}
			if (!level.wall(this, 0, dy * slowdown)) {
				move(0, dy * slowdown);
			}

			String name = level.wallName(this);

			if (name != null) {
				switch (name) {
				case "collision_door_main":
					level.up();
					break;
				case "collision_door_staff":
					level.up();
					break;
				default:
					break;
				}
			}
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
