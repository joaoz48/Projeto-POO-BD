package br.inatel.projeto.Exceptions;

public class DinheiroInsuficienteException extends Exception{

    public DinheiroInsuficienteException(){
        super("Voce nao tem coins o suficiente para comprar!");
    }
}