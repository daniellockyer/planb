
public class Objective implements Comparable<Objective>{
	private int ID;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	private String message;
	private int timesec;
	private boolean iscomplete;
	private boolean isactive;
	
	public Objective(int ID,int xpos,int ypos,int width,int height,String message,int timesec){
		this.ID = ID;
		this.xpos = xpos;
		this.ypos = ypos;
		this.width = width;
		this.height = height;
		this.message = message;
		this.timesec = timesec;
		if(ID == 1){
			isactive = true;
		}
	}

	public boolean isIscomplete() {
		return iscomplete;
	}

	public void setIscomplete(boolean iscomplete) {
		this.iscomplete = iscomplete;
	}

	public int getID() {
		return ID;
	}

	public int getXpos() {
		return xpos;
	}

	public int getYpos() {
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

	public int compareTo(Objective object) {
		int otherID = object.getID();
		if(otherID > ID){
			return -1;
		}
		return 1;
	}

}
