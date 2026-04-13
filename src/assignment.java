import java.util.*;

public class assignment {
    public static void run() {

        // Scanner для ввода пользователя
        Scanner scanner = new Scanner(System.in);

        // Task 1: LinkedList для хранения аккаунтов
        LinkedList<BankAccount> Accounts = new LinkedList<>();

        // Task 3: Stack для хранения истории транзакций (LIFO)
        Stack<String> transactions = new Stack<>();

        // Task 4: Queue для платежей (FIFO)
        Queue<String> billQueue = new LinkedList<>();

        // Task 5: Queue для заявок на создание аккаунта
        Queue<BankAccount> accountRequests = new LinkedList<>();

        int id = 0;

        while (true) {
            System.out.println("1.  Add account request to queue");
            System.out.println("2.  Display all accounts");
            System.out.println("3.  Search account by username");
            System.out.println("4.  Deposit money");
            System.out.println("5.  Withdraw money");
            System.out.println("6.  Undo last transaction");
            System.out.println("7.  Display last transaction");
            System.out.println("8.  Add bill payment request");
            System.out.println("9.  Process next bill payment");
            System.out.println("10. Display bill payment queue");
            System.out.println("11. Process request (for admins)");
            System.out.println("12. Display pending requests");
            System.out.println("13. Quit");
            System.out.print("Choose one: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // =======================
                // Task 5: Account Request Queue
                // =======================
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();

                    accountRequests.add(new BankAccount(++id, username, balance));
                    System.out.println("Account request added successfully to the queue");
                    System.out.println();
                    break;

                // =======================
                // Task 1: Display accounts (LinkedList)
                // =======================
                case 2:
                    int i = 1;
                    for (BankAccount account : Accounts) {
                        System.out.print(i + ". ");
                        account.information();
                        i++;
                    }
                    if (i == 1) System.out.println("No account found!");
                    System.out.println();
                    break;

                // =======================
                // Task 1: Search account
                // =======================
                case 3:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    boolean found = false;

                    for (BankAccount account : Accounts) {
                        if (account.getUsername().equals(username)) {
                            account.information();
                            found = true;
                        }
                    }

                    if (!found) System.out.println("No accounts found!");
                    System.out.println();
                    break;

                // =======================
                // Task 2: Deposit money
                // =======================
                case 4:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    boolean isFound = false;

                    for (BankAccount account : Accounts) {
                        if (account.getUsername().equals(username)) {
                            System.out.print("Deposit: ");
                            double deposit = scanner.nextDouble();
                            scanner.nextLine();

                            account.deposit(deposit);
                            isFound = true;

                            System.out.println("New balance: " + account.getBalance());

                            // Task 3: Добавляем в Stack
                            transactions.push("Deposit " + deposit + " to " + username);
                        }
                    }

                    if (!isFound) System.out.println("Username not found!");
                    System.out.println();
                    break;

                // =======================
                // Task 2: Withdraw money
                // =======================
                case 5:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    isFound = false;

                    for (BankAccount account : Accounts) {
                        if (account.getUsername().equals(username)) {
                            System.out.print("Withdraw: ");
                            double withdraw = scanner.nextDouble();
                            scanner.nextLine();

                            account.withdraw(withdraw);
                            isFound = true;

                            System.out.println("New balance: " + account.getBalance());

                            // Task 3: Stack
                            transactions.push("Withdraw " + withdraw + " from " + username);
                        }
                    }

                    if (!isFound) System.out.println("Username not found!");
                    System.out.println();
                    break;

                // =======================
                // Task 3: Undo transaction (Stack)
                // =======================
                case 6:
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions to undo!");
                        break;
                    }

                    String last = transactions.peek();

                    if (last.charAt(0) == 'W')
                        System.out.println("Undo → Withdraw removed");
                    else
                        System.out.println("Undo → Deposit removed");

                    transactions.pop();
                    System.out.println();
                    break;

                // =======================
                // Task 3: Show last transaction
                // =======================
                case 7:
                    if (transactions.isEmpty()) {
                        System.out.println("No transactions found!");
                        break;
                    }

                    System.out.println("Last transaction: " + transactions.peek());
                    System.out.println();
                    break;

                // =======================
                // Task 4: Add bill (Queue)
                // =======================
                case 8:
                    System.out.print("Enter bill payment request: ");
                    String request = scanner.nextLine();

                    billQueue.offer(request);
                    System.out.println("Added: " + request);
                    System.out.println();
                    break;

                // =======================
                // Task 4: Process bill (FIFO)
                // =======================
                case 9:
                    if (billQueue.isEmpty()) {
                        System.out.println("No bill payment request found!");
                        break;
                    }

                    System.out.println("Processing: " + billQueue.poll());
                    System.out.println();
                    break;

                // =======================
                // Task 4: Display bill queue
                // =======================
                case 10:
                    if (billQueue.isEmpty()) {
                        System.out.println("No bill payment request found!");
                        break;
                    }

                    System.out.println("Bill Payment Queue:");
                    int num = 1;
                    for (String bill : billQueue) {
                        System.out.println(num++ + ". " + bill);
                    }
                    System.out.println();
                    break;

                // =======================
                // Task 5: Process account requests (Admin)
                // =======================
                case 11:
                    if (accountRequests.isEmpty()) {
                        System.out.println("No request found!");
                        break;
                    }

                    Iterator<BankAccount> iter = accountRequests.iterator();
                    int idx = 1;

                    while (iter.hasNext()) {
                        BankAccount account = iter.next();

                        System.out.print(idx++ + ". ");
                        account.information();

                        System.out.print("Approve? (Y/N): ");
                        String c = scanner.nextLine();

                        if (c.equalsIgnoreCase("Y")) {
                            Accounts.add(account);
                            System.out.println("Account approved and added.");
                        } else {
                            System.out.println("Request rejected.");
                        }

                        iter.remove();
                    }

                    System.out.println();
                    break;

                // =======================
                // Task 5: Display pending requests
                // =======================
                case 12:
                    if (accountRequests.isEmpty()) {
                        System.out.println("No request found!");
                        break;
                    }

                    i = 1;
                    for (BankAccount account : accountRequests) {
                        System.out.print(i++ + ". ");
                        account.information();
                    }

                    System.out.println();
                    break;

                case 13:
                    System.out.println("Good Luck!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}