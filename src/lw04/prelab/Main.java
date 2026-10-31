package lw04.prelab;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Read file
        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("branches.txt")
        );

        int n = scanner.nextInt();
        int[] branches = new int[n];

        for (int i = 0; i < n; i++) {
            branches[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];

        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        // Insertion sort (descending), exactly 3 passes
        insertionSort(branches, 3);

        // Display array after 3 passes
        for (int i = 0; i < branches.length; i++) {
            System.out.print(branches[i]);
            if (i < branches.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        // Linear search for each target
        for (int target : targets) {

            int index = linearSearch(branches, target);

            if (index != -1) {
                System.out.println(target + ": FOUND at index " + index);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }

    // Insertion sort in descending order, stops after the given number of passes
    public static void insertionSort(int[] arr, int passes) {

        for (int i = 1; i <= passes && i < arr.length; i++) {

            int key = arr[i];
            int j = i - 1;

            // Shift smaller elements one position to the right
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Put key in the empty position
            arr[j + 1] = key;
        }
    }

    // Linear search, returns index of target or -1 if not found
    public static int linearSearch(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}