package dtos;


public class RentalDto {
    private int inventory;
    private int customer;
    private int staff;
    private String date;

    public int getInventory() {
        return this.inventory;
    }

    public void setInventory(final int inventory) {
        this.inventory = inventory;
    }

    public int getCustomer() {
        return this.customer;
    }

    public void setCustomer(final int customer) {
        this.customer = customer;
    }

    public int getStaff() {
        return this.staff;
    }

    public void setStaff(final int staff) {
        this.staff = staff;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(final String date) {
        this.date = date;
    }
}
