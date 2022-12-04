package br.inatel.projeto.model;

public class Arma {

    int idArma;
    String nome;
    int poder;
    int tipo;
    boolean pego;

    public String getNome() {
        return nome;
    }

    public int getPoder() {
        return poder;
    }

    public int getTipo() {
        return tipo;
    }

    public int getIdArma() {
        return idArma;
    }

    public Arma(int id,String nome, int poder, int tipo, boolean pego) {
        this.idArma = id;
        this.nome = nome;
        this.poder = poder;
        this.tipo = tipo;
        this.pego = pego;
    }

    public boolean isPego() {
        return pego;
    }

    public void setPego(boolean pego) {
        this.pego = pego;
    }
}
