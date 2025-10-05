import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

public class Trainer {
    private String name;
    private List<Pokemon> team;
    private int activePokemonIndex;
    private Map<String, Integer> inventory;

    // Constructor to create a trainer with a name
    public Trainer(String name) {
    	
        this.name = name;
        this.team = new ArrayList<>();
        this.activePokemonIndex = 0;
        this.inventory = new HashMap();
    }

    // Getter method for trainer's name
    public String getName() {
        return name;
    }
    
    public void addItem(String item, int amount) {
    	inventory.put(item, amount);
    }
    public boolean checkItem(String useItem) {
    	if (inventory.containsKey(useItem)) {
    		return true;
    	}
    	else {
    		return false;
    		
    	}
    }
    
    public void getInventory() {
    	for (String i : inventory.keySet()) {
    		System.out.println("Item: " + i + "\t x" + inventory.get(i));
    		}
    }
   
    public void usePotion(Pokemon target, Potion potion) {
    	potion.setPotion();
		target.setHp(target.getHp()+potion.healAmount());
		if (target.getHp()>target.maxHp) {
			target.setHp(target.maxHp);
		}
		
	}
    
    // Add a Pokemon to the trainer's team
    public void addPokemon(Pokemon pokemon) {
        team.add(pokemon);
    }

    // Getter method for the active Pokemon
    public Pokemon getActivePokemon() {
        return team.get(activePokemonIndex);
    }
    
    public int getActiveIndex() {
    	return activePokemonIndex;
    }
    
    public Pokemon getPokemon(int index) {
    	return team.get(index);
    }

    // Switch to a different Pokemon in the team
    public void switchPokemon(int index) {
        if (index >= 0 && index < team.size()) {
        	if (team.get(index).getHp()>0) {
            activePokemonIndex = index;
            System.out.println(name + " switched to " + getActivePokemon().getName() + "!");
        	}
        } else {
            System.out.println("Invalid Choice.");
        }
    }
    
    public boolean useItem(Scanner scanner) {
    	System.out.println("What item would you like to use?");
		getInventory();
		String useItem = scanner.next();
			if (!checkItem(useItem)){
				System.out.println("You don't have that in your bag!");
					scanner.nextLine();
					return false;
				}
			else {			
				if (useItem.contains("Potion")) {
					
					System.out.println("Use " + useItem + " on which Pokemon?\n(Type the party slot of the Pokemon)");
					for (int i = 0; i < getTeam().size(); i++) {
						System.out.println(i+1 + ": " + getTeam().get(i).getName());
					}
					Potion potion = new Potion(useItem);
					int healIndex = scanner.nextInt();
					if (0<= healIndex && healIndex <= getTeam().size()) {
						usePotion(getTeam().get(healIndex-1), potion);
						System.out.println(getTeam().get(healIndex-1).getName()+ " now has " + getTeam().get(healIndex-1).getHp() + " HP!");
						int remains = inventory.get(useItem)-1;
						if (remains == 0) {
							inventory.remove(useItem);
						}
						else {
						inventory.replace(useItem, remains);
						}
    }
				}
				return true;
			}
    }
    
    private static Pokemon speedCheck(Pokemon one, Pokemon two) {
    	Random rand = new Random();
    	Pokemon first = one;
    	if (one.getSpeed() < two.getSpeed()) {
    		first = two;
    	}
    	else if (one.getSpeed()==two.getSpeed()) {
    		if (rand.nextInt(10) < 5) {
    			first = two;
    		}
    	}
    	return first;
    }
    
    public boolean attackPhase(Trainer foe, Scanner scanner) {
    	Pokemon activePokemon = getActivePokemon();
    	Pokemon foePokemon = foe.getActivePokemon();
    	try {
			System.out.println("Which move do you want to use?");
		
		// Display attack options
		activePokemon.moveOptions();
		// Prompt user for attack choice
		int attackChoice = scanner.nextInt();
		Pokemon first = speedCheck(activePokemon, foePokemon);
		if (activePokemon == first) {
			activePokemon.attack(foePokemon, attackChoice);
			if (foePokemon.isAlive()) {
				foePokemon.attack(activePokemon, 1);
			}
			else {
				foe.autoSwitch();
			}
		}
		if (foePokemon == first) {
			foePokemon.attack(activePokemon, 1);
			if (activePokemon.isAlive()) {
				activePokemon.attack(foePokemon, attackChoice);
			}
			else {
				autoSwitch();
			}
		}
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("There is no move in that slot!");
			return false;
		} 
    }
 // Method to switch the active Pokemon for a trainer  
    public boolean switchPhase(Scanner scanner) {
    	if (!partyAlive()) {
    		System.out.println("All other Pokemon unable to battle!");
    		return false;
    	}
    	else {
    		System.out.println("Are you sure you want to switch Pokemon? (1. Yes / 2. No)");
			int choice = scanner.nextInt();
			if (choice == 1) {
				boolean valid = false;
			    while (!valid) {
				System.out.println("Choose a Pokemon to switch to:");
			    List<Pokemon> team = getTeam();

			    // Display the list of Pokemon for the user to choose from
			    for (int i = 0; i < team.size(); i++) {
			        System.out.println((i + 1) + ". " + team.get(i).getName());
			    }

			    // User input for the Pokemon choice to switch to
			    int switchChoice = scanner.nextInt();
			    if (getTeam().get(switchChoice-1).getHp()==0) {
			    	System.out.println(getTeam().get(switchChoice-1).getName() + " is unable to battle!");
			    }
			    	else {
			    		switchPokemon(switchChoice - 1);  // Switch to the selected Pokemon
			    		activePokemonIndex = switchChoice -1;
			    		valid = true;
			    	}
			    }
			}
			return true;
    	}
    }
    
    public boolean partyAlive() {
    	int aliveCount = 0;
		for (Pokemon pokemon : getTeam()) {
			if (pokemon.getHp()>0) {
				aliveCount++;
			}
		} 
		if (aliveCount == 1) {
			return false;
		}
		return true;
    }
    
    public boolean ableToBattle() {
    	for (Pokemon pokemon : getTeam()) {
            if (pokemon.getHp() > 0) {
                return true;  // At least one Pokemon is alive
            	} 
            }
        return false;  // No Pokemon is alive
    }
    
    public void autoSwitch() {
    	if (ableToBattle() && getActivePokemon().getHp()==0) {
    		System.out.println(getActivePokemon().getName() + " has fainted!");
    		int choice = 0;
    			int p = getActiveIndex();
    			boolean stop = false;
    				while (!stop) {
    					if (team.get(p).isAlive()) {
    						choice = p;
    						stop = true;
    						break;
    					}
    					else if (p+1==team.size()) {
    						p = 0;
    					}
    					p++;
    				}
    		switchPokemon(choice);
    		System.out.println(getActivePokemon().getName() + " has entered the battle!");	
    	}
    }

    // Getter method for the trainer's team
    public List<Pokemon> getTeam() {
        return team;
    }
   
}
