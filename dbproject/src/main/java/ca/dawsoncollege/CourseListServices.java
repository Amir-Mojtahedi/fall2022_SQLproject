package ca.dawsoncollege;
import java.sql.*;
import java.util.Map;
//import java.util.Scanner;
public class CourseListServices{
    private String username;
    private String password;
    private Connection conn;
    public CourseListServices(String username, String password) throws SQLException {
        this.username = username;
        this.password = password;
        this.conn=DriverManager.getConnection("jdbc:oracle:thin:@198.168.52.211:1521/pdbora19c.dawsoncollege.qc.ca",this.username,this.password);
        try {
            Map map=conn.getTypeMap();
            conn.setTypeMap(map);
            map.put(Season.TYPE_NAME, Class.forName("ca.dawsoncollege.Season"));
            map.put(TermSeason.TYPE_NAME,Class.forName("ca.dawsoncollege.TermSeason"));
            map.put(Education.TYPE_NAME,Class.forName("ca.dawsoncollege.Education"));
            map.put(DawsonCourse.TYPE_NAME,Class.forName("ca.dawsoncollege.DawsonCourse"));
            map.put(Competencies.TYPE_NAME, Class.forName("ca.dawsoncollege.Competencies"));
            map.put(ElementOfCompetency.TYPE_NAME, Class.forName("ca.dawsoncollege.ElementOfCompetency"));
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
    public void addCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType){
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason);
        course.addToDatabase(this.conn);
    }
    public void addCompetency(String compId,String compName,char specification,String compDescription){
        Competencies competency = new Competencies(compId, compName, specification, compDescription);
        competency.addToDatabase(conn);    
    }
    public void addElementCourseBridge(String courseID, String elementId, double allocatedTime){
        try(CallableStatement stmt = conn.prepareCall("{ call add_join(?,?,?)}")) {
            stmt.setObject(1, courseID);
            stmt.setObject(2, elementId);
            stmt.setObject(3, allocatedTime);
            stmt.execute();
        } catch (Exception e) {
            //TODO handle exception
        }
    }
    public void addElement(String Id, String  name, String  Description, Competencies  Competency){
        ElementOfCompetency element = new ElementOfCompetency(Id, name, Description, Competency);
        element.addToDatabase(conn);
    }
    //-------------delete rows--------------
    public void removeCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType){
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason);
        course.removeFromDatabase(conn);
    }
    public void removeCompetency(){

    }
    public void removeElementCourseBridge(String courseID, String elementId){
        try(CallableStatement stmt = conn.prepareCall("{ call remove_join(?,?)}")) {
            stmt.setObject(1, courseID);
            stmt.setObject(2, elementId);
            stmt.execute();
        } catch (Exception e) {
            //TODO handle exception
        }
    }
    public void removeElement(String Id, String  name, String  Description, Competencies  Competency){
        ElementOfCompetency element = new ElementOfCompetency(Id, name, Description, Competency);
        element.removeFromDatabase(conn);
    }
    //-----------updates-----------------
    public void updateCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType){
        Season season=getSeason(term);
        Education education_type=getEducation(educationType);
        TermSeason termSeason=new TermSeason(term, season);
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason);
        course.updateFromDatabase(conn);
    }
    public void updateCompetency(){

    }
    public void updateElementCourseBridge(String courseID, String elementId, double allocatedTime){
        try(CallableStatement stmt = conn.prepareCall("{ call update_allocated_time(?,?,?)}")) {
            stmt.setObject(1, courseID);
            stmt.setObject(2, elementId);
            stmt.setObject(3, allocatedTime);
            stmt.execute();
        } catch (Exception e) {
            //TODO handle exception
        }
    }
    public void updateElement(String Id, String  name, String  Description, Competencies  Competency){
        ElementOfCompetency element = new ElementOfCompetency(Id, name, Description, Competency);
        element.updateFromDatabase(conn);
    }

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


