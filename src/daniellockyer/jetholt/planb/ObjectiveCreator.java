import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ObjectiveCreator {

	private ArrayList<Objective> objectiveList = new ArrayList<Objective>();
	
	public static void main(String[] args){
		ObjectiveCreator myObjCreate = new ObjectiveCreator();
		try {
			myObjCreate.generateMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void generateMap() throws IOException{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("bank.tmx")));
			String line;
			while((line = reader.readLine()) != null){
				if(line.equals(" <objectgroup name=\"objective\">")){
					while(!(line = reader.readLine()).equals(" </objectgroup>")){
						if(line.contains("name=\"objective_")){
							int ID = Integer.parseInt(line.split("objective_")[1].split("\"")[0]);
							int xpos = Integer.parseInt(line.split("x=\"")[1].split("\"")[0]);
							int ypos = Integer.parseInt(line.split("y=\"")[1].split("\"")[0]);
							int width = Integer.parseInt(line.split("width=\"")[1].split("\"")[0]);
							int height = Integer.parseInt(line.split("height=\"")[1].split("\"")[0]);
							reader.readLine();
							line = reader.readLine();
							String message = line.split("&quot;")[1];
							line = reader.readLine();
							int time = Integer.parseInt(line.split("value=\"")[1].split("\"")[0]);
							objectiveList.add(new Objective(ID,xpos,ypos,width,height,message,time));
							Collections.sort(objectiveList);
						}
					}
					break;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
