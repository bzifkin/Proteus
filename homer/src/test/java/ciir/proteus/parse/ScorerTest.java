/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ciir.proteus.parse;


import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bzifkin
 */
public class ScorerTest {

    public ScorerTest() {
    }

    /**
     * Test of compareAndScore method, of class Scorer.
     */
    @Test
    public void testCompareAndScore_List_List() {
        System.out.println("compareAndScore");
        List<String[]> obs = new ArrayList<String[]>();

        String[] obs1 = {"I", "1"};
        String[] obs2 = {"II", "2"};
        String[] obs3 = {"III", "3"};
        String[] obs4 = {"cont", "8"};
        String[] obs5 = {"9", "9"};
        String[] obs6 = {"x", "10"};
        String[] obs7 = {"xi", "11"};
        
        String[] obs9 = {"i", "13"};
        String[] obs8 = {"blank", "14"};
        
        obs.add(obs1);
        obs.add(obs2);
        obs.add(obs3);
        obs.add(obs4);
        obs.add(obs5);
        obs.add(obs6);
        obs.add(obs7);
        obs.add(obs8);
        obs.add(obs9);
       

        List<String[]> guess = new ArrayList<String[]>();

        String[] guess1 = {"I", "1"};
        String[] guess2 = {"II", "2"};
        String[] guess3 = {"III", "2"};
        String[] guess4 = {"4", "4"};
        String[] guess5 = {"V", "5"};
        String[] guess6 = {"VI", "6"};
        String[] guess7 = {"VII", "7"};
        String[] guess8 = {"blank", "14"};
        String[] guess9 = {"VIII", "8"};
        String[] guess10 = {"9", "9"};
        guess.add(guess1);
        guess.add(guess2);
        guess.add(guess3);
        guess.add(guess4);
        guess.add(guess5);
        guess.add(guess6);
        guess.add(guess7);
        guess.add(guess8);
        guess.add(guess9);
        guess.add(guess10);

        System.out.println(Scorer.compareAndScore(obs, guess));

    }

    @Test
    public void testInTheList() {
        System.out.println("inTheList");
        List<String[]> obs = new ArrayList<String[]>();

        String[] obs1 = {"1", "1"};
        String[] obs2 = {"2", "2"};
        String[] obs3 = {"3", "3"};
        String[] obs4 = {"cont", "8"};
        String[] obs5 = {"9", "9"};
        String[] obs6 = {"10", "10"};
        String[] obs7 = {"11", "11"};
        String[] obs8 = {"blank", "2"};
        String[] obs9 = {"1", "13"};
        String[] obs10 = {"end", "14"};
        obs.add(obs1);
        obs.add(obs2);
        obs.add(obs3);
        obs.add(obs4);
        obs.add(obs5);
        obs.add(obs6);
        obs.add(obs7);
        obs.add(obs8);
        obs.add(obs9);
        obs.add(obs10);

        String[] guess1 = {"1", "1"};
        String[] guess2 = {"1", "2"};
        String[] guess3 = {"blank", "2"};
        String[] guess4 = {"2", "blank"};

        boolean test1 = Scorer.inTheList(obs, guess1);
        boolean test2 = Scorer.inTheList(obs, guess2);
        boolean test3 = Scorer.inTheList(obs, guess3);
        boolean test4 = Scorer.inTheList(obs, guess4);
        
        assertEquals(test1, true);
        assertEquals(test2, false);
        assertEquals(test3, true);
        assertEquals(test4, false);

    }
    
    
    @Test
    public void testExpandList() {
        System.out.println("expand list");
        ArrayList<String[]> arab = new ArrayList<String[]>();

        String[] obs1 = {"1", "1"};
         String[] obs2 = {"2", "2"};
        String[] obs3 = {"cont", "8"};
        String[] obs4 = {"9", "9"};
        String[] obs5 = {"cont", "17"};
       //String[] obs6 = {"18", "18"};
        arab.add(obs1);
        arab.add(obs2);
        arab.add(obs3);
        arab.add(obs4);
        arab.add(obs5);
       // arab.add(obs6);
        
        
        ArrayList<String[]> roman = new ArrayList<String[]>();

        String[] rom1 = {"I", "1"};
        String[] rom2 = {"II", "2"};
        String[] rom3 = {"cont", "8"};
        String[] rom4 = {"9", "9"};
        
        String[] rom5 = {"cont", "17"};
      
        roman.add(rom1);
        roman.add(rom2);
        roman.add(rom3);
        roman.add(rom4);
        roman.add(rom5);
        

       
        List<String[]> arrec =Scorer.expandList(arab);
        
        String out ="";
        for(String[] stray : arrec){
        out = out + stray[0] + "," + stray[1] + "\n";
        }
        System.out.println(out);
       
       
       List<String[]> romrec = Scorer.expandList(roman);
       String out1 ="";
        for(String[] stray : romrec){
        out1 = out1 + stray[0] + "," + stray[1] + "\n";
        }
        System.out.println(out1);
        
        assertEquals(17,arrec.size());
        assertEquals(17,romrec.size());
    }

    @Test
    public void testFindIndOfCont() {
        List<String[]> obs = new ArrayList<String[]>();

        String[] obs1 = {"1", "1"};
        String[] obs2 = {"conti", "2"};
        String[] obs3 = {"3", "3"};
        String[] obs4 = {"cont", "8"};
        String[] obs5 = {"9", "9"};
        String[] obs6 = {"10", "cont"};
        String[] obs7 = {"cont", "11"};
        String[] obs8 = {"cont", "1"};
        String[] obs9 = {"1", "2"};
        String[] obs10 = {"end", "1"};
        obs.add(obs1);
        obs.add(obs2);
        obs.add(obs3);
        obs.add(obs4);
        obs.add(obs5);
        obs.add(obs6);
        obs.add(obs7);
        obs.add(obs8);
        obs.add(obs9);
        obs.add(obs10);

        ArrayList<Integer> test;
        test = Scorer.findIndOfCont(obs);
        ArrayList<Integer> corr = new ArrayList<Integer>();
        corr.add(3);
        corr.add(6);
        corr.add(7);

        assertEquals(3, test.size());
        assertEquals(corr, test);
    }

 

    /**
     * Test of isInteger method, of class Scorer.
     *
     * @Test public void testIsInteger() { System.out.println("isInteger");
     * String s = ""; boolean expResult = false; boolean result =
     * Scorer.isInteger(s); assertEquals(expResult, result); // TODO review the
     * generated test code and remove the default call to fail. fail("The test
     * case is a prototype."); }
     */
}
