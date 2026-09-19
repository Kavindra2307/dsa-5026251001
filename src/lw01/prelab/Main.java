package lw01.prelab;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(Main.class.getResourceAsStream("jobs.txt"));
        ArrayList<PrintJob> jobs = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String id = sc.next();
            int pages = sc.nextInt();
            // int copies = sc.nextInt();
            // There is no copies in the input file

            if (type.equals("MONO")) {
                jobs.add(new MonoPrint(id, pages));
            } else if (type.equals("COLOUR")) {
                jobs.add(new ColourPrint(id, pages));
            }
        }
        sc.close();

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}
