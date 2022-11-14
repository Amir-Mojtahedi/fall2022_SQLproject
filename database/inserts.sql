INSERT INTO seasons VALUES('1','Fall');
INSERT INTO seasons VALUES('2','Winter');


INSERT INTO educations VALUES('1','General');
INSERT INTO educations VALUES('2','Concentration');

INSERT INTO term_seasons VALUES(1,'1');
INSERT INTO term_seasons VALUES(2,'2');
INSERT INTO term_seasons VALUES(3,'1');
INSERT INTO term_seasons VALUES(4,'2');
INSERT INTO term_seasons VALUES(5,'1');
INSERT INTO term_seasons VALUES(6,'2');


INSERT INTO dawson_courses VALUES('201-NYC-05','Linear Algebra','Systems of linear equations and elementary operations, matrices and
determinants, vectors, lines, planes and vector spaces are studied in this course.',3,2,3,'2',3);

INSERT INTO dawson_courses VALUES('420-310-DW','Programming III','The course will enhance the students’ knowledge of object-oriented
programming and Java to produce stand-alone applications employing reusable
objects, data structures and the Java collections framework. The concepts of
inheritance, polymorphism, data abstraction and programming to interfaces are
used to design software. Students are introduced to software version control
and e?ective team collaboration.',3,3,3,'2',3);

INSERT INTO dawson_courses VALUES('420-510-DW','Programming V','The course will focus on the use of algorithms and data structures to simulate
real-life phenomena using an appropriate gaming framework. Projects are
implemented using an object-oriented language.',3,3,3,'2',5);

INSERT INTO dawson_courses VALUES('420-331-DW','Database II','This course will introduce the student to the Oracle environment for building,
deploying, hosting, and managing enterprise-class applications. The student will
use SQL and PL/SQL to build and manage a database. In addition, the student
will complete a case study which involves requirements analysis and data
modeling, implemented using PL/SQL and Java.',3,3,3,'2',3);
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO competencies VALUES('00Q3','Solve computer-related problems using mathematics.','1','Based on situational problems
Using quantitative data');

INSERT INTO competencies VALUES('00Q6','Use an object-oriented development approach.','1','Based on a problem
Using nomenclature and coding rules');

INSERT INTO competencies VALUES('00SE','Interact in a professional setting.','1','In various types of work settings
Using application programming and network management standards, methods and best practices
Using laws, code of ethics and corporate policies');

INSERT INTO competencies VALUES('00SS','Develop native applications with a database.','0','For different target platforms: tablets, smartphones, desktop computers, etc.
For new applications and applications to be modified
Based on design documents
Using a compiler designed for the target platform, a cross compiler or an interpreter
Using an emulator on the development platform
Using images
Using issue tracking and version control procedures');

INSERT INTO competencies VALUES('00SR','Develop native applications without a database.','0','For different target platforms: tablets, smartphones, desktop computers, etc.
For new applications and applications to be modified
Based on design documents
Using a compiler designed for the target platform, a cross compiler or an interpreter
Using an emulator on the development platform
Using images, sounds and videos
Using issue tracking and version control procedures');

INSERT INTO competencies VALUES('00SW','Develop gaming or simulation applications.','0','For different games or simulations: action games, role-playing games, flight simulators, industrial process simulators, etc.
For new applications and applications to be modified
Based on design documents
Using game or simulation engines
Using sounds and 2D and 3D images
Using issue tracking and version control procedures');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO elements_of_competency VALUES('1','Process numbers as they are represented in the computer memory.','Accurate representation of numbers in different base systems
Accurate conversion of numbers from one base to another
Accurate interpretation of the ranges of numeric types
Accurate interpretation of the precision of numeric types
Appropriate choice of the numeric type','00Q3');

INSERT INTO elements_of_competency VALUES('2','Represent two-dimensional geometric figures in the form of digital images.','Correct identification of the size, dimensions and resolution of the image
Accurate representation of points and lines
Correct application of translation equations, rotation equations and homothetic equations
Consistency of geometric figures with their graphic representation','00Q3');

INSERT INTO elements_of_competency VALUES('3','Model multi-variable logical reasoning.','Correct formulation of logical functions
Efficient simplification of logical functions
Appropriate use of Boolean algebra
Accurate production of truth tables
Appropriate verification of logical functions','00Q3');

INSERT INTO elements_of_competency VALUES('4','Process quantitative data using descriptive statistics.','Precise calculation of the average, median, variance and standard deviation
Clarity and accuracy of the graphic representation of data
Accurate analysis of results','00Q3');

INSERT INTO elements_of_competency VALUES('5','Analyze the problem.','Breakdown of the problem based on the requirements of an object-oriented approach
Proper identification of input and output data and the nature of the processes
Accurate identification of the classes to be modelled
Proper identification of the algorithms to be created','00Q6');

INSERT INTO elements_of_competency VALUES('6','Model the classes.','Proper identification of class attributes and methods
Proper application of encapsulation and inheritance principles
Proper graphic representation of the classes and their relationships
Compliance with nomenclature rules','00Q6');

INSERT INTO elements_of_competency VALUES('7','Produce the algorithms for the methods.','Appropriate identification of the operations necessary for each method
Proper identification of a logical sequence of operations
Appropriate verification of algorithm correctness
Accurate representation of algorithms','00Q6');

INSERT INTO elements_of_competency VALUES('8','Create the graphic interface.','Appropriate choice of graphic elements for display and data input
Proper layout of graphic elements
Proper set-up of graphic elements','00Q6');

INSERT INTO elements_of_competency VALUES('9','Program the classes.','Appropriate choice of instructions, types of primitive data and data structures
Logical organization of the instructions
Proper programming of messages to be displayed for the user
Proper integration of the classes into the program
Proper program performance
Compliance with the language syntax
Compliance with coding rules','00Q6');

INSERT INTO elements_of_competency VALUES('10','Document the code.','Clear comments in the computer code
Clear record of the programming support documentation
Appropriate use of the documentation generators','00Q6');

INSERT INTO elements_of_competency VALUES('11','Establish professional relationships with users and clients.','Attitudes and behaviours that demonstrate the ability to listen
Adaptation of the level of language to the situation
Observance of rules of politeness and common courtesy
Observance of the client-based approach','00SE');

INSERT INTO elements_of_competency VALUES('12','Work within a multidisciplinary team.','Attitudes and behaviours that demonstrate respect, openness and a collaborative spirit
Effective communication with all team members
Proper performance of assigned tasks
Compliance with rules for optimal team function
Respect for the corporate culture
Compliance with application programming and network management standards, methods and best practices
Observance of the limits of the scope of professional intervention and respect for the expertise of team members in other occupations
Adherence to deadlines','00SE');

INSERT INTO elements_of_competency VALUES('13','Become familiar with the legal obligations and rules of professional ethics.','Accurate listing of the main offences and criminal acts in information technology
Accurate listing of the main breaches of intellectual property rights in information technology
Accurate assessment of the consequences of offences, criminal acts and breaches of intellectual property
Determination of the measures appropriate to the situation
Compliance with laws, codes of ethics and corporate policies','00SE');

INSERT INTO elements_of_competency VALUES('14','Analyze the application development project.','Accurate analysis of design documents
Proper identification of the tasks to be carried out','00SS');

INSERT INTO elements_of_competency VALUES('15','Prepare the computer development environment.','Proper installation of software and libraries on the development platform
Proper configuration of the target platform
Proper configuration of the version control system
Proper importing of the source code','00SS');

INSERT INTO elements_of_competency VALUES('16','Prepare the database(s).','Proper creation or adaptation of the local or remote database
Proper insertion of initial or test data
Compliance with the data model','00SS');

INSERT INTO elements_of_competency VALUES('17','Generate or program the graphical user interface.','Appropriate choice and use of graphic elements for display and input
Proper integration of images
Adaptation of the interface based on the display format and resolution','00SS');

INSERT INTO elements_of_competency VALUES('18','Program the application logic.','Proper programming or integration of authentication and authorization mechanisms
Proper programming of interactions between the graphical user interface and the user
Appropriate choice of clauses, operators, commands or parameters in database queries
Correct handling of database data
Proper programming of data synchronization
Appropriate use of data exchange services
Proper application of internationalization techniques
Precise application of secure programming techniques','00SS');

INSERT INTO elements_of_competency VALUES('19','Analyze the application development project.','Accurate analysis of design documents
Proper identification of tasks to be carried out','00SR');

INSERT INTO elements_of_competency VALUES('20','Prepare the computer development environment.','Proper installation of software and libraries on the development platform
Proper configuration of the target platform
Proper configuration of the version control system
Proper importing of the source code','00SR');

INSERT INTO elements_of_competency VALUES('21','Generate or program the graphical interface.','Appropriate choice and use of graphic elements for display and input
Proper integration of images
Adaptation of the interface based on the display format and resolution','00SR');

INSERT INTO elements_of_competency VALUES('22','Program the application logic.','Proper programming of interactions between the graphical user interface and the user
Proper programming of communications between the peripheral devices and the software functions of the target platform
Effective use of execution threads
Proper integration of sounds and videos
Proper application of internationalization techniques
Precise application of secure coding techniques','00SR');

INSERT INTO elements_of_competency VALUES('23','Control the quality of the application.','Precise application of test plans in the emulator and on the target platform
Thorough reviews of code and security
Relevance of the corrective actions
Compliance with issue tracking and version control procedures
Compliance with design documents','00SR');

INSERT INTO elements_of_competency VALUES('24','Analyze the application development project.','Accurate analysis of design documents
Proper identification of the tasks to be carried out','00SW');

INSERT INTO elements_of_competency VALUES('25','Prepare the computer development environment.','Proper installation of software and libraries
Appropriate configuration of the version control system
Proper importing of the source code','00SW');

INSERT INTO elements_of_competency VALUES('26','Generate real or virtual world representations.','Appropriate choice and use of graphic elements for display and input
Proper integration of 2D and 3D images
Adaptation of the interface based on the display format and resolution','00SW');

INSERT INTO elements_of_competency VALUES('27','Program the game or simulation logic.','Proper programming of behaviours of graphic elements and peripheral devices
Proper programming of visual effects
Accurate integration of sounds
Proper programming of interactions
Proper application of internationalization techniques
Precise application of secure programming techniques
Appropriate use of game or simulation engines','00SW');

INSERT INTO elements_of_competency VALUES('28','Control the quality of the application.','Precise application of test plans
Thorough reviews of code and security
Relevance of the corrective actions
Compliance with issue tracking and version control procedures
Compliance with design documents','00SW');

INSERT INTO elements_of_competency VALUES('29','Participate in the deployment of the application.','Appropriate preparation of the application in view of its deployment, export or installation
Proper deployment, export or installation of the application','00SW');

INSERT INTO elements_of_competency VALUES('30','Produce the documentation.','Proper identification of the information to be written up
Clear record of the work carried out','00SW');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO element_course VALUES('1','201-NYC-05',18.75);
INSERT INTO element_course VALUES('2','201-NYC-05',18.75);
INSERT INTO element_course VALUES('3','201-NYC-05',18.75);
INSERT INTO element_course VALUES('4','201-NYC-05',18.75);
INSERT INTO element_course VALUES('5','420-310-DW',14);
INSERT INTO element_course VALUES('6','420-310-DW',14);
INSERT INTO element_course VALUES('7','420-310-DW',14);
INSERT INTO element_course VALUES('8','420-310-DW',14);
INSERT INTO element_course VALUES('9','420-310-DW',14);
INSERT INTO element_course VALUES('10','420-310-DW',14);
INSERT INTO element_course VALUES('11','420-310-DW',2);
INSERT INTO element_course VALUES('12','420-310-DW',2);
INSERT INTO element_course VALUES('13','420-310-DW',2);
INSERT INTO element_course VALUES('14','420-331-DW',18);
INSERT INTO element_course VALUES('15','420-331-DW',18);
INSERT INTO element_course VALUES('16','420-331-DW',18);
INSERT INTO element_course VALUES('17','420-331-DW',18);
INSERT INTO element_course VALUES('18','420-331-DW',18);
INSERT INTO element_course VALUES('19','420-510-DW',6);
INSERT INTO element_course VALUES('20','420-510-DW',6);
INSERT INTO element_course VALUES('21','420-510-DW',6);
INSERT INTO element_course VALUES('22','420-510-DW',6);
INSERT INTO element_course VALUES('23','420-510-DW',6);
INSERT INTO element_course VALUES('24','420-510-DW',8.57);
INSERT INTO element_course VALUES('25','420-510-DW',8.57);
INSERT INTO element_course VALUES('26','420-510-DW',8.57);
INSERT INTO element_course VALUES('27','420-510-DW',8.57);
INSERT INTO element_course VALUES('28','420-510-DW',8.57);
INSERT INTO element_course VALUES('29','420-510-DW',8.57);
INSERT INTO element_course VALUES('30','420-510-DW',8.57);

COMMIT;