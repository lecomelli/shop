package br.com.leandrojacomelli.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static java.math.BigDecimal.valueOf;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ItemLine> items = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Cart() {
    }

    public Cart(Customer customer) {
        this.customer = customer;
    }


    public BigDecimal getTotal() {
        return items.stream()
                .map(itemLine -> itemLine.getPrice().multiply(valueOf(itemLine.getPayedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ItemLine> getItems() {
        return items;
    }

    public void setItems(Set<ItemLine> items) {
        this.items = items;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
