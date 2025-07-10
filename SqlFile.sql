CREATE DATABASE operator_db;

USE operator_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);
CREATE TABLE images (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    image_path VARCHAR(500)
);

-- Sample user
INSERT INTO users (username, password) VALUES ('Likith', '1234');
INSERT INTO images (name, image_path)
VALUES ('Suspect1', 'C:/Users/Likith/Desktop/criminals/pic1.jpg'),
       ('Suspect2', 'C:/Users/Likith/Desktop/criminals/pic2.jpg'),
       ('Suspect2', 'C:/Users/Likith/Desktop/criminals/pic3.jpg');
       
       CREATE TABLE images_blob (
		id INT AUTO_INCREMENT PRIMARY KEY,
		name VARCHAR(255),
		photo LONGBLOB
);
CREATE TABLE witness_descriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT
);
DROP TABLE IF EXISTS witness_descriptions;
CREATE TABLE witness_descriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT
);

INSERT INTO witness_descriptions (name, description)
VALUES
('Ravi', 'Tall, wearing blue jacket, last seen near park'),
('Sneha', 'Medium height, curly hair, suspicious behavior');


