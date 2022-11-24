package ca.dawsoncollege;

import java.sql.*;

//import org.omg.PortableInterceptor.SUCCESSFUL;

public class Competencies implements SQLData{
    private String compId;
    private String compName;
    private char specification;
    private String compDescription;
    public static String TYPENAME="COMP_TYP";
    
    public Competencies(String compId, String compName, char specification, String compDescription) {
        this.compId = compId;
        this.compName = compName;
        this.specification = specification;
        this.compDescription = compDescription;
    }
    public Competencies() {
    }
    
    public String addToDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call COMPETENCIES_PACKAGE.add_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
            return "SUCCESSFUL";
        } catch (Exception e) {
            return "failure";
            //TODO handle exception
        }
    }/*
    public void removeFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call remove_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (Exception e) {
        }
    }*/
    public String updateFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call COMPETENCIES_PACKAGE.update_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
            return "SUCCESSFUL";
        } catch (Exception e) {
            return "failure";
        }
    }
    public void displayCompetencies(Connection conn){
        CompetenciesView competencies = null; 
        try(PreparedStatement stmt = conn.prepareStatement("select * from competencies_view")) {
            ResultSet results = stmt.executeQuery();

            while(results.next()){
                competencies = new CompetenciesView(
                    results.getString("comp_id"),
                    results.getString("comp_name"),
                    results.getString("comp_description"),
                    results.getString("specification"),
                    results.getString("element_name"),
                    results.getString("element_description")
                    );

                    System.out.println(competencies);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
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
