import java.util.ArrayList;

public class Account {
    private String userId;
    private String userPin;
    private String userName;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account(String userId, String userPin, String userName, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.userName = userName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean validatePin(String pin) {
        return this.userPin.equals(pin);
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount, balance));
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.add(new Transaction("Withdrawal", amount, balance));
        return true;
    }

    public boolean transfer(Account recipient, double amount) {
        if (recipient == null || recipient == this || amount <= 0 || amount > balance) {
            return false;
        }
        // Deduct from sender and credit recipient
        balance -= amount;
        recipient.balance += amount;

        // Record transaction in both accounts
        transactionHistory.add(new Transaction("Transfer to " + recipient.getUserId(), amount, balance));
        recipient.transactionHistory.add(new Transaction("Transfer from " + this.userId, amount, recipient.balance));
        return true;
    }
}

