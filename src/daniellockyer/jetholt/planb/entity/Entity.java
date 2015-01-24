package daniellockyer.jetholt.planb.entity;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.Level;

public abstract class Entity {
	protected Level level;
	protected Vector2f position;
	protected float width, height;
	public boolean removed = false;
	protected Random r = new Random();

	protected Entity() {
		position = new Vector2f(0, 0);
	}

	public void init(Level level) throws SlickException {
		this.level = level;
	}

	public abstract void update();

	public abstract void render(Graphics g);

	protected void setSize(float i, float j) {
		width = i;
		height = j;
	}

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

	public void move(float xa, float ya) {
		position.x += xa;
		position.y += ya;
	}

	public void remove() {
		removed = true;
	}

	public int getX() {
		return (int) position.x;
	}

	public int getY() {
		return (int) position.y;
	}

}
