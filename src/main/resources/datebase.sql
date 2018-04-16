/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.22-community : Database - acm
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ACM` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `ACM`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` varchar(15) collate utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(15) collate utf8_bin NOT NULL COMMENT '密码',
  `name` varchar(45) collate utf8_bin NOT NULL COMMENT '姓名',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `admin` */

insert  into `admin`(`id`,`password`,`name`) values ('a','a','admin');

/*Table structure for table `attendance` */

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL auto_increment COMMENT '序号',
  `scholar` varchar(15) collate utf8_bin NOT NULL default '' COMMENT '学号',
  `date` date NOT NULL COMMENT '日期',
  `arriveTime` time NOT NULL COMMENT '签到时间',
  `leaveTime` time NOT NULL COMMENT '签退时间',
  PRIMARY KEY  (`id`),
  KEY `FK_attendance` (`scholar`),
  CONSTRAINT `FK_attendance` FOREIGN KEY (`scholar`) REFERENCES `student` (`scholar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `attendance` */

insert  into `attendance`(`id`,`scholar`,`date`,`arriveTime`,`leaveTime`) values (1,'1','2018-04-11','01:00:00','00:59:00'),(2,'1','2018-04-11','01:00:00','23:58:00'),(3,'1','2018-04-11','01:00:00','23:59:00');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `scholar` varchar(15) collate utf8_bin NOT NULL COMMENT '学号',
  `name` varchar(20) collate utf8_bin NOT NULL COMMENT '姓名',
  `stdclass` varchar(20) character set utf8 NOT NULL COMMENT '班级',
  `passwd` varchar(20) collate utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(20) collate utf8_bin NOT NULL COMMENT '手机',
  `sex` int(11) NOT NULL COMMENT '性别（0男1女）',
  `hdu` varchar(20) collate utf8_bin NOT NULL COMMENT '杭电账号',
  PRIMARY KEY  (`scholar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `student` */

insert  into `student`(`scholar`,`name`,`stdclass`,`passwd`,`phone`,`sex`,`hdu`) values ('1','test','1','1','1',1,'1'),('20141222171','谢永鹏','计科1404','123456','18515886123',0,'flycode');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
