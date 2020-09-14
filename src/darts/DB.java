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
            System.out.println("Valami baj van a connection (híd) létrehozásakor.");
            System.out.println(""+ex);
        }
        
        if (conn != null){
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Valami baj van van a createStatament (teherautó) létrehozásakor.");
                System.out.println(""+ex);
            }
        }
        
        try {           
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a DatabaseMetaData (adatbázis leírása) létrehozásakor..");
            System.out.println(""+ex);
        }
        
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "DARTS", null);
            if(!rs.next())
            { 
                createStatement.execute("create table darts( username varchar(10), userpassword varchar(12), highestcheckout int, checkouttry int, playedgames int, useddartsnumber int)");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az adattáblák létrehozásakor.");
            System.out.println(""+ex);
        }       
    }
    
    public ArrayList<Stats> getAllStats(){
        String sql = "select * from darts";
        ArrayList<Stats> stats = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            stats = new ArrayList<Stats>();
            while(rs.next()){
                Stats actualStat = new Stats( rs.getString("username"), rs.getString("userpassword"), rs.getInt("highestcheckout"), 
                                              rs.getInt("checkouttry"), rs.getInt("playedgames"), rs.getInt("useddartsnumber"));
                stats.add(actualStat);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a kiolvasáskor");
            System.out.println(""+ex);
        }
       return stats; 
    }
    
    public Stats getOwnStats(String userName){
        String sql = "select * from darts where username='"+userName+"'";
        Stats stat = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            if (rs.next()) {
                stat = new Stats( rs.getString("username"), rs.getString("userpassword"), rs.getInt("highestcheckout"), 
                                  rs.getInt("checkouttry"), rs.getInt("playedgames"), rs.getInt("useddartsnumber"));
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a kiolvasáskor");
            System.out.println(""+ex);
        }
        return stat; 
    }
    
    public String getUserPassword(String userName){
        String sql = "select userpassword from darts where username='"+userName+"'";
        String password = "";
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            if (rs.next()) {
                password = rs.getString("userpassword");
            }   
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a kiolvasáskor");
            System.out.println(""+ex);
        }
       return password; 
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
            System.out.println("Valami baj van  a kiolvasáskor");
            System.out.println(""+ex);
        }
        return userNames; 
    }
    

    public void addStat(String username, String password){
        try {
            String sql = "insert into darts ( username, userpassword, highestcheckout,checkouttry,playedgames,useddartsnumber) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a stat átadáskor");
            System.out.println(""+ex);
        }
    }
    
        public void clearStat(String userName){
        try {
            String sql = "update darts set highestcheckout = ?, checkouttry = ?, playedgames = ?, useddartsnumber = ? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setString(5, userName);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van statok törlésekor átadáskor");
            System.out.println(""+ex);
        }
    }
    
    public void updateStat(Stats newStats){
        Integer Hi=0;
        Integer HCo=0;
        Stats oldStats = getOwnStats(newStats.getUserName());
    
        if (Integer.valueOf(newStats.getHCo()) > Integer.valueOf(oldStats.getHCo()))
            HCo = Integer.valueOf(newStats.getHCo());
        else
            HCo = Integer.valueOf(oldStats.getHCo());
        int checkoutTry = Integer.valueOf(oldStats.getChekoutTry())+Integer.valueOf(newStats.getChekoutTry());
        int checkoutPercentage = Integer.valueOf(oldStats.getPlayedGames())+Integer.valueOf(newStats.getPlayedGames());
        int usedDartsNumber = Integer.valueOf(oldStats.getUsedDartsNumber())+Integer.valueOf(newStats.getUsedDartsNumber());
        try {
            String sql = "update darts set highestcheckout = ?, checkouttry = ?, playedgames = ?, useddartsnumber = ? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, HCo);
            preparedStatement.setInt(2, checkoutTry);
            preparedStatement.setInt(3, checkoutPercentage);
            preparedStatement.setInt(4, usedDartsNumber);
            preparedStatement.setString(5, oldStats.getUserName());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a stat hozzáadásakor");
            System.out.println(""+ex);
        }
    }
    public void saveNewPassword(String userName, String password){
        try {
            String sql = "update darts set userpassword = ? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, userName);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a stat hozzáadásakor");
            System.out.println(""+ex);
        }
    }
   
}
