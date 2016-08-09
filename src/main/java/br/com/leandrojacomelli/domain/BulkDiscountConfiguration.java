package br.com.leandrojacomelli.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bulk")
public class BulkDiscountConfiguration extends DiscountConfiguration {

    @Column(name = "minimum_quantity")
    private Integer minimumQuantity;

    @Column(name = "new_price")
    private BigDecimal newPrice;

    public BulkDiscountConfiguration() {
    }

    public BulkDiscountConfiguration(Product product, Integer minimumQuantity, BigDecimal newPrice, Boolean active) {
        super("Bulk Discount", product, active, DiscountType.BULK);
        this.minimumQuantity = minimumQuantity;
        this.newPrice = newPrice;
    }


    @Override
    public void applyDiscount(final Cart cart) {
        cart.getItems()
                .stream()
                .filter(itemLine -> itemLine.getProduct().equals(product))
                .filter(itemLine -> itemLine.getPayedQuantity() >= minimumQuantity)
                .findFirst()
                .ifPresent(
                        itemLine -> itemLine.setPrice(newPrice)
                );
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }
}
