package br.edu.infnet.caiovincenzo.controller;

import br.edu.infnet.caiovincenzo.model.domain.Produto;
import br.edu.infnet.caiovincenzo.model.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
