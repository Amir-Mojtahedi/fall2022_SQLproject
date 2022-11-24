package ca.dawsoncollege;
import java.sql.*;
import java.util.Map;

public class CourseListServices{
    private String username;
    private String password;
    private Connection conn;
    public CourseListServices(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;
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
            map.put(DawsonCourse.TYPENAME,
            Class.forName("ca.dawsoncollege.DawsonCourse"));
            map.put(Competencies.TYPENAME, 
            Class.forName("ca.dawsoncollege.Competencies"));
            map.put(ElementOfCompetency.TYPENAME, 
            Class.forName("ca.dawsoncollege.ElementOfCompetency"));
            conn.setTypeMap(map);
        }
        catch(SQLException e){
            System.out.println("Invalid username or password");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
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
    public String addCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType, String domain){
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason, domain);
        return course.addToDatabase(this.conn);
    }
    public String addCompetency(String compId,String compName,char specification,String compDescription){
        Competencies competency = new Competencies(compId, compName, specification, compDescription);
        return competency.addToDatabase(conn);    
    }
    public String addElementCourseBridge(String courseID, String elementId, double allocatedTime){
        try(CallableStatement stmt = conn.prepareCall("{ call  CC_BRIDGE_PACKAGE.add_join(?,?,?)}")) {
            stmt.setObject(1, courseID);
            stmt.setObject(2, elementId);
            stmt.setObject(3, allocatedTime);
            stmt.execute();
            return "success";
        } catch (Exception e) {
          return "failed";
            //TODO handle exception
        }
    }
    public String addElement(String Id, String  name, String  Description, String  Competency){
        ElementOfCompetency element = new ElementOfCompetency(Id, name, Description, Competency);
        return element.addToDatabase(conn);
    }
    //-------------delete rows--------------
    public String removeCourse(String courseNumber){
        return removeRowFromDatabase("{ call COURSES_PACKAGE.delete_course(?)}",courseNumber);
    }
    public String removeCompetency(String id){
        return removeRowFromDatabase("{ call COMPETENCIES_PACKAGE.remove_competency(?)}", id);

    }
    public String removeElement(String Id){
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
    public String removeRowFromDatabase(String query, String value){//query not altered by user
        try(CallableStatement stmt = conn.prepareCall(query)) {
            stmt.setObject(1,  value);
            stmt.execute();
            return "succesful";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    //-----------updates-----------------
    public String updateCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType,String domain){
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason,domain);
        return course.updateFromDatabase(conn);
    }

    public void updateCompetency(String id, String name, char specification, String description){
        Competencies competency = new Competencies(id, name, specification, description);
        competency.updateFromDatabase(conn);
    }

    public String updateElement(String Id, String  name, String  Description, String  Competency){
        ElementOfCompetency element = new ElementOfCompetency(Id, name, Description, Competency);
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
    public void displayCourses(){
        DawsonCourse coursesView = new DawsonCourse();
        coursesView.displayCourses(this.conn);
    }

    public void displayCompetencies(){
        Competencies competenciesView = new Competencies();
        competenciesView.displayCompetencies(this.conn);
    }

    public void displayFull(){
        
    }
}


