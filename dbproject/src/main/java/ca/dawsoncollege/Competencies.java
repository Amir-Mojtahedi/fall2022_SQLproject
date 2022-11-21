package ca.dawsoncollege;

import java.sql.*;

public class Competencies implements SQLData{
    private String compId;
    private String compName;
    private char specification;
    private String compDescription;
    public static String TYPE_NAME="COMP_TYP";
    
    public void addToDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call add_competency(?)}")) {
            stmt.execute();
        } catch (Exception e) {
            //TODO handle exception
        }
    }
    public void removeFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call remove_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (Exception e) {
            //TODO handle exception
        }
    }
    public void updateFromDatabase(Connection conn){
        try(CallableStatement stmt = conn.prepareCall("{ call update_competency(?)}")) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (Exception e) {
            //TODO handle exception
        }
    }
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPE_NAME;
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
    public static void setTYPE_NAME(String tYPE_NAME) {
        TYPE_NAME = tYPE_NAME;
    }
}
