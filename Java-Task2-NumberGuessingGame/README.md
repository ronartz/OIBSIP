# Task 2: Number Guessing Game

**Oasis Infobyte Java Development Internship (OIBSIP)**  
**Author:** Shreejit Mondal  
**Track:** Java Development  

---

## 📌 Project Description

An interactive desktop Number Guessing Game built using Core Java and Java Swing. The computer randomly generates a secret number between 1 and 100, and the player gets up to 7 valid attempts to guess it. After each guess, the game provides clear feedback ("Too High", "Too Low", or "Correct"), tracks remaining attempts, maintains a multi-round scoreboard, and displays round history.

---

## 🚀 Features

- **Random Number Generation**: Generates a random number from 1 to 100 at the start of each round.
- **Swing User Interface**: Clean dark-themed GUI built with standard Swing components (`JFrame`, `JPanel`, `JLabel`, `JTextField`, `JButton`, `JTextArea`, `JScrollPane`).
- **Input Submission**: Submit guesses by clicking **Guess** or pressing the `Enter` key.
- **Immediate Feedback**: Displays "Too High", "Too Low", or "Correct" with color-coded alerts.
- **Attempt Tracking**: Real-time counter showing remaining attempts (7 attempts per round).
- **Input Validation**: Rejects empty inputs, letters, symbols, and numbers outside 1–100 without consuming attempts.
- **Win & Loss Handling**:
  - Displays total attempts used upon winning.
  - Displays "Game Over" and reveals the secret number when attempts reach 0.
  - Automatically disables guess inputs when a round ends.
- **Play Again**: Restarts a new round with a fresh secret number while preserving score and round history.
- **Difficulty Levels (Bonus)**:
  - **Easy**: Range 1–50 (10 attempts)
  - **Medium**: Range 1–100 (7 attempts) — *Default*
  - **Hard**: Range 1–200 (5 attempts)
- **Scoreboard & History**: Tracks round number, total wins, remaining attempts, and displays a scrollable summary log (`Round X — guessed in Y attempts`).

---

## 🛠️ Requirements & Tech Stack

- **Java**: JDK 8 or higher (OpenJDK 25 tested)
- **GUI Framework**: Java Swing & AWT
- **Operating System**: Linux (Fedora KDE) / Windows / macOS

---

## 📁 Project Structure

```text
Java-Task2-NumberGuessingGame/
├── NumberGuessingGame.java   # Complete game logic and Swing GUI
├── README.md                 # Project documentation and manual testing checklist
└── screenshots/              # Gameplay screenshots captured on Fedora KDE
```

---

## 💻 Compilation & Execution

Open terminal in the `Java-Task2-NumberGuessingGame` directory:

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

Authentic screenshots can be captured using KDE Spectacle on Fedora and stored in the `screenshots/` directory:

- `screenshots/gameplay.png` — Active round showing guess submission and hint.
- `screenshots/win.png` — Victory screen showing attempts used and score update.
- `screenshots/game_over.png` — Game over screen showing revealed secret number.

---

## ✅ Manual Testing Checklist

- [ ] **Startup**: Window opens centered with Round 1, 0 Wins, and 7 Attempts Left.
- [ ] **Enter Key**: Type a number and press `Enter` to submit.
- [ ] **Guess Button**: Type a number and click `Guess` to submit.
- [ ] **Hints**: Returns "Too High!" or "Too Low!".
- [ ] **Win**: Entering the correct number shows "Correct!", increments wins, disables inputs, and logs to history.
- [ ] **Game Over**: Using 7 failed attempts shows "You Lost! The correct number was: X", disables inputs, and enables Play Again.
- [ ] **Play Again**: Starts Round 2 with 7 attempts and a new secret number.
- [ ] **Input Validation**:
  - [ ] Empty text shows "Please enter a valid number!" (attempts remaining unchanged).
  - [ ] Non-numeric input (e.g. `abc`) shows "Please enter a valid number!" (attempts remaining unchanged).
  - [ ] Out-of-bounds input (e.g. `150` or `0`) shows "Enter 1 to 100 only!" (attempts remaining unchanged).
