package br.edu.infnet.caiovincenzo.controller;

import br.edu.infnet.caiovincenzo.model.domain.Produto;
import br.edu.infnet.caiovincenzo.model.domain.ProdutoAlimenticio;
import br.edu.infnet.caiovincenzo.model.service.ProdutoAlimenticioService;
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
    public List<ProdutoAlimenticio> obterLista() {
        return produtoAlimenticioService.obterLista();
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable Integer id) {
        return produtoAlimenticioService.obterPorId(id);
    }

    @DeleteMapping(value = "/{id}")
    public void excluir(@PathVariable Integer id) {
        produtoAlimenticioService.excluir(id);
    }

    @PostMapping
    public ProdutoAlimenticio incluir(@RequestBody ProdutoAlimenticio produto) {
        return produtoAlimenticioService.incluir(produto);
    }

    @PutMapping(value = "/{id}")
    public ProdutoAlimenticio alterar(@PathVariable Integer id, @RequestBody ProdutoAlimenticio produto) {
        return produtoAlimenticioService.alterar(id, produto);
    }

    @PatchMapping(value = "/{id}")
    public ProdutoAlimenticio alterarDiasValidade(@PathVariable Integer id, @RequestBody Integer diasValidade) {
        return produtoAlimenticioService.trocarDataValidade(id, diasValidade);
    }
}
