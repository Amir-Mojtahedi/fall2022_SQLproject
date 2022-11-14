package ca.dawsoncollege;
import java.sql.*;

public class DawsonCourse implements SQLData{
    private String courseNumber;
    private String courseName;
    private String courseDescription;
    private int classHours;
    private int labHours;
    private int homeworkHours;
    private Education education_type;
    private TermSeason termID;
    public static String TYPE_NAME="COURSE_TYPE";
    public DawsonCourse(String courseNumber, String courseName, String courseDescription, int classHours, int labHours,
            int homeworkHours, Education education_type, TermSeason termID) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.classHours = classHours;
        this.labHours = labHours;
        this.homeworkHours = homeworkHours;
        this.education_type = education_type;
        this.termID = termID;
    }
    public DawsonCourse(){
        
    }
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
    public void addToDatabase(Connection conn){
        String sql="{call dawson_classes.add_course(?)}";
        try(CallableStatement stmt=conn.prepareCall(sql)) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "DawsonCourse [courseNumber=" + courseNumber + ", courseName=" + courseName + ", courseDescription="
                + courseDescription + ", classHours=" + classHours + ", labHours=" + labHours + ", homeworkHours="
                + homeworkHours + ", education_type=" + education_type + ", termID=" + termID + "]";
    }
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPE_NAME;
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
        
    }
    

}