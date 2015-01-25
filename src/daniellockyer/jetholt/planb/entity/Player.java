package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.*;

public class Player extends Entity {
	private Input input;
	private double angle = 90;
	private float slowdown = 3.0f;
	private int ticker;

	public Player(Input input) {
		this.input = input;
		this.position.x = 30;
		this.position.y = 475;

		setSize(48, 96);
	}

	@Override
	public void init(Main main, Level level) throws SlickException {
		super.init(main, level);
		drawable = primary = new Image("player2.png");
		secondary = new Image("player.png");
	}

	@Override
	public void update() {
		float dx = 0, dy = 0;

		if (input.isKeyPressed(Input.KEY_ESCAPE)) System.exit(0);

		if (input.isKeyDown(Input.KEY_W)) dy--;

		if (input.isKeyDown(Input.KEY_A)) dx--;

		if (input.isKeyDown(Input.KEY_S)) dy++;

		if (input.isKeyDown(Input.KEY_D)) dx++;

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			level.add(new Bullet(position.copy().add(new Vector2f(24, 48)), angle));
		}

		if (dy == -1) angle = 0;
		if (dx == 1) angle = 90;
		if (dy == 1) angle = 180;
		if (dx == -1) angle = 270;

		if (dx != 0 || dy != 0) {
			moveCounter++;
			ticker = 0;
		} else {
			ticker++;

			if (ticker == 30) {
				drawable = primary;
			}
		}

		if (position.x + dx >= 0 && position.x + dy + width < Main.WIDTH - 1 && position.y + height + 40 + dy < Main.HEIGHT) {

			if (!level.wall(this, dx * slowdown, 0)) {
				move(dx * slowdown, 0);
			}

			if (position.y + dy < 220 && main.yOffset < -98) {
				main.yOffset -= slowdown * dy;
			} else if (dy > 0 && position.y + dy > 400 && main.yOffset > -384) {
				main.yOffset -= slowdown * dy;
			} else if (!level.wall(this, 0, dy * slowdown)) {
				move(0, dy * slowdown);
			}

			try {
				Wall w = level.getWallIntersect(this);
				if (w.isWalkable() && !w.been()) {
					w.done();
					level.up();
				}
			} catch (NullPointerException e) {
			}
		}

		if (moveCounter == MAX_MOVE) {
			drawable = (drawable == primary ? secondary : primary);
			moveCounter = 0;
		}

		if (input.isKeyPressed(Input.KEY_UP)) {
			level.translate(-1);
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			level.translate(1);
		}
	}
}
