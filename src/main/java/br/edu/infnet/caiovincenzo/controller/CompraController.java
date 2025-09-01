package br.edu.infnet.caiovincenzo.controller;

import br.edu.infnet.caiovincenzo.model.domain.Compra;
import br.edu.infnet.caiovincenzo.model.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {


    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>> obterLista() {
        List<Compra> lista = compraService.obterLista();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obterPorId(@PathVariable Integer id) {
        Compra compra = compraService.obterPorId(id);
        if (compra == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(compra);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        compraService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Compra> cadastrarCompra(@Valid  @RequestBody Compra compra) {
        Compra novaCompra = compraService.incluir(compra);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaCompra);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Compra> alterar(@PathVariable Integer id, @RequestBody Compra compra) {
        Compra compraAlterada = compraService.alterar(id, compra);

        return ResponseEntity.ok(compraAlterada);
    }

}
