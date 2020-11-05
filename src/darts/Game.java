/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author István
 */
public class Game {
    
    private ArrayList<Integer> scoreList = new ArrayList<Integer>();
    private int remainingScore = 501;
    private int latestScore = 0;
    private int chekoutTry = 0;
    private int highestCo = 0;
    private int usedDartsNumber = 0;
    private int playedGamesNumber = 0;
    private HashMap<String, Integer> rangeMap = new HashMap<String, Integer>();
    private int legPos = 0;
    private double doublePer = 0.0;
    private double actualAvg = 0.0;
    private boolean isSecondUndo = false;
    private boolean possibleCheckout = false;
    private boolean coHappened = false;
    public ArrayList<Integer> getScoreList() {
        return scoreList;
    }
    
    public void addScorelist(int score){
        scoreList.add(score);
    }
    
    public Game() {
        initHashMap();
    }

    public void setScoreList(ArrayList<Integer> scoreList) {
        this.scoreList = scoreList;
    }
    
    public void setRemainingScore(int remainingScore) {
        this.remainingScore = remainingScore;
    }
    
    public void setLatestScore(int score){
        this.latestScore = score;
    }
    
    public int getLatestScore() {
        return latestScore;
    }
    
    public int getUsedDartsNumber() {
        return usedDartsNumber;
    }

    public void setUsedDartsNumber(int usedDartsNumber) {
        this.usedDartsNumber = usedDartsNumber;
    }
    
    public int getPlayedGamesNumber() {
        return playedGamesNumber;
    }

    public void setPlayedGamesNumber(int playedGamesNumber) {
        this.playedGamesNumber = playedGamesNumber;
    }
    
    public int getBelow20() {
        return this.rangeMap.get("20-");
    }

    public void setBelow20() {
        this.rangeMap.put("20-", getBelow20()+1);
    }

    public int getAbove20() {
        return this.rangeMap.get("20+");
    }

    public void setAbove20() {
        this.rangeMap.put("20+", getAbove20()+1);
    }

    public int getAbove40() {
        return this.rangeMap.get("40+");
    }

    public void setAbove40() {
        this.rangeMap.put("40+", getAbove40()+1);
    }

    public int getAbove60() {
        return this.rangeMap.get("60+");
    }

    public void setAbove60() {
        this.rangeMap.put("60+", getAbove60()+1);
    }

    public int getAbove80() {
        return this.rangeMap.get("80+");
    }

    public void setAbove80() {
        this.rangeMap.put("80+", getAbove80()+1);
    }

    public int getAbove100() {
        return this.rangeMap.get("100+");
    }

    public void setAbove100() {
        this.rangeMap.put("100+", getAbove100()+1);
    }

    public int getAbove120() {
        return this.rangeMap.get("120+");
    }

    public void setAbove120() {
        this.rangeMap.put("120+", getAbove120()+1);
    }

    public int getAbove140() {
        return this.rangeMap.get("140+");
    }

    public void setAbove140() {
        this.rangeMap.put("140+", getAbove140()+1);
    }

    public int getAbove160() {
        return this.rangeMap.get("160+");
    }

    public void setAbove160() {
        this.rangeMap.put("160+", getAbove160()+1);
    }

    public int getP180() {
        return this.rangeMap.get("180");
    }

    public void setP180() {
        this.rangeMap.put("180", getP180()+1);
    }

    public int getHighestCo() {
        return highestCo;
    }

    public void setHighestCo(int highestCo) {
        this.highestCo = highestCo;
    }
    
    public int getChekoutTry() {
        return chekoutTry;
    }
    
    public String getRemainigScore() {
        return String.valueOf(this.remainingScore);
    }
    
    public int getScoreListSize(){
        return scoreList.size();
    }

    public String getActualAvg() {
        int actualLegSum = 0;
        
        for(int i=legPos; i<scoreList.size();i++){
            actualLegSum += scoreList.get(i);
        }
        actualAvg = actualLegSum/(double)(scoreList.size()-legPos);
        return String.valueOf(actualAvg);
    }
    
    public void setIsSecondUndo(boolean isSecondUndo) {
        this.isSecondUndo = isSecondUndo;
    }
    
    public void setChekoutTry(int chekoutTry) {
        this.chekoutTry = chekoutTry;
    }
    
    public boolean getCoHappened() {
        return coHappened;
    }
    
    public void setCoHappened(boolean coHappened) {
        this.coHappened = coHappened;
    }
    
    public boolean getPossibleCheckout() {
        return possibleCheckout;
    }
    
    public void initGame(){
        legPos = scoreList.size();
        coHappened = false;
        possibleCheckout = false;
        remainingScore = 501;
    }
    public void calculation(Integer actualScore){
        double avg = 0;
        
        if((remainingScore-actualScore) < 2 && 
            !((remainingScore-actualScore) == 0) ){
            actualScore = 0;
        }
        setRange(actualScore);
        scoreList.add(actualScore);
        latestScore=remainingScore;
        setRemainingScore(remainingScore-actualScore);
        if (!coHappened)
            usedDartsNumber = getUsedDartsNumber()+3;
        else 
            checkOutHappened();
        this.isSecondUndo = false;

    }
    
    public int giveCheckOutNumber(int actualScore){
        int currentRes = remainingScore - actualScore;
        int res = 0;
        if (currentRes <= 40 || currentRes == 50)
            possibleCheckout = true;
        if (currentRes == 0) { 
            coHappened = true;
        }
        if (remainingScore % 2 != 0 || 
            (remainingScore > 40 && remainingScore != 50)) {
            res = 1;
        }
        if (remainingScore > 100){
            res = 2;
        }
        return res;
    }
    
    public void initActualStat(String name){
        setPlayedGamesNumber(0);
        setUsedDartsNumber(0);
        setChekoutTry(0);
    }
    
    public void checkOutHappened(){
        int checkOutScore = scoreList.get(scoreList.size()-1);
        if ( checkOutScore > highestCo) {
            highestCo = checkOutScore;
        }
    }
    
    public String removeLastScore() {
        int size = getScoreListSize();
        if (isSecondUndo) {
            return "You can only use once the 'Undo' button";
        }
        if(size == 0) {
            return "There is nothing to delete";
        }
        int deletedScore = scoreList.get(size-1);
        setRemainingScore(remainingScore + deletedScore);
        scoreList.remove(size-1);
        isSecondUndo = true;
        setUsedDartsNumber( getUsedDartsNumber()-3);
        return "";
    }
    public String checkOutTable(){
        String checkOut= "";
        switch (remainingScore){
            case 170: checkOut ="T20   T20   Bull";break;
            case 167: checkOut ="T20   T19   Bull"; break;
            case 164: checkOut ="T20   T18   Bull";break;
            case 161: checkOut ="T20   T17   Bull";break;
            case 160: checkOut ="T20   T20   D20";break;
            case 158: checkOut ="T20   T20   D19";break;
            case 156: checkOut ="T20   T20   D18";break;
            case 155: checkOut ="T20   T15   Bull";break;
            case 154: checkOut ="T20   T18   D20";break;
            case 153: checkOut ="T20   T19   D18";break;
            case 152: checkOut ="T20   T20   D16";break;
            case 151: checkOut ="T20   T17   D20";break;
            case 150: checkOut ="T20   T18   D18";break;
            case 149: checkOut ="T20   T19   D16";break;
            case 148: checkOut ="T20   T16   D20";break;
            case 147: checkOut ="T20   T17   D18";break;
            case 146: checkOut ="T20   T18   D16";break;
            case 145: checkOut ="T20   T15   D20";break;
            case 144: checkOut ="T20   T20   D12";break;
            case 143: checkOut ="T20   T17   D16";break;
            case 142: checkOut ="T20   T14   D20";break;
            case 141: checkOut ="T20   T15   D18";break;
            case 140: checkOut ="T20   T20   D10";break;
            case 139: checkOut ="T20   T13   D20";break;
            case 138: checkOut ="T20   T14   D18";break;
            case 137: checkOut ="T17   T18   D16";break;
            case 136: checkOut ="T20   T20   D8";break;
            case 135: checkOut ="T20   T15   D15";break;
            case 134: checkOut ="T20   T14   D16";break;
            case 133: checkOut ="T20   T19   D8";break;
            case 132: checkOut ="T20   T20   D6";break;
            case 131: checkOut ="T20   T13   D16";break;
            case 130: checkOut ="T20   T18   D8";break;
            case 129: checkOut ="T19   T20   D6";break;
            case 128: checkOut ="T18   T14   D16";break;
            case 127: checkOut ="T19   T18   D8";break;
            case 126: checkOut ="T19   T19   D6";break;
            case 125: checkOut ="BULL  T20   D20";break;
            case 124: checkOut ="T20   T16   D16";break;
            case 123: checkOut ="T19   T16   D9";break;
            case 122: checkOut ="T18   T20   D4";break;
            case 121: checkOut ="T20   T15   D8";break;
            case 120: checkOut ="T20    20   D20";break;
            case 119: checkOut ="T20   19   D20";break;
            case 118: checkOut ="T20   18   D20";break;
            case 117: checkOut ="T20   17   D20";break;
            case 116: checkOut ="T20   16   D20";break;
            case 115: checkOut ="T20   15   D20";break;
            case 114: checkOut ="T20   14   D20";break;
            case 113: checkOut ="T20   13   D20";break;
            case 112: checkOut ="T20   20   D16";break;
            case 111: checkOut ="T20   19   D16";break;
            case 110: checkOut ="T20   18   D16";break;
            case 109: checkOut ="T20   17   D16";break;
            case 108: checkOut ="T20   16   D16";break;
            case 107: checkOut ="T19   18   D16";break;
            case 106: checkOut ="T20   14   D16";break;
            case 105: checkOut ="T20   13   D16";break;
            case 104: checkOut ="T18   18   D16";break;
            case 103: checkOut ="T20   11   D16";break;
            case 102: checkOut ="T20   10   D16";break;
            case 101: checkOut ="T17   18   D16";break;
            case 100: checkOut ="T20   D20";break;
            case  99: checkOut ="T19   10   D16";break;
            case  98: checkOut ="T20   D19";break;
            case  97: checkOut ="T19   D20";break;
            case  96: checkOut ="T20   D18";break;
            case  95: checkOut ="T19   D19";break;
            case  94: checkOut ="T18   D20";break;
            case  93: checkOut ="T19   D18";break;
            case  92: checkOut ="T20   D16";break;
            case  91: checkOut ="T17   D20";break;
            case  90: checkOut ="T18   D18";break;
            case  89: checkOut ="T19   D16";break;
            case  88: checkOut ="T16   D20";break;
            case  87: checkOut ="T17   D18";break;
            case  86: checkOut ="T18   D16";break;
            case  85: checkOut ="T15   D20";break;
            case  84: checkOut ="T20   D12";break;
            case  83: checkOut ="T17   D16";break;
            case  82: checkOut ="T14   D20";break;
            case  81: checkOut ="T15   D18";break;
            case  80: checkOut ="T16   D16";break;
            case  79: checkOut ="T13   D20";break;
            case  78: checkOut ="T14   D18";break;
            case  77: checkOut ="T19   D20";break;
            case  76: checkOut ="T20   D8";break;
            case  75: checkOut ="T15   D15";break;
            case  74: checkOut ="T14   D16";break;
            case  73: checkOut ="T19   D8";break;
            case  72: checkOut ="T20   D6";break;
            case  71: checkOut ="T13   D6";break;
            case  70: checkOut ="T18   D8";break;
            case  69: checkOut ="T19   D6";break;
            case  68: checkOut ="T16   D10";break;
            case  67: checkOut ="T17   D8";break;
            case  66: checkOut ="T10   D18";break;
            case  65: checkOut ="25   D20";break;
            case  64: checkOut ="T16   D8";break;
            case  63: checkOut ="T13   D12";break;
            case  62: checkOut ="T10   D16";break;
            case  61: checkOut ="T13   D6";break;
            case  60: checkOut ="20   D20";break;
            case  59: checkOut ="19   D20";break;
            case  58: checkOut ="18   D20";break;
            case  57: checkOut ="17   D20";break;
            case  56: checkOut ="16   D20";break;
            case  55: checkOut ="15   D20";break;
            case  54: checkOut ="14   D20";break;
            case  53: checkOut ="13   D20";break;
            case  52: checkOut ="20   D20";break;
            case  51: checkOut ="19   D16";break;
            case  50: checkOut ="BULL";break;
            case  49: checkOut ="17   D16";break;
            case  48: checkOut ="16   D16";break;
            case  47: checkOut ="15   D16";break;
            case  46: checkOut ="6 D20";break;
            case  45: checkOut ="5   D20";break;
            case  44: checkOut ="12   D16";break;
            case  43: checkOut ="11   D16";break;
            case  42: checkOut ="10   D16";break;
            case  41: checkOut ="9   D16";break;
            case  40: checkOut ="D20";break;
            case  39: checkOut ="7   D16";break;
            case  38: checkOut ="D19";break;
            case  37: checkOut ="5   D16";break;
            case  36: checkOut ="D18";break;
            case  35: checkOut ="3   D16";break;
            case  34: checkOut ="D17";break;
            case  33: checkOut ="1   D16";break;
            case  32: checkOut ="D16";break;
            case  31: checkOut ="15   D8";break;
            case  30: checkOut ="D15";break;
            case  29: checkOut ="13   D8";break;
            case  28: checkOut ="D14";break;
            case  27: checkOut ="11   D8";break;
            case  26: checkOut ="D13";break;
            case  25: checkOut ="9   D8";break;
            case  24: checkOut ="D12";break;
            case  23: checkOut ="7   D8";break;
            case  22: checkOut ="D11";break;
            case  21: checkOut ="5   D8";break;
            case  20: checkOut ="D10";break;
            case  19: checkOut ="3   D8";break;
            case  18: checkOut ="D9";break;
            case  17: checkOut ="9   D4";break;
            case  16: checkOut ="D8";break;
            case  15: checkOut ="7   D4";break;
            case  14: checkOut ="D7";break;
            case  13: checkOut ="5   D4";break;
            case  12: checkOut ="D6";break;
            case  11: checkOut ="3   D4";break;
            case  10: checkOut ="D5";break;
            case   9: checkOut ="1    D4";break;
            case   8: checkOut ="D4";break;
            case   7: checkOut ="3   D2";break;
            case   6: checkOut ="D3";break;
            case   5: checkOut ="1   D2";break;
            case   4: checkOut ="D2";break;
            case   3: checkOut ="1   D1";break;
            case   2: checkOut ="D2";break;
            default: checkOut ="";break;
        }
        return checkOut;
    }
    
    public void setRange(int score){
        if (score        <          20)  setBelow20();
        if (score < 40  && score >= 20)  setAbove20();
        if (score < 60  && score >= 40)  setAbove40();
        if (score < 80  && score >= 60)  setAbove60();
        if (score < 100 && score >= 80)  setAbove80();
        if (score < 120 && score >= 100) setAbove100();
        if (score < 140 && score >= 120) setAbove120();
        if (score < 160 && score >= 140) setAbove140();
        if (score < 180 && score >= 160) setAbove160();
        if (score       ==          180) setP180();
    }
    
    public boolean isValidCheckoutData(int checkout, int usedDarts) {
        int remainScore = latestScore;
        if ( (remainScore <= 40 && remainScore %2 == 0) || remainScore == 50 ) {
            if (usedDarts >= checkout)
                return true;
            else 
                return false;
        }        
        if (usedDarts > checkout)
            return true;
        
        return false;
    }
     
    public String getHighestScore(){
        return String.valueOf(Collections.max(scoreList));
    }
    
    public double getAvarage(){
        double avarage = 0;
        if (getUsedDartsNumber() != 0)
            avarage = (this.getPlayedGamesNumber()*501)/(double)(this.getUsedDartsNumber()/3.0);
       
        return avarage;
    }
    
    public double getchekoutPercentage(){
        int percent = 0;
        if (chekoutTry != 0)
            percent = getPlayedGamesNumber()*100/chekoutTry;
        
        return percent;    
    }
    
    private void initHashMap(){
        rangeMap.put("20-",  0);
        rangeMap.put("20+",  0);
        rangeMap.put("40+",  0);
        rangeMap.put("60+",  0);
        rangeMap.put("80+",  0);
        rangeMap.put("100+", 0);
        rangeMap.put("120+", 0);
        rangeMap.put("140+", 0);
        rangeMap.put("160+", 0);
        rangeMap.put("180",  0);
    }
}
