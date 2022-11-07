DROP TABLE dawson_courses;
DROP TABLE term_seasons;
DROP TABLE seasons;
DROP TABLE educations;
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
CREATE TABLE elements_of_competency(
    element_id varchar2(2) PRIMARY KEY,
    element_name varchar2(100) NOT NULL,
    element_description varchar2(1000),
    comp_id char(4) REFERENCES competencies(comp_id)
);

INSERT INTO seasons VALUES('1','Fall');
INSERT INTO seasons VALUES('2','Winter');


INSERT INTO educations VALUES('1','General');
INSERT INTO educations VALUES('2','Concentration');

INSERT INTO term_seasons VALUES(1,'1');
INSERT INTO term_seasons VALUES(2,'2');


INSERT INTO dawson_courses VALUES('420-110-DW','Programming I','The course will introduce the student to the basic building blocks (sequential,
selection and repetitive control structures) and modules (methods and classes)
used to write a program. The student will use the Java programming language to
implement the algorithms studied. The array data structure is introduced, and
student will learn how to program with objects.',3,3,3,'2',1);

INSERT INTO dawson_courses VALUES('420-210-DW','Programming II','The course will introduce the student to basic object-oriented methodology in
order to design, implement, use and modify classes, to write programs in the
Java language that perform interactive processing, array and string processing,
and data validation. Object-oriented features such as encapsulation and
inheritance will be explored.',3,3,3,'2',2);

COMMIT;


