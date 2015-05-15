/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciir.proteus.parse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
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
public class PreprocessingTest {
    
    //this test was written when the method was passed an input stream rather than A file.
//    @Test public void testTrim() throws Exception { 
//        String yeahright ="<BODY><OBJECT data=\"file://localhost//tmp/derive/cairngormmounta01burtgoog//cairngormmounta01burtgoog.djvu\" height=\"1650\" type=\"image/x.djvu\" ysemap=\"cairngormmounta01burtgoog_0001.djvu\" width=\"1275\">" 
//                + "<PARAM name=\"DPI\" value=\"300\"/>" 
//                + "<WORD coords=\"203,290,65,276,290\">4545</WORD>" 
//                + "<WORD coords=\"73,290,84,276,290\">45</WORD>" 
//                + "<WORD coords=\"92,290,100,280,290\">a</WORD>" 
//                + "<WORD coords=\"105,295,157,276,290\">digital</WORD>" 
//                + "<WORD coords=\"810,290,840,280,290\">was</WORD>"
//                + "<WORD coords=\"847,295,919,276,290\">carefully</WORD>" 
//                + "<WORD coords=\"927,290,991,276,290\">scanned</WORD>"
//                + "<WORD coords=\"999,295,1017,276,290\">by</WORD>" 
//                + "<WORD coords=\"661,346,685,332,346\">the</WORD>" 
//                + "<WORD coords=\"690,351,742,332,346\">public</WORD>"
//                + "<WORD coords=\"747,347,813,332,346\">domain.</WORD>" 
//                + "<WORD coords=\"1024,295,1085,276,290\">x</WORD>" 
//                + "</OBJECT></BODY>";
//      InputStream is = new ByteArrayInputStream(yeahright.getBytes());
//     
//      XMLEvent event = null; Preprocessing instance = new Preprocessing(is);
//    
//      List<Pages> result = instance.trim(event); List<Word> expWords =
//      result.get(0).getWords(); int expSize = result.get(0).wordsOnPage.size();
//      assertEquals(2, expSize);
//     
//      }
     
    
    
//    @Test
//    public void testGetPageIndex() throws Exception {
//        String yeahright = "<BODY><OBJECT data=\"file://localhost//tmp/derive/cairngormmounta01burtgoog//cairngormmounta01burtgoog.djvu\" height=\"1650\" type=\"image/x.djvu\" usemap=\"cairngormmounta01burtgoog_0001.djvu\" width=\"1275\">"
//                + "<PARAM name=\"DPI\" value=\"300\"/>"
//                + "<MAP name=\"anessayonperson00richgoog_0001.djvu\"/>"
//                + "<MAP name=\"anessayonperson00richgoog_0002.djvu\"/>"
//                + "<WORD coords=\"92,290,100,280,290\">a</WORD>"
//                + "<WORD coords=\"105,295,157,276,290\">digital</WORD>" + "<MAP name=\"anessayonperson00richgoog_0065.djvu\"/>"
//                + "<WORD coords=\"847,295,919,276,290\">carefully</WORD>"
//                + "<WORD coords=\"927,290,991,276,290\">scanned</WORD>"
//                + "<WORD coords=\"999,295,1017,276,290\">by</WORD>"
//                + "<WORD coords=\"661,346,685,332,346\">the</WORD>"
//                + "<WORD coords=\"690,351,742,332,346\">public</WORD>"
//                + "<WORD coords=\"747,347,813,332,346\">domain.</WORD>"
//                + "<WORD coords=\"1024,295,1085,276,290\">x</WORD>"
//                + "</OBJECT></BODY>";
//        InputStream is = new ByteArrayInputStream(yeahright.getBytes());
//
//        XMLEvent event = null;
//        Preprocessing instance = new Preprocessing(is);
//
//        List<Pages> result = instance.trim(event);
//
//        assertEquals(1, result.get(0).indexNum);
//        assertEquals(2, result.get(1).indexNum);
//        assertEquals(65, result.get(2).indexNum);
//
//    }

    /**
     * Test of getPageHeightAndWidth method, of class Preprocessing.
     */
    @Test
    public void testGetPageHeightAndWidth() {
        System.out.println("getPageHeightAndWidth");
        StartElement se = null;
        Preprocessing instance = new Preprocessing();
        instance.getPageHeightAndWidth(se);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   

    /**
     * Test of calculateMargins method, of class Preprocessing.
     */
    @Test
    public void testCalculateMargins() {
        System.out.println("calculateMargins");
        int ph = 0;
        int pw = 0;
        Preprocessing instance = new Preprocessing();
        instance.calculateMargins(ph, pw);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWordCoords method, of class Preprocessing.
     */
    @Test
    public void testGetWordCoords() {
        System.out.println("getWordCoords");
        StartElement se = null;
        Preprocessing instance = new Preprocessing();
        instance.getWordCoords(se);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   

//    /**
//     * Test of inMargin method, of class Preprocessing.
//     */
//    @Test
//    public void testInMargin() {
//        System.out.println("inMargin");
//        int xone = 0;
//        int yone = 0;
//        int xtwo = 0;
//        int ytwo = 0;
//        Preprocessing instance = new Preprocessing();
//        boolean expResult = false;
//        boolean result = instance.inMargin(xone, yone, xtwo, ytwo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of checkIndex method, of class Preprocessing.
     */
    @Test
    public void testCheckIndex() {
        System.out.println("checkIndex");
        StartElement se = null;
        Preprocessing instance = new Preprocessing();
        boolean expResult = false;
        boolean result = instance.checkIndex(se);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeNewElement method, of class Preprocessing.
     */
    @Test
    public void testMakeNewElement() {
        System.out.println("makeNewElement");
        StartElement se = null;
        Preprocessing instance = new Preprocessing();
        StartElement expResult = null;
        StartElement result = instance.makeNewElement(se);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increment method, of class Preprocessing.
     */
    @Test
    public void testIncrement() {
        System.out.println("increment");
        Preprocessing instance = new Preprocessing();
        instance.increment();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    
}
