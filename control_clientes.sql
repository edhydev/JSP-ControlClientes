CREATE SCHEMA `control_clientes` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE TABLE `control_clientes`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellidos` VARCHAR(90) NULL,
  `email` VARCHAR(255) NULL,
  `telefono` VARCHAR(45) NULL,
  `saldo` DOUBLE NULL,
  PRIMARY KEY (`id_cliente`));

INSERT INTO `control_clientes`.`cliente` (`nombre`, `apellidos`, `email`, `telefono`, `saldo`) VALUES ('Edgar', 'Jiménez Galicia', 'edgar.test@feikmail.com', '1234567891', '100');
INSERT INTO `control_clientes`.`cliente` (`nombre`, `apellidos`, `email`, `telefono`, `saldo`) VALUES ('Carmen', 'Galicia Escobar', 'carmen.test@feikmail.com', '1234567892', '150');
