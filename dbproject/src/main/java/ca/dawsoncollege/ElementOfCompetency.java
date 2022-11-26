package ca.dawsoncollege;

import java.sql.*;
public class ElementOfCompetency implements SQLData{
    private String elementId;
    private String elementNumber;
    private String elementName;
    private String elementDescription;
    private String competencies;
    public static String TYPENAME="ELEMENT_TYP";
    public ElementOfCompetency(String elementId, String elementNumber, String elementName, String elementDescription, String competencies) {
        this.elementId = elementId;
        this.elementNumber = elementNumber;
        this.elementName = elementName;
        this.elementDescription = elementDescription;
        this.competencies = competencies;
    }
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPENAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setElementId(stream.readString());
        setElementNumber(stream.readString());
        setElementName(stream.readString());
        setElementDescription(stream.readString());
        setCompentcies(stream.readString());
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(elementId);
        stream.writeString(elementNumber);
        stream.writeString(elementName);
        stream.writeString(elementDescription);
        stream.writeString(competencies);
    }

    public String addToDatabase(Connection conn){
        System.out.println(elementId);
        try(CallableStatement stmt = conn.prepareCall("{ call COMPETENCIES_PACKAGE.add_element_of_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
            return "SUCCESSFUL";
        } catch (SQLException e) {
            e.printStackTrace();
            return "failure";
            //TODO handle exception
        }
    }
    public String updateFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call COMPETENCIES_PACKAGE.update_element_of_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
            return "SUCCESSFUL";
        } catch (Exception e) {
            return "failure";
            //TODO handle exception
        }
    }
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }
    public void setElementDescription(String elementDescription) {
        this.elementDescription = elementDescription;
    }
    public void setCompentcies(String compentcie) {
        this.competencies = compentcie;
    }
    public void setElementNumber(String elementNumber) {
        this.elementNumber = elementNumber;
    }

}
