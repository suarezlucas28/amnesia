-- MySQL dump 10.13  Distrib 5.5.20, for Win32 (x86)
--
-- Host: localhost    Database: amnesia
-- ------------------------------------------------------
-- Server version	5.5.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `amnesia`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `amnesia` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `amnesia`;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo` (
  `art_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pro_codigo` int(10) unsigned NOT NULL,
  `cla_codigo` int(10) unsigned NOT NULL,
  `art_descri` varchar(50) DEFAULT NULL,
  `art_pcosto` float(10,2) DEFAULT '0.00',
  `art_pvemin` float(10,2) DEFAULT '0.00',
  `art_pvemay` float(10,2) DEFAULT '0.00',
  `art_estado` varchar(15) DEFAULT 'ACTIVO',
  `art_canfar` int(10) DEFAULT NULL,
  `art_cosuni` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`art_codigo`),
  KEY `articulo_FKIndex1` (`cla_codigo`),
  KEY `articulo_FKIndex2` (`pro_codigo`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`cla_codigo`) REFERENCES `clasificacion` (`cla_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`pro_codigo`) REFERENCES `proveedor` (`pro_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clasificacion`
--

DROP TABLE IF EXISTS `clasificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clasificacion` (
  `cla_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cla_descri` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cla_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificacion`
--

LOCK TABLES `clasificacion` WRITE;
/*!40000 ALTER TABLE `clasificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `clasificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `cli_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cli_nomape` varchar(50) DEFAULT NULL,
  `cli_domici` varchar(30) DEFAULT NULL,
  `cli_telefo` varchar(30) DEFAULT NULL,
  `cli_cuit` varchar(30) DEFAULT NULL,
  `cli_telef1` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cli_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pantallas`
--

DROP TABLE IF EXISTS `pantallas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pantallas` (
  `pan_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pan_descri` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pan_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pantallas`
--

LOCK TABLES `pantallas` WRITE;
/*!40000 ALTER TABLE `pantallas` DISABLE KEYS */;
/*!40000 ALTER TABLE `pantallas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `pro_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pro_nombre` varchar(30) DEFAULT NULL,
  `pro_telefo` varchar(30) DEFAULT NULL,
  `pro_domici` varchar(50) DEFAULT NULL,
  `pro_correo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pro_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `usu_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usu_usuari` varchar(30) DEFAULT NULL,
  `usu_contra` varchar(100) DEFAULT NULL,
  `usu_estado` varchar(15) DEFAULT 'ACTIVO',
  PRIMARY KEY (`usu_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','ACTIVO');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'amnesia'
--
/*!50003 DROP PROCEDURE IF EXISTS `abm_articulos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_articulos`(IN ope INT, IN codigo INT, IN descri VARCHAR(50), IN clasif INT, IN provee INT, IN costo FLOAT(10,2), IN minori FLOAT(10,2), IN mayori FLOAT(10,2), IN canti INT, in cosuni float(10,2), in ndepo int)
BEGIN
	DECLARE v1 INT DEFAULT 1;
	IF ope = 1 THEN
		INSERT INTO articulo (art_codigo,art_descri,pro_codigo,cla_codigo,art_pcosto,art_pvemin,art_pvemay,art_canfar,art_cosuni) VALUES (codigo,descri,provee,clasif,costo,minori,mayori,canti,cosuni);
		WHILE v1 <= ndepo DO
			INSERT INTO stock (art_codigo,dep_codigo) VALUES (codigo,v1);
			SET v1 = v1 + 1;
		END WHILE;
	ELSE IF ope = 2 THEN
		UPDATE articulo SET art_estado = 'ANULADO' WHERE art_codigo = codigo;
		UPDATE stock SET art_canact = 0, art_canven = 0, art_canaju = 0 WHERE art_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE articulo SET art_descri = descri, pro_codigo = provee, cla_codigo = clasif, art_pcosto = costo, art_pvemin = minori, art_pvemay = mayori,art_canfar = canti,art_cosuni = cosuni WHERE art_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `abm_clasificacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_clasificacion`(IN ope INT, IN codigo INT, IN descri VARCHAR(30))
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
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `abm_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_cliente`(IN ope INT, IN codigo INT, IN nombre VARCHAR(50), IN domici VARCHAR(30), IN telefo VARCHAR(30), IN telef1 VARCHAR(30), IN cuit VARCHAR(30))
BEGIN
	IF ope = 1 THEN
		INSERT INTO cliente (cli_codigo,cli_nomape,cli_domici,cli_telefo,cli_telef1,cli_cuit) VALUES (codigo,nombre,domici,telefo,telef1,cuit);
	ELSE IF ope = 2 THEN
		DELETE FROM cliente WHERE cli_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE cliente SET cli_nomape = nombre, cli_telefo = telefo, cli_domici = domici,cli_telef1 = telef1, cli_cuit = cuit WHERE cli_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `abm_proveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_proveedor`(IN ope INT, IN codigo INT, IN nombre VARCHAR(30), IN telefo VARCHAR(30), in domici varchar(50), in correo varchar (100))
BEGIN
	IF ope = 1 THEN
		INSERT INTO proveedor (pro_codigo,pro_nombre,pro_telefo,pro_domici,pro_correo) VALUES (codigo,nombre,telefo,domici,correo);
	ELSE IF ope = 2 THEN
		delete from proveedor where pro_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE proveedor SET pro_nombre = nombre, pro_telefo = telefo, pro_domici = domici, pro_correo = correo WHERE pro_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `abm_usuarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_usuarios`(IN ope INT, IN codigo INT, IN usuario VARCHAR(30), IN contraseña VARCHAR(100))
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
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-14 15:35:11
-- MySQL dump 10.13  Distrib 5.5.20, for Win32 (x86)
--
-- Host: localhost    Database: amnesia
-- ------------------------------------------------------
-- Server version	5.5.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `amnesia`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `amnesia` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `amnesia`;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo` (
  `art_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pro_codigo` int(10) unsigned NOT NULL,
  `cla_codigo` int(10) unsigned NOT NULL,
  `art_descri` varchar(50) DEFAULT NULL,
  `art_pcosto` float(10,2) DEFAULT '0.00',
  `art_pvemin` float(10,2) DEFAULT '0.00',
  `art_pvemay` float(10,2) DEFAULT '0.00',
  `art_estado` varchar(15) DEFAULT 'ACTIVO',
  `art_canfar` int(10) DEFAULT NULL,
  `art_cosuni` float(10,2) DEFAULT '0.00',
  PRIMARY KEY (`art_codigo`),
  KEY `articulo_FKIndex1` (`cla_codigo`),
  KEY `articulo_FKIndex2` (`pro_codigo`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`cla_codigo`) REFERENCES `clasificacion` (`cla_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`pro_codigo`) REFERENCES `proveedor` (`pro_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clasificacion`
--

DROP TABLE IF EXISTS `clasificacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clasificacion` (
  `cla_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cla_descri` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cla_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clasificacion`
--

LOCK TABLES `clasificacion` WRITE;
/*!40000 ALTER TABLE `clasificacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `clasificacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `cli_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cli_nomape` varchar(50) DEFAULT NULL,
  `cli_domici` varchar(30) DEFAULT NULL,
  `cli_telefo` varchar(30) DEFAULT NULL,
  `cli_cuit` varchar(30) DEFAULT NULL,
  `cli_telef1` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cli_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pantallas`
--

DROP TABLE IF EXISTS `pantallas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pantallas` (
  `pan_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pan_descri` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pan_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pantallas`
--

LOCK TABLES `pantallas` WRITE;
/*!40000 ALTER TABLE `pantallas` DISABLE KEYS */;
/*!40000 ALTER TABLE `pantallas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `pro_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pro_nombre` varchar(30) DEFAULT NULL,
  `pro_telefo` varchar(30) DEFAULT NULL,
  `pro_domici` varchar(50) DEFAULT NULL,
  `pro_correo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pro_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `usu_codigo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usu_usuari` varchar(30) DEFAULT NULL,
  `usu_contra` varchar(100) DEFAULT NULL,
  `usu_estado` varchar(15) DEFAULT 'ACTIVO',
  PRIMARY KEY (`usu_codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','ACTIVO');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'amnesia'
--
/*!50003 DROP PROCEDURE IF EXISTS `abm_articulos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_articulos`(IN ope INT, IN codigo INT, IN descri VARCHAR(50), IN clasif INT, IN provee INT, IN costo FLOAT(10,2), IN minori FLOAT(10,2), IN mayori FLOAT(10,2), IN canti INT, in cosuni float(10,2), in ndepo int)
BEGIN
	DECLARE v1 INT DEFAULT 1;
	IF ope = 1 THEN
		INSERT INTO articulo (art_codigo,art_descri,pro_codigo,cla_codigo,art_pcosto,art_pvemin,art_pvemay,art_canfar,art_cosuni) VALUES (codigo,descri,provee,clasif,costo,minori,mayori,canti,cosuni);
		WHILE v1 <= ndepo DO
			INSERT INTO stock (art_codigo,dep_codigo) VALUES (codigo,v1);
			SET v1 = v1 + 1;
		END WHILE;
	ELSE IF ope = 2 THEN
		UPDATE articulo SET art_estado = 'ANULADO' WHERE art_codigo = codigo;
		UPDATE stock SET art_canact = 0, art_canven = 0, art_canaju = 0 WHERE art_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE articulo SET art_descri = descri, pro_codigo = provee, cla_codigo = clasif, art_pcosto = costo, art_pvemin = minori, art_pvemay = mayori,art_canfar = canti,art_cosuni = cosuni WHERE art_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `abm_clasificacion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_clasificacion`(IN ope INT, IN codigo INT, IN descri VARCHAR(30))
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
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `abm_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_cliente`(IN ope INT, IN codigo INT, IN nombre VARCHAR(50), IN domici VARCHAR(30), IN telefo VARCHAR(30), IN telef1 VARCHAR(30), IN cuit VARCHAR(30))
BEGIN
	IF ope = 1 THEN
		INSERT INTO cliente (cli_codigo,cli_nomape,cli_domici,cli_telefo,cli_telef1,cli_cuit) VALUES (codigo,nombre,domici,telefo,telef1,cuit);
	ELSE IF ope = 2 THEN
		DELETE FROM cliente WHERE cli_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE cliente SET cli_nomape = nombre, cli_telefo = telefo, cli_domici = domici,cli_telef1 = telef1, cli_cuit = cuit WHERE cli_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `abm_proveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_proveedor`(IN ope INT, IN codigo INT, IN nombre VARCHAR(30), IN telefo VARCHAR(30), in domici varchar(50), in correo varchar (100))
BEGIN
	IF ope = 1 THEN
		INSERT INTO proveedor (pro_codigo,pro_nombre,pro_telefo,pro_domici,pro_correo) VALUES (codigo,nombre,telefo,domici,correo);
	ELSE IF ope = 2 THEN
		delete from proveedor where pro_codigo = codigo;
	ELSE IF ope = 3 THEN
		UPDATE proveedor SET pro_nombre = nombre, pro_telefo = telefo, pro_domici = domici, pro_correo = correo WHERE pro_codigo = codigo;
	END IF;
	END IF;
	END IF;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `abm_usuarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `abm_usuarios`(IN ope INT, IN codigo INT, IN usuario VARCHAR(30), IN contraseña VARCHAR(100))
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
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-14 15:35:55
