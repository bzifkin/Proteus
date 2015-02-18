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
     public void testMinEditDistance() throws Exception {
Runner instance  = new Runner();

Word st1 = new Word("Bitch");

Word st2 = new Word("Stitches");

Word st3 = new Word("meager meat");

Word st4 = new Word("meagermeat");

Word st5 = new Word("BEN");

Word st6 = new Word("ben");

Word st7 = new Word("c@$h!");
Word st8 = new Word("cash!");
Word st9 = new Word("f@$h!");

int res1 = instance.minEditDistance(st1,st2);
int res2 = instance.minEditDistance(st3,st4);        
int res3 = instance.minEditDistance(st5,st6);       
int res4 = instance.minEditDistance(st7,st8);
int res5 = instance.minEditDistance(st7,st9);

assertEquals(4, res1);
assertEquals(1, res2);
assertEquals(0, res3);
assertEquals(2, res4);
assertEquals(1, res5);
}
     @Test
     public void testBuildHeaderOnPage() throws Exception {
Runner instance  = new Runner();

Pages p1 = new Pages(1);
p1.qInch= 600;


Word w1 = new Word("heya",1); //low b/c distanc
w1.xOne = 100;
w1.yOne =0;
w1.xTwo = 0;
w1.yTwo = 0;
Word w2 = new Word("heya",2); //low b/c strength 
w2.xOne = 200;
w2.yOne =0;
w2.xTwo = 0;
w2.yTwo = 0;
Word w3 = new Word("heya",3); // should be middle 
w3.xOne = 300;
w3.yOne =0;
w3.xTwo = 0;
w3.yTwo = 0;
Word w4 = new Word("heya",4); //should be high
w4.xOne = 400;
w4.yOne =0;
w4.xTwo = 0;
w4.yTwo = 0;
Word w5 = new Word("hay",5); //we'll see
w5.xOne = 350;
w5.yOne =0;
w5.xTwo = 0;
w5.yTwo = 0;
p1.wordsOnPage.add(w1);
p1.wordsOnPage.add(w2);
p1.wordsOnPage.add(w3);
p1.wordsOnPage.add(w4);
p1.wordsOnPage.add(w5);


//-----------------------------------------------
Pages p2 = new Pages(2);
p2.qInch= 150;


Word w6 = new Word("heya",6);
w6.xOne = 161;
w6.yOne =61;
w6.xTwo = 61;
w6.yTwo = 61;
Word w7 = new Word("hey",7);
w7.xOne = 200;
w7.yOne =61;
w7.xTwo = 61;
w7.yTwo = 61;
Word w8 = new Word("huya",8);
w8.xOne = 300;
w8.yOne =61;
w8.xTwo = 61;
w8.yTwo = 61;
Word w9 = new Word("heya",9);
w9.xOne = 400;
w9.yOne =61;
w9.xTwo = 61;
w9.yTwo = 61;
Word w10 = new Word("heya",10);
w10.xOne = 408;
w10.yOne =61;
w10.xTwo = 61;
w10.yTwo = 61;
p2.wordsOnPage.add(w6);
p2.wordsOnPage.add(w7);
p2.wordsOnPage.add(w8);
p2.wordsOnPage.add(w9);
p2.wordsOnPage.add(w10);
//-------------------------------------------------------
Pages p3 = new Pages(3);
p3.qInch= 150;


Word w11 = new Word("heya",11); //low b/c both
w11.xOne = 39;
w11.yOne = 122;
w11.xTwo = 122;
w11.yTwo = 122;
Word w12 = new Word("benjamin",12); //middle
w12.xOne =200;
w12.yOne = 122;
w12.xTwo = 122;
w12.yTwo = 122;
Word w13 = new Word("meya",13);//midle
w13.xOne = 300;
w13.yOne = 122;
w13.xTwo = 122;
w13.yTwo = 122;
Word w14 = new Word("hey",14);
w14.xOne =400;
w14.yOne = 122;
w14.xTwo = 122;
w14.yTwo = 122;
Word w15 = new Word("raging",15);
w15.xOne= 310;
w15.yOne = 122;
w15.xTwo = 122;
w15.yTwo = 122;
p3.wordsOnPage.add(w11);
p3.wordsOnPage.add(w12);
p3.wordsOnPage.add(w13);
p3.wordsOnPage.add(w14);
p3.wordsOnPage.add(w15);
//----------------------------------------------
Pages p4 = new Pages(4);
p4.qInch= 150;


Word w16 = new Word("heya",16);
w16.xOne = 100;
w16.yOne = 183;
w16.xTwo = 183;
w16.yTwo = 183;
Word w17 = new Word("benmjain",17);
w17.xOne = 200;
w17.yOne = 183;
w17.xTwo = 183;
w17.yTwo = 183;
Word w18 = new Word("meta",18);
w18.xOne = 300;
w18.yOne = 183;
w18.xTwo = 183;
w18.yTwo = 183;
Word w19 = new Word("ham",19);
w19.xOne = 440;
w19.yOne = 183;
w19.xTwo = 183;
w19.yTwo = 183;
Word w20 = new Word("gauging",20);
w20.xOne = 320;
w20.yOne = 183;
w20.xTwo = 183;
w20.yTwo = 183;

p4.wordsOnPage.add(w16);
p4.wordsOnPage.add(w17);
p4.wordsOnPage.add(w18);
p4.wordsOnPage.add(w19);
p4.wordsOnPage.add(w20);
//-----------------------------------------------------
Pages p5 = new Pages(5);
p5.qInch= 150;



Word w21 = new Word("dick",21);
w21.xOne = 99;
w21.yOne = 244;
w21.xTwo = 244;
w21.yTwo = 244;
Word w22 = new Word("benjami",22);
w22.xOne = 200;
w22.yOne = 244;
w22.xTwo = 244;
w22.yTwo = 244;
Word w23 = new Word("mesa",23);
w23.xOne = 300;
w23.yOne = 244;
w23.xTwo = 244;
w23.yTwo = 244;
Word w24 = new Word("gey",24);
w24.xOne = 424;
w24.yOne = 244;
w24.xTwo = 244;
w24.yTwo = 244;
Word w25 = new Word("paging",25);
w25.xOne = 330;
w25.yOne = 244;
w25.xTwo = 244;
w25.yTwo = 244;
p5.wordsOnPage.add(w21);
p5.wordsOnPage.add(w22);
p5.wordsOnPage.add(w23);
p5.wordsOnPage.add(w24);
p5.wordsOnPage.add(w25);

ArrayList<Pages> plist = new ArrayList<Pages>();
plist.add(p1);
plist.add(p2);
plist.add(p3);
plist.add(p4);
plist.add(p5);

instance.buildHeaderOnPage(p1, plist);
instance.buildHeaderOnPage(p2, plist);
instance.buildHeaderOnPage(p3, plist);
instance.buildHeaderOnPage(p4, plist);
instance.buildHeaderOnPage(p5, plist);


   for(Pages p : plist){
       System.out.println("for page " + p.indexNum + "\n");
       for(Word w: p.wordsOnPage){
           System.out.println(w.toString() + " STRENGTH " + w.strength);
       
       }
       System.out.println("------------------------------");
   }
/*
System.out.println("for page p1 LIKELY has " + p1.likely.toString() + "\n MEDIUM has " + p1.maybe.toString() + "\n and UNLIKELY has " + p1.unlikely.toString() + "\n");
    System.out.println("-------------------------------\n");
    System.out.println("for page p2 LIKELY has " + p2.likely.toString() + "\n MEDIUM has " + p2.maybe.toString() + "\n and UNLIKELY has " + p2.unlikely.toString() + "\n");
    System.out.println("-------------------------------\n");
    System.out.println("for page p3 LIKELY has " + p3.likely.toString() + "\n MEDIUM has " + p3.maybe.toString() + "\n and UNLIKELY has " + p3.unlikely.toString() + "\n");
    System.out.println("-------------------------------\n");
    System.out.println("for page p4 LIKELY has " + p4.likely.toString() + "\n MEDIUM has " + p4.maybe.toString() + "\n and UNLIKELY has " + p4.unlikely.toString() + "\n");
    System.out.println("-------------------------------\n");
    System.out.println("for page p5 LIKELY has " + p5.likely.toString() + "\n MEDIUM has " + p5.maybe.toString() + "\n and UNLIKELY has " + p5.unlikely.toString() + "\n");
    System.out.println("-------------------------------\n");
*/
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
