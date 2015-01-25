package daniellockyer.jetholt.planb;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import daniellockyer.jetholt.planb.entity.Player;

public class Main extends BasicGame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 680;
	public Player player;
	private GUI gui;
	private Level level;
	public int yOffset = -12 * Level.TILE_SIZE;

	public Main(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		level = new Level(this, new TiledMap("res/bank.tmx"));
		gui = new GUI(this);
		gui.setMessage("Neo: Hello world! This is a text demo....");
		player = new Player(gc.getInput());
		player.init(this, level);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		level.update();
		player.update();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		level.render(g);
		gui.render(g);
		player.render(g);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc = new AppGameContainer(new Main("PlanB"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setVSync(true);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}
}