@types.sql
--I removed procedures which were adding an education an a season to the databse. I don't think they are useful. Alos there is no update proceure for the mentioned tables. Later in the program, if they are needed somehow, I should add them.
--update data-----------------
CREATE OR REPLACE PACKAGE COURSES_PACKAGE AS
    PROCEDURE delete_course(vcourse IN course_typ);
    PROCEDURE add_course (
        vcourse IN course_type
    );
    FUNCTION calculate_total_hours(vcourse IN course_typ) RETURN number;
    
    PROCEDURE update_course(vcourse IN course_type) ;
    
END COURSES_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY COURSES_PACKAGE AS
-- Commented for now. After the procedure is created, I will uncomment.
--    PROCEDURE delete_course(dawson_course_number IN dawson_courses.course_number%TYPE)
--    AS
--    BEGIN
--        --I should call the delete procedure in the bridging package  [package Name].remove_course(course_id)
--        DELETE FROM dawson_courses WHERE dawson_course_number=course_number;
--    END;


    PROCEDURE update_course(vcourse IN course_typ)
    AS
    BEGIN
        UPDATE dawson_courses SET 
            course_name=vcourse.course_name,
            course_description=vcourse.course_description,
            class_hours=vcourse.class_hours,
            lab_hours=vcourse.lab_hours,
            homework_hours=vcourse.homework_hours,
            education_type_id=vcourse.veducation.education_type_id,
            term_id=vcourse.vterm.term_id
            WHERE course_number=vcourse.course_number;
    END;
   
 
    PROCEDURE add_course (
        vcourse IN course_typ
    ) AS
    BEGIN
        INSERT INTO dawson_courses VALUES (
            vcourse.course_number,
            vcourse.course_name,
            vcourse.course_description,
            vcourse.class_hours,
            vcourse.lab_hours,
            vcourse.homework_hours,
            vcourse.veducation.education_type_id,
            vcourse.vterm.term_id
        );
    EXCEPTION
        WHEN dup_val_on_index THEN
            update_course(vcourse);
    END;
    
    FUNCTION calculate_total_hours(vcourse IN course_typ)
    RETURN number
    AS
    BEGIN
        RETURN (vcourse.class_hours+vcourse.lab_hours)*15;
    END;
    
END COURSES_PACKAGE;
/
--add row------------------
CREATE OR REPLACE PACKAGE COMPETENCIES_PACKAGE AS
END COMPETENCIES_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY COMPETENCIES_PACKAGE AS
END COMPETENCIES_PACKAGE;
/
--delete row package---------
CREATE OR REPLACE PACKAGE CC_BRIDGE_PACKAGE AS
END CC_BRIDGE_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY CC_BRIDGE_PACKAGE AS
END CC_BRIDGE_PACKAGE;
/