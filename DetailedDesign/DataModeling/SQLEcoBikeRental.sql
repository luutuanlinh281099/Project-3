-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ecobike
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ecobike
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecobike` DEFAULT CHARACTER SET utf8 ;
USE `ecobike` ;

-- -----------------------------------------------------
-- Table `ecobike`.`parking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`parking` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dia_chi` VARCHAR(255) NULL DEFAULT NULL,
  `so_o_trong` INT(11) NULL DEFAULT NULL,
  `ten_bai` VARCHAR(255) NULL DEFAULT NULL,
  `tong_o_chua` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ecobike`.`category_bike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`category_bike` (
  `id` BIGINT(20) NOT NULL,
  `loai_xe` VARCHAR(45) NULL DEFAULT NULL,
  `gia_xe` INT(11) NULL DEFAULT NULL,
  `image` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ecobike`.`bike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`bike` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dung_luong_pin` FLOAT NULL DEFAULT NULL,
  `parking_id` BIGINT(20) NULL DEFAULT NULL,
  `trang_thai_thue` TINYINT(4) NULL DEFAULT NULL,
  `category_bike_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKbu4yoyk3grhagplgar7ptfher` (`parking_id` ASC),
  INDEX `category_bike_id_idx` (`category_bike_id` ASC),
  CONSTRAINT `FKbu4yoyk3grhagplgar7ptfher`
    FOREIGN KEY (`parking_id`)
    REFERENCES `ecobike`.`parking` (`id`),
  CONSTRAINT `category_bike_id`
    FOREIGN KEY (`category_bike_id`)
    REFERENCES `ecobike`.`category_bike` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ecobike`.`rental_bike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`rental_bike` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `tien_coc` FLOAT NULL DEFAULT NULL,
  `thoi_gian_ket_thuc_thue` VARCHAR(255) NULL DEFAULT NULL,
  `thoi_gian_bat_dau_thue` DATETIME NULL DEFAULT NULL,
  `bike_id` BIGINT(20) NULL DEFAULT NULL,
  `trang_thai` TINYINT(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK8j4hpnk3jrnbr4y181i4emhax` (`bike_id` ASC),
  CONSTRAINT `FK8j4hpnk3jrnbr4y181i4emhax`
    FOREIGN KEY (`bike_id`)
    REFERENCES `ecobike`.`bike` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `ecobike`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecobike`.`invoice` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `tong_tien_thue` INT(11) NULL DEFAULT NULL,
  `rental_bike_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `rental_bike_id_idx` (`rental_bike_id` ASC),
  CONSTRAINT `rental_bike_id`
    FOREIGN KEY (`rental_bike_id`)
    REFERENCES `ecobike`.`rental_bike` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
