-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.32 - MySQL Community Server (GPL)
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- sample 의 데이터베이스 구조 덤핑
CREATE DATABASE IF NOT EXISTS `sample` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sample`;


-- 테이블 sample의 구조를 덤프합니다. board_article
CREATE TABLE IF NOT EXISTS `board_article` (
  `board_article_no` int(10) NOT NULL AUTO_INCREMENT,
  `board_article_title` varchar(50) NOT NULL,
  `board_article_content` varchar(500) NOT NULL,
  PRIMARY KEY (`board_article_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 sample의 구조를 덤프합니다. board_file
CREATE TABLE IF NOT EXISTS `board_file` (
  `board_file_no` int(10) NOT NULL AUTO_INCREMENT,
  `board_article_no` int(10) NOT NULL,
  `board_file_name` varchar(500) NOT NULL,
  `board_file_ext` varchar(10) NOT NULL,
  `board_file_size` bigint(20) NOT NULL,
  `board_file_type` varchar(50) NOT NULL,
  PRIMARY KEY (`board_file_no`),
  KEY `FK_board_file_board_article` (`board_article_no`),
  CONSTRAINT `FK_board_file_board_article` FOREIGN KEY (`board_article_no`) REFERENCES `board_article` (`board_article_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
