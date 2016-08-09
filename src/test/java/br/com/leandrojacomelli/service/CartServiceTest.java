package br.com.leandrojacomelli.service;

import br.com.leandrojacomelli.ShopSeekApplication;
import br.com.leandrojacomelli.domain.Cart;
import br.com.leandrojacomelli.web.dto.CartDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopSeekApplication.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class CartServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Test
    public void findCreateCart() {
        CartDTO dto = new CartDTO();
        String name = "customer";
        dto.setCustomer(name);
        Cart cart = cartService.newCart(dto);
        assertNotNull(cart.getId());
        assertEquals(name, cart.getCustomer().getName());

    }

    @Test
    public void findAddItemToCart() {
        CartDTO dto = new CartDTO();
        String name = "customer";
        dto.setCustomer(name);
        Cart cart = cartService.newCart(dto);
        assertNotNull(cart.getId());
        assertEquals(name, cart.getCustomer().getName());
        dto.setSku("mbp");
        Cart addItemCart = cartService.addItemCart(dto);
        assertEquals(2, addItemCart.getItems().size());
    }

    @Test
    public void testAddItems1() {
        CartDTO dto = new CartDTO();
        String name = "customer";
        dto.setCustomer(name);
        Cart cart = cartService.newCart(dto);
        assertNotNull(cart.getId());
        assertEquals(name, cart.getCustomer().getName());
        dto.setSku("atv");
        cartService.addItemCart(dto);

        dto.setSku("atv");
        cartService.addItemCart(dto);

        dto.setSku("atv");
        cartService.addItemCart(dto);

        dto.setSku("vga");
        Cart addItemCart = cartService.addItemCart(dto);

        assertEquals(249.00, addItemCart.getTotal().doubleValue(), 0.001);
    }

    @Test
    public void testAddItems2() {
        CartDTO dto = new CartDTO();
        String name = "customer";
        dto.setCustomer(name);
        Cart cart = cartService.newCart(dto);
        assertNotNull(cart.getId());
        assertEquals(name, cart.getCustomer().getName());


        dto.setSku("atv");
        cartService.addItemCart(dto);

        dto.setSku("ipd");
        cartService.addItemCart(dto);

        dto.setSku("ipd");
        cartService.addItemCart(dto);

        dto.setSku("atv");
        cartService.addItemCart(dto);

        dto.setSku("ipd");
        cartService.addItemCart(dto);

        dto.setSku("ipd");
        cartService.addItemCart(dto);

        dto.setSku("ipd");
        Cart addItemCart = cartService.addItemCart(dto);

        assertEquals(BigDecimal.valueOf(2718.95), addItemCart.getTotal());
    }

    @Test
    public void testAddItems3() {
        CartDTO dto = new CartDTO();
        String name = "customer";
        dto.setCustomer(name);
        Cart cart = cartService.newCart(dto);
        assertNotNull(cart.getId());
        assertEquals(name, cart.getCustomer().getName());

        dto.setSku("mbp");
        cartService.addItemCart(dto);

        dto.setSku("ipd");
        Cart addItemCart = cartService.addItemCart(dto);

        assertEquals(BigDecimal.valueOf(1949.98), addItemCart.getTotal());
    }

}
