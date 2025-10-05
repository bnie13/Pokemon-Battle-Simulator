import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PokemonGame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trainer trainer1 = new Trainer("Red");
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

					PokemonGame frame = new PokemonGame(trainer1, trainer2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PokemonGame(Trainer user, Trainer foe) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Attack");
		btnNewButton.setBounds(26, 188, 140, 39);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 170, 450, 96);
		contentPane.add(separator);
		
		JButton btnBag = new JButton("Bag");
		btnBag.setBounds(264, 188, 140, 39);
		contentPane.add(btnBag);
		
		JButton btnParty = new JButton("Party");
		btnParty.setBounds(26, 227, 140, 39);
		contentPane.add(btnParty);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(264, 227, 140, 39);
		contentPane.add(btnRun);
		
		JLabel lblNewLabel = new JLabel(user.getActivePokemon().getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(306, 125, 98, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("HP: " + user.getActivePokemon().getHp() + "/" +  user.getActivePokemon().maxHp);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(285, 142, 140, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(foe.getActivePokemon().getName());
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(57, 6, 83, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("HP: " + foe.getActivePokemon().getHp() + "/" +  foe.getActivePokemon().maxHp);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(26, 21, 140, 16);
		contentPane.add(lblNewLabel_1_1);
	}
}
