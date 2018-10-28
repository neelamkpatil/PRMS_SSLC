CREATE USER IF NOT EXISTS 'phoenix'@'localhost' IDENTIFIED BY 'password';
-- GRANT ALL PRIVILEGES ON phoenix.* TO 'phoenix'@'localhost';

REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'phoenix'@'localhost';

GRANT SELECT, INSERT, UPDATE, DELETE on `phoenix`.`radio-program`  to 'phoenix'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE on `phoenix`.`program-slot`  to 'phoenix'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE on `phoenix`.`user`  to 'phoenix'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE on `phoenix`.`role`  to 'phoenix'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE on `phoenix`.`login-audit`  to 'phoenix'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE on `phoenix`.`maintain-audit`  to 'phoenix'@'localhost';
flush privileges;

-- Check grants in console
-- show grants for 'phoenix'@'localhost';

CREATE USER IF NOT EXISTS 'phadmin'@'localhost' IDENTIFIED BY 'phadmin';
GRANT ALL PRIVILEGES ON phoenix.* TO 'phadmin'@'localhost';
flush privileges;
