package ca.dawsoncollege;
import java.sql.*;
public class Domain implements SQLData {
    // Fields
    private String domainId;
    private String domainName;
    private String domainDescription;
    public static String TYPENAME="DOMAIN_TYP";
    
    //Constructor
    public Domain(String domainId, String domainName, String domainDescription) {
        this.domainId = domainId;
        this.domainName = domainName;
        this.domainDescription = domainDescription;
    }
    public Domain(){

    };
    //Getters
    public String getDomainId() {
        return domainId;
    }
    public String getDomainName() {
        return domainName;
    }
    public String getDomainDescription() {
        return domainDescription;
    }
    //Setters
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    public void setDomainDescription(String domainDescription) {
        this.domainDescription = domainDescription;
    }
    //toString() method
    @Override
    public String toString() {
        return "domain [domainId=" + domainId + ", domainName=" + domainName + ", domainDescription="
                + domainDescription + "]";
    }
    //Implemented methods which are used to setup the object properly for it to being sent to database.
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPENAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setDomainId(stream.readString());
        setDomainName(stream.readString());
        setDomainDescription(stream.readString());
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getDomainId());
        stream.writeString(getDomainName());
        stream.writeString(getDomainDescription());
        
    }
    
}
