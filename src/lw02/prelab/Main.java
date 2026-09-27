package lw02.prelab;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Read file directly from resources without checked exceptions
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));

        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        // 1. Read transactions and record customers
        while (scanner.hasNext()) {
            String name = scanner.next();
            String type = scanner.next();
            String amount = scanner.next();

            // Store transaction in the list
            transactionList.add(new String[]{name, type, amount});

            // Check if customer already exists
            boolean isRegistered = false;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    isRegistered = true;
                    break;
                }
            }

            // If not found, add customer with initial balance 0
            if (!isRegistered) {
                customerList.add(new String[]{name, "0"});
            }
        }
        scanner.close();

        // 2. Queue for FIFO processing and Stack for failed withdrawals
        Queue<String[]> processQueue = new LinkedList<>();
        Stack<String[]> failedStack = new Stack<>();

        // Move all transactions into the queue
        for (String[] t : transactionList) {
            processQueue.add(t);
        }

        // 3. Process transactions in FIFO order
        while (!processQueue.isEmpty()) {
            String[] currentTx = processQueue.poll();
            String txName = currentTx[0];
            String txType = currentTx[1];
            int txAmount = Integer.parseInt(currentTx[2]);

            // Find matching customer
            for (String[] cust : customerList) {
                if (cust[0].equals(txName)) {
                    int balance = Integer.parseInt(cust[1]);

                    if (txType.equalsIgnoreCase("DEPOSIT")) {
                        balance += txAmount;
                        cust[1] = String.valueOf(balance);
                    } else if (txType.equalsIgnoreCase("WITHDRAW")) {
                        if (balance >= txAmount) {
                            balance -= txAmount;
                            cust[1] = String.valueOf(balance);
                        } else {
                            // Insufficient funds -> push to stack
                            failedStack.push(currentTx);
                        }
                    }
                    break;
                }
            }
        }

        // 4. Output final balances
        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        // 5. Output failed transactions in LIFO order
        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failedTx = failedStack.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}