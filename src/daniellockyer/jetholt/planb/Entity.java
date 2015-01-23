package daniellockyer.jetholt.planb;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity {
	private Level level;
	protected Vector2f position;
	protected float width, height;
	public boolean removed = false;

	protected Entity() {
		position = new Vector2f(0, 0);
	}

	public abstract void update();

	public void init(Level level) throws SlickException {
		this.level = level;
	}

	public abstract void render(Graphics g);

	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void remove() {
		removed = true;
	}

	public int getX() {
		return (int) position.x;
	}

	public void move(float xa, float ya) {
		position.x += xa;
		position.y += ya;
	}

	public int getY() {
		return (int) position.y;
	}

}
