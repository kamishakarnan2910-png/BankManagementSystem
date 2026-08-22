import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BankManagementSystemtree{

    static Scanner sc = new Scanner(System.in);
    static TreeMap<Integer, Account> accounts = new TreeMap<>();
    static DateTimeFormatter f =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    static class Account {
        int id;
        String name;
        double balance;
        TreeMap<LocalDateTime, String> transactions = new TreeMap<>();

        Account(int id, String name, double balance) {
            this.id = id;
            this.name = name;
            this.balance = balance;
        }
    }

    static void addAccount() {
        System.out.print("Account ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (accounts.containsKey(id)) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Initial Balance: ");
        double balance = Double.parseDouble(sc.nextLine());

        if (balance < 0) {
            System.out.println("Invalid balance.");
            return;
        }

        accounts.put(id, new Account(id, name, balance));
        System.out.println("Account created successfully.");
    }

    static void deposit() {
        System.out.print("Account ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Account a = accounts.get(id);

        if (a == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        a.balance += amount;
        a.transactions.put(
                LocalDateTime.now(),
                "CREDIT +₹" + amount
        );

        System.out.println("Deposit successful.");
        System.out.println("Balance: ₹" + a.balance);
    }

    static void withdraw() {
        System.out.print("Account ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Account a = accounts.get(id);

        if (a == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Amount: ");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0 || amount > a.balance) {
            System.out.println("Invalid amount or insufficient balance.");
            return;
        }

        a.balance -= amount;
        a.transactions.put(
                LocalDateTime.now(),
                "DEBIT -₹" + amount
        );

        System.out.println("Withdrawal successful.");
        System.out.println("Balance: ₹" + a.balance);
    }

    static void statement() {
        System.out.print("Account ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Account a = accounts.get(id);

        if (a == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("\nAccount: " + a.name);
        System.out.println("ID: " + a.id);
        System.out.println("------------------------------");

        if (a.transactions.isEmpty()) {
            System.out.println("No transactions.");
        } else {
            for (Map.Entry<LocalDateTime, String> e :
                    a.transactions.entrySet()) {
                System.out.println(
                        e.getKey().format(f) + "  " + e.getValue()
                );
            }
        }

        System.out.println("------------------------------");
        System.out.println("Current Balance: ₹" + a.balance);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== SECURE BANK =====");
            System.out.println("1. Add Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Statement");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        addAccount();
                        break;
                    case 2:
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        statement();
                        break;
                    case 5:
                        System.out.println("Thank you!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("Please enter valid input.");
            }
        }
    }
}