import java.util.Scanner;
import java.util.List;
import java.util.Random;
/**
 * Author: Charles greene, Bruce Nie
 * This program simulates a Pokemon battle between two trainers and their two Pokemon.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
     
        System.out.println("Hello trainer! What is your name?");
        String name = scanner.next();
        // Create trainers
        Trainer trainer1 = new Trainer(name);
        Trainer trainer2 = new Trainer("Gary");
        
        // Add Pokemon to trainers
        // Preconditions: Trainer and Pokemon classes are defined.
        // Postconditions: Trainers are created with specified Pokemon teams.
        
        Move hydroPump = new Move("Hydro Pump", "Water", 110, 80);
        Move surf = new Move("Surf", "Water", 90, 100);
        Move returns = new Move("Return", "Normal", 100, 100);
        Move iceBeam = new Move("Ice Beam", "Ice", 90, 100);
        Move[]blastoiseMove= {hydroPump, surf, returns, iceBeam};
        Pokemon blastoise = new Pokemon("Blastoise", "Water", 150, 65, 70, 60, blastoiseMove);
        Move fireBlast = new Move("Fire Blast", "Fire", 110, 85);
        Move flamethrower= new Move("Flamethrower", "Fire", 90, 100);
        Move slash = new Move("Slash", "Normal", 80, 100);
        Move dragonPulse = new Move("Dragon Pulse", "Dragon", 85, 100);
        Move[]charizardMove = {fireBlast, flamethrower, slash, dragonPulse};
        Pokemon charizard = new Pokemon("Charizard", "Fire", 130, 90, 45, 90, charizardMove);
        Move leafStorm = new Move("Leaf Storm", "Grass", 130, 85);
        Move energyBall = new Move("Energy Ball", "Grass", 90, 100);
        Move sludgeBomb = new Move("Sludge Bomb", "Poison", 100, 100);
        Move[]venusaurMove = {leafStorm, energyBall, sludgeBomb, returns};
        Pokemon venusaur = new Pokemon("Venusaur", "Grass", 180, 55, 90, 50, venusaurMove);
        Move waterfall = new Move("Waterfall", "Water", 80, 100);
        Move[]gyaradosMove = {hydroPump, waterfall, iceBeam, dragonPulse};
        Pokemon gyarados = new Pokemon("Gyarados", "Water", 110, 100, 40, 80, gyaradosMove);
        
        
        trainer1.addPokemon(blastoise);

        trainer1.addPokemon(charizard);

        trainer2.addPokemon(venusaur);

        trainer2.addPokemon(gyarados);
        trainer1.addItem("Potion", 5);
        trainer1.addItem("SuperPotion", 5);
        trainer2.addItem("Potion", 5);
        
        // Start the battle
        // Preconditions: Trainer and Pokemon classes are defined.
        // Postconditions: The battle is initiated between trainer1 and trainer2.
        newBattle(trainer1, trainer2, scanner);
        
        scanner.close();
    }

// Method to check if any Pokemon in a trainer's team is alive
private static boolean anyPokemonAlive(Trainer trainer) {
    for (Pokemon pokemon : trainer.getTeam()) {
        if (pokemon.getHp() > 0) {
            return true;  // At least one Pokemon is alive
        	} 
        }
    return false;  // No Pokemon is alive
}


// Method to prompt the user for the decision to end the game
private static boolean endGameDecision(Scanner scanner) {
    System.out.println("Do you want to end the game? (1. Yes / 2. No)");

    // User input for the decision to end the game
    int choice = scanner.nextInt();
    return choice == 1;  // Return true if the user chooses to end the game
}


    private static void restartGame(Trainer trainer1, Trainer trainer2) {
        for (Pokemon pokemon : trainer1.getTeam()) {
            pokemon.setHp(pokemon.maxHp);  // Reset HP for each Pokemon
        }
        for (Pokemon pokemon : trainer2.getTeam()) {
            pokemon.setHp(pokemon.maxHp);  // Reset HP for each Pokemon
        }
    }
    
    private static void newBattle(Trainer user, Trainer foe, Scanner scanner) {
    	while (user.ableToBattle() && foe.ableToBattle()) {
    		if (!user.getActivePokemon().isAlive()) {
				user.autoSwitch();
			}
    		boolean turnOver=false;
    		while (!turnOver) {
    			scanner.nextLine();
    			Pokemon activePokemon = user.getActivePokemon();
    			Pokemon foePokemon = foe.getActivePokemon();
    			System.out.println("\n" + user.getName() + "'s turn!\nCurrent Pokemon: ");
    			activePokemon.printInfo();
    			System.out.println("What would you like to do?\n1: Attack / 2: Use an item / 3: Switch Pokemon");
    			int action = scanner.nextInt();
    			if (action == 1) {
    				turnOver = user.attackPhase(foe, scanner);
    			}
    			else if (action == 2) {
    				turnOver = user.useItem(scanner);
    				if (turnOver) {
    					foePokemon.attack(activePokemon, 1);
    				}
    			}
    			else if (action == 3) {
    				turnOver = user.switchPhase(scanner);
    				if (turnOver) {
    					activePokemon = user.getActivePokemon();
    					foePokemon.attack(activePokemon, 1);
    				}
    			}
    		}
    	 if (!anyPokemonAlive(foe)) {
             System.out.println(foe.getName() + " has been defeated!");
         	if (endGameDecision(scanner)) {
                 System.out.println("Better catch 'em all! Goodbye!!");
                 break;
             } else {
                 restartGame(user, foe);
                 System.out.println("Restarting the game!");
             	}
         	}
    	 if (!anyPokemonAlive(user)) {
             System.out.println(user.getName() + " has been defeated!");
             if (endGameDecision(scanner)) {
                 System.out.println("Smell ya later!");
                 break;
             } else {
                 restartGame(user, foe);
                 System.out.println("Restarting the game!");
             }
         }
    	}
    }
}
    

