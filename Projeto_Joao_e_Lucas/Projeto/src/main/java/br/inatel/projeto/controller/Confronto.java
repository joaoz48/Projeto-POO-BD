package br.inatel.projeto.controller;

import br.inatel.projeto.model.Arma;
import br.inatel.projeto.model.Item;
import br.inatel.projeto.model.Personagem;

public class Confronto {
    public Personagem[] personagem = new Personagem[2];
    public Arma[] arma = new Arma[6];
    public Item[] item = new Item[4];

    public Confronto(Personagem p1, Personagem p2){

        personagem[0] = p1;
        personagem[1] = p2;

        gerarArmas();
        gerarItens();
    }

    private void gerarItens(){
        //Item 1 - Ruptor Divino
        item[0] = new Item(1,"Ruptor Divino", 120, 1,200,30);

        //Item 2 - Tempestade de Luden
        item[1] = new Item(2,"Tempestade de Luden", 150, 2,30,120);

        //Item 3 - Gume do Infinito
        item[2] = new Item(3,"Gume do Infinito", 100, 3,100,60);

        //Item 4 - Mata-Cráquens
        item[3] = new Item(4,"Mata-Cráquens", 130, 4,50,120);
    }

    private void gerarArmas(){
        //Arma1 - Espada Sangrenta
        arma[0] = new Arma(1,"Espada Sangrenta",150,1,false);

        //Arma2 - Katana Flamejante
        arma[1] = new Arma(2,"Katana Flamejante",130,1,false);

        //Arma3 - Manopla de Gelo
        arma[2] = new Arma(3,"Manopla de Gelo",150,2,false);

        //Arma4 - Cajado do Necromante
        arma[3] = new Arma(4,"Cajado do Necromante",130,2,false);

        //Arma5 - Arco Recurvo
        arma[4] = new Arma(5,"Arco Recurvo",150,3,false);

        //Arma6 - Besta Envenada
        arma[5] = new Arma(6,"Besta Envenada",130,3,false);
    }

    public void mostraArmas(){
        System.out.println(" --- Mostrando Armas disponiveis ---");

        for (int cont=0; cont<arma.length; cont++){
            if (cont != 0)
                System.out.println("-----------------------");

            System.out.println("Id arma: "+arma[cont].getIdArma());
            System.out.println("Nome: "+arma[cont].getNome());
            System.out.println("Poder: "+arma[cont].getPoder());
            if (arma[cont].getTipo() == 1) {
                System.out.println("Essa arma eh melhor para guerreiros(as)");
            }
            else if (arma[cont].getTipo() == 2) {
                System.out.println("Essa arma eh melhor para magos(as)");
            }
            else
                System.out.println("Essa arma eh melhor para cacadores(as)");
        }

        System.out.println("-----------------------");

    }

    public Item getItem(int numItem) {
        return item[numItem];
    }

    public void mostraItens(){

        System.out.println(" --- Mostrando Itens da Loja ---");

        for (int cont=0; cont<item.length; cont++){
            if (cont != 0)
                System.out.println("-----------------------");

            System.out.println("Id item: "+item[cont].getIdItem());
            System.out.println("Nome: "+item[cont].getNome());
            System.out.println("Preco: "+item[cont].getPreco());
            System.out.println("Aumento de Vida: "+item[cont].getAumentoDeVida());
            System.out.println("Aumento de Dano: "+item[cont].getAumentoDeDano());
        }

        System.out.println("-----------------------");
    }

    public Arma[] getArmas() {
        return arma;
    }

    public Item[] getItens() {
        return item;
    }
}
