package br.inatel.projeto.view;

import br.inatel.projeto.Exceptions.DinheiroInsuficienteException;
import br.inatel.projeto.Exceptions.OpcaoInvalidaException;
import br.inatel.projeto.controller.Confronto;
import br.inatel.projeto.controller.Database;
import br.inatel.projeto.controller.GeraPersonagem;
import br.inatel.projeto.model.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        //Iniciando conexao como BD
        Database database = new Database();
        database.connect();

        //Instanciando a variavel personagem
        Personagem personagem = new Personagem();

        //Instancia uma variavel auxiliar que gera funcoes para controlar o personagem inimigo
        GeraPersonagem pAux = new GeraPersonagem();

        //Saida de dados do Menu de Criacao de Personagem
        System.out.println("Bem vindo ao Menu de Criacao de Personagem!");
        System.out.println("Escolha um nome para seu personagem");
        Scanner input = new Scanner(System.in);
        String nomePersonagem = input.nextLine();
        System.out.println("Escolha a classe do seu personagem:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Cacador");
        database.insertClasses(pAux.geraClasses());

        //Variaveis auxiliares para controle de fluxo
        int aux = 0;
        boolean flag = true;

        //Criando Personagem
        while (flag) {
            aux = input.nextInt();
            try {
                if (aux == 1) {
                    Guerreiro guerreiro = new Guerreiro();
                    personagem = new Personagem(1,nomePersonagem, 40, guerreiro);
                    flag = false;
                }
                else if (aux == 2) {
                    Mago mago = new Mago();
                    personagem = new Personagem(1,nomePersonagem, 40, mago);
                    flag = false;
                }
                else if (aux == 3) {
                    Cacador cacador = new Cacador();
                    personagem = new Personagem(1,nomePersonagem, 40, cacador);
                    flag = false;
                }
                else
                    throw new OpcaoInvalidaException(); //Lanca a Excecao opcao invalida

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        //Crinado personagem Inimigo
        Personagem inimigo;
        inimigo = pAux.geraPersonagem(aux);

        //Adicionando Personagens na tabela
        database.insertPersonagem(personagem);
        database.insertPersonagem(inimigo);

        //Iniciar o confronto
        Confronto confronto = new Confronto(personagem, inimigo);
        //Guardando Armas e Itens no BD
        database.insertArmas(confronto.getArmas());
        database.insertItens(confronto.getItens());

        //Escolher as armas do personagem
        System.out.println("Escolha duas armas para seu personagem!");
        confronto.mostraArmas();
        int numArma1 = input.nextInt();
        int numArma2 = input.nextInt();

        Arma[] armas = new Arma[2];
        armas[0] = confronto.arma[numArma1-1];
        confronto.arma[numArma1-1].setPego(true);
        armas[1] = confronto.arma[numArma2-1];
        confronto.arma[numArma2-1].setPego(true);
        personagem.setArmas(armas);
        //Atualizando dados das Armas no BD
        database.updateArma(armas[0].getIdArma(),personagem.getIdPersonagem());
        database.updateArma(armas[1].getIdArma(),personagem.getIdPersonagem());

        //Escolher as armas do bot
        Arma[] armasBot = pAux.gerarArmas(confronto.getArmas());

        inimigo.setArmas(armasBot);
        //Atualizando dados das Armas no BD
        database.updateArma(armasBot[0].getIdArma(),inimigo.getIdPersonagem());
        database.updateArma(armasBot[1].getIdArma(),inimigo.getIdPersonagem());

        //Mostrando seu inimigo
        System.out.println("Voce vai enfrentar o " +inimigo.getNome());
        System.out.println("Ele(a) eh um(a) " +inimigo.getClasse().getNomeClasse()+ "(a)");

        //Inicio do combate
        System.out.println("Inicio do combate!!!");
        int numTurno = 1;
        while(personagem.getClasse().getVida() > 0 && inimigo.getClasse().getVida() > 0){

            System.out.println("Inicio do turno " +numTurno);

            //Variaveis auxiliares para controle de fluxo
            int vidaAux1;
            int vidaAux2;
            boolean flagLoja = false;
            boolean flagException = false;

            //Personagens usam sua Habilidade especial no 4 round
            if (numTurno == 4) {

                System.out.println("Voce e seu inimigo usaram suas habilidades especiais!!");
                //retirando a vida do inimigo
                int danoHCausado = personagem.getClasse().habilidade();
                vidaAux1 = inimigo.getClasse().getVida();
                vidaAux1 = vidaAux1 - danoHCausado;
                inimigo.getClasse().setVida(vidaAux1);

                //retirando a vida do personagem
                int danoHSofrido = inimigo.getClasse().habilidade();
                vidaAux2 = personagem.getClasse().getVida();
                vidaAux2 = vidaAux2 - danoHSofrido;
                personagem.getClasse().setVida(vidaAux2);
            }
            else{
                //Escolhendo acoes do turno
                System.out.println("Escolha uma acao para fazer no combate");
                System.out.println("1 - Ataque Primario");
                System.out.println("2 - Ataque Secundario");
                System.out.println("3 - Acessar a loja");
                int op = input.nextInt();

                //Variaveis auxiliares para controle de fluxo
                int danoCausado;
                int danoSofrido;

                //Da o ataque do personagem
                try {
                    switch (op) {
                        case 1 -> {
                            danoCausado = personagem.getClasse().ataquePrimario(personagem.getArmas()[0]);
                            System.out.println("Voce usou o ataque primario e deu " + danoCausado + " de dano");

                            //retirando a vida do inimigo
                            vidaAux1 = inimigo.getClasse().getVida();
                            vidaAux1 = vidaAux1 - danoCausado;
                            inimigo.getClasse().setVida(vidaAux1);
                        }
                        case 2 -> {
                            danoCausado = personagem.getClasse().ataqueSecundario(personagem.getArmas()[1]);
                            System.out.println("Voce usou o ataque secundario e deu " + danoCausado + " de dano");

                            //retirando a vida do inimigo
                            vidaAux1 = inimigo.getClasse().getVida();
                            vidaAux1 = vidaAux1 - danoCausado;
                            inimigo.getClasse().setVida(vidaAux1);
                        }
                        case 3 -> { //Entra na Loja
                            flagLoja = true;

                            //Gerando primeira compra possivel do Bot + atualizando no BD
                            int auxItem = pAux.geraCompraBot(inimigo, confronto.getItens());
                            if (auxItem != -1)
                                database.insertCompra(inimigo.getIdPersonagem(), auxItem);

                            //Saida de dados da Loja
                            System.out.println("Bem vindo a loja");
                            System.out.println("Voce tem " + personagem.getDinheiro() + " de gold");
                            System.out.println("Esses sao os itens disponiveis para compra");

                            //mostrar os itens da loja
                            confronto.mostraItens();
                            System.out.println("5 - Sair da Loja");


                            try {

                                int numCompra = input.nextInt() - 1;
                                if(numCompra == 4)
                                    break;

                                if(numCompra > 4 || numCompra < 0){
                                    throw new OpcaoInvalidaException();
                                }

                                Item item = confronto.getItem(numCompra);

                                if (item.getPreco() <= personagem.getDinheiro()) {
                                    int aumentoVidaAux = personagem.getClasse().getVida();
                                    aumentoVidaAux += item.getAumentoDeVida();
                                    personagem.getClasse().setVida(aumentoVidaAux);

                                    int aumentoDanoAux = personagem.getClasse().getDano();
                                    aumentoDanoAux += item.getAumentoDeDano();
                                    personagem.getClasse().setDano(aumentoDanoAux);

                                    float dinheiroAux = personagem.getDinheiro();
                                    dinheiroAux -= item.getPreco();
                                    personagem.setDinheiro(dinheiroAux);

                                    database.insertCompra(personagem.getIdPersonagem(), item.getIdItem());
                                } else
                                    throw new DinheiroInsuficienteException(); //Lanca Excecao dinheiro insuficiente
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        default -> throw new OpcaoInvalidaException(); //Lanca Excecao opcao invalida
                    }
                }catch (Exception e){
                    System.out.println(e);
                    flagException = true;
                }

                //Saida de dados Turno Loja
                if (flagLoja) {
                    System.out.println("Turno dedicado a compra de itens!");
                    System.out.println("Nesse turno ninguem ataca ninguem!");
                    if(numTurno == 4)
                        numTurno++;
                }

                //Limita a acao do inimigo a turnos de confronto
                if(!flagException && !flagLoja){
                    //da o ataque do bot
                    danoSofrido = inimigo.getClasse().ataquePrimario(inimigo.getArmas()[0]);
                    System.out.println("Voce sofreu " + danoSofrido + " de dano");

                    //retirar a vida do personagem
                    vidaAux2 = personagem.getClasse().getVida();
                    vidaAux2 = vidaAux2 - danoSofrido;
                    personagem.getClasse().setVida(vidaAux2);

                    //gera um farm aleatorio entre 60-100 de gold
                    pAux.geraGold(personagem);
                    pAux.geraGold(inimigo);

                    System.out.println("Fim do turno " +numTurno);
                    System.out.println("-----------------------");
                    numTurno++;
                }

            }

            //Atualizando Personagens e suas Classes
            //Personagem
            database.updateDinheiroPersonagem(personagem.getDinheiro(), personagem.getIdPersonagem());
            database.updateVidaDano(personagem.getClasse().getVida(),personagem.getClasse().getDano(),personagem.getClasse().getIdClasse());
            //Inimigo
            database.updateDinheiroPersonagem(inimigo.getDinheiro(), inimigo.getIdPersonagem());
            database.updateVidaDano(inimigo.getClasse().getVida(),inimigo.getClasse().getDano(),inimigo.getClasse().getIdClasse());

        }

        //Verifica Vencedor e Saida de dados finais\
        System.out.println();

        //Caso perdeu
        if (personagem.getClasse().getVida() <= inimigo.getClasse().getVida()) {
            System.out.println("Voce perdeu o combate!");
            System.out.println("O " +inimigo.getNome()+ " foi o grande vencedor!");
            System.out.println();

            //Informacoes do Vencedor
            database.mostraPersonagemVencedor(2);

            //Deletando o Perdedor da Tabela Personagem
            database.deletePersonagem(1);
        }
        //Caso ganhou
        else{
            System.out.println("Voce ganhou o combate!");
            System.out.println("Parabens voce foi o grande vencedor!");
            System.out.println();

            //Informacoes do Vencedor
            database.mostraPersonagemVencedor(1);

            //Deletando o Perdedor da Tabela Personagem
            database.deletePersonagem(2);
        }

    }

}