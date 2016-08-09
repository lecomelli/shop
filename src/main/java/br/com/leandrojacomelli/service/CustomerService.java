package br.com.leandrojacomelli.service;

import br.com.leandrojacomelli.domain.Customer;
import br.com.leandrojacomelli.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer findOrCreate(String name) {
        Optional<Customer> customer = customerRepository.findByName(name);
        if (!customer.isPresent()) {
            return customerRepository.save(new Customer(name));
        } else {
            return customer.get();
        }
    }


}
