
CREATE OR REPLACE TYPE season_typ AS OBJECT (
    season_id   CHAR(1),
    season_name VARCHAR2(20)
);
/

CREATE OR REPLACE TYPE TERM_TYP AS OBJECT (
    term_id NUMBER(2, 0),
    season season_typ
);
/

CREATE OR REPLACE TYPE education_typ AS OBJECT (
    education_type_id CHAR(1),
    education_type    VARCHAR2(30)
);
/

CREATE OR REPLACE TYPE course_typ AS OBJECT (
    course_number      VARCHAR2(20),
    course_name        VARCHAR2(50),
    course_description VARCHAR2(1000),
    class_hours        NUMBER(2, 0),
    lab_hours          NUMBER(2, 0),
    homework_hours     NUMBER(2, 0),
<<<<<<< HEAD
    OEducation         education_type,
    OTerm              term_type
);
/
CREATE OR REPLACE TYPE ELEMENT_TYP AS OBJECT (
    element_id VARCHAR2(3),
    element_name VARCHAR2(100),
    element_description VARCHAR2(1000),
    comp COMP_TYP
);
/
CREATE OR REPLACE TYPE EDUCATION_TYP AS OBJECT (
    education_type_id char(1),
    education_type varchar2(30)
);
/
=======
    education         education_typ,
    term              term_typ
);
/
>>>>>>> a35b08dbbd490f6e9c3aae07ad318a4e50fedd44
CREATE OR REPLACE TYPE COMP_TYP AS OBJECT (
    comp_id CHAR(4),
    comp_name VARCHAR2(60),
    specification CHAR(1),
    comp_description VARCHAR2(1000)
);
/
CREATE OR REPLACE TYPE ELEMENT_TYP AS OBJECT (
    element_id VARCHAR2(3),
    element_name VARCHAR2(100),
    element_description VARCHAR2(1000),
    comp COMP_TYP
);
/
