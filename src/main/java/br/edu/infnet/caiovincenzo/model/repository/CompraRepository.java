package br.edu.infnet.caiovincenzo.model.repository;

import br.edu.infnet.caiovincenzo.model.domain.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

    Optional<Compra> findByNotaFiscal(String notaFiscal);
}
