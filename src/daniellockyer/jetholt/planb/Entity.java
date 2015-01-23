package daniellockyer.jetholt.planb;

import org.newdawn.slick.Graphics;

public abstract class Entity {
	private int x, y;

	public abstract void update();

	public abstract void render(Graphics g);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
