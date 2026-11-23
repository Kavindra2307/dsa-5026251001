package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();
        Scanner lineSc = new Scanner(sc.nextLine());
        while (lineSc.hasNextInt()) {
            bst.insert(lineSc.nextInt());
        }
        lineSc.close();

        int target = sc.nextInt();
        sc.close();

        System.out.print("Preorder : ");
        bst.preOrder();

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Minimum : " + bst.findMin());
        System.out.println("Maximum : " + bst.findMax());
    }
}