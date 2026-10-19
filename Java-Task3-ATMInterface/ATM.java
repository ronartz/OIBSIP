import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this(bank, new Scanner(System.in));
    }

    public ATM(Bank bank, Scanner scanner) {
        this.bank = bank;
        this.scanner = scanner;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void start() {
        System.out.println("=========================================");
        System.out.println("          WELCOME TO THE ATM             ");
        System.out.println("=========================================");

        int maxAttempts = 3;
        int attempts = 0;
        Account currentAccount = null;

        while (attempts < maxAttempts) {
            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine().trim();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine().trim();

            currentAccount = bank.authenticate(userId, pin);

            if (currentAccount != null) {
                System.out.println("\nLogin successful! Welcome, " + currentAccount.getUserName() + ".\n");
                break;
            } else {
                attempts++;
                int remaining = maxAttempts - attempts;
                if (remaining > 0) {
                    System.out.println("Invalid User ID or PIN. Attempts remaining: " + remaining + "\n");
                } else {
                    System.out.println("Maximum login attempts exceeded. Access locked for security.");
                    return;
                }
            }
        }

        runMenu(currentAccount);
    }

    private void runMenu(Account account) {
        boolean running = true;

        while (running) {
            System.out.println("=========================================");
            System.out.println("               ATM MENU                  ");
            System.out.println("=========================================");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option (1-5): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    showTransactionHistory(account);
                    break;
                case "2":
                    performWithdraw(account);
                    break;
                case "3":
                    performDeposit(account);
                    break;
                case "4":
                    performTransfer(account);
                    break;
                case "5":
                    System.out.println("\nThank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 5.\n");
                    break;
            }
        }
    }

    private void showTransactionHistory(Account account) {
        System.out.println("\n--- Transaction History ---");
        ArrayList<Transaction> history = account.getTransactionHistory();

        if (history.isEmpty()) {
            System.out.println("No transactions found in this session.");
        } else {
            for (Transaction tx : history) {
                System.out.println(tx);
            }
        }
        System.out.printf("Current Balance: $%.2f%n%n", account.getBalance());
    }

    private void performWithdraw(Account account) {
        System.out.println("\n--- Withdraw Money ---");
        System.out.printf("Current Balance: $%.2f%n", account.getBalance());
        System.out.print("Enter amount to withdraw: ");

        double amount = readPositiveAmount();
        if (amount <= 0) {
            return;
        }

        if (amount > account.getBalance()) {
            System.out.printf("Error: Insufficient Funds. Current balance: $%.2f%n%n", account.getBalance());
            return;
        }

        boolean success = account.withdraw(amount);
        if (success) {
            System.out.printf("Success: $%.2f withdrawn. Remaining balance: $%.2f%n%n", amount, account.getBalance());
        }
    }

    private void performDeposit(Account account) {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Enter amount to deposit: ");

        double amount = readPositiveAmount();
        if (amount <= 0) {
            return;
        }

        boolean success = account.deposit(amount);
        if (success) {
            System.out.printf("Success: $%.2f deposited. Updated balance: $%.2f%n%n", amount, account.getBalance());
        }
    }

    private void performTransfer(Account account) {
        System.out.println("\n--- Transfer Money ---");
        System.out.printf("Current Balance: $%.2f%n", account.getBalance());
        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.nextLine().trim();

        if (recipientId.isEmpty()) {
            System.out.println("Error: Recipient User ID cannot be empty.\n");
            return;
        }

        // Prevent transfer to the same account
        if (recipientId.equals(account.getUserId())) {
            System.out.println("Error: You cannot transfer money to your own account.\n");
            return;
        }

        Account recipient = bank.findAccount(recipientId);
        if (recipient == null) {
            System.out.println("Error: Recipient account not found. Please verify the User ID.\n");
            return;
        }

        System.out.println("Recipient found: " + recipient.getUserName());
        System.out.print("Enter amount to transfer: ");

        double amount = readPositiveAmount();
        if (amount <= 0) {
            return;
        }

        if (amount > account.getBalance()) {
            System.out.printf("Error: Insufficient Funds. Current balance: $%.2f%n%n", account.getBalance());
            return;
        }

        boolean success = account.transfer(recipient, amount);
        if (success) {
            System.out.printf("Success: $%.2f transferred to %s (%s). Remaining balance: $%.2f%n%n",
                    amount, recipient.getUserName(), recipient.getUserId(), account.getBalance());
        }
    }

    private double readPositiveAmount() {
        String input = scanner.nextLine().trim();
        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) {
                System.out.println("Error: Amount must be greater than zero.\n");
                return -1;
            }
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a valid numerical amount.\n");
            return -1;
        }
    }
}

