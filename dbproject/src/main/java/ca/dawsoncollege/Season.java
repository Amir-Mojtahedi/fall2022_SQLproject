package ca.dawsoncollege;
import java.sql.*;

public class Season implements SQLData {
    private String seasonID;
    private String seasonName;
    public static String TYPENAME="SEASON_TYP";

    public Season(String seasonID, String seasonName) {
        this.seasonID = seasonID;
        this.seasonName = seasonName;
    }
    public Season(){

    }
    public String getSeasonID() {
        return seasonID;
    }


    public String getSeasonName() {
        return seasonName;
    }
    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }
    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
    
    @Override
    public String toString() {
        return "Season [seasonID=" + seasonID + ", seasonName=" + seasonName + "]";
    }
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
