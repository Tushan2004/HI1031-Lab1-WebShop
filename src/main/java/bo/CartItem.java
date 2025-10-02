package bo;

public class CartItem {
    private Item product;
    private int quantity;

    public CartItem(Item product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Item getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int q) {
        this.quantity = q;
    }
}
