package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;

public class Civilian extends Entity {
	private float slowdown = 3.0f;

	public Civilian(float x, float y) {
		this.position.x = x;
		this.position.y = y;
		setSize(32, 96);
	}

	@Override
	public void init(Main main, Level level) throws SlickException {
		super.init(main, level);

		SpriteSheet sheet = new SpriteSheet(new Image("civilians.png"), 32, 96);
		drawable = primary = sheet.getSprite(r.nextInt(5), 0);
	}

	@Override
	public void update() {
		float dx = 0, dy = 0;

		if (r.nextBoolean()) {
			// dx++;
		}

		if (r.nextBoolean()) {
			// dy++;
		}

		// angle = r.nextInt(5) * 90;

		if (position.x + dx >= 0 && position.x + dy + width < Main.WIDTH - 1) {
			if (!level.wall(this, dx * slowdown, 0)) {
				move(dx * slowdown, 0);
			}
			if (!level.wall(this, 0, dy * slowdown)) {
				move(0, dy * slowdown);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(primary, getX(), getY() + main.yOffset);
	}
}
