package br.edu.infnet.caiovincenzo.model.repository;

import br.edu.infnet.caiovincenzo.model.domain.Produto;
import br.edu.infnet.caiovincenzo.model.domain.ProdutoAlimenticio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findByCodigoDeBarras(String codigoDeBarras);
}
