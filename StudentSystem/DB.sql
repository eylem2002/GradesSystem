DROP table STUDENTS;
DROP table MARKS;
CREATE TABLE STUDENTS(
    id INT PRIMARY KEY AUTO_INCREMENT,
    password VARCHAR(120) NOT NULL,
    name VARCHAR(120) NOT NULL
);

CREATE TABLE MARKS(
    id INT PRIMARY KEY AUTO_INCREMENT,
    std_id INT NOT NULL,
    english  Double,
    arabic Double,
    spanish Double
);

INSERT INTO STUDENTS(id, password, name)
VALUES (1, "123al", "Alaa"),
       (2, "123na", "Nada"),
       (3, "123ma", "Malak");

INSERT INTO MARKS(id, std_id, english, arabic, spanish)
VALUES (1, 1, 93, 100, 99),
       (2, 2, 64, 86, 76),
       (3, 3, 87, 85, 93);