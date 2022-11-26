--View that represents the course_list
CREATE OR REPLACE VIEW course_list_view AS
SELECT term_id AS "Semester",course_number,course_name,course_description,class_hours,lab_hours,homework_hours,COURSES_PACKAGE.calculate_total_hours(class_hours,lab_hours) AS "total hours", domain_name, description AS "Domain description"  FROM dawson_courses
LEFT OUTER JOIN domains USING(domain_id);

--View that conncects competencies and their corresponding elements
CREATE OR REPLACE VIEW competencies_view AS
SELECT comp_id AS "CODE", comp_name AS "Statement of the Competency",comp_description AS "Achievement Context",COMPETENCIES_PACKAGE.find_specification(specification) AS "specification",element_id AS "element number",element_name AS "Elements of the Competency",element_description AS "Performance Criteria" FROM competencies
INNER JOIN elements_of_competency USING(comp_id);

--View that conncets courses with the competencies they contain.
CREATE OR REPLACE VIEW grid_view AS
SELECT DISTINCT course_name,course_number, comp_id AS "Competency Code",comp_name AS "Statement of the Competency"
FROM dawson_courses
LEFT OUTER JOIN element_course USING(course_number)
LEFT OUTER JOIN elements_of_competency USING(element_id)
LEFT OUTER JOIN competencies USING(comp_id);

--View that connects courses with each of their elements of competency
CREATE OR REPLACE VIEW element_course_view AS
SELECT course_number,course_name, element_id AS "element number",element_name AS "Elements of the Competency",associated_time AS "Assosiated time per competency" FROM dawson_courses
LEFT OUTER JOIN element_course USING(course_number)
LEFT OUTER JOIN elements_of_competency USING(element_id) ORDER BY course_name,element_number; 

CREATE OR REPLACE VIEW user_logs_view AS
SELECT * FROM user_logs;

--select * from element_course_view;
--select * from user_logs_view;
--select * from competencies_view;
--SELECT "Semester" FROM course_list_view ;
--select * from grid_view;
--select * from user_logs;