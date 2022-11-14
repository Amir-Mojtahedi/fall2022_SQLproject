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
);
@inserts.sql





