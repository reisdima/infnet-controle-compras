package br.edu.infnet.caiovincenzo.model.service;

import br.edu.infnet.caiovincenzo.model.domain.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProdutoService implements CrudService<Produto, Integer> {

    private final Map<Integer, Produto> mapa = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public Produto incluir(Produto produto) {
        validarProduto(produto);
        produto.setId(nextId.getAndIncrement());
        mapa.put(produto.getId(), produto);
        return produto;
    }

    @Override
    public Produto alterar(Integer id, Produto produto) {
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
    public Produto obterPorId(Integer id) {
        if (!mapa.containsKey(id)) {
            throw new IllegalArgumentException("O produto de id " + id + " não foi encontrado.");
        }
        return mapa.get(id);
    }

    @Override
    public void excluir(Integer id) {
        if(!mapa.containsKey(id)) {
            throw new IllegalArgumentException("O produto de id " + id + " não foi encontrado.");
        }

        mapa.remove(id);
    }

    @Override
    public List<Produto> obterLista() {
        return new ArrayList<>(mapa.values());
    }

    private void validarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode estar nulo!");
        }
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode estar vazio!");
        }
    }
}
