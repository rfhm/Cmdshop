import java.util.Date;
import java.util.Map;

public class Order {
    private User user;
    private Product products[];
    private Map<Integer,Integer> ammount;
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

    public Map<Integer, Integer> getAmmount() {
        return ammount;
    }

    public void setAmmount(Map<Integer, Integer> ammount) {
        this.ammount = ammount;
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
