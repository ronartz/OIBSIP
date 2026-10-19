# ATM Interface

Task 3 for Oasis Infobyte Java Development Internship (OIBSIP).

## Description

A console-based ATM simulation built in Java using object-oriented programming. The application allows users to authenticate with a User ID and PIN, view session transactions, withdraw money, deposit money, and transfer funds between accounts.

This program is an educational simulation that runs locally in memory with pre-configured demo accounts.

## Demo Accounts

The application initializes with three demo accounts for testing:

| User ID | PIN | Name | Starting Balance |
| :---: | :---: | :--- | :---: |
| 1001 | 1234 | John Doe | $1,500.00 |
| 1002 | 5678 | Jane Smith | $2,500.00 |
| 1003 | 4321 | Alex Rivera | $500.00 |

## Features

- Startup authentication prompts for User ID and PIN.
- Security lockout denies access after 3 consecutive incorrect login attempts.
- Interactive menu driven by switch-case with five options:
  1. Transaction History: Displays all transactions from the current session.
  2. Withdraw: Deducts money only when the account has sufficient balance.
  3. Deposit: Adds a valid positive amount to the account.
  4. Transfer: Transfers funds to another existing account and updates balances for both accounts.
  5. Quit: Displays a goodbye message and exits the program.
- Balance check before any withdrawal or transfer. Displays "Insufficient Funds" if the balance is too low.
- Input validation rejects negative numbers, zero, NaN, Infinity, and non-numeric inputs.
- All transactions are stored in an ArrayList and displayed with transaction type, amount, remaining balance, and timestamp.
- Modular architecture using five classes:
  - Main.java: Initializes demo accounts and starts the application.
  - Account.java: Stores user details, balance, and transaction history.
  - ATM.java: Handles the menu, user input, and display logic.
  - Bank.java: Manages account lookup and authentication.
  - Transaction.java: Represents individual transaction records.

## How to Run

Open a terminal in this directory:

```bash
javac *.java
java Main
```
