package ca.dawsoncollege;
import java.sql.*;
import java.util.Map;

public class CourseListServices{
    private Connection conn;
    public CourseListServices(String username, String password) throws SQLException {
        this.conn=DriverManager.getConnection("jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",username,password);
        Map map;
        try {
            map=conn.getTypeMap();
            map.put(Season.TYPENAME, 
            Class.forName("ca.dawsoncollege.Season"));
            map.put(TermSeason.TYPENAME,
            Class.forName("ca.dawsoncollege.TermSeason"));
            map.put(Education.TYPENAME,
            Class.forName("ca.dawsoncollege.Education"));
            map.put(Domain.TYPENAME,
            Class.forName("ca.dawsoncollege.Domain"));
            map.put(DawsonCourse.TYPENAME,
            Class.forName("ca.dawsoncollege.DawsonCourse"));
            map.put(Competency.TYPENAME, 
            Class.forName("ca.dawsoncollege.Competency"));
            map.put(ElementOfCompetency.TYPENAME, 
            Class.forName("ca.dawsoncollege.ElementOfCompetency"));
            conn.setTypeMap(map);
        }
        catch (ClassNotFoundException e) {
            
        }
    }
    /**
     * @throws SQLException
     */
    public void close() throws SQLException{
        if(this.conn!=null || !this.conn.isClosed()){
            this.conn.close();
        }
    }
    //get assign season and term id from term
    /**
     * @param educationType number that represents if its general education or concentration
     * @return if education type is 1 return general, if education type is 2 return concentration
     */
    private Education getEducation(String educationType){
        String education_id=null;
        if(educationType.equals("general")){
            education_id="1";
        }
        else if(educationType.equals("concentration")){
            education_id="2";
        }
        return new Education(education_id, educationType);
    }
    /**
     * @param term term number from 1 to 6
     * @return if even return Winter, if odd return Fall
     */
    private Season getSeason(int term){
        //checks the term to find the corresponding season
        String season_id=null;
        String seasonName=null;
        if(term%2==1){
            season_id="1";
            seasonName="Fall";
        }
        else if(term%2==0){
            season_id="2";
            seasonName="Winter";
        }
        return new Season(season_id, seasonName);
    }
    
    //-----------------ADD rows to databse------------------
    
    /**
     * @param courseNumber the course identifier
     * @param courseName name of course
     * @param courseDescription description for the course
     * @param classHours amount of hours in class per week
     * @param labHours amount of hours in lab per week
     * @param homeworkHours amount of hours that should be dedicated for homework per week
     * @param term term number
     * @param educationType type of class
     * @param domainId domain the class is in
     * @return messsage about completion
     * @throws SQLException
     */
    public String addCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType, String domainId) throws SQLException{
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason, domainId);
        return course.addToDatabase(this.conn);
    }
    /**
     * @param compId competency identifier
     * @param compName name of competency
     * @param specification mandatory or not
     * @param compDescription description for competency
     * @return message about completion
     * @throws SQLException
     */
    public String addCompetency(String compId,String compName,char specification,String compDescription) throws SQLException{
        Competency competency = new Competency(compId, compName, specification, compDescription);
        return competency.addToDatabase(conn);    
    }
    public String addElementCourseBridge(String courseID, String elementId, double allocatedTime) throws SQLException{
        try(CallableStatement stmt = conn.prepareCall("{ call  CC_BRIDGE_PACKAGE.add_join(?,?,?)}")) {
            stmt.setObject(1, courseID);
            stmt.setObject(2, elementId);
            stmt.setObject(3, allocatedTime);
            stmt.execute();
            return "success";
        } catch (Exception e) {
          return "failed";
        }
    }
    /**
     * @param Id id for element
     * @param number number code for element
     * @param name name of element
     * @param Description description of element
     * @param Competency competency that element is associated to
     * @return message about completion
     * @throws SQLException
     */
    public String addElement(String Id, String number, String  name, String  Description, String  Competency) throws SQLException{
        ElementOfCompetency element = new ElementOfCompetency(Id, number, name, Description, Competency);
        try{
            return element.addToDatabase(conn);
        }
        catch(NumberFormatException e){
            return "failure";
        }
    }
    //-------------delete rows--------------
    /**
     * @param courseNumber course number to remove
     * @return message about completion
     * @throws SQLException
     */
    public String removeCourse(String courseNumber) throws SQLException{
        return removeRowFromDatabase("{ call COURSES_PACKAGE.delete_course(?)}",courseNumber);
    }
    /**
     * @param id competency to remove   
     * @return message about completion
     * @throws SQLException
     */
    public String removeCompetency(String id) throws SQLException{
        return removeRowFromDatabase("{ call COMPETENCIES_PACKAGE.remove_competency(?)}", id);
    }
    /**
     * @param Id element to remove
     * @return message about completion
     * @throws SQLException
     */
    public String removeElement(String Id) throws SQLException{
        return removeRowFromDatabase("{ call COMPETENCIES_PACKAGE.remove_element(?)}",Id);
    }
    /**
     * @param courseID course id to check for
     * @param elementId elemnt id to check for
     * @return message about completion
     */
    public String removeElementCourseBridge(String courseID, String elementId){
        try(CallableStatement stmt = conn.prepareCall("{ call CC_BRIDGE_PACKAGE.remove_join(?,?)}")) {
            stmt.setObject(1, courseID);
            stmt.setObject(2, elementId);
            stmt.execute();
            return "success";
        } catch (Exception e) {
          return "failed";
        }
    }
    /**
     * @param query query that decides from which table to remove
     * @param value thing to remove
     * @return message about completion
     * @throws SQLException
     */
    public String removeRowFromDatabase(String query, String value) throws SQLException{//query not altered by user
        try(CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setObject(1,  value);
            stmt.execute();
            return "succesful";
        } catch (Exception e) {
            return "fail";
        }
    }
    //-----------updates-----------------
    /**
     * @param courseNumber the course identifier
     * @param courseName name of course
     * @param courseDescription description for the course
     * @param classHours amount of hours in class per week
     * @param labHours amount of hours in lab per week
     * @param homeworkHours amount of hours that should be dedicated for homework per week
     * @param term term number
     * @param educationType type of class
     * @param domainId domain the class is in
     * @return messsage about completion
     * @throws SQLException
     */
    public String updateCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType,String domainId){
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
       
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason,domainId);
        return course.updateFromDatabase(conn);
    }

    /**
     * @param id id for competency
     * @param name name of the competency
     * @param specification specification of competency
     * @param description description of competency
     * @return message about completion
     */
    public String updateCompetency(String id, String name, char specification, String description){
        Competency competency = new Competency(id, name, specification, description);
        return competency.updateFromDatabase(conn);
    }

    /**
     * @param Id identifier for element to update
     * @param number updated number of element
     * @param name updated name of element
     * @param description updated description
     * @param Competency updated competency to be connected to
     * @return
     */
    public String updateElement(String Id, String number, String  name, String description, String  Competency){
        ElementOfCompetency element = new ElementOfCompetency(Id, number, name, description, Competency);
        return element.updateFromDatabase(conn);
    }

    /**
     * @param courseID change the courseid connection
     * @param elementId change the element connection
     * @param allocatedTime change the allocated time
     * @return message about completion
     */
    public String updateElementCourseBridge(String courseID, String elementId, double allocatedTime){
        try(CallableStatement stmt = conn.prepareCall("{ call  CC_BRIDGE_PACKAGE.update_allocated_time(?,?,?)}")) {
            stmt.setObject(1, courseID);
            stmt.setObject(2, elementId);
            stmt.setObject(3, allocatedTime);
            stmt.execute();
            return "SUCCESSFUL";
        } catch (Exception e) {
            return "failure";
            //TODO handle exception
        }
    }
//-------------display----------
    /**
     * Displays all courses in the database in a user friendly way
     * @throws SQLException
     */
    public void displayCourses() throws SQLException{
        DawsonCourse coursesView = new DawsonCourse();
        coursesView.displayCourses(this.conn);
    }

    public void DisplayCourses(String filter) throws SQLException{
        DawsonCourse coursesView = new DawsonCourse();
        coursesView.displayCourses(this.conn, filter);
    }

    /**
     * Displays all competencies and relational info in a user friendly way
     * @throws SQLException
     */
    public void displayCompetencies() throws SQLException{
        Competency competenciesView = new Competency();
        competenciesView.displayCompetencies(this.conn);
    }

    public void displayCompetencies(String filter) throws SQLException{
        Competency competenciesView = new Competency();
        competenciesView.displayCompetencies(this.conn, filter);
    }

    /**
     * Displays all connections from courses to competencies in user friendly way
     * @throws SQLException
     */
    public void displayFull(String query) throws SQLException{
        try(PreparedStatement stmt = this.conn.prepareStatement(query)){
            ResultSet results = stmt.executeQuery();

            while(results.next()){
                FullDisplayView display = new FullDisplayView(
                    results.getString("course_number"),
                    results.getString("course_name"),
                    results.getInt("class_hours"),
                    results.getInt("lab_hours"),
                    results.getInt("homework_hours"),
                    results.getInt("total hours"),
                    results.getInt("term_id"),
                    results.getDouble("credits"),
                    results.getString("comp_id"),
                    results.getString("Statement of the Competency"),
                    results.getString("specification")
                ); 
                System.out.println(display);
            }
        }   
    }

    /**
     * Displays the user logs table
     * @throws SQLException
     */
    public void displayLogs() throws SQLException{
        try(PreparedStatement stmt = this.conn.prepareStatement("select * from user_logs_view")){
            ResultSet results = stmt.executeQuery();

            while(results.next()){
                LogsView logs = new LogsView(
                    results.getString("username"),
                    results.getDate("date_time"),
                    results.getString("message"));

                    System.out.println(logs);
            }
        }
    }
}


