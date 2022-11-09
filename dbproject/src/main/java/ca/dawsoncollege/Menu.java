package ca.dawsoncollege;

public class Menu {
    public void start(){
        boolean running = true;
        while(running){
            welcomeMenuText();
            String input = inputRequest();

            switch(input){
                case "1":
                    System.out.println("Moving to display menu");
                    displayMenu();
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
        //display courses and competencies
        System.out.println("Here are all the courses and their competencies");
        System.out.println("(1) Filter by course id");
        System.out.println("(2) Filter by course name");
        System.out.println("(3) Filter by competency id");
        System.out.println("(4) Filter by competency name");

        String input = inputRequest();

        switch(input){
            case "1":
                System.out.println("Enter your course id");
                
        }
    }

    private void displayCompetencies() {
        //display competencies
    }

    private void displayCourses() {
        //display courses
    }

    private void invalidInput() {
        System.out.println("Invalid Input. Please Try Again");
    }

    private String inputRequest() {
        return System.console().readLine("Please select an option: ");
    }
}
