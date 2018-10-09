CREATE DATABASE  IF NOT EXISTS `sponsorsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sponsorsdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sponsorsdb
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `candidate_status_change_log`
--

DROP TABLE IF EXISTS `candidate_status_change_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidate_status_change_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL,
  `case_status_id` bigint(20) NOT NULL,
  `candidate_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfe9aynwv9tp6rj8ummvttvgmt` (`candidate_id`),
  CONSTRAINT `FKfe9aynwv9tp6rj8ummvttvgmt` FOREIGN KEY (`candidate_id`) REFERENCES `candidates` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate_status_change_log`
--

LOCK TABLES `candidate_status_change_log` WRITE;
/*!40000 ALTER TABLE `candidate_status_change_log` DISABLE KEYS */;
INSERT INTO `candidate_status_change_log` VALUES (13,'2018-10-08 19:17:45','2018-10-08 19:18:06',2,3),(14,'2018-10-08 19:17:45','2018-10-08 19:18:06',2,4),(15,'2018-10-08 19:17:45','2018-10-08 19:18:06',2,5),(16,'2018-10-08 19:18:06','2018-10-08 19:18:19',3,3),(17,'2018-10-08 19:18:06','2018-10-08 19:18:19',3,4),(18,'2018-10-08 19:18:06','2018-10-08 19:18:19',3,5),(19,'2018-10-08 19:18:19',NULL,4,3),(20,'2018-10-08 19:18:19',NULL,4,4),(21,'2018-10-08 19:18:19',NULL,5,5);
/*!40000 ALTER TABLE `candidate_status_change_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidates`
--

DROP TABLE IF EXISTS `candidates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidates` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `sponsored_by` bigint(20) NOT NULL,
  `internal_reference` varchar(255) DEFAULT NULL,
  `assigned_veeting_officer` bigint(20) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `case_status_id` smallint(5) NOT NULL,
  `activation_code` varchar(255) DEFAULT NULL,
  `activations_code_expiration` timestamp NULL DEFAULT NULL,
  `interview_audio` varchar(255) DEFAULT NULL,
  `interview_transcript` varchar(255) DEFAULT NULL,
  `interview_sar` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` bigint(20) NOT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CANDIDATES_ACTIVATION_COD` (`activation_code`),
  KEY `FK_CANDIDATES_USERS_idx` (`user_id`),
  KEY `FK_CANDIDATES_SPONSORS_idx` (`sponsored_by`),
  KEY `FK_CANDIDATES_CASE_STATUS_idx` (`case_status_id`),
  KEY `FKgsdob1t2b7no84ccg0xb71nx` (`assigned_veeting_officer`),
  KEY `FK62x7sm60567tfu13wc64kmhcp` (`created_by`),
  KEY `FKbmmywwhfgvd28rgdmuc7v8y9e` (`updated_by`),
  CONSTRAINT `FK62x7sm60567tfu13wc64kmhcp` FOREIGN KEY (`created_by`) REFERENCES `staff` (`id`),
  CONSTRAINT `FK_CANDIDATES_CASE_STATUS` FOREIGN KEY (`case_status_id`) REFERENCES `case_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CANDIDATES_SPONSORS` FOREIGN KEY (`sponsored_by`) REFERENCES `sponsors` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CANDIDATES_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKbmmywwhfgvd28rgdmuc7v8y9e` FOREIGN KEY (`updated_by`) REFERENCES `staff` (`id`),
  CONSTRAINT `FKgsdob1t2b7no84ccg0xb71nx` FOREIGN KEY (`assigned_veeting_officer`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidates`
--

LOCK TABLES `candidates` WRITE;
/*!40000 ALTER TABLE `candidates` DISABLE KEYS */;
INSERT INTO `candidates` VALUES (3,1,1,NULL,NULL,'test',4,'ABC','2018-10-22 19:15:27',NULL,NULL,NULL,'2018-10-08 19:09:14','2018-10-08 19:18:19',1,NULL),(4,2,2,NULL,NULL,'COMMENTS',4,'XYZ','2018-10-22 19:16:27',NULL,NULL,NULL,'2018-10-08 19:16:27','2018-10-08 19:18:19',3,NULL),(5,3,2,NULL,NULL,'COMMETNS2',5,'POI','2018-10-22 19:17:02',NULL,NULL,NULL,'2018-10-08 19:17:02','2018-10-08 19:18:19',3,NULL);
/*!40000 ALTER TABLE `candidates` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER handle_expiration_date BEFORE INSERT ON `candidates` 
FOR EACH ROW SET
    NEW.created_at = IFNULL(NEW.created_at, NOW()),
    NEW.activations_code_expiration = TIMESTAMPADD(DAY, 14, NOW()) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER case_status_log_after_insert AFTER INSERT ON `candidates` 
FOR EACH ROW 
   BEGIN
    insert into candidate_status_change_log (start_time,case_status_id, candidate_id) values (NOW(), NEW.case_status_id, NEW.id);
   END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER case_status_change_log_after_update AFTER UPDATE ON `candidates` 
FOR EACH ROW 
BEGIN
  IF(OLD.case_status_id != NEW.case_status_id) THEN
	UPDATE candidate_status_change_log
	SET end_time = NOW()
	WHERE end_time is null 
    AND candidate_id =  NEW.id 
	AND id > 0;
  
	INSERT INTO candidate_status_change_log (start_time,case_status_id, candidate_id) values (NOW(), NEW.case_status_id, NEW.id);
  END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `case_status`
--

DROP TABLE IF EXISTS `case_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `case_status` (
  `id` smallint(5) NOT NULL AUTO_INCREMENT,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CASE_STATUS_STATUS` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case_status`
--

LOCK TABLES `case_status` WRITE;
/*!40000 ALTER TABLE `case_status` DISABLE KEYS */;
INSERT INTO `case_status` VALUES (4,'Assigned to VO'),(9,'Complete'),(2,'In interview progress'),(5,'In QA Review'),(3,'Interview completed'),(6,'QA review complete'),(1,'Ready for interview'),(8,'Report Send to Sponsor'),(7,'Report Send to Vetting Manager');
/*!40000 ALTER TABLE `case_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1),(1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ROLES_NAME` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (4,'CANDIDATE'),(1,'SPONSOR_MANAGERS'),(2,'SPONSOR_OFFICERS'),(3,'SPONSOR_RECIPIENT');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsors`
--

DROP TABLE IF EXISTS `sponsors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sponsors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organization_name` varchar(255) NOT NULL,
  `country_code` varchar(45) NOT NULL,
  `phone_number` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_SPONSORS_PHONE` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsors`
--

LOCK TABLES `sponsors` WRITE;
/*!40000 ALTER TABLE `sponsors` DISABLE KEYS */;
INSERT INTO `sponsors` VALUES (1,'Sponosor1','US',8851238402,'New York'),(2,'Sponosor2','US',8851238403,'Las, Vegas');
/*!40000 ALTER TABLE `sponsors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `sponsor_id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_STAFF_SPONSORS_idx` (`sponsor_id`),
  KEY `FK_STAFF_USERS_idx` (`user_id`),
  KEY `FK_STAFF_USERS_CREATD_BY_idx` (`created_by`),
  KEY `FK_STAFF_USERS_UPDATED_BY_idx` (`updated_by`),
  CONSTRAINT `FK7nip23l6tnhx842rhsiilo0vw` FOREIGN KEY (`updated_by`) REFERENCES `staff` (`id`),
  CONSTRAINT `FK_STAFF_SPONSORS` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsors` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STAFF_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STAFF_USERS_CREATD_BY` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_STAFF_USERS_UPDATED_BY` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FKqwnxpfxc4w6kn9543s1behhwj` FOREIGN KEY (`created_by`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,4,1,NULL,NULL,'2018-10-08 19:05:03','2018-10-08 19:07:36'),(2,6,1,1,NULL,'2018-10-08 19:07:17','2018-10-08 19:07:58'),(3,8,1,1,NULL,'2018-10-08 19:07:17','2018-10-08 19:07:58'),(4,5,2,NULL,NULL,'2018-10-08 19:07:17','2018-10-08 19:07:36'),(5,7,2,4,NULL,'2018-10-08 19:07:17','2018-10-08 19:07:58'),(6,9,2,4,NULL,'2018-10-08 19:07:17','2018-10-08 19:07:58');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_USER_ROLES_USER_ID_USERS_idx` (`user_id`),
  KEY `FK_USER_ROLES_ROLE_ID_ROLES_idx` (`role_id`),
  CONSTRAINT `FK_USER_ROLES_ROLE_ID_ROLES` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_ROLES_USER_ID_USERS` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (12,4,1),(13,4,2),(14,4,3),(15,1,4),(16,1,5),(17,2,6),(18,2,7),(19,3,8),(20,3,9);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERS_EMAIL` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'candidate1',1452369870,'candidate1@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy'),(2,'candidate2',1452369770,'candidate2@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy'),(3,'candidate3',1452369771,'candidate3@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy'),(4,'manager1',1452369772,'manager1@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy'),(5,'manager2',1452369773,'manager2@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy'),(6,'officer1',1452369774,'officer1@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy'),(7,'officer2',1452369775,'officer2@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy'),(8,'recepient1',1452369776,'recepient1@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy'),(9,'recepient2',1452369777,'recepient2@gmail.com','$2a$10$PhABXUwm07VtnhCdGxZZ0uA2kWuUs/ZK3GwiAumR9JZGcd2w.4Pwy');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-09  2:41:42
