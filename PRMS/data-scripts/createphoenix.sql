create database phoenix;

use phoenix;

-- -----------------------------------------------------
-- Table `phoenix`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`role` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`role` (
  `role` VARCHAR(15) NOT NULL ,
  `accessPrivilege` VARCHAR(45) NULL ,
  PRIMARY KEY (`role`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `role_UNIQUE` ON `phoenix`.`role` (`role` ASC) ;


-- -----------------------------------------------------
-- Table `phoenix`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`user` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`user` (
  `id` VARCHAR(40) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  `name` VARCHAR(45) NULL ,
  `role` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`))
--  CONSTRAINT `role`
--    FOREIGN KEY (`role` )
--    REFERENCES `phoenix`.`role` (`role` )
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `phoenix`.`user` (`id` ASC) ;

CREATE INDEX `role_index` ON `phoenix`.`user` (`role` ASC) ;

-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`role`
-- -----------------------------------------------------

-- role, access privilege
insert into `phoenix`.`role` values("presenter","radio program presenter");
insert into `phoenix`.`role` values("manager", "station manager");
insert into `phoenix`.`role` values("admin", "system administrator");
insert into `phoenix`.`role` values("producer", "program producer");


-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`user`
-- -----------------------------------------------------

-- id, password, name, role
insert into `phoenix`.`user` values("dilbert", "dilbert", "dilbert, the hero", "presenter:producer");
insert into `phoenix`.`user` values("wally", "wally", "wally, the bludger", "producer");
insert into `phoenix`.`user` values("pointyhead", "pointyhead", "pointyhead, the manager", "manager");
insert into `phoenix`.`user` values("catbert", "catbert", "catbert, the hr", "admin:manager");
insert into `phoenix`.`user` values("dogbert", "dogbert", "dogbert, the CEO", "producer:admin");

-- -----------------------------------------------------
-- Table `phoenix`.`radio-program`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`radio-program` ;

CREATE TABLE IF NOT EXISTS `phoenix`.`radio-program` (
  `name` VARCHAR(45) NOT NULL ,
  `desc` VARCHAR(100) NULL ,
  `typicalDuration` TIME NULL ,
  PRIMARY KEY (`name`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `phoenix`.`radio-program` (`name` ASC) ;


-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`radio-program`
-- -----------------------------------------------------

insert into `phoenix`.`radio-program` values("short news", "summarised 5 minutes broadcasted every 2 hours", '00:05:00');
insert into `phoenix`.`radio-program` values("news", "full news broadcasted four times a day", '00:30:00');
insert into `phoenix`.`radio-program` values("top 10", "countdown music play of top 10 songs of the week", '01:00:00');
insert into `phoenix`.`radio-program` values("your choice", "audinece ask for music album song of thier choice", '01:00:00');
insert into `phoenix`.`radio-program` values("opinions", "discuss, debate or share opinions regarding a theme or subject", '00:30:00');
insert into `phoenix`.`radio-program` values("noose", "black comedy show", '00:30:00');
insert into `phoenix`.`radio-program` values("ppk", "phu chu kang comedy show", '00:30:00');
insert into `phoenix`.`radio-program` values("dance floor", "dance show", '00:30:00');
insert into `phoenix`.`radio-program` values("charity", "president charity show for unfortunate", '00:30:00');

-- -----------------------------------------------------
-- Table `phoenix`.`annual-schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`annual-schedule` ;

-- -----------------------------------------------------
-- Table `phoenix`.`program-slot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`program-slot` ;

CREATE  TABLE IF NOT EXISTS `phoenix`.`program-slot` (
  `id` int(10) NOT NULL,
  `program-name` VARCHAR(45) NOT NULL ,
  `dateOfProgram` DATE NOT NULL ,
  `startTime` TIME NOT NULL ,
  `duration` TIME NULL ,
  `presenter` VARCHAR(45) NULL ,
  `producer` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `name`
    FOREIGN KEY (`program-name` )
    REFERENCES `phoenix`.`radio-program` (`name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `presenter`
    FOREIGN KEY (`presenter` )
    REFERENCES `phoenix`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `producer`
    FOREIGN KEY (`producer` )
    REFERENCES `phoenix`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
CREATE INDEX `startTime` ON `phoenix`.`program-slot` (`startTime` ASC) ;
CREATE INDEX `name_program_slot` ON `phoenix`.`program-slot` (`program-name` ASC) ;
CREATE INDEX `dateOfProgram` ON `phoenix`.`program-slot` (`dateOfProgram` DESC) ;

-- -----------------------------------------------------
-- Insert Data For Table `phoenix`.`program-slot`
-- -----------------------------------------------------
INSERT INTO `phoenix`.`program-slot` VALUES (1, 'charity', '2018-08-30', '19:00:00', '00:30:00','dilbert', 'dilbert');
INSERT INTO `phoenix`.`program-slot` VALUES (2, 'news', '2018-08-30', '19:30:00', '00:30:00', 'dilbert', 'wally');
INSERT INTO `phoenix`.`program-slot` VALUES (3, 'news', '2018-08-31', '19:30:00', '00:30:00', 'dilbert', 'wally');

-- -----------------------------------------------------
-- Table `phoenix`.`weekly-schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`weekly-schedule` ;
