package ca.dawsoncollege;

import java.sql.*;
public class ElementOfCompetency implements SQLData{
    private String elementId;
    private String elementName;
    private String elementDescription;
    private Competencie compentcie;
    public static String TYPE_NAME="ELEMENT_TYP";
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPE_NAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setElementId(stream.readString());
        setElementName(stream.readString());
        setElementDescription(stream.readString());
        setCompentcie((Competencie)stream.readObject());
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(elementId);
        stream.writeString(elementName);
        stream.writeString(elementDescription);
        stream.writeObject(compentcie);
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
    public void setCompentcie(Competencie compentcie) {
        this.compentcie = compentcie;
    }
    public static void setTYPE_NAME(String tYPE_NAME) {
        TYPE_NAME = tYPE_NAME;
    }
}
