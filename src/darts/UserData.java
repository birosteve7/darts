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
    private SimpleStringProperty email;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty birthDate;
    private SimpleStringProperty country;
    private SimpleStringProperty city;

    
    
    public UserData() {
        this.userName = new SimpleStringProperty("");
        this.userPassword = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.birthDate = new SimpleStringProperty("");
        this.country = new SimpleStringProperty("");
        this.city = new SimpleStringProperty("");
    }
    
    public UserData(String userName, String password, String email, String firstName, String lastName, String date, String country, String city) {
        this.userName = new SimpleStringProperty(userName);
        this.userPassword = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthDate = new SimpleStringProperty(date);
        this.city = new SimpleStringProperty(city);
        this.country = new SimpleStringProperty(country);
    }
    
    public String getUserPassword() {
        return userPassword.get();
    }

    public void setUserPassword(String password) {
        this.userPassword.set(password);
    }
    
    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
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
    
    public String getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(String date) {
        this.birthDate.set(date);
    }
    
    public String getCity() {
        return city.get();
    }

    public void setCity(String name) {
        this.city.set(name);
    }
    
    public String getCountry() {
        return country.get();
    }

    public void setCountry(String date) {
        this.country.set(date);
    }
}
