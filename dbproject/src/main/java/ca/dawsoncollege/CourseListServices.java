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
    public void close() throws SQLException{
        if(this.conn!=null || !this.conn.isClosed()){
            this.conn.close();
        }
    }
    //get assign season and term id from term
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
    //Adds a new course to the database.
    public String addCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType, String domainId) throws SQLException{
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason, domainId);
        return course.addToDatabase(this.conn);
    }
    public String addCompetency(String compId,String compName,char specification,String compDescription) throws SQLException{
        Competency competency = new Competency(compId, compName, specification, compDescription);
        return competency.addToDatabase(conn);    
    }
    public String addElementCourseBridge(String courseID, String elementId, double allocatedTime) throws SQLException{
        try(CallableStatement stmt = conn.prepareCall("{ call  CC_BRIDGE_PACKAGE.add_join(?,?,?)}")) {
            stmt.setObject(1, elementId);
            stmt.setObject(2, courseID);
            stmt.setObject(3, allocatedTime);
            stmt.execute();
            return "success";
        } catch (Exception e) {
          return "failed";
        }
    }
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
    public String removeCourse(String courseNumber) throws SQLException{
        return removeRowFromDatabase("{ call COURSES_PACKAGE.delete_course(?)}",courseNumber);
    }
    public String removeCompetency(String id) throws SQLException{
        return removeRowFromDatabase("{ call COMPETENCIES_PACKAGE.remove_competency(?)}", id);
    }
    public String removeElement(String Id) throws SQLException{
        return removeRowFromDatabase("{ call COMPETENCIES_PACKAGE.remove_element(?)}",Id);
    }
    public String removeElementCourseBridge(String courseID, String elementId){
        try(CallableStatement stmt = conn.prepareCall("{ call CC_BRIDGE_PACKAGE.remove_join(?,?)}")) {
            stmt.setObject(1, courseID);
            stmt.setObject(2, elementId);
            stmt.execute();
            return "success";
        } catch (Exception e) {
          return "failed";
            //TODO handle exception
        }
    }
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
    public String updateCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType,String domainId){
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
       
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason,domainId);
        return course.updateFromDatabase(conn);
    }

    public void updateCompetency(String id, String name, char specification, String description){
        Competency competency = new Competency(id, name, specification, description);
        competency.updateFromDatabase(conn);
    }

    public String updateElement(String Id, String number, String  name, String  Description, String  Competency){
        ElementOfCompetency element = new ElementOfCompetency(Id, number, name, Description, Competency);
        return element.updateFromDatabase(conn);
    }

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
    public void displayCourses() throws SQLException{
        DawsonCourse coursesView = new DawsonCourse();
        coursesView.displayCourses(this.conn);
    }

    public void displayCompetencies() throws SQLException{
        Competency competenciesView = new Competency();
        competenciesView.displayCompetencies(this.conn);
    }

    public void displayFull() throws SQLException{
        try(PreparedStatement stmt = this.conn.prepareStatement("select * from grid_view ORDER BY term_id")){
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
                    results.getString("Competency Code"),
                    results.getString("Statement of the Competency"),
                    results.getString("specification")
                ); 
                System.out.println(display);
            }
        }   
    }

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
    public void displayTimeValidity()throws SQLException{
        CallableStatement stmt = conn.prepareCall("{? = call CC_BRIDGE_PACKAGE.timeValidation()}");
        stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
        stmt.executeQuery();
            System.out.println("here are the "+stmt.getString(1));
        if(!stmt.isClosed()){
            stmt.close();
        }
    }
}


