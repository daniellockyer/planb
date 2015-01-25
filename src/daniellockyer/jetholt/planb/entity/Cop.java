package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;

public class Cop extends Entity {

	public Cop(float x, float y) {
		this.position.x = x;
		this.position.y = y;
		setSize(48, 96);
	}

	@Override
	public void init(Main main, Level level) throws SlickException {
		super.init(main, level);

		drawable = primary = new Image("cop1.png");
		secondary = new Image("cop2.png");
	}

	@Override
	public void update() {
		int dx = 0, dy = 0;

		if (r.nextInt(20) == 0) {
			dx -= 5;
		}

		if (!level.wall(this, dx, 0)) {
			if (position.x + dx > 640) {
				move(dx, 0);
			}
		}
		if (!level.wall(this, 0, dy)) {
			move(0, dy);
		}

		for (Entity e : level.entities) {
			if (e instanceof Player) {
				double bulletAngle = Math.toDegrees(Math.atan((this.position.copy().getY() - e.getPosition().copy().y) / (this.position.copy().getX() - e.getPosition().copy().x)));
				level.addTemp(new Bullet(position.copy(), bulletAngle));
				break;
			} else if (e instanceof BadGuy) {
				Vector2f directionVector = this.getPosition().copy().sub(e.position);
				double bulletAngle = Math.toDegrees(Math.atan(directionVector.y / directionVector.x));
				break;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(primary, getX(), getY() + main.yOffset);
	}
}
