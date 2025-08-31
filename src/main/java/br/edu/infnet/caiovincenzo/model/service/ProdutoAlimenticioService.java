package br.edu.infnet.caiovincenzo.model.service;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoAlimenticio;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.ProdutoInvalidoException;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.ProdutoNaoEncontradoException;
import br.edu.infnet.caiovincenzo.model.repository.ProdutoAlimenticioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoAlimenticioService implements CrudService<ProdutoAlimenticio, Integer> {

    private final ProdutoAlimenticioRepository produtoAlimenticioRepository;

    public ProdutoAlimenticioService (ProdutoAlimenticioRepository produtoAlimenticioRepository) {
        this.produtoAlimenticioRepository = produtoAlimenticioRepository;
    }

    @Override
    public ProdutoAlimenticio incluir(ProdutoAlimenticio produto) {
        validarProduto(produto);
        return produtoAlimenticioRepository.save(produto);
    }

    @Override
    public ProdutoAlimenticio alterar(Integer id, ProdutoAlimenticio produto) {
        obterPorId(id);
        validarProduto(produto);

        produto.setId(id);
        return produtoAlimenticioRepository.save(produto);
    }

    @Override
    public ProdutoAlimenticio obterPorId(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("O ID para alteração é inválido!");
        }
        return produtoAlimenticioRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("O produto com ID " + id + " não foi encontrado!"));
    }

    @Override
    public void excluir(Integer id) {
        ProdutoAlimenticio produto = obterPorId(id);

        produtoAlimenticioRepository.delete(produto);
    }

    @Override
    public List<ProdutoAlimenticio> obterLista() {
        return produtoAlimenticioRepository.findAll();
    }

    public ProdutoAlimenticio trocarDataValidade(Integer id, Integer diasValidade) {
        ProdutoAlimenticio produto = obterPorId(id);
        produto.setDiasValidade(diasValidade);
        return produtoAlimenticioRepository.save(produto);
    }

    private void validarProduto(ProdutoAlimenticio produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode estar nulo!");
        }
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new ProdutoInvalidoException("O nome do produto não pode estar vazio!");
        }
    }
}
