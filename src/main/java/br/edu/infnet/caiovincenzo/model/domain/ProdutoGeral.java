package br.edu.infnet.caiovincenzo.model.domain;

import jakarta.persistence.Entity;

@Entity
public class ProdutoGeral extends Produto {

    private String categoria;

    @Override
    public String obterTipo() {
        return "Produto geral";
    }

    @Override
    public String toString() {
        return String.format("ProdutoGeral %s - %s", super.toString(), categoria);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
