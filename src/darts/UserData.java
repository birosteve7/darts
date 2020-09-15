/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darts;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author István
 */
public class UserData {
    private SimpleStringProperty userName;
    private SimpleStringProperty userPassword;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty birthDate;
    private SimpleStringProperty city;
    private SimpleStringProperty country;
    
    
    public UserData() {
        this.userName = new SimpleStringProperty("");
        this.userPassword = new SimpleStringProperty("");
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.birthDate = new SimpleStringProperty("");
        this.city = new SimpleStringProperty("");
        this.country = new SimpleStringProperty("");
    }
    
    public UserData(String userName, String password, String firstName, String lastName, String date, String city, String country) {
        this.userName = new SimpleStringProperty(userName);
        this.userPassword = new SimpleStringProperty(password);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthDate = new SimpleStringProperty(date);
        this.city = new SimpleStringProperty(city);
        this.country = new SimpleStringProperty(country);
    }
    
    public String getUserPassword() {
        return userPassword.get();
    }

    public void setUserPassword(String userName) {
        this.userPassword.set(userName);
    }
    
    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String name) {
        this.firstName.set(name);
    }
    
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String name) {
        this.lastName.set(name);
    }
    
}
