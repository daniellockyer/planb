package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;

public class Civilian extends Entity {
	private Image image;
	private double angle = 90;
	private float slowdown = 3.0f;

	public Civilian(float x, float y) {
		this.position.x = x;
		this.position.y = y;

		setSize(32, 32);
	}

	@Override
	public void init(Level level) throws SlickException {
		super.init(level);
		image = new Image("civilian.png");
	}

	@Override
	public void update() {
		float dx = 0, dy = 0;

		if (r.nextBoolean()) {
			dx++;
		}

		if (r.nextBoolean()) {
			dy++;
		}

		angle = r.nextInt(5) * 90;

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
		g.drawImage(image, getX(), getY());
		g.setColor(Color.red);
		g.drawString(angle + "", (float) (position.x + 5), (float) (position.y + 5));
	}
}
