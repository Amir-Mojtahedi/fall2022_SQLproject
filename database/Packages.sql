@types.sql
--courses-----------------
CREATE OR REPLACE PACKAGE COURSES_PACKAGE AS
END COURSES_PACKAGE;
/
CREATE OR REPLACE PACKAGE BODY COURSES_PACKAGE AS
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