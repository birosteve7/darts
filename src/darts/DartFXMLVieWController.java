package darts;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javax.swing.ButtonGroup;

/**
 *
 * @author István
 */
public class DartFXMLVieWController implements Initializable {
  
    
    @FXML
    private Pane userPane;
    @FXML
    private Pane menuPane;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane statPane;
    @FXML
    private Pane scorePane;
    @FXML
    private Pane checkoutPane;
    @FXML
    private Pane errorPane;
    @FXML
    private Pane allPlayerStatPane;
    @FXML
    private Label errorText;
    @FXML
    private Button errorOK;
    @FXML
    private TextField inputScore;
    @FXML
    private Button BtnStat;
    @FXML
    private Button BtnGame;
    @FXML
    private Button BtnUndo;
    @FXML
    private Button BtnSave;
    @FXML
    private Button BtnClear;
    @FXML
    private Button inputOne;
    @FXML
    private Button inputTwo;
    @FXML
    private Button inputThree;
    @FXML
    private Button inputFour;
    @FXML
    private Button inputFive;
    @FXML
    private Button inputSix;
    @FXML
    private Button inputSeven;
    @FXML
    private Button inputEight;
    @FXML
    private Button inputNine;
    @FXML
    private Button inputZero;
    @FXML
    private Button gameSave;
    @FXML
    private Label outputRem;
    @FXML
    private Label outputHi;
    @FXML
    private Label ouputLegAvg;
    @FXML
    private Label outputHCo;
    @FXML
    private Label labelCo;
    @FXML
    private Label gameAvg;
    @FXML
    private Label outputDouble;
    @FXML
    private RadioButton radio0;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private Label statPlayedGames;
    @FXML
    private Label statAvg;
    @FXML
    private Label statHCo;
    @FXML
    private Label statHScore;
    @FXML
    private Label statCo;
    @FXML
    private Button btnStatExit;
    @FXML
    private Button btnStatReset;
    @FXML
    private TextField inputUserName;
    @FXML
    private Button btnLogin;
    @FXML
    private Label outputUserName;
    @FXML
    private TableView table;
    @FXML
    private Button btnBackTable;
    @FXML
    private Button btnBackfromScore;
    
    
    DB db = new DB();
    Stats actualStat = new Stats();
    DBAvg avgTable = null;
    ArrayList<Integer> scoreList = new ArrayList<Integer>();
    ToggleGroup group = new ToggleGroup();
    boolean coHappened = false;
    boolean isSecondUndo = false;
    String latestHi="";
    int latestCo = 0;
    int chekoutTry = 0;
    int gameNumber = 0;
    @FXML
    private void handleButtonOne(ActionEvent event) {
        handleButton("1");
    }
    @FXML
    private void handleButtonTwo(ActionEvent event) {
        handleButton("2");
    }
    @FXML
    private void handleButtonThree(ActionEvent event) {
        handleButton("3");
    }
    @FXML
    private void handleButtonFour(ActionEvent event) {
        handleButton("4");
    }
    @FXML
    private void handleButtonFive(ActionEvent event) {
        handleButton("5");
    }
    @FXML
    private void handleButtonSix(ActionEvent event) {
        handleButton("6");
    }
    @FXML
    private void handleButtonSeven(ActionEvent event) {
        handleButton("7");
    }    
    @FXML
    private void handleButtonEight(ActionEvent event) {
        handleButton("8");
    }
    @FXML
    private void handleButtonNine(ActionEvent event) {
        handleButton("9");
    }
    @FXML
    private void handleButtonZero(ActionEvent event) {
        handleButton("0");
    }
    @FXML
    private void handleButtonClear(ActionEvent event) {
        inputScore.clear();
    }
    private void handleButton(String number) {
        String txt = inputScore.getText();
        inputScore.setText(txt+number);
        inputScore.setAlignment(Pos.BASELINE_RIGHT);
    }
    @FXML
    private void handleButtonScore(ActionEvent event) {
        try {
            Integer.valueOf(inputScore.getText());
            inputScore.getText().equals("");
        }
        catch(Exception e){
             alert("Give a number from 0 to 180"); 
             return;
        }  
        int actualScore    = Integer.valueOf(inputScore.getText());
        int remainingScore = Integer.valueOf(outputRem.getText());
        if (actualScore > 180){
            alert("The score must less than 180");
            inputScore.clear();
            return;
        }
        calculation(actualScore,remainingScore);
        isSecondUndo = false;
    }
    @FXML
    private void handleBtnLogin(ActionEvent event) {
        ArrayList<String> userNames = db.getAllUserName();
        boolean newPlayer = true;
        
        menuPane.setDisable(false);
        menuPane.setVisible(true);
        userPane.setDisable(true);
        userPane.setVisible(false);
        actualStat.setUserName(inputUserName.getText());
        outputUserName.setText(actualStat.getUserName()+"!");
        
        avgTable = new DBAvg(inputUserName.getText());
        if (userNames == null ) {
           db.addStat(actualStat);
           return;
        }
        for (int i=0; i<userNames.size(); i++){
            if (actualStat.getUserName().equals(userNames.get(i)))
                newPlayer = false;
        }
        if (newPlayer) {
           db.addStat(actualStat);       
        }
    }
    @FXML
    private void handleBtnStat(ActionEvent event) {
        Stats showedStats = new Stats();
        
        statPane.setDisable(false);
        statPane.setVisible(true);
        menuPane.setDisable(true);
        menuPane.setVisible(false);

        showedStats = db.getOwnStats(actualStat.getUserName());
        if (showedStats.getPlayedGames().equals("0")){
            statAvg.setText("");
            statCo.setText("");
            statHCo.setText("");
            statHScore.setText("");
            statPlayedGames.setText("");
        }
        else{        
            double avg = avgTable.getAvarageSum(actualStat.getUserName())/Integer.valueOf(showedStats.getPlayedGames());
            statAvg.setText(String.valueOf(avg));
            statPlayedGames.setText(String.valueOf(showedStats.getPlayedGames()));
            statCo.setText(String.valueOf(Integer.valueOf(showedStats.getPlayedGames())*100/Integer.valueOf(showedStats.getChekoutTry())));
            statHCo.setText(String.valueOf(showedStats.getHCo()));
            statHScore.setText(String.valueOf(showedStats.getHi()));
        }
    }
    @FXML
    private void handleBtnGame(ActionEvent event) {;
        scorePane.setDisable(false);
        scorePane.setVisible(true);
        menuPane.setDisable(true);
        menuPane.setVisible(false);
    }
    @FXML
    private void handleBtnExit(ActionEvent event) {
        statPane.setDisable(true);
        statPane.setVisible(false);
        menuPane.setDisable(false);
        menuPane.setVisible(true);
    }
    @FXML
    private void handleBtnBackfromScore(ActionEvent event) {
        coHappened = false;
        gameNumber = 0;
        latestCo   = 0;
        chekoutTry = 0;
        
        menuPane.setDisable(false);
        menuPane.setVisible(true);
        scorePane.setDisable(true);
        scorePane.setVisible(false);
        outputRem.setText("501");
        outputHi.setText("");
        ouputLegAvg.setText("");
    }
    @FXML
    private void handleBtnUndo(ActionEvent event) {
        if (isSecondUndo) {
            alert("You can only undo one score");
            return;
        }    
        int deletedScore = scoreList.get(scoreList.size()-1);
        Integer.valueOf(outputRem.getText());
        outputRem.setText(String.valueOf(Integer.valueOf(outputRem.getText())+deletedScore));
        scoreList.remove(scoreList.size()-1);
        isSecondUndo = true;
        outputHi.setText(latestHi);
    }
    
    @FXML
    private void handleCheckout(ActionEvent event) {
        int checkoutNumber = Integer.valueOf(group.getSelectedToggle().toString().substring(group.getSelectedToggle().toString().length()-2, group.getSelectedToggle().toString().length()-1));
        chekoutTry+=checkoutNumber;
        if (coHappened) {
            actualStat.setChekoutTry(chekoutTry);
            actualStat.setPlayedGamesNumber(gameNumber);
            outputDouble.setText(String.valueOf(actualStat.getchekoutPercentage()));
            coHappened = false;
        }    
        scorePane.setDisable(false);
        scorePane.setOpacity(1);
        checkoutPane.setOpacity(0);
        checkoutPane.setDisable(true);
        checkoutPane.setVisible(false);
    }
    
    @FXML
    private void handleBtnAllPlayerStat(ActionEvent event) {
        allPlayerStatPane.setDisable(false);
        allPlayerStatPane.setVisible(true);
        menuPane.setDisable(true);
        menuPane.setVisible(false);
    }
    
    @FXML
    private void handleButtonError(ActionEvent event) {
        errorPane.setVisible(false);
        scorePane.setOpacity(0);
    }
    
     private void alert(String text) {
        Label label = new Label(text);
        Button alertButton = new Button("OK");
        VBox vbox = new VBox(label, alertButton);
        
        scorePane.setDisable(true);
        scorePane.setOpacity(0.4);
        vbox.setAlignment(Pos.CENTER);
        
        alertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                scorePane.setDisable(false);
                scorePane.setOpacity(1);
                vbox.setVisible(false);
            }
        });
        
        anchorPane.getChildren().add(vbox);
        anchorPane.setTopAnchor(vbox, 200.0);
        anchorPane.setLeftAnchor(vbox, 85.0);
    }
    
    @FXML
    private void updateStatToDB (){
        gameNumber = 0;
        
        menuPane.setDisable(false);
        menuPane.setVisible(true);
        scorePane.setDisable(true);
        scorePane.setVisible(false);
        db.updateStat(actualStat);
    }
    
    @FXML
    private void clearStat (){
        menuPane.setDisable(false);
        menuPane.setVisible(true);
        statPane.setDisable(true);
        statPane.setVisible(false);db.clearStat(actualStat.getUserName());
        avgTable.clearAvg(actualStat.getUserName());
    }
    
    private void giveCheckOutNumber(){
        radio0.setToggleGroup(group);
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        
        checkoutPane.setOpacity(1);
        checkoutPane.setDisable(false);
        checkoutPane.setVisible(true);
        scorePane.setDisable(true);
        scorePane.setOpacity(0.1);
    }
        
    private void calculation(Integer actualScore, Integer remainingScore){
        double avg[] = avarageCalculation(actualScore);
        int hiScore  = 0;
        int newScore = 0;
        
        if((remainingScore-actualScore) < 2 && 
            !((remainingScore-actualScore) == 0) ){
            actualScore = 0;
            System.out.println("Túldobás");
        }

        newScore = remainingScore-actualScore;
        outputRem.setText(String.valueOf(newScore));

        if (actualStat.getHi() != "")
            hiScore = Integer.valueOf(actualStat.getHi());
        
        if (actualScore > hiScore){
            latestHi = outputHi.getText();
            outputHi.setText(String.valueOf(actualScore));
            actualStat.setHi(actualScore);
        }

        if (latestCo == 0) {
            ouputLegAvg.setText(String.valueOf(avg[0]));
            gameAvg.setText((String.valueOf(avg[1])));
        }else{
            ouputLegAvg.setText(String.valueOf(avg[1]));
            gameAvg.setText((String.valueOf(avg[0])));
        }
        
        if (newScore <= 170)
            checkOutTable(newScore);
        
        if (newScore <= 50){
            giveCheckOutNumber();
        }
        if (newScore == 0){
            if (latestCo == 0) 
                checkOutHappened(avg[0], actualScore);
            else 
                checkOutHappened(avg[1], actualScore);
        } 
        inputScore.clear();
    }
    private void checkOutHappened(double avg, int checkOutScore){
        int hCo = 0;
        gameNumber += 1;
        coHappened = true;
        outputRem.setText("501");
        avgTable.addAvg(avg, actualStat.getUserName());
        if (actualStat.getHCo() == "")
            hCo = 0;
        if (checkOutScore >= Integer.valueOf(hCo)) {
            actualStat.setHCo(checkOutScore);
        }
        outputHCo.setText(actualStat.getHCo());
        latestCo = scoreList.size();
    }
    
    private double[] avarageCalculation(Integer score){
        double [] avarages = new double[2];
        int gameScoreSum = 0;
        int actualLegSum = 0;
        scoreList.add(score);
        
        for(int i=0; i<scoreList.size();i++){
            gameScoreSum += scoreList.get(i);
        }
        if (latestCo > 0){
            for(int i=latestCo; i<scoreList.size();i++){
                actualLegSum += scoreList.get(i);
            }
            avarages[1] = actualLegSum/(scoreList.size()-latestCo);
        }
        avarages[0] = gameScoreSum/scoreList.size();
        return avarages;
    }

    private void checkOutTable(Integer remainingScore){
        switch (remainingScore){
                case 170: labelCo.setText("T20   T20   Bull");break;
                case 167: labelCo.setText("T20   T19   Bull");break;
                case 164: labelCo.setText("T20   T18   Bull");break;
                case 161: labelCo.setText("T20   T17   Bull");break;
                case 160: labelCo.setText("T20   T20   D20");break;
                case 158: labelCo.setText("T20   T20   D19");break;
                case 156: labelCo.setText("T20   T20   D18");break;
                case 155: labelCo.setText("T20   T15   Bull");break;
                case 154: labelCo.setText("T20   T18   D20");break;
                case 153: labelCo.setText("T20   T19   D18");break;
                case 152: labelCo.setText("T20   T20   D16");break;
                case 151: labelCo.setText("T20   T17   D20");break;
                case 150: labelCo.setText("T20   T18   D18");break;
                case 149: labelCo.setText("T20   T19   D16");break;
                case 148: labelCo.setText("T20   T16   D20");break;
                case 147: labelCo.setText("T20   T17   D18");break;
                case 146: labelCo.setText("T20   T18   D16");break;
                case 145: labelCo.setText("T20   T15   D20");break;
                case 144: labelCo.setText("T20   T20   D12");break;
                case 143: labelCo.setText("T20   T17   D16");break;
                case 142: labelCo.setText("T20   T14   D20");break;
                case 141: labelCo.setText("T20   T15   D18");break;
                case 140: labelCo.setText("T20   T20   D10");break;
                case 139: labelCo.setText("T20   T13   D20");break;
                case 138: labelCo.setText("T20   T14   D18");break;
                case 137: labelCo.setText("T17   T18   D16");break;
                case 136: labelCo.setText("T20   T20   D8");break;
                case 135: labelCo.setText("T20   T15   D15");break;
                case 134: labelCo.setText("T20   T14   D16");break;
                case 133: labelCo.setText("T20   T19   D8");break;
                case 132: labelCo.setText("T20   T20   D6");break;
                case 131: labelCo.setText("T20   T13   D16");break;
                case 130: labelCo.setText("T20   T18   D8");break;
                case 129: labelCo.setText("T19   T20   D6");break;
                case 128: labelCo.setText("T18   T14   D16");break;
                case 127: labelCo.setText("T19   T18   D8");break;
                case 126: labelCo.setText("T19   T19   D6");break;
                case 125: labelCo.setText("BULL  T20   D20");break;
                case 124: labelCo.setText("T20   T16   D16");break;
                case 123: labelCo.setText("T19   T16   D9");break;
                case 122: labelCo.setText("T18   T20   D4");break;
                case 121: labelCo.setText("T20   T15   D8");break;
                case 120: labelCo.setText("T20    20   D20");break;
                case 119: labelCo.setText("T20   19   D20");break;
                case 118: labelCo.setText("T20   18   D20");break;
                case 117: labelCo.setText("T20   17   D20");break;
                case 116: labelCo.setText("T20   16   D20");break;
                case 115: labelCo.setText("T20   15   D20");break;
                case 114: labelCo.setText("T20   14   D20");break;
                case 113: labelCo.setText("T20   13   D20");break;
                case 112: labelCo.setText("T20   20   D16");break;
                case 111: labelCo.setText("T20   19   D16");break;
                case 110: labelCo.setText("T20   18   D16");break;
                case 109: labelCo.setText("T20   17   D16");break;
                case 108: labelCo.setText("T20   16   D16");break;
                case 107: labelCo.setText("T19   18   D16");break;
                case 106: labelCo.setText("T20   14   D16");break;
                case 105: labelCo.setText("T20   13   D16");break;
                case 104: labelCo.setText("T18   18   D16");break;
                case 103: labelCo.setText("T20   11   D16");break;
                case 102: labelCo.setText("T20   10   D16");break;
                case 101: labelCo.setText("T17   18   D16");break;
                case 100: labelCo.setText("T20   D20");break;
                case  99: labelCo.setText("T19   10   D16");break;
                case  98: labelCo.setText("T20   D19");break;
                case  97: labelCo.setText("T19   D20");break;
                case  96: labelCo.setText("T20   D18");break;
                case  95: labelCo.setText("T19   D19");break;
                case  94: labelCo.setText("T18   D20");break;
                case  93: labelCo.setText("T19   D18");break;
                case  92: labelCo.setText("T20   D16");break;
                case  91: labelCo.setText("T17   D20");break;
                case  90: labelCo.setText("T18   D18");break;
                case  89: labelCo.setText("T19   D16");break;
                case  88: labelCo.setText("T16   D20");break;
                case  87: labelCo.setText("T17   D18");break;
                case  86: labelCo.setText("T18   D16");break;
                case  85: labelCo.setText("T15   D20");break;
                case  84: labelCo.setText("T20   D12");break;
                case  83: labelCo.setText("T17   D16");break;
                case  82: labelCo.setText("T14   D20");break;
                case  81: labelCo.setText("T15   D18");break;
                case  80: labelCo.setText("T16   D16");break;
                case  79: labelCo.setText("T13   D20");break;
                case  78: labelCo.setText("T14   D18");break;
                case  77: labelCo.setText("T19   D20");break;
                case  76: labelCo.setText("T20   D8");break;
                case  75: labelCo.setText("T15   D15");break;
                case  74: labelCo.setText("T14   D16");break;
                case  73: labelCo.setText("T19   D8");break;
                case  72: labelCo.setText("T20   D6");break;
                case  71: labelCo.setText("T13   D6");break;
                case  70: labelCo.setText("T18   D8");break;
                case  69: labelCo.setText("T19   D6");break;
                case  68: labelCo.setText("T16   D10");break;
                case  67: labelCo.setText("T17   D8");break;
                case  66: labelCo.setText("T10   D18");break;
                case  65: labelCo.setText("25   D20");break;
                case  64: labelCo.setText("T16   D8");break;
                case  63: labelCo.setText("T13   D12");break;
                case  62: labelCo.setText("T10   D16");break;
                case  61: labelCo.setText("T13   D6");break;
                case  60: labelCo.setText("20   D20");break;
                case  59: labelCo.setText("19   D20");break;
                case  58: labelCo.setText("18   D20");break;
                case  57: labelCo.setText("17   D20");break;
                case  56: labelCo.setText("16   D20");break;
                case  55: labelCo.setText("15   D20");break;
                case  54: labelCo.setText("14   D20");break;
                case  53: labelCo.setText("13   D20");break;
                case  52: labelCo.setText("20   D20");break;
                case  51: labelCo.setText("19   D16");break;
                case  50: labelCo.setText("BULL");break;
                case  49: labelCo.setText("17   D16");break;
                case  48: labelCo.setText("16   D16");break;
                case  47: labelCo.setText("15   D16");break;
                case  46: labelCo.setText("6 D20");break;
                case  45: labelCo.setText("5   D20");break;
                case  44: labelCo.setText("12   D16");break;
                case  43: labelCo.setText("11   D16");break;
                case  42: labelCo.setText("10   D16");break;
                case  41: labelCo.setText("9   D16");break;
                case  40: labelCo.setText("D20");break;
                case  39: labelCo.setText("7   D16");break;
                case  38: labelCo.setText("D19");break;
                case  37: labelCo.setText("5   D16");break;
                case  36: labelCo.setText("D18");break;
                case  35: labelCo.setText("3   D16");break;
                case  34: labelCo.setText("D17");break;
                case  33: labelCo.setText("1   D16");break;
                case  32: labelCo.setText("D16");break;
                case  31: labelCo.setText("15   D8");break;
                case  30: labelCo.setText("D15");break;
                case  29: labelCo.setText("13   D8");break;
                case  28: labelCo.setText("D14");break;
                case  27: labelCo.setText("11   D8");break;
                case  26: labelCo.setText("D13");break;
                case  25: labelCo.setText("9   D8");break;
                case  24: labelCo.setText("D12");break;
                case  23: labelCo.setText("7   D8");break;
                case  22: labelCo.setText("D11");break;
                case  21: labelCo.setText("5   D8");break;
                case  20: labelCo.setText("D10");break;
                case  19: labelCo.setText("3   D8");break;
                case  18: labelCo.setText("D9");break;
                case  17: labelCo.setText("9   D4");break;
                case  16: labelCo.setText("D8");break;
                case  15: labelCo.setText("7   D4");break;
                case  14: labelCo.setText("D7");break;
                case  13: labelCo.setText("5   D4");break;
                case  12: labelCo.setText("D6");break;
                case  11: labelCo.setText("3   D4");break;
                case  10: labelCo.setText("D5");break;
                case   9: labelCo.setText("1    D4");break;
                case   8: labelCo.setText("D4");break;
                case   7: labelCo.setText("3   D2");break;
                case   6: labelCo.setText("D3");break;
                case   5: labelCo.setText("1   D2");break;
                case   4: labelCo.setText("D2");break;
                case   3: labelCo.setText("1   D1");break;
                case   2: labelCo.setText("D2");break;
                default: labelCo.setText("");break;
        }            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
