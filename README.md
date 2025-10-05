Pokémon Battle Simulator

A Java-based Pokémon battle simulator that loads Pokémon data from a CSV file and performs basic battle calculations. The program simulates one-on-one fights between Pokémon based on their stats, type matchups, and simplified damage formulas.

The simulator supports battles between two trainers, each with a party of Pokémon. Players can switch between Pokémon and use items such as potions to heal during battles, introducing basic strategy and turn-based decision-making.

This project also includes a work-in-progress GUI prototype that provides the interface framework (buttons, panels, and layouts), though full functionality and event handling are still being developed.

Features

Loads Pokémon data (name, type, HP, attack, defense, etc.) from a CSV file

Simulates battles between two trainers, each with multiple Pokémon in their party

Allows switching between active Pokémon during battle

Supports using items such as potions to restore HP

Calculates damage using a simplified Pokémon formula

Runs a turn-by-turn battle until all Pokémon on one side faint

Displays results such as remaining HP, item usage, and the winner

Includes an early-stage Graphical User Interface (GUI) layout using Java Swing

Technologies Used

Java (Eclipse)

CSV File I/O for loading Pokémon data

Java Swing for GUI framework (in progress)

Object-Oriented Programming (OOP) principles (classes for Pokémon, Battle logic, Trainers, Items, GUI components)

How to Run

Clone or download this repository.

Open the project in Eclipse or another Java IDE.

Ensure the Pokémon CSV file is in the correct project directory (or update the path in the code).

Run the main class (console version).

The GUI version can be launched from the GUI class file, but functionality is still under development.
