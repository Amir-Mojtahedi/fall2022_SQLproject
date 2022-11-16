
--I removed procedures which were adding an education an a season to the databse. I don't think they are useful. Alos there is no update proceure for the mentioned tables. Later in the program, if they are needed somehow, I should add them.
--update data
CREATE PACKAGE COURSES_PACKAGE AS
    --PROCEDURE delete_course(vcourse IN course_typ);
    PROCEDURE add_course (
        vcourse IN course_typ
    );
    PROCEDURE update_course(vcourse IN course_typ) ;
    FUNCTION calculate_total_hours(vcourse IN course_typ) RETURN number; 
END COURSES_PACKAGE;
/
CREATE PACKAGE BODY COURSES_PACKAGE AS
-- Commented for now. After the procedure is created, I will uncomment.
--    PROCEDURE delete_course(dawson_course_number IN dawson_courses.course_number%TYPE)
--    AS
--    BEGIN
--        CC_BRIDGE_PACKAGE.remove_course(course_number);
--        DELETE FROM dawson_courses WHERE dawson_course_number=course_number;
--    END;

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
            vcourse.education.education_type_id,
            vcourse.term.term_id
        );
    EXCEPTION
        WHEN dup_val_on_index THEN
            update_course(vcourse);
    END;
    
    PROCEDURE update_course(vcourse IN course_typ)
    AS
    BEGIN
        UPDATE dawson_courses SET 
            course_name=vcourse.course_name,
            course_description=vcourse.course_description,
            class_hours=vcourse.class_hours,
            lab_hours=vcourse.lab_hours,
            homework_hours=vcourse.homework_hours,
            education_type_id=vcourse.education.education_type_id,
            term_id=vcourse.term.term_id
            WHERE course_number=vcourse.course_number;
    END;
   
 
    
    
    FUNCTION calculate_total_hours(vcourse IN course_typ)
    RETURN number
    AS
    BEGIN
        RETURN (vcourse.class_hours+vcourse.lab_hours)*15;
    END;
    
END COURSES_PACKAGE;
/
--competencies------------------
CREATE OR REPLACE PACKAGE COMPETENCIES_PACKAGE AS
    PROCEDURE add_competency(new_competency in COMP_TYP);
    PROCEDURE add_element_of_competency(new_element in element_typ);
    FUNCTION get_terminal_comp(comp_id CHAR) RETURN comp_typ;
END COMPETENCIES_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY COMPETENCIES_PACKAGE AS
    PROCEDURE add_competency(new_competency in COMP_TYP) AS
    BEGIN
        INSERT INTO competencies VALUES(
            new_competency.comp_id,
            new_competency.comp_name,
            new_competency.specification,
            new_competency.comp_description
        );
        EXCEPTION
        WHEN dup_val_on_index THEN
            UPDATE competencies set 
                comp_name = new_competency.comp_name,
                specification = new_competency.specification,
                comp_description = new_competency.comp_description
                where comp_id = new_competency.comp_id;
    END;
    PROCEDURE add_element_of_competency(new_element in element_typ) AS
    BEGIN
        INSERT INTO elements_of_competency VALUES(
            new_element.element_id,
            new_element.element_name,
            new_element.element_description,
            new_element.comp.comp_id
        );
        EXCEPTION
        WHEN dup_val_on_index THEN
            UPDATE elements_of_competency set 
                element_name = new_element.element_name,
                element_description = new_element.element_description,
                comp_id = new_element.comp.comp_id
                where element_id = new_element.element_id;
    END;
    FUNCTION get_terminal_comp(comp_id CHAR) RETURN comp_typ AS
        comp_typ terminal_comp;
    BEGIN
        FOR arow IN (SELECT * FROM courses) LOOP


END COMPETENCIES_PACKAGE;
/
--hours---------
CREATE OR REPLACE PACKAGE CC_BRIDGE_PACKAGE AS
END CC_BRIDGE_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY CC_BRIDGE_PACKAGE AS
END CC_BRIDGE_PACKAGE;
/