/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ciir.proteus.parse;

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
public class PagesTest {
/*
    @Test
    public void testGetWords() {
        System.out.println("getWords");
        Pages instance = new Pages();
        List<Pages.Word> expResult = null;
        List<Pages.Word> result = instance.getWords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

 
    @Test
    public void testToString() {
        System.out.println("toString");
        Pages instance = new Pages();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
  */  
    
    
    @Test
    public void testGetOffSet(){
    
    Pages instance = new Pages();
    Pages.Word fuck = new Pages.Word("5", 8);
    fuck.isArabic = true;
    int expResult = 3;
    assertEquals(expResult,fuck.getOffSet());
    
    
    
    
    }
}
