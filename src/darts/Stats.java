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
    private SimpleStringProperty Hi;
    private SimpleStringProperty HCo;
    private SimpleStringProperty chekoutTry;
    private SimpleStringProperty playedGamesNumber;

    public Stats() {
        this.userName = new SimpleStringProperty("");
        this.Hi = new SimpleStringProperty("");
        this.HCo = new SimpleStringProperty("");
        this.chekoutTry = new SimpleStringProperty("");
        this.playedGamesNumber = new SimpleStringProperty("");
    }
    
    public Stats(String userName,int Hi, int HCo, int chekoutTry, int playedGamesNumber) {
        this.userName = new SimpleStringProperty(userName);
        this.Hi = new SimpleStringProperty(String.valueOf(Hi));
        this.HCo = new SimpleStringProperty(String.valueOf(HCo));
        this.chekoutTry = new SimpleStringProperty(String.valueOf(chekoutTry));
        this.playedGamesNumber =  new SimpleStringProperty(String.valueOf(playedGamesNumber));
    }
    
    public Stats(int Hi, int HCo, int chekoutTry, int playedGamesNumber) {
        this.Hi = new SimpleStringProperty(String.valueOf(Hi));
        this.HCo = new SimpleStringProperty(String.valueOf(HCo));
        this.chekoutTry = new SimpleStringProperty(String.valueOf(chekoutTry));
        this.playedGamesNumber =  new SimpleStringProperty(String.valueOf(playedGamesNumber));
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
    
  
    
    
}
