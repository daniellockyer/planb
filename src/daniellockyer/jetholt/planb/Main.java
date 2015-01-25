package daniellockyer.jetholt.planb;

import java.io.IOException;

import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.ResourceLoader;

import daniellockyer.jetholt.planb.entity.Player;

public class Main extends BasicGame {
	private Audio wavEffect;
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 680;
	public Player player;
	public GUI gui;
	private Level level;
	public int yOffset = -12 * Level.TILE_SIZE;
	public static final boolean DEBUG = true;

	public Main(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		gui = new GUI(this);
		level = new Level(this, new TiledMap("res/bank.tmx"));
		player = new Player(gc.getInput());
		player.init(this, level);

		try {
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/fear.wav"));
			wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
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