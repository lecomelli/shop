package br.com.leandrojacomelli.repository;

import br.com.leandrojacomelli.domain.ItemLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemLineRepository extends JpaRepository<ItemLine, Long> {
}
