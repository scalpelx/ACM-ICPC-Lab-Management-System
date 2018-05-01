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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`acm` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `acm`;

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
  `scholar` varchar(15) collate utf8_bin NOT NULL default '' COMMENT '学号',
  `date` date NOT NULL COMMENT '日期',
  `arriveTime` time NOT NULL COMMENT '签到时间',
  `leaveTime` time NOT NULL COMMENT '签退时间',
  PRIMARY KEY  (`scholar`,`date`),
  KEY `FK_attendance` (`scholar`),
  CONSTRAINT `FK_attendance` FOREIGN KEY (`scholar`) REFERENCES `student` (`scholar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `attendance` */

insert  into `attendance`(`scholar`,`date`,`arriveTime`,`leaveTime`) values ('1','2018-04-16','02:00:00','23:00:00'),('20141222171','2018-04-15','02:00:00','23:00:00');

/*Table structure for table `evaluation` */

DROP TABLE IF EXISTS `evaluation`;

CREATE TABLE `evaluation` (
  `id` int(11) NOT NULL auto_increment COMMENT 'ID',
  `scholar` varchar(15) collate utf8_bin NOT NULL COMMENT '学生学号',
  `date` year(4) NOT NULL COMMENT '评价年份',
  `content` text collate utf8_bin COMMENT '内容',
  `self_eva` text collate utf8_bin COMMENT '自评',
  PRIMARY KEY  (`id`),
  KEY `FK_evaluation` (`scholar`),
  CONSTRAINT `FK_evaluation` FOREIGN KEY (`scholar`) REFERENCES `student` (`scholar`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `evaluation` */

/*Table structure for table `hibernate_sequences` */

DROP TABLE IF EXISTS `hibernate_sequences`;

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) collate utf8_bin default NULL,
  `sequence_next_hi_value` int(11) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hibernate_sequences` */

insert  into `hibernate_sequences`(`sequence_name`,`sequence_next_hi_value`) values ('train',1);

/*Table structure for table `stu_tr` */

DROP TABLE IF EXISTS `stu_tr`;

CREATE TABLE `stu_tr` (
  `id` int(11) NOT NULL auto_increment,
  `scholar` varchar(15) collate utf8_bin default NULL,
  `train_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_stu_tr_1_idx` (`scholar`),
  KEY `fk_stu_tr_2_idx` (`train_id`),
  CONSTRAINT `fk_stu_tr_1` FOREIGN KEY (`scholar`) REFERENCES `student` (`scholar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stu_tr_2` FOREIGN KEY (`train_id`) REFERENCES `train` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `stu_tr` */

insert  into `stu_tr`(`id`,`scholar`,`train_id`) values (7,'1',1);

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

insert  into `student`(`scholar`,`name`,`stdclass`,`passwd`,`phone`,`sex`,`hdu`) values ('1','test','1','1','1',1,'flycode'),('20141222171','谢永鹏','计科1404','123456','18515886123',0,'flycode');

/*Table structure for table `train` */

DROP TABLE IF EXISTS `train`;

CREATE TABLE `train` (
  `id` int(11) NOT NULL default '1' COMMENT '序号',
  `name` varchar(30) collate utf8_bin NOT NULL COMMENT '名称',
  `beginDate` datetime NOT NULL COMMENT '开始时间',
  `endDate` datetime NOT NULL COMMENT '结束时间',
  `problems` varchar(100) collate utf8_bin NOT NULL COMMENT '题目列表，分号分隔',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `train` */

insert  into `train`(`id`,`name`,`beginDate`,`endDate`,`problems`) values (1,'plan','2018-04-17 00:00:00','2018-04-17 00:00:00','1001 1044');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
