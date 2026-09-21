package lw01.unguided;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("Rentals.txt"));
            int totalRecords = sc.nextInt();
            
            Rental[] rentals = new Rental[totalRecords];
            int[] unitsList = new int[totalRecords]; 

            for (int i = 0; i < totalRecords; i++) {
                String type = sc.next();
                String id = sc.next();
                int days = sc.nextInt();
                int units = sc.nextInt(); 

                if (type.equals("LAPTOP")) {
                    rentals[i] = new LaptopRental(id, days);
                } else if (type.equals("PROJECTOR")) {
                    rentals[i] = new ProjectorRental(id, days);
                }
                unitsList[i] = units;
            }
            sc.close();

            // Print each rental using a single loop
            for (int i = 0; i < rentals.length; i++) {
                System.out.println(rentals[i].getId() + " | " + rentals[i].label() + " | " + rentals[i].calculateCharge(unitsList[i]));
            }
        }
}