package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;

public class Cop extends Entity {
	private Path path;
	private AStarPathFinder pathFinder;

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
		pathFinder = new AStarPathFinder(level, 1000, true);
	}

	@Override
	public void update() {
		int dx = 0, dy = 0;

		if (r.nextInt(20) == 0) {
			dx--;
		}

		if (!level.wall(this, dx, 0)) {
			move(dx, 0);
		}
		if (!level.wall(this, 0, dy)) {
			move(0, dy);
		}

		// move(path.getStep(i).getX() - getX(), path.getStep(i).getY() - getY());
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(primary, getX(), getY() + main.yOffset);
	}
}
