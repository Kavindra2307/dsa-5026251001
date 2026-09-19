package lw01.prelab;

public class ColourPrint extends PrintJob {
    
    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    public int calculateCharge() {
        int charge = 2000; 
        if (pages <= 10) {
            charge += pages * 1500;
        } else {
            charge += (10 * 1500) + ((pages - 10) * 1000);
        }
        return charge;
    }

    public String label() {
        return "Colour";
    }
}