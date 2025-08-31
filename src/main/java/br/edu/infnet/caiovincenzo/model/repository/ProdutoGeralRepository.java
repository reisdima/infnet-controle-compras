package br.edu.infnet.caiovincenzo.model.repository;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoGeral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoGeralRepository extends JpaRepository<ProdutoGeral, Integer> {
}
