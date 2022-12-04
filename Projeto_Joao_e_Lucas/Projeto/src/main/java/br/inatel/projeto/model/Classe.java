package br.inatel.projeto.model;

public abstract class Classe {

    int idClasse;
    String nomeClasse;
    int vida;
    int dano;

    public Classe(String nomeClasse, int vida, int dano) {
        this.nomeClasse = nomeClasse;
        this.vida = vida;
        this.dano = dano;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }

    public abstract int ataquePrimario(Arma arma);

    public abstract int ataqueSecundario(Arma arma);

    public abstract int habilidade();

}
