package daniellockyer.jetholt.planb;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

import daniellockyer.jetholt.planb.entity.Player;

public class Main extends BasicGame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 640;
	private Player player;
	private Level level;
	private int time;

	public Main(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Input input = gc.getInput();
		level = new Level(new TiledMap("res/bank.tmx"));

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
		level.render(g);
		player.render(g);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc = new AppGameContainer(new Main("PlanB"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			// appgc.setShowFPS(false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}
}