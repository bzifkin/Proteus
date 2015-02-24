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
public class NumTest {
    
    public NumTest() {
    }
    
   
   
    @Test
    public void testGetPageNum() {
        System.out.println("getPageNum");
        Num instance = new Num("1", 1);
        Num instance2 = new Num("2", 1);
        Num instance3 = new Num("ii", 1);
        Num instance4 = new Num("xi", 1);
     
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
    
    @Test
    public void testCompareTo() {
        
        Num instance = new Num("1", 1);
        Num instance2 = new Num("2", 2);
        Num instance3 = new Num("ii", 3);
        Num instance4 = new Num("xi", 15);
        Num instance5 = new Num("xi", 18);
        Num instance6 = new Num("xi", 77);
        Num instance7 = new Num("xi", 92);
        Num instance8 = new Num("xi", 2);
        Num instance9 = new Num("xi", 16);
        Num instance10 = new Num("xi", 15);
        Num instance11 = new Num("xi", 8);
        Num instance12 = new Num("xi", 18);
        Num instance13 = new Num("xi", 92);
        Num instance14 = new Num("xi", 5);
        Num instance15 = new Num("xi", 5);
        Num instance16 = new Num("xi", 4);
        Num instance17 = new Num("xi", 4);
        
        List<Num> blah = new ArrayList<Num>();
        blah.add(instance);
        blah.add(instance2);
        blah.add(instance3);
        blah.add(instance4);
        blah.add(instance5);
        blah.add(instance6);
        blah.add(instance7);
        blah.add(instance8);
        blah.add(instance9);
        blah.add(instance10);
        blah.add(instance11);
        blah.add(instance12);
        blah.add(instance13);
        blah.add(instance14);
        blah.add(instance15);
        blah.add(instance16);
        blah.add(instance17);
        
        Collections.sort(blah);
        String o = "";
        for(Num n : blah){
        o = o+n.toString();
        }
        System.out.println(o);
        
        
     
        int exp1 = -1;
        int exp2 = 0;
        int exp3 = 1;
        int result1 = instance.compareTo(instance2);
        int result2 = instance2.compareTo(instance3);
        int result3 = instance4.compareTo(instance3);
        int result4 = instance4.getPageNum();
        
        
        
        assertEquals(exp1, result1);
        assertEquals(exp1, result2);
        assertEquals(exp3, result3);
        
        
       
    }

    /**
     * Test of getOffSet method, of class Num.
     */
    @Test
    public void testGetOffSet() {
       Num instance = new Num("1", 23);
       instance.isArabic = true;
        Num instance2 = new Num("2", 1);
        instance2.isArabic = true;
        Num instance3 = new Num("ii", 1);
        Num instance4 = new Num("xi", 22);
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

}
