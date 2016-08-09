package br.com.leandrojacomelli.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "by_x_pay_for_y")
public class ByXPayForYDiscountConfiguration extends DiscountConfiguration {

    @Column(name = "by_x")
    private Integer byX;

    @Column(name = "pay_for_y")
    private Integer payForY;


    public ByXPayForYDiscountConfiguration() {
        super();
    }

    public ByXPayForYDiscountConfiguration(Integer byX, Integer payForY, Product product, Boolean active) {
        super("By X Pay For Y Discount", product, active, DiscountType.BY_X_PAY_FOR_Y);
        this.byX = byX;
        this.payForY = payForY;
        this.product = product;

    }


    @Override
    public void applyDiscount(final Cart cart) {
        cart.getItems()
                .stream()
                .filter(itemLine -> itemLine.getProduct().equals(product))
                .filter(itemLine -> itemLine.getPayedQuantity() >= byX)
                .findFirst()
                .ifPresent(
                        itemLine -> {
                            Integer factor = (itemLine.getPayedQuantity() / byX) + (itemLine.getPayedQuantity() % byX);
                            itemLine.setPayedQuantity(factor * payForY);
                        }
                );

    }


    public Integer getByX() {
        return byX;
    }

    public void setByX(Integer byX) {
        this.byX = byX;
    }

    public Integer getPayForY() {
        return payForY;
    }

    public void setPayForY(Integer payForY) {
        this.payForY = payForY;
    }
}
