import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PokemonGUI extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField typeField;
	private JTextField healthField;
	private JTextField attackField;
	private JTextField defenseField;
	private JTextField speedField;
	private Pokemon pokemon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokemonGUI frame = new PokemonGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void createPokemon() {
		int health = Integer.parseInt(healthField.getText());
		int attack = Integer.parseInt(attackField.getText());
		int defense = Integer.parseInt(defenseField.getText());
		int speed = Integer.parseInt(speedField.getText());
		Pokemon pokemon = new Pokemon(nameField.getText(), typeField.getText(), health, attack, defense, speed);
		this.pokemon = pokemon;
		

	}
	
	/**
	 * Create the frame.
	 */
	public PokemonGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Create Pokemon");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(6, 293, 438, 94);
		contentPane.add(btnNewButton);
		
		nameField = new JTextField();
		nameField.setBounds(134, 37, 310, 52);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		typeField = new JTextField();
		typeField.setColumns(10);
		typeField.setBounds(134, 77, 310, 52);
		contentPane.add(typeField);
		
		healthField = new JTextField();
		healthField.setColumns(10);
		healthField.setBounds(134, 115, 310, 52);
		contentPane.add(healthField);
		
		attackField = new JTextField();
		attackField.setColumns(10);
		attackField.setBounds(134, 158, 310, 52);
		contentPane.add(attackField);
		
		defenseField = new JTextField();
		defenseField.setColumns(10);
		defenseField.setBounds(134, 200, 310, 52);
		contentPane.add(defenseField);
		
		speedField = new JTextField();
		speedField.setColumns(10);
		speedField.setBounds(134, 240, 310, 52);
		contentPane.add(speedField);
		
		JLabel nameLbl = new JLabel("Name:");
		nameLbl.setBounds(16, 55, 61, 16);
		contentPane.add(nameLbl);
		
		JLabel typeLbl = new JLabel("Type:");
		typeLbl.setBounds(16, 95, 61, 16);
		contentPane.add(typeLbl);
		
		JLabel healthLbl = new JLabel("Health:");
		healthLbl.setBounds(16, 133, 61, 16);
		contentPane.add(healthLbl);
		
		JLabel attackLbl = new JLabel("Attack:");
		attackLbl.setBounds(16, 176, 61, 16);
		contentPane.add(attackLbl);
		
		JLabel defenseLbl = new JLabel("Defense:");
		defenseLbl.setBounds(16, 218, 61, 16);
		contentPane.add(defenseLbl);
		
		JLabel speedLbl = new JLabel("Speed:");
		speedLbl.setBounds(16, 258, 61, 16);
		contentPane.add(speedLbl);
		
		JLabel titleLbl = new JLabel("Create a Pokemon!");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(16, 9, 428, 16);
		contentPane.add(titleLbl);
	}
}
