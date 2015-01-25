package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
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
			ticker = 0;
		} else {
			ticker++;

			if (ticker == 30) {
				drawable = primary;
			}
		}

		if (position.x + dx >= 0 && position.x + dy + width < Main.WIDTH - 1
				&& position.y + dy < Main.HEIGHT) {

			if (!level.wall(this, dx * slowdown, 0)) {
				move(dx * slowdown, 0);
			}

			if (position.y + dy < 200) {
				main.yOffset -= slowdown * dy;
			} else if (!level.wall(this, 0, dy * slowdown)) {
				move(0, dy * slowdown);
			}

			try {
				Wall w = level.getWallIntersect(this);
				if (w.isWalkable()) {
					if (!w.been()) {
						w.done();
						level.up();
					}
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

		if (input.isKeyPressed(Input.KEY_O)) {
			level.up();
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.pink);
		Rectangle r2 = new Rectangle(getPosition().x, getPosition().y + getHeight()
				- (getHeight() / 6), getWidth(), getHeight() / 6);

		g.draw(r2);
	}
}
