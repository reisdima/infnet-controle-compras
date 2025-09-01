package br.edu.infnet.caiovincenzo.model.domain.exceptions;

public class EntidadeInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntidadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}
