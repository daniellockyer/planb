package daniellockyer.jetholt.planb.entity;

import org.newdawn.slick.*;

public class Civilian extends Entity {
	private float slowdown = 3.0f;

	public Civilian(float x, float y, int xa, int ya) {
		this.position.x = x;
		this.position.y = y;
		setSize(32, 96);

		try {
			SpriteSheet sheet = new SpriteSheet(new Image("civilians.png"), 32, 96);
			drawable = primary = sheet.getSprite(xa, ya);

			sheet = new SpriteSheet(new Image("civilians_cowering.png"), 46, 32);
			secondary = sheet.getSprite(xa, ya);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		drawable = level.getBadGuys(this) ? secondary : primary;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(drawable, getX(), getY() + main.yOffset);
	}
}
