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
		path = pathFinder.findPath(null, getX() / Level.TILE_SIZE, getY() % Level.TILE_SIZE, //
				main.player.getX() / Level.TILE_SIZE, main.player.getY() / Level.TILE_SIZE);
		if (path != null) {
			for (int i = 0; i < 1; i++) {

				System.out.println(getX() / Level.TILE_SIZE + " - " + path.getX(i));

				int newX = ((getX() / Level.TILE_SIZE) - path.getX(i));
				if (!level.wall(this, newX, 0)) move(newX, 0);

				int newY = ((getY() % Level.TILE_SIZE) - path.getY(i));
				if (!level.wall(this, 0, newY)) move(0, newY);

				// move(path.getStep(i).getX() - getX(), path.getStep(i).getY() - getY());
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(primary, getX(), getY() + main.yOffset);
	}
}
