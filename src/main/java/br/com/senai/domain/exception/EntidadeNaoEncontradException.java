package br.com.senai.domain.exception;



public class EntidadeNaoEncontradException extends NegocioException{

    public EntidadeNaoEncontradException(String messsage) {
        super(messsage);
    }
}
