package br.edu.infnet.caiovincenzo.model.repository;

import br.edu.infnet.caiovincenzo.model.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
