import java.util.ArrayList;
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
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void displayAccount() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }
}

public class BankManagementSystem {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Bank Management System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> viewAccount();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter holder name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        accounts.add(new Account(accNo, name, balance));
        System.out.println("Account created successfully!");
    }

    private static Account findAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    private static void deposit() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter deposit amount: ");
            double amount = sc.nextDouble();
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void withdraw() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = sc.nextDouble();
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    private static void viewAccount() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccount();
        } else {
            System.out.println("Account not found!");
        }
    }
}
