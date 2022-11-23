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
    //Adds a new course to the database.
    public void addCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours, int homeworkHours,int term,String educationType) throws SQLException{
        String season_id=null;
        String seasonName=null;
        String education_id=null;
        //checks the term to find the corresponding season
        if(term%2==1){
            season_id="1";
            seasonName="Fall";
        }
        else if(term%2==0){
            season_id="2";
            seasonName="Winter";
        }

        if(educationType.equals("general")){
            education_id="1";
        }
        else if(educationType.equals("concentration")){
            education_id="2";
        }
        Season season=new Season(season_id, seasonName);
        Education education_type=new Education(education_id, educationType);
        TermSeason termSeason=new TermSeason(term, season);
        DawsonCourse course=new DawsonCourse(courseNumber, courseName, courseDescription, classHours, labHours, homeworkHours, education_type, termSeason);
        course.addToDatabase(this.conn);
    }
    public void addCompetency(){

    }
    public void addElementCourseBridge(){

    }
    public void removeCourse(){

    }
    public void removeCompetency(){

    }
    public void removeElementCourseBridge(){

    }
    public void updateCourse(){

    }
    public void updateCompetency(){

    }
    public void updateElementCourseBridge(){

    }

    public void displayCourses(){
        DawsonCourse coursesView = new DawsonCourse();
        coursesView.displayCourses(this.conn);
    }

    public void displayCompetencies(){
        Competencies competenciesView = new Competencies();
        competenciesView.displayCompetencies(this.conn);
    }
}
