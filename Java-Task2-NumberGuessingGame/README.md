# Task 2: Number Guessing Game

**Oasis Infobyte Java Development Internship (OIBSIP)**  
**Author:** Shreejit Mondal  
**Track:** Java Development  

---

## 📌 Project Description

An interactive desktop Number Guessing Game built using Core Java and standard Java Swing. The computer randomly generates a secret number (1–100 by default), and the player attempts to guess it within a limited number of attempts. After each guess, the game displays clear feedback ("Too High!", "Too Low!", or "Correct!"), tracks remaining attempts, maintains a multi-round scoreboard, and logs round-by-round summaries.

---

## 🚀 Features

- **Random Number Generation**: Generates a new random number at the start of each round using `java.util.Random`.
- **Swing User Interface**: Clean desktop interface built using standard Swing components (`JFrame`, `JPanel`, `JLabel`, `JTextField`, `JButton`, `JComboBox`, `JTextArea`, `JScrollPane`).
- **Input Submission**: Submit guesses by clicking **Guess** or pressing the `Enter` key.
- **Immediate Feedback**: Displays `"Too High!"`, `"Too Low!"`, or `"Correct!"` after each guess.
- **Attempt Tracking**: Continuous counter showing remaining attempts for the active round.
- **Input Validation**: Safely handles empty input, letters, symbols, and numbers outside the active range without consuming attempts.
- **Win & Loss Handling**:
  - Displays `"Correct!"`, records attempts used, and increments total wins.
  - Displays `"You Lost! The correct number was: X"` when attempts reach 0.
  - Automatically disables guess inputs when a round ends.
- **Play Again**: Restarts a new round with a fresh secret number while preserving score and round history.
- **Difficulty Levels (Bonus)**:
  - **Easy**: Range 1–50 (10 attempts)
  - **Medium**: Range 1–100 (7 attempts) — *Default*
  - **Hard**: Range 1–200 (5 attempts)
  - Switching difficulty mid-round is locked to preserve fair play, and re-enables between rounds.
- **Scoreboard & History**: Tracks round number, total wins, remaining attempts, and displays a summary log (`Round X — guessed in Y attempts`).

---

## 🛠️ Requirements & Tech Stack

- **Java**: JDK 8 or higher (tested with standard OpenJDK)
- **GUI Framework**: Java Swing & AWT
- **Operating System**: Linux (Fedora KDE) / Windows / macOS

---

## 📁 Project Structure

```text
Java-Task2-NumberGuessingGame/
├── NumberGuessingGame.java   # Complete game logic and Swing GUI
├── README.md                 # Project documentation and manual testing checklist
└── screenshots/              # Reserved for gameplay captures
```

---

## 💻 Compilation & Execution

Open your terminal in the `Java-Task2-NumberGuessingGame` directory:

### 1. Compile
```bash
javac NumberGuessingGame.java
```

### 2. Run
```bash
java NumberGuessingGame
```

---

## 📸 Screenshots

The `screenshots/` directory is prepared for submission artifacts. Screenshots can be captured during manual gameplay on your system and placed here before final evaluation:

- `screenshots/gameplay.png` — Active round showing guess submission and hint.
- `screenshots/win.png` — Victory state showing score update and summary entry.
- `screenshots/game_over.png` — Loss state showing revealed secret number.

---

## ✅ Manual Testing Checklist

- [ ] **Startup**: Window opens with Medium difficulty selected, Round: 1, Wins: 0, and Attempts left: 7.
- [ ] **Difficulty Selection**: Changing difficulty before making a guess updates range and attempts immediately (e.g. Easy sets 1–50 with 10 attempts).
- [ ] **Mid-round Lock**: After the first valid guess, the difficulty selector is disabled until the round finishes.
- [ ] **Guess Submission**: Typing a guess and pressing `Enter` or clicking `Guess` submits the guess.
- [ ] **Hints**: Returns `"Too High!"` if guess > target, or `"Too Low!"` if guess < target.
- [ ] **Win**: Guessing the target displays `"Correct!"`, increments wins, and logs `"Round X — guessed in Y attempts"`.
- [ ] **Loss**: Using all attempts displays `"You Lost! The correct number was: X"` and logs the loss.
- [ ] **Round Transition**: The scoreboard displays the current round number during the win/loss state, and increments to the next round only when `Play Again` is clicked.
- [ ] **Input Validation**:
  - [ ] Submitting empty input or letters shows `"Please enter a valid whole number!"` (attempts unchanged).
  - [ ] Submitting out-of-range numbers shows `"Guess must be between 1 and X!"` (attempts unchanged).
