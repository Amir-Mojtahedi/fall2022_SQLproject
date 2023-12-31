package ca.dawsoncollege;
import java.sql.*;

public class DawsonCourse implements SQLData{
    //All the fields
    private String courseNumber;
    private String courseName;
    private String courseDescription;
    private int classHours;
    private int labHours;
    private int homeworkHours;
    private Education education_type;
    private TermSeason termID;
    private String domain;
    public static String TYPENAME="COURSE_TYP";

    /**
     * @param courseNumber
     * @param courseName
     * @param courseDescription
     * @param classHours
     * @param labHours
     * @param homeworkHours
     * @param education_type
     * @param termID
     * @param domain
     */
    //Constructor
    public DawsonCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours,
            int homeworkHours, Education education_type, TermSeason termID,String domain) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.classHours = classHours;
        this.labHours = labHours;
        this.homeworkHours = homeworkHours;
        this.education_type = education_type;
        this.termID = termID;
        this.domain = domain;
    }
    public DawsonCourse(){
        
    }
    //Getters
    public String getCourseNumber() {
        return courseNumber;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public int getClassHours() {
        return classHours;
    }
    public int getLabHours() {
        return labHours;
    }
    public int getHomeworkHours() {
        return homeworkHours;
    }
    public Education getEducation_type() {
        return education_type;
    }
    public TermSeason getTermID() {
        return termID;
    }
    private String getDomain() {
        return this.domain;
    }
    //------------------------------------------------------------------

    //Setters
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    public void setClassHours(int classHours) {
        this.classHours = classHours;
    }
    public void setLabHours(int labHours) {
        this.labHours = labHours;
    }
    public void setHomeworkHours(int homeworkHours) {
        this.homeworkHours = homeworkHours;
    }
    public void setEducation_type(Education education_type) {
        this.education_type = education_type;
    }
    public void setTermID(TermSeason termID) {
        this.termID = termID;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }

    //The following method add the current object to the database using a procedure.
    public String addToDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call COURSES_PACKAGE.add_course(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
            return "SUCCESSFUL";
        } catch (Exception e) {
            return "failure";
        }
    }

    //The following function updates an existing row(s) in the database using the following procedure
    public String updateFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call COURSES_PACKAGE.update_course(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
            return "SUCCESSFUL";
        } catch (Exception e) {
            return "failure";
            //TODO handle exception
        }
    }

    //This function creates a courseView object using an existing view in the database.
    public void displayCourses(Connection conn) throws SQLException{
        try(PreparedStatement stmt = conn.prepareStatement("select * from course_list_view ORDER BY \"Semester\" ASC")){
            ResultSet results = stmt.executeQuery();
            CoursesView course = null;
            while(results.next()){
                course = new CoursesView(
                results.getString("course_number"),
                results.getString("course_name"),
                results.getString("course_description"),
                results.getInt("class_hours"),
                results.getInt("lab_hours"),
                results.getInt("homework_hours"),
                results.getInt("total hours"),
                results.getDouble("credits"),
                results.getInt("semester"),
                results.getString("season_name"),
                results.getString("education_type"),
                results.getString("domain_name"),
                results.getString("Domain description"));

                System.out.println(course);
            }
        }
    }
    public void displayCourses(Connection conn, String filter) throws SQLException{
        try(PreparedStatement stmt = conn.prepareStatement("select * from course_list_view WHERE course_number = ?")){
            stmt.setString(1, filter);
            ResultSet results = stmt.executeQuery();
            CoursesView course = null;
            while(results.next()){
                course = new CoursesView(
                results.getString("course_number"),
                results.getString("course_name"),
                results.getString("course_description"),
                results.getInt("class_hours"),
                results.getInt("lab_hours"),
                results.getInt("homework_hours"),
                results.getInt("total hours"),
                results.getDouble("credits"),
                results.getInt("semester"),
                results.getString("season_name"),
                results.getString("education_type"),
                results.getString("domain_name"),
                results.getString("Domain description"));

                System.out.println(course);
            }
        }
    }
    //toString() method
    @Override
    public String toString() {
        return "DawsonCourse [courseNumber=" + courseNumber + ", courseName=" + courseName + ", courseDescription="
                + courseDescription + ", classHours=" + classHours + ", labHours=" + labHours + ", homeworkHours="
                + homeworkHours + ", education_type=" + education_type + ", termID=" + termID + ", DomainID=" + domain+ "]";
    }
    //Implemented methods which are used to setup the object properly for it to being sent to database.
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPENAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setCourseNumber(stream.readString());
        setCourseName(stream.readString());
        setCourseDescription(stream.readString());
        setClassHours(stream.readInt());
        setLabHours(stream.readInt());
        setHomeworkHours(stream.readInt());
        setEducation_type((Education)stream.readObject());
        setTermID((TermSeason)stream.readObject());
        setDomain(stream.readString());
        
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getCourseNumber());
        stream.writeString(getCourseName());
        stream.writeString(getCourseDescription());
        stream.writeInt(getClassHours());
        stream.writeInt(getLabHours());
        stream.writeInt(getHomeworkHours());
        stream.writeObject(getEducation_type());
        stream.writeObject(getTermID());
        stream.writeString(getDomain());
        
    }
    
}