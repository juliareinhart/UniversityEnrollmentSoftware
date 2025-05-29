-- Select database
USE universityenrollmentsoftware;

-- 1. Department Table
CREATE TABLE department (
    deptid INT AUTO_INCREMENT PRIMARY KEY,
    deptname VARCHAR(100)
);

-- 2. Professor Table
CREATE TABLE professor (
    profid INT AUTO_INCREMENT PRIMARY KEY,
    professorname VARCHAR(100),
    deptid INT,
    FOREIGN KEY (deptid) REFERENCES department(deptid)
);

-- 3. Course Table
CREATE TABLE course (
    courseid INT AUTO_INCREMENT PRIMARY KEY,
    coursetitle VARCHAR(100),
    coursenumber INT,
    deptid INT,
    profid INT,
    FOREIGN KEY (profid) REFERENCES professor(profid),
    FOREIGN KEY (deptid) REFERENCES department(deptid)
);

-- 4. Student Table
CREATE TABLE student (
    studentid INT AUTO_INCREMENT PRIMARY KEY,
    studentname VARCHAR(100),
    address VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(100),
    zip VARCHAR(100)
);

-- 5. Enrollment Table
CREATE TABLE enrollment (
    enrollmentid INT AUTO_INCREMENT PRIMARY KEY,
    studentid INT,
    courseid INT,
    year int,
    semester VARCHAR(50),
    grade VARCHAR(10),
    UNIQUE (studentid, courseid, year, semester),
    FOREIGN KEY (studentid) REFERENCES student(studentid),
    FOREIGN KEY (courseid) REFERENCES course(courseid)
);

-- 5. Stored Procedure
DELIMITER //

CREATE PROCEDURE add_department (
    IN departmentname VARCHAR(100)
)
BEGIN
    INSERT INTO department(deptname)
    VALUES (departmentname);
END //

DELIMITER ;

-- Renaming studentname to firstname and lastname to match the JavaFX data, and for sorting 

ALTER TABLE student
DROP COLUMN studentname,
ADD COLUMN firstname VARCHAR(100),
ADD COLUMN lastname VARCHAR(100);

DELIMITER //

CREATE PROCEDURE add_department(IN departmentName VARCHAR(100))
BEGIN
    INSERT INTO department (deptname) VALUES (departmentName);
END //

DELIMITER ;

-- To delete ALL data but preserve schemas in the table and database :D

SET FOREIGN_KEY_CHECKS = 0;  -- 1) Disable foreign key checks to avoid constraint violations

TRUNCATE TABLE enrollment;   -- 2) Truncate child tables first
TRUNCATE TABLE student;
TRUNCATE TABLE course;
TRUNCATE TABLE professor;
TRUNCATE TABLE department;

SET FOREIGN_KEY_CHECKS = 1;  -- 3) Re-enable foreign key checks

-- Note: TRUNCATE is faster than DELETE — it's like deleting a file rather than erasing line by line