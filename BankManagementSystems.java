import java.util.HashMap;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String holderName;
    private double balance;

    public Account(int accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    @Override
    public String toString() {
        return "Account No: " + accountNumber + ", Holder: " + holderName + ", Balance: " + balance;
    }
}

public class BankManagementSystems {
    private static HashMap<Integer, Account> accounts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. Delete Account");
            System.out.println("6. View All Accounts");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> viewAccount();
                case 5 -> deleteAccount();
                case 6 -> viewAllAccounts();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void createAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine(); // consume newline
        if (accounts.containsKey(accNo)) {
            System.out.println("Account number already exists!");
            return;
        }
        System.out.print("Enter Holder Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        accounts.put(accNo, new Account(accNo, name, balance));
        System.out.println("Account created successfully!");
    }

    private static void deposit() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        Account acc = accounts.get(accNo);
        if (acc != null) {
            System.out.print("Enter Deposit Amount: ");
            double amount = sc.nextDouble();
            acc.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        Account acc = accounts.get(accNo);
        if (acc != null) {
            System.out.print("Enter Withdrawal Amount: ");
            double amount = sc.nextDouble();
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        Account acc = accounts.get(accNo);
        if (acc != null) {
            System.out.println(acc);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deleteAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        if (accounts.remove(accNo) != null) {
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (Account acc : accounts.values()) {
                System.out.println(acc);
            }
        }
    }
}
