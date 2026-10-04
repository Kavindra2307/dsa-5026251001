package lw03.prelab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static void problem1() {
        InputStream stream = Main.class.getResourceAsStream("playlist.txt"); 
        Scanner scanner = new Scanner(stream);
        List<String> activePlaylist = new ArrayList<>(); 

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine().trim();
            if (currentLine.isEmpty()) continue;

            String[] segments = currentLine.split(" ", 2);
            String command = segments[0];

            if (command.equals("ADD")) {
                activePlaylist.add(segments[1]); 
            } 
            else if (command.equals("INSERT")) {
                String[] insertData = segments[1].split(" ", 2);
                int targetIndex = Integer.parseInt(insertData[0]);
                String trackName = insertData[1];
                
                activePlaylist.add(targetIndex, trackName); 
            } 
            else if (command.equals("REMOVE")) {
                activePlaylist.remove(segments[1]); 
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + activePlaylist.size()); 
        for (int idx = 0; idx < activePlaylist.size(); idx++) {
            System.out.println((idx + 1) + ": " + activePlaylist.get(idx));
        }
    }

    private static void problem2() {
        InputStream stream = Main.class.getResourceAsStream("participants.txt"); 
        Scanner scanner = new Scanner(stream);
        
        Set<String> participants = new LinkedHashSet<>(); 
        int duplicateTally = 0;

        while (scanner.hasNextLine()) {
            String attendee = scanner.nextLine().trim();
            if (attendee.isEmpty()) continue;

            boolean isNewEntry = participants.add(attendee);
            if (!isNewEntry) {
                duplicateTally++; 
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size()); 
        
        int position = 1;
        for (String person : participants) {
            System.out.println(position + ". " + person); 
            position++;
        }
        System.out.println("Duplicate registrations: " + duplicateTally);
    }

    private static void problem3() {
        InputStream stream = Main.class.getResourceAsStream("inventory.txt"); 
        Scanner scanner = new Scanner(stream);
        
        Map<String, Integer> stockMap = new LinkedHashMap<>();
        int failedOperations = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] elements = line.split(" ");
            String action = elements[0];
            String itemName = elements[1];
            int qty = Integer.parseInt(elements[2]);

            if (action.equals("ADD")) {
                stockMap.merge(itemName, qty, Integer::sum); 
            } 
            else if (action.equals("SELL")) {
                if (stockMap.containsKey(itemName) && stockMap.get(itemName) >= qty) { 
                    stockMap.merge(itemName, -qty, Integer::sum);
                } else {
                    failedOperations++; 
                }
            }
        }

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : stockMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue()); 
        }
        System.out.println("Failed sales: " + failedOperations); 

    }

    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
        System.out.println();
    }
}