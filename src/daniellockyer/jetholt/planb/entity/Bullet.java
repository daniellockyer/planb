package daniellockyer.jetholt.planb.entity;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.Main;

public class Bullet extends Entity {
	private Vector2f direction;
	private float slowdown = 0.75f;

	public Bullet(Vector2f position, double angle) {
		this.position = position;
		angle = Math.toRadians(angle - 90);
		this.direction = new Vector2f((float) Math.cos(angle), (float) Math.sin(angle));
	}

	@Override
	public void update() {
		for (int i = 0; i < 12; i++) {
			if (position.x > Main.WIDTH || position.x < 0 || position.y > Main.HEIGHT
					|| position.y < 0) remove();
			if (level.wall(this, 0, 0)) remove();
			if (collision()) remove();
			move(direction.x * slowdown, direction.y * slowdown);
		}
	}

	private boolean collision() {
		List<Entity> entities = level.getEntities();

		for (Entity e : entities) {
			if (!(e instanceof Bullet)) {

				if (new Rectangle(position.x, position.y, width, height).intersects(new Rectangle(
						e.position.x, e.position.y + main.yOffset, e.width, e.height))) {
					if (e.removed) continue;
					e.remove();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.drawRoundRect(position.x, position.y, 15, 5, 5);
	}

}
