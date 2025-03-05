import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Account class to store the account details
class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}

// ATM class to manage multiple accounts
class ATM {
    private Map<String, Account> accounts = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private Account currentAccount = null; // Stores the currently logged-in account

    // Method to add an account to the system
    public void addAccount(Account account) {
        accounts.put(account.getAccountHolder(), account);
    }

    // Method to start the ATM interface
    public void start() {
        System.out.println("Welcome to the ATM System");

        while (true) {
            if (currentAccount == null) {
                // Asking for account holder name only if not already logged in
                System.out.print("\nEnter account holder name: ");
                String name = scanner.nextLine();

                // Validating account
                currentAccount = accounts.get(name);
                if (currentAccount == null) {
                    System.out.println("Account not found. Try again.");
                    continue;
                }
                System.out.println("Welcome, " + currentAccount.getAccountHolder() + "!");
            }

            // Showing options
            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Switch Account");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    currentAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Current balance: " + currentAccount.getBalance());
                    break;
                case 4:
                    // Allowing switching accounts
                    System.out.println("Logging out of " + currentAccount.getAccountHolder());
                    currentAccount = null; // Reseting current account
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return; // Exiting the program
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

// Main class to run the program
public class ATMSystem {
    public static void main(String[] args) {
        ATM atm = new ATM();

        // Adding sample accounts
        atm.addAccount(new Account("123456", "Eric Nyamwaya", 1000.0));
        atm.addAccount(new Account("789012", "James Bunge", 500.0));

        // Starting the ATM system
        atm.start();
    }
}
