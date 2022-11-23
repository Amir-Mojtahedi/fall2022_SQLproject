package ca.dawsoncollege;

import java.sql.*;
public class ElementOfCompetency implements SQLData{
    private String elementId;
    private String elementName;
    private String elementDescription;
    private String competencies;
    public static String TYPE_NAME="ELEMENT_TYP";
    public ElementOfCompetency(String elementId, String elementName, String elementDescription, String competencies) {
        this.elementId = elementId;
    }
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPE_NAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setElementId(stream.readString());
        setElementName(stream.readString());
        setElementDescription(stream.readString());
        setCompentcies(stream.readString());
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(elementId);
        stream.writeString(elementName);
        stream.writeString(elementDescription);
        stream.writeString(competencies);
    }

    public void addToDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call add_element_of_competency(?)}")) {
            stmt.execute();
        } catch (Exception e) {
            //TODO handle exception
        }
    }
    public void removeFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call remove_element(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (Exception e) {
            //TODO handle exception
        }
    }
    public void updateFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call update_element_of_competncy(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (Exception e) {
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

}
