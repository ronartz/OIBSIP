# Number Guessing Game

Task 2 for Oasis Infobyte Java Development Internship (OIBSIP).

## Description

A desktop Number Guessing Game built with Java Swing. The computer generates a secret number between 1 and 100 by default, and the player tries to guess it within a limited number of attempts. The application provides immediate feedback ("Too High!", "Too Low!", or "Correct!"), tracks remaining attempts, maintains a multi-round scoreboard, and records round summaries.

## Features

- Generates a random number at the start of each round using java.util.Random.
- Graphical user interface built using standard Java Swing components.
- Guess submission via button click or Enter key.
- Displays feedback after each guess: "Too High!", "Too Low!", or "Correct!".
- Attempt counter displays remaining attempts for the current round.
- Input validation handles empty input, non-numeric characters, and out-of-range guesses without deducting attempts.
- Win and loss detection with round summary logging.
- Play Again button to start a new round while preserving score and history.
- Difficulty levels:
  - Easy: Range 1 to 50, 10 attempts
  - Medium: Range 1 to 100, 7 attempts (default)
  - Hard: Range 1 to 200, 5 attempts
- Mid-round difficulty lock disables switching during an active round to prevent premature resets.
- Scoreboard displays current round, total wins, and remaining attempts.

## How to Run

Open a terminal in this directory:

```bash
javac NumberGuessingGame.java
java NumberGuessingGame
```

## Screenshots

![Round Won and History](screenshots/Won%20%2B%20history%20rounds.png)

![Round Lost](screenshots/Round%20lost.png)
