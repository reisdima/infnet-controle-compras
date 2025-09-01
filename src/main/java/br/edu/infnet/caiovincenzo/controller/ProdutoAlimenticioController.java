package br.edu.infnet.caiovincenzo.controller;

import br.edu.infnet.caiovincenzo.model.domain.ProdutoAlimenticio;
import br.edu.infnet.caiovincenzo.model.service.ProdutoAlimenticioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtosAlimenticios")
public class ProdutoAlimenticioController {


    private final ProdutoAlimenticioService produtoAlimenticioService;

    public ProdutoAlimenticioController(ProdutoAlimenticioService produtoAlimenticioService) {
        this.produtoAlimenticioService = produtoAlimenticioService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoAlimenticio>> obterLista() {
        List<ProdutoAlimenticio> lista = produtoAlimenticioService.obterLista();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoAlimenticio> obterPorId(@PathVariable Integer id) {
        ProdutoAlimenticio produto = produtoAlimenticioService.obterPorId(id);
        if (produto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        produtoAlimenticioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoAlimenticio> incluir(@Valid  @RequestBody ProdutoAlimenticio produto) {
        ProdutoAlimenticio novoProduto = produtoAlimenticioService.incluir(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoAlimenticio> alterar(@PathVariable Integer id, @RequestBody ProdutoAlimenticio produto) {
        ProdutoAlimenticio produtoAlterado = produtoAlimenticioService.alterar(id, produto);

        return ResponseEntity.ok(produtoAlterado);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ProdutoAlimenticio> alterarDiasValidade(@PathVariable Integer id, @RequestBody Integer diasValidade) {
        ProdutoAlimenticio produtoAlterado = produtoAlimenticioService.trocarDataValidade(id, diasValidade);

        return ResponseEntity.ok(produtoAlterado);
    }
}
