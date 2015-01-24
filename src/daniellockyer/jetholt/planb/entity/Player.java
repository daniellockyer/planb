package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;

public class Player extends Entity {
	private Image image, secondary;
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
		drawable = image = new Image("player2.png");
		secondary = new Image("player.png");
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

		if (dx != 0 || dy != 0) {
			moveCounter++;
		}

		if (position.x + dx >= 0 && position.x + dy + width < Main.WIDTH - 1
				&& position.y + dy < Main.HEIGHT) {

			if (!level.wall(this, dx * slowdown, 0)) {
				move(dx * slowdown, 0);
			}

			if (position.y + dy < 200) {
				main.yOffset -= slowdown * dy;
			} else {
				if (!level.wall(this, 0, dy * slowdown)) {
					move(0, dy * slowdown);
				}
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

		if (moveCounter == MAX_MOVE) {
			drawable = (drawable == image ? secondary : image);
			moveCounter = 0;
		}

		if (input.isKeyPressed(Input.KEY_UP)) {
			level.translate(-1);
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			level.translate(1);
		}

		if (input.isKeyPressed(Input.KEY_O)) {
			level.up();
		}
	}
}
