/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ciir.proteus.parse;

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
public class RomanNumeralTest {
    
    /**
   
    @Test
    public void testRomanToDecimal() {
        System.out.println("romanToDecimal");
        Word word = null;
        int expResult = 0;
        int result = RomanNumeral.romanToDecimal(word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testProcessDecimal() {
        System.out.println("processDecimal");
        int decimal = 0;
        int lastNumber = 0;
        int lastDecimal = 0;
        int expResult = 0;
        int result = RomanNumeral.processDecimal(decimal, lastNumber, lastDecimal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


  
     
    @Test
    public void testIsRomanNum() {
        System.out.println("isRomanNum");
        Word w = null;
        boolean expResult = false;
        boolean result = RomanNumeral.isRomanNum(w);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of arabToRoman method, of class RomanNumeral.
     */
    @Test
    public void testArabToRoman() {
        System.out.println("arabToRoman");
        int num1 = 1;
        int num2 = 3;
        int num3 = 4;
        int num4 = 9;
        int num5 = 90;
        int num6 = 19;
        int num7 = 92;
        RomanNumeral instance = new RomanNumeral();
        
        String result1 = instance.arabToRoman(num1);
        
        String result2 = instance.arabToRoman(num2);
        String result3 = instance.arabToRoman(num3);
        String result4 = instance.arabToRoman(num4);
        String result5 = instance.arabToRoman(num5);
        String result6 = instance.arabToRoman(num6);
        String result7 = instance.arabToRoman(num7);
        
        
        System.out.println("Result 1 " + result1);
        System.out.println("Result 2 " + result2);
        System.out.println("Result 3 " + result3);
        System.out.println("Result 4 " + result4);
        System.out.println("Result 5 " + result5);
        System.out.println("Result 6 " + result6);
        System.out.println("Result 7 " + result7);
       
        
        assertEquals("I", result1);
        assertEquals("III", result2);
        assertEquals("IV", result3);
        assertEquals("IX", result4);
        assertEquals("XC", result5);
        assertEquals("XIX", result6);
        assertEquals("XCII", result7);
       
    }
    
}
