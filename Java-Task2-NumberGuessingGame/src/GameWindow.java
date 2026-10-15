import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public final class GameWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final Color BG_DARK = new Color(15, 23, 42);
    private static final Color CARD_BG = new Color(30, 41, 59);
    private static final Color FIELD_BG = new Color(51, 65, 85);
    private static final Color BORDER_COLOR = new Color(71, 85, 105);
    private static final Color TEXT_PRIMARY = new Color(248, 250, 252);
    private static final Color TEXT_MUTED = new Color(148, 163, 184);
    private static final Color ACCENT_TEAL = new Color(45, 212, 191);
    private static final Color ERROR_RED = new Color(248, 113, 113);

    private final transient GuessingGame game;

    private JComboBox<String> difficultyComboBox;
    private JLabel rangeLabel;
    private JLabel roundHeaderLabel;
    private JTextField guessInputField;
    private JButton guessButton;
    private JLabel feedbackMessageLabel;
    private JLabel attemptsCounterLabel;
    private JProgressBar attemptsProgressBar;
    private JButton playAgainButton;

    private JLabel scoreSummaryLabel;
    private JTextArea roundHistoryArea;
    private JButton resetScoresButton;

    public GameWindow() {
        super("OIBSIP | Number Guessing Game");
        this.game = new GuessingGame();

        initializeWindow();
        buildUserInterface();
        updateDisplayForNewRound();
    }

    private void initializeWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 600);
        setMinimumSize(new Dimension(800, 560));
        setLocationRelativeTo(null);
    }

    private void buildUserInterface() {
        JPanel mainContainer = new JPanel(new BorderLayout(16, 16));
        mainContainer.setBackground(BG_DARK);
        mainContainer.setBorder(new EmptyBorder(20, 24, 20, 24));

        JPanel headerPanel = createHeaderPanel();
        mainContainer.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 18, 0));
        contentPanel.setOpaque(false);

        JPanel gamePanel = createGamePanel();
        JPanel scorePanel = createScoreboardPanel();

        contentPanel.add(gamePanel);
        contentPanel.add(scorePanel);
        mainContainer.add(contentPanel, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("OIBSIP Java Development Internship · Task 02", SwingConstants.CENTER);
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footerLabel.setForeground(TEXT_MUTED);
        mainContainer.add(footerLabel, BorderLayout.SOUTH);

        setContentPane(mainContainer);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel titleLabel = new JLabel("Number Guessing Game");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(TEXT_PRIMARY);

        JLabel subtitleLabel = new JLabel("Java Development — Task 2 | Oasis Infobyte");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(ACCENT_TEAL);

        JPanel titleBlock = new JPanel();
        titleBlock.setOpaque(false);
        titleBlock.setLayout(new BoxLayout(titleBlock, BoxLayout.Y_AXIS));
        titleBlock.add(titleLabel);
        titleBlock.add(Box.createVerticalStrut(4));
        titleBlock.add(subtitleLabel);

        header.add(titleBlock, BorderLayout.WEST);
        return header;
    }

    private JPanel createGamePanel() {
        JPanel card = createCardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        roundHeaderLabel = new JLabel("ROUND 1  ·  MEDIUM");
        roundHeaderLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        roundHeaderLabel.setForeground(ACCENT_TEAL);
        card.add(roundHeaderLabel);
        card.add(Box.createVerticalStrut(10));

        JLabel diffPromptLabel = new JLabel("Difficulty (applies to next round):");
        diffPromptLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        diffPromptLabel.setForeground(TEXT_MUTED);
        card.add(diffPromptLabel);
        card.add(Box.createVerticalStrut(6));

        difficultyComboBox = new JComboBox<>(new String[] {
            "Medium (1–100, 7 attempts)",
            "Easy (1–50, 10 attempts)",
            "Hard (1–200, 5 attempts)"
        });
        styleComboBox(difficultyComboBox);
        card.add(difficultyComboBox);
        card.add(Box.createVerticalStrut(16));

        rangeLabel = new JLabel("Guess a secret number between 1 and 100:");
        rangeLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        rangeLabel.setForeground(TEXT_PRIMARY);
        card.add(rangeLabel);
        card.add(Box.createVerticalStrut(10));

        JPanel inputRow = new JPanel(new BorderLayout(8, 0));
        inputRow.setOpaque(false);
        inputRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));

        guessInputField = new JTextField();
        styleTextField(guessInputField);
        guessInputField.addActionListener(e -> handleGuessSubmission());
        inputRow.add(guessInputField, BorderLayout.CENTER);

        guessButton = new JButton("Guess");
        styleButton(guessButton, ACCENT_TEAL, BG_DARK);
        guessButton.setPreferredSize(new Dimension(100, 44));
        guessButton.addActionListener(e -> handleGuessSubmission());
        inputRow.add(guessButton, BorderLayout.EAST);
        card.add(inputRow);
        card.add(Box.createVerticalStrut(14));

        feedbackMessageLabel = new JLabel("Enter a number to start guessing.");
        feedbackMessageLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        feedbackMessageLabel.setForeground(TEXT_MUTED);
        card.add(feedbackMessageLabel);
        card.add(Box.createVerticalStrut(14));

        attemptsCounterLabel = new JLabel("Attempts: 0 / 7");
        attemptsCounterLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        attemptsCounterLabel.setForeground(TEXT_PRIMARY);
        card.add(attemptsCounterLabel);
        card.add(Box.createVerticalStrut(6));

        attemptsProgressBar = new JProgressBar();
        attemptsProgressBar.setForeground(ACCENT_TEAL);
        attemptsProgressBar.setBackground(FIELD_BG);
        attemptsProgressBar.setBorderPainted(false);
        attemptsProgressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        attemptsProgressBar.setPreferredSize(new Dimension(200, 10));
        card.add(attemptsProgressBar);
        card.add(Box.createVerticalStrut(20));

        playAgainButton = new JButton("Play Again  →");
        styleButton(playAgainButton, ACCENT_TEAL, BG_DARK);
        playAgainButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        playAgainButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        playAgainButton.setVisible(false);
        playAgainButton.addActionListener(e -> handlePlayAgain());
        card.add(playAgainButton);

        return card;
    }

    private JPanel createScoreboardPanel() {
        JPanel card = createCardPanel();
        card.setLayout(new BorderLayout(0, 12));

        JPanel topBlock = new JPanel();
        topBlock.setOpaque(false);
        topBlock.setLayout(new BoxLayout(topBlock, BoxLayout.Y_AXIS));

        JLabel sessionTitle = new JLabel("SCOREBOARD");
        sessionTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
        sessionTitle.setForeground(ACCENT_TEAL);
        topBlock.add(sessionTitle);
        topBlock.add(Box.createVerticalStrut(8));

        scoreSummaryLabel = new JLabel("0 Wins  /  0 Losses");
        scoreSummaryLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        scoreSummaryLabel.setForeground(TEXT_PRIMARY);
        topBlock.add(scoreSummaryLabel);
        topBlock.add(Box.createVerticalStrut(4));

        JLabel historyHeader = new JLabel("Round History:");
        historyHeader.setFont(new Font("SansSerif", Font.PLAIN, 13));
        historyHeader.setForeground(TEXT_MUTED);
        topBlock.add(historyHeader);

        card.add(topBlock, BorderLayout.NORTH);

        roundHistoryArea = new JTextArea("No completed rounds yet.");
        roundHistoryArea.setEditable(false);
        roundHistoryArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        roundHistoryArea.setForeground(TEXT_MUTED);
        roundHistoryArea.setBackground(CARD_BG);
        roundHistoryArea.setLineWrap(true);
        roundHistoryArea.setWrapStyleWord(true);
        roundHistoryArea.setBorder(new EmptyBorder(8, 8, 8, 8));

        JScrollPane scrollPane = new JScrollPane(roundHistoryArea);
        scrollPane.setBorder(new LineBorder(BORDER_COLOR, 1));
        scrollPane.getViewport().setBackground(CARD_BG);
        card.add(scrollPane, BorderLayout.CENTER);

        resetScoresButton = new JButton("Reset Scores");
        styleButton(resetScoresButton, FIELD_BG, TEXT_PRIMARY);
        resetScoresButton.addActionListener(e -> handleResetScores());
        card.add(resetScoresButton, BorderLayout.SOUTH);

        return card;
    }

    private void handleGuessSubmission() {
        if (!game.isRoundActive()) {
            return;
        }

        String rawInput = guessInputField.getText().trim();

        if (rawInput.isEmpty()) {
            showFeedback("Please enter a number.", ERROR_RED);
            return;
        }

        int playerGuess;
        try {
            playerGuess = Integer.parseInt(rawInput);
        } catch (NumberFormatException ex) {
            showFeedback("Please enter a valid whole number.", ERROR_RED);
            return;
        }

        if (playerGuess < 1 || playerGuess > game.getUpperLimit()) {
            showFeedback("Out of range! Guess between 1 and " + game.getUpperLimit() + ".", ERROR_RED);
            return;
        }

        String result = game.processGuess(playerGuess);

        int used = game.getAttemptsUsed();
        int max = game.getMaxAttempts();
        attemptsCounterLabel.setText("Attempts: " + used + " / " + max);
        attemptsProgressBar.setValue(used);
        guessInputField.setText("");

        if (result.startsWith("Correct!")) {
            showFeedback(result, ACCENT_TEAL);
            endRound();
        } else if (result.startsWith("You Lost!")) {
            showFeedback(result, ERROR_RED);
            endRound();
        } else if (result.equals("Too High!")) {
            showFeedback("Too High!", ERROR_RED);
        } else {
            showFeedback("Too Low!", ACCENT_TEAL);
        }
    }

    private void endRound() {
        guessInputField.setEnabled(false);
        guessButton.setEnabled(false);
        playAgainButton.setVisible(true);

        updateScoreboard();
    }

    private void handlePlayAgain() {
        String selectedDifficulty = getSelectedDifficultyName();
        game.startNewRound(selectedDifficulty);

        updateDisplayForNewRound();
    }

    private void handleResetScores() {
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to reset your scores and start from Round 1?",
            "Reset Scores",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            game.resetSession();
            updateDisplayForNewRound();
            updateScoreboard();
        }
    }

    private void updateDisplayForNewRound() {
        roundHeaderLabel.setText("ROUND " + game.getRoundNumber() + "  ·  " + game.getCurrentDifficulty().toUpperCase());
        rangeLabel.setText("Guess a secret number between 1 and " + game.getUpperLimit() + ":");

        attemptsCounterLabel.setText("Attempts: 0 / " + game.getMaxAttempts());
        attemptsProgressBar.setMaximum(game.getMaxAttempts());
        attemptsProgressBar.setValue(0);

        feedbackMessageLabel.setForeground(TEXT_MUTED);
        feedbackMessageLabel.setText("Enter a number to start guessing.");

        guessInputField.setText("");
        guessInputField.setEnabled(true);
        guessButton.setEnabled(true);
        playAgainButton.setVisible(false);

        updateScoreboard();
        SwingUtilities.invokeLater(() -> guessInputField.requestFocusInWindow());
    }

    private void updateScoreboard() {
        scoreSummaryLabel.setText(game.getTotalWins() + " Wins  /  " + game.getTotalLosses() + " Losses");

        if (game.getRoundHistory().isEmpty()) {
            roundHistoryArea.setText("No completed rounds yet.");
        } else {
            roundHistoryArea.setText(String.join("\n\n", game.getRoundHistory()));
        }
    }

    private String getSelectedDifficultyName() {
        int index = difficultyComboBox.getSelectedIndex();
        if (index == 1) {
            return GuessingGame.EASY;
        } else if (index == 2) {
            return GuessingGame.HARD;
        }
        return GuessingGame.MEDIUM;
    }

    private void showFeedback(String message, Color color) {
        feedbackMessageLabel.setForeground(color);
        feedbackMessageLabel.setText(message);
    }

    private JPanel createCardPanel() {
        JPanel card = new JPanel();
        card.setBackground(CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_COLOR, 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));
        return card;
    }

    private void styleTextField(JTextField field) {
        field.setBackground(FIELD_BG);
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(TEXT_PRIMARY);
        field.setFont(new Font("SansSerif", Font.BOLD, 18));
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_COLOR, 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
    }

    private void styleButton(JButton button, Color background, Color foreground) {
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBorder(new LineBorder(background.darker(), 1));
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setBackground(FIELD_BG);
        comboBox.setForeground(TEXT_PRIMARY);
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
}
