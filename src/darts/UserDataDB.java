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
            System.err.println("Create connection error.");
            System.err.println(""+ex);
        }
        
        if (conn != null){
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.err.println("createStatament error");
                System.err.println(""+ex);
            }
        }
        
        try {           
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.err.println("Create DatabaseMetaData error");
            System.err.println(""+ex);
        }
        
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "USERDATA", null);
            if(!rs.next())
            { 
                createStatement.execute("create table userdata( username varchar(10), userpassword varchar(12), email varchar(25), firstname varchar(10), lastname varchar(10), birthdate date, country varchar(25), city varchar(15))");
            }
        } catch (SQLException ex) {
            System.err.println("Create Database table error");
            System.err.println(""+ex);
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
            System.err.println("Database read error(getUserPassword)");
            System.err.println(""+ex);
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
            System.err.println("Database write error(saveNewpassword)");
            System.err.println(""+ex);
        }
    }
    
    public UserData getUserdata(String userName){
        String sql = "select * from userdata where username='"+userName+"'";
        UserData data = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            if (rs.next()) {
                data = new UserData( rs.getString("username"),  rs.getString("userpassword"), rs.getString("email"),
                                     rs.getString("firstname"), rs.getString("lastname"),     rs.getString("birthdate"), 
                                     rs.getString("country"),   rs.getString("city"));
            }
        } catch (SQLException ex) {
            System.err.println("Database read error(getuserData)");
            System.err.println(""+ex);
        }
        return data; 
    }

    public void addData(String userName, String userPassword, String email, String firstName, String lastName, String birthDate, String country, String city){
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
            System.err.println("Database write error(addData)");
            System.err.println(""+ex);
        }
    }
    
    public void updateData(String email, String country, String city, String userName){      
        try {
            String sql = "update userdata set email=?, country=?, city=? where username=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, userName);
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.err.println("Database write error(updateData)");
            System.err.println(""+ex);
        }
    }
   
}

