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
public class DBAvg {
    final String URL = "jdbc:derby:AvarageDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    
    //Létrehozzuk a kapcsolatot (hidat)
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    
    public DBAvg(String name) {
        //Megpróbáljuk életre kelteni
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("A AVG DB híd létrejött");
        } catch (SQLException ex) {
            System.out.println("Valami baj van a connection (AVGBD híd) létrehozásakor.");
            System.out.println(""+ex);
        }
        
        //Ha életre kelt, csinálunk egy megpakolható teherautót
        if (conn != null){
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Valami baj van van a createStatament (AVGDB teherautó) létrehozásakor.");
                System.out.println(""+ex);
            }
        }
        
        //Megnézzük, hogy üres-e az adatbázis? Megnézzük, létezik-e az adott adattábla.
        try {           
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a DatabaseMetaData (AVGDB adatbázis leírása) létrehozásakor..");
            System.out.println(""+ex);
        }
        
        try {
            ResultSet rs = dbmd.getTables(null, "APP",name, null);
            if(!rs.next())
            { 
                String sql = "create table "+name+"( avarage float)";
                System.out.println("sql: "+sql);
                createStatement.execute(sql);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az DBAVG adattáblák létrehozásakor.");
            System.out.println(""+ex);
        }       
    }
    public Double getAvarageSum(String name){
        String sql = "select * from "+name+"";
        System.out.println("sql: "+sql);
        double sum = 0;
        ArrayList<Double> avgs = new ArrayList<>();
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while(rs.next()){
                System.out.println("Avg getAvarageSum: "+rs.getDouble("avarage"));
                avgs.add(rs.getDouble("avarage"));
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van  a DBAvg kiolvasáskor");
            System.out.println(""+ex);
        }
        for (int i=0; i<avgs.size(); i++){
            sum += avgs.get(i);
        }
       return sum; 
    }
    public void addAvg(Double avg, String name){
        try {
            String sql = "insert into "+name+" (avarage) values (?)";
            System.out.println("sql"+sql);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDouble(1, avg);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van DBAVG stat átadáskor");
            System.out.println(""+ex);
        }
    }
     
        public void clearAvg(String name){
        try {
            String sql = "delete from "+name+"";
            System.out.println("sql"+sql);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van DBAVG  törléskor");
            System.out.println(""+ex);
        }
    }

}

