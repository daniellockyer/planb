package daniellockyer.jetholt.planb;

public class Objective implements Comparable<Objective> {
	private int id;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private String message;
	private int timesec;
	private boolean iscomplete;
	private boolean isactive;

	public Objective(int id, int xpos, int ypos, int width, int height, String message, int timesec) {
		this.id = id;
		this.xpos = xpos;
		this.ypos = ypos;
		this.width = width;
		this.height = height;
		this.message = message;
		this.timesec = timesec;
		if (id == 1) {
			isactive = true;
		}
	}

	public boolean isActive() {
		return isactive;
	}

	public boolean isIscomplete() {
		return iscomplete;
	}

	public void setIscomplete(boolean iscomplete) {
		this.iscomplete = iscomplete;
	}

	public int getID() {
		return id;
	}

	public int getX() {
		return xpos;
	}

	public int getY() {
		return ypos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getMessage() {
		return message;
	}

	public int getTimesec() {
		return timesec;
	}

	@Override
	public int compareTo(Objective object) {
		return id - object.getID();
	}

}
