package ca.dawsoncollege;
import java.sql.*;

public class Season implements SQLData {
    //Fields
    private String seasonID;
    private String seasonName;
    public static String TYPENAME="SEASON_TYP";

    //Constructor
    public Season(String seasonID, String seasonName) {
        this.seasonID = seasonID;
        this.seasonName = seasonName;
    }
    public Season(){

    }
    //Getters
    public String getSeasonID() {
        return seasonID;
    }


    public String getSeasonName() {
        return seasonName;
    }
    //Setters
    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }
    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
    // toString() method
    @Override
    public String toString() {
        return "Season [seasonID=" + seasonID + ", seasonName=" + seasonName + "]";
    }
    //Implemented methods which are used to setup the object properly for it to being sent to database.
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPENAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setSeasonID(stream.readString());
        setSeasonName(stream.readString());
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(getSeasonID());
        stream.writeString(getSeasonName());
        
    }
    
}
