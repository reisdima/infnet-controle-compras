package br.edu.infnet.caiovincenzo.model.repository;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoAlimenticio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoAlimenticioRepository extends JpaRepository<ProdutoAlimenticio, Integer> {
}
