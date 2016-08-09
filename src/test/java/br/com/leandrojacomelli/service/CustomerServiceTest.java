package br.com.leandrojacomelli.service;

import br.com.leandrojacomelli.ShopSeekApplication;
import br.com.leandrojacomelli.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopSeekApplication.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void findCreateCustomer() {
        String name = "teste";
        Customer teste = customerService.findOrCreate(name);
        Assert.assertEquals(name, teste.getName());
        Assert.assertNotNull(teste.getId());
    }

    @Test
    public void findCreateAndFindCustomer() {
        String name = "teste";
        Customer teste = customerService.findOrCreate(name);
        Assert.assertEquals(name, teste.getName());
        Assert.assertNotNull(teste.getId());

        Customer found = customerService.findOrCreate(name);

        Assert.assertEquals(teste, found);
        Assert.assertEquals(teste.getId(), found.getId());

    }


}
