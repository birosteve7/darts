package darts;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    private Pane registerPane;
    @FXML 
    private Pane checkoutHappenedPane;
    @FXML
    private Pane userDataPane;
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
    private Label outputLegAvg;
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
    private RadioButton checkout1;
    @FXML
    private RadioButton checkout2;
    @FXML
    private RadioButton checkout3;
    @FXML
    private Label statPlayedGames;
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
    private PasswordField password;
    @FXML
    private PasswordField password1;
    @FXML
    private Button btnSingUp;
    @FXML
    private ListView statNameList;
    @FXML
    private ListView statList;
    @FXML
    private Button btnRegister;
    @FXML
    private TextField inputFirstName;
    @FXML
    private TextField inputLastName;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputBirthDate;
    @FXML
    private ComboBox countryBox;
    @FXML
    private TextField inputCity;
    @FXML
    private Label outputFirstName;
    @FXML
    private Label outputLastName;
    @FXML
    private Label outputEmail;
    @FXML
    private Label outputBirthDate;
    @FXML
    private Label outputCountry;
    @FXML
    private Label outputCity;
    @FXML
    private TextField inputNewEmail;
    @FXML
    private ComboBox inputNewCountryBox;
    @FXML
    private TextField inputNewCity;
    @FXML
    private Button btnUserDataModify;
    @FXML
    private Button btnUserDataOK;
    @FXML
    private Button btnUserDataSave; 
    
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    
    DB db = new DB();
    UserDataDB userDataDB = new UserDataDB();
    UserData data = new UserData();
    CountryList countries = new CountryList();
    Game game = null;
    ToggleGroup group = new ToggleGroup();
    ToggleGroup usedDartsGroup = new ToggleGroup();
    List<CountryList.Country> countryList;
    
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
                String savedPassword = userDataDB.getUserPassword(actualUserName);
                if(actualPassword.equals(savedPassword)){
                    paneChange(userPane,menuPane);
                    outputUserName.setText(actualUserName+"!");
                    data.setUserName(actualUserName);
                    return;
                }
            }
        }
        alert("Your username or password are incorrect!", userPane);
        inputPassword.clear();
        return;
        
    }
    
    @FXML 
    private void handleBtnRegister(){
        countryBox.getItems().addAll(countryList);
        paneChange(userPane, registerPane);
    }
    
    @FXML
    private void handleRegisterBtn(ActionEvent event) {
        ArrayList<String> userNames = db.getAllUserName();
        String UserName = inputNewUserName.getText();
        String Password = password.getText();
        String repPassword = password1.getText();
        String email = inputEmail.getText();
        String birthDate = inputBirthDate.getText();
        
        for (int i=0; i<userNames.size(); i++){
            if (UserName.equals(userNames.get(i))){
                alert("Name is in use, please chose another",registerPane);
                return;
            }
        }
        if (!Password.equals(repPassword)) {
            alert("Your passwords don't match",registerPane);
            return;
        }
        if (!isValidEmail(email)){
            alert("Please give a valid email adress", registerPane);
            return;
        }
        if (!isvalidDate(birthDate)) {
            alert("The birthdate is invalid", registerPane);
            return;
        }
        userDataDB.addData(UserName, Password, email, inputFirstName.getText(), inputLastName.getText(),
                           birthDate, countryBox.getSelectionModel().getSelectedItem().toString(), inputCity.getText());
        db.addStat(UserName);   
        alert("Your account created. Plese log in", userPane);
        paneChange(registerPane,userPane);
        inputNewUserName.clear();
        password.clear();
        password1.clear();
        return;
    }
    @FXML
    private void handleBtnStat(ActionEvent event) {
        statNameListAddition(inputUserName.getText());
        paneChange(menuPane,statPane);
    }
    
    @FXML
    private void handleBtnGame(ActionEvent event) {
        game = new Game();
        paneChange(menuPane,scorePane);
    }
    
    @FXML
    private void handleChangePasswordBtn(ActionEvent event) {
        paneChange(menuPane,passwordPane);
    }
    
    @FXML
    private void handleBtnShowUserData(ActionEvent event) {
        UserData data = userDataDB.getUserdata(inputUserName.getText());
        outputFirstName.setText(data.getFirstName());
        outputLastName.setText(data.getLastName());
        outputBirthDate.setText(data.getBirthDate());
        outputEmail.setText(data.getEmail());
        outputCountry.setText(data.getCountry());
        outputCity.setText(data.getCity());
        paneChange(menuPane,userDataPane);
    }
    
    @FXML
    private void handleBtnUserDataCancel(ActionEvent event) {
        paneChange(userDataPane, menuPane);
    }
    
    @FXML
    private void handleBtnChangeData(ActionEvent event) {
        outputEmail.setVisible(false);
        outputCountry.setVisible(false);
        outputCity. setVisible(false);
        btnUserDataOK.setVisible(false);
        btnUserDataModify.setVisible(false);
        btnUserDataSave.setVisible(true);
        inputNewEmail.setVisible(true);
        inputNewCountryBox.setVisible(true);
        inputNewCity.setVisible(true);
        inputNewCountryBox.getItems().addAll(countryList);
    }
    
    @FXML
    private void handleBtnUserDataSave(ActionEvent event) {
        userDataDB.updateData(inputNewEmail.getText(),inputNewCountryBox.getSelectionModel().getSelectedItem().toString(), inputNewCity.getText(), inputUserName.getText());
        UserData data = userDataDB.getUserdata(inputUserName.getText());
        outputEmail.setText(data.getEmail());
        outputCountry.setText(data.getCountry());
        outputCity.setText(data.getCity());
        outputEmail.setVisible(true);
        outputCountry.setVisible(true);
        outputCity. setVisible(true);
        btnUserDataOK.setVisible(true);
        btnUserDataModify.setVisible(true);
        btnUserDataSave.setVisible(false);
        inputNewEmail.setVisible(false);
        inputNewCountryBox.setVisible(false);
        inputNewCity.setVisible(false);
    }
    
    @FXML
    private void handleCancelNewPasswordBtn(ActionEvent event) {
        paneChange(passwordPane, menuPane);
    }
    
    @FXML
    private void handleLogOutBtn(ActionEvent event) {
        inputPassword.clear();
        inputUserName.clear();
        paneChange(menuPane, userPane);
    }
    
    @FXML
    private void handleSaveNewPasswordBtn(ActionEvent event) {
        String savedPassword = userDataDB.getUserPassword(data.getUserName());
        
        if (!currentPassword.getText().equals(savedPassword)) {
            alert("The given password don't match with the saved one", passwordPane);
            return;
        }
        if (!newPassword1.getText().equals(newPassword2.getText())) {
            alert("The new password dooesn't match", passwordPane);
            return;
        }
        
        userDataDB.saveNewPassword(data.getUserName(),newPassword1.getText());
        paneChange(passwordPane, menuPane);
    }
    
    @FXML
    private void handleBtnExit(ActionEvent event) {
        statNameList.getItems().clear();
        statList.getItems().clear();
        paneChange(statPane,menuPane);
    }
    @FXML
    private void handleBtnBackfromScore(ActionEvent event) {
        game = new Game(); 
        paneChange(scorePane,menuPane);
        setLabelValue(outputRem, game.getRemainigScore());
        setLabelValue(outputHi, "");
        setLabelValue(outputLegAvg, "");
        setLabelValue(outputHCo, "");
        setLabelValue(outputDouble, "");
        setLabelValue(gameAvg, "");
    }
    
    @FXML
    private void handleButtonScore(ActionEvent event) {
        int remainingScore = Integer.valueOf(outputRem.getText());
        int actualScore    = Integer.valueOf(inputScore.getText());
        initRadioButtons();
        try {
            Integer.valueOf(inputScore.getText());
            inputScore.getText().equals("");
        }
        catch(Exception e){
             alert("Give a number from 0 to 180", scorePane); 
             return;
        }
        if (actualScore > 180){
            alert("The score must less than 180", scorePane);
            inputScore.clear();
            return;
        } 
        setCheckoutOpt(actualScore);
        game.calculation(actualScore);
   
        if(Integer.valueOf(game.getRemainigScore()) <= 170){
            setLabelValue(labelCo, game.checkOutTable());
        }     
        setLabelValue(outputRem, game.getRemainigScore());
        setActualLegDatas();
        inputScore.clear();
    }
    
    @FXML
    private void handleBtnUndo(ActionEvent event) {
        String res = game.removeLastScore();
        if (!res.equals("")) {
            alert(res, scorePane);
            return;
        }
        setActualLegDatas();
    }
    
    @FXML
    private void handleCheckout(ActionEvent event) {
        int dartNumber = 0;
        int checkoutNumber = Integer.valueOf(group.getSelectedToggle().toString().substring(group.getSelectedToggle().toString().length()-2, 
                                             group.getSelectedToggle().toString().length()-1));
        if (game.getRemainigScore().equals("0")) {
            dartNumber = Integer.valueOf(usedDartsGroup.getSelectedToggle().toString().substring(usedDartsGroup.getSelectedToggle().toString().length()-2, 
                                         usedDartsGroup.getSelectedToggle().toString().length()-1));
            if (!game.isValidCheckoutData(checkoutNumber,dartNumber)){
                alert("Your given data is invalid", checkoutPane);
                return;
            }
            game.setChekoutTry(game.getChekoutTry()+checkoutNumber);
            game.setPlayedGamesNumber(Integer.valueOf(game.getPlayedGamesNumber())+1);
            game.setUsedDartsNumber(Integer.valueOf(game.getUsedDartsNumber())+dartNumber);
            game.setChekoutTry(game.getChekoutTry());
            setGameDatas();
            game.initGame();
            inputScore.clear();
            setLabelValue(outputLegAvg, "");

        } else {
            game.setChekoutTry(game.getChekoutTry()+checkoutNumber);
        }    
        paneChange(checkoutPane, scorePane);
    }
    
    @FXML
    private void handleButtonError(ActionEvent event) {
        errorPane.setVisible(false);
        scorePane.setOpacity(0);
    }
    
    @FXML
    private void updateStatToDB (){
        Stats actualStat = new Stats(data.getUserName(), game.getHighestCo(), game.getChekoutTry(), game.getPlayedGamesNumber(),
                                     game.getUsedDartsNumber(), game.getBelow20(), game.getAbove20(), game.getAbove40(),
                                     game.getAbove60(), game.getAbove80(), game.getAbove100(), game.getAbove120(), 
                                     game.getAbove140(),game.getAbove60(), game.getP180());
        if(actualStat.getPlayedGames() == 0 || !game.getRemainigScore().equals("501")) {
            alert("You cannont save the statistic yet",scorePane);
            return;
        }
        db.updateStat(actualStat);
        clearScoreBoardElements();
        paneChange(scorePane,menuPane);
    }
    
    @FXML
    private void removeStatFromDB (){
        db.clearStat(data.getUserName());
        statNameList.getItems().clear();
        statList.getItems().clear();
        paneChange(statPane,menuPane);
    }
    @FXML
    private void statNameListAddition(String userName) {
        Stats showedStat = db.getOwnStats(userName);
        statNameList.getItems().add("Played Game:");
        statNameList.getItems().add("Avarage:");
        statNameList.getItems().add("Checkout % :");
        statNameList.getItems().add("Highest checkout: ");
        statNameList.getItems().add("20-:");
        statNameList.getItems().add("20+");
        statNameList.getItems().add("40+");
        statNameList.getItems().add("60+");
        statNameList.getItems().add("80+");
        statNameList.getItems().add("100+");
        statNameList.getItems().add("120+");
        statNameList.getItems().add("140+");
        statNameList.getItems().add("160+");
        statNameList.getItems().add("180");
        statList.getItems().add(showedStat.getPlayedGames());
        statList.getItems().add(df2.format(showedStat.getAvarage()));
        statList.getItems().add(showedStat.getchekoutPercentage());
        statList.getItems().add(showedStat.getHCo());
        statList.getItems().add(showedStat.getBelow20());
        statList.getItems().add(showedStat.getAbove20());
        statList.getItems().add(showedStat.getAbove40());
        statList.getItems().add(showedStat.getAbove60());
        statList.getItems().add(showedStat.getAbove80());
        statList.getItems().add(showedStat.getAbove100());
        statList.getItems().add(showedStat.getAbove120());
        statList.getItems().add(showedStat.getAbove140());
        statList.getItems().add(showedStat.getAbove160());
        statList.getItems().add(showedStat.getP180());
    }

    private void setCheckoutOpt(int actualScore){
        int option = game.giveCheckOutNumber(actualScore);
        if ( option == 1 ) {         
            checkout1.setDisable(true);
            radio3.setDisable(true);
        }
        if ( option == 2 ) {
            radio3.setDisable(true);
            radio2.setDisable(true);
            checkout1.setDisable(true);
            checkout2.setDisable(true);
        }
        if ( game.getCoHappened()){
                radio0.setDisable(true);
                checkoutHappenedPane.setVisible(true);
                checkoutHappenedPane.setDisable(false);
        } else {
                checkoutHappenedPane.setVisible(false);
                checkoutHappenedPane.setDisable(true);              
        }
        if (game.getPossibleCheckout() || game.getCoHappened())      
            paneChange(scorePane,checkoutPane);  
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
    
    public void clearScoreBoardElements(){
        outputLegAvg.setText("");
        gameAvg.setText("");
        outputHi.setText("");
        outputHCo.setText("");
        outputDouble.setText("");
    }
    
    public boolean isValidEmail(String email){
        if (email.length() > 3 && email.contains("@") && email.contains("."))
            return true;
 
        return false;
    }
    
    public boolean isvalidDate(String strDate) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern ( "dd.MM.uuuu" );
        try {
            LocalDate ld = LocalDate.parse ( strDate , f );
        } catch ( Exception e ) {
            return false;
        }
        return true;
    } 
        
    private void alert(String text, Pane pane) {
        Label label = new Label(text);
        Button alertButton = new Button("OK");
        VBox vbox = new VBox(label, alertButton);
        
        pane.setDisable(true);
        pane.setOpacity(0.4);
        vbox.setAlignment(Pos.CENTER);
        
        alertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setDisable(false);
                pane.setOpacity(1);
                vbox.setVisible(false);
            }
        });
        
        anchorPane.getChildren().add(vbox);
        anchorPane.setTopAnchor(vbox, 200.0);
        anchorPane.setLeftAnchor(vbox, 85.0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radio0.setToggleGroup(group);
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        checkout1.setToggleGroup(usedDartsGroup);
        checkout2.setToggleGroup(usedDartsGroup);
        checkout3.setToggleGroup(usedDartsGroup);
        countryList = countries.getCountry();
    }
    
    public void setActualLegDatas(){
        setLabelValue(outputRem, game.getRemainigScore());
        setLabelValue(outputHi, game.getHighestScore());
        setLabelValue(outputLegAvg, game.getActualAvg());    
    }
    
    public void setGameDatas(){
        setLabelValue(outputHCo, game.getLatestCo());
        setLabelValue(outputDouble, game.getchekoutPercentage());
        setLabelValue(gameAvg,String.valueOf(df2.format(game.getAvarage())));
    }
    
    public void initRadioButtons(){
        radio0.setDisable(false);
        radio1.setDisable(false);
        radio2.setDisable(false);
        radio3.setDisable(false);
        checkout1.setDisable(false);
        checkout2.setDisable(false);
        checkout3.setDisable(false);
    }
}
