package lw01.unguided;

public class LaptopRental extends Rental {
    public LaptopRental(String id, int days) {
        super(id, days);
    }

    @Override 
    public int calculateCharge() {
        int charge = 10000;
        return getDays() * 40000 + charge;
    }
    
    @Override
    public String label() {
        return "Laptop";
    }
}
