CREATE TRIGGER after_dawson_courses_delete
AFTER DELETE
ON dawson_courses
BEGIN
    INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has deleted row(s) from dawson_courses table');
END;
/
CREATE TRIGGER after_dawson_courses_insert
AFTER INSERT
ON dawson_courses
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has inserted row(s) into dawson_courses table');
END;
/
CREATE TRIGGER after_dawson_courses_update
AFTER UPDATE
ON dawson_courses
BEGIN
    INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has updated row(s) in dawson_courses table');
END;
/
----------------------------------------------------------------------------------------------------------------
CREATE TRIGGER after_element_course_insert
AFTER INSERT
ON element_course
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has inserted row(s) into element_course table');
END;
/
CREATE TRIGGER after_element_course_delete
AFTER DELETE
ON element_course
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has deleted row(s) from element_course table');
END;
/
CREATE TRIGGER after_element_course_update
AFTER UPDATE
ON element_course
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has updated row(s) in element_course table');
END;
/
----------------------------------------------------------------------------------------------------------------

CREATE TRIGGER after_competencies_insert
AFTER INSERT
ON competencies
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has inserted row(s) into competencies table');
END;
/
CREATE TRIGGER after_competencies_delete
AFTER DELETE
ON competencies
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has deleted row(s) from competencies table');
END;
/
CREATE TRIGGER after_competencies_update
AFTER UPDATE
ON competencies
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has updated row(s) in competencies table');
END;
/
----------------------------------------------------------------------------------------------------------------

CREATE TRIGGER after_elements_of_competency_insert
AFTER INSERT
ON elements_of_competency
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has inserted row(s) into elements_of_competency table');
END;
/
CREATE TRIGGER after_elements_of_competency_delete
AFTER DELETE
ON elements_of_competency
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has deleted row(s) from elements_of_competency table');
END;
/
CREATE TRIGGER after_elements_of_competency_update
AFTER UPDATE
ON elements_of_competency
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has updated row(s) in elements_of_competency table');
END;
/