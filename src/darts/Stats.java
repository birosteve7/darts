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
    private int Hi;
    private int HCo;
    private int chekoutTry;
    private int playedGamesNumber;
    private int usedDartsNumber;
    private int below20;
    private int above20;
    private int above40;
    private int above60;
    private int above80;
    private int above100;
    private int above120;
    private int above140;
    private int above160;
    private int p180;

    public Stats() {
        this.userPassword = new SimpleStringProperty("");
        this.userName = new SimpleStringProperty("");
        this.Hi = 0;
        this.HCo = 0;
        this.chekoutTry = 0;
        this.playedGamesNumber = 0;
        this.usedDartsNumber = 0;
        this.below20 = 0;
        this.above20 = 0;
        this.above40 = 0;
        this.above60 = 0;
        this.above80 = 0;
        this.above100 = 0;
        this.above120 = 0;
        this.above140 = 0;
        this.above160 = 0;
        this.p180 = 0;
    }
    
    public Stats(String userName, String password, int HCo, int chekoutTry, int playedGamesNumber, int usedDartsNumber, int below20, int above20, int above40,
                 int above60, int above80, int above100, int above120, int above140, int above160, int p180) {
        this.userPassword = new SimpleStringProperty(password);
        this.userName = new SimpleStringProperty(userName);
        this.HCo = HCo;
        this.chekoutTry = chekoutTry;
        this.playedGamesNumber =  playedGamesNumber;
        this.usedDartsNumber = usedDartsNumber;
        this.below20 = below20;
        this.above20 = above20;
        this.above40 = above40;
        this.above60 = above60;
        this.above80 = above80;
        this.above100 = above100;
        this.above120 = above120;
        this.above140 = above140;
        this.above160 = above160;
        this.p180 = p180;
    }
    
    public Stats(int Hi, int HCo, int chekoutTry, int playedGamesNumber, int usedDartsNumber, int below20, int above20, int above40,
                 int above60, int above80, int above100, int above120, int above140, int above160, int p180) {
        this.Hi = Hi;
        this.HCo = HCo;
        this.chekoutTry = chekoutTry;
        this.playedGamesNumber =  playedGamesNumber;
        this.usedDartsNumber = usedDartsNumber;
        this.below20 = below20;
        this.above20 = above20;
        this.above40 = above40;
        this.above60 = above60;
        this.above80 = above80;
        this.above100 = above100;
        this.above120 = above120;
        this.above140 = above140;
        this.above160 = above160;
        this.p180 = p180;
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
    
    public int getHi() {
        return Hi;
    }

    public void setHi(int Hi) {
        this.Hi= Hi;
    }

    public int getHCo() {
        return HCo;
    }

    public void setHCo(int HCo) {
        this.HCo = HCo;
    }

    public int getChekoutTry() {
        return chekoutTry;
    }

    public void setChekoutTry(int chekoutTry) {
        this.chekoutTry = chekoutTry;
    }

    public int getPlayedGames() {
        return playedGamesNumber;
    }

    public void setPlayedGamesNumber(int playedGamesNumber) {
        this.playedGamesNumber = playedGamesNumber;
    }

    public int getUsedDartsNumber() {
        return usedDartsNumber;
    }

    public void setUsedDartsNumber(int usedDartsNumber) {
        this.usedDartsNumber = usedDartsNumber;
    }

    public int getBelow20() {
        return below20;
    }

    public void setBelow20() {
        this.below20 = getBelow20()+1;
    }

    public int getAbove20() {
        return above20;
    }

    public void setAbove20() {
        this.above20 = getAbove20()+1;
    }

    public int getAbove40() {
        return above40;
    }

    public void setAbove40() {
        this.above40 = getAbove40()+1;
    }

    public int getAbove60() {
        return above60;
    }

    public void setAbove60() {
        this.above60 = getAbove60()+1;
    }

    public int getAbove80() {
        return above80;
    }

    public void setAbove80() {
        this.above80 = getAbove80()+1;
    }

    public int getAbove100() {
        return above100;
    }

    public void setAbove100() {
        this.above100 = getAbove100()+1;
    }

    public int getAbove120() {
        return above120;
    }

    public void setAbove120() {
        this.above120 = getAbove120()+1;
    }

    public int getAbove140() {
        return above140;
    }

    public void setAbove140() {
        this.above140 = getAbove140()+1;
    }

    public int getAbove160() {
        return above160;
    }

    public void setAbove160() {
        this.above160 = getAbove160()+1;
    }

    public int getP180() {
        return p180;
    }

    public void setP180() {
        this.p180 = getP180()+1;
    }
    
    public double getchekoutPercentage(){
        int percent = 0;
        if (chekoutTry != 0)
            percent = getPlayedGames()*100/chekoutTry;
        
        return percent;    
    }
   
    public double getAvarage(){
        double avarage = 0;
        System.out.println("Played games: "+this.getPlayedGames());
        System.out.println("Dart: "+this.getUsedDartsNumber());
        if (getUsedDartsNumber() != 0)
            avarage = (this.getPlayedGames()*501)/(double)(this.getUsedDartsNumber()/3.0);
        
        return avarage;
    }
}
