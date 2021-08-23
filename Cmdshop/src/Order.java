import java.util.Date;

public class Order {
    private User user;
    private Product products[];
    private int amount;
    private float totalPrice;
    private float finalpay;
    private Date orderDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {//把订单和用户关联
        this.user = user;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getFinalpay() {
        return finalpay;
    }

    public void setFinalpay(float finalpay) {
        this.finalpay = finalpay;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
