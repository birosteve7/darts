/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    final String URL = "jdbc:derby:sampleDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    
    //Létrehozzuk a kapcsolatot (hidat)
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    
    public DB() {
        //Megpróbáljuk életre kelteni
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("A híd létrejött");
        } catch (SQLException ex) {
            System.out.println("Valami baj van a connection (híd) létrehozásakor.");
            System.out.println(""+ex);
        }
        
        //Ha életre kelt, csinálunk egy megpakolható teherautót
        if (conn != null){
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Valami baj van van a createStatament (teherautó) létrehozásakor.");
                System.out.println(""+ex);
            }
        }
        
        //Megnézzük, hogy üres-e az adatbázis? Megnézzük, létezik-e az adott adattábla.
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
                createStatement.execute("create table darts( username varchar(10), avarage int, highestscore int, highestcheckout int, checkouttry int, playedgames int)");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az adattáblák létrehozásakor.");
            System.out.println(""+ex);
        }       
    }
    
    public ArrayList<Stats> getAllStats(){
        System.out.println("darts.DB.getAllStats()");
        String sql = "select * from darts";
        ArrayList<Stats> stats = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            stats = new ArrayList<Stats>();
            while(rs.next()){
                Stats actualStat = new Stats( rs.getString("username"), rs.getInt("highestscore"), rs.getInt("highestcheckout"), 
                                              rs.getInt("checkouttry"), rs.getInt("playedgames"));
                stats.add(actualStat);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a kiolvasáskor");
            System.out.println(""+ex);
        }

       return stats; 
    }
    
    public Stats getOwnStats(String userName){
        System.out.println("darts.DB.getAllStats()");
        String sql = "select * from darts";
        ArrayList<Stats> stats = null;
        int savedN = 0;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            stats = new ArrayList<Stats>();
            while(rs.next()){
                Stats actualStat = new Stats( rs.getString("username"), rs.getInt("highestscore"), rs.getInt("highestcheckout"), 
                                              rs.getInt("checkouttry"), rs.getInt("playedgames"));
                stats.add(actualStat);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a kiolvasáskor");
            System.out.println(""+ex);
        }
        for (int i=0; i<stats.size();i++){
           if (userName.equals(stats.get(i).getUserName()))
               savedN = i;
       }
       return stats.get(savedN); 
    }
    
    public ArrayList<String> getAllUserName(){
        String sql = "select username from darts";
        ArrayList<String> userNames = new ArrayList<>();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while(rs.next()){
                System.out.println("User: "+rs.getString("username"));
                userNames.add( rs.getString("username"));
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a kiolvasáskor");
            System.out.println(""+ex);
        }
        for (int i=0; i<userNames.size(); i++)
            System.out.println("Username("+i+")= "+userNames.get(i));
        return userNames; 
    }
    

    public void addStat(Stats stat){
        try {
            String sql = "insert into darts (username, highestscore, highestcheckout,checkouttry,playedgames) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, stat.getUserName());
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.setInt(5, 0);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a stat átadáskor");
            System.out.println(""+ex);
        }
    }
    
        public void clearStat(String userName){
        try {
            String sql = "update darts set highestscore = ?, highestcheckout = ?, checkouttry = ?, playedgames = ? where username='"+userName+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
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
        if (Integer.valueOf(newStats.getHi()) > Integer.valueOf(oldStats.getHi()))
            Hi = Integer.valueOf(newStats.getHi());
        else
            Hi = Integer.valueOf(oldStats.getHi());
    
        if (Integer.valueOf(newStats.getHCo()) > Integer.valueOf(oldStats.getHCo()))
            HCo = Integer.valueOf(newStats.getHCo());
        else
            HCo = Integer.valueOf(oldStats.getHCo());
        int checkoutTry = Integer.valueOf(oldStats.getChekoutTry())+Integer.valueOf(newStats.getChekoutTry());
        int checkoutPercentage = Integer.valueOf(oldStats.getPlayedGames())+Integer.valueOf(newStats.getPlayedGames());
        System.out.println("Try: "+checkoutTry);
        System.out.println("Perc: "+checkoutPercentage);
        try {
            String sql = "update darts set highestscore = ?, highestcheckout = ?, checkouttry = ?, playedgames = ? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, Hi);
            preparedStatement.setInt(2, HCo);
            preparedStatement.setInt(3, checkoutTry);
            preparedStatement.setInt(4, checkoutPercentage);
            preparedStatement.setString(5, oldStats.getUserName());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a stat hozzáadásakor");
            System.out.println(""+ex);
        }
    }
   
}
