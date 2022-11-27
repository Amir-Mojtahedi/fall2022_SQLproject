package ca.dawsoncollege;

import java.sql.*;

//import org.omg.PortableInterceptor.SUCCESSFUL;

public class Competency implements SQLData{
    private String compId;
    private String compName;
    private char specification;
    private String compDescription;
    public static String TYPENAME="COMP_TYP";
    
    /*
     * Constructors
     */
    public Competency(String compId, String compName, char specification, String compDescription) {
        this.compId = compId;
        this.compName = compName;
        this.specification = specification;
        this.compDescription = compDescription;
    }
    public Competency() {
    }
    
    /**
     * @param conn connection to the database
     * @return String message about completion of task
     * @throws SQLException
     */
    public String addToDatabase(Connection conn) throws SQLException{
        try(CallableStatement stmt = conn.prepareCall("{ call COMPETENCIES_PACKAGE.add_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
             return "";
        } catch (Exception e) {
            return "fail";
        }
    }
    /**
     * @param conn connextion to the database
     * @return String message about completion of task
     */
    public String updateFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call COMPETENCIES_PACKAGE.update_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
            return "SUCCESSFUL";
        } catch (SQLException e) {
            return "failure";
        }
    }
    /**
     * @param conn Connection to the database
     * @throws SQLException
     */
    public void displayCompetencies(Connection conn) throws SQLException{
        CompetenciesView competencies = null; 
        try(PreparedStatement stmt = conn.prepareStatement("select * from competencies_view")) {
            ResultSet results = stmt.executeQuery();

            while(results.next()){
                competencies = new CompetenciesView(
                    results.getString("code"),
                    results.getString("\"Statement of the Competency\""),
                    results.getString("\"Achievement Context\""),
                    results.getString("\"specification\""),
                    results.getString("\"element number\""),
                    results.getString("\"Elements of the Competency\""),
                    results.getString("\"Performance Criteria\"")
                    );

                    System.out.println(competencies);
            }
        }
    }

    public void displayCompetencies(Connection conn, String filter) throws SQLException{
        CompetenciesView competencies = null; 
        try(PreparedStatement stmt = conn.prepareStatement("select * from competencies_view where comp_id = ?")) {
            stmt.setString(1, filter);
            ResultSet results = stmt.executeQuery();

            while(results.next()){
                competencies = new CompetenciesView(
                    results.getString("comp_id"),
                    results.getString("\"Statement of the Competency\""),
                    results.getString("\"Achievement Context\""),
                    results.getString("\"specification\""),
                    results.getString("\"element number\""),
                    results.getString("\"Elements of the Competency\""),
                    results.getString("\"Performance Criteria\"")
                    );

                    System.out.println(competencies);
            }
        }
    }
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPENAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setCompId(stream.readString());
        setCompName(stream.readString());
        setSpecification((stream.readString()).charAt(0));
        setCompDescription(stream.readString());
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(compId);
        stream.writeString(compName);
        stream.writeString(Character.toString(specification));
        stream.writeString(compDescription);
    }

    /*
     * Setters for reading from sql type
     */
    public void setCompId(String compId) {
        this.compId = compId;
    }
    public void setCompName(String compName) {
        this.compName = compName;
    }
    public void setSpecification(char specification) {
        this.specification = specification;
    }
    public void setCompDescription(String compDescription) {
        this.compDescription = compDescription;
    }
}
