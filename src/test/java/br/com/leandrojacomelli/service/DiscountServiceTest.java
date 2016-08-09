package br.com.leandrojacomelli.service;

import br.com.leandrojacomelli.ShopSeekApplication;
import br.com.leandrojacomelli.domain.DiscountConfiguration;
import br.com.leandrojacomelli.domain.Product;
import br.com.leandrojacomelli.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopSeekApplication.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class DiscountServiceTest {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findDiscountsByProduct(){
        Optional<Product> mpb = productRepository.findBySku("mbp");

        Stream<DiscountConfiguration> activeDiscounts = discountService.findActiveDiscounts(mpb.get());
        Optional<DiscountConfiguration> any = activeDiscounts.filter(product -> product.getProduct().equals(mpb.get())).findAny();
        Assert.assertTrue(any.isPresent());
    }

    @Test
    public void shouldNotFindDiscountsByProduct(){
        Optional<Product> mpb = productRepository.findBySku("vga");

        Stream<DiscountConfiguration> activeDiscounts = discountService.findActiveDiscounts(mpb.get());
        Optional<DiscountConfiguration> any = activeDiscounts.filter(product -> product.getProduct().equals(mpb.get())).findAny();
        Assert.assertFalse(any.isPresent());
    }

}
