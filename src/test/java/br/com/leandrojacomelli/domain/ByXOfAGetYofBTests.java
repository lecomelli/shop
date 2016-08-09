package br.com.leandrojacomelli.domain;

import br.com.leandrojacomelli.ShopSeekApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopSeekApplication.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class ByXOfAGetYofBTests {

    @Test
    public void ShouldBy1WithBy1xGet1FreeDiscount() {
        Cart cart = new Cart();

        BigDecimal price = BigDecimal.valueOf(1399.99);
        Product mbp = new Product("mbp", "MacBook Pro", price);
        Product vga = new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00));

        ItemLine line = new ItemLine(cart, mbp, 1, 1, price);
        cart.getItems().add(line);

        DiscountConfiguration discount = new ByXOfAGetYOfBDiscountConfiguration(1, mbp, 1, vga, true);
        discount.applyDiscount(cart);

        assertEquals(cart.getItems().size(), 2);

        Optional<ItemLine> line1 = cart.getItems().stream().filter(itemLine -> itemLine.getProduct().equals(mbp)).findFirst();
        Optional<ItemLine> line2 = cart.getItems().stream().filter(itemLine -> itemLine.getProduct().equals(vga)).findFirst();

        assertTrue(line1.isPresent());
        assertEquals(mbp, line1.get().getProduct());
        assertEquals(new Integer(1), line1.get().getCartQuantity());

        assertTrue(line2.isPresent());
        assertEquals(vga, line2.get().getProduct());
        assertEquals(new Integer(1), line2.get().getCartQuantity());

    }

    @Test
    public void ShouldBy2WithBy2Get1FreeDiscount() {
        Cart cart = new Cart();

        BigDecimal price = BigDecimal.valueOf(1399.99);
        Product mbp = new Product("mbp", "MacBook Pro", price);
        Product vga = new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00));

        ItemLine line = new ItemLine(cart, mbp, 2, 2, price);
        cart.getItems().add(line);

        DiscountConfiguration discount = new ByXOfAGetYOfBDiscountConfiguration(2, mbp, 1, vga, true);
        discount.applyDiscount(cart);

        assertEquals(cart.getItems().size(), 2);

        Optional<ItemLine> line1 = cart.getItems().stream().filter(itemLine -> itemLine.getProduct().equals(mbp)).findFirst();
        Optional<ItemLine> line2 = cart.getItems().stream().filter(itemLine -> itemLine.getProduct().equals(vga)).findFirst();

        assertTrue(line1.isPresent());
        assertEquals(mbp, line1.get().getProduct());
        assertEquals(new Integer(2), line1.get().getCartQuantity());

        assertTrue(line2.isPresent());
        assertEquals(vga, line2.get().getProduct());
        assertEquals(new Integer(1), line2.get().getCartQuantity());

    }


    @Test
    public void ShouldBy3WithBy2Get1FreeDiscount() {
        Cart cart = new Cart();

        BigDecimal price = BigDecimal.valueOf(1399.99);
        Product mbp = new Product("mbp", "MacBook Pro", price);
        Product vga = new Product("vga", "VGA adapter", BigDecimal.valueOf(30.00));

        ItemLine line = new ItemLine(cart, mbp, 3, 3, price);
        cart.getItems().add(line);

        DiscountConfiguration discount = new ByXOfAGetYOfBDiscountConfiguration(2, mbp, 1, vga, true);
        discount.applyDiscount(cart);

        assertEquals(cart.getItems().size(), 2);
        Optional<ItemLine> line1 = cart.getItems().stream().filter(itemLine -> itemLine.getProduct().equals(mbp)).findFirst();
        Optional<ItemLine> line2 = cart.getItems().stream().filter(itemLine -> itemLine.getProduct().equals(vga)).findFirst();

        assertTrue(line1.isPresent());
        assertEquals(mbp, line1.get().getProduct());
        assertEquals(new Integer(3), line1.get().getCartQuantity());

        assertTrue(line2.isPresent());
        assertEquals(vga, line2.get().getProduct());
        assertEquals(new Integer(1), line2.get().getCartQuantity());

    }


}
