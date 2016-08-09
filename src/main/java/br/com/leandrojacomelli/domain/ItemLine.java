package br.com.leandrojacomelli.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_line")
public class ItemLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name= "cart_quantity")
    private Integer cartQuantity;

    @Column(name = "payed_quantity")
    private Integer payedQuantity;

    private BigDecimal price;

    public ItemLine() {
        super();
    }

    public ItemLine(Cart cart, Product product, Integer totalQuantity, Integer payedQuantity, BigDecimal price) {
        this.cart = cart;
        this.product = product;
        this.cartQuantity = totalQuantity;
        this.payedQuantity = payedQuantity;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer totalQuantity) {
        this.cartQuantity = totalQuantity;
    }

    public Integer getPayedQuantity() {
        return payedQuantity;
    }

    public void setPayedQuantity(Integer payedQuantity) {
        this.payedQuantity = payedQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
