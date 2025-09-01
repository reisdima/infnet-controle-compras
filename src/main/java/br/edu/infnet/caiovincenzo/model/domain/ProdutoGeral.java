package br.edu.infnet.caiovincenzo.model.domain;

import br.edu.infnet.caiovincenzo.model.domain.enums.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProdutoGeral extends Produto {

    @NotNull(message = "A categoria é obrigatória")
    @Enumerated
    private Categoria categoria;

    @Override
    public String obterTipo() {
        return "Produto geral";
    }

    @Override
    public String toString() {
        return String.format("ProdutoGeral %s - %s", super.toString(), categoria);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
