package br.inatel.projeto.controller;

import br.inatel.projeto.model.Arma;
import br.inatel.projeto.model.Classe;
import br.inatel.projeto.model.Item;
import br.inatel.projeto.model.Personagem;

import java.sql.*;

public class Database {
    Connection connection; // objeto responsável por fazer a conexão com mysql
    Statement statement; // objeto responsável por preparar consultas "SELECT"
    ResultSet result; // objeto responsável por executar consultas "SELECT"
    PreparedStatement pst; // objeto responsável por preparar querys de manipulação dinâmica(INSERT, UPDATE, DELETE)

    static final String user = "root"; // usuário da instância local do servidor
    static final String password = "root"; // senha do usuário da instância local do servidor
    static final String database = "projeto"; // nome do banco de dados a ser utilizado

    // String com URL de conexão com o servidor
    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private boolean check = false;

    public void connect(){

        try{
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

    //--------------------INSERINDO NOVO REGISTRO--------------------

    //Personagens
    public boolean insertPersonagem(Personagem personagem){
        connect();
        String sql = "INSERT INTO Personagem(idPersonagem,Nome,Dinheiro,Classe_idClasse) VALUES (?,?,?,?)";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,personagem.getIdPersonagem());
            pst.setString(2,personagem.getNome());// concatena nome no primeiro ? do comando
            pst.setFloat(3,personagem.getDinheiro());// concatena nome no segundo ? do comando
            pst.setInt(4,personagem.getClasse().getIdClasse());
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //Classes
    public boolean insertClasses(Classe[] classes){
        int contador = 0;

        while (contador < classes.length) {
            connect();

            String sql = "INSERT INTO Classe(idClasse,NomeClasse,Vida,Dano) VALUES (?,?,?,?)";

            try {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, classes[contador].getIdClasse());// concatena nome no primeiro ? do comando
                pst.setString(2, classes[contador].getNomeClasse());// concatena nome no segundo ? do comando
                pst.setInt(3, classes[contador].getVida());
                pst.setInt(4, classes[contador].getDano());
                pst.execute();
                check = true;
            } catch (SQLException e) {
                System.out.println("Erro de conexão: " + e.getMessage());
                check = false;
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }

            contador++;
        }
        return check;
    }

    //Itens
    public boolean insertItens(Item[] itens){
        int contador = 0;

        while (contador < itens.length) {
            connect();

            String sql = "INSERT INTO Itens(idItem,Nome,Preco,aumentoDeVida,aumentoDeDano) VALUES (?,?,?,?,?)";

            try {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, itens[contador].getIdItem());// concatena nome no primeiro ? do comando
                pst.setString(2, itens[contador].getNome());// concatena nome no segundo ? do comando
                pst.setFloat(3, itens[contador].getPreco());
                pst.setInt(4, itens[contador].getAumentoDeVida());
                pst.setInt(5, itens[contador].getAumentoDeDano());
                pst.execute();
                check = true;
            } catch (SQLException e) {
                System.out.println("Erro de conexão: " + e.getMessage());
                check = false;
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }

            contador++;
        }
        return check;
    }

    //Armas
    public boolean insertArmas(Arma[] armas){
        int contador = 0;

        while (contador < armas.length) {
            connect();

            String sql = "INSERT INTO Arma(idArma,Nome,Poder) VALUES (?,?,?)";

            try {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, armas[contador].getIdArma());// concatena nome no primeiro ? do comando
                pst.setString(2, armas[contador].getNome());// concatena nome no segundo ? do comando
                pst.setInt(3, armas[contador].getPoder());
                pst.execute();
                check = true;
            } catch (SQLException e) {
                System.out.println("Erro de conexão: " + e.getMessage());
                check = false;
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }

            contador++;
        }
        return check;
    }

    //Compras
    public boolean insertCompra(int idP, int idI){
        connect();

            String sql = "INSERT INTO Personagem_comprou_item(Personagem_idPersonagem,Itens_idItem) VALUES (?,?)";

            try {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, idP);// concatena nome no primeiro ? do comando
                pst.setInt(2, idI);// concatena nome no segundo ? do comando
                pst.execute();
                check = true;
            } catch (SQLException e) {
                System.out.println("Erro de conexão: " + e.getMessage());
                check = false;
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }

        return check;
    }

    //--------------------BUSCANDO NOVO REGISTRO--------------------
    //Buscando Personagem Vencedor
    public boolean mostraPersonagemVencedor(int idPersonagem){
        connect();
        Personagem personagem;
        String sql = "SELECT * FROM personagem where idPersonagem = ?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idPersonagem);
            result = pst.executeQuery();
            while (result.next()) {
                String aux = result.getString("Nome");
                if(aux.isEmpty())
                {
                    check = false;
                } else {
                    personagem = new Personagem(result.getInt("idPersonagem"),result.getString("Nome"),result.getFloat("Dinheiro"),result.getInt("Classe_idClasse"));
                    System.out.println("----- Mostrando Informacoes do vencedor -----");
                    System.out.println("idPersonagem = " + personagem.getIdPersonagem());
                    System.out.println("Nome = " + personagem.getNome());
                    System.out.println("Dinheiro = " + personagem.getDinheiro());
                    if (personagem.getIdClasse() == 1) {
                        System.out.println("Classe = Guerreiro");
                    }
                    else if (personagem.getIdClasse() == 2) {
                        System.out.println("Classe = Mago");
                    }
                    else
                        System.out.println("Classe = Cacador");
                    System.out.println("---------------------------------");

                }
            }
            check = true;
        } catch(SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            check = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch(SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        return check;
    }


    //--------------------ATUALIZANDO Dados no BD--------------------

    //Personagem
    public boolean updateDinheiroPersonagem(float dinheiro,int idPersonagem){
        connect();
        String sql = "UPDATE Personagem SET Dinheiro=? WHERE idPersonagem=?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setFloat(1,dinheiro);
            pst.setInt(2,idPersonagem);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //Classe
    public boolean updateVidaDano(int Vida, int Dano, int idClasse){
        connect();
        String sql = "UPDATE Classe SET Vida=?, Dano=?  WHERE idClasse=?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,Vida);
            pst.setInt(2,Dano);
            pst.setInt(3,idClasse);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //Arma
    public boolean updateArma(int idArma, int idPersonagem){
        connect();
        String sql = "UPDATE Arma SET Personagem_idPersonagem=? WHERE idArma=?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,idPersonagem);
            pst.setInt(2,idArma);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //--------------------EXCLUINDO REGISTRO--------------------
    public boolean deletePersonagem(int id){
        connect();
        String sql = "DELETE FROM Personagem WHERE idPersonagem = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        }
        finally {
            try{
                connection.close();
                pst.close();
            } catch (SQLException e){
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }
}
