CREATE OR REPLACE VIEW dawson_courses_view AS
SELECT course_number,course_name,course_description,class_hours,lab_hours,homework_hours,COURSES_PACKAGE.calculate_total_hours(class_hours,lab_hours) AS "total hours",term_id AS "semester",season_name,education_type,domain_name,description AS "domain description" FROM dawson_courses
LEFT OUTER JOIN domains USING(domain_id)
LEFT OUTER JOIN educations USING(education_type_id)
LEFT OUTER JOIN term_seasons USING(term_id)
LEFT OUTER JOIN seasons USING(season_id);

CREATE OR REPLACE VIEW competencies_view AS
SELECT comp_id, comp_name,comp_description,COMPETENCIES_PACKAGE.find_specification(specification) AS "specification",element_name,element_description FROM competencies
INNER JOIN elements_of_competency USING(comp_id);

CREATE OR REPLACE VIEW full_view AS
SELECT course_number,course_name,course_description,class_hours,term_id AS "semester",season_name,education_type,lab_hours,homework_hours,COURSES_PACKAGE.calculate_total_hours(class_hours,lab_hours) AS "total hours",domain_name,description AS "domain description",comp_name,COMPETENCIES_PACKAGE.find_specification(specification) AS "specification",comp_description,element_name,element_description
FROM dawson_courses
LEFT OUTER JOIN domains USING(domain_id)
LEFT OUTER JOIN educations USING(education_type_id)
LEFT OUTER JOIN term_seasons USING(term_id)
LEFT OUTER JOIN seasons USING(season_id)
LEFT OUTER JOIN element_course USING(course_number)
LEFT OUTER JOIN elements_of_competency USING(element_id)
LEFT OUTER JOIN competencies USING(comp_id);

CREATE OR REPLACE VIEW user_logs_view AS
SELECT * FROM user_logs;

--select * from user_logs_view;
--select * from competencies_view;
--SELECT * FROM dawson_courses_view ;
--select * from full_view;