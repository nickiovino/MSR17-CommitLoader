/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `data_commits` */

CREATE TABLE `data_commits` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `repo` varchar(200) NOT NULL,
  `sha` varchar(40) NOT NULL,
  `message` text,
  `author` int(10) unsigned DEFAULT NULL,
  `author_date` datetime DEFAULT NULL,
  `committer` int(10) unsigned DEFAULT NULL,
  `commit_date` datetime DEFAULT NULL,
  `verified` tinyint(1) DEFAULT NULL,
  `verified_reason` varchar(50) DEFAULT NULL,
  `added` mediumint(8) unsigned DEFAULT '0',
  `removed` mediumint(8) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_data_commits__author` (`author`),
  KEY `FK_data_commits__committer` (`committer`),
  CONSTRAINT `FK_data_commits__author` FOREIGN KEY (`author`) REFERENCES `data_users` (`id`),
  CONSTRAINT `FK_data_commits__committer` FOREIGN KEY (`committer`) REFERENCES `data_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10462 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `data_json` */

CREATE TABLE `data_json` (
  `id` bigint(20) unsigned NOT NULL,
  `data` text,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_data_json__id` FOREIGN KEY (`id`) REFERENCES `data_commits` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `data_users` */

CREATE TABLE `data_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gh_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=382 DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
