package ca.dawsoncollege;

import java.sql.*;

public class Competencie implements SQLData{
    private String compId;
    private String compName;
    private char specification;
    private String compDescription;
    public static String TYPE_NAME="COMP_TYP";
    @Override
    public String getSQLTypeName() throws SQLException {
        return null;
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
        // TODO Auto-generated method stub
        
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
