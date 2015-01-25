package daniellockyer.jetholt.planb.entity;

import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

import daniellockyer.jetholt.planb.Level;
import daniellockyer.jetholt.planb.Main;

public abstract class Entity {
	protected Level level;
	protected Vector2f position;
	protected float width, height;
	public boolean removed = false;
	protected int direction;
	protected Image drawable;
	public int health = 10;
	protected Image primary, secondary;
	protected Main main;
	protected final int MAX_MOVE = 10;
	protected int moveCounter = 0;
	protected Random r = new Random();

	protected Entity() {
		position = new Vector2f(0, 0);
	}

	public void init(Main main, Level level) throws SlickException {
		this.main = main;
		this.level = level;
	}

	public abstract void update();

	public void render(Graphics g) {
		switch (direction) {
		case 0:
		case 1:
		case 2:
		case 3:
			g.drawImage(drawable, getX(), getY());
			break;
		case 4:
		case 5:
		case 6:
		case 7:
			g.drawImage(drawable.getFlippedCopy(true, false), getX(), getY());
			break;
		}
	}

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

		if (xa == 0 && ya < 0) direction = 0;
		else if (xa > 0 && ya < 0) direction = 1;
		else if (xa > 0 && ya == 0) direction = 2;
		else if (xa > 0 && ya > 0) direction = 3;
		else if (xa == 0 && ya > 0) direction = 4;
		else if (xa < 0 && ya > 0) direction = 5;
		else if (xa < 0 && ya == 0) direction = 6;
		else if (xa < 0 && ya < 0) direction = 7;
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
