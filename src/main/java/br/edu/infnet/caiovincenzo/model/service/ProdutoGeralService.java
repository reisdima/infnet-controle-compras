package br.edu.infnet.caiovincenzo.model.service;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoGeral;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.ProdutoInvalidoException;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.ProdutoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProdutoGeralService implements CrudService<ProdutoGeral, Integer> {

    private final Map<Integer, ProdutoGeral> mapa = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public ProdutoGeral incluir(ProdutoGeral produto) {
        validarProduto(produto);
        produto.setId(nextId.getAndIncrement());
        mapa.put(produto.getId(), produto);
        return produto;
    }

    @Override
    public ProdutoGeral alterar(Integer id, ProdutoGeral produto) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("O ID para alteração é inválido!");
        }
        validarProduto(produto);
        obterPorId(id);
        produto.setId(id);
        mapa.put(id, produto);

        return produto;
    }

    @Override
    public ProdutoGeral obterPorId(Integer id) {
        if (!mapa.containsKey(id)) {
            throw new ProdutoNaoEncontradoException("O produto de id " + id + " não foi encontrado.");
        }
        return mapa.get(id);
    }

    @Override
    public void excluir(Integer id) {
        if (!mapa.containsKey(id)) {
            throw new ProdutoNaoEncontradoException("O produto de id " + id + " não foi encontrado.");
        }

        mapa.remove(id);
    }

    @Override
    public List<ProdutoGeral> obterLista() {
        return new ArrayList<>(mapa.values());
    }

    public ProdutoGeral trocarCategoria(Integer id, String novaCategoria) {
        ProdutoGeral produto = obterPorId(id);
        produto.setCategoria(novaCategoria);
        mapa.put(id, produto);

        return null;

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
