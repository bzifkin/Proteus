/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ciir.proteus.parse;

import java.util.Collections;
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
        
        Word instance = new Word("1", 1);
        Word instance2 = new Word("2", 6);
        Word instance3 = new Word("3", 8);
        Word instance4 = new Word("4", 20);
         Word instance5 = new Word("5", 2);
     
         instance.count =1;
        instance2.count =6;
        instance3.count =8;
        instance4.count =20;
        instance5.count =2;
        
        instance.potentialMatches.add(instance2);
        instance.potentialMatches.add(instance3);
        instance.potentialMatches.add(instance4);
        instance.potentialMatches.add(instance5);
        
        
        Collections.sort(instance.potentialMatches, Word.countComparator);
        
        String o ="";
        for(Word w : instance.potentialMatches){
        o = o+ w.toString();
        }
        System.out.println(o);
        
       
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
