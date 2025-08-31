package br.edu.infnet.caiovincenzo.controller;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoGeral;
import br.edu.infnet.caiovincenzo.model.service.ProdutoGeralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtosGerais")
public class ProdutoGeralController {

    private final ProdutoGeralService produtoGeralService;

    public ProdutoGeralController(ProdutoGeralService produtoGeralService) {
        this.produtoGeralService = produtoGeralService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoGeral>> obterLista() {

        List<ProdutoGeral> lista = produtoGeralService.obterLista();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoGeral> obterPorId(@PathVariable Integer id) {
        ProdutoGeral produto = produtoGeralService.obterPorId(id);
        if (produto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        produtoGeralService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoGeral> incluir(@RequestBody ProdutoGeral produto) {
        ProdutoGeral novoProduto = produtoGeralService.incluir(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoGeral> alterar(@PathVariable Integer id, @RequestBody ProdutoGeral produto) {
        ProdutoGeral produtoAlterado = produtoGeralService.alterar(id, produto);

        return ResponseEntity.ok(produtoAlterado);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ProdutoGeral> alterarCategoria(@PathVariable Integer id, @RequestBody String categoria) {
        ProdutoGeral produtoAlterado = produtoGeralService.trocarCategoria(id, categoria);

        return ResponseEntity.ok(produtoAlterado);
    }

}
