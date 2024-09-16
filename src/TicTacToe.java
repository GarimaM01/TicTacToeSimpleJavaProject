import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class TicTacToe implements ActionListener {
    // Declare GUI components
    JFrame frame = new JFrame(); // Main window
    JPanel t_panel = new JPanel(); // Panel for the title and status text
    JPanel bt_panel = new JPanel(); // Panel for the game board
    JLabel textfield = new JLabel(); // Label to display game status
    JButton[] bton = new JButton[9]; // Array of buttons for the game board
    int chance_flag = 0; // Counter to keep track of the number of moves
    Random random = new Random(); // Random number generator to decide first player
    boolean pl1_chance; // Boolean to track if it's player X's turn

    // Constructor to set up the GUI
    TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        frame.setSize(500, 500); // Set the size of the frame
        frame.getContentPane().setBackground(new Color(250, 184, 97)); // Set background color
        frame.setTitle("Tic Tac Toe Game"); // Set the title of the window
        frame.setLayout(new BorderLayout()); // Use BorderLayout for the frame
        frame.setVisible(true); // Make the frame visible

        // Set up the text label for game status
        textfield.setForeground(new Color(255, 77, 77)); // Set text color
        textfield.setFont(new Font("Serif", Font.BOLD, 32)); // Set font size and style
        textfield.setHorizontalAlignment(JLabel.CENTER); // Center align text
        textfield.setText("Tic Tac Toe Game"); // Set initial text
        textfield.setOpaque(true); // Make background opaque

        // Set up the panel for the text label
        t_panel.setLayout(new BorderLayout()); // Use BorderLayout for the panel
        t_panel.setBounds(0, 0, 900, 150); // Set panel bounds (though not used here)

        // Set up the panel for the game buttons
        bt_panel.setLayout(new GridLayout(3, 3)); // Use GridLayout to arrange buttons in a 3x3 grid
        bt_panel.setBackground(new Color(0, 0, 0)); // Set background color to black

        // Initialize buttons and add them to the panel
        for (int i = 0; i < 9; i++) {
            bton[i] = new JButton(); // Create a new button
            bt_panel.add(bton[i]); // Add button to the grid panel
            bton[i].setFont(new Font("Serif", Font.BOLD, 120)); // Set font size for button text
            bton[i].setFocusable(false); // Disable focus on button
            bton[i].addActionListener(this); // Add action listener for button clicks
            bton[i].setBackground(new Color(220, 206, 163)); // Set background color of button
        }

        // Add the text label to the text panel
        t_panel.add(textfield);
        frame.add(t_panel, BorderLayout.NORTH); // Add text panel to the top of the frame
        frame.add(bt_panel); // Add grid panel to the center of the frame

        startGame(); // Start the game
    }

    // Method to start the game and decide the first player's turn
    public void startGame() {
        try {
            textfield.setText("Let's Begin The Game..."); // Show start message
            Thread.sleep(2000); // Wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Print stack trace if an exception occurs
        }
        int chance = random.nextInt(100); // Generate a random number

        if (chance % 2 == 0) {
            pl1_chance = true; // Set Player X's turn
            textfield.setText("Player X turn"); // Update status text
        } else {
            pl1_chance = false; // Set Player O's turn
            textfield.setText("Player O turn"); // Update status text
        }
    }

    // Method to handle the end of the game
    public void gameOver(String s) {
        chance_flag = 0; // Reset move counter
        Object[] option = {"New Game", "Exit"}; // Options for the game over dialog
        int n = JOptionPane.showOptionDialog(frame, "Game Over\n" + s, "Game Over", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        if (n == 0) {
            frame.dispose(); // Close the current frame
            new TicTacToe(); // Start a new game
        } else {
            frame.dispose(); // Close the frame if "Exit" is selected
        }
    }

    // Method to check for winning conditions
    public void matchCheck() {
        // Check all possible winning combinations for Player X
        if ((bton[0].getText().equals("X") && bton[1].getText().equals("X") && bton[2].getText().equals("X")) ||
                (bton[0].getText().equals("X") && bton[4].getText().equals("X") && bton[8].getText().equals("X")) ||
                (bton[0].getText().equals("X") && bton[3].getText().equals("X") && bton[6].getText().equals("X")) ||
                (bton[1].getText().equals("X") && bton[4].getText().equals("X") && bton[7].getText().equals("X")) ||
                (bton[2].getText().equals("X") && bton[4].getText().equals("X") && bton[6].getText().equals("X")) ||
                (bton[2].getText().equals("X") && bton[5].getText().equals("X") && bton[8].getText().equals("X")) ||
                (bton[3].getText().equals("X") && bton[4].getText().equals("X") && bton[5].getText().equals("X")) ||
                (bton[6].getText().equals("X") && bton[7].getText().equals("X") && bton[8].getText().equals("X"))) {
            xWins(); // Call xWins method if Player X wins
        }
        // Check all possible winning combinations for Player O
        else if ((bton[0].getText().equals("O") && bton[1].getText().equals("O") && bton[2].getText().equals("O")) ||
                (bton[0].getText().equals("O") && bton[3].getText().equals("O") && bton[6].getText().equals("O")) ||
                (bton[0].getText().equals("O") && bton[4].getText().equals("O") && bton[8].getText().equals("O")) ||
                (bton[1].getText().equals("O") && bton[4].getText().equals("O") && bton[7].getText().equals("O")) ||
                (bton[2].getText().equals("O") && bton[4].getText().equals("O") && bton[6].getText().equals("O")) ||
                (bton[2].getText().equals("O") && bton[5].getText().equals("O") && bton[8].getText().equals("O")) ||
                (bton[3].getText().equals("O") && bton[4].getText().equals("O") && bton[5].getText().equals("O")) ||
                (bton[6].getText().equals("O") && bton[7].getText().equals("O") && bton[8].getText().equals("O"))) {
            oWins(); // Call oWins method if Player O wins
        }
        // Check for a tie (draw)
        else if (chance_flag == 9) {
            textfield.setText("Game Draw!!"); // Update status text
            gameOver("It's a Tie!!"); // Call gameOver method for a draw
        }
    }

    // Method to handle the winning condition for Player X
    public void xWins() {
        for (int i = 0; i < 9; i++) {
            bton[i].setBackground(Color.YELLOW); // Highlight winning buttons
        }
        for (int i = 0; i < 9; i++) {
            bton[i].setEnabled(false); // Disable all buttons
        }
        textfield.setText("Congratulations Player X wins"); // Update status text
        gameOver("Congratulations Player X Wins"); // Call gameOver method
    }

    // Method to handle the winning condition for Player O
    public void oWins() {
        for (int i = 0; i < 9; i++) {
            bton[i].setBackground(Color.YELLOW); // Highlight winning buttons
        }
        for (int i = 0; i < 9; i++) {
            bton[i].setEnabled(false); // Disable all buttons
        }
        textfield.setText("Congratulations Player O Wins"); // Update status text
        gameOver("Congratulations Player O Wins"); // Call gameOver method
    }

    // Method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == bton[i]) { // Check which button was clicked
                if (pl1_chance) { // If it's Player X's turn
                    if (bton[i].getText().equals("")) { // Check if button is empty
                        bton[i].setForeground(new Color(0, 188, 255)); // Set text color for X
                        bton[i].setText("X"); // Set button text to X
                        pl1_chance = false; // Switch turn to Player O
                        textfield.setText("O turn"); // Update status text
                        chance_flag++; // Increment move counter
                        matchCheck(); // Check for game conditions
                    }
                } else { // If it's Player O's turn
                    if (bton[i].getText().equals("")) { // Check if button is empty
                        bton[i].setForeground(new Color(0, 255, 9)); // Set text color for O
                        bton[i].setText("O"); // Set button text to O
                        pl1_chance = true; // Switch turn to Player X
                        textfield.setText("X turn"); // Update status text
                        chance_flag++; // Increment move counter
                        matchCheck(); // Check for game conditions
                    }
                }
            }
        }
    }
}
