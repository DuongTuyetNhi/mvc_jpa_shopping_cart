package shoppingcart.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderDetails")
public class OrderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetailsId")
    private int orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

    @Column(name = "quantity")
    private int quantity;

    // Constructors
    public OrderDetailsEntity() {
        // Default constructor
    }

    // Getters and Setters
    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // toString method
    @Override
    public String toString() {
        return "OrderDetailsEntity{" +
                "orderDetailsId=" + orderDetailsId +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
