package com.example.pharmacy.model;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    private Double price;

    // Getters y setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double quantity) {
        this.price = quantity;
    }
}
