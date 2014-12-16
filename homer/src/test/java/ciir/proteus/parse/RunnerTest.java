/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciir.proteus.parse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.h2.util.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bzifkin
 */
public class RunnerTest {

/*    
     @Test
     public void testTrimBlanks() throws Exception {

         System.out.println("**********************************TRIMMING BLANKS**************************");

     Runner instance = new Runner();
     List<Pages> pageList = new ArrayList<Pages>();

     NumScheme p1 = new NumScheme();
     p1.sequence.add(new Word("14", 1));
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();

     //***************************************************************************************************************************************************
     NumScheme p2 = new NumScheme();
     p2.sequence.add(new Word("9", 9));
     p2.sequence.add(new Word("11", 12));
     p2.sequence.add(new Word("15", 17));
     p2.sequence.add(new Word("18", 20));
     p2.sequence.add(new Word("19", 20));
     p2.sequence.add(new Word("20", 21));
     p2.sequence.add(new Word("20", 20));

     //*************************************************************************************************************************************************** 
     NumScheme p3 = new NumScheme();
     p3.sequence.add(new Word("14", 1));
     p3.addBlank();
     p3.addBlank();
     p3.addBlank();
     p3.addBlank();
     p3.addBlank();
     p3.addBlank();

     //***************************************************************************************************************************************************   
     NumScheme p4 = new NumScheme();
     p4.sequence.add(new Word("15", 3));
     p4.addBlank();
     p4.addBlank();
     p4.addBlank();
     p4.addBlank();
     p4.addBlank();
     p4.addBlank();

     NumScheme exp1 = instance.trimBlanks(p1, p2);
     NumScheme exp2 = instance.trimBlanks(p3, p4);
     System.out.println(exp1.toString());
     System.out.println(exp2.toString());
     assertEquals( 7, exp1.getAllBlanks());
     assertEquals(1, exp2.getAllBlanks());
     }
    */
  /*
     @Test
     public void testFillinBlanks() throws Exception {
  System.out.println("**********************************FILLING IN BLANKS**************************");
     
     Runner instance = new Runner();
     List<Pages> pageList = new ArrayList<Pages>();

     NumScheme p1 = new NumScheme();
     p1.sequence.add(new Word("I", 1));
     
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();  
     p1.sequence.add(new Word("IX", 9));
     p1.addBlank();    
     p1.sequence.add(new Word("11", 12));
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.sequence.add(new Word("15", 17));
     p1.addBlank();
     p1.addBlank();
     p1.sequence.add(new Word("18", 20));
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.addBlank();
     p1.sequence.add(new Word("33", 33));
     p1.sequence.add(new Word("34", 34));
     p1.sequence.add(new Word("35", 35));
        
     


     NumScheme result = instance.fillInBlanks(p1);
       
     System.out.println("the result\n" +result.toString());
      
     assertEquals(14, result.getTrueSize());
     }
    */ 
     
     /*
     @Test
     public void testStitchMyBitchUp() throws Exception {
  System.out.println("**********************************STITICHING**************************");

     Runner instance = new Runner();
     List<Pages> pageList = new ArrayList<Pages>();

     List<NumScheme> sList = new ArrayList<NumScheme>();

      
     NumScheme p = new NumScheme();
     p.sequence.add(new Word("i", 1));
     p.sequence.add(new Word("ii", 2));
     p.sequence.add(new Word("iii", 3));
     p.sequence.add(new Word("iv", 4));
     p.addBlank(); //5
     p.addBlank(); //6
     p.addBlank(); //7
     p.addBlank(); //8
     p.addBlank(); //9
     p.addBlank(); //10

      
        
     //***************************************************************************************************************************************************
     NumScheme p1 = new NumScheme();
     p1.sequence.add(new Word("1", 2));
     p1.sequence.add(new Word("2", 3));
     p1.sequence.add(new Word("3", 4));
     p1.sequence.add(new Word("4", 5));
     p1.addBlank(); //5
     p1.addBlank(); //6
     p1.addBlank(); //7
     p1.addBlank(); //8
     p1.addBlank(); //9
     p1.addBlank(); //10

     //***************************************************************************************************************************************************
     NumScheme p2 = new NumScheme();
     p2.sequence.add(new Word("9", 10));
     p2.sequence.add(new Word("11", 13));
     p2.sequence.add(new Word("15", 17));

     p2.sequence.add(new Word("19", 19));

     p2.sequence.add(new Word("20", 20));
     p2.addBlank();
     p2.addBlank();
     p2.addBlank();
     p2.addBlank();

     //*************************************************************************************************************************************************** 
     NumScheme p3 = new NumScheme();
     p3.sequence.add(new Word("14", 21));
     p3.sequence.add(new Word("15", 22));
     p3.sequence.add(new Word("16", 23));
     p3.sequence.add(new Word("17", 24));
     p3.addBlank();
     p3.addBlank();
     p3.addBlank();
     p3.addBlank();
     p3.addBlank();
     p3.addBlank();

     //***************************************************************************************************************************************************   
     NumScheme p4 = new NumScheme();
     p4.sequence.add(new Word("15", 3));
     p4.addBlank();
     p4.addBlank();
     p4.addBlank();
     p4.addBlank();
     p4.addBlank();
     p4.addBlank();
     sList.add(p1);
     sList.add(p2);
     sList.add(p3);
     sList.add(p4);

     for (NumScheme ns : sList) {
     ns.arabSeq = true;
     for (Word w : ns.sequence) {
     if (!w.isBlank) {
     w.isArabic = true;
     }
     }

     }

     //sList.add(p);
     NumScheme exp1 = instance.stitchMyBitchUp(sList);
     System.out.println("best guess------------------------\n " + exp1.toString());

     assertEquals(13, exp1.getTrueSize());

     }
     */
     @Test
     public void testMinDistance() throws Exception {
Runner instance  = new Runner();
String st1 = "Bitch";
String st2 = "Stitches";

String st3 = "meager meat";
String st4 = "meagermeat";

String st5 = "BEN";
String st6 = "ben";

String st7 = "c@$h!";
String st8 ="cash!";
String st9 = "f@$h!";

int res1 = instance.minDistance(st1,st2);
int res2 = instance.minDistance(st3,st4);        
int res3 = instance.minDistance(st5,st6);       
int res4 = instance.minDistance(st7,st8);
int res5 = instance.minDistance(st7,st9);

assertEquals(4, res1);
assertEquals(1, res2);
assertEquals(0, res3);
assertEquals(2, res4);
assertEquals(1, res5);
}
     
    /*
     @Test
     public void testIsArarbicWord() throws Exception {
     String yeahright = "<BODY><OBJECT  height=\"1650\" width=\"1275\">"
     + "</OBJECT></BODY>";
     InputStream is = new ByteArrayInputStream(yeahright.getBytes());

     Runner instance = new Runner(is);

     Word testWord1 = new Word("grease");
     Word testWord2 = new Word("i");
     Word testWord3 = new Word("1");
     Word testWord4 = new Word("1z");
     Word testWord5 = new Word("23");
     Word testWord6 = new Word("2 3");

     assertEquals(false, instance.isArabicWord(testWord1.text));
     assertEquals(false, instance.isArabicWord(testWord2.text));
     assertEquals(true, instance.isArabicWord(testWord3.text));
     assertEquals(false, instance.isArabicWord(testWord4.text));
     assertEquals(true, instance.isArabicWord(testWord5.text));
     assertEquals(false, instance.isArabicWord(testWord6.text));

     }

     */
    /*
    
     @Test
     public void testSortWordsAndWords() throws Exception {
        
     // Tested this, works well, test date 12/11
        
         
     Runner xt = new Runner();
     List<Pages> pList = new ArrayList<Pages>();
     Pages p = new Pages();
     Token t1 = new Token("wash me", 1);
     Token t2 = new Token("wash me", 1);
     Token t3 = new Token("wash me", 1);
     Token t4 = new Token("wash me", 1);
     Token t5 = new Token("wash me", 1);
     Token t6 = new Token("1", 1);
     Token t7 = new Token("2", 1);
     Token t8 = new Token("3", 1);
     Token t9 = new Token("4", 1);
     Token t10 = new Token("5", 1);
     Token t11 = new Token("i", 1);
     Token t12 = new Token("II", 1);
     Token t13 = new Token("IIi", 1);
     Token t14 = new Token("IV", 1);
     Token t15 = new Token("V", 1);
     Token t16 = new Token("10.", 1);
     Token t17 = new Token("wash me.", 1);
     Token t18 = new Token("wash m3", 1);
     Token t19 = new Token("3a", 1);
     Token t20 = new Token("wash me", 1);
     p.tokensOnPage.add(t1);
     p.tokensOnPage.add(t2);
     p.tokensOnPage.add(t3);
     p.tokensOnPage.add(t4);
     p.tokensOnPage.add(t5);
     p.tokensOnPage.add(t6);
     p.tokensOnPage.add(t7);
     p.tokensOnPage.add(t8);
     p.tokensOnPage.add(t9);
     p.tokensOnPage.add(t10);
     p.tokensOnPage.add(t11);
     p.tokensOnPage.add(t12);
     p.tokensOnPage.add(t13);
     p.tokensOnPage.add(t14);
     p.tokensOnPage.add(t15);
     p.tokensOnPage.add(t16);
     p.tokensOnPage.add(t17);
     p.tokensOnPage.add(t18);
     p.tokensOnPage.add(t19);
     p.tokensOnPage.add(t20);
     pList.add(p);

     xt.sortWordsAndWords(pList);
     List<Word> words = xt.wordList;
     List<Word> nums = xt.numList;
     String out = "";
     String put = "";
     for (Word w : words) {
     out = out + w.toString();
     }
     for (Word n : nums) {
     put = put + n.toString();
     }

     System.out.println("This is the words list:" + out);
     System.out.println("This is the nums list:" + put);

     assertEquals(10, words.size());
     assertEquals(10, nums.size());

     }
    
    
    
    

     @Test
     public void testSetBlankParkFound() throws Exception {

     Runner instance = new Runner();

     ArrayList<NumScheme> schList = new ArrayList<NumScheme>();

     // these first two schemes test that its sets the parking lot correctly
     NumScheme ns1 = new NumScheme();
     Word n1 = new Word("1", 1);
     Word n2 = new Word();
     n2.isBlank = true;
     Word n3 = new Word();
     n3.isBlank = true;

     ns1.sequence.add(n1);
     ns1.sequence.add(n2);
     ns1.sequence.add(n3);
     ns1.foundElementOnCurrPage = true;
     schList.add(ns1);
     //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

     NumScheme ns2 = new NumScheme();
     Word n4 = new Word("1", 1);
     Word n5 = new Word();
     n5.isBlank = true;
     Word n6 = new Word();
     n6.isBlank = true;

     ns2.sequence.add(n4);
     ns2.sequence.add(n5);
     ns2.sequence.add(n6);
     ns2.foundElementOnCurrPage = false;
     schList.add(ns2);

     //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     //these two scheme will test that it is properly inserting blanks
     NumScheme ns3 = new NumScheme();
     Word n7 = new Word("1", 1);
     Word n8 = new Word("2", 2);

     ns3.sequence.add(n7);
     ns3.sequence.add(n8);

     ns3.foundElementOnCurrPage = false;
     schList.add(ns3);
     //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     NumScheme ns4 = new NumScheme();
     Word n9 = new Word("1", 1);
     Word n10 = new Word("2", 2);

     ns4.sequence.add(n9);
     ns4.sequence.add(n10);

     ns4.foundElementOnCurrPage = true;
     schList.add(ns4);
        
     ArrayList<NumScheme> testList = instance.setBlankParkFound(schList);
     String out = "";
     for(NumScheme ns : testList){
     out = out + ns.toString() + "\n" + "this scheme is in the parking lot: " + ns.inParkLot + "\n" + "this scheme has found an element on the current page: " + ns.foundElementOnCurrPage;
     }
         
     System.out.println(out);
     assertEquals(4, testList.size());
     assertEquals(true, testList.get(0).inParkLot);
     assertEquals(true, testList.get(1).inParkLot);
     assertEquals(3, testList.get(2).sequence.size());
     assertEquals(2, testList.get(3).sequence.size());
     assertEquals(false, testList.get(0).foundElementOnCurrPage);
     assertEquals(false, testList.get(1).foundElementOnCurrPage);
     assertEquals(false, testList.get(2).foundElementOnCurrPage);
     assertEquals(false, testList.get(3).foundElementOnCurrPage);
     }
     */
     
     
   // @Test
   // public void testSortAndSearchForSchemes() throws Exception {

        /*Here we want to test a few things
         1) that nums are added to the proper seqs
         2) that blanks/park/found are set appropriately via above test 
         3) that y
         **********************Tested dec-13, string out put seems right but keep eyes on it*********************8888888
         */
        
        /*
        Runner instance = new Runner();
        List<Pages> pageList = new ArrayList<Pages>();

        Word w1 = new Word("VIII", 16);
        Word w2 = new Word("IX", 17);
        Word w3 = new Word("X", 18);
        Word w4 = new Word("xi", 19);
        Word w5 = new Word("xII", 20);
        w1.isRoman = true;
        w2.isRoman = true;
        w3.isRoman = true;
        w4.isRoman = true;
        w5.isRoman = true;
        Word w6 = new Word("4", 24);
        Word w7 = new Word("5", 27);
        Word w8 = new Word("6", 28);
        Word w9 = new Word("7", 29);
        Word w10 = new Word("8", 30);
        Word w11 = new Word("9", 31);
        Word w12 = new Word("10", 32);
        Word w13 = new Word("12", 34);
        Word w14 = new Word("13", 35);
        Word w15 = new Word("15", 37);
        Word w16 = new Word("16", 38);
        Word w17 = new Word("17", 39);
        Word w18 = new Word("18", 40);
        Word w19 = new Word("20", 42);
        Word w20 = new Word("21", 43);
        Word w21 = new Word("22", 44);
        Word w22 = new Word("23", 45);
        Word w23 = new Word("24", 46);
        Word w24 = new Word("25", 47);
        Word w25 = new Word("26", 48);
        Word w26 = new Word("27", 49);
        Word w27 = new Word("30", 50);
        Word w28 = new Word("31", 51);
        Word w29 = new Word("32", 52);

        w6.isArabic = true;
        w7.isArabic = true;
        w8.isArabic = true;
        w9.isArabic = true;
        w10.isArabic = true;
        w11.isArabic = true;
        w12.isArabic = true;
        w13.isArabic = true;
        w14.isArabic = true;
        w15.isArabic = true;
        w16.isArabic = true;
        w17.isArabic = true;
        w18.isArabic = true;
        w19.isArabic = true;
        w20.isArabic = true;
        w21.isArabic = true;
        w22.isArabic = true;
        w23.isArabic = true;
        w24.isArabic = true;
        w25.isArabic = true;
        w26.isArabic = true;
        w27.isArabic = true;
        w28.isArabic = true;
        w29.isArabic = true;
        //------------------------------------------------------------------------------------------------------------------------------
        Pages p1 = new Pages();
        p1.wordsOnPage.add(w1);
        Pages p2 = new Pages();
        p2.wordsOnPage.add(w2);
        Pages p3 = new Pages();
        p3.wordsOnPage.add(w3);
        Pages p4 = new Pages();
        p4.wordsOnPage.add(w4);
        Pages p5 = new Pages();
        p5.wordsOnPage.add(w5);
        Pages p6 = new Pages();
        p6.wordsOnPage.add(w6);
        Pages p7 = new Pages();
        p7.wordsOnPage.add(w7);
        Pages p8 = new Pages();
        p8.wordsOnPage.add(w8);
        Pages p9 = new Pages();
        p9.wordsOnPage.add(w9);
        Pages p10 = new Pages();
        p10.wordsOnPage.add(w10);
        Pages p11 = new Pages();
        p11.wordsOnPage.add(w11);
        Pages p12 = new Pages();
        p12.wordsOnPage.add(w12);
        Pages p30= new Pages();
        Pages p13 = new Pages();
        p13.wordsOnPage.add(w13);
        Pages p14 = new Pages();
        p14.wordsOnPage.add(w14);
        Pages p31= new Pages();
        Pages p15 = new Pages();
        p15.wordsOnPage.add(w15);
        Pages p16 = new Pages();
        p16.wordsOnPage.add(w16);
        Pages p17 = new Pages();
        p17.wordsOnPage.add(w17);
        Pages p18 = new Pages();
        p18.wordsOnPage.add(w18);
        Pages p19 = new Pages();
        p19.wordsOnPage.add(w19);
        Pages p20 = new Pages();
        p20.wordsOnPage.add(w20);
        Pages p21 = new Pages();
        p21.wordsOnPage.add(w21);
        Pages p22 = new Pages();
        p22.wordsOnPage.add(w22);
        Pages p23 = new Pages();
        p23.wordsOnPage.add(w23);
        Pages p24 = new Pages();
        p24.wordsOnPage.add(w24);
        Pages p25 = new Pages();
        p25.wordsOnPage.add(w25);
        Pages p26 = new Pages();
        p26.wordsOnPage.add(w26);
        Pages p27 = new Pages();
        p27.wordsOnPage.add(w27);
        Pages p28 = new Pages();
        p28.wordsOnPage.add(w28);
        Pages p29 = new Pages();
        p29.wordsOnPage.add(w29);
        pageList.add(p1);
        pageList.add(p2);
        pageList.add(p3);
        pageList.add(p4);
        pageList.add(p5);
        pageList.add(p6);
        pageList.add(p7);
        pageList.add(p8);
        pageList.add(p9);
        pageList.add(p10);
        pageList.add(p11);
        pageList.add(p12);
        pageList.add(p30);
        pageList.add(p13);
        pageList.add(p14);
        pageList.add(p31);
        pageList.add(p15);
        pageList.add(p16);
        pageList.add(p17);
        pageList.add(p18);
        pageList.add(p19);
        pageList.add(p20);
        pageList.add(p21);
        pageList.add(p22);
        pageList.add(p23);
        pageList.add(p24);
        pageList.add(p25);
        pageList.add(p26);
        pageList.add(p27);
        pageList.add(p28);
        pageList.add(p29);

        ArrayList<NumScheme> test = Runner.sortAndSearchForSchemes(pageList);
        for (NumScheme ben : test) {
            System.out.println(ben.toString());
            System.out.println("------------------");
        }
        assertEquals(4, test.size());

    }
    */
    /*
    
     @Test
     public void testFindBegining() throws Exception {
        
     
     Runner instance = new Runner();
     List<NumScheme> pageList = new ArrayList<NumScheme>();
        
     Word start = new Word("15", 15);
        
     List<NumScheme> loss = new ArrayList<NumScheme>();
     
     NumScheme p1 = new NumScheme();
     p1.sequence.add(new Word("14", 1));
     p1.sequence.add(new Word("14", 16));
     p1.sequence.add(new Word("14", 13));
     p1.sequence.add(new Word("14", 14));
     p1.sequence.add(new Word("13", 13));
     // p1.sequence.add(new Word("12", 10));
     p1.sequence.add(new Word("11", 11));
     //p1.sequence.add(new Word("12", 11));
     p1.sequence.add(new Word("10", 10));

     loss.add(p1);
     //***************************************************************************************************************************************************
        
        
     NumScheme p2 = new NumScheme();
     p2.sequence.add(new Word("9", 9));
     p2.sequence.add(new Word("11", 12));
     p2.sequence.add(new Word("15", 17));
     p2.sequence.add(new Word("18", 20));
     p2.sequence.add(new Word("19", 20));
     p2.sequence.add(new Word("20", 21));
     p2.sequence.add(new Word("20", 20));
        
     loss.add(p2);
     //*************************************************************************************************************************************************** 
     NumScheme p3 = new NumScheme();
     p3.sequence.add(new Word("26", 26));
     p3.sequence.add(new Word("28", 31));
     p3.sequence.add(new Word("29", 32));
     p3.sequence.add(new Word("28", 27));
      
        
     loss.add(p3);


     NumScheme complete  = instance.findBegining(loss);

     System.out.println(complete.toString());
     assertEquals(7, complete.sequence.size());
     }
     */

}
