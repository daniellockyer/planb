package daniellockyer.jetholt.planb.entity;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

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
			if (level.wall(this, 0, 0)) remove();
			if (collision()) remove();

			move(direction.x * slowdown, direction.y * slowdown);
		}
	}

	private boolean collision() {
		List<Entity> entities = level.getEntities(this, 16);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			float x0 = position.x;
			float y0 = position.y;
			float x1 = e.position.x;
			float y1 = e.position.y;
			if (x0 >= x1 - 4 && x0 <= x1 + e.getWidth() + 4) {
				if (y0 >= y1 - 4 && y0 <= y1 + e.getHeight() + 4) {
					if (!(e instanceof Bullet)) {
						if (e.removed) continue;
						e.remove();
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.drawRoundRect(position.x, position.y, 10, 10, 5);
	}

}
