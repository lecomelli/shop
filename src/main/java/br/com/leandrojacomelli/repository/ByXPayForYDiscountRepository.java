package br.com.leandrojacomelli.repository;

import br.com.leandrojacomelli.domain.ByXPayForYDiscountConfiguration;
import br.com.leandrojacomelli.domain.DiscountConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ByXPayForYDiscountRepository extends JpaRepository<ByXPayForYDiscountConfiguration, Long>, DiscountRepository {

}
