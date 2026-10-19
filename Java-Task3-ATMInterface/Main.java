public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Register demo accounts for testing
        bank.addAccount(new Account("1001", "1234", "John Doe", 1500.00));
        bank.addAccount(new Account("1002", "5678", "Jane Smith", 2500.00));
        bank.addAccount(new Account("1003", "4321", "Alex Rivera", 500.00));

        ATM atm = new ATM(bank);
        atm.start();
    }
}

