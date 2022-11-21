CREATE OR REPLACE PACKAGE CC_BRIDGE_PACKAGE AS
    PROCEDURE add_join(course in course_typ, element in element_typ, associated_time in element_course.associated_time%type);
    PROCEDURE remove_courses(courses_id in element_course.course_number%type);
    PROCEDURE remove_elements(elements_id in element_course.element_id%type);
    PROCEDURE update_allocated_time(course in course_typ, element in element_typ, New_associated_time in element_course.associated_time%type);
END CC_BRIDGE_PACKAGE;
/

CREATE OR REPLACE PACKAGE BODY CC_BRIDGE_PACKAGE AS
    PROCEDURE add_join(course in course_typ, element in element_typ, associated_time in element_course.associated_time%type)
        AS
        BEGIN
            INSERT INTO element_course VALUES(course.course_number,element.element_id,associated_time);
        END;
    PROCEDURE remove_courses(courses_id in element_course.course_number%type)
        AS
        BEGIN
          delete from element_course
           where courses_id = course_number;
        END;
    PROCEDURE remove_elements(elements_id in element_course.element_id%type)
        AS
        BEGIN
          delete from element_course
           where elements_id = element_id;
        END;
    PROCEDURE update_allocated_time(course in course_typ, element in element_typ, New_associated_time in element_course.associated_time%type)
        AS
        BEGIN
            update element_course
                set associated_time = New_associated_time
                where course.course_number = course_number and element.element_id = element_id; 
        END;         
END CC_BRIDGE_PACKAGE;
/
--update data
CREATE OR REPLACE PACKAGE COURSES_PACKAGE AS
    PROCEDURE delete_course(dawson_course_number IN dawson_courses.course_number%TYPE);
    PROCEDURE add_course (
        vcourse IN course_typ
    );
    PROCEDURE update_course(vcourse IN course_typ) ;
    FUNCTION calculate_total_hours(vcourse IN course_typ) RETURN number; 
END COURSES_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY COURSES_PACKAGE AS

    PROCEDURE delete_course(dawson_course_number IN dawson_courses.course_number%TYPE)
    AS
    BEGIN
        CC_BRIDGE_PACKAGE.remove_courses(dawson_course_number);
        DELETE FROM dawson_courses WHERE dawson_course_number=course_number;
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
    PROCEDURE update_competency(new_competency IN COMP_TYP);
    PROCEDURE update_element_of_competency(new_element in element_typ);
    PROCEDURE add_competency(new_competency in COMP_TYP);
    PROCEDURE add_element_of_competency(new_element in element_typ);
    PROCEDURE remove_element(rem_element_id IN varchar2);
    PROCEDURE remove_competency(rem_comp_id IN varchar2);
    FUNCTION get_terminal_comp(comp_id CHAR) RETURN comp_typ;
END COMPETENCIES_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY COMPETENCIES_PACKAGE AS
    PROCEDURE update_competency(new_competency IN COMP_TYP) AS
    BEGIN
        UPDATE competencies SET 
            comp_name = new_competency.comp_name,
            specification = new_competency.specification,
            comp_description = new_competency.comp_description
            WHERE comp_id = new_competency.comp_id;
    END;
    

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
            update_competency(new_competency);
--            UPDATE competencies set 
--                comp_name = new_competency.comp_name,
--                specification = new_competency.specification,
--                comp_description = new_competency.comp_description
--                where comp_id = new_competency.comp_id;
    END;
    
    PROCEDURE update_element_of_competency(new_element in element_typ) AS
    BEGIN
        UPDATE elements_of_competency set 
            element_name = new_element.element_name,
            element_description = new_element.element_description,
            comp_id = new_element.comp.comp_id
            where element_id = new_element.element_id;
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
        update_element_of_competency(new_element);
--            UPDATE elements_of_competency set 
--                element_name = new_element.element_name,
--                element_description = new_element.element_description,
--                comp_id = new_element.comp.comp_id
--                where element_id = new_element.element_id;
    END;
    PROCEDURE remove_competency(rem_comp_id IN varchar2) AS
    BEGIN
        DELETE FROM COMPETENCIES WHERE COMP_ID = rem_comp_id;
    END;
    PROCEDURE remove_element(rem_element_id IN varchar2) AS
    BEGIN
        CC_BRIDGE_PACKAGE.remove_elements(rem_element_id);
        DELETE FROM ELEMENTS_OF_COMPETENCY WHERE element_id = rem_element_id;
    END;
    FUNCTION get_terminal_comp(comp_id CHAR) RETURN comp_typ AS
        comp_typ terminal_comp;
    BEGIN
        FOR arow IN (SELECT term_id, COURSE_NAME, COMP_ID FROM TERM_seasons JOIN dawson_courses USING(term_id) JOIN ELEMENT_COURSE USING(COURSE_NUMBER) JOIN ELEMENTS_OF_COMPETENCY USING(ELEMENT_ID) JOIN COMPETENCIES USING(COMP_ID)) LOOP
            dbms_output.put_line(arow.term_id || ' ' || arow.course_name || ' ' || arow.comp_id);
        END LOOP;
    END;
END COMPETENCIES_PACKAGE;
/
--hours---------
CREATE OR REPLACE PACKAGE CC_BRIDGE_PACKAGE AS
    PROCEDURE add_join(course in course_typ, element in element_typ, associated_time in element_course.associated_time%type);
    PROCEDURE remove_courses(courses_id in element_course.course_number%type);
    PROCEDURE remove_elements(elements_id in element_course.element_id%type);
    PROCEDURE update_allocated_time(ourse in course_typ, element in element_typ, New_associated_time in element_course.associated_time%type);
END CC_BRIDGE_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY CC_BRIDGE_PACKAGE AS
    PROCEDURE add_join(course in course_typ, element in element_typ, associated_time in element_course.associated_time%type)
        AS
        BEGIN
            INSERT INTO element_course VALUES(course.course_number,element.element_id,associated_time);
        END;
    PROCEDURE remove_courses(courses_id in element_course.course_number%type)
        AS
        BEGIN
          delete from element_course
           where courses_id = course_number;
        END;
    PROCEDURE remove_elements(elements_id in element_course.element_id%type)
        AS
        BEGIN
          delete from element_course
           where elements_id = element_id;
        END;
    PROCEDURE update_allocated_time(course in course_typ, element in element_typ, New_associated_time in element_course.associated_time%type)
        AS
        BEGIN
            update element_course
                set associated_time = New_associated_time
                where course.course_number = course_number and element.element_id = element_id; 
        END;

    function timeValidation()return VARCHAR2(1000);
        AS 
        ERROR_TEXT VARCHAR2(1000)
        COURSE_HOURS NUMBER;
        COMPETENCY_HOURS NUMBER;
        cursor element_times is (select COURSE_NUMBER COURSE_NAME from element_course 
        JOIN ELEMENTS_OF_COMPETENCY USING (ELEMENT_ID) GROUP BY COURSE_NUMBER, COMP_ID);
        Begin
            FOR element IN element_times loop
                SELECT ((lab_hours+class_hours)*15)INTO COURSE_HOURS 
                    FROM DAWSON_COURSES 
                    WHERE COURSE_NUMBER = ELEMENT.COURSE_NUMBER;

                SELECT ROUND(SUM(Associated_time),0) INTO COMPETENCY_HOURS 
                    FROM ELEMENT_COURSE
                    WHERE COURSE_NUMBER LIKE '420-510-DW';
                    if COURSE_HOURS!=COMPETENCY_HOURS then
                      ERROR_TEXT += "/n Hours conflict with course "+ELEMENT.COURSE_NAME;
                    end if;
            end loop
            return ERROR_TEXT;
    END;
END CC_BRIDGE_PACKAGE;
/
