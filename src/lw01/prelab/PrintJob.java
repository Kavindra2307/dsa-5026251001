package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    public String id;
    public int pages;

    public PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be positive.");
        }
        this.id = id;
        this.pages = pages;
    }

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("Copies must be positive.");
        }
        return copies * calculateCharge(); 
    }

    public abstract String label();

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}