package bo;

/**
 * Represents an order placed by a user in the webshop.
 * Contains information about the user, product, quantity, price, and total.
 */
public class Order {

    /** Unique identifier for the order. */
    private int id;

    /** ID of the user who placed the order. */
    private int userId;

    /** ID of the product in the order. */
    private int productId;

    /** Name of the product. */
    private String name;

    /** Description of the product. */
    private String descr;

    /** Price of a single unit of the product. */
    private double price;

    /** Quantity of the product in this order. */
    private int quantity;

    /** Total price for this product (price * quantity). */
    private double total;

    /** Default constructor. */
    public Order() {}

    /**
     * Constructs a new Order with all fields specified.
     *
     * @param id the order ID
     * @param userId the user ID who placed the order
     * @param productId the product ID
     * @param name the product name
     * @param descr the product description
     * @param price the price per unit
     * @param quantity the quantity ordered
     * @param total the total price (price * quantity)
     */
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

    /** @return the order ID */
    public int getId() { return id; }

    /** @param id the order ID to set */
    public void setId(int id) { this.id = id; }

    /** @return the user ID who placed the order */
    public int getUserId() { return userId; }

    /** @param userId the user ID to set */
    public void setUserId(int userId) { this.userId = userId; }

    /** @return the product ID */
    public int getProductId() { return productId; }

    /** @param productId the product ID to set */
    public void setProductId(int productId) { this.productId = productId; }

    /** @return the product name */
    public String getName() { return name; }

    /** @param name the product name to set */
    public void setName(String name) { this.name = name; }

    /** @return the product description */
    public String getDescr() { return descr; }

    /** @param descr the product description to set */
    public void setDescr(String descr) { this.descr = descr; }

    /** @return the price per unit */
    public double getPrice() { return price; }

    /** @param price the price per unit to set */
    public void setPrice(double price) { this.price = price; }

    /** @return the quantity ordered */
    public int getQuantity() { return quantity; }

    /** @param quantity the quantity to set */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /** @return the total price for this product */
    public double getTotal() { return total; }

    /** @param total the total price to set */
    public void setTotal(double total) { this.total = total; }
}
