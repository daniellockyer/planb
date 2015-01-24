package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;

public class Player extends Entity {
	private Image image;
	private Input input;
	private double angle = 90;

	private float slowdown = 3.0f;
	public int health = 10;

	public Player(Input input) {
		this.input = input;
		this.position.x = 30;
		this.position.y = 475;

		setSize(32, 32);
	}

	@Override
	public void init(Main main, Level level) throws SlickException {
		super.init(main, level);
		image = new Image("player2.png");
	}

	@Override
	public void update() {
		float dx = 0, dy = 0;

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}

		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) {
			dy--;
		}

		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			dx--;
		}

		if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) {
			dy++;
		}

		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			dx++;
		}

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			level.add(new Bullet(position.copy().add(new Vector2f(16, 48)), angle));
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
					if (!level.getWallIntersect(this).been()) {
						level.getWallIntersect(this).done();
						level.up();
					}
					break;
				case "collision_door_staff":
					if (!level.getWallIntersect(this).been()) {
						level.getWallIntersect(this).done();
						level.up();
					}
					break;
				case "collision_door_vault":
					if (!level.getWallIntersect(this).been()) {
						level.getWallIntersect(this).done();
						level.up();
					}
					break;
				case "collision_door_prevault":
					if (!level.getWallIntersect(this).been()) {
						level.getWallIntersect(this).done();
						level.up();
					}
					break;
				default:
					break;
				}
			}
		}

		if (input.isKeyPressed(Input.KEY_UP)) {
			level.translate(1);
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			level.translate(-1);
		}

		if (input.isKeyPressed(Input.KEY_O)) {
			level.up();
		}
	}

	public void render(int yOffset, Graphics g) {
		g.drawImage(image, getX(), getY());
		g.setColor(Color.green);
		g.drawString(angle + "", (float) (position.x + 5), (float) (position.y + 5));
	}

	@Override
	public void render(Graphics g) {
		switch ((int) angle) {
		case 0:
			break;
		case 1:
			break;
		case 2:
		case 3:
			g.drawImage(image, getX(), getY());
			break;
		case 4:
			break;
		case 5:
		case 6:
			g.drawImage(image.getFlippedCopy(false, true), getX(), getY());
			break;
		case 7:
			break;
		}
		g.setColor(Color.green);
		g.drawString(angle + "", (float) (position.x + 5), (float) (position.y + 5));
	}
}
