package bo;

public class Item {
    private int id;
    private String name;
    private String descr;
    private double price;

    public Item(int id, String name, String descr, double price) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescr() {
        return descr;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
