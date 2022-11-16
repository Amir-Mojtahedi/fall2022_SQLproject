--Drop package--------------
DROP PACKAGE COURSES_PACKAGE;
DROP PACKAGE COMPETENCIES_PACKAGE;
DROP PACKAGE CC_BRIDGE_PACKAGE;

--DROP TYPE----------------
DROP TYPE COURSE_TYP;
DROP TYPE TERM_TYP;
DROP TYPE SEASON_TYP;
DROP TYPE ELEMENT_TYP;
DROP TYPE EDUCATION_TYP;
DROP TYPE COMP_TYP;

--DROP TRIGGER----------------
DROP TRIGGER after_dawson_courses_delete;
DROP TRIGGER after_dawson_courses_update;
DROP TRIGGER after_dawson_courses_insert;
--DROP TABLE------------------
DROP TABLE user_logs;
DROP TABLE element_course;
DROP TABLE elements_of_competency;
DROP TABLE dawson_courses;
DROP TABLE term_seasons;
DROP TABLE seasons;
DROP TABLE educations;
DROP TABLE COMPETENCIES;



