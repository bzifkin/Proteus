/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ciir.proteus.parse;

import java.util.ArrayList;
import java.util.Collections;
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
public class WordTest {
    

   /*
    @Test
    public void testGetPageNum() {
        System.out.println("getPageNum");
        Word instance = new Word("1", 1);
        Word instance2 = new Word("2", 1);
        Word instance3 = new Word("ii", 1);
        Word instance4 = new Word("xi", 1);
     
        int exp1 = 1;
        int exp2 = 2;
        int exp3 = 11;
        int result1 = instance.getPageNum();
        int result2 = instance2.getPageNum();
        int result3 = instance3.getPageNum();
        int result4 = instance4.getPageNum();
        
        
        
        assertEquals(exp1, result1);
        assertEquals(exp2, result2);
        assertEquals(exp2, result3);
        assertEquals(exp3, result4);
        
       
    }
    */
    @Test
    public void testComparator() {
        List<Word> blah = new ArrayList<Word>();
        Word instance = new Word("hey", 1);
        Word instance2 = new Word("you", 2);
        Word instance3 = new Word("whats", 3);
        Word instance4 = new Word("going", 4);
         Word instance5 = new Word("on", 5);
     
         instance.count =1;
         instance.strength = 20.0;
         instance.xOne = 100;
        
        instance2.count =6;
        instance2.strength = 16.4;
        instance2.xOne = 200;
        
        instance3.count =8;
        instance3.strength = 16.2;
        instance3.xOne = 300;
        
        instance4.count =10;
        instance4.strength = 10.0;
        instance4.xOne = 400;
        
        instance5.count =20;
        instance5.strength =8.0;
        instance5.xOne = 500;
        
        blah.add(instance2);
       blah.add(instance);
       blah.add(instance4);
       blah.add(instance3);
       blah.add(instance5);
         
       String a ="unsorted";
       
        
         for(Word w : blah){
        a = a+ w.toStringSC();
        }
        System.out.println(a);
        
        String o ="sorted by strength";
        Collections.sort(blah, new Word.WordStrengthComparator());
         for(Word w : blah){
        o = o+ w.toStringSC();
        }
        System.out.println(o);
        
        Collections.sort(blah, new Word.WordCountComparator());
        String s ="sorted by count";
         for(Word w : blah){
        s = s+ w.toStringSC();
        }
        System.out.println(s);
        
        Collections.sort(blah, new Word.WordXOneComparator());
        String x ="Sorted by xOne";
         for(Word w : blah){
        x = x+ w.toStringSC();
        }
        System.out.println(x);
        
       
    }

    /**
     * Test of getOffSet method, of class Word.
     */
    
    /*
    @Test
    public void testGetOffSet() {
       Word instance = new Word("1", 23);
       instance.isArabic = true;
        Word instance2 = new Word("2", 1);
        instance2.isArabic = true;
        Word instance3 = new Word("ii", 1);
        Word instance4 = new Word("xi", 22);
        instance3.isRoman = true;
        instance4.isRoman = true;
     
        int exp1 = 22;
        int exp2 = -1;
        int exp3 = 11;
        int result1 = instance.getOffSet();
        int result2 = instance2.getOffSet();
        int result3 = instance3.getOffSet();
        int result4 = instance4.getOffSet();;
        
        
        
        assertEquals(exp1, result1);
        assertEquals(exp2, result2);
        assertEquals(exp2, result3);
        assertEquals(exp3, result4);
    }

   */
    
}
