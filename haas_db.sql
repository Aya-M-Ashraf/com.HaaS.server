CREATE DATABASE  IF NOT EXISTS `haas_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `haas_db`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: haas_db
-- ------------------------------------------------------
-- Server version	5.0.51b-community-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `device_old_session_devices`
--

DROP TABLE IF EXISTS `device_old_session_devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_old_session_devices` (
  `host_device_id` int(20) NOT NULL,
  `guest_device_id` int(20) NOT NULL,
  `start_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `end_timestamp` timestamp NULL default NULL,
  `consumed_mb` double NOT NULL,
  PRIMARY KEY  (`host_device_id`,`guest_device_id`,`start_timestamp`),
  KEY `guest_device_FK` (`guest_device_id`),
  KEY `host_device_FK` (`host_device_id`),
  CONSTRAINT `guest_device_FK` FOREIGN KEY (`guest_device_id`) REFERENCES `device` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `host_device_FK` FOREIGN KEY (`host_device_id`) REFERENCES `device` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_old_session_devices`
--

LOCK TABLES `device_old_session_devices` WRITE;
/*!40000 ALTER TABLE `device_old_session_devices` DISABLE KEYS */;
/*!40000 ALTER TABLE `device_old_session_devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_transfer_coins_user`
--

DROP TABLE IF EXISTS `user_transfer_coins_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_transfer_coins_user` (
  `borrower_user_id` int(20) NOT NULL,
  `lender_user_id` int(20) NOT NULL,
  `transaction_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `coins_amount` int(11) NOT NULL,
  PRIMARY KEY  (`borrower_user_id`,`lender_user_id`,`transaction_timestamp`),
  KEY `borrower_FK` (`borrower_user_id`),
  KEY `lender_FK` (`lender_user_id`),
  CONSTRAINT `borrower_FK` FOREIGN KEY (`borrower_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `lender_FK` FOREIGN KEY (`lender_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_transfer_coins_user`
--

LOCK TABLES `user_transfer_coins_user` WRITE;
/*!40000 ALTER TABLE `user_transfer_coins_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_transfer_coins_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_uses_device`
--

DROP TABLE IF EXISTS `user_uses_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_uses_device` (
  `device_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  `start_using_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `end_using_timestamp` timestamp NULL default NULL,
  PRIMARY KEY  (`device_id`,`user_id`),
  KEY `user_FK` (`user_id`),
  KEY `device_FK` (`device_id`),
  CONSTRAINT `device_FK` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`),
  CONSTRAINT `user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_uses_device`
--

LOCK TABLES `user_uses_device` WRITE;
/*!40000 ALTER TABLE `user_uses_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_uses_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `device_id` int(20) NOT NULL auto_increment,
  `serial_number` varchar(100) NOT NULL,
  PRIMARY KEY  (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_currently_connected_devices`
--

DROP TABLE IF EXISTS `device_currently_connected_devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_currently_connected_devices` (
  `host_device_id` int(20) NOT NULL,
  `guest_device_id` int(20) NOT NULL,
  `start_timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `update_ver` int(10) NOT NULL,
  `consumed_mb` double NOT NULL,
  PRIMARY KEY  (`host_device_id`,`guest_device_id`),
  KEY `host_device_id_FK` (`host_device_id`),
  KEY `ghest_device_id_FK` (`guest_device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_currently_connected_devices`
--

LOCK TABLES `device_currently_connected_devices` WRITE;
/*!40000 ALTER TABLE `device_currently_connected_devices` DISABLE KEYS */;
/*!40000 ALTER TABLE `device_currently_connected_devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(20) NOT NULL auto_increment,
  `email` varchar(100) character set latin1 NOT NULL,
  `first_name` varchar(50) character set latin1 NOT NULL,
  `last_name` varchar(50) character set latin1 NOT NULL,
  `phone` varchar(50) character set latin1 NOT NULL,
  `password` varchar(50) character set latin1 NOT NULL,
  `golden_coins` double NOT NULL,
  `silver_coins` double NOT NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2016-05-09 22:24:31
