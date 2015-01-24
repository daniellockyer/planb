package daniellockyer.jetholt.planb;

import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import daniellockyer.jetholt.planb.entity.Entity;

public class Level {
	private final int TILE_SIZE = 32;
	private int width, height;
	private TiledMap map;
	private int yOffset = -12 * TILE_SIZE;
	private ArrayList<Integer> layersToDraw = new ArrayList<Integer>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	private final int OUTSIDE, WALL_OUTSIDE, FOYER, WALL_FOYER, OFFICES, WALL_OFFICES, PREVAULT,
			WALL_PREVAULT, FLOOR;

	public Level(TiledMap map) {
		this.map = map;
		this.width = map.getWidth();
		this.height = map.getHeight();

		int objectGroupCount = map.getObjectGroupCount();
		for (int gi = 0; gi < objectGroupCount; gi++) {
			int objectCount = map.getObjectCount(gi);
			for (int oi = 0; oi < objectCount; oi++) {
				int x = map.getObjectX(gi, oi);
				int y = map.getObjectY(gi, oi);
				int width = map.getObjectWidth(gi, oi);
				int height = map.getObjectHeight(gi, oi);
				String a = map.getObjectName(gi, oi);

				Wall w = new Wall(new Rectangle(x, y, width, height));
				if (!a.equals("")) {
					w.setName(a);
					w.setWalkable(true);
				}

				walls.add(w);
			}
		}

		OUTSIDE = map.getLayerIndex("outside");
		WALL_OUTSIDE = map.getLayerIndex("wall_outside");
		FOYER = map.getLayerIndex("foyer");
		WALL_FOYER = map.getLayerIndex("wall_foyer");
		OFFICES = map.getLayerIndex("offices");
		WALL_OFFICES = map.getLayerIndex("wall_offices");
		PREVAULT = map.getLayerIndex("prevault");
		WALL_PREVAULT = map.getLayerIndex("wall_prevault");
		FLOOR = map.getLayerIndex("floor");

		layersToDraw.add(WALL_OUTSIDE);
	}

	public void update() {
		ArrayList<Entity> toremove = new ArrayList<Entity>();

		for (Entity e : entities) {
			e.update();
			if (e.removed) toremove.add(e);

		}
		entities.removeAll(toremove);
	}

	public void moveOffset(int y) {
		yOffset += y * TILE_SIZE;
	}

	public void render(Graphics g) {
		map.render(0, yOffset, FLOOR);
		map.render(0, yOffset, PREVAULT);
		map.render(0, yOffset, OFFICES);
		map.render(0, yOffset, FOYER);

		for (int i : layersToDraw) {
			map.render(0, yOffset, i);
		}

		map.render(0, yOffset, OUTSIDE);

		for (Wall w : walls) {
			g.setColor(Color.green);
			g.drawRect(w.getBoundaries().getX(), w.getBoundaries().getY() + yOffset, w
					.getBoundaries().getWidth(), w.getBoundaries().getHeight());
		}

		for (Entity e : entities) {
			e.render(g);
		}
	}

	public void up() {
		for (int i : layersToDraw) {
			System.out.println("Before: " + i);
		}

		if (layersToDraw.contains(WALL_PREVAULT)) {
			return;
		} else if (layersToDraw.contains(WALL_OFFICES) && !layersToDraw.contains(WALL_PREVAULT)) {
			layersToDraw.remove((Object) WALL_OFFICES);
			layersToDraw.add(WALL_PREVAULT);
		} else if (layersToDraw.contains(WALL_FOYER) && !layersToDraw.contains(WALL_OFFICES)) {
			layersToDraw.remove((Object) WALL_FOYER);
			layersToDraw.add(WALL_OFFICES);
		} else if (layersToDraw.contains(WALL_OUTSIDE) && !layersToDraw.contains(WALL_FOYER)) {
			layersToDraw.remove((Object) WALL_OUTSIDE);
			layersToDraw.add(WALL_FOYER);
		}
		Collections.sort(layersToDraw);

		for (int i : layersToDraw) {
			System.out.println("After: " + i);
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
			float x0 = w.getBoundaries().getX();
			float y0 = w.getBoundaries().getY() + yOffset;
			float x1 = pos.x + xa;
			float y1 = pos.y + ya;
			if (x1 + e.getWidth() >= x0 && x1 <= x0 + w.getWidth()) {
				if (y1 <= y0 + w.getHeight() && y1 + e.getHeight() >= y0) { return true; }
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

	public String wallName(Entity e) {
		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			float x0 = w.getBoundaries().getX();
			float y0 = w.getBoundaries().getY() + yOffset;
			float x1 = e.getPosition().x;
			float y1 = e.getPosition().y;
			if (x1 + e.getWidth() >= x0 && x1 <= x0 + w.getWidth() && y1 <= y0 + w.getHeight()
					&& y1 + e.getHeight() >= y0) { return w.getName(); }
		}
		return "";
	}

	public boolean wall(Entity e, float xa, float ya) {
		for (int i = 0; i < walls.size(); i++) {
			Wall w = walls.get(i);
			float x0 = w.getBoundaries().getX();
			float y0 = w.getBoundaries().getY() + yOffset;
			float x1 = e.getPosition().x + xa;
			float y1 = e.getPosition().y + ya;
			if (w.isWalkable()) return false;
			if (x1 + e.getWidth() >= x0 && x1 <= x0 + w.getWidth() && y1 <= y0 + w.getHeight()
					&& y1 + e.getHeight() >= y0) { return true; }
		}
		return false;
	}

}
