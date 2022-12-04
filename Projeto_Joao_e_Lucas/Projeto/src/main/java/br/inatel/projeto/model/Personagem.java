package br.inatel.projeto.model;

public class Personagem {

    int idPersonagem;
    String nome;
    float dinheiro;
    Classe classe;
    int idClasse;
    Arma[] armas = new Arma[2];

    public Personagem(){}

    public void setArmas(Arma[] armas) {
        this.armas = armas;
    }

    public String getNome() {
        return nome;
    }

    public float getDinheiro() {
        return dinheiro;
    }

    public Classe getClasse() {
        return classe;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setDinheiro(float dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Arma[] getArmas() {
        return armas;
    }

    public Personagem(int id, String nome, float dinheiro, Classe classe) {
        this.idPersonagem = id;
        this.nome = nome;
        this.dinheiro = dinheiro;
        this.classe = classe;
    }

    public Personagem(int idPersonagem, String nome, float dinheiro, int idClasse) {
        this.idPersonagem = idPersonagem;
        this.nome = nome;
        this.dinheiro = dinheiro;
        this.idClasse = idClasse;
    }


}
