package daniellockyer.jetholt.planb;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import daniellockyer.jetholt.planb.entity.Player;

public class Main extends BasicGame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 640;
	public Player player;
	private GUI gui;
	private Level level;
	private int time;
	public int yOffset = -12 * Level.TILE_SIZE;

	public Main(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Input input = gc.getInput();
		level = new Level(this, new TiledMap("res/bank.tmx"));
		gui = new GUI(this);
		gui.setMessage("Hello world! This is a text demo....");
		player = new Player(input);
		player.init(level);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		time++;
		level.update();
		player.update();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// int yScroll = player.getY() - ((Main.HEIGHT - 8) / 2);
		int yScroll = yOffset;

		level.render(yScroll, g);
		gui.render(g);
		player.render(yScroll, g);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc = new AppGameContainer(new Main("PlanB"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			// appgc.setShowFPS(false);
			appgc.setVSync(true);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}
}