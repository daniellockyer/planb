package daniellockyer.jetholt.planb;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

public class Level {
	private TiledMap map;

	public Level(TiledMap map) {
		this.map = map;
	}

	public void update() {
	}

	public void render(Graphics g) {
		map.render(0, 0);
	}

}
