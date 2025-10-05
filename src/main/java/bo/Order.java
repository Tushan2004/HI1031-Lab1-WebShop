package bo;

public class Order {
    private int id;
    private int userId;
    private int productId;
    private String name;
    private String descr;
    private double price;
    private int quantity;
    private double total;

    public Order() {}

    public Order(int id, int userId, int productId, String name, String descr,
                 double price, int quantity, double total) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.name = name;
        this.descr = descr;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    // Getters och setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescr() { return descr; }
    public void setDescr(String descr) { this.descr = descr; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
