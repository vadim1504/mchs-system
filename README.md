mchs-system

searching for coordinates by call

module:  
   calculation-coordinate:  reads data from db.call and calculates coordinates and writes to bd.user  
   jms: accepts jms messages  
   rest-service: accepts message and implements the rest service user and call  
   search-form: implements the from of users search  
   service: The data model and the access services to the database  

data base script:

create database mchs;  
CREATE TABLE users (  
  id INT NOT NULL PRIMARY KEY,  
  first_name VARCHAR (35) NOT NULL,  
  last_name VARCHAR(35) NOT NULL,  
  number   VARCHAR(35) NOT NULL,  
  coordinate_x DOUBLE DEFAULT 0,  
  coordinate_y DOUBLE DEFAULT 0  
);  

CREATE TABLE calls (  
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  
  id_tower INT NOT NULL,  
  id_user INT NOT NULL,  
  time_call TIME NOT NULL,  
  distance DOUBLE NOT NULL,  
  id_call INT NOT NULL  
);  

example json request message:
{
        "id":1,
        "idTower": 1,
        "idSub": 1,
        "firstName": "first",
        "lastName": "last",
        "number": "1234567",
        "idCall": 1,
        "timeCall": "00:10:00",
        "distance": 1000
}
