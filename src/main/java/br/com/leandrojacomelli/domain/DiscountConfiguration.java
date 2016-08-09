package br.com.leandrojacomelli.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class DiscountConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "discount_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private DiscountType type;

    @ManyToOne
    @JoinColumn(name = "product_id")
    protected Product product;


    public DiscountConfiguration() {
    }

    public DiscountConfiguration(String name, Product product, Boolean active, DiscountType type) {
        this.name = name;
        this.product = product;
        this.type = type;
        this.active = active;

    }


    public abstract void applyDiscount(Cart cart);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
