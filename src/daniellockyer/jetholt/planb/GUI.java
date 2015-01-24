package daniellockyer.jetholt.planb;

import org.newdawn.slick.*;

public class GUI {
	private Main main;
	private String message;
	private Image heart;

	public GUI(Main main) {
		this.main = main;
		try {
			heart = new Image("heart.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, Main.HEIGHT - 40, Main.WIDTH, 40);
		g.setColor(Color.white);
		g.drawString(message, 10, Main.HEIGHT - 30);

		for (int i = 0; i < main.player.health; i++) {
			g.drawImage(heart, 600 + (32 * i), 605);
		}
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
