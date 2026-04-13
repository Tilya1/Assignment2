import java.util.*;

public class MiniBankingMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<BankAccount> Accounts = new LinkedList<>(); // Task 1. LinkedList
        Stack<String> transactions = new Stack<>(); // Task 3, Stack<String> transaction History
        Queue<String> billQueue = new LinkedList<>();
        Queue<BankAccount> accountRequests = new LinkedList<>();
        int id = 0;

        while (true) {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║        MINI BANK SYSTEM      ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Enter Bank                ║");
            System.out.println("║ 2. Enter ATM                 ║");
            System.out.println("║ 3. Admin Area                ║");
            System.out.println("║ 4. Exit                      ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // ── BANK MENU ─────────────────────────────────────────────
                case 1:
                    boolean bankBack = false;
                    while (!bankBack) {
                        System.out.println("\n╔══════════════════════════════╗");
                        System.out.println("║          BANK MENU           ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ 1. Open account request      ║");
                        System.out.println("║ 2. Deposit money             ║");
                        System.out.println("║ 3. Withdraw money            ║");
                        System.out.println("║ 4. Back                      ║");
                        System.out.println("╚══════════════════════════════╝");
                        System.out.print("Choose: ");
                        int number = scanner.nextInt();
                        scanner.nextLine();
                        switch (number) {
                            case 1: // Task 1, username, balance
                                System.out.print("Enter username: ");
                                String username = scanner.nextLine();
                                System.out.print("Enter initial balance: ");
                                double balance = scanner.nextDouble();
                                scanner.nextLine();
                                accountRequests.add(new BankAccount(++id, username, balance));
                                System.out.println("Account request added successfully to the queue");
                                System.out.println();
                                break;
                            case 2: // Task 2, Deposite money, withdraw money
                                System.out.print("Enter username: ");
                                username = scanner.nextLine();
                                boolean isFound = false;
                                for (BankAccount account : Accounts) { // Task 2, update balance inside LinkedList
                                    if (account.getUsername().equals(username)) {
                                        System.out.print("Deposit: ");
                                        double deposit = scanner.nextDouble();
                                        scanner.nextLine();
                                        account.deposit(deposit);
                                        isFound = true;
                                        System.out.println("New balance: " + account.getBalance());
                                        transactions.push("Deposit " + deposit + " to " + username); // Task 3, transactions
                                    }
                                }
                                if (!isFound) System.out.println("Username not found!");
                                System.out.println();
                                break;
                            case 3:
                                System.out.print("Enter username: ");
                                username = scanner.nextLine();
                                isFound = false;
                                for (BankAccount account : Accounts) {
                                    if (account.getUsername().equals(username)) {
                                        System.out.print("Withdraw: ");
                                        double withdraw = scanner.nextDouble();
                                        scanner.nextLine();
                                        if (withdraw > account.getBalance()) {
                                            System.out.println("Insufficient funds!");
                                            break;
                                        }
                                        account.withdraw(withdraw);
                                        isFound = true;
                                        System.out.println("New balance: " + account.getBalance());
                                        transactions.push("Withdraw " + withdraw + " from " + username);
                                    }
                                }
                                if (!isFound) System.out.println("Username not found!");
                                System.out.println();
                                break;
                            case 4:
                                bankBack = true;
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break; // ← обязательный break чтобы не упасть в case 2

                // ── ATM MENU ──────────────────────────────────────────────
                case 2:
                    boolean atmBack = false;
                    while (!atmBack) {
                        System.out.println("\n╔══════════════════════════════╗");
                        System.out.println("║           ATM MENU           ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ 1. Balance enquiry           ║");
                        System.out.println("║ 2. Withdraw                  ║");
                        System.out.println("║ 3. Back                      ║");
                        System.out.println("╚══════════════════════════════╝");
                        System.out.print("Choose one: ");
                        int number = scanner.nextInt();
                        scanner.nextLine();
                        switch (number) {
                            case 1:
                                System.out.print("Enter username: ");
                                String username = scanner.nextLine();
                                boolean isFound = false;
                                for (BankAccount account : Accounts) {
                                    if (account.getUsername().equals(username)) {
                                        account.information();
                                        isFound = true;
                                    }
                                }
                                if (!isFound) System.out.println("Account not found!");
                                System.out.println();
                                break;
                            case 2:
                                System.out.print("Enter username: ");
                                username = scanner.nextLine();
                                isFound = false;
                                for (BankAccount account : Accounts) {
                                    if (account.getUsername().equals(username)) {
                                        isFound = true;
                                        System.out.print("Enter amount to withdraw: ");
                                        double withdraw = scanner.nextDouble();
                                        scanner.nextLine();
                                        while (withdraw > account.getBalance() || withdraw < 1) {
                                            System.out.println("Invalid amount!");
                                            System.out.print("Enter again: ");
                                            withdraw = scanner.nextDouble();
                                            scanner.nextLine();
                                        }
                                        account.withdraw(withdraw);
                                        transactions.push("ATM Withdraw " + withdraw + " from " + username);
                                        System.out.println("New balance: " + account.getBalance());
                                    }
                                }
                                if (!isFound) System.out.println("Account not found!");
                                System.out.println();
                                break;
                            case 3:
                                atmBack = true;
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                // ── ADMIN MENU ────────────────────────────────────────────
                case 3:
                    boolean adminBack = false;
                    while (!adminBack) {
                        System.out.println("\n╔══════════════════════════════╗");
                        System.out.println("║          ADMIN MENU          ║");
                        System.out.println("╠══════════════════════════════╣");
                        System.out.println("║ 1. View requests             ║");
                        System.out.println("║ 2. Process requests          ║");
                        System.out.println("║ 3. View bill queue           ║");
                        System.out.println("║ 4. Back                      ║");
                        System.out.println("╚══════════════════════════════╝");
                        System.out.print("Choose: ");
                        int number = scanner.nextInt();
                        scanner.nextLine();
                        switch (number) {
                            case 1:
                                if (accountRequests.isEmpty()) {
                                    System.out.println("No pending requests!");
                                } else {
                                    System.out.println("Pending account requests:");
                                    int i = 1;
                                    for (BankAccount account : accountRequests) {
                                        System.out.print(i++ + ". ");
                                        account.information();
                                    }
                                }
                                System.out.println();
                                break;

                            case 2:
                                if (accountRequests.isEmpty()) {
                                    System.out.println("No pending requests!");
                                    break;
                                }
                                Iterator<BankAccount> iter = accountRequests.iterator();
                                int idx = 1;
                                while (iter.hasNext()) {
                                    BankAccount account = iter.next();
                                    System.out.print(idx++ + ". ");
                                    account.information();
                                    System.out.print("Approve? (Y/N): ");
                                    String ans = scanner.nextLine();
                                    if (ans.equalsIgnoreCase("Y")) {
                                        Accounts.add(account);
                                        System.out.println("Account approved and added.");
                                    } else {
                                        System.out.println("Request rejected.");
                                    }
                                    iter.remove();
                                }
                                System.out.println();
                                break;
                            case 3:

                                System.out.println("Bill Payment Queue:");
                                if (billQueue.isEmpty()) {
                                    System.out.println("No bill payments found!");
                                } else {
                                    int i = 1;
                                    for (String bill : billQueue) {
                                        System.out.println(i++ + ". " + bill);
                                    }
                                }
                                System.out.println();
                                break;
                            case 4:
                                adminBack = true;
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Good Bye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}