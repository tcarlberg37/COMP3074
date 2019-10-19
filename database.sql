DROP DATABASE IF EXISTS COMP3095;

CREATE DATABASE IF NOT EXISTS COMP3095;
USE COMP3095;
CREATE USER 'admin'@'localhost' identified by 'admin'; 
grant all privileges on COMP3095.* to 'admin'@'localhost'; 
	
CREATE TABLE USERS 
( 
	id int AUTO_INCREMENT PRIMARY KEY, 
	firstname varchar(255),
	lastname varchar(255),
	email varchar(255),
	address varchar(255),
	role varchar(20),
	created timestamp default current_timestamp,
	password varchar(20)	
);

INSERT INTO USERS (firstname, lastname, email, address, role, password) VALUES
(NULL, NULL, 'admin@isp.net', NULL, 'admin', 'P@ssword1');
