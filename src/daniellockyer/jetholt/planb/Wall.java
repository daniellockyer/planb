package daniellockyer.jetholt.planb;

import org.newdawn.slick.geom.Vector2f;

public class Wall {
	private int width, height;
	private Vector2f position;

	public Wall(Vector2f position) {
		this.position = position;
		width = 32;
		height = 32;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Vector2f getPosition() {
		return position;
	}
}