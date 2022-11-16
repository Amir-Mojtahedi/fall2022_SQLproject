CREATE TRIGGER after_dawson_courses_delete
AFTER DELETE
ON dawson_courses
BEGIN
    INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has updated row(s) in dawson_courses table');
END;
/
CREATE TRIGGER after_dawson_courses_insert
AFTER INSERT
ON dawson_courses
BEGIN
   INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has updated row(s) in dawson_courses table');
END;
/
CREATE TRIGGER after_dawson_courses_update
AFTER UPDATE
ON dawson_courses
BEGIN
    INSERT INTO user_logs VALUES(user,CURRENT_TIMESTAMP,user||' has updated row(s) in dawson_courses table');
END;
/