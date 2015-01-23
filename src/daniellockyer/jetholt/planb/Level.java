package daniellockyer.jetholt.planb;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {
	private final int TILE_SIZE = 32;
	private int width, height;
	private TiledMap map;
	private int yOffset = -12 * TILE_SIZE;

	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public Level(TiledMap map) {
		this.map = map;
		this.width = map.getWidth();
		this.height = map.getHeight();
	}

	public void update() {
	}

	public void render(Graphics g) {
		map.render(0, yOffset);
	}

	public void add(Entity e) {
		try {
			e.init(this);
			entities.add(e);
		} catch (SlickException e1) {
			e1.printStackTrace();
		}

	}

}
