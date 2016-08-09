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
public class BulkTests {

    @Test
    public void ShouldBy4PayNewPrice() {
        Cart cart = new Cart();

        BigDecimal price = BigDecimal.valueOf(549.99);
        Product product = new Product("ipd", "Super iPad", price);

        ItemLine line = new ItemLine(cart, product, 4, 4, price);
        cart.getItems().add(line);

        BigDecimal newPrice = BigDecimal.valueOf(549.99);
        DiscountConfiguration discount = new BulkDiscountConfiguration(product, 4, newPrice, true);
        discount.applyDiscount(cart);

        Assert.assertEquals(newPrice.multiply(BigDecimal.valueOf(4)), cart.getTotal());
    }


}
