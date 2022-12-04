package br.inatel.projeto.model;

public class Cacador extends Classe {

    public Cacador() {
        super("Cacador", 1100, 130);
        idClasse = 3;
    }

    @Override
    public int ataquePrimario(Arma arma) {
        if (arma.tipo == 3) {

            return (int) (dano + 1.25*arma.poder);
        }
        else
            return (int) (dano + 0.5*arma.poder);
    }

    @Override
    public int ataqueSecundario(Arma arma) {
        if (arma.tipo == 3) {

            return (int) (dano + 1.25*arma.poder);
        }
        else
            return (int) (dano + 0.5*arma.poder);
    }

    @Override
    public int habilidade() {
        System.out.println("O cacador esta usando seu Disparo perfurante!!");
        return (int) (1.75*dano);
    }

}
