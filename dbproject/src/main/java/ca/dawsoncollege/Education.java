package ca.dawsoncollege.assignment8;
import java.sql.*;

public class Education implements SQLData{
    private String education_type_id;
    private String educationType;
    public static String TYPE_NAME="EDUCATION_TYPE";
    public Education(String education_type_id, String educationType) {
        this.education_type_id = education_type_id;
        this.educationType = educationType;
    }
    public Education(){

    }
    public String getEducation_type_id() {
        return education_type_id;
    }

    public String getEducationType() {
        return educationType;
    }
    public void setEducation_type_id(String education_type_id) {
        this.education_type_id = education_type_id;
    }
    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }
    public void addToDatabase(Connection conn){
        String sql="{call dawson_classes.add_education(?)}";
        try(CallableStatement stmt=conn.prepareCall(sql)) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Education [education_type_id=" + education_type_id + ", educationType=" + educationType + "]";
    }
    @Override
    public String getSQLTypeName() throws SQLException {
        
        return TYPE_NAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setEducation_type_id(stream.readString());
        setEducationType(stream.readString());
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getEducation_type_id());
        stream.writeString(getEducationType());
        
    }
    
    
}
