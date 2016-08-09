package br.com.leandrojacomelli.repository;

import br.com.leandrojacomelli.domain.BulkDiscountConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulkDiscountRepository extends JpaRepository<BulkDiscountConfiguration, Long>, DiscountRepository {



}
