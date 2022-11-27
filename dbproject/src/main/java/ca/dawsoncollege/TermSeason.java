package ca.dawsoncollege;
import java.sql.*;
public class TermSeason implements SQLData{
    // All the fields
    private int termID;
    private Season season;
    public static String TYPENAME="TERM_TYP";

    // Constructor
    public TermSeason(int termID, Season season) {
        this.termID = termID;
        this.season = season;
    }
    public TermSeason(){

    }

    // Getters
    public int getTermID() {
        return termID;
    }
    public Season getSeason() {
        return season;
    }
    // Setters
    public void setTermID(int termID) {
        this.termID = termID;
    }
    public void setSeason(Season season) {
        this.season = season;
    }
   
    // toString() method
    @Override
    public String toString() {
        return "TermSeason [termID=" + termID + ", season=" + season + "]";
    }
    //Implemented methods which are used to setup the object properly for it to being sent to database.
    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPENAME;
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