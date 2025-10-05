import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.*;

public class Pokemon {
    private String name;
    private String type;
    private int hp;
    public int maxHp;
    private int attack;
    private int defense;
    private int speed;
    private Move[] moves;
    private Map<String, String> effective;
    
 // Add a new attribute to store the type effectiveness chart as a hashmap
    private static final HashMap<String, HashMap<String, Double>> typeChart = new HashMap<>();

    // Add a static block to initialize the type chart
    static {
        // Create a hashmap for each type
        HashMap<String, Double> normal = new HashMap<>();
        HashMap<String, Double> fire = new HashMap<>();
        HashMap<String, Double> water = new HashMap<>();
        HashMap<String, Double> grass = new HashMap<>();
        HashMap<String, Double> electric = new HashMap<>();

        // Add the type effectiveness values for each type pair
        normal.put("Normal", 1.0);
        normal.put("Fire", 1.0);
        normal.put("Water", 1.0);
        normal.put("Grass", 1.0);
        normal.put("Electric", 1.0);

        fire.put("Normal", 1.0);
        fire.put("Fire", 0.5);
        fire.put("Water", 0.5);
        fire.put("Grass", 2.0);
        fire.put("Electric", 1.0);

        water.put("Normal", 1.0);
        water.put("Fire", 2.0);
        water.put("Water", 0.5);
        water.put("Grass", 0.5);
        water.put("Electric", 1.0);

        grass.put("Normal", 1.0);
        grass.put("Fire", 0.5);
        grass.put("Water", 2.0);
        grass.put("Grass", 0.5);
        grass.put("Electric", 1.0);

        electric.put("Normal", 1.0);
        electric.put("Fire", 1.0);
        electric.put("Water", 2.0);
        electric.put("Grass", 0.5);
        electric.put("Electric", 0.5);

        // Add each hashmap to the type chart
        typeChart.put("Normal", normal);
        typeChart.put("Fire", fire);
        typeChart.put("Water", water);
        typeChart.put("Grass", grass);
        typeChart.put("Electric", electric);
    }

    // Constructor with custom attacks
    public Pokemon(String name, String type, int hp, int attack, int defense, int speed, Move[] moveset) {
        this.name = name;
        this.type = type;
        this.maxHp = hp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        moves = moveset;
        this.effective= new HashMap();
    }
    
    public Pokemon(String name, String type, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.type = type;
        this.maxHp = hp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        
        this.effective= new HashMap();
    }
    
    public void setMove(int slot, Move move) {
		if (slot>=0 && slot < moves.length) {
			this.moves[slot] = move;
		}
		else {
			System.out.println("Invalid move slot!");
		}
	}
    
    public Pokemon(String filepath, int rowNum) {
    	File file = new File(filepath);
    	String line;
    	try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
    		for (int i = 1;i <rowNum; i++) {
				reader.readLine();
			}
    		String[] lineSplit = reader.readLine().split(",");
    		int j=0;
    		for (int i = 6; i <lineSplit.length;i+=4) {
    			moves[j] = new Move(lineSplit[i], lineSplit[i+1], Integer.parseInt(lineSplit[i+2]),Integer.parseInt(lineSplit[i+3]));
    		}
    		this.name = lineSplit[0];
    		this.type = lineSplit[1];
    		this.hp = Integer.parseInt(lineSplit[2]);
    		this.maxHp= Integer.parseInt(lineSplit[2]);
    		this.attack = Integer.parseInt(lineSplit[3]);
    		this.defense = Integer.parseInt(lineSplit[4]);
    		this.speed = Integer.parseInt(lineSplit[5]);
    		
    	}
    	catch (IOException e) {
    		System.out.println("There was an error reading the file!");
    	}
    	
    }

  
    // Getter methods for Pokemon attributes
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }
    
    public int getDefense() {
    	return defense;
    }
    
    public int getSpeed() {
    	return speed;
    }

    public Move getMove(int index) {
        return moves[index];
    }
    
    public void getMoves() {
    	for (int i = 0; i < moves.length; i++) {
    		System.out.println(moves[i].getName());
    	}
    }
    
    public void moveOptions() {
    	System.out.println("Choose an attack for " + getName() + ":");

        // Display the list of attacks for the user to choose from
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ". " + getMove(i).getName());
        }
    }

    
    public double calculateEffectiveness(HashMap<String, HashMap<String, Double>> typeChart, String attackType, String defenseType) {
        return typeChart.getOrDefault(attackType, new HashMap<>()).getOrDefault(defenseType, 1.0);
    }
    
    public boolean miss(Move move) {
    	int acc = move.getAccuracy();
    	Random rand = new Random();
    	if (rand.nextInt(100) > acc) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean isAlive() {
    	if (this.hp>0) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }


    // Perform an attack on the opponent Pokemon
    public void attack(Pokemon opponent, int attackIndex) {
        Move currentAttack = moves[attackIndex - 1];
        if (miss(currentAttack)) {
        	System.out.println(name + " missed the attack!");
        }
        else {
        double multiplier = calculateEffectiveness(typeChart, currentAttack.getType(), opponent.getType());
        if (currentAttack.getType() == this.type) {
        	multiplier = multiplier*1.5;
        }
        double power=(currentAttack.getPower()*this.attack)/50;
        int damage = (int)(power*multiplier-opponent.getDefense());
        if (damage <= 0) {
        	damage = 1;
        }
        System.out.println(name + " uses " + currentAttack.getName() + " on " + opponent.getName() + "!");
        opponent.takeDamage(damage);
        if (multiplier > 1) {
        	System.out.println("It's super effective!");
        }
        if (multiplier < 1) {
        	System.out.println("It's not very effective...");
        }
        System.out.println(opponent.getName() + " takes " + damage + " damage.");
        System.out.println(opponent.getName()+ " has " + opponent.getHp() + " HP left!");
        }
    }

    // Reduce HP based on the damage received
    private void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    // Display Pokemon information
    public void printInfo() {
        System.out.println("Pokemon Information:");
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("HP: " + hp);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Speed: " + speed);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                '}';
    }

    // Setter method for HP
    public void setHp(int hp) {
        this.hp = hp;
    }
    

}
