CREATE OR REPLACE PACKAGE CC_BRIDGE_PACKAGE AS
    PROCEDURE add_join(course_id in DAWSON_COURSES.course_number%TYPE, element_id in ELEMENT_COURSE.element_id%TYPE, associated_time in element_course.associated_time%type);
    PROCEDURE remove_courses(courses_id in element_course.course_number%type);
    PROCEDURE remove_elements(elements_id in element_course.element_id%type);
    PROCEDURE update_allocated_time(course_id in DAWSON_COURSES.course_number%TYPE, elements_id in ELEMENT_COURSE.element_id%TYPE, new_associated_time in element_course.associated_time%type);
    PROCEDURE REMOVE_JOIN(course_id in DAWSON_COURSES.course_number%TYPE, elements_id in ELEMENT_COURSE.element_id%TYPE);
    function timeValidation RETURN VARCHAR2;
END CC_BRIDGE_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY CC_BRIDGE_PACKAGE AS
    PROCEDURE add_join(course_id in DAWSON_COURSES.course_number%TYPE, element_id in ELEMENT_COURSE.element_id%TYPE, associated_time in element_course.associated_time%type)
        AS
        BEGIN
            INSERT INTO element_course VALUES(course_id, element_id, associated_time);
        END;
    PROCEDURE remove_courses(courses_id in element_course.course_number%type)
        AS
        BEGIN
          delete from element_course
           where course_number = courses_id;
        END;
    PROCEDURE remove_elements(elements_id in element_course.element_id%type)
        AS
        BEGIN
          delete from element_course
           where element_id = elementS_id;
        END;
    PROCEDURE update_allocated_time(course_id in DAWSON_COURSES.course_number%TYPE, elements_id in ELEMENT_COURSE.element_id%TYPE, new_associated_time in element_course.associated_time%type)
        AS
        BEGIN
            update element_course
                set associated_time = New_associated_time
                where course_number = course_id and element_id = elements_id; 
        END;
    PROCEDURE REMOVE_JOIN(course_id in DAWSON_COURSES.course_number%TYPE, elements_id in ELEMENT_COURSE.element_id%TYPE)
        AS
        begin
            DELETE FROM ELEMENT_COURSE 
                where course_number = course_id and element_id = elements_id;
        end;
    function timeValidation RETURN VARCHAR2
        AS 
        ERROR_TEXT VARCHAR2(1000);
        COURSE_HOURS NUMBER;
        COMPETENCY_HOURS NUMBER;
        cursor element_times is (select COURSE_NUMBER, COURSE_NAME from DAWSON_COURSES 
        GROUP BY COURSE_NUMBER, COURSE_NAME);
        Begin
            FOR element IN element_times loop
                SELECT ((lab_hours+class_hours)*15)INTO COURSE_HOURS 
                    FROM DAWSON_COURSES 
                    WHERE COURSE_NUMBER = element.COURSE_NUMBER;

                SELECT ROUND(SUM(Associated_time),0) INTO COMPETENCY_HOURS 
                    FROM ELEMENT_COURSE
                    WHERE COURSE_NUMBER LIKE '420-510-DW';
                    if COURSE_HOURS!=COMPETENCY_HOURS then
                      ERROR_TEXT := ERROR_TEXT||chr(13)||chr(10)||'c Hours conflicting for the course '||element.COURSE_NAME;
                    end if;
            end loop;
            return ERROR_TEXT;
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
    FUNCTION calculate_total_hours(dawson_class_hours IN dawson_courses.class_hours%type,dawson_lab_hours IN dawson_courses.lab_hours%TYPE) RETURN number; 
    FUNCTION getCredits(class_time dawson_courses.class_hours%type,lab_time dawson_courses.lab_hours%type,individual_time dawson_courses.homework_hours%type) RETURN number;
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
            vcourse.term.term_id,
            vcourse.domain_id
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
            term_id=vcourse.term.term_id,
            domain_id=vcourse.domain_id
            WHERE course_number=vcourse.course_number;
    END;
   
 
    
    
    FUNCTION calculate_total_hours(dawson_class_hours IN dawson_courses.class_hours%type,dawson_lab_hours IN dawson_courses.lab_hours%TYPE)
    RETURN number
    AS
    BEGIN
        RETURN (dawson_class_hours+dawson_lab_hours)*15;
    END;

    FUNCTION getCredits(class_time dawson_courses.class_hours%type,lab_time dawson_courses.lab_hours%type,individual_time dawson_courses.homework_hours%type) RETURN number AS
    BEGIN
        RETURN (class_time+lab_time+individual_time)/3;
    END;
    
END COURSES_PACKAGE;
/
--competencies------------------
CREATE OR REPLACE PACKAGE COMPETENCIES_PACKAGE AS
    PROCEDURE update_competency(new_competency IN COMP_TYP);
    PROCEDURE add_competency(new_competency in COMP_TYP);
    PROCEDURE update_element_of_competency(new_element in element_typ);
    PROCEDURE add_element_of_competency(new_element in element_typ);
    PROCEDURE remove_element(rem_element_id IN varchar2);
    PROCEDURE remove_competency(rem_comp_id IN char);
    FUNCTION find_specification(specification_number IN competencies.specification%TYPE) RETURN varchar2;
    --FUNCTION get_terminal_comp(comp_id_check CHAR) RETURN varchar2;
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
        -- EXCEPTION
        -- WHEN dup_val_on_index THEN
        --     update_competency(new_competency);
    END;
    
    PROCEDURE update_element_of_competency(new_element in element_typ) AS
    BEGIN
        UPDATE elements_of_competency set 
            element_name = new_element.element_name,
            element_description = new_element.element_description,
            comp_id = new_element.comp_id
            where element_id = new_element.element_id;
    END;
    
    PROCEDURE add_element_of_competency(new_element in element_typ) AS
    BEGIN
        INSERT INTO elements_of_competency VALUES(
            new_element.element_id,
            new_element.element_number,
            new_element.element_name,
            new_element.element_description,
            new_element.comp_id
        );
        -- EXCEPTION
        -- WHEN dup_val_on_index THEN
        --     update_element_of_competency(new_element);
    END;

    PROCEDURE remove_competency(rem_comp_id IN char) AS
    BEGIN
        DELETE FROM COMPETENCIES WHERE COMP_ID = rem_comp_id;
    END;

    PROCEDURE remove_element(rem_element_id IN varchar2) AS
    BEGIN
        CC_BRIDGE_PACKAGE.remove_elements(rem_element_id);
        DELETE FROM ELEMENTS_OF_COMPETENCY WHERE element_id = rem_element_id;
    END;
    FUNCTION find_specification(specification_number IN competencies.specification%TYPE) RETURN varchar2
    AS
    BEGIN
        IF specification_number=1 THEN
            RETURN 'Mandatory';
        ELSE
            RETURN 'Optional';
        END IF;
    END;

--    FUNCTION get_terminal_comp(comp_id_check CHAR) RETURN varchar2 AS
--        --comp_typ terminal_comp;
--        last_term varchar2(2);
--    BEGIN
--        SELECT MAX(term_id) INTO last_term FROM TERM_seasons 
--        JOIN dawson_courses USING(term_id) 
--        JOIN ELEMENT_COURSE USING(COURSE_NUMBER) 
--        JOIN ELEMENTS_OF_COMPETENCY USING(ELEMENT_ID) 
--        JOIN COMPETENCIES USING(COMP_ID)
--        WHERE comp_id = comp_id_check;
--        
--        return last_term;
--    END;
END COMPETENCIES_PACKAGE;
/

