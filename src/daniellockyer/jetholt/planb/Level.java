package daniellockyer.jetholt.planb;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import daniellockyer.jetholt.planb.entity.Bullet;
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

	private final int FOREGROUND = 5, WALL_OUTSIDE = 4, WALL_FOYER = 3, WALL_OFFICES = 2,
			WALL_PREVAULT = 1, FLOOR = 0;

	public Level(TiledMap map) {
		this.map = map;
		this.width = map.getWidth();
		this.height = map.getHeight();

		layersToDraw.add(WALL_OUTSIDE);
		layersToDraw.add(FOREGROUND);
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
			if (entities.get(i).removed) entities.remove(i);
		}
	}

	public void moveOffset(int x, int y) {
		xOffset += x * TILE_SIZE;
		yOffset += y * TILE_SIZE;
	}

	public void render(Graphics g) {
		map.render(xOffset, yOffset, FLOOR);

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

	public boolean wall(Vector2f pos, Entity e, float xa, float ya) {
		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			float x0 = w.getPosition().x;
			float y0 = w.getPosition().y;
			float x1 = pos.x + xa;
			float y1 = pos.y + ya;
			if (x1 + e.getWidth() >= x0 && x1 <= x0 + w.getWidth()) {
				if (y1 <= y0 + w.getHeight() - 8 && y1 + e.getHeight() >= y0 - 12) { return true; }
			}
		}
		return false;
	}

	public List<Entity> getEntities(Vector2f from, Entity ignore, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e == ignore) continue;
			double dx = e.getPosition().x - from.x;
			double dy = e.getPosition().y - from.y;
			if (Math.sqrt(dx * dx + dy * dy) > radius) continue;
			result.add(e);
		}
		return result;
	}

	public List<Entity> getEntities(Entity from, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e == from) continue;
			double dx = e.getPosition().x - from.getPosition().x;
			double dy = e.getPosition().y - from.getPosition().y;
			if (Math.sqrt(dx * dx + dy * dy) > radius) continue;
			result.add(e);
		}
		return result;
	}

	public boolean wall(Entity e, int xa, int ya) {
		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			float x0 = w.getPosition().x;
			float y0 = w.getPosition().y;
			float x1 = e.getPosition().x + xa;
			float y1 = e.getPosition().y + ya;
			int xOffset0 = 0;
			int xOffset1 = 0;
			int yOffset0 = 0;
			int yOffset1 = 0;
			if (e instanceof Bullet) {
				xOffset0 = 0;
				xOffset1 = -8;
				yOffset0 = 8;
			}
			if (x1 + e.getWidth() + xOffset1 >= x0 && x1 <= x0 + w.getWidth() + xOffset0) {
				if (y1 <= y0 + w.getHeight() + yOffset1 && y1 + e.getHeight() >= y0 + yOffset0) { return true; }
			}
		}
		return false;
	}

	public void up() {
		if (layersToDraw.contains(WALL_OUTSIDE)) {
			layersToDraw.remove((Object) WALL_OUTSIDE);
			layersToDraw.add(WALL_FOYER);
		} else if (layersToDraw.contains(WALL_PREVAULT)) {
			return;
		} else if (layersToDraw.contains(WALL_OFFICES)) {
			layersToDraw.remove((Object) WALL_OFFICES);
			layersToDraw.add(WALL_PREVAULT);
		} else if (layersToDraw.contains(WALL_FOYER)) {
			layersToDraw.remove((Object) WALL_FOYER);
			layersToDraw.add(WALL_OFFICES);
		}
	}
}
