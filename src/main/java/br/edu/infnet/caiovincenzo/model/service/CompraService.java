package br.edu.infnet.caiovincenzo.model.service;

import br.edu.infnet.caiovincenzo.model.domain.Compra;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeInvalidaException;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeNaoEncontradaException;
import br.edu.infnet.caiovincenzo.model.repository.CompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompraService implements CrudService<Compra, Integer> {
    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    @Transactional
    public Compra incluir(Compra compra) {
        validarCompra(compra);
        return compraRepository.save(compra);
    }

    @Override
    @Transactional
    public Compra alterar(Integer id, Compra compra) {
        obterPorId(id);
        validarCompra(compra);

        compra.setId(id);
        return compraRepository.save(compra);
    }

    @Override
    public Compra obterPorId(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("O ID para alteração é inválido!");
        }
        return compraRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("A compra com ID " + id + " não foi encontrada!"));
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        Compra compra = obterPorId(id);

        compraRepository.delete(compra);
    }

    @Override
    public List<Compra> obterLista() {
        return compraRepository.findAll();
    }

    @Transactional
    public Compra obterPorNotaFiscal(String notaFiscal) {
        if (notaFiscal == null || notaFiscal.isEmpty()) {
            throw new IllegalArgumentException("Nota Fiscal informada é inválida");
        }
        return compraRepository.findByNotaFiscal(notaFiscal).orElseThrow(() -> new EntidadeNaoEncontradaException("A compra de nota fiscal " + notaFiscal + " não foi encontrado!"));
    }

    private void validarCompra(Compra compra) {
        if (compra == null) {
            throw new IllegalArgumentException("O compra não pode estar nulo!");
        }
        if(compra.getDataDaCompra() == null) {
            compra.setDataDaCompra(LocalDate.now());
        }
    }
}
