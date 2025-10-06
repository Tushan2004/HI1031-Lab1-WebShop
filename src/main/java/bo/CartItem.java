package bo;

/**
 * Represents an item in the shopping cart.
 * Stores a reference to the product (Item) and the quantity selected.
 */
public class CartItem {

    /** The product associated with this cart item. */
    private Item product;

    /** Quantity of the product in the cart. */
    private int quantity;

    /**
     * Constructs a CartItem with the specified product and quantity.
     *
     * @param product the product to add to the cart
     * @param quantity the number of units of the product
     */
    public CartItem(Item product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Returns the product associated with this cart item.
     *
     * @return the product
     */
    public Item getProduct() {
        return product;
    }

    /**
     * Returns the quantity of the product in the cart.
     *
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the cart.
     *
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
