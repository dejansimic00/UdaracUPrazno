-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema evidencija_radnog_vremena
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema evidencija_radnog_vremena
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `evidencija_radnog_vremena` ;
USE `evidencija_radnog_vremena` ;

-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`OSOBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`OSOBA` (
  `id_osoba` INT(15) NOT NULL,
  `ime` VARCHAR(64) NOT NULL,
  `prezime` VARCHAR(64) NOT NULL,
  `jmbg` VARCHAR(13) NOT NULL,
  `slika` VARCHAR(256) NOT NULL,
  `sef` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_osoba`),
  UNIQUE INDEX `jmbg` (`jmbg` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`NALOG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`NALOG` (
  `id_osoba` INT(15) NOT NULL,
  `korisnicko_ime` VARCHAR(64) NOT NULL,
  `lozinka` VARCHAR(64) NOT NULL,
  `uloga` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_osoba`),
  UNIQUE INDEX `korisnicko_ime` (`korisnicko_ime` ASC) VISIBLE,
  CONSTRAINT `NALOG_ibfk_1`
    FOREIGN KEY (`id_osoba`)
    REFERENCES `evidencija_radnog_vremena`.`OSOBA` (`id_osoba`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`PROSTORIJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`PROSTORIJA` (
  `id_prostorija` INT(15) NOT NULL AUTO_INCREMENT,
  `port` VARCHAR(128) NOT NULL,
  `kamera` VARCHAR(128) NOT NULL,
  `naziv` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id_prostorija`))
ENGINE = InnoDB
AUTO_INCREMENT = 8;


-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`OSOBA_PROSTORIJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`OSOBA_PROSTORIJA` (
  `id_osoba` INT(15) NOT NULL,
  `id_prostorija` INT(15) NOT NULL,
  PRIMARY KEY (`id_osoba`, `id_prostorija`),
  INDEX `id_prostorija` (`id_prostorija` ASC) VISIBLE,
  CONSTRAINT `OSOBA_PROSTORIJA_ibfk_1`
    FOREIGN KEY (`id_osoba`)
    REFERENCES `evidencija_radnog_vremena`.`OSOBA` (`id_osoba`),
  CONSTRAINT `OSOBA_PROSTORIJA_ibfk_2`
    FOREIGN KEY (`id_prostorija`)
    REFERENCES `evidencija_radnog_vremena`.`PROSTORIJA` (`id_prostorija`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`POSAO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`POSAO` (
  `id_posao` INT(15) NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(64) NOT NULL,
  `koeficijent` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_posao`),
  UNIQUE INDEX `naziv` (`naziv` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6;


-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`PRISTUP_PROSTORIJI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`PRISTUP_PROSTORIJI` (
  `id_pristup_prostoriji` INT(11) NOT NULL AUTO_INCREMENT,
  `id_osoba` INT(15) NOT NULL,
  `id_prostorija` INT(15) NOT NULL,
  `vrijeme_ulaska` DATETIME NOT NULL,
  PRIMARY KEY (`id_pristup_prostoriji`),
  INDEX `fk_pristup_prostoriji_osoba_idx` (`id_osoba` ASC) VISIBLE,
  INDEX `fk_pristup_prostoriji_prostorija_idx` (`id_prostorija` ASC) VISIBLE,
  CONSTRAINT `fk_pristup_prostoriji_osoba`
    FOREIGN KEY (`id_osoba`)
    REFERENCES `evidencija_radnog_vremena`.`OSOBA` (`id_osoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pristup_prostoriji_prostorija`
    FOREIGN KEY (`id_prostorija`)
    REFERENCES `evidencija_radnog_vremena`.`PROSTORIJA` (`id_prostorija`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`RADNO_VRIJEME`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`RADNO_VRIJEME` (
  `id` INT(15) NOT NULL,
  `id_osoba` INT(15) NOT NULL,
  `id_posao` INT(15) NOT NULL,
  `pocetak` DATETIME NULL DEFAULT NULL,
  `kraj` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`SEF`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`SEF` (
  `id_osoba` INT(15) NOT NULL,
  `id_sef` INT(15) NOT NULL,
  PRIMARY KEY (`id_osoba`, `id_sef`),
  INDEX `id_sef` (`id_sef` ASC) VISIBLE,
  CONSTRAINT `SEF_ibfk_1`
    FOREIGN KEY (`id_osoba`)
    REFERENCES `evidencija_radnog_vremena`.`OSOBA` (`id_osoba`),
  CONSTRAINT `SEF_ibfk_2`
    FOREIGN KEY (`id_sef`)
    REFERENCES `evidencija_radnog_vremena`.`OSOBA` (`id_osoba`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `evidencija_radnog_vremena`.`SMJENA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `evidencija_radnog_vremena`.`SMJENA` (
  `id_smjena` INT(15) NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(64) NOT NULL,
  `pocetak` TIME NOT NULL,
  `kraj` TIME NOT NULL,
  PRIMARY KEY (`id_smjena`))
ENGINE = InnoDB
AUTO_INCREMENT = 4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
