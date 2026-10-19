# Task 3: ATM Interface

Oasis Infobyte Java Development Internship (OIBSIP)  
Author: Shreejit Mondal  
Track: Java Development  

---

## Project Overview

This project is a console-based ATM (Automated Teller Machine) interface built using Core Java. It simulates essential banking operations including user authentication, transaction history tracking, cash withdrawals, deposits, and account-to-account funds transfers.

This project is developed solely for educational purposes as an internship submission. It operates on in-memory demo data and does not connect to real banking networks or external databases.

---

## Demo Accounts

The application initializes with three pre-configured fictional accounts for testing:

| User ID | PIN | Account Holder | Initial Balance |
| :---: | :---: | :--- | :---: |
| `1001` | `1234` | John Doe | $1,500.00 |
| `1002` | `5678` | Jane Smith | $2,500.00 |
| `1003` | `4321` | Alex Rivera | $500.00 |

*Note: These credentials are strictly for local testing and demonstration.*

---

## Class Architecture

The application is modularized across five separate Java classes:

1. **`Main.java`**
   - Entry point of the application.
   - Initializes the `Bank` object, registers the demo accounts, creates an `ATM` instance, and begins execution.

2. **`Account.java`**
   - Represents an individual bank account.
   - Stores account attributes: `userId`, `userPin`, `userName`, `balance`, and `transactionHistory` (using `ArrayList<Transaction>`).
   - Contains business logic to deposit, withdraw, and transfer funds while updating account balances.

3. **`ATM.java`**
   - Manages all user interaction and console input/output via `Scanner`.
   - Handles the 3-attempt login verification and lockout security rule.
   - Displays the 5-option interactive menu, reads choices, and routes user requests.
   - Validates user inputs, numerical values, and error states.

4. **`Bank.java`**
   - Serves as the repository of all accounts using an `ArrayList<Account>`.
   - Provides methods to register new accounts, find accounts by User ID, and authenticate user credentials against stored records.

5. **`Transaction.java`**
   - Represents a single financial transaction record.
   - Stores the transaction type (e.g., Deposit, Withdrawal, Transfer), transaction amount, remaining balance after transaction, and a timestamp.

## Feature Checklist

- [x] **Startup prompt for User ID and PIN; deny access after 3 incorrect attempts**
- [x] **Main menu displayed after successful login with the following options**:
  - [x] `1. Transaction History` — display a log of all past transactions in the current session
  - [x] `2. Withdraw` — prompt for amount; validate sufficient balance; update balance; log transaction
  - [x] `3. Deposit` — prompt for amount; update balance; log transaction
  - [x] `4. Transfer` — prompt for recipient account ID and amount; validate balance; update both accounts; log transaction
  - [x] `5. Quit` — display a goodbye message and exit
- [x] **Balance check before any withdrawal or transfer; display "Insufficient Funds" if balance is too low**
- [x] **All transactions stored in an `ArrayList` and displayed clearly in Transaction History**
- [x] **At least 5 distinct Java classes**: `ATM`, `Account`, `Transaction`, `Bank`, `Main`

---

## Features and Rules

- **Secure Login**: Users must provide a valid User ID and 4-digit PIN.
- **3-Attempt Lockout**: If an incorrect User ID or PIN is entered 3 consecutive times, access is locked for security and the application terminates.
- **Interactive Menu**:
  1. **Transaction History**: Displays all transactions recorded during the current session along with the latest balance.
  2. **Withdraw**: Deducts funds only if the requested amount is greater than zero and does not exceed the current balance.
  3. **Deposit**: Adds valid positive amounts to the account balance.
  4. **Transfer**: Transfers funds from the current user to another existing account. Validates that the recipient exists, is not the sender's own account, and that sufficient balance exists. Updates the balance and transaction history of both accounts.
  5. **Quit**: Exits the ATM session cleanly with a goodbye message.
- **Robust Validation**:
  - Rejects non-numeric text when a numerical amount is expected.
  - Rejects negative or zero amounts.
  - Prevents overdrafts and insufficient-balance transactions.
  - Prevents transferring to nonexistent accounts or the sender's own account.
  - Prompts clearly when an invalid menu choice is entered.

---

## How to Compile and Run

Open a terminal and navigate to the project directory:

```bash
cd Java-Task3-ATMInterface
```

### 1. Compile

```bash
javac *.java
```

### 2. Run

```bash
java Main
```

---

## Testing Report

### What Was Tested
- **Successful Authentication**: Verified login with valid credentials (e.g., `1001` / `1234`).
- **Failed Authentication & Lockout**: Verified attempt counters on incorrect PIN entry and lockout termination after 3 failed attempts.
- **Deposit Operations**: Verified positive amount deposit, zero amount rejection, negative amount rejection, and non-numeric input handling.
- **Withdrawal Operations**: Verified valid withdrawal, overdraft prevention (insufficient funds), negative amount rejection, and non-numeric input handling.
- **Funds Transfer Operations**:
  - Verified successful transfer between two accounts.
  - Verified deduction from sender balance and addition to recipient balance.
  - Verified transaction record created in sender's history (`Transfer to <id>`).
  - Verified transaction record created in recipient's history (`Transfer from <id>`).
  - Verified rejection of transfer exceeding available balance.
  - Verified rejection of transfer to nonexistent account IDs.
  - Verified rejection of self-transfer.
  - Verified rejection of zero or negative transfer amounts.
- **Transaction History**: Verified display of session transactions with formatted amounts, remaining balances, and timestamps.
- **Menu Navigation**: Verified valid menu selections (1–5) and handling of invalid menu options.

### What Was Not Tested
- Hardware integration with physical cash dispensers or card readers (simulation only).
- Multi-threaded concurrent transactions across distributed network terminals (single-user console scope).
- Persistent storage across program restarts (data is stored in-memory during the program lifecycle as required for this task).

