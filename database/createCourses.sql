@remove.sql
--CREATING TABLES

CREATE TABLE seasons(
    season_id Char(1) PRIMARY KEY,
    season_name Varchar2(20) NOT NULL
);
CREATE TABLE term_seasons(
    term_id number(2,0) PRIMARY KEY,
    season_id Char(1) REFERENCES seasons(season_id) 
);
CREATE TABLE educations(
    education_type_id Char(1) PRIMARY KEY,
    education_type varchar2(30) NOT NULL
);
CREATE TABLE dawson_courses(
    course_number varchar2(20) PRIMARY KEY, 
    course_name varchar2(50) NOT NULL,
    course_description varchar2(1000) NOT NULL,
    class_hours number(2,0),
    lab_hours number(2,0),
    homework_hours number(2,0),
    education_type_id Char(1) REFERENCES educations(education_type_id),
    term_id number REFERENCES term_seasons(term_id)
);

CREATE TABLE competencies(
    comp_id char(4) PRIMARY KEY,
    comp_name varchar2(60) NOT NULL,
    specification char(1)NOT NULL,
    comp_description varchar2(1000) NOT NULL
);

CREATE TABLE elements_of_competency(
    element_id varchar2(3) PRIMARY KEY,
    element_name varchar2(100) NOT NULL,
    element_description varchar2(1000) NOT NULL,
    comp_id char(4) REFERENCES competencies(comp_id)
);

CREATE TABLE element_course(
    element_id varchar2(3) REFERENCES elements_of_competency(element_id),
    course_number varchar2(20) REFERENCES dawson_courses(course_number),
    associated_time number(4,2) NOT NULL
)


INSERT INTO seasons VALUES('1','Fall');
INSERT INTO seasons VALUES('2','Winter');


INSERT INTO educations VALUES('1','General');
INSERT INTO educations VALUES('2','Concentration');

INSERT INTO term_seasons VALUES(1,'1');
INSERT INTO term_seasons VALUES(2,'2');
INSERT INTO term_seasons VALUES(3,'1');
INSERT INTO term_seasons VALUES(4,'2');
INSERT INTO term_seasons VALUES(5,'1');
INSERT INTO term_seasons VALUES(6,'2');


INSERT INTO dawson_courses VALUES('201-NYC-05','Linear Algebra','Systems of linear equations and elementary operations, matrices and
determinants, vectors, lines, planes and vector spaces are studied in this course.',3,2,3,'2',3);

INSERT INTO dawson_courses VALUES('420-310-DW','Programming III','The course will enhance the students’ knowledge of object-oriented
programming and Java to produce stand-alone applications employing reusable
objects, data structures and the Java collections framework. The concepts of
inheritance, polymorphism, data abstraction and programming to interfaces are
used to design software. Students are introduced to software version control
and e?ective team collaboration.',3,3,3,'2',3);

INSERT INTO dawson_courses VALUES('420-510-DW','Programming V','The course will focus on the use of algorithms and data structures to simulate
real-life phenomena using an appropriate gaming framework. Projects are
implemented using an object-oriented language.',3,3,3,'2',5);

INSERT INTO dawson_courses VALUES('420-331-DW','Database II','This course will introduce the student to the Oracle environment for building,
deploying, hosting, and managing enterprise-class applications. The student will
use SQL and PL/SQL to build and manage a database. In addition, the student
will complete a case study which involves requirements analysis and data
modeling, implemented using PL/SQL and Java.',3,3,3,'2',3);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO elements_of_competency VALUES('1','Process numbers as they are represented in the computer memory.','Accurate representation of numbers in different base systems
Accurate conversion of numbers from one base to another
Accurate interpretation of the ranges of numeric types
Accurate interpretation of the precision of numeric types
Appropriate choice of the numeric type','00Q3');

INSERT INTO elements_of_competency VALUES('2','Represent two-dimensional geometric figures in the form of digital images.','Correct identification of the size, dimensions and resolution of the image
Accurate representation of points and lines
Correct application of translation equations, rotation equations and homothetic equations
Consistency of geometric figures with their graphic representation','00Q3');

INSERT INTO elements_of_competency VALUES('3','Model multi-variable logical reasoning.','Correct formulation of logical functions
Efficient simplification of logical functions
Appropriate use of Boolean algebra
Accurate production of truth tables
Appropriate verification of logical functions','00Q3');

INSERT INTO elements_of_competency VALUES('4','Process quantitative data using descriptive statistics.','Precise calculation of the average, median, variance and standard deviation
Clarity and accuracy of the graphic representation of data
Accurate analysis of results','00Q3');

INSERT INTO elements_of_competency VALUES('5','Analyze the problem.','Breakdown of the problem based on the requirements of an object-oriented approach
Proper identification of input and output data and the nature of the processes
Accurate identification of the classes to be modelled
Proper identification of the algorithms to be created','00Q6');

INSERT INTO elements_of_competency VALUES('6','Model the classes.','Proper identification of class attributes and methods
Proper application of encapsulation and inheritance principles
Proper graphic representation of the classes and their relationships
Compliance with nomenclature rules','00Q6');

INSERT INTO elements_of_competency VALUES('7','Produce the algorithms for the methods.','Appropriate identification of the operations necessary for each method
Proper identification of a logical sequence of operations
Appropriate verification of algorithm correctness
Accurate representation of algorithms','00Q6');

INSERT INTO elements_of_competency VALUES('8','Create the graphic interface.','Precise calculation of the average, median, variance and standard deviation
Clarity and accuracy of the graphic representation of data
Accurate analysis of results','00Q6');

INSERT INTO elements_of_competency VALUES('9','Program the classes.','Precise calculation of the average, median, variance and standard deviation
Clarity and accuracy of the graphic representation of data
Accurate analysis of results','00Q6');

INSERT INTO elements_of_competency VALUES('10','Document the code.','Precise calculation of the average, median, variance and standard deviation
Clarity and accuracy of the graphic representation of data
Accurate analysis of results','00Q6');


INSERT INTO competencies VALUES();


INSERT INTO element_course VALUES();


COMMIT;


