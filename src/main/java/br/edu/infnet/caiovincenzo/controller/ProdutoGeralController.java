package br.edu.infnet.caiovincenzo.controller;

import br.edu.infnet.caiovincenzo.model.domain.Produto;
import br.edu.infnet.caiovincenzo.model.domain.ProdutoGeral;
import br.edu.infnet.caiovincenzo.model.service.ProdutoGeralService;
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
    public List<ProdutoGeral> obterLista() {
        return produtoGeralService.obterLista();
    }

    @GetMapping("/{id}")
    public ProdutoGeral obterPorId(@PathVariable Integer id) {
        return produtoGeralService.obterPorId(id);
    }

    @DeleteMapping(value = "/{id}")
    public void excluir(@PathVariable Integer id) {
        produtoGeralService.excluir(id);
    }

    @PostMapping
    public ProdutoGeral incluir(@RequestBody ProdutoGeral produto) {
        return produtoGeralService.incluir(produto);
    }

    @PutMapping(value = "/{id}")
    public ProdutoGeral alterar(@PathVariable Integer id, @RequestBody ProdutoGeral produto) {
        return produtoGeralService.alterar(id, produto);
    }

    @PatchMapping(value = "/{id}")
    public ProdutoGeral alterarCategoria(@PathVariable Integer id, @RequestBody String categoria) {
        return produtoGeralService.trocarCategoria(id, categoria);
    }

}
