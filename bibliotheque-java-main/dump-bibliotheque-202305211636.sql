-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bibliotheque
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abonne`
--

DROP TABLE IF EXISTS `abonne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abonne` (
  `id_abonne` int NOT NULL AUTO_INCREMENT,
  `adresse` varchar(110) NOT NULL,
  `code_postal` int NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `tel` varchar(10) DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `nb_infractions` int NOT NULL DEFAULT '0',
  `id_utilisateur` int NOT NULL,
  PRIMARY KEY (`id_abonne`),
  UNIQUE KEY `id_utilisateur` (`id_utilisateur`),
  UNIQUE KEY `email` (`email`),
  CONSTRAINT `abonne_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonne`
--

LOCK TABLES `abonne` WRITE;
/*!40000 ALTER TABLE `abonne` DISABLE KEYS */;
INSERT INTO `abonne` VALUES (1,'123 Rue de la Liberté',12345,'Dupont','Jean','1234567890','jean.dupont@example.com',2,1),(2,'789 Boulevard de la Mer',54321,'Leclerc','Pierre',NULL,'pierre.leclerc@example.com',1,3),(4,'456 Avenue des Fleurs',98765,'Martin','Sophie','2147483646','sophie.martin@example.com',0,2);
/*!40000 ALTER TABLE `abonne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auteur` (
  `id_auteur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  PRIMARY KEY (`id_auteur`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auteur`
--

LOCK TABLES `auteur` WRITE;
/*!40000 ALTER TABLE `auteur` DISABLE KEYS */;
INSERT INTO `auteur` VALUES (1,'J. K. Rowling'),(2,'Michael Crichton'),(3,' Albert Uderzo'),(4,'René Goscinny'),(5,'Virtor Hugo'),(6,'Stephen King'),(7,'Albert Camus'),(15,'Jules Verne');
/*!40000 ALTER TABLE `auteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avoir_auteur`
--

DROP TABLE IF EXISTS `avoir_auteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avoir_auteur` (
  `id_ouvrage` int NOT NULL,
  `id_auteur` int NOT NULL,
  PRIMARY KEY (`id_ouvrage`,`id_auteur`),
  KEY `id_auteur` (`id_auteur`),
  CONSTRAINT `avoir_auteur_ibfk_1` FOREIGN KEY (`id_ouvrage`) REFERENCES `ouvrage` (`id_ouvrage`),
  CONSTRAINT `avoir_auteur_ibfk_2` FOREIGN KEY (`id_auteur`) REFERENCES `auteur` (`id_auteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avoir_auteur`
--

LOCK TABLES `avoir_auteur` WRITE;
/*!40000 ALTER TABLE `avoir_auteur` DISABLE KEYS */;
INSERT INTO `avoir_auteur` VALUES (44,1),(37,2),(39,2),(40,2),(43,2),(44,2),(7,3),(37,3),(39,3),(40,3),(43,3),(7,4),(37,4),(39,4),(40,4),(43,4),(46,4),(42,5),(45,5),(5,6),(42,7);
/*!40000 ALTER TABLE `avoir_auteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exemplaire`
--

DROP TABLE IF EXISTS `exemplaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exemplaire` (
  `id_exemplaire` int NOT NULL AUTO_INCREMENT,
  `disponibilitee` tinyint(1) NOT NULL,
  `id_ouvrage` int NOT NULL,
  PRIMARY KEY (`id_exemplaire`),
  KEY `id_ouvrage` (`id_ouvrage`),
  CONSTRAINT `exemplaire_ibfk_1` FOREIGN KEY (`id_ouvrage`) REFERENCES `ouvrage` (`id_ouvrage`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exemplaire`
--

LOCK TABLES `exemplaire` WRITE;
/*!40000 ALTER TABLE `exemplaire` DISABLE KEYS */;
INSERT INTO `exemplaire` VALUES (1,0,5),(2,0,7),(3,1,6),(4,1,5),(5,0,7),(6,1,6),(7,1,5),(8,1,37),(9,1,7),(10,1,7),(13,1,5),(17,1,40),(18,1,40),(19,1,40),(21,1,40),(22,1,5),(23,1,40);
/*!40000 ALTER TABLE `exemplaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ouvrage`
--

DROP TABLE IF EXISTS `ouvrage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ouvrage` (
  `id_ouvrage` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) NOT NULL,
  `duree_autorisation_max` int NOT NULL,
  `nb_exemplaires` int NOT NULL,
  `id_type_ouvrage` int NOT NULL,
  PRIMARY KEY (`id_ouvrage`),
  KEY `id_type_ouvrage` (`id_type_ouvrage`),
  CONSTRAINT `ouvrage_ibfk_1` FOREIGN KEY (`id_type_ouvrage`) REFERENCES `type_ouvrage` (`id_type_ouvrage`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ouvrage`
--

LOCK TABLES `ouvrage` WRITE;
/*!40000 ALTER TABLE `ouvrage` DISABLE KEYS */;
INSERT INTO `ouvrage` VALUES (5,'Harry Potter',3,6,2),(6,'Le Seigneur des Anneaux',6,3,2),(7,'Astérix et Obélix',9,7,3),(8,'Le Monde',10,7,1),(37,'Asterix 2023',10,1,3),(39,'Asterix 5',10,0,3),(40,'Asterix 6',10,5,3),(42,'Lion King',10,0,2),(43,'Asterix 43',10,0,3),(44,'Lion Princess',3,0,3),(45,'La tunisie',9,0,9),(46,'Pardon my french',10,0,14);
/*!40000 ALTER TABLE `ouvrage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserver`
--

DROP TABLE IF EXISTS `reserver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserver` (
  `id_abonne` int NOT NULL AUTO_INCREMENT,
  `id_exemplaire` int NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_abonne`,`id_exemplaire`),
  KEY `id_exemplaire` (`id_exemplaire`),
  CONSTRAINT `reserver_ibfk_1` FOREIGN KEY (`id_abonne`) REFERENCES `abonne` (`id_abonne`),
  CONSTRAINT `reserver_ibfk_2` FOREIGN KEY (`id_exemplaire`) REFERENCES `exemplaire` (`id_exemplaire`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserver`
--

LOCK TABLES `reserver` WRITE;
/*!40000 ALTER TABLE `reserver` DISABLE KEYS */;
INSERT INTO `reserver` VALUES (2,2,'2023-07-20','2023-07-25'),(2,5,'2023-08-05','2023-08-10');
/*!40000 ALTER TABLE `reserver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_ouvrage`
--

DROP TABLE IF EXISTS `type_ouvrage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_ouvrage` (
  `id_type_ouvrage` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_type_ouvrage`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_ouvrage`
--

LOCK TABLES `type_ouvrage` WRITE;
/*!40000 ALTER TABLE `type_ouvrage` DISABLE KEYS */;
INSERT INTO `type_ouvrage` VALUES (3,'bd'),(14,'femme'),(15,'homme'),(9,'livres poche'),(1,'magazine'),(16,'musique'),(2,'roman'),(5,'santé'),(7,'sports');
/*!40000 ALTER TABLE `type_ouvrage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `id_utilisateur` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(50) NOT NULL,
  `mot_passe` varchar(50) NOT NULL,
  `bibliothecaire` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `pseudo` (`pseudo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'john_doe','password123',1),(2,'jane_smith','securepass',0),(3,'admin','admin123',1),(4,'ps','mp',1);
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bibliotheque'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-21 16:36:25
