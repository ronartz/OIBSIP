import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    public int targetNumber;
    public int maxRange = 100;
    public int maxAttempts = 7;
    public int attemptsUsed = 0;
    public int roundCount = 1;
    public int totalWins = 0;
    public int totalLosses = 0;
    public List<String> roundHistory = new ArrayList<>();

    Random random = new Random();

    public JComboBox<String> difficultyBox = new JComboBox<>(new String[]{
        "Easy (1 - 50, 10 attempts)",
        "Medium (1 - 100, 7 attempts)",
        "Hard (1 - 200, 5 attempts)"
    });

    public JLabel scoreLabel = new JLabel("Round: 1 | Total Wins: 0 | Attempts left: 7", JLabel.CENTER);
    public JLabel statusLabel = new JLabel("Choose difficulty and enter your guess!", JLabel.CENTER);
    public JTextField guessInput = new JTextField(10);
    public JButton guessButton = new JButton("Guess");
    public JButton playAgainButton = new JButton("Play Again");
    public JTextArea roundHistoryArea = new JTextArea(5, 25);
    public JTextArea summaryArea = roundHistoryArea;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(440, 400);
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

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        JLabel historyTitle = new JLabel("Round History:");
        roundHistoryArea.setEditable(false);
        roundHistoryArea.setName("roundHistory");
        bottomPanel.add(historyTitle, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(roundHistoryArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        playAgainButton.setEnabled(false);

        guessButton.addActionListener(e -> processGuess());
        guessInput.addActionListener(e -> processGuess());
        playAgainButton.addActionListener(e -> {
            roundCount++;
            startNewRound();
        });
        difficultyBox.addActionListener(e -> {
            if (attemptsUsed == 0 && guessButton.isEnabled()) {
                startNewRound();
            }
        });

        startNewRound();
    }

    public void startNewRound() {
        difficultyBox.setEnabled(true);
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
        scoreLabel.setText("Round: " + roundCount + " | Total Wins: " + totalWins + " | Attempts left: " + maxAttempts);
    }

    public void processGuess() {
        int guess;
        try {
            guess = Integer.parseInt(guessInput.getText().trim());
        } catch (NumberFormatException e) {
            statusLabel.setText("Please enter a valid whole number!");
            return;
        }
        processGuess(guess);
    }

    public String processGuess(int guess) {
        if (!guessButton.isEnabled() && attemptsUsed > 0) {
            return "Round is over";
        }

        if (guess < 1 || guess > maxRange) {
            statusLabel.setText("Guess must be between 1 and " + maxRange + "!");
            return "Out of range";
        }

        if (attemptsUsed == 0) {
            difficultyBox.setEnabled(false);
        }

        attemptsUsed++;

        String result;
        if (guess == targetNumber) {
            totalWins++;
            result = "Correct!";
            statusLabel.setText(result);
            String summary = "Round " + roundCount + " — guessed in " + attemptsUsed + " attempts";
            roundHistory.add(summary);
            roundHistoryArea.append(summary + "\n");
            roundHistoryArea.setCaretPosition(roundHistoryArea.getDocument().getLength());
            endRound();
        } else if (attemptsUsed >= maxAttempts) {
            totalLosses++;
            result = "You Lost! The correct number was: " + targetNumber;
            statusLabel.setText(result);
            String summary = "Round " + roundCount + " — lost after " + maxAttempts + " attempts";
            roundHistory.add(summary);
            roundHistoryArea.append(summary + "\n");
            roundHistoryArea.setCaretPosition(roundHistoryArea.getDocument().getLength());
            endRound();
        } else if (guess > targetNumber) {
            result = "Too High!";
            statusLabel.setText(result);
        } else {
            result = "Too Low!";
            statusLabel.setText(result);
        }

        scoreLabel.setText("Round: " + roundCount + " | Total Wins: " + totalWins + " | Attempts left: " + (maxAttempts - attemptsUsed));
        guessInput.setText("");
        return result;
    }

    public void endRound() {
        guessInput.setEnabled(false);
        guessButton.setEnabled(false);
        playAgainButton.setEnabled(true);
        difficultyBox.setEnabled(true);
    }

    public void resetSession() {
        roundCount = 1;
        totalWins = 0;
        totalLosses = 0;
        roundHistory.clear();
        roundHistoryArea.setText("");
        difficultyBox.setSelectedIndex(1);
        startNewRound();
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public void setTotalLosses(int totalLosses) {
        this.totalLosses = totalLosses;
    }

    public List<String> getRoundHistory() {
        return new ArrayList<>(roundHistory);
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public int getSecretNumber() {
        return targetNumber;
    }

    public void setSecretNumber(int secretNumber) {
        this.targetNumber = secretNumber;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public int getRoundNumber() {
        return roundCount;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundCount = roundNumber;
    }

    public int getAttemptsUsed() {
        return attemptsUsed;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getUpperLimit() {
        return maxRange;
    }

    public JTextArea getRoundHistoryArea() {
        return roundHistoryArea;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame().setVisible(true));
    }
}
