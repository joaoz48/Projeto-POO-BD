package br.inatel.projeto.model;

public class Mago extends Classe{

    public Mago() {
        super("Mago", 1000, 150);
        idClasse = 2;
    }


    @Override
    public int ataquePrimario(Arma arma) {
        if (arma.getTipo() == 2) {

            return (int) (dano + 1.25*arma.poder);
        }
        else
            return (int) (dano + 0.5*arma.poder);
    }

    @Override
    public int ataqueSecundario(Arma arma) {
        if (arma.tipo == 2) {

            return (int) (dano + 1.25*arma.poder);
        }
        else
            return (int) (dano + 0.5*arma.poder);
    }


    @Override
    public int habilidade() {
        System.out.println("O mago esta usando sua Tempestade de Gelo!!");
        return (int) (1.75*dano);
    }

}
