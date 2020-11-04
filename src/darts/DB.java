package darts;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author István
 */
public class DB {
    final String URL = "jdbc:derby:dartStatDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    
    //Létrehozzuk a kapcsolatot (hidat)
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    
    public DB() {
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            System.err.println("Create Connection error");
            System.err.println(""+ex);
        }
        
        if (conn != null){
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.err.println("CreateStatament error");
                System.err.println(""+ex);
            }
        }
        
        try {           
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.err.println("DatabaseMetaData creation error");
            System.err.println(""+ex);
        }
        
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "DARTS", null);
            if(!rs.next())
            { 
                createStatement.execute("create table darts( username varchar(10), highestcheckout int, checkouttry int, playedgames int, useddartsnumber int, below20 int, above20 int, above40 int, above60 int, above80 int, above100 int, above120 int, above140 int, above160 int, p180 int)");
            }
        } catch (SQLException ex) {
            System.err.println("Database table creation error");
            System.err.println(""+ex);
        }       
    }
    
    public ArrayList<Stats> getAllStats(){
        String sql = "select * from darts";
        ArrayList<Stats> stats = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            stats = new ArrayList<Stats>();
            while(rs.next()){
                Stats actualStat = new Stats( rs.getString("username"), rs.getInt("highestcheckout"), rs.getInt("checkouttry"), 
                                              rs.getInt("playedgames"), rs.getInt("useddartsnumber"), rs.getInt("below20"),
                                              rs.getInt("above20"),     rs.getInt("above40"),         rs.getInt("above60"), 
                                              rs.getInt("above80"),     rs.getInt("above100"),        rs.getInt("above120"), 
                                              rs.getInt("above140"),    rs.getInt("above160"),        rs.getInt("p180"));
                stats.add(actualStat);
            }
        } catch (SQLException ex) {
            System.err.println("Database read error(getAllStats)");
            System.err.println(""+ex);
        }
       return stats; 
    }
    
    public Stats getOwnStats(String userName){
        String sql = "select * from darts where username='"+userName+"'";
        Stats stat = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            if (rs.next()) {
                stat = new Stats( rs.getString("username"), rs.getInt("highestcheckout"), rs.getInt("checkouttry"), 
                                  rs.getInt("playedgames"), rs.getInt("useddartsnumber"), rs.getInt("below20"), 
                                  rs.getInt("above20"),     rs.getInt("above40"),         rs.getInt("above60"),
                                  rs.getInt("above80"),     rs.getInt("above100"),        rs.getInt("above120"),
                                  rs.getInt("above140"),    rs.getInt("above160"),        rs.getInt("p180"));
            }
        } catch (SQLException ex) {
            System.err.println("Database read error(getOwnStat)");
            System.err.println(""+ex);
        }
        return stat; 
    }
    
    public ArrayList<String> getAllUserName(){
        String sql = "select username from darts";
        ArrayList<String> userNames = new ArrayList<>();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while(rs.next()){
                userNames.add( rs.getString("username"));
            }
        } catch (SQLException ex) {
            System.err.println("Database read error(GetAllUserName)");
            System.err.println(""+ex);
        }
        return userNames; 
    }
    

    public void addStat(String username){
        try {
            String sql = "insert into darts (username, highestcheckout, checkouttry, playedgames, useddartsnumber, below20, above20, above40, above60, above80, above100, above120, above140, above160, p180) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setInt(8, 0);
            preparedStatement.setInt(9, 0);
            preparedStatement.setInt(10, 0);
            preparedStatement.setInt(11, 0);
            preparedStatement.setInt(12, 0);
            preparedStatement.setInt(13, 0);
            preparedStatement.setInt(14, 0);
            preparedStatement.setInt(15, 0);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println("Stat wirte error(addStat)");
            System.err.println(""+ex);
        }
    }
    
        public void clearStat(String userName){
        try {
            String sql = "update darts set highestcheckout=?, checkouttry=?, playedgames=?, useddartsnumber=?, below20=?, above20=?, above40=?, above60=?, above80=?, above100=?, above120=?, above140=?, above160=?, p180=? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setInt(8, 0);
            preparedStatement.setInt(9, 0);
            preparedStatement.setInt(10, 0);
            preparedStatement.setInt(11, 0);
            preparedStatement.setInt(12, 0);
            preparedStatement.setInt(13, 0);
            preparedStatement.setInt(14, 0);
            preparedStatement.setString(15, userName);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println("Stat deletion error(clearStat)");
            System.err.println(""+ex);
        }
    }
    
    public void updateStat(Stats newStats){
        Integer Hi=0;
        Integer HCo=0;
        System.out.println("Old Username: "+newStats.getUserName());
        Stats oldStats = getOwnStats(newStats.getUserName());
        System.out.println("New Co"+newStats.getUserName());
        
        if (Integer.valueOf(newStats.getHCo()) > Integer.valueOf(oldStats.getHCo()))
            HCo = Integer.valueOf(newStats.getHCo());
        else
            HCo = Integer.valueOf(oldStats.getHCo());
        int checkoutTry = Integer.valueOf(oldStats.getChekoutTry())+Integer.valueOf(newStats.getChekoutTry());
        int checkoutPercentage = Integer.valueOf(oldStats.getPlayedGames())+Integer.valueOf(newStats.getPlayedGames());
        int usedDartsNumber = Integer.valueOf(oldStats.getUsedDartsNumber())+Integer.valueOf(newStats.getUsedDartsNumber());
        int below20  = Integer.valueOf(oldStats.getBelow20())+Integer.valueOf(newStats.getBelow20());
        int above20  = Integer.valueOf(oldStats.getAbove20())+Integer.valueOf(newStats.getAbove20());
        int above40  = Integer.valueOf(oldStats.getAbove40())+Integer.valueOf(newStats.getAbove40());
        int above60  = Integer.valueOf(oldStats.getAbove60())+Integer.valueOf(newStats.getAbove60());
        int above80  = Integer.valueOf(oldStats.getAbove80())+Integer.valueOf(newStats.getAbove80());
        int above100 = Integer.valueOf(oldStats.getAbove100())+Integer.valueOf(newStats.getAbove100());
        int above120 = Integer.valueOf(oldStats.getAbove120())+Integer.valueOf(newStats.getAbove120());
        int above140 = Integer.valueOf(oldStats.getAbove140())+Integer.valueOf(newStats.getAbove140());
        int above160 = Integer.valueOf(oldStats.getAbove160())+Integer.valueOf(newStats.getAbove160());
        int p180     = Integer.valueOf(oldStats.getP180())+Integer.valueOf(newStats.getP180());
        try {
            String sql = "update darts set highestcheckout=?, checkouttry=?, playedgames=?, useddartsnumber=?, below20=?, above20=?, above40=?, above60=?, above80=?, above100=?, above120=?, above140=?, above160=?, p180=? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, HCo);
            preparedStatement.setInt(2, checkoutTry);
            preparedStatement.setInt(3, checkoutPercentage);
            preparedStatement.setInt(4, usedDartsNumber);
            preparedStatement.setInt(5, below20);
            preparedStatement.setInt(6, above20);
            preparedStatement.setInt(7, above40);
            preparedStatement.setInt(8, above60);
            preparedStatement.setInt(9, above80);
            preparedStatement.setInt(10, above100);
            preparedStatement.setInt(11, above120);
            preparedStatement.setInt(12, above140);      
            preparedStatement.setInt(13, above160);
            preparedStatement.setInt(14, p180);
            preparedStatement.setString(15, oldStats.getUserName());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println("Stat addition error to Database(updateStat)");
            System.err.println(""+ex);
        }
    }
   
}
