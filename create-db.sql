DROP SCHEMA IF EXISTS `hospital_db`;

CREATE SCHEMA `hospital_db`;

use `hospital_db`;

SET FOREIGN_KEY_CHECKS = 0;





DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor`(
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_firstname` varchar(45) NOT NULL,
  `doc_lastname` varchar(45) NOT NULL,
  `doc_phoneno` varchar(10) NOT NULL,
 
  PRIMARY KEY (`doc_id`)
 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `specialization`;

CREATE TABLE `specialization` (
  `spec_id` int(11) NOT NULL AUTO_INCREMENT,
  `spec_name` varchar(128) DEFAULT NULL,
  
  PRIMARY KEY (`spec_id`),
  
  UNIQUE KEY `TITLE_UNIQUE` (`spec_name`)
  
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `doctor_specialization`;

CREATE TABLE `doctor_specialization` (
  `doc_id` int(11) NOT NULL,
  `spec_id` int(11) NOT NULL,
  
  PRIMARY KEY (`doc_id`,`spec_id`),
  
  KEY `FK_DOC_idx` (`doc_id`),
  
  CONSTRAINT `FK_SPEC` FOREIGN KEY (`spec_id`) 
  REFERENCES `specialization` (`spec_id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_DOC` FOREIGN KEY (`doc_id`) 
  REFERENCES `doctor` (`doc_id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient`(
  `pat_id` int(11) NOT NULL AUTO_INCREMENT,
  `pat_firstname` varchar(45) NOT NULL,
  `pat_lastname` varchar(45) NOT NULL,
  `pat_phoneno` varchar(20) NOT NULL,
  PRIMARY KEY (`pat_id`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
  `app_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_date` varchar(20) NOT NULL,
  `app_time` varchar(128) NOT NULL,
  `app_reason` varchar(128) NOT NULL,
  `doc_id` int(11) NOT NULL,
  `pat_id` int(11) NOT NULL,
  PRIMARY KEY (`app_id`),
  
  KEY `FK_DETAIL_doc_idx` (`doc_id`),
  CONSTRAINT `FK_DOC_DETAIL` FOREIGN KEY (`doc_id`) REFERENCES `doctor` (`doc_id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  KEY `FK_DETAIL_pat_idx` (`pat_id`),
  CONSTRAINT `FK_PAT_DETAIL` FOREIGN KEY (`pat_id`) REFERENCES `patient` (`pat_id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;



INSERT INTO `doctor` VALUES 
	(1,'Joey','Andrews',9908338102),
	(2,'Ross','Baumgarten',9948750016),
	(3,'Monica','Gupta',9676756588),
	(4,'Pheebs','Petrov',8686477119),
	(5,'Chandler','Vega',8074901014);


INSERT INTO `patient` VALUES 
	(1,'Krishna','Kumar',9908338102),
	(2,'Sanjay','Reddy',9948750016),
	(3,'Ajay','Kumar',9676756588),
	(4,'Sowmya','Vemula',8686477119),
	(5,'Vishal','Vega',8074901014);

INSERT INTO `appointment` VALUES 
	(1,'17-02-2020','12:30 PM','FEVER',2,1),
	(2,'22-01-2020','11;00 AM','COUGH',4,2),
	(3,'18-03-2020','11:15 AM','COLD',1,4),
	(4,'23-04-2020','11:15 AM','FEVER',5,3),
	(5,'07-03-2020','11;00 AM','FEVER',4,5);
    
INSERT INTO `specialization` VALUES 
	(1,'MBBS'),
	(2,'MS'),
	(3,'FRCS'),
	(4,'MRCS');

INSERT INTO `doctor_specialization` VALUES 
	(1,1),
	(1,2),
	(2,1),
	(2,3),
	(3,1),
    (3,4),
	(4,1),
    (4,2),
    (5,1),
    (5,3);

    
    
--
-- Tables for Authentication
--


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `role_id` int NOT NULL,
  `role_name` varchar(50) NOT NULL,
   primary key(role_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `role_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  
  PRIMARY KEY (`role_id`,`username`),
  

  
  CONSTRAINT `FK_ROLES` FOREIGN KEY (`role_id`) 
  REFERENCES `roles` (`role_id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_USERS` FOREIGN KEY (`username`) 
  REFERENCES `users` (`username`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('shravan','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
('ajay','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
('vijay','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);


--
-- Dumping data for table `roles`
--

INSERT INTO `roles` 
VALUES 
(1,'ADMIN'),
(2,'PATIENT'),
(3,'DOCTOR');

INSERT INTO `user_roles`
VALUES
(2,'shravan'),
(3,'ajay'),
(1,'vijay');
