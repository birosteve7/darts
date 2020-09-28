/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darts;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author István
 */
public class UserDataDB {
    final String URL = "jdbc:derby:UsersStatDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    
    //Létrehozzuk a kapcsolatot (hidat)
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    
    public UserDataDB() {
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
            ResultSet rs = dbmd.getTables(null, "APP", "Data", null);
            if(!rs.next())
            { 
                createStatement.execute("create table userdata( username varchar(10), userpassword varchar(12), email varchar(25), firstname varchar(10), lastname varchar(10), birthdate date, country varchar(25), city varchar(15))");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az adattáblák létrehozásakor.");
            System.out.println(""+ex);
        }       
    }
    public String getUserPassword(String userName){
        String sql = "select userpassword from userdata where username='"+userName+"'";
        String password = "";
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            if (rs.next()) {
                password = rs.getString("userpassword");
            }   
        } catch (SQLException ex) {
            System.out.println("Valami baj van  data a kiolvasáskor");
            System.out.println(""+ex);
        }
       return password; 
    }
    
    public void saveNewPassword(String userName, String password){
        try {
            String sql = "update userdata set userpassword = ? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, userName);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a data hozzáadásakor");
            System.out.println(""+ex);
        }
    }
    
    public UserData getUserdata(String userName){
        System.out.println("darts.UserDataDB.getUserdata()");
        String sql = "select * from userdata where username='"+userName+"'";
        System.out.println("sql: "+sql);
        UserData data = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            if (rs.next()) {
                data = new UserData( rs.getString("username"),  rs.getString("userpassword"), rs.getString("email"),
                                     rs.getString("firstname"), rs.getString("lastname"),     rs.getString("birthdate"), 
                                     rs.getString("country"),   rs.getString("city"));
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van  data a kiolvasáskor");
            System.out.println(""+ex);
        }
        return data; 
    }

    public void addData(String userName, String userPassword, String email, String firstName, String lastName, String birthDate, String country, String city){
        System.out.println("darts.UserDataDB.addData()");
        try {
        String sql = "insert into userdata (username, userpassword, email, firstname, lastname, birthdate, country, city) values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, userPassword);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, firstName);
        preparedStatement.setString(5, lastName);
        preparedStatement.setString(6, birthDate);
        preparedStatement.setString(7, country);
        preparedStatement.setString(8, city);
        preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a contact hozzáadásakor");
            System.out.println(""+ex);
        }
    }
    
    public void updateData(String email, String country, String city, String userName){
        System.out.println("darts.UserDataDB.updateData()");
        
        try {
            String sql = "update userdata set email=?, country=?, city=? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, userName);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a stat hozzáadásakor");
            System.out.println(""+ex);
        }
    }
   
}

