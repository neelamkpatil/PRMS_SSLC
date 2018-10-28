-- -----------------------------------------------------
-- Table `phoenix`.`audit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`audit`;

CREATE  TABLE IF NOT EXISTS `phoenix`.`audit` (
  `id` int  auto_increment NOT NULL,
  `dateTime` TIMESTAMP NOT NULL ,
  `user` VARCHAR(40) NOT NULL ,
  `tableName` VARCHAR(45) NOT NULL ,
  `event` VARCHAR(15) NOT NULL ,
  `before` NVARCHAR(200) NULL ,
  `after` NVARCHAR(200)  NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE INDEX `dateTime` ON `phoenix`.`audit` (`dateTime` DESC) ;
CREATE INDEX `tableName` ON `phoenix`.`audit` (`tableName` ASC) ;

-- -----------------------------------------------------
-- Trigger `phoenix`.`TRG_PROGRAM`
-- -----------------------------------------------------

DROP trigger IF EXISTS TRG_PROGRAM_UPDATE;
DROP trigger IF EXISTS TRG_PROGRAM_INSERT;
DROP trigger IF EXISTS TRG_PROGRAM_DELETE;

delimiter //
CREATE TRIGGER TRG_PROGRAM_UPDATE 
AFTER UPDATE ON `phoenix`.`radio-program` 
FOR EACH ROW
BEGIN
INSERT INTO `phoenix`.`audit` ( `dateTime`, `user`, `tableName`,  `event`, `before`, `after` ) 
VALUES ( now(), user(), 'radio-program', 'update', CONCAT('name=', old.`name`, ', desc=', old.`desc`, ', dur=', old.`typicalDuration`),
 CONCAT('name=', new.`name`, ', desc=', new.`desc`, ', dur=', new.`typicalDuration`)); 

END;
//
delimiter ;

delimiter //
CREATE TRIGGER TRG_PROGRAM_INSERT 
AFTER INSERT ON `phoenix`.`radio-program` 
FOR EACH ROW
BEGIN
INSERT INTO `phoenix`.`audit` ( `dateTime`, `user`, `tableName`,  `event`, `before`, `after` ) 
VALUES ( now(), user(), 'radio-program', 'insert', '',
 CONCAT('name=', new.`name`, ', desc=', new.`desc`, ', dur=', new.`typicalDuration`)); 

END;
//
delimiter ;

delimiter //
CREATE TRIGGER TRG_PROGRAM_DELETE 
AFTER DELETE ON `phoenix`.`radio-program` 
FOR EACH ROW
BEGIN
INSERT INTO `phoenix`.`audit` ( `dateTime`, `user`, `tableName`,  `event`, `before`, `after` ) 
VALUES ( now(), user(), 'radio-program', 'delete', CONCAT('name=', old.`name`, ', desc=', old.`desc`, ', dur=', old.`typicalDuration`),
 ''); 

END;
//
delimiter ;

-- -----------------------------------------------------
-- Table `phoenix`.`login-audit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`login-audit`;

CREATE  TABLE IF NOT EXISTS `phoenix`.`login-audit` (
  `id` int  auto_increment NOT NULL,
  `dateTime` TIMESTAMP NOT NULL ,
  `user` VARCHAR(40) NOT NULL ,
  `appUserId` VARCHAR(40) NOT NULL ,
  `operation` VARCHAR(15) NOT NULL ,
  `isSuccess` VARCHAR(15) NOT NULL ,
  `role` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE INDEX `dateTime` ON `phoenix`.`login-audit` (`dateTime` DESC) ;
CREATE INDEX `appUserId` ON `phoenix`.`login-audit` (`appUserId` ASC) ;

-- -----------------------------------------------------
-- Table `phoenix`.`maintain-audit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `phoenix`.`maintain-audit`;

CREATE  TABLE IF NOT EXISTS `phoenix`.`maintain-audit` (
  `id` int  auto_increment NOT NULL,
  `dateTime` TIMESTAMP NOT NULL ,
  `user` VARCHAR(40) NOT NULL ,
  `appUserId` VARCHAR(40) NOT NULL ,
  `operation` VARCHAR(15) NOT NULL ,
  `isSuccess` VARCHAR(15) NOT NULL ,
  `key` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE INDEX `dateTime` ON `phoenix`.`maintain-audit` (`dateTime` DESC) ;
CREATE INDEX `appUserId` ON `phoenix`.`maintain-audit` (`appUserId` ASC) ;

