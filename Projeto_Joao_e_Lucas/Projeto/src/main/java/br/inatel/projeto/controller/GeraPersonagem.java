package br.inatel.projeto.controller;

import br.inatel.projeto.model.*;

import java.util.Random;

public class GeraPersonagem {
    private Random random = new Random();

    //Gera um personagem com uma classe diferente da escolhida
    public Personagem geraPersonagem(int classe){

        int num;
        Personagem personagem = null;
        num = random.nextInt(1,4);

        while (num == classe){
            num = random.nextInt(1,4);
        }

        switch (num) {
            case 1 -> {
                Guerreiro guerreiro = new Guerreiro();
                personagem = new Personagem(2, "Efesto Vorsath", 40, guerreiro);
            }
            case 2 -> {
                Mago mago = new Mago();
                personagem = new Personagem(2, "Urhan de Morrowits", 40, mago);
            }
            case 3 -> {
                Cacador cacador = new Cacador();
                personagem = new Personagem(2, "Geraldo de Riviera", 40, cacador);
            }
        }

        return personagem;
    }

    public Arma[] gerarArmas(Arma[] armas){
        Arma[] armasAux = new Arma[2];
        int numAux;
        int contador = 0;

        while (armasAux[0] == null || armasAux[1] == null){
            numAux = random.nextInt(6);
            if(!armas[numAux].isPego()){
                armasAux[contador] = armas[numAux];
                armas[numAux].setPego(true);
                contador ++;
            }
        }

        return armasAux;
    }

    public Classe[] geraClasses(){
        Classe[] classes = new Classe[3];

        classes[0] = new Guerreiro();
        classes[1] = new Mago();
        classes[2] = new Cacador();

        return classes;
    }

    public void geraGold(Personagem personagem){
        int gold = random.nextInt(60,101);

        personagem.setDinheiro(personagem.getDinheiro()+gold);

        System.out.println(personagem.getNome() + " farmou " + gold + " de gold!");
    }

    public int geraCompraBot(Personagem personagem, Item[] item){

        for (Item value : item) {
            if (value.getPreco() <= personagem.getDinheiro()) {
                int aumentoVidaAux = personagem.getClasse().getVida();
                aumentoVidaAux += value.getAumentoDeVida();
                personagem.getClasse().setVida(aumentoVidaAux);

                int aumentoDanoAux = personagem.getClasse().getDano();
                aumentoDanoAux += value.getAumentoDeDano();
                personagem.getClasse().setDano(aumentoDanoAux);

                float dinheiroAux = personagem.getDinheiro();
                dinheiroAux -= value.getPreco();
                personagem.setDinheiro(dinheiroAux);

                return value.getIdItem();
            }
        }

        return -1;
    }

}
