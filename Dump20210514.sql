-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: paf_project
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `adminId` int NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1000),(1020),(1028),(1032),(1054);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `clientId` int NOT NULL,
  PRIMARY KEY (`clientId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1003),(1004),(1012),(1013),(1014),(1029),(1030),(1031),(1034),(1035),(1036),(1037),(1038),(1039),(1040),(1041),(1042),(1043),(1044),(1045),(1046),(1047),(1048),(1049),(1050),(1052),(1056),(1059),(1060);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `researcher`
--

DROP TABLE IF EXISTS `researcher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `researcher` (
  `researcherId` int NOT NULL,
  PRIMARY KEY (`researcherId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `researcher`
--

LOCK TABLES `researcher` WRITE;
/*!40000 ALTER TABLE `researcher` DISABLE KEYS */;
INSERT INTO `researcher` VALUES (1001),(1002),(1021),(1022),(1023),(1024),(1025),(1026),(1027),(1033),(1051),(1053),(1055),(1057),(1058);
/*!40000 ALTER TABLE `researcher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `cardNumber` bigint DEFAULT NULL,
  `CVV` int DEFAULT NULL,
  `expDate` date DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=1061 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1005,'oooo','lolol','New Name user','999.99',13231313,1234,NULL,'Updated pw'),(1006,'hamal_4','dwwa@gmail.com','fff','ggg',123456,5558,NULL,'hfghfg'),(1007,'New Name user','999.99','fdfda','lolol',13231313,1234,NULL,'Updated pw'),(1008,'34','asd','sad','rrr',12154654,2123,NULL,'adasd'),(1009,'rtyuu','uuuu','jjjj',NULL,456456,556,NULL,'hhhh'),(1011,'siri','das@gamil.com','siriaa',NULL,1326546,454,'2021-02-28','123qwe'),(1015,'researcher1','asdd@gamil.com','jkjk',NULL,1326546,454,'2021-02-28','123qwe'),(1016,'researcher1','asdd@gamil.com','jkjk',NULL,1326546,454,'2021-02-28','123qwe'),(1017,'admin1','asdd@gamil.com','jkjk',NULL,1326546,454,'2021-02-28','123qwe'),(1018,'admin1','asdd@gamil.com','jkjk',NULL,1326546,454,'2021-02-28','123qwe'),(1019,'admin1','asdd@gamil.com','jkjk',NULL,1326546,454,'2021-02-28','123qwe'),(1028,'hiru_123','hiru.99@gmail.com','Hiruni','Gamage',7458963215874596,451,'2024-10-23','456'),(1032,'Nimal_456','nimal12%2540gmail.com','Nimal','Perera',8574963125874586,854,'2025-10-30','555'),(1034,'Amali_n','amali.99%2540gmail.com','Amal','Gunarathne',1236587459632574,555,'2023-05-23','ggez'),(1049,'unam1','sadg','fname','lname',1287587436559658,255,'2021-12-21','qwe'),(1050,'user1','use1@gmail.com','Ru','Gune',5687459632154856,125,'2021-05-26','asd'),(1052,'client1','cl%40gmail.com','cl','client',3254875965485412,125,'2015-02-25','1234'),(1055,'gayani_l','gayani@gmail.com','Gayani','Fernando',4563125795412365,235,'2026-11-10','asdf'),(1056,'Har_1','asd@hma','H','A',1236458796523584,123,'2021-03-06','444'),(1057,'gaya','gayani@gmail.com','Gayani','Perera',4563125795412365,235,'2026-11-10','asdf'),(1058,'gaya','gayani@gmail.com','Gayani','Perera',4563125795412365,235,'2026-11-10','asdf'),(1059,'gaya','gayani@gmail.com','Gayani','Perera',4563125795412365,235,'2026-11-10','asdf');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-14 19:46:16
