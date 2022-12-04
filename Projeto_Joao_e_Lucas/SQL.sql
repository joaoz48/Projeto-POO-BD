-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema projeto
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `projeto` ;

-- -----------------------------------------------------
-- Schema projeto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projeto` DEFAULT CHARACTER SET utf8 ;
USE `projeto` ;

-- -----------------------------------------------------
-- Table `projeto`.`Classe`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto`.`Classe` ;

CREATE TABLE IF NOT EXISTS `projeto`.`Classe` (
  `idClasse` INT NOT NULL,
  `NomeClasse` VARCHAR(45) NOT NULL,
  `Vida` INT NOT NULL,
  `Dano` INT NOT NULL,
  PRIMARY KEY (`idClasse`));

-- -----------------------------------------------------
-- Table `projeto`.`Personagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto`.`Personagem` ;

CREATE TABLE IF NOT EXISTS `projeto`.`Personagem` (
  `idPersonagem` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Dinheiro` FLOAT NOT NULL,
  `Classe_idClasse` INT NOT NULL,
  PRIMARY KEY (`idPersonagem`),
  CONSTRAINT `fk_Personagem_Classe1`
    FOREIGN KEY (`Classe_idClasse`)
    REFERENCES `projeto`.`Classe` (`idClasse`)
    ON DELETE cascade
    ON UPDATE cascade);


-- -----------------------------------------------------
-- Table `projeto`.`Itens`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto`.`Itens` ;

CREATE TABLE IF NOT EXISTS `projeto`.`Itens` (
  `idItem` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Preco` FLOAT NOT NULL,
  `aumentoDeVida` INT NOT NULL,
  `aumentoDeDano` INT NOT NULL,
  PRIMARY KEY (`idItem`));


-- -----------------------------------------------------
-- Table `projeto`.`Personagem_comprou_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto`.`Personagem_comprou_item` ;

CREATE TABLE IF NOT EXISTS `projeto`.`Personagem_comprou_item` (
  `Personagem_idPersonagem` INT NOT NULL,
  `Itens_idItem` INT NOT NULL,
  PRIMARY KEY (`Personagem_idPersonagem`, `Itens_idItem`),
  CONSTRAINT `fk_Personagem_has_Itens_Personagem`
    FOREIGN KEY (`Personagem_idPersonagem`)
    REFERENCES `projeto`.`Personagem` (`idPersonagem`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_Personagem_has_Itens_Itens1`
    FOREIGN KEY (`Itens_idItem`)
    REFERENCES `projeto`.`Itens` (`idItem`)
    ON DELETE cascade
    ON UPDATE cascade);

-- -----------------------------------------------------
-- Table `projeto`.`Arma`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projeto`.`Arma` ;

CREATE TABLE IF NOT EXISTS `projeto`.`Arma` (
  `idArma` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Poder` INT NOT NULL,
  `Personagem_idPersonagem` INT NULL,
  PRIMARY KEY (`idArma`),
  CONSTRAINT `fk_Arma_Personagem1`
    FOREIGN KEY (`Personagem_idPersonagem`)
    REFERENCES `projeto`.`Personagem` (`idPersonagem`)
    ON DELETE cascade
    ON UPDATE cascade);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

select * from projeto.personagem;
select * from projeto.Arma;
select * from projeto.itens;
select * from projeto.classe;
select * from projeto.Personagem_comprou_item;
select i.Nome as 'Itens' from itens as i, Personagem_comprou_item as pi, Personagem as p where pi.Personagem_idPersonagem = p.idPersonagem and pi.Itens_idItem = i.idItem ; 
