package bo;

/**
 * Represents a product item in the webshop.
 * Stores product ID, name, description, and price.
 */
public class Item {

    /** Unique identifier of the product. */
    private int id;

    /** Name of the product. */
    private String name;

    /** Description of the product. */
    private String descr;

    /** Price of the product. */
    private double price;

    /**
     * Constructs a new Item with the specified details.
     *
     * @param id the unique ID of the product
     * @param name the name of the product
     * @param descr the description of the product
     * @param price the price of the product
     */
    public Item(int id, String name, String descr, double price) {
        this.id = id;
        this.name = name;
        this.descr = descr;
        this.price = price;
    }

    /**
     * Returns the product ID.
     *
     * @return product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the product name.
     *
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the product description.
     *
     * @return product description
     */
    public String getDescr() {
        return descr;
    }

    /**
     * Returns the product price.
     *
     * @return product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product name.
     *
     * @param name new name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the product description.
     *
     * @param descr new description of the product
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * Sets the product price.
     *
     * @param price new price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
