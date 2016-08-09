package br.com.leandrojacomelli.service;

import br.com.leandrojacomelli.domain.DiscountConfiguration;
import br.com.leandrojacomelli.domain.Product;
import br.com.leandrojacomelli.repository.BulkDiscountRepository;
import br.com.leandrojacomelli.repository.ByXOfAGetYOfBDiscountRepository;
import br.com.leandrojacomelli.repository.ByXPayForYDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

@Service
public class DiscountService {

    @Autowired
    private BulkDiscountRepository bulkDiscountRepository;

    @Autowired
    private ByXOfAGetYOfBDiscountRepository byXOfAGetYOfBDiscountRepository;

    @Autowired
    private ByXPayForYDiscountRepository byXPayForYDiscountRepository;

    public Stream<DiscountConfiguration> findActiveDiscounts(Product product) {
        Stream<DiscountConfiguration> byXOfAGetYOfB = byXOfAGetYOfBDiscountRepository.findByProductAndActiveTrue(product);

        Stream<DiscountConfiguration> byXPayForY = byXPayForYDiscountRepository.findByProductAndActiveTrue(product);

        Stream<DiscountConfiguration> bulk = bulkDiscountRepository.findByProductAndActiveTrue(product);

        return concat(concat(byXOfAGetYOfB, bulk), byXPayForY);
    }
}
