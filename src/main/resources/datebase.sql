CREATE DATABASE  IF NOT EXISTS `ACM` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `ACM`;
-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: ACM
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('a','a','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `scholar` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '学号',
  `date` date NOT NULL COMMENT '日期',
  `arriveTime` time NOT NULL COMMENT '签到时间',
  `leaveTime` time NOT NULL COMMENT '签退时间',
  PRIMARY KEY (`scholar`,`date`),
  KEY `FK_attendance` (`scholar`),
  CONSTRAINT `FK_attendance` FOREIGN KEY (`scholar`) REFERENCES `student` (`scholar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES ('1','2018-04-16','02:00:00','23:00:00');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('train',2);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stu_tr`
--

DROP TABLE IF EXISTS `stu_tr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stu_tr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scholar` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `train_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stu_tr_1_idx` (`scholar`),
  KEY `fk_stu_tr_2_idx` (`train_id`),
  CONSTRAINT `fk_stu_tr_1` FOREIGN KEY (`scholar`) REFERENCES `student` (`scholar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stu_tr_2` FOREIGN KEY (`train_id`) REFERENCES `train` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stu_tr`
--

LOCK TABLES `stu_tr` WRITE;
/*!40000 ALTER TABLE `stu_tr` DISABLE KEYS */;
INSERT INTO `stu_tr` VALUES (8,'1',32769),(9,'20141222171',32769);
/*!40000 ALTER TABLE `stu_tr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `scholar` varchar(15) COLLATE utf8_bin NOT NULL COMMENT '学号',
  `name` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `stdclass` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '班级',
  `passwd` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '手机',
  `sex` int(11) NOT NULL COMMENT '性别（0男1女）',
  `hdu` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '杭电账号',
  PRIMARY KEY (`scholar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('1','test','1','1','1',1,'1'),('20141222171','谢永鹏','计科1404','123456','18515886123',0,'flycode');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `train` (
  `id` int(11) NOT NULL DEFAULT '1' COMMENT '序号',
  `name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `beginDate` datetime NOT NULL COMMENT '开始时间',
  `endDate` datetime NOT NULL COMMENT '结束时间',
  `problems` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '题目列表，分号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (32769,'test','2018-04-17 00:00:00','2018-04-17 00:00:00','1001');
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-17 21:03:47
