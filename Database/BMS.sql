-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: theproject
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `Bill_No` int(20) NOT NULL AUTO_INCREMENT,
  `DateTime` datetime DEFAULT NULL,
  `Vat` int(3) DEFAULT NULL,
  PRIMARY KEY (`Bill_No`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (76,'2018-02-22 06:40:24',5),(77,'2018-02-22 06:50:42',5),(78,'2018-02-22 06:51:11',5),(79,'2018-02-22 06:51:55',5),(80,'2018-02-22 06:53:01',5),(81,'2018-02-22 06:53:06',5);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_product`
--

DROP TABLE IF EXISTS `bill_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_product` (
  `Bill_No` int(20) DEFAULT NULL,
  `Pro_No` int(5) DEFAULT NULL,
  `Q` int(5) DEFAULT NULL,
  `Price` int(6) DEFAULT NULL,
  KEY `Pro_No` (`Pro_No`),
  KEY `Bill_No` (`Bill_No`),
  CONSTRAINT `bill_product_ibfk_2` FOREIGN KEY (`Pro_No`) REFERENCES `product` (`pro_No`),
  CONSTRAINT `bill_product_ibfk_3` FOREIGN KEY (`Bill_No`) REFERENCES `bill` (`Bill_No`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_product`
--

LOCK TABLES `bill_product` WRITE;
/*!40000 ALTER TABLE `bill_product` DISABLE KEYS */;
INSERT INTO `bill_product` VALUES (76,9,3,8),(77,15,1,8),(77,12,4,5),(78,12,3,5),(79,9,4,8),(79,12,3,5),(80,15,3,8),(81,10,2,17);
/*!40000 ALTER TABLE `bill_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `confg`
--

DROP TABLE IF EXISTS `confg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `confg` (
  `Confg_No` int(1) NOT NULL,
  `Password` varchar(30) DEFAULT NULL,
  `Vat` int(3) DEFAULT NULL,
  `Currency_Code` char(3) DEFAULT NULL,
  PRIMARY KEY (`Confg_No`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `confg`
--

LOCK TABLES `confg` WRITE;
/*!40000 ALTER TABLE `confg` DISABLE KEYS */;
INSERT INTO `confg` VALUES (0,'1234',5,'USD');
/*!40000 ALTER TABLE `confg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `User_ID` int(5) NOT NULL AUTO_INCREMENT,
  `Password` varchar(30) DEFAULT NULL,
  `Actual_Name` varchar(40) DEFAULT NULL,
  `Availability` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (5,'8','Espresso',NULL),(6,'0258','Bassam Ali',NULL),(7,'159','Hamed',NULL),(8,'111','AA',NULL),(15,'1234','Sultan Bassam Albusaymi','ON'),(16,'1248','Bassam Ahmed','ON');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_bill`
--

DROP TABLE IF EXISTS `employee_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_bill` (
  `User_ID` int(9) DEFAULT NULL,
  `Bill_No` int(20) DEFAULT NULL,
  KEY `User_ID` (`User_ID`),
  KEY `Bill_No` (`Bill_No`),
  CONSTRAINT `employee_bill_ibfk_3` FOREIGN KEY (`Bill_No`) REFERENCES `bill` (`Bill_No`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_bill`
--

LOCK TABLES `employee_bill` WRITE;
/*!40000 ALTER TABLE `employee_bill` DISABLE KEYS */;
INSERT INTO `employee_bill` VALUES (15,76),(15,77),(15,78),(15,79),(15,80),(15,81);
/*!40000 ALTER TABLE `employee_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `pro_No` int(7) NOT NULL AUTO_INCREMENT,
  `Pro_Name` varchar(40) DEFAULT NULL,
  `Price` decimal(6,2) DEFAULT NULL,
  `Availability` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`pro_No`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (9,'Arabic Coffee',8.00,'ON'),(10,'Espresso',17.50,'ON'),(12,'Chocolate  Cookie',5.50,'ON'),(15,'Moca',8.00,'ON'),(17,'Black Tea',2.70,'ON');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-22  6:55:13
