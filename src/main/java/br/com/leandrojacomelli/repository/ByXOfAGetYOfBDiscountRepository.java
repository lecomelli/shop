package br.com.leandrojacomelli.repository;

import br.com.leandrojacomelli.domain.ByXOfAGetYOfBDiscountConfiguration;
import br.com.leandrojacomelli.domain.DiscountConfiguration;
import br.com.leandrojacomelli.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ByXOfAGetYOfBDiscountRepository extends JpaRepository<ByXOfAGetYOfBDiscountConfiguration, Long> , DiscountRepository {

}
