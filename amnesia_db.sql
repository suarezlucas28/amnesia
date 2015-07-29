/*
SQLyog Community v12.09 (64 bit)
MySQL - 5.5.20 : Database - amnesia
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`amnesia` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `amnesia`;

/*Table structure for table `articulo` */

DROP TABLE IF EXISTS `articulo`;

CREATE TABLE `articulo` (
  `art_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cla_codigo` int(10) unsigned NOT NULL,
  `art_descri` varchar(50) DEFAULT NULL,
  `art_pvemin` float(10,2) DEFAULT '0.00',
  `art_estado` varchar(15) DEFAULT 'AC',
  PRIMARY KEY (`art_codigo`),
  KEY `articulo_FKIndex1` (`cla_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `articulo` */

insert  into `articulo`(`art_codigo`,`cla_codigo`,`art_descri`,`art_pvemin`,`art_estado`) values (1,1,'BRAMA',12.00,'AC'),(2,2,'PICADA',30.00,'AC');

/*Table structure for table `cc_test` */

DROP TABLE IF EXISTS `cc_test`;

CREATE TABLE `cc_test` (
  `cco_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cli_codigo` int(10) unsigned NOT NULL,
  `cco_moncom` float(10,2) DEFAULT '0.00',
  `cco_monent` float(10,2) DEFAULT '0.00',
  `cco_segun` varchar(30) DEFAULT NULL,
  `cco_fecha` date DEFAULT NULL,
  `cco_estado` varchar(30) DEFAULT 'ACTIVO',
  PRIMARY KEY (`cco_codigo`),
  KEY `cc_test_FKIndex1` (`cli_codigo`),
  CONSTRAINT `cc_test_ibfk_1` FOREIGN KEY (`cli_codigo`) REFERENCES `cliente` (`cli_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `cc_test` */

insert  into `cc_test`(`cco_codigo`,`cli_codigo`,`cco_moncom`,`cco_monent`,`cco_segun`,`cco_fecha`,`cco_estado`) values (1,1,60.00,0.00,'venta 7','2015-06-24','ACTIVO'),(2,1,24.00,0.00,'venta 9','2015-06-24','ACTIVO'),(3,1,36.00,0.00,'venta 10','2015-06-24','ACTIVO'),(4,1,24.00,0.00,'venta 11','2015-06-24','ACTIVO');

/*Table structure for table `clasificacion` */

DROP TABLE IF EXISTS `clasificacion`;

CREATE TABLE `clasificacion` (
  `cla_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cla_descri` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cla_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `clasificacion` */

insert  into `clasificacion`(`cla_codigo`,`cla_descri`) values (1,'BEBIDAS'),(2,'MINUTAS'),(3,'OTROS');

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `cli_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cli_nomape` varchar(50) DEFAULT NULL,
  `cli_domici` varchar(30) DEFAULT NULL,
  `cli_telefo` varchar(30) DEFAULT NULL,
  `cli_estado` varchar(2) DEFAULT 'AC',
  PRIMARY KEY (`cli_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `cliente` */

insert  into `cliente`(`cli_codigo`,`cli_nomape`,`cli_domici`,`cli_telefo`,`cli_estado`) values (1,'LUCAS MARCELO','SDASD','123123','IN'),(2,'ASDASD','ASDASD','ASDAS','AC'),(3,'SIN CLIENTE',' ',' ','AC');

/*Table structure for table `mesas` */

DROP TABLE IF EXISTS `mesas`;

CREATE TABLE `mesas` (
  `mes_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mes_descri` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`mes_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `mesas` */

insert  into `mesas`(`mes_codigo`,`mes_descri`) values (1,'MESA UNO'),(2,'MESA DOS'),(3,'MESA TRES'),(4,'MESA CUATRO');

/*Table structure for table `pantallas` */

DROP TABLE IF EXISTS `pantallas`;

CREATE TABLE `pantallas` (
  `pan_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pan_descri` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pan_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `pantallas` */

insert  into `pantallas`(`pan_codigo`,`pan_descri`) values (1,'VENTA EN BARRA'),(2,'ANULAR VENTA'),(3,'COBRO'),(4,'ANULAR COBRO'),(5,'ARTICULOS'),(6,'CLIENTES'),(7,'PERMISOS'),(8,'INFORME VENTA'),(9,'INFORME COBRO'),(10,'INFORME CUENTA CORRIENTE'),(11,'MESAS'),(12,'VENTA EN MESAS');

/*Table structure for table `pedidos` */

DROP TABLE IF EXISTS `pedidos`;

CREATE TABLE `pedidos` (
  `ped_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mes_codigo` int(10) unsigned NOT NULL,
  `usu_codigo` int(10) unsigned NOT NULL,
  `ped_horfec` datetime DEFAULT NULL,
  `ped_estado` varchar(10) DEFAULT 'ACTIVO',
  `ped_total` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`ped_codigo`),
  KEY `fk_usaurios_pedidos` (`usu_codigo`),
  KEY `fk_mesa_pedidos` (`mes_codigo`),
  CONSTRAINT `fk_mesa_pedidos` FOREIGN KEY (`mes_codigo`) REFERENCES `mesas` (`mes_codigo`),
  CONSTRAINT `fk_usaurios_pedidos` FOREIGN KEY (`usu_codigo`) REFERENCES `usuarios` (`usu_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `pedidos` */

insert  into `pedidos`(`ped_codigo`,`mes_codigo`,`usu_codigo`,`ped_horfec`,`ped_estado`,`ped_total`) values (13,4,1,'2015-07-10 15:39:06','COBRADO',96.00),(14,3,1,'2015-07-10 15:39:11','ACTIVO',48.00),(15,2,1,'2015-07-10 15:39:16','ACTIVO',132.00),(16,1,1,'2015-07-10 15:39:20','ACTIVO',72.00),(17,4,1,'2015-07-10 15:46:53','ACTIVO',48.00);

/*Table structure for table `pedidos_detalle` */

DROP TABLE IF EXISTS `pedidos_detalle`;

CREATE TABLE `pedidos_detalle` (
  `ped_codigo` int(10) unsigned NOT NULL,
  `art_codigo` int(10) unsigned NOT NULL,
  `ped_cantid` int(10) unsigned DEFAULT NULL,
  `ped_subtot` float(10,2) DEFAULT '0.00',
  KEY `venta_has_articulo_FKIndex1` (`ped_codigo`),
  KEY `venta_has_articulo_FKIndex2` (`art_codigo`),
  CONSTRAINT `fk_detalle_pedido_articulo` FOREIGN KEY (`art_codigo`) REFERENCES `articulo` (`art_codigo`),
  CONSTRAINT `fk_detalle_pedido_pedido` FOREIGN KEY (`ped_codigo`) REFERENCES `pedidos` (`ped_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pedidos_detalle` */

insert  into `pedidos_detalle`(`ped_codigo`,`art_codigo`,`ped_cantid`,`ped_subtot`) values (13,1,1,12.00),(13,1,1,12.00),(13,1,1,12.00),(13,1,1,12.00),(13,1,1,12.00),(13,1,1,12.00),(13,1,1,12.00),(13,1,1,12.00),(14,1,1,12.00),(14,1,1,12.00),(14,1,1,12.00),(14,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(17,1,1,12.00),(17,1,1,12.00),(17,1,1,12.00),(17,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00),(15,1,1,12.00);

/*Table structure for table `permisos` */

DROP TABLE IF EXISTS `permisos`;

CREATE TABLE `permisos` (
  `usu_codigo` int(10) unsigned NOT NULL,
  `pan_codigo` int(10) unsigned NOT NULL,
  `per_permis` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`usu_codigo`,`pan_codigo`),
  KEY `usuarios_has_pantallas_FKIndex1` (`usu_codigo`),
  KEY `usuarios_has_pantallas_FKIndex2` (`pan_codigo`),
  CONSTRAINT `permisos_ibfk_1` FOREIGN KEY (`usu_codigo`) REFERENCES `usuarios` (`usu_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `permisos_ibfk_2` FOREIGN KEY (`pan_codigo`) REFERENCES `pantallas` (`pan_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `permisos` */

insert  into `permisos`(`usu_codigo`,`pan_codigo`,`per_permis`) values (2,1,1),(2,2,0),(2,3,0),(2,4,0),(2,5,0),(2,6,0),(2,7,0),(2,8,0),(2,9,0),(2,10,0);

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `usu_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usu_usuari` varchar(30) DEFAULT NULL,
  `usu_contra` varchar(100) DEFAULT NULL,
  `usu_estado` varchar(15) DEFAULT 'ACTIVO',
  PRIMARY KEY (`usu_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `usuarios` */

insert  into `usuarios`(`usu_codigo`,`usu_usuari`,`usu_contra`,`usu_estado`) values (1,'LUCAS','dc53fc4f621c80bdc2fa0329a6123708','ACTIVO'),(2,'OTRO','1c6bcd4e2b7bd75973d52207f63dbaa2','ACTIVO');

/*Table structure for table `venta` */

DROP TABLE IF EXISTS `venta`;

CREATE TABLE `venta` (
  `ven_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usu_codigo` int(10) unsigned NOT NULL,
  `cli_codigo` int(10) unsigned NOT NULL,
  `ven_horfec` datetime DEFAULT NULL,
  `ven_total` float(10,2) DEFAULT '0.00',
  `ven_estado` varchar(15) DEFAULT 'ACTIVA',
  PRIMARY KEY (`ven_codigo`),
  KEY `venta_FKIndex1` (`cli_codigo`),
  KEY `venta_FKIndex2` (`usu_codigo`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`cli_codigo`) REFERENCES `cliente` (`cli_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`usu_codigo`) REFERENCES `usuarios` (`usu_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `venta` */

insert  into `venta`(`ven_codigo`,`usu_codigo`,`cli_codigo`,`ven_horfec`,`ven_total`,`ven_estado`) values (16,1,3,'2015-07-10 15:39:06',96.00,'ACTIVA');

/*Table structure for table `venta_detalle` */

DROP TABLE IF EXISTS `venta_detalle`;

CREATE TABLE `venta_detalle` (
  `ven_codigo` int(10) unsigned NOT NULL,
  `art_codigo` int(10) unsigned NOT NULL,
  `vde_cantid` int(10) unsigned DEFAULT NULL,
  `vde_subtot` float(10,2) DEFAULT '0.00',
  KEY `venta_has_articulo_FKIndex1` (`ven_codigo`),
  KEY `venta_has_articulo_FKIndex2` (`art_codigo`),
  CONSTRAINT `FK_venta_detalle` FOREIGN KEY (`art_codigo`) REFERENCES `articulo` (`art_codigo`),
  CONSTRAINT `venta_detalle_ibfk_1` FOREIGN KEY (`ven_codigo`) REFERENCES `venta` (`ven_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `venta_detalle` */

insert  into `venta_detalle`(`ven_codigo`,`art_codigo`,`vde_cantid`,`vde_subtot`) values (16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00),(16,1,1,12.00);

/* Procedure structure for procedure `abm_articulos` */

/*!50003 DROP PROCEDURE IF EXISTS  `abm_articulos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `abm_articulos`(IN ope INT, IN codigo INT, IN descri VARCHAR(50), IN clasif INT, IN minori FLOAT(10,2))
BEGIN
	IF ope = 1 THEN
		INSERT INTO articulo (art_codigo,art_descri,cla_codigo,art_pvemin) VALUES (codigo,descri,clasif,minori);
	ELSE IF ope = 2 THEN
		UPDATE articulo SET art_estado = 'IN' WHERE art_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE articulo SET art_descri = descri, cla_codigo = clasif, art_pvemin = minori WHERE art_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `abm_clasificacion` */

/*!50003 DROP PROCEDURE IF EXISTS  `abm_clasificacion` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `abm_clasificacion`(IN ope INT, IN codigo INT, IN descri VARCHAR(30))
BEGIN
	IF ope = 1 THEN
		INSERT INTO clasificacion (cla_codigo,cla_descri) VALUES (codigo,descri);
	ELSE IF ope = 2 THEN
		delete from clasificacion WHERE cla_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE clasificacion SET cla_descri = descri wHERE cla_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `abm_cliente` */

/*!50003 DROP PROCEDURE IF EXISTS  `abm_cliente` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `abm_cliente`(IN ope INT, IN codigo INT, IN nombre VARCHAR(50), IN domici VARCHAR(30), IN telefo VARCHAR(30),in estado varchar(2))
BEGIN
	IF ope = 1 THEN
		INSERT INTO cliente (cli_codigo,cli_nomape,cli_domici,cli_telefo) VALUES (codigo,nombre,domici,telefo);
	ELSE IF ope = 2 THEN
		UPDATE cliente SET cli_estado = estado WHERE cli_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE cliente SET cli_nomape = nombre, cli_telefo = telefo, cli_domici = domici WHERE cli_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `abm_mesas` */

/*!50003 DROP PROCEDURE IF EXISTS  `abm_mesas` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `abm_mesas`(IN ope INT, IN codigo INT, IN descri VARCHAR(50))
BEGIN
	IF ope = 1 THEN
		INSERT INTO mesas (mes_codigo,mes_descri) VALUES (codigo,descri);
	ELSE IF ope = 2 THEN
		delete from mesas WHERE mes_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE mesas SET mes_descri = descri WHERE mes_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `abm_usuarios` */

/*!50003 DROP PROCEDURE IF EXISTS  `abm_usuarios` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `abm_usuarios`(IN ope INT, IN codigo INT, IN usuario VARCHAR(30), IN contraseña VARCHAR(100))
BEGIN
	IF ope = 1 THEN
		INSERT INTO usuarios (usu_codigo,usu_usuari,usu_contra) VALUES (codigo,usuario,contraseña);
	ELSE IF ope = 2 THEN
		UPDATE usuarios SET usu_estado = 'BLOQUEADO' WHERE usu_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE usuarios SET usu_usuari = usuario, usu_contra = contraseña WHERE usu_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
