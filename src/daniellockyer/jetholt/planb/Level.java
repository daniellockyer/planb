package daniellockyer.jetholt.planb;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import daniellockyer.jetholt.planb.entity.Entity;

public class Level {
	private final int TILE_SIZE = 32;
	private int width, height;
	private TiledMap map;
	private int xOffset = 0 * TILE_SIZE;
	private int yOffset = -12 * TILE_SIZE;
	private ArrayList<Integer> layersToDraw = new ArrayList<Integer>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public Level(TiledMap map) {
		this.map = map;
		this.width = map.getWidth();
		this.height = map.getHeight();

		layersToDraw.add(4);
	}

	public void update() {
	}

	public void moveOffset(int x, int y) {
		xOffset += x * TILE_SIZE;
		yOffset += y * TILE_SIZE;
	}

	public void render(Graphics g) {
		map.render(xOffset, yOffset, 0);
		for (int i : layersToDraw) {
			map.render(xOffset, yOffset, i);
		}

		for (Entity e : entities) {
			e.render(g);
		}
	}

	public void add(Entity e) {
		try {
			e.init(this);
			entities.add(e);
		} catch (SlickException e1) {
			e1.printStackTrace();
		}

	}

	public void up() {
		if (layersToDraw.contains(4)) {
			layersToDraw.remove((Object) 4);
			layersToDraw.add(3);
		} else if (layersToDraw.contains(2)) {
			layersToDraw.remove((Object) 2);
			layersToDraw.add(1);
		} else if (layersToDraw.contains(3)) {
			layersToDraw.add(2);
		}

		for (int i : layersToDraw) {
			// System.out.println(map.getTileSet(i).name);
		}
		System.out.println();
	}
}
