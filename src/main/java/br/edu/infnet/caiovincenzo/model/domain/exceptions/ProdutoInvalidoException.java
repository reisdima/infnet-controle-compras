package br.edu.infnet.caiovincenzo.model.domain.exceptions;

public class ProdutoInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
