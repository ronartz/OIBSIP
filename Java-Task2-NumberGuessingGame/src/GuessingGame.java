import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuessingGame {

    public static final String EASY = "Easy";
    public static final String MEDIUM = "Medium";
    public static final String HARD = "Hard";

    private final Random random;

    private String currentDifficulty;
    private int upperLimit;
    private int maxAttempts;

    private int secretNumber;
    private int attemptsUsed;
    private int roundNumber;
    private boolean roundActive;

    private int totalWins;
    private int totalLosses;
    private final List<String> roundHistory;

    public GuessingGame() {
        this.random = new Random();
        this.roundHistory = new ArrayList<>();
        this.currentDifficulty = MEDIUM;
        this.roundNumber = 0;
        this.totalWins = 0;
        this.totalLosses = 0;
        startNewRound(MEDIUM);
    }

    public final void startNewRound(String difficulty) {
        this.currentDifficulty = difficulty;
        this.roundNumber++;
        this.attemptsUsed = 0;
        this.roundActive = true;

        if (EASY.equalsIgnoreCase(difficulty)) {
            this.upperLimit = 50;
            this.maxAttempts = 10;
        } else if (HARD.equalsIgnoreCase(difficulty)) {
            this.upperLimit = 200;
            this.maxAttempts = 5;
        } else {
            this.currentDifficulty = MEDIUM;
            this.upperLimit = 100;
            this.maxAttempts = 7;
        }

        this.secretNumber = random.nextInt(upperLimit) + 1;
    }

    public String processGuess(int playerGuess) {
        if (!roundActive) {
            return "Round is over. Click 'Play Again' to start a new round.";
        }

        attemptsUsed++;

        if (playerGuess == secretNumber) {
            roundActive = false;
            totalWins++;
            roundHistory.add(
                    "Round " + roundNumber + " — guessed in " + attemptsUsed + " attempts (" + currentDifficulty + ")");
            return "Correct!";
        }

        if (attemptsUsed >= maxAttempts) {
            roundActive = false;
            totalLosses++;
            roundHistory.add(
                    "Round " + roundNumber + " — lost after " + attemptsUsed + " attempts (" + currentDifficulty + ")");
            return "You Lost! The secret number was " + secretNumber + ".";
        }

        if (playerGuess > secretNumber) {
            return "Too High!";
        } else {
            return "Too Low!";
        }
    }

    public void resetSession() {
        this.roundNumber = 0;
        this.totalWins = 0;
        this.totalLosses = 0;
        this.roundHistory.clear();
        startNewRound(MEDIUM);
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttemptsUsed() {
        return attemptsUsed;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public String getCurrentDifficulty() {
        return currentDifficulty;
    }

    public boolean isRoundActive() {
        return roundActive;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public List<String> getRoundHistory() {
        return roundHistory;
    }
}
