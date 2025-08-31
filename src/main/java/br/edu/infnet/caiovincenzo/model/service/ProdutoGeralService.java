package br.edu.infnet.caiovincenzo.model.service;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoGeral;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.ProdutoInvalidoException;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.ProdutoNaoEncontradoException;
import br.edu.infnet.caiovincenzo.model.repository.ProdutoGeralRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoGeralService implements CrudService<ProdutoGeral, Integer> {

    private final ProdutoGeralRepository produtoGeralRepository;

    public ProdutoGeralService(ProdutoGeralRepository produtoGeralRepository) {
        this.produtoGeralRepository = produtoGeralRepository;
    }

    @Override
    public ProdutoGeral incluir(ProdutoGeral produto) {
        validarProduto(produto);
        return produtoGeralRepository.save(produto);
    }

    @Override
    public ProdutoGeral alterar(Integer id, ProdutoGeral produto) {
        obterPorId(id);
        validarProduto(produto);

        produto.setId(id);
        return produtoGeralRepository.save(produto);
    }

    @Override
    public ProdutoGeral obterPorId(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("O ID para alteração é inválido!");
        }
        return produtoGeralRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException("O produto com ID " + id + " não foi encontrado!"));
    }

    @Override
    public void excluir(Integer id) {
        ProdutoGeral produto = obterPorId(id);
        produtoGeralRepository.delete(produto);
    }

    @Override
    public List<ProdutoGeral> obterLista() {
        return produtoGeralRepository.findAll();
    }

    public ProdutoGeral trocarCategoria(Integer id, String novaCategoria) {
        ProdutoGeral produto = obterPorId(id);
        produto.setCategoria(novaCategoria);
        return produtoGeralRepository.save(produto);
    }

    private void validarProduto(ProdutoGeral produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode estar nulo!");
        }
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new ProdutoInvalidoException("O nome do produto não pode estar vazio!");
        }
    }


}
