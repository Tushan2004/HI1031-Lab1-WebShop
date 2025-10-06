package bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart containing multiple items (CartItem).
 * Provides methods to add, remove items and calculate the total price.
 */
public class Cart {

    /** List of items currently in the cart. */
    private List<CartItem> items = new ArrayList<>();

    /**
     * Adds an item to the cart.
     * If the product already exists in the cart, its quantity is increased.
     *
     * @param item the CartItem to add
     */
    public void addItem(CartItem item) {
        for (CartItem i : items) {
            if (i.getProduct().getId() == item.getProduct().getId()) {
                i.setQuantity(i.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    /**
     * Removes an item from the cart based on the product ID.
     *
     * @param productId the ID of the product to remove
     */
    public void removeItem(int productId) {
        items.removeIf(i -> i.getProduct().getId() == productId);
    }

    /**
     * Returns the list of items in the cart.
     *
     * @return list of CartItem objects
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Calculates the total price of all items in the cart.
     *
     * @return total price as double
     */
    public double getTotal() {
        return items.stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();
    }
}
