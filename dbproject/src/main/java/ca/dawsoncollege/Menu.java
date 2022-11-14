package ca.dawsoncollege;

import java.sql.SQLException;

public class Menu {
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
                            CourseListServices dbDriver = new CourseListServices(user, password);
                        }
                        catch(SQLException e){
                            if(loginAttempts == 0){
                                System.out.println("Login Failed. Returning to main menu");
                                break;
                            }
                            System.out.println("Please Try Again(" + loginAttempts+ " attempts remaining)");
                            continue;
                        }
                        displayMenu();
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

    private void displayMenu() {
        boolean running = true;
        while(running){
            displayMenuText();

            String input = inputRequest();

            switch(input){
                case "1":
                    displayCourses();
                    break;
                case "2":
                    displayCompetencies();
                    break;
                case "3":
                    displayFull();
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

    private void welcomeMenuText() {
        System.out.println("Welcome to the database");
        System.out.println("(1) Log-In");
        System.out.println("(2) Disconnect");
    }

    private void displayMenuText() {
        System.out.println("Welcome to the Display Menu");
        System.out.println("(1) Display All Courses");
        System.out.println("(2) Display All Competencies");
        System.out.println("(3) Full Display");
        System.out.println("(4) Back");
    }

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
            System.out.println("(2) Filter by competency name");
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

    private void displayCourses() {
        boolean running = true;
        while(running){
            System.out.println("Here are all the courses");
            //display user friendly table of objects
            System.out.println("(1) Filter by course id");
            System.out.println("(2) Filter by course name");
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

    private void filterCompetencyName() {
        System.out.println("Enter your competency name");
    }

    private void filterCompetencyId() {
        System.out.println("Enter your competency id");
    }

    private void filterCourseName() {
        System.out.println("Enter your course name");
    }

    private void filterCourseId() {
        System.out.println("Enter your course id");
    }

    private void invalidInput() {
        System.out.println("Invalid Input. Please Try Again");
    }

    private String inputRequest() {
        return System.console().readLine("Please select an option: ");
    }
}
