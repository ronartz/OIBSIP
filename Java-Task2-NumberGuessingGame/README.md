# Task 2: Number Guessing Game

**Oasis Infobyte Java Development Internship (OIBSIP)**  
**Author:** Shreejit Mondal  
**Track:** Java Development  

---

## 📌 Project Overview

An interactive, beginner-friendly desktop Number Guessing Game built in Java using the **Swing** GUI framework. The computer generates a secret random number, and the player attempts to guess it within a limited number of attempts, receiving clear higher/lower hints after each guess.

The application follows a clean separation of concerns:
- `GuessingGame.java`: Core game logic, attempts tracking, difficulty ranges, and score history (no UI code).
- `GameWindow.java`: Graphical user interface built with standard Swing components in a clean dark theme.
- `Main.java`: Clean application entry point.

---

## 🚀 Features

- **Random Number Generation**: Generates a new random number at the start of every round.
- **Interactive Swing Input**: Input via text field with Enter key support or button click.
- **Accurate Hints**: Displays `"Too High!"`, `"Too Low!"`, or `"Correct!"` after each valid guess.
- **Attempt Tracking**: Continuously displays remaining attempts alongside a visual progress bar.
- **Maximum Attempt Limits**: Enforces attempt limits (7 attempts in standard Medium mode) and displays `"You Lost!"` revealing the secret number if attempts run out.
- **Difficulty Modes**:
  - **Easy**: 1–50 (10 attempts)
  - **Medium**: 1–100 (7 attempts) — *Default*
  - **Hard**: 1–200 (5 attempts)
- **Scoreboard & History**: Tracks total wins, total losses, and shows round-by-round summary logs.
- **Play Again & Reset**: Easily replay rounds or reset session scores back to round 1.
- **Input Validation**: Safely handles empty input, letters, symbols, and numbers outside the valid range without crashing or consuming player attempts.

---

## 🎮 How to Play

1. Choose your desired difficulty from the dropdown (applied at the start of each round).
2. Enter your guess in the input box and click **Guess** (or press `Enter`).
3. Read the hint:
   - `"Too High!"` → Try a smaller number.
   - `"Too Low!"` → Try a larger number.
   - `"Correct!"` → You won the round!
4. If you run out of attempts, `"You Lost!"` is shown along with the secret number.
5. Click **Play Again** to begin the next round, or **Reset Scores** to restart from Round 1.

---

## 🛠️ Technologies Used

- **Language**: Java (JDK 8+ / OpenJDK 25)
- **GUI Framework**: Java Swing & AWT (`JFrame`, `JPanel`, `JLabel`, `JTextField`, `JButton`, `JComboBox`, `JProgressBar`, `JTextArea`, `JScrollPane`)
- **Core Concepts**: `java.util.Random`, `java.util.ArrayList`, standard event listeners, layout managers (`BorderLayout`, `GridLayout`, `BoxLayout`)

---

## 📁 Project Structure

```text
Java-Task2-NumberGuessingGame/
├── README.md
└── src/
    ├── Main.java         # Application entry point
    ├── GuessingGame.java # Core game logic (independent of UI)
    └── GameWindow.java   # Swing GUI interface
```

---

## 💻 How to Compile and Run

Open your terminal in the `Java-Task2-NumberGuessingGame` folder:

### 1. Compile the project
```bash
javac -encoding UTF-8 -d build src/*.java
```

### 2. Run the application
```bash
java -cp build Main
```
