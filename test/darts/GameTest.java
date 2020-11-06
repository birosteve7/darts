/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darts;

import java.util.ArrayList;
import javafx.scene.control.Label;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author István
 */

public class GameTest {
    
    public GameTest() {
    }
    private Game game;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        game = new Game();
    }
    
    @After
    public void tearDown() {
        game.initGame();
    }

    /**
     * Test of initialize method, of class DartFXMLVieWController.
     */
    @Test
    public void testCalculation() {
        int expResult = 321;
        game.calculation(180);
        int result = Integer.valueOf(game.getRemainigScore());
        assertEquals("testCalculation: ",expResult, result);
    }
    
   @Test
    public void testCalculation1() {
        int expResult = 195;
        game.calculation(180);
        game.calculation(100);
        game.calculation(26);
        int result = Integer.valueOf(game.getRemainigScore());
        assertEquals("testCalculation: ",expResult, result);
    }
    
    
    @Test
    public void testGetActualAvg(){
        double expResult = 113.33;
        game.addScorelist(180);
        game.addScorelist(60);
        game.addScorelist(100);
        double result = Double.valueOf(game.getActualAvg());
        assertEquals("testAvarage: ",expResult, result, 0);
    }
    
    @Test
    public void testCheckoutTable(){
        String expResult = "T20   T20   D16";
        game.calculation(180);
        game.calculation(140);
        game.calculation(29);
        String resString = game.checkOutTable();
        assertEquals("testCheckoutTable: ",expResult, resString);  
    }
    
    @Test
    public void testRange(){
        int expResult = 2;
        game.setRange(100);
        game.setRange(119);
        int result = game.getAbove100();
        assertEquals("testRange: ",expResult, result);
    }
    
    @Test
    public void testRange2(){
        int expResult180 = 2;
        int expResult140 = 1;
        game.setRange(180);
        game.setRange(180);
        game.setRange(141);
        int result180 = game.getP180();
        int result140 = game.getAbove140();
        int result160 = game.getAbove160();
        assertEquals("testRange of 180: ",expResult180, result180);
        assertEquals("testRange of 140: ",expResult140, result140);
        assertEquals("testRange of 160: ",0, result160);
    }
    
    @Test
    public void testGiveCheckOutNumber(){
        int expResult = 1;
        game.setRemainingScore(52);
        int result = game.giveCheckOutNumber(12);
        boolean possibleCheckout = game.getPossibleCheckout();
        assertEquals("testGiveCheckOutNumber: ",expResult, result);
        assertEquals("testGiveCheckOutNumber pssCheckout: ",true, possibleCheckout);
    }
    
    @Test
    public void testGiveCheckOutNumber1(){
        int expResult = 2;
        game.setRemainingScore(101);
        int result = game.giveCheckOutNumber(65);
        boolean possibleCheckout = game.getPossibleCheckout();
        boolean coHappened = game.getCoHappened();
        assertEquals("testGiveCheckOutNumber: ",expResult, result);
        assertEquals("testGiveCheckOutNumber pssCheckout: ",true, possibleCheckout);
        assertEquals("testGiveCheckOutNumber coHappaened: ",false, coHappened);
    }
    
    @Test
    public void testGiveCheckOutNumber3(){
        int expResult = 2;
        game.setRemainingScore(101);
        int result = game.giveCheckOutNumber(101);
        boolean possibleCheckout = game.getPossibleCheckout();
        boolean coHappened = game.getCoHappened();
        assertEquals("testGiveCheckOutNumber: ",expResult, result);
        assertEquals("testGiveCheckOutNumber pssCheckout: ",true, possibleCheckout);
        assertEquals("testGiveCheckOutNumber coHappaened: ",true, coHappened);
    }
    
    @Test
    public void testCheckOutHappened(){
        int expResult = 141;
        game.setHighestCo(80);
        game.addScorelist(180);
        game.addScorelist(180);
        game.addScorelist(141);
        game.checkOutHappened();
        assertEquals("Highest checkout: ", expResult ,game.getHighestCo());
    }
    
    @Test
    public void testCheckOutHappened1(){
        int expResult = 80;
        game.setHighestCo(80);
        game.addScorelist(180);
        game.addScorelist(180);
        game.addScorelist(62);
        game.addScorelist(79);
        game.checkOutHappened();
        assertEquals("Highest checkout: ", expResult ,game.getHighestCo());
    }   
    
    @Test
    public void testRemoveLastScore(){
        String expResult = "There is nothing to delete";
        String result = game.removeLastScore();
        assertEquals("testRemoveLastScore: ", expResult, result);
    }
    
    @Test
    public void testRemoveLastScore1(){
        String expResult = "You can only use once the 'Undo' button";
        game.setIsSecondUndo(true);
        String result = game.removeLastScore();
        assertEquals("testRemoveLastScore: ", expResult, result);
    }
    
    @Test
    public void testRemoveLastScore2(){
        String expResult = "";
        game.addScorelist(60);
        game.addScorelist(180);
        game.addScorelist(120);
        game.addScorelist(26);
        String result = game.removeLastScore();
        ArrayList scoreList = game.getScoreList();
        int scoreListSize = game.getScoreListSize(); 
        assertEquals("testRemoveLastScore: ", expResult, result);
        assertEquals("testRemoveLastScore scoreList size: ",scoreListSize , 3);
        assertEquals("testRemoveLastScore last Score: ", scoreList.get(scoreListSize-1) , 120);
    }
    
    @Test 
    public void testIsValidCheckoutData(){
        boolean expResult = false;
        game.setLatestScore(60);
        
        assertEquals("IsValidCheckoutData: ", expResult, game.isValidCheckoutData(1, 1));
    }
    
    
    @Test 
    public void testIsValidCheckoutData2(){
        boolean expResult = false;
        game.setLatestScore(141);
        
        assertEquals("IsValidCheckoutData: ", expResult, game.isValidCheckoutData(3, 3));
    }
    
    @Test 
    public void testIsValidCheckoutData3(){
        boolean expResult = true;
        game.setLatestScore(141);
        
        assertEquals("IsValidCheckoutData: ", expResult, game.isValidCheckoutData(1, 3));
    }
    
    @Test 
    public void testIsValidCheckoutData4(){
        boolean expResult = true;
        game.setLatestScore(28);
        
        assertEquals("IsValidCheckoutData: ", expResult, game.isValidCheckoutData(1, 1));
    }
    
    @Test 
    public void testIsValidCheckoutData5(){
        boolean expResult = false;
        game.setLatestScore(29);
        
        assertEquals("IsValidCheckoutData: ", expResult, game.isValidCheckoutData(1, 1));
    }
    
}
