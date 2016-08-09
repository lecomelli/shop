package br.com.leandrojacomelli.repository;

import br.com.leandrojacomelli.domain.DiscountConfiguration;
import br.com.leandrojacomelli.domain.Product;

import java.util.stream.Stream;


interface DiscountRepository {
    <T extends DiscountConfiguration> Stream<T> findByProductAndActiveTrue(Product product);
}
