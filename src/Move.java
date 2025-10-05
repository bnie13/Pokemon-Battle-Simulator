import java.io.*;
import java.io.File;

public class Move {
	
private String name;
	
	private int power;
	
	private String type;
	
	private int accuracy;
	
	private Move[] moves;
	
	public Move (String filepath, int rowNum) {
		File file = new File(filepath);
		String line;
		String[] lineSplit = new String[4];
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			for (int i = 1;i <rowNum; i++) {
				reader.readLine();
			}
			line = reader.readLine();
			lineSplit = line.split(",");
			this.name = lineSplit[0];
			this.type = lineSplit[1];
			this.power = Integer.parseInt(lineSplit[2]);
			this.accuracy = Integer.parseInt(lineSplit[3]);

		}
		catch (IOException e) {
			System.out.println("An error occured when trying to open the file!");
		}
	}
	
	public Move(String filepath) {
		File file = new File(filepath);
		String line;
		String[] lineSplit = new String[4];
		int count = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				count++;
			}
			Move[] moves = new Move[count];
			int i = 0;
			while (i < count) {	
				line = reader.readLine();
				lineSplit = line.split(",");
				moves[i] = new Move(lineSplit[0], lineSplit[1], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3]));
				i++;
				}
			this.moves = moves;
			}
			
		catch (IOException e) {
			System.out.println("An error occured when trying to open the file!");
		}
	}

	public Move(String name, String type, int power, int accuracy) {
		this.name=name;
		this.power=power;
		this.type=type;
		this.accuracy=accuracy;
	}
	public String getName() {
		return this.name;
	}

	public int getPower() {
		return this.power;
	}
	
	public int getAccuracy() {
		return this.accuracy;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void getInfo() {
		System.out.println("Move: " + this.name + " Type: " + this.type + " Base Power: " + this.power + " Accuracy: " + this.accuracy);
	}
	
	public static void main(String[] args) {
		String file = "/Users/brucenie/eclipse-workspace/Pokemon Project/src/moves.csv";
		Move input = new Move(file, 3);
		input.getInfo();
	}
	
}
	
