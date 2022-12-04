package br.inatel.projeto.model;

public class Guerreiro extends Classe{

    public Guerreiro() {
        super("Guerreiro", 1300, 100);
        idClasse = 1;
    }

    @Override
    public int ataquePrimario(Arma arma) {
        if (arma.tipo == 1) {

            return (int) (dano + 1.25*arma.poder);
        }
        else
            return (int) (dano + 0.5*arma.poder);
    }

    @Override
    public int ataqueSecundario(Arma arma) {
        if (arma.tipo == 1) {

            return (int) (dano + 1.25*arma.poder);
        }
        else
            return (int) (dano + 0.5*arma.poder);
    }

    @Override
    public int habilidade() {
        System.out.println("O guerreiro esta usando a Investida Mortal!!");
        return (int) (1.75*dano);
    }

}
