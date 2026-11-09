package lw04.unguided;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("cards.txt")
        );

        int n = scanner.nextInt();
        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] requests = new int[m];

        for (int i = 0; i < m; i++) {
            requests[i] = scanner.nextInt();
        }

        scanner.close();

        sortCards(cards, 2);

        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i]);
            if (i < cards.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int target : requests) {

            int index = findCard(cards, target);

            if (index != -1) {
                System.out.println(target + ": FOUND at index " + index);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }

    public static void sortCards(int[] arr, int passes) {

        for (int i = 0; i < passes && i < arr.length - 1; i++) {

            int idx = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[idx]) {
                    idx = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }

    public static int findCard(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}