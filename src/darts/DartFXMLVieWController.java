package darts;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.control.PasswordField;
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
    private Pane passwordPane;
    @FXML
    private Pane allPlayerStatPane;
    @FXML 
    private Pane checkoutHappenedPane;
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
    private RadioButton checkout0;
    @FXML
    private RadioButton checkout1;
    @FXML
    private RadioButton checkout2;
    @FXML
    private RadioButton checkout3;
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
    private PasswordField inputPassword;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword1;
    @FXML
    private PasswordField newPassword2;
    @FXML
    private Button btnLogin;
    @FXML
    private Button cancelNewPasswordBtn;
    @FXML
    private Button saveNewPasswordBtn;
    @FXML
    private Button changePasswordBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private Label outputUserName;
    @FXML
    private TableView table;
    @FXML
    private Button btnBackTable;
    @FXML
    private Button btnBackfromScore;
    @FXML
    private TextField inputNewUserName;
    @FXML
    private PasswordField inputNewPassword;
    @FXML
    private PasswordField inputNewPassword1;
    @FXML
    private Button btnSingUp;
    
    
    DB db = new DB();
    Stats actualStat = new Stats();
    ArrayList<Integer> scoreList = new ArrayList<Integer>();
    ToggleGroup group = new ToggleGroup();
    ToggleGroup usedDartsGroup = new ToggleGroup();
    boolean coHappened = false;
    boolean isSecondUndo = false;
    int latestCo = 0;
    int chekoutTry = 0;
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
    private void handleBtnLogin(ActionEvent event) {
        ArrayList<String> userNames = db.getAllUserName();
        String actualUserName = inputUserName.getText();
        String actualPassword = inputPassword.getText();
        boolean playerFound = false;
        
        for (int i=0; i<userNames.size(); i++){
            if (actualUserName.equals(userNames.get(i))){
                System.out.println("Megvan!");
                String savedPassword = db.getUserPassword(actualUserName);
                System.out.println("SavedPassword: "+savedPassword);
                System.out.println("Actual pass: "+actualPassword);
                if(actualPassword.equals(savedPassword)){
                    paneChange(userPane,menuPane);
                    actualStat = db.getOwnStats(actualUserName);
                    outputUserName.setText(actualStat.getUserName()+"!");
                    return;
                }
            }
        }
        alert("Your username or password are incorrect!");
        inputPassword.clear();
        return;
        
    }
    @FXML
    private void handleSingUpBtn(ActionEvent event) {
        ArrayList<String> userNames = db.getAllUserName();
        String UserName = inputNewUserName.getText();
        String Password = inputNewPassword.getText();
        String repPassword = inputNewPassword1.getText();
        for (int i=0; i<userNames.size(); i++){
            if (UserName.equals(userNames.get(i))){
                alert("Name is in use, please chose another");
                return;
            }
        }
        if (!Password.equals(repPassword)) {
            alert("Your passwords don't match");
            return;
        }
        db.addStat(UserName, Password);   
        alert("Your account created. Plese log in");
        inputNewUserName.clear();
        inputNewPassword.clear();
        inputNewPassword1.clear();
        clearStatData();
        return;
    }
    @FXML
    private void handleBtnStat(ActionEvent event) {
        Stats showedStats = new Stats();
        paneChange(menuPane,statPane);

        showedStats = db.getOwnStats(actualStat.getUserName());
        System.out.println("showedStats.getPlayedGames() "+showedStats.getPlayedGames());
        if (showedStats.getPlayedGames().equals("0")){
            statAvg.setText("");
            statCo.setText("");
            statHCo.setText("");
            statHScore.setText("");
            statPlayedGames.setText("");
        }
        else{        
            setLabelValue(statAvg, String.valueOf(showedStats.getAvarage()));
            setLabelValue(statPlayedGames, showedStats.getPlayedGames());
            setLabelValue(statCo, String.valueOf(Integer.valueOf(showedStats.getPlayedGames())*100/Integer.valueOf(showedStats.getChekoutTry())));
            setLabelValue(statHCo, showedStats.getHCo());
            setLabelValue(statHScore, showedStats.getHi());
        }
    }
    @FXML
    private void handleBtnGame(ActionEvent event) {
        actualStat = db.getOwnStats(actualStat.getUserName());
        System.out.println("played games: "+actualStat.getPlayedGames());
        paneChange(menuPane,scorePane);
    }
    @FXML
    private void handleChangePasswordBtn(ActionEvent event) {
        paneChange(menuPane,passwordPane);
    }
    @FXML
    private void handleCancelNewPasswordBtn(ActionEvent event) {
        paneChange(passwordPane, menuPane);
    }
    @FXML
    private void handleLogOutBtn(ActionEvent event) {
        paneChange(menuPane, userPane);
        inputPassword.clear();
        inputUserName.clear();
        inputNewUserName.clear();
        inputNewPassword.clear();
        inputNewPassword1.clear();
    }
    @FXML
    private void handleSaveNewPasswordBtn(ActionEvent event) {
        String savedPassword = db.getUserPassword(actualStat.getUserName());
        
        if (!currentPassword.getText().equals(savedPassword)) {
            alert("The given password don't match with the saved one");
            return;
        }
        if (!newPassword1.getText().equals(newPassword2.getText())) {
            alert("The new password dooesn't match");
            return;
        }
        
        db.saveNewPassword(actualStat.getUserName(),newPassword1.getText());
        paneChange(passwordPane, menuPane);
    }
    @FXML
    private void handleBtnExit(ActionEvent event) {
        paneChange(statPane,menuPane);
    }
    @FXML
    private void handleBtnBackfromScore(ActionEvent event) {
        coHappened = false;
        latestCo   = 0;
        chekoutTry = 0;
        paneChange(scorePane,menuPane);
        setLabelValue(outputRem, "501");
        setLabelValue(outputHi, "");
        setLabelValue(ouputLegAvg, "");
        setLabelValue(outputHCo, "");
        setLabelValue(outputDouble, "");
        setLabelValue(gameAvg, "");
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
        System.err.println("Played gamesd: "+actualStat.getPlayedGames());
    }
    
    @FXML
    private void handleBtnUndo(ActionEvent event) {
        int size = scoreList.size(); 
        if (isSecondUndo) {
            alert("You can only use once the 'Undo' button");
            return;
        }
        if(size == 0) {
            alert("There is nothing to undo");
            return;
        }
        int deletedScore = scoreList.get(size-1);
        Integer.valueOf(outputRem.getText());
        setLabelValue(outputRem, String.valueOf(Integer.valueOf(outputRem.getText())+deletedScore));
        scoreList.remove(size-1);
        actualStat.setUsedDartsNumber(Integer.valueOf(actualStat.getUsedDartsNumber())-3);
        isSecondUndo = true;
        setLabelValue(outputHi, String.valueOf(Collections.max(scoreList)));
    }
    
    @FXML
    private void handleCheckout(ActionEvent event) {
        int dartNumber = 0;
        int checkoutNumber = Integer.valueOf(group.getSelectedToggle().toString().substring(group.getSelectedToggle().toString().length()-2, 
                                             group.getSelectedToggle().toString().length()-1));
        chekoutTry+=checkoutNumber;
        if (coHappened) {
            actualStat.setPlayedGamesNumber(Integer.valueOf(actualStat.getPlayedGames())+1);
            dartNumber = Integer.valueOf(usedDartsGroup.getSelectedToggle().toString().substring(usedDartsGroup.getSelectedToggle().toString().length()-2, 
                                         usedDartsGroup.getSelectedToggle().toString().length()-1));
            actualStat.setUsedDartsNumber(Integer.valueOf(actualStat.getUsedDartsNumber())+dartNumber);
            System.out.println("Dart Number: "+actualStat.getUsedDartsNumber());
            actualStat.setChekoutTry(chekoutTry);
            outputDouble.setText(String.valueOf(actualStat.getchekoutPercentage()));
            coHappened = false;
            setLabelValue(gameAvg, String.valueOf(actualStat.getAvarage()));
            inputScore.clear();
            setLabelValue(ouputLegAvg, "");
            scoreList.clear();
        }
        paneChange(checkoutPane, scorePane);
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
        if(actualStat.getPlayedGames().equals("") || !outputRem.getText().equals("501")) {
            alert("You cannont save the statistic yet");
            return;
        }
        System.out.println("played games: "+actualStat.getPlayedGames());
        db.updateStat(actualStat);
        actualStat.setPlayedGamesNumber(0);
        actualStat.setUsedDartsNumber(0);
        paneChange(scorePane,menuPane);
    }
    
    @FXML
    private void clearStat (){
        paneChange(statPane,menuPane);
        db.clearStat(actualStat.getUserName());
        actualStat = db.getOwnStats(actualStat.getUserName());
    }
    
    private void giveCheckOutNumber(Boolean checkOutHappend){
        radio0.setToggleGroup(group);
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        
        checkout0.setToggleGroup(usedDartsGroup);
        checkout1.setToggleGroup(usedDartsGroup);
        checkout2.setToggleGroup(usedDartsGroup);
        checkout3.setToggleGroup(usedDartsGroup);
        if (checkOutHappend){
            checkoutHappenedPane.setVisible(true);
            checkoutHappenedPane.setDisable(false);
        }
        else
        {
            checkoutHappenedPane.setVisible(false);
            checkoutHappenedPane.setDisable(true);
        }    
        paneChange(scorePane,checkoutPane);
    }

    private void calculation(Integer actualScore, Integer remainingScore){
        double avg = 0;
        int newScore = 0;
        
        if((remainingScore-actualScore) < 2 && 
            !((remainingScore-actualScore) == 0) ){
            actualScore = 0;
        }
        avg = avarageCalculation(actualScore);
        newScore = remainingScore-actualScore;
        setLabelValue(outputRem, String.valueOf(newScore));
        setLabelValue(outputHi, String.valueOf(Collections.max(scoreList)));
        setLabelValue(ouputLegAvg, String.valueOf(avg));
        
        if (newScore <= 170)
            checkOutTable(newScore);
        
        if (newScore == 0){
            checkOutHappened(avg, actualScore);
            return;
        }
        
        if (newScore <= 50 ){
            giveCheckOutNumber(false);
        }

        actualStat.setUsedDartsNumber(Integer.valueOf(actualStat.getUsedDartsNumber())+3);
        System.out.println("dart Number: "+actualStat.getUsedDartsNumber());
        inputScore.clear();
    }
    public void checkOutHappened(double avg, int checkOutScore){
        int hCo = 0;
        coHappened = true;
        setLabelValue(outputRem, "501");
        giveCheckOutNumber(true);
        //avgTable.addAvg(avg, actualStat.getUserName());
        if (actualStat.getHCo() == "")
            hCo = 0;
        if (checkOutScore >= Integer.valueOf(hCo)) {
            actualStat.setHCo(checkOutScore);
        }
        setLabelValue(outputHCo,actualStat.getHCo());
        latestCo = scoreList.size();
    }
    
    public double avarageCalculation(Integer score){
        double avarages  = 0;
        int actualLegSum = 0;
        scoreList.add(score);
        
        for(int i=0; i<scoreList.size();i++){
            actualLegSum += scoreList.get(i);
        }
        avarages = actualLegSum/scoreList.size();
        return avarages;
    }

    public void checkOutTable(Integer remainingScore){
        switch (remainingScore){
                case 170: setLabelValue(labelCo,"T20   T20   Bull");break;
                case 167: setLabelValue(labelCo,"T20   T19   Bull");break;
                case 164: setLabelValue(labelCo,"T20   T18   Bull");break;
                case 161: setLabelValue(labelCo,"T20   T17   Bull");break;
                case 160: setLabelValue(labelCo,"T20   T20   D20");break;
                case 158: setLabelValue(labelCo,"T20   T20   D19");break;
                case 156: setLabelValue(labelCo,"T20   T20   D18");break;
                case 155: setLabelValue(labelCo,"T20   T15   Bull");break;
                case 154: setLabelValue(labelCo,"T20   T18   D20");break;
                case 153: setLabelValue(labelCo,"T20   T19   D18");break;
                case 152: setLabelValue(labelCo,"T20   T20   D16");break;
                case 151: setLabelValue(labelCo,"T20   T17   D20");break;
                case 150: setLabelValue(labelCo,"T20   T18   D18");break;
                case 149: setLabelValue(labelCo,"T20   T19   D16");break;
                case 148: setLabelValue(labelCo,"T20   T16   D20");break;
                case 147: setLabelValue(labelCo,"T20   T17   D18");break;
                case 146: setLabelValue(labelCo,"T20   T18   D16");break;
                case 145: setLabelValue(labelCo,"T20   T15   D20");break;
                case 144: setLabelValue(labelCo,"T20   T20   D12");break;
                case 143: setLabelValue(labelCo,"T20   T17   D16");break;
                case 142: setLabelValue(labelCo,"T20   T14   D20");break;
                case 141: setLabelValue(labelCo,"T20   T15   D18");break;
                case 140: setLabelValue(labelCo,"T20   T20   D10");break;
                case 139: setLabelValue(labelCo,"T20   T13   D20");break;
                case 138: setLabelValue(labelCo,"T20   T14   D18");break;
                case 137: setLabelValue(labelCo,"T17   T18   D16");break;
                case 136: setLabelValue(labelCo,"T20   T20   D8");break;
                case 135: setLabelValue(labelCo,"T20   T15   D15");break;
                case 134: setLabelValue(labelCo,"T20   T14   D16");break;
                case 133: setLabelValue(labelCo,"T20   T19   D8");break;
                case 132: setLabelValue(labelCo,"T20   T20   D6");break;
                case 131: setLabelValue(labelCo,"T20   T13   D16");break;
                case 130: setLabelValue(labelCo,"T20   T18   D8");break;
                case 129: setLabelValue(labelCo,"T19   T20   D6");break;
                case 128: setLabelValue(labelCo,"T18   T14   D16");break;
                case 127: setLabelValue(labelCo,"T19   T18   D8");break;
                case 126: setLabelValue(labelCo,"T19   T19   D6");break;
                case 125: setLabelValue(labelCo,"BULL  T20   D20");break;
                case 124: setLabelValue(labelCo,"T20   T16   D16");break;
                case 123: setLabelValue(labelCo,"T19   T16   D9");break;
                case 122: setLabelValue(labelCo,"T18   T20   D4");break;
                case 121: setLabelValue(labelCo,"T20   T15   D8");break;
                case 120: setLabelValue(labelCo,"T20    20   D20");break;
                case 119: setLabelValue(labelCo,"T20   19   D20");break;
                case 118: setLabelValue(labelCo,"T20   18   D20");break;
                case 117: setLabelValue(labelCo,"T20   17   D20");break;
                case 116: setLabelValue(labelCo,"T20   16   D20");break;
                case 115: setLabelValue(labelCo,"T20   15   D20");break;
                case 114: setLabelValue(labelCo,"T20   14   D20");break;
                case 113: setLabelValue(labelCo,"T20   13   D20");break;
                case 112: setLabelValue(labelCo,"T20   20   D16");break;
                case 111: setLabelValue(labelCo,"T20   19   D16");break;
                case 110: setLabelValue(labelCo,"T20   18   D16");break;
                case 109: setLabelValue(labelCo,"T20   17   D16");break;
                case 108: setLabelValue(labelCo,"T20   16   D16");break;
                case 107: setLabelValue(labelCo,"T19   18   D16");break;
                case 106: setLabelValue(labelCo,"T20   14   D16");break;
                case 105: setLabelValue(labelCo,"T20   13   D16");break;
                case 104: setLabelValue(labelCo,"T18   18   D16");break;
                case 103: setLabelValue(labelCo,"T20   11   D16");break;
                case 102: setLabelValue(labelCo,"T20   10   D16");break;
                case 101: setLabelValue(labelCo,"T17   18   D16");break;
                case 100: setLabelValue(labelCo,"T20   D20");break;
                case  99: setLabelValue(labelCo,"T19   10   D16");break;
                case  98: setLabelValue(labelCo,"T20   D19");break;
                case  97: setLabelValue(labelCo,"T19   D20");break;
                case  96: setLabelValue(labelCo,"T20   D18");break;
                case  95: setLabelValue(labelCo,"T19   D19");break;
                case  94: setLabelValue(labelCo,"T18   D20");break;
                case  93: setLabelValue(labelCo,"T19   D18");break;
                case  92: setLabelValue(labelCo,"T20   D16");break;
                case  91: setLabelValue(labelCo,"T17   D20");break;
                case  90: setLabelValue(labelCo,"T18   D18");break;
                case  89: setLabelValue(labelCo,"T19   D16");break;
                case  88: setLabelValue(labelCo,"T16   D20");break;
                case  87: setLabelValue(labelCo,"T17   D18");break;
                case  86: setLabelValue(labelCo,"T18   D16");break;
                case  85: setLabelValue(labelCo,"T15   D20");break;
                case  84: setLabelValue(labelCo,"T20   D12");break;
                case  83: setLabelValue(labelCo,"T17   D16");break;
                case  82: setLabelValue(labelCo,"T14   D20");break;
                case  81: setLabelValue(labelCo,"T15   D18");break;
                case  80: setLabelValue(labelCo,"T16   D16");break;
                case  79: setLabelValue(labelCo,"T13   D20");break;
                case  78: setLabelValue(labelCo,"T14   D18");break;
                case  77: setLabelValue(labelCo,"T19   D20");break;
                case  76: setLabelValue(labelCo,"T20   D8");break;
                case  75: setLabelValue(labelCo,"T15   D15");break;
                case  74: setLabelValue(labelCo,"T14   D16");break;
                case  73: setLabelValue(labelCo,"T19   D8");break;
                case  72: setLabelValue(labelCo,"T20   D6");break;
                case  71: setLabelValue(labelCo,"T13   D6");break;
                case  70: setLabelValue(labelCo,"T18   D8");break;
                case  69: setLabelValue(labelCo,"T19   D6");break;
                case  68: setLabelValue(labelCo,"T16   D10");break;
                case  67: setLabelValue(labelCo,"T17   D8");break;
                case  66: setLabelValue(labelCo,"T10   D18");break;
                case  65: setLabelValue(labelCo,"25   D20");break;
                case  64: setLabelValue(labelCo,"T16   D8");break;
                case  63: setLabelValue(labelCo,"T13   D12");break;
                case  62: setLabelValue(labelCo,"T10   D16");break;
                case  61: setLabelValue(labelCo,"T13   D6");break;
                case  60: setLabelValue(labelCo,"20   D20");break;
                case  59: setLabelValue(labelCo,"19   D20");break;
                case  58: setLabelValue(labelCo,"18   D20");break;
                case  57: setLabelValue(labelCo,"17   D20");break;
                case  56: setLabelValue(labelCo,"16   D20");break;
                case  55: setLabelValue(labelCo,"15   D20");break;
                case  54: setLabelValue(labelCo,"14   D20");break;
                case  53: setLabelValue(labelCo,"13   D20");break;
                case  52: setLabelValue(labelCo,"20   D20");break;
                case  51: setLabelValue(labelCo,"19   D16");break;
                case  50: setLabelValue(labelCo,"BULL");break;
                case  49: setLabelValue(labelCo,"17   D16");break;
                case  48: setLabelValue(labelCo,"16   D16");break;
                case  47: setLabelValue(labelCo,"15   D16");break;
                case  46: setLabelValue(labelCo,"6 D20");break;
                case  45: setLabelValue(labelCo,"5   D20");break;
                case  44: setLabelValue(labelCo,"12   D16");break;
                case  43: setLabelValue(labelCo,"11   D16");break;
                case  42: setLabelValue(labelCo,"10   D16");break;
                case  41: setLabelValue(labelCo,"9   D16");break;
                case  40: setLabelValue(labelCo,"D20");break;
                case  39: setLabelValue(labelCo,"7   D16");break;
                case  38: setLabelValue(labelCo,"D19");break;
                case  37: setLabelValue(labelCo,"5   D16");break;
                case  36: setLabelValue(labelCo,"D18");break;
                case  35: setLabelValue(labelCo,"3   D16");break;
                case  34: setLabelValue(labelCo,"D17");break;
                case  33: setLabelValue(labelCo,"1   D16");break;
                case  32: setLabelValue(labelCo,"D16");break;
                case  31: setLabelValue(labelCo,"15   D8");break;
                case  30: setLabelValue(labelCo,"D15");break;
                case  29: setLabelValue(labelCo,"13   D8");break;
                case  28: setLabelValue(labelCo,"D14");break;
                case  27: setLabelValue(labelCo,"11   D8");break;
                case  26: setLabelValue(labelCo,"D13");break;
                case  25: setLabelValue(labelCo,"9   D8");break;
                case  24: setLabelValue(labelCo,"D12");break;
                case  23: setLabelValue(labelCo,"7   D8");break;
                case  22: setLabelValue(labelCo,"D11");break;
                case  21: setLabelValue(labelCo,"5   D8");break;
                case  20: setLabelValue(labelCo,"D10");break;
                case  19: setLabelValue(labelCo,"3   D8");break;
                case  18: setLabelValue(labelCo,"D9");break;
                case  17: setLabelValue(labelCo,"9   D4");break;
                case  16: setLabelValue(labelCo,"D8");break;
                case  15: setLabelValue(labelCo,"7   D4");break;
                case  14: setLabelValue(labelCo,"D7");break;
                case  13: setLabelValue(labelCo,"5   D4");break;
                case  12: setLabelValue(labelCo,"D6");break;
                case  11: setLabelValue(labelCo,"3   D4");break;
                case  10: setLabelValue(labelCo,"D5");break;
                case   9: setLabelValue(labelCo,"1    D4");break;
                case   8: setLabelValue(labelCo,"D4");break;
                case   7: setLabelValue(labelCo,"3   D2");break;
                case   6: setLabelValue(labelCo,"D3");break;
                case   5: setLabelValue(labelCo,"1   D2");break;
                case   4: setLabelValue(labelCo,"D2");break;
                case   3: setLabelValue(labelCo,"1   D1");break;
                case   2: setLabelValue(labelCo,"D2");break;
                default: setLabelValue(labelCo,"");break;
        }            
    }
    
    private void paneChange(Pane changeFrom, Pane changeTo){
        changeTo.setVisible(true);
        changeTo.setDisable(false);
        changeFrom.setVisible(false);
        changeFrom.setDisable(true);
    }
    
    public void setLabelValue(Label label, String text){
        label.setText(text);
    }
    public void clearStatData(){
        actualStat = new Stats();
        scoreList.clear();
        coHappened = false;
        isSecondUndo = false;
        latestCo = 0;
        chekoutTry = 0;    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
