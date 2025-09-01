package br.edu.infnet.caiovincenzo.model.service;

import br.edu.infnet.caiovincenzo.model.domain.Produto;
import br.edu.infnet.caiovincenzo.model.domain.ProdutoAlimenticio;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeInvalidaException;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeNaoEncontradaException;
import br.edu.infnet.caiovincenzo.model.repository.ProdutoRepository;
import br.edu.infnet.caiovincenzo.model.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto obterProdutoPorCodigoDeBarras(String codigoDeBarras) {
        if (codigoDeBarras == null || codigoDeBarras.isEmpty()) {
            throw new IllegalArgumentException("Codigo de barras informado é inválido");
        }
        return produtoRepository.findByCodigoDeBarras(codigoDeBarras).orElseThrow(() -> new EntidadeNaoEncontradaException("O produto de codigo de barras " + codigoDeBarras + " não foi encontrado!"));

    }
}
