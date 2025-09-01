package br.edu.infnet.caiovincenzo.model.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;

@Entity
public class ProdutoAlimenticio extends Produto {
    
    private boolean perecivel;

    @Min(value = 0, message = "A validade em dias não pode ser negativa")
    private Integer diasValidade;


    @Override
    public String obterTipo() {
        return "Produto alimentício";
    }

    @Override
    public String toString() {
        return String.format("ProdutoAlmenticio %s - %d - %s", super.toString(), diasValidade, perecivel ? "Perecível" : "Não perecível");
    }

    public boolean isPerecivel() {
        return perecivel;
    }

    public void setPerecivel(boolean perecivel) {
        this.perecivel = perecivel;
    }

    public Integer getDiasValidade() {
        return diasValidade;
    }

    public void setDiasValidade(Integer diasValidade) {
        this.diasValidade = diasValidade;
    }
}
