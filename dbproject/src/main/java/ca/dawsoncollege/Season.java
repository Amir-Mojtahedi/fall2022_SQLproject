package ca.dawsoncollege;
import java.sql.*;

public class Season implements SQLData {
    private String seasonID;
    private String seasonName;
    public static String TYPE_NAME="SEASON_TYP";

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
    public void addToDatabase(Connection conn){
        String sql="{call dawson_classes.add_season(?)}";
        try(CallableStatement stmt=conn.prepareCall(sql)) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "Season [seasonID=" + seasonID + ", seasonName=" + seasonName + "]";
    }
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPE_NAME;
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
