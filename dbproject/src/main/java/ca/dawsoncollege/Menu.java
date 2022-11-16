package ca.dawsoncollege;

import java.io.Console;
import java.sql.SQLException;

public class Menu {
    private CourseListServices dbDriver;

    public void start(){
        boolean running = true;
        while(running){
            welcomeMenuText();
            String input = inputRequest();

            switch(input){
                case "1":
                    for (int loginAttempts = 2; loginAttempts >= 0; loginAttempts--){
                        try{
                            String user = System.console().readLine("Username: ");
                            String password = new String(System.console().readPassword("Password: "));
                            this.dbDriver = new CourseListServices(user, password);
                        }
                        catch(SQLException e){
                            if(loginAttempts == 0){
                                System.out.println("Login Failed. Returning to main menu");
                                break;
                            }
                            System.out.println("Please Try Again(" + loginAttempts+ " attempts remaining)");
                            continue;
                        }
                        mainMenu();
                        break;
                    }
                    break;
                case "2":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void mainMenu() {
        boolean running = true;
        while(running){
            optionsMenuText();

            String input = inputRequest();

            switch(input){
                case "1":
                    displayMenu();
                    break;
                case "2":
                    addMenu();
                    break;
                case "3":
                    deleteMenu();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void displayMenu() {
        boolean running = true;
        while(running){
            System.out.println("Welcome to the Display Options");
            System.out.println("(1) Display All Courses");
            System.out.println("(2) Display All Competencies");
            System.out.println("(3) Display All Elements of Competencies");
            System.out.println("(4) Full Display");
            System.out.println("(5) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    displayCourses();
                    break;
                case "2":
                    displayCompetencies();
                    break;
                case "3":
                    displayElements();
                    break;
                case "4":
                    displayFull();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
                
            }
        }
    }

    private void welcomeMenuText() {
        System.out.println("Welcome to the database");
        System.out.println("(1) Log-In");
        System.out.println("(2) Disconnect");
    }

    private void optionsMenuText() {
        System.out.println("Welcome to the Options Menu");
        
        System.out.println("(1) Display Options");
        System.out.println("(2) Add Options");
        System.out.println("(3) Delete Options");
        System.out.println("(4) Back");
    }

    /*
     * DISPLAY FUNCTIONS
     */
    private void displayFull() {
        boolean running = true;
        while(running){
            System.out.println("Here are all the courses and their competencies");
            //display user friendly table of objects
            System.out.println("(1) Filter by course id");
            System.out.println("(2) Filter by course name");
            System.out.println("(3) Filter by competency id");
            System.out.println("(4) Filter by competency name");
            System.out.println("(5) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    filterCourseId();
                    break;
                case "2":
                    filterCourseName();
                    break;
                case "3":
                    filterCompetencyId();
                    break;
                case "4":
                    filterCompetencyName();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void displayCompetencies() {
        
        boolean running = true;
        while(running){
            System.out.println("Here are all the competencies");
            //display user friendly table of objects
            System.out.println("(1) Filter by competency id");
            System.out.println("(2) Filter by keyword in competency name");
            System.out.println("(3) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    filterCompetencyId();
                    break;
                case "2":
                    filterCompetencyName();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void displayElements() {
        boolean running = true;
        while(running){
            System.out.println("Here are all of the Elements of Competencies");
            //display user friendly table of elements
            System.out.println("(1) Filter by element id");
            System.out.println("(2) Filter by keyword in  element name");
            System.out.println("(3) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    filterElementName();
                    break;
                case "2":
                    filterElementId();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void displayCourses() {
        boolean running = true;
        while(running){
            System.out.println("Here are all the courses");
            //display user friendly table of objects
            System.out.println("(1) Filter by course id");
            System.out.println("(2) Filter by keyword in course name");
            System.out.println("(3) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    filterCourseId();
                    break;
                case "2":
                    filterCourseName();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void filterElementId() {
        System.out.println("Enter your element id");
        //prepared statement with a element id check
    }

    private void filterElementName() {
        System.out.println("Enter your element name");
        //prepared statement with a competency name using LIKE
    }

    private void filterCompetencyName() {
        System.out.println("Enter your competency name");
        //prepared statement with a competency name using LIKE
    }

    private void filterCompetencyId() {
        System.out.println("Enter your competency id");
        //prepared statement with a competency id check
    }

    private void filterCourseName() {
        System.out.println("Enter your course name");
        //prepared statement with a course name using LIKE
    }

    private void filterCourseId() {
        System.out.println("Enter your course id");
        //prepared statement with a course id
    }

    /*
     * ADD FUNCTIONS
     */

    private void addMenu() {
        boolean running = true;
        while(running){
            System.out.println("Welcome to the Add Options");
            System.out.println("(1) Add a Course");
            System.out.println("(2) Add an Element of a Competency");
            System.out.println("(3) Add a Competency");
            System.out.println("(4) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    addCourseMenu();
                    break;
                case "2":
                    addElementMenu();
                    break;
                case "3":
                    addCompetencyMenu();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void addElementMenu() {
        //call a method in CourseListServices that adds an element
        String elementId = System.console().readLine("Please input your new element id: ");
        String elementName = System.console().readLine("Please input your new element name: ");
        String elementDescription = System.console().readLine("Please input your new element description: ");
        String compId = System.console().readLine("Please input the competency id of the competency this element connects to: ");

        //addElement from dbDriver with these inputs
    }

    private void addCompetencyMenu() {
        //call a method in CourseListServices that adds a competency
    }

    private void addCourseMenu() {
        //call a method in CourseListServices that adds a course
    }

     /*
      * DELETE FUNCTIONS
      */

      private void deleteMenu() {
        boolean running = true;
        while(running){
            System.out.println("Welcome to the Delete Options");
            System.out.println("(1) Delete a Course");
            System.out.println("(2) Delete a Competency");
            System.out.println("(3) Delete an Element");
            System.out.println("(4) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    deleteCourse();
                    break;
                case "2":
                    deleteCompetency();
                    break;
                case "3":
                    deleteElement();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }

    private void deleteCourse() {
        String courseToRemove = System.console().readLine("Please write the course id of the course you would like to remove: ");
        //call remove procedure with course id
    }

    private void deleteCompetency() {
        String compToRemove = System.console().readLine("Please write the competency id of the course you would like to remove: ");
        //call remove procedure with comp id
    }

    private void deleteElement() {
        String elementToRemove = System.console().readLine("Please write the element id of the course you would like to remove: ");
        //call remove procedure with elemend id
    }

    /*
       * GENERAL FUNCTIONS
       */
    private void invalidInput() {
        System.out.println("Invalid Input. Please Try Again");
    }

    private String inputRequest() {
        return System.console().readLine("Please select an option: ");
    }
}
