package ca.dawsoncollege;
import java.sql.*;
public class TermSeason implements SQLData{
    private int termID;
    private Season season;
    public static String TYPE_NAME="TERM_TYPE";
    public TermSeason(int termID, Season season) {
        this.termID = termID;
        this.season = season;
    }
    public TermSeason(){

    }
    public int getTermID() {
        return termID;
    }
    public Season getSeason() {
        return season;
    }
    public void setTermID(int termID) {
        this.termID = termID;
    }
    public void setSeason(Season season) {
        this.season = season;
    }
    public void addToDatabase(Connection conn){
        String sql="{call dawson_classes.add_term(?)}";
        try(CallableStatement stmt=conn.prepareCall(sql)) {
            stmt.setObject(1, this);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "TermSeason [termID=" + termID + ", season=" + season + "]";
    }
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPE_NAME;
    }
    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        setTermID(stream.readInt());
        setSeason((Season)stream.readObject());
    }
    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(getTermID());
        stream.writeObject(getSeason());
    }
    
  

}