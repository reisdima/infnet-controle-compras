package br.edu.infnet.caiovincenzo.controller;

import br.edu.infnet.caiovincenzo.model.domain.Produto;
import br.edu.infnet.caiovincenzo.model.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> obterLista() {
        return produtoService.obterLista();
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable Integer id) {
        return produtoService.obterPorId(id);
    }

    @DeleteMapping (value = "/{id}")
    public void excluir(@PathVariable Integer id) {
        produtoService.excluir(id);
    }

    @PostMapping
    public Produto incluir(@RequestBody Produto produto) {
        return produtoService.incluir(produto);
    }

    @PutMapping(value = "/{id}")
    public Produto alterar(@PathVariable Integer id, @RequestBody Produto produto) {
        return produtoService.alterar(id, produto);
    }

}
