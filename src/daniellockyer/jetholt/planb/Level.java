package daniellockyer.jetholt.planb;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import daniellockyer.jetholt.planb.entity.*;

public class Level {
	public static final int TILE_SIZE = 32;
	private TiledMap map;
	private Main main;
	private State layersToDraw = State.OUTSIDE;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	public ArrayList<Entity> entities = new ArrayList<Entity>();

	private final int OUTSIDE, FOYER, OFFICES, PREVAULT, VAULT, FLOOR;

	public Level(Main main, TiledMap map) {
		this.main = main;
		this.map = map;

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
		FOYER = map.getLayerIndex("foyer");
		OFFICES = map.getLayerIndex("offices");
		PREVAULT = map.getLayerIndex("prevault");
		VAULT = map.getLayerIndex("vault");
		FLOOR = map.getLayerIndex("floor");

		for (Entity e : new ConfigFileReader().read()) {
			add(e);
		}

		/*
		 * add(new Civilian(155, 225)); add(new Civilian(355, 225)); add(new Civilian(555, 225));
		 */
	}

	public void update() {
		ArrayList<Entity> toremove = new ArrayList<Entity>();

		for (Entity e : entities) {
			e.update();
			if (e.removed) toremove.add(e);

		}
		entities.removeAll(toremove);
	}

	public void translate(int amount) {
		if (main.yOffset + (12 * amount) < -96 && main.yOffset + (12 * amount) > -372)
			main.yOffset += 12 * amount;
	}

	public void render(Graphics g) {
		map.render(0, main.yOffset, FLOOR);
		map.render(0, main.yOffset, VAULT);
		map.render(0, main.yOffset, PREVAULT);
		map.render(0, main.yOffset, OFFICES);
		map.render(0, main.yOffset, FOYER);

		for (String i : layersToDraw.layers()) {
			map.render(0, main.yOffset, map.getLayerIndex(i));
		}

		for (Entity e : entities)
			e.render(g);

		map.render(0, main.yOffset, OUTSIDE);

		if (true) {
			for (Wall w : walls) {
				g.setColor(Color.green);
				g.drawRect(w.getBoundaries().getX(), w.getBoundaries().getY() + main.yOffset, w
						.getBoundaries().getWidth(), w.getBoundaries().getHeight());
			}
		}

	}

	public void up() {
		if (layersToDraw == State.OUTSIDE) {
			layersToDraw = State.FOYER;
		} else if (layersToDraw == State.FOYER) {
			layersToDraw = State.OFFICES;
		} else if (layersToDraw == State.OFFICES) {
			layersToDraw = State.PREVAULT;
		} else if (layersToDraw == State.PREVAULT) {
			layersToDraw = State.VAULT;
		}
	}

	public void add(Entity e) {
		try {
			e.init(main, this);
			entities.add(e);
		} catch (SlickException e1) {
			e1.printStackTrace();
		}

	}

	public Wall getWallIntersect(Entity e) {
		for (Wall w : walls) {

			Rectangle r1 = new Rectangle(w.getBoundaries().getX(), w.getBoundaries().getY()
					+ main.yOffset, w.getWidth(), w.getHeight());

			Rectangle r2 = new Rectangle(e.getPosition().x, e.getPosition().y + e.getHeight()
					- (e.getHeight() / 6), e.getWidth(), e.getHeight() / 6);

			if (r1.intersects(r2)) return w;
		}
		return null;
	}

	public boolean wall(Entity e, float xa, float ya) {
		for (Wall w : walls) {
			Rectangle r = new Rectangle(w.getBoundaries().getX(), w.getBoundaries().getY()
					+ main.yOffset, w.getWidth(), w.getHeight());

			if (e instanceof Player) {
				if (w.isWalkable()) continue;

				if (r.intersects(new Rectangle(e.getPosition().x + xa, e.getPosition().y
						+ e.getHeight() - (e.getHeight() / 6) + ya, e.getWidth(), e.getHeight() / 6)))
					return true;
			} else if (e instanceof Bullet) {
				if (r.intersects(new Rectangle(e.getPosition().x + xa, e.getPosition().y, e
						.getWidth(), e.getHeight()))) return true;
			}
		}
		return false;
	}
}
