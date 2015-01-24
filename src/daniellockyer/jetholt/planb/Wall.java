package daniellockyer.jetholt.planb;

import org.newdawn.slick.geom.Rectangle;

public class Wall {
	private Rectangle boundaries;
	private boolean walk = false, been = false;
	private String name;

	public Wall(Rectangle rec) {
		this.boundaries = rec;
	}

	public boolean been() {
		return this.been;
	}

	public void done() {
		been = true;
	}

	public void setWalkable(boolean walk) {
		this.walk = walk;
	}

	public boolean isWalkable() {
		return this.walk;
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

	public void setName(String a) {
		this.name = a;
	}

	public String getName() {
		return name;
	}
}