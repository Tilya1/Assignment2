Assignment #2 – Banking System
Student: Aktilek Zhumagaliyev
Group: IT-2503

Structure

BankAccount.java – data model
assignment.java – Tasks 1–5 (LinkedList, Stack, Queue)
Main.java – Task 6 (Array) + launches menu
MiniBankingMenu.java – Part 3 (Bank / ATM / Admin menus)


Tasks
Task 1 – LinkedList<BankAccount> stores accounts. Add / display / search by username.
Task 2 – Deposit and withdraw update balance inside LinkedList.
Task 3 – Stack<String> stores transaction history. Supports peek (view last) and pop (undo).
Task 4 – Queue<String> processes bill payments in FIFO order.
Task 5 – Queue<BankAccount> holds account opening requests. Admin approves → account moves to LinkedList.
Task 6 – BankAccount[3] fixed array with 3 predefined accounts.
Part 3 – Menu with Bank, ATM, and Admin sections integrating all structures above.


Summary
The project simulates a basic banking system using core Java data structures.
LinkedList was chosen for accounts due to its dynamic size.
Stack fits transaction history because of LIFO (undo = last action first).
Queue fits bill payments and account requests because of FIFO (first come, first served).
Array was used for the fixed physical storage task.
