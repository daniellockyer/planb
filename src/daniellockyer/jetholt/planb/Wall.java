package daniellockyer.jetholt.planb;

import org.newdawn.slick.geom.Rectangle;

public class Wall {
	private Rectangle boundaries;

	public Wall(Rectangle rec) {
		this.boundaries = rec;
	}

	public float getWidth() {
		return boundaries.getWidth();
	}

	public float getHeight() {
		return boundaries.getHeight();
	}

	public Rectangle getBoundaries() {
		return boundaries;
	}
}