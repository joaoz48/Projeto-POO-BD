package br.inatel.projeto.model;

public class Item {

    private int idItem;
    private String Nome;
    private float Preco;
    private int aumentoDeVida;
    private int aumentoDeDano;

    public float getPreco() {
        return Preco;
    }

    public int getAumentoDeVida() {
        return aumentoDeVida;
    }

    public int getAumentoDeDano() {
        return aumentoDeDano;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getNome() {
        return Nome;
    }

    public Item(int id,String nome, float preco, int numItem, int aumentoDeVida, int aumentoDeDano) {
        this.idItem = id;
        this.Nome = nome;
        this.Preco = preco;
        this.idItem = numItem;
        this.aumentoDeVida = aumentoDeVida;
        this.aumentoDeDano = aumentoDeDano;
    }

}
