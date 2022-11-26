package ca.dawsoncollege;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        Menu menu = new Menu();
        menu.start();
        // System.out.println("Enter your username: ");
        // String username=System.console().readLine();
        // System.out.println("Enter your password: ");
        // String password=new String(System.console().readPassword());
        // CourseListServices service=new CourseListServices(username, password);
        

    }
}

// System.out.println("Enter your username: ");
//         String username=System.console().readLine();
//         System.out.println("Enter your password: ");
//         String password=new String(System.console().readPassword());
//         CourseListServices service=new CourseListServices(username, password);

//         String courseNumber="420-310-DW";
//         String courseName="Programming III";
//         String courseDescription="The course will enhance the students knowledge of object-oriented programming and Java to produce stand-alone applications employing reusable objects, data structures and the Java collections framework. The concepts of inheritance, polymorphism, data abstraction and programming to interfaces are used to design software. Students are introduced to software version control and effective team collaboration.";
//         int classHours=3;
//         int labHours=3;
//         int homeworkHours=3;

       

        
//         String stringTerm="Term 3";
//         //Finds the last digit(s) of the String "Term X"
//         String digit=stringTerm.substring(stringTerm.indexOf(" ")+1,stringTerm.length());
//         //Converts the digit to an int so that it can be used both in the database(term_id type is number(2,0)) and the if/else statement of the addCousre method.
//         int term=Integer.parseInt(digit);

//         String educationType="Concentration";
//         educationType=educationType.toLowerCase();

        
//         service.addCourse(courseNumber, courseName, courseDescription, classHours,labHours,homeworkHours,term,educationType);
//         System.out.println(courseName+" has been added.");

//         courseNumber="420-410-DW";
//         courseName="Programming IV";
//         courseDescription= "The course will focus on the construction of stand-alone applications that requires the design and implementation of multiple classes. The student will study standard data structures and their relevant use in the manipulation and management of data in applications. The student will learn to use databases from within an application and will work individually on a project to develop a complete software application."; 
//         classHours=3;
//         labHours=3;
//         homeworkHours=3;
//         stringTerm="Term 4";
//         digit=stringTerm.substring(stringTerm.indexOf(" ")+1,stringTerm.length());
//         term=Integer.parseInt(digit);
//         educationType="Concentration";
//         educationType=educationType.toLowerCase();
//         service.addCourse(courseNumber, courseName, courseDescription, classHours,labHours,homeworkHours,term,educationType);
//         System.out.println(courseName+" has been added.");
//         service.close();
//         //After the data is added to the databse, connection gets closed.
        
        
//     }
// }
