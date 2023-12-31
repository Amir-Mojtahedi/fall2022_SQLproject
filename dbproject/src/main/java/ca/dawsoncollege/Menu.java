package ca.dawsoncollege;

//import java.io.Console;

//Imports
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Menu {
    //Fields
    private CourseListServices dbDriver;
    final static java.util.Scanner scan = new java.util.Scanner (System.in);

    //strat() uses other methods to build up a nice menu while the program is running.
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

    //mainMenu() creates the main menu. There exist some sub-menus for the main menu that are provoked based on the user's input.
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
                    updateMenu();
                    break;
                case "4":
                    deleteMenu();
                    break;
                case "5":
                    miscMenu();
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
    }
    //Logs menu and validation menu are diplayed using miscMenu() function. This menu is a main menu's sub-menu.
    private void miscMenu() {
        boolean running = true;
        while(running){
            System.out.println("Welcome to the Validation and Logs Center");
            System.out.println("(1) Display validation");
            System.out.println("(2) Display Logs");
            System.out.println("(3) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                try {
                    dbDriver.displayTimeValidity(); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    break;
                case "2":
                    try{
                        this.dbDriver.displayLogs();
                    }
                    catch(SQLException e){
                        
                    }
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

    //diplayMenu() holds within it all the display options. It is one of the main menu's sub-menues
    private void displayMenu() {
        boolean running = true;
        while(running){
            System.out.println("Welcome to the Display Options");
            System.out.println("(1) Display All Courses");
            System.out.println("(2) Display All Competencies");
            System.out.println("(3) Full Display");
            System.out.println("(4) Back");

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

    //welcomeMenuTet() function welcomes the user to the projcect by prompting them to enter their access credentials
    private void welcomeMenuText() {
        System.out.println("Welcome to the database");
        System.out.println("(1) Log-In");
        System.out.println("(2) Disconnect");
    }

    //This functions show all the options when the user successfully logs in to the databse.
    private void optionsMenuText() {
        System.out.println("Welcome to the Options Menu");
        
        System.out.println("(1) Display Options");
        System.out.println("(2) Add Options");
        System.out.println("(3) Update options");
        System.out.println("(4) Delete Options");
        System.out.println("(5) Validation/Logs Options");
        System.out.println("(6) Back");
    }

    /*
     * DISPLAY FUNCTIONS
     */
    private void displayFull() {
        boolean running = true;
        while(running){
            System.out.println("Here are all the courses and their competencies");
            System.out.println("");
            //display user friendly table of objects
            try{
                dbDriver.displayFull("select * from grid_view ORDER BY term_id");
            }
            catch(SQLException e){

            }
            System.out.println("(1) Filter by course id");
            System.out.println("(2) Filter by competency id");
            System.out.println("(3) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    filterCourseIdFull();
                    break;
                case "2":
                    filterCompetencyIdFull();
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

    private void filterCompetencyIdFull() {
        boolean onpage = true;
        while(onpage){
            String filter = System.console().readLine("Please enter the competency code you would like to see or type exit to leave page: ");

            if(filter.equals("exit")){
                onpage = false;
                break;
            }
            try{
                String query = "select * from grid_view WHERE comp_id =";
                query = query + " \'" + filter + "\'";
                this.dbDriver.displayFull(query);
            }
            catch(SQLException e){
            }
        }
    }

    private void filterCourseIdFull() {
        boolean onpage = true;
        while(onpage){
            String filter = System.console().readLine("Please enter the course id you would like to see or type exit to leave page: ");

            if(filter.equals("exit")){
                onpage = false;
                break;
            }
            try{
                String query = "select * from grid_view WHERE course_number =";
                query = query + " \'" + filter + "\'";
                this.dbDriver.displayFull(query);
            }
            catch(SQLException e){
            }
        }
    }

    private void displayCompetencies() {
        
        boolean running = true;
        while(running){
            System.out.println("Here are all the competencies");
            //display user friendly table of objects
            System.out.println("");
            try{
                dbDriver.displayCompetencies();
            }
            catch(SQLException e){

            }
            System.out.println("(1) Filter by competency id");
            System.out.println("(2) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    filterCompetencyId();
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

    //This function display all the courses which are in the database.
    private void displayCourses() {
        boolean running = true;
        while(running){
            System.out.println("Here are all the courses");
            //display user friendly table of objects
            System.out.println("");
            try{
                dbDriver.displayCourses();
            }
            catch(SQLException e){

            }
            System.out.println("(1) Filter by course id");
            System.out.println("(2) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    filterCourseId();
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

    private void filterCompetencyId() {
        boolean onpage = true;
        while(onpage){
            String filter = System.console().readLine("Please enter the competency id you would like to see or type exit to leave page: ");

            if(filter.equals("exit")){
                onpage = false;
                break;
            }
            try{
                this.dbDriver.displayCompetencies(filter);
            }
            catch(SQLException e){
            }
        }
    }

    private void filterCourseId() {
        boolean onpage = true;
        while(onpage){
            String filter = System.console().readLine("Please enter the course id you would like to see or type exit to leave page: ");

            if(filter.equals("exit")){
                onpage = false;
                break;
            }
            try{
                this.dbDriver.DisplayCourses(filter);
            }
            catch(SQLException e){

            }
        }
    }

    /*
     * ADD FUNCTIONS
     */

     //This function creates a add menu which is one of the sub-menus of the main menu.
    private void addMenu() {
        boolean running = true;
        while(running){
            System.out.println("Welcome to the Add Options");
            System.out.println("(1) Add a Course");
            System.out.println("(2) Add an Element of a Competency");
            System.out.println("(3) Add a Competency");
            System.out.println("(4) Add a link between a course and elements");
            System.out.println("(5) Back");

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
                    addJoin();
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

    //This function adds an element of competency to the database.It sends the user's inputs to another method
    //which is defined in CourseListServices so that an object of the corresponding type can be created and added to the databse.
    private void addElementMenu() {
        //call a method in CourseListServices that adds an element
        String competency = System.console().readLine("Input competency Code to which belongs the elements you wish to add: ");
        String elementNumber = System.console().readLine("Please input your new element number: ");
        String elementName = System.console().readLine("Please input your new element name: ");
        String elementDescription = System.console().readLine("Please input your new element description: ");

        try{
        System.out.println(dbDriver.addElement(competency+elementNumber, elementNumber, elementName, elementDescription, competency));
        }
        catch(SQLException e){

        }
    }

    //The following method creates a menu for adding a competency to the database. It sends the user's inputs to another method
    //which is defined in CourseListServices so that an object of the corresponding type can be created and added to the databse.
    private void addCompetencyMenu() {
        String code = System.console().readLine("Input the Competentcy Code of the Competency you wish to add: ");
        String name = System.console().readLine("Input the name of the Competency you wish to add: ");
        char specification = (System.console().readLine("If the competency is Mandatory input '1' \n if competency is optional input '0': ")).charAt(0);
        String description =System.console().readLine("Input the competency description: ");
        try{
        System.out.println(dbDriver.addCompetency(code, name, specification, description));
        }
        catch(SQLException e){

        }
    }

    //The following mehtod creates a menu for adding a course to the database.It sends the user's inputs to another method
    //which is defined in CourseListServices so that an object of the corresponding type can be created and added to the databse.
    private void addCourseMenu(){
        //call a method in CourseListServices that adds a course
        String courseNumber = System.console().readLine("Please input your new course number: ");
        String courseName = System.console().readLine("Please input your new course name: ");
        String courseDescription = System.console().readLine("Please input your new course description: ");
        int classHours = getInt("Please input the amount of class hours per week this class consumes: ");
        int labHours = getInt("Please input the amount of lab hours per week this class consumes: ");
        int homeworkHours = getInt("Please input the amount of homework hours per week this class consumes: ");
        int TermSeason = getInt("Input the term of the course: ");
        String educationType = System.console().readLine("Input the course education type: ");
        String domain = System.console().readLine("Input the course's domain: ");
        try{
            System.out.println(dbDriver.addCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, TermSeason, educationType, domain));
        }
        catch(SQLException e){

        }
    }
    private void addJoin(){
        String course = System.console().readLine("Input a course Number: ");
        String competency = System.console().readLine("Input competency Code to which belongs the elements you wish to join: ");
        char continueConnection;
        do{
        String element = System.console().readLine("Input an element number: "  );
        double allocatedTime = getDouble("Input the amount of time this element is covered in this course: ");
        try{
            System.out.println(dbDriver.addElementCourseBridge(course, competency+element, allocatedTime));
        }
        catch(SQLException e){

        }
        continueConnection = getFirstChar(System.console().readLine("do you wish to continue connecting element and courses if so input 'y' if not input 'n': "));
        }while(continueConnection == 'Y');
    }
    /*
      * DELETE FUNCTIONS
      */
    // The following function creates a delete menu for the program. It is one of the sub-menus of the main method.
      private void deleteMenu() {
        boolean running = true;
        while(running){
            System.out.println("Welcome to the Delete Options");
            System.out.println("(1) Delete a Course");
            System.out.println("(2) Delete a Competency");
            System.out.println("(3) Delete an Element");
            System.out.println("(4) Delete a link between a course and one/many elements");
            System.out.println("(5) Back");

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
                    deleteElementCoursJoin();
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

    //This function asks the user to enter the course_id of the course they want to delete. It uses the response by sending it 
    //to another function.
    private void deleteCourse() {
        String courseToRemove = System.console().readLine("Please write the course id of the course you would like to remove: ");
        try{
            System.out.println(dbDriver.removeCourse(courseToRemove));
        }
        catch(SQLException e){

        }
    }
    //This function deletes a competency by getting from the user the competency id.
    private void deleteCompetency() {
        String compToRemove = System.console().readLine("Please write the competency id of the course you would like to remove: ");
        try{
            System.out.println(dbDriver.removeCompetency(compToRemove));
        }
        catch(SQLException e){

        }
    }

    //This function deletes an elemets by concatenating the two inputs of the user and giving the result to another function. 
    private void deleteElement() {
        String competency = System.console().readLine("Input competency Code to which belongs the elements you wish to remove: ");
        String elementNumber = System.console().readLine("Please input the number of the element you wish to remove: ");
        try{
            System.out.println(dbDriver.removeElement(competency+elementNumber));
        }
        catch(SQLException e){

        }
    }

    private void deleteElementCoursJoin() {
        String course = System.console().readLine("Input a course Number: ");
        String competency = System.console().readLine("Input competency Code to which belongs the elements you wish to disconnect from the course: ");
        char continueConnection;
        do{
        String element = System.console().readLine("Input an element you wish to disconnect from the course: ");//TODO finish sentence
        System.out.println(dbDriver.removeElementCourseBridge(course, competency+element));
        continueConnection = getFirstChar(System.console().readLine("do you wish to continue disconnecting element and courses if so input 'y' if not 'n': "));
        }while(continueConnection == 'Y');
    }
    //--------------Update menu---------------
    //This function creates a menu for updating. It is one of the sub-menus of the main menu.
    private void updateMenu() {
        boolean running = true;
        while(running){
            System.out.println("Welcome to the Update Options");
            System.out.println("(1) Update a Course");
            System.out.println("(2) Update a Competency");
            System.out.println("(3) Update an Element");
            System.out.println("(4) Update a link between a course and one/many elements");
            System.out.println("(5) Back");

            String input = inputRequest();

            switch(input){
                case "1":
                    UpdateCourse();
                    break;
                case "2":
                    UpdateCompetency();
                    break;
                case "3":
                    UpdateElement();
                    break;
                case "4":
                    UpdateElementCoursJoin();
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
        //This function updates an element by getting the modified values from the user and giving its values to another function.
        private void UpdateElement() {
            //call a method in CourseListServices that Update an element
            String competency = System.console().readLine("Input competency Code to which belongs the elements you wish to update: ");
            String elementNumber = System.console().readLine("Please input your new element number: ");
            String elementName = System.console().readLine("Please input your new element name: ");
            String elementDescription = System.console().readLine("Please input your new element description: ");
                System.out.println(dbDriver.updateElement(competency+elementNumber, elementNumber, elementName, elementDescription, competency));
        }
        //This function updates a competency by giving new values to another funcction.
        private void UpdateCompetency() {
            String code = System.console().readLine("Input the Competentcy Code of the Competency you wish to update: ");
            String name = System.console().readLine("Input the name of the Competentcy: ");
            char specification = (System.console().readLine("If the competency is Mandatory input '1' \n if competency is optional input '0': ")).charAt(0);
            String description =System.console().readLine("Input the competency description: ");
                dbDriver.updateCompetency(code, name, specification, description);
        }
    
        //This function updates a course by calling a function which takes as inputs the user values.
        private void UpdateCourse(){
            //call a method in CourseListServices that Update a course
            String courseNumber = System.console().readLine("Please input the course number of the course you would like to update: ");
            String courseName = System.console().readLine("Please input your updated course name: ");
            String courseDescription = System.console().readLine("Please input your updated course description: ");
            int classHours = getInt("Please input the updated amount of class hours per week this class consumes: ");
            int labHours = getInt("Please input the updated amount of lab hours per week this class consumes: ");
            int homeworkHours = getInt("Please input the updated amount of homework hours per week this class consumes: ");
            int TermSeason = getInt("Input the updated term of the course: ");
            String educationType = System.console().readLine("Input the updated course education type: ");
            String domain = System.console().readLine("Input the course's updated domain: ");
                System.out.println(dbDriver.updateCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, TermSeason, educationType, domain));
    }
        private void UpdateElementCoursJoin(){
            String course = System.console().readLine("Input a course Number: ");
            String competency = System.console().readLine("Input competency Code to which belongs the elements you wish to update: ");
            char continueConnection;
            do{
            String element = System.console().readLine("Input an element number: "  );
            double allocatedTime = getDouble("Input the new amount of time this element is covered in this course: ");
                System.out.println(dbDriver.updateElementCourseBridge(course, competency+element, allocatedTime));
            continueConnection = getFirstChar(System.console().readLine("do you wish to continue updating time allocation if so input 'y' if not input 'n': "));
            }while(continueConnection == 'Y');
        }
    /*
       * GENERAL FUNCTIONS
       */
    
       //This function gets provoked when the user enters an invalid input
    private void invalidInput() {
        System.out.println("Invalid Input. Please Try Again");
    }

    //Everytime the user has an option to choose, this function is provoked.
    private String inputRequest() {
        return System.console().readLine("Please select an option: ");
    }
    private double getDouble(String inputQuestion){
        double number= 0;
        System.out.println(inputQuestion);
            while(!scan.hasNextDouble())
            {
            System.out.println(" invalid input\n"+inputQuestion);
            scan.next();
            }
            number = scan.nextDouble();
        return number;
    }
    private int  getInt(String inputQuestion){
        int number= 0;
        System.out.println(inputQuestion);
            while(!scan.hasNextInt())
            {
            System.out.println(" invalid input\n"+inputQuestion);
            scan.next();
            }
            number = scan.nextInt();
        return number;
    }
    private char getFirstChar(String word){
        return Character.toUpperCase(word.charAt(0));
    }
}
