package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        final int MAX_BORROW = 2;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));

        LinkedList<String[]> reqlist = new LinkedList<>();
        LinkedList<String[]> booklist = new LinkedList<>();
        LinkedList<String[]> memberlist = new LinkedList<>();

        // Initialize books and starting stocks in booklist
        booklist.add(new String[]{"Kalkulus", "2"});
        booklist.add(new String[]{"Fisika", "1"});
        booklist.add(new String[]{"Statistika", "2"});

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();
        LinkedList<String[]> success = new LinkedList<>();

        // Read requests and initialize members on first appearance
        while (scanner.hasNext()) {
            String name = scanner.next();
            String bookTitle = scanner.next();

            // Store request as [name, bookTitle]
            reqlist.add(new String[]{name, bookTitle});

            // Check if member is already registered; if not, add with count 0
            boolean memberExists = false;
            for (String[] member : memberlist) {
                if (member[0].equals(name)) {
                    memberExists = true;
                    break;
                }
            }

            if (!memberExists) {
                memberlist.add(new String[]{name, "0"});
            }
        }

        scanner.close();

        // Move all requests into Queue
        queue.addAll(reqlist);

        // Process requests in FIFO order
        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            String name = request[0];
            String bookTitle = request[1];

            // Locate member record
            String[] member = null;
            for (String[] data : memberlist) {
                if (data[0].equals(name)) {
                    member = data;
                    break;
                }
            }

            // Locate book record from booklist
            String[] book = null;
            for (String[] data : booklist) {
                if (data[0].equalsIgnoreCase(bookTitle)) {
                    book = data;
                    break;
                }
            }

            int stock = (book != null) ? Integer.parseInt(book[1]) : 0;
            int borrowed = (member != null) ? Integer.parseInt(member[1]) : 0;

            // Check borrowing limit and stock availability
            if (stock > 0 && borrowed < MAX_BORROW) {
                book[1] = String.valueOf(stock - 1);
                member[1] = String.valueOf(borrowed + 1);
                success.add(request);
            } else {
                failed.push(request);
            }
        }

        // Successfully processed requests
        System.out.println("=== Successfully Processed Requests ===");
        for (String[] req : success) {
            System.out.println(req[0] + " " + req[1]);
        }

        // Remaining Book Stock
        System.out.println("=== Remaining Book Stock ===");
        for (String[] book : booklist) {
            System.out.println(book[0] + ": " + book[1]);
        }

        // Failed requests (LIFO via Stack pop)
        System.out.println("=== Failed Requests ===");
        while (!failed.isEmpty()) {
            String[] req = failed.pop();
            System.out.println(req[0] + " " + req[1]);
        }
    }
}