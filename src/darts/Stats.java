package darts;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author István
 */
public class Stats {
    private SimpleStringProperty userName;
    private SimpleStringProperty userPassword;
    private SimpleStringProperty Hi;
    private SimpleStringProperty HCo;
    private SimpleStringProperty chekoutTry;
    private SimpleStringProperty playedGamesNumber;
    private SimpleStringProperty usedDartsNumber;

    public Stats() {
        this.userPassword = new SimpleStringProperty("");
        this.userName = new SimpleStringProperty("");
        this.Hi = new SimpleStringProperty("");
        this.HCo = new SimpleStringProperty("");
        this.chekoutTry = new SimpleStringProperty("");
        this.playedGamesNumber = new SimpleStringProperty("");
        this.usedDartsNumber = new SimpleStringProperty("");
    }
    
    public Stats(String userName, String password, int Hi, int HCo, int chekoutTry, int playedGamesNumber, int usedDartsNumber) {
        this.userPassword = new SimpleStringProperty(password);
        this.userName = new SimpleStringProperty(userName);
        this.Hi = new SimpleStringProperty(String.valueOf(Hi));
        this.HCo = new SimpleStringProperty(String.valueOf(HCo));
        this.chekoutTry = new SimpleStringProperty(String.valueOf(chekoutTry));
        this.playedGamesNumber =  new SimpleStringProperty(String.valueOf(playedGamesNumber));
        this.usedDartsNumber = new SimpleStringProperty(String.valueOf(usedDartsNumber));
    }
    
    public Stats(int Hi, int HCo, int chekoutTry, int playedGamesNumber, int usedDartsNumber) {
        this.Hi = new SimpleStringProperty(String.valueOf(Hi));
        this.HCo = new SimpleStringProperty(String.valueOf(HCo));
        this.chekoutTry = new SimpleStringProperty(String.valueOf(chekoutTry));
        this.playedGamesNumber =  new SimpleStringProperty(String.valueOf(playedGamesNumber));
        this.usedDartsNumber = new SimpleStringProperty(String.valueOf(usedDartsNumber));
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
    
    public String getHi() {
        return Hi.get();
    }

    public void setHi(int Hi) {
        this.Hi.set(String.valueOf(Hi));
    }

    public String getHCo() {
        return HCo.get();
    }

    public void setHCo(int HCo) {
        this.HCo.set(String.valueOf(HCo));
    }

    public void setChekoutTry(int chekoutTry) {
        this.chekoutTry.set(String.valueOf(chekoutTry));
    }
    
    public String getChekoutTry() {
        return chekoutTry.get();
    }

    public void setPlayedGamesNumber(int playedGamesNumber) {
        this.playedGamesNumber.set(String.valueOf(playedGamesNumber));
    }
    
    public String getPlayedGames() {
        return playedGamesNumber.get();
    }
    
    public double getchekoutPercentage(){
        return Integer.valueOf(playedGamesNumber.get())*100/Integer.valueOf(chekoutTry.get());
    }
    
    public void setUsedDartsNumber(int usedDarts) {
        this.usedDartsNumber.set(String.valueOf(usedDarts));
    }
    public String getUsedDartsNumber() {
        return usedDartsNumber.get();
    }
 
    public double getAvarage(){
        System.out.println("Played games: "+this.getPlayedGames());
        System.out.println("Dart: "+this.getUsedDartsNumber());
        return (Integer.valueOf(this.getPlayedGames())*501)/(double)(Integer.valueOf(this.getUsedDartsNumber())/3.0);
    }
}
