package daniellockyer.jetholt.planb;

import org.newdawn.slick.*;

public class GUI {
	private Main main;
	private String message;
	private Image heart, damaged_heart, face;

	public GUI(Main main) {
		this.main = main;
		try {
			damaged_heart = new Image("heart_damaged.png");
			heart = new Image("heart.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, Main.HEIGHT - 40, Main.WIDTH, 40);

		for (int i = 0; i < 10; i++) {
			g.drawImage((main.player.health > i ? heart : damaged_heart), 5 + (32 * i), 643);
		}

		if (face != null) {
			g.setColor(Color.black);
			g.drawRect(Main.WIDTH - 650, Main.HEIGHT - 38, 36, 36);
			g.drawImage(face, Main.WIDTH - 648, Main.HEIGHT - 36);
		}

		g.setColor(Color.white);
		g.drawString(message, Main.WIDTH - 500, Main.HEIGHT - 30);
	}

	public void face(Image image) {
		this.face = image;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
