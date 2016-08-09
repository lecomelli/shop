package br.com.leandrojacomelli.domain;

import br.com.leandrojacomelli.ShopSeekApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopSeekApplication.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class ByXPayForYTests {

    @Test
    public void ShouldBy4PayFor3() {
        Cart cart = new Cart();

        BigDecimal price = BigDecimal.valueOf(549.99);
        Product product = new Product("ipd", "Super iPad", price);

        ItemLine line = new ItemLine(cart, product, 4, 4, price);
        cart.getItems().add(line);

        DiscountConfiguration discount = new ByXPayForYDiscountConfiguration(4, 3, product, true);
        discount.applyDiscount(cart);

        Assert.assertEquals(price.multiply(BigDecimal.valueOf(3)), cart.getTotal());
    }

    @Test
    public void ShouldBy4PayFor2() {
        Cart cart = new Cart();

        BigDecimal price = BigDecimal.valueOf(549.99);
        Product product = new Product("ipd", "Super iPad", price);

        ItemLine line = new ItemLine(cart, product, 4, 4, price);
        cart.getItems().add(line);

        DiscountConfiguration discount = new ByXPayForYDiscountConfiguration(2, 1, product, true);
        discount.applyDiscount(cart);

        Assert.assertEquals(price.multiply(BigDecimal.valueOf(2)), cart.getTotal());
    }

    @Test
    public void ShouldBy3PayFor1() {
        Cart cart = new Cart();

        BigDecimal price = BigDecimal.valueOf(549.99);
        Product product = new Product("ipd", "Super iPad", price);

        ItemLine line = new ItemLine(cart, product, 3, 3, price);
        cart.getItems().add(line);

        DiscountConfiguration discount = new ByXPayForYDiscountConfiguration(2, 1, product, true);
        discount.applyDiscount(cart);

        Assert.assertEquals(price.multiply(BigDecimal.valueOf(2)), cart.getTotal());
    }


}
