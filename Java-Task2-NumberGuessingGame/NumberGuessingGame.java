import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    int targetNumber;
    int maxRange = 100;
    int maxAttempts = 7;
    int attemptsUsed = 0;
    int roundCount = 1;
    int totalWins = 0;

    Random random = new Random();

    JComboBox<String> difficultyBox = new JComboBox<>(new String[]{
        "Easy (1 - 50, 10 attempts)",
        "Medium (1 - 100, 7 attempts)",
        "Hard (1 - 200, 5 attempts)"
    });

    JLabel scoreLabel = new JLabel("Round: 1 | Wins: 0 | Attempts left: 7", JLabel.CENTER);
    JLabel statusLabel = new JLabel("Choose difficulty and enter your guess!", JLabel.CENTER);
    JTextField guessInput = new JTextField(10);
    JButton guessButton = new JButton("Guess");
    JButton playAgainButton = new JButton("Play Again");
    JTextArea summaryArea = new JTextArea(5, 25);

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(440, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        difficultyBox.setSelectedIndex(1);

        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        topPanel.add(scoreLabel);
        topPanel.add(difficultyBox);
        topPanel.add(statusLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Enter your guess:"));
        centerPanel.add(guessInput);
        centerPanel.add(guessButton);
        centerPanel.add(playAgainButton);
        add(centerPanel, BorderLayout.CENTER);

        summaryArea.setEditable(false);
        add(new JScrollPane(summaryArea), BorderLayout.SOUTH);

        playAgainButton.setEnabled(false);

        guessButton.addActionListener(e -> processGuess());
        guessInput.addActionListener(e -> processGuess());
        playAgainButton.addActionListener(e -> startNewRound());
        difficultyBox.addActionListener(e -> startNewRound());

        startNewRound();
    }

    void startNewRound() {
        int choice = difficultyBox.getSelectedIndex();
        if (choice == 0) {
            maxRange = 50;
            maxAttempts = 10;
        } else if (choice == 2) {
            maxRange = 200;
            maxAttempts = 5;
        } else {
            maxRange = 100;
            maxAttempts = 7;
        }

        targetNumber = random.nextInt(maxRange) + 1;
        attemptsUsed = 0;

        guessInput.setText("");
        guessInput.setEnabled(true);
        guessButton.setEnabled(true);
        playAgainButton.setEnabled(false);

        statusLabel.setText("Guess the number between 1 and " + maxRange);
        scoreLabel.setText("Round: " + roundCount + " | Wins: " + totalWins + " | Attempts left: " + maxAttempts);
    }

    void processGuess() {
        int guess;
        try {
            guess = Integer.parseInt(guessInput.getText().trim());
        } catch (NumberFormatException e) {
            statusLabel.setText("Please enter a valid whole number!");
            return;
        }

        if (guess < 1 || guess > maxRange) {
            statusLabel.setText("Guess must be between 1 and " + maxRange + "!");
            return;
        }

        attemptsUsed++;

        if (guess == targetNumber) {
            totalWins++;
            statusLabel.setText("Correct!");
            summaryArea.append("Round " + roundCount + " — guessed in " + attemptsUsed + " attempts\n");
            endRound();
        } else if (attemptsUsed >= maxAttempts) {
            statusLabel.setText("You Lost! The correct number was: " + targetNumber);
            summaryArea.append("Round " + roundCount + " — lost after " + maxAttempts + " attempts\n");
            endRound();
        } else if (guess > targetNumber) {
            statusLabel.setText("Too High!");
        } else {
            statusLabel.setText("Too Low!");
        }

        scoreLabel.setText("Round: " + roundCount + " | Wins: " + totalWins + " | Attempts left: " + (maxAttempts - attemptsUsed));
        guessInput.setText("");
    }

    void endRound() {
        guessInput.setEnabled(false);
        guessButton.setEnabled(false);
        playAgainButton.setEnabled(true);
        roundCount++;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame().setVisible(true));
    }
}
