package br.edu.infnet.caiovincenzo.controller;

import br.edu.infnet.caiovincenzo.model.domain.Item;
import br.edu.infnet.caiovincenzo.model.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itemsAlimenticios")
public class ItemController {


    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> obterLista() {
        List<Item> lista = itemService.obterLista();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> obterPorId(@PathVariable Integer id) {
        Item item = itemService.obterPorId(id);
        if (item == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(item);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        itemService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Item> incluir(@Valid  @RequestBody Item item) {
        Item novoItem = itemService.incluir(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Item> alterar(@PathVariable Integer id, @RequestBody Item item) {
        Item itemAlterado = itemService.alterar(id, item);

        return ResponseEntity.ok(itemAlterado);
    }


}
