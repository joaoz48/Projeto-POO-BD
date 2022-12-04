package br.inatel.projeto.Exceptions;

public class OpcaoInvalidaException extends Exception{

    public OpcaoInvalidaException(){
        super("Opcao invalida digitada!");
        System.out.println("Digite novamente!");
    }
}
