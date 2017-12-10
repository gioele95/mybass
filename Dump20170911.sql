CREATE DATABASE  IF NOT EXISTS `mybass` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mybass`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: mybass
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Temporary table structure for view `bagpergiorno`
--

DROP TABLE IF EXISTS `bagpergiorno`;
/*!50001 DROP VIEW IF EXISTS `bagpergiorno`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `bagpergiorno` (
  `bag` tinyint NOT NULL,
  `data` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `percentuali`
--

DROP TABLE IF EXISTS `percentuali`;
/*!50001 DROP VIEW IF EXISTS `percentuali`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `percentuali` (
  `totale` tinyint NOT NULL,
  `tecnica` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tabellacatture`
--

DROP TABLE IF EXISTS `tabellacatture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabellacatture` (
  `codicecattura` int(11) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `cattura` int(11) DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `tecnica` varchar(45) DEFAULT NULL,
  `esca` varchar(45) DEFAULT NULL,
  `coordinataX` double DEFAULT NULL,
  `coordinataY` double DEFAULT NULL,
  PRIMARY KEY (`codicecattura`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabellacatture`
--

LOCK TABLES `tabellacatture` WRITE;
/*!40000 ALTER TABLE `tabellacatture` DISABLE KEYS */;
INSERT INTO `tabellacatture` VALUES (1,'2017-08-10',1,3,'Finesse','Sculpo',957,185),(2,'2017-08-10',2,4,'Finesse','Pelagus',0,0),(3,'2017-08-10',3,5,'Finesse','Pelagus',0,0),(4,'2017-08-10',4,0,'','',0,0),(5,'2017-08-10',5,0,'','',0,0),(6,'2017-08-03',1,3,'Reazione','Trago',789,188),(7,'2017-08-08',1,3,'Finesse','Pelagus',0,0),(8,'2017-08-03',2,3,'Finesse','Pelagus',861,84),(9,'2017-08-03',3,0,'','',0,0),(10,'2017-08-03',4,0,'','',0,0),(11,'2017-08-03',5,0,'','',0,0),(12,'2017-08-08',2,3.8,'Reazione','Jubar',0,0),(13,'2017-08-08',3,2.5,'Finesse','Stylo',0,0),(14,'2017-08-08',4,0,'','',0,0),(15,'2017-08-08',5,0,'','',0,0),(16,'2017-08-28',1,2,'Reazione','Sculpo',790,224),(17,'2017-08-28',2,4.5,'Finesse','Cut tail',902,194),(18,'2017-08-28',3,1.7,'Gomma','Ultra vibe',0,0),(19,'2017-08-28',4,0,'','',0,0),(20,'2017-08-28',5,0,'','',0,0),(21,'2017-08-19',1,2.4,'Finesse','Sculpo',0,0);
/*!40000 ALTER TABLE `tabellacatture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mybass'
--

--
-- Dumping routines for database 'mybass'
--

--
-- Final view structure for view `bagpergiorno`
--

/*!50001 DROP TABLE IF EXISTS `bagpergiorno`*/;
/*!50001 DROP VIEW IF EXISTS `bagpergiorno`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bagpergiorno` AS select sum(`tabellacatture`.`peso`) AS `bag`,`tabellacatture`.`data` AS `data` from `tabellacatture` group by `tabellacatture`.`data` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `percentuali`
--

/*!50001 DROP TABLE IF EXISTS `percentuali`*/;
/*!50001 DROP VIEW IF EXISTS `percentuali`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `percentuali` AS select ((count(0) / (select count(0) from `tabellacatture` where ((`tabellacatture`.`tecnica` = 'gomma') or (`tabellacatture`.`tecnica` = 'reazione') or (`tabellacatture`.`tecnica` = 'finesse')))) * 100) AS `totale`,`tabellacatture`.`tecnica` AS `tecnica` from `tabellacatture` where ((`tabellacatture`.`tecnica` = 'gomma') or (`tabellacatture`.`tecnica` = 'reazione') or (`tabellacatture`.`tecnica` = 'finesse')) group by `tabellacatture`.`tecnica` order by `tabellacatture`.`tecnica` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-11 21:14:39
