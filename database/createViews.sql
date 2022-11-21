CREATE OR REPLACE VIEW dawson_courses_view AS
SELECT course_number,course_name,course_description,class_hours,term_id,season_name,education_type,lab_hours,homework_hours,COURSES_PACKAGE.calculate_total_hours(class_hours,lab_hours) AS "total hours",domain_name,description FROM dawson_courses
LEFT OUTER JOIN domains USING(domain_id)
LEFT OUTER JOIN educations USING(education_type_id)
LEFT OUTER JOIN term_seasons USING(term_id)
LEFT OUTER JOIN seasons USING(season_id);

SELECT * FROM dawson_courses_view;