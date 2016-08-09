package br.com.leandrojacomelli.domain;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "by_x_of_a_get_y_of_b")
public class ByXOfAGetYOfBDiscountConfiguration extends DiscountConfiguration {


    @Column(name = "by_x")
    private Integer byX;

    @Column(name = "get_y")
    private Integer getY;
    @ManyToOne
    @JoinColumn(name = "product_b_id")
    private Product productB;

    public ByXOfAGetYOfBDiscountConfiguration() {
    }


    public ByXOfAGetYOfBDiscountConfiguration(Integer byX, Product productA, Integer getY, Product productB, Boolean active) {
        super("By X of A get Y of B Discount", productA, active, DiscountType.BY_X_OF_A_GET_Y_OF_B);
        this.byX = byX;
        this.getY = getY;
        this.productB = productB;
    }


    @Override
    public void applyDiscount(final Cart cart) {
        cart.getItems()
                .stream()
                .filter(itemLine -> itemLine.getProduct().equals(product))
                .filter(itemLine -> itemLine.getPayedQuantity() >= byX)
                .findFirst().ifPresent(
                itemLine -> {
                    Integer bonus = (itemLine.getPayedQuantity() / byX) * getY;

                    Optional<ItemLine> item = cart.getItems().stream().filter(itemLine1 -> itemLine1.getProduct().equals(productB)).findFirst();

                    if (item.isPresent()) {
                        ItemLine cartProductB = item.get();

                        if (cartProductB.getPayedQuantity() >= bonus) {
                            int payedQuantity = cartProductB.getPayedQuantity() - bonus;
                            cartProductB.setPayedQuantity(payedQuantity);

                        } else {
                            if (cartProductB.getPayedQuantity() > bonus) {
                                cartProductB.setCartQuantity(bonus - cartProductB.getPayedQuantity());
                            } else {
                                cartProductB.setCartQuantity(cartProductB.getPayedQuantity() - bonus);
                            }
                        }
                    } else {
                        cart.getItems().add(new ItemLine(cart, productB, bonus, 0, productB.getPrice()));
                    }
                }
        );

    }

    public Integer getByX() {
        return byX;
    }

    public void setByX(Integer byX) {
        this.byX = byX;
    }

    public Integer getGetY() {
        return getY;
    }

    public void setGetY(Integer getY) {
        this.getY = getY;
    }

    public Product getProductB() {
        return productB;
    }

    public void setProductB(Product productB) {
        this.productB = productB;
    }
}
