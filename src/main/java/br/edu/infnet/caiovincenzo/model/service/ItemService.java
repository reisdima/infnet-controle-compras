package br.edu.infnet.caiovincenzo.model.service;

import br.edu.infnet.caiovincenzo.model.domain.Item;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeInvalidaException;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeNaoEncontradaException;
import br.edu.infnet.caiovincenzo.model.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements CrudService<Item, Integer> {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public Item incluir(Item item) {
        validarItem(item);
        return itemRepository.save(item);
    }

    @Override
    @Transactional
    public Item alterar(Integer id, Item item) {
        obterPorId(id);
        validarItem(item);

        item.setId(id);
        return itemRepository.save(item);
    }

    @Override
    public Item obterPorId(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("O ID para alteração é inválido!");
        }
        return itemRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("O item com ID " + id + " não foi encontrado!"));
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        Item item = obterPorId(id);

        itemRepository.delete(item);
    }

    @Override
    public List<Item> obterLista() {
        return itemRepository.findAll();
    }

    private void validarItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode estar nulo!");
        }
        if (item.getProduto() == null || item.getPreco() == null) {
            throw new EntidadeInvalidaException("O produto do item nem seu valor podem estar vazios!");
        }
    }
}
