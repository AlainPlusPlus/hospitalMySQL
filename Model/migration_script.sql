-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: hospitals
-- Created: Fri May 13 14:54:42 2016
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema hospitals1
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `hospitals` ;
CREATE SCHEMA IF NOT EXISTS `hospitals` ;

-- ----------------------------------------------------------------------------
-- Table hospitals1.employee
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hospitals`.`employee` (
  `idemployee` INT(11) NOT NULL AUTO_INCREMENT ,
  `hospital_id` INT(11) NOT NULL ,
  `name` VARCHAR(45) NOT NULL DEFAULT 'no_name' ,
  `telephone` DECIMAL(10,0) NOT NULL DEFAULT '0' ,
  `title` VARCHAR(45) NOT NULL DEFAULT 'doctor_nurse' ,
  `speciality` VARCHAR(45) NULL DEFAULT 'speciality' ,
  PRIMARY KEY (`idemployee`) ,
  UNIQUE INDEX `idemployee_UNIQUE` (`idemployee` ASC) ,
  INDEX `fk_hospital_idx` (`hospital_id` ASC) ,
  CONSTRAINT `fk_hospital`
    FOREIGN KEY (`hospital_id` )
    REFERENCES `hospitals`.`hospital` (`idhospitals` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table hospitals1.hospital
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hospitals`.`hospital` (
  `idhospitals` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL DEFAULT 'no_name' ,
  `address` VARCHAR(45) NOT NULL DEFAULT 'address' ,
  `telephone` DECIMAL(10,0) NOT NULL DEFAULT '0' ,
  `info` VARCHAR(45) NULL DEFAULT 'more info' ,
  PRIMARY KEY (`idhospitals`) )
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = latin1;
SET FOREIGN_KEY_CHECKS = 1;
