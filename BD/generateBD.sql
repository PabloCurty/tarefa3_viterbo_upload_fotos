-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema BDFoto
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `BDFoto` ;

-- -----------------------------------------------------
-- Schema BDFoto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BDFoto` DEFAULT CHARACTER SET utf8 ;
USE `BDFoto` ;

-- -----------------------------------------------------
-- Table `BDFoto`.`foto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `BDFoto`.`foto` ;

CREATE TABLE IF NOT EXISTS `BDFoto`.`foto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `foto` LONGBLOB NOT NULL,
  `author` VARCHAR(45) NULL,
  `place` VARCHAR(45) NULL,
  `subtitle` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
