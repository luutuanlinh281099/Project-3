-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: ecobike
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bike` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dung_luong_pin` float DEFAULT NULL,
  `parking_id` bigint(20) DEFAULT NULL,
  `trang_thai_thue` tinyint(4) DEFAULT NULL,
  `category_bike_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbu4yoyk3grhagplgar7ptfher` (`parking_id`),
  KEY `category_bike_id_idx` (`category_bike_id`),
  CONSTRAINT `FKbu4yoyk3grhagplgar7ptfher` FOREIGN KEY (`parking_id`) REFERENCES `parking` (`id`),
  CONSTRAINT `category_bike_id` FOREIGN KEY (`category_bike_id`) REFERENCES `category_bike` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bike`
--

LOCK TABLES `bike` WRITE;
/*!40000 ALTER TABLE `bike` DISABLE KEYS */;
INSERT INTO `bike` VALUES (1,6000,1,0,2),(2,0,2,0,1),(3,0,1,0,3),(4,300,1,0,2),(5,0,2,0,3),(6,0,1,0,1),(7,0,2,0,1);
/*!40000 ALTER TABLE `bike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_bike`
--

DROP TABLE IF EXISTS `category_bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_bike` (
  `id` bigint(20) NOT NULL,
  `loai_xe` varchar(45) DEFAULT NULL,
  `gia_xe` int(11) DEFAULT NULL,
  `image` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_bike`
--

LOCK TABLES `category_bike` WRITE;
/*!40000 ALTER TABLE `category_bike` DISABLE KEYS */;
INSERT INTO `category_bike` VALUES (1,'xe đơn thường',400000,'xedonthuong.jpg'),(2,'xe đơn điện',700000,'xedondien.jpeg'),(3,'xe đôi thường',550000,'xedoithuong.jpg');
/*!40000 ALTER TABLE `category_bike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tong_tien_thue` int(11) DEFAULT NULL,
  `rental_bike_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rental_bike_id_idx` (`rental_bike_id`),
  CONSTRAINT `rental_bike_id` FOREIGN KEY (`rental_bike_id`) REFERENCES `rental_bike` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (18,24000,28),(19,0,29),(20,15000,30),(21,15000,31),(22,0,32),(23,64000,33);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dia_chi` varchar(255) DEFAULT NULL,
  `so_o_trong` int(11) DEFAULT NULL,
  `ten_bai` varchar(255) DEFAULT NULL,
  `tong_o_chua` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (1,'Đường 1',3,'Bãi 1',9),(2,'Đường 2',0,'Bãi 2',10);
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental_bike`
--

DROP TABLE IF EXISTS `rental_bike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental_bike` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tien_coc` float DEFAULT NULL,
  `thoi_gian_ket_thuc_thue` varchar(255) DEFAULT NULL,
  `thoi_gian_bat_dau_thue` datetime DEFAULT NULL,
  `bike_id` bigint(20) DEFAULT NULL,
  `trang_thai` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8j4hpnk3jrnbr4y181i4emhax` (`bike_id`),
  CONSTRAINT `FK8j4hpnk3jrnbr4y181i4emhax` FOREIGN KEY (`bike_id`) REFERENCES `bike` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental_bike`
--

LOCK TABLES `rental_bike` WRITE;
/*!40000 ALTER TABLE `rental_bike` DISABLE KEYS */;
INSERT INTO `rental_bike` VALUES (28,280000,'2021-01-01 12:40:19.202','2021-01-01 11:51:56',1,1),(29,280000,'2021-01-03 07:09:25.761','2021-01-03 07:09:06',1,1),(30,280000,'2021-01-03 10:11:46.626','2021-01-03 09:57:35',1,1),(31,280000,'2021-01-03 11:11:56.318','2021-01-03 11:00:54',1,1),(32,280000,'2021-01-04 01:46:42.105','2021-01-04 01:46:12',4,1),(33,160000,'2021-01-04 07:52:49.027','2021-01-04 02:58:31',2,1);
/*!40000 ALTER TABLE `rental_bike` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-04 14:54:17
