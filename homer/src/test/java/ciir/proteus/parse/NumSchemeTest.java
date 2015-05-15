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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bzifkin
 */
public class NumSchemeTest {

    public NumSchemeTest() {
    }

    @Test
    public void testGetLast() {
/*
        NumScheme p1 = new NumScheme();

        p1.sequence.add(new Num("5", 1));
        p1.sequence.add(new Num("5", 5));
        p1.sequence.add(new Num("5", 6));
        p1.sequence.add(new Num("6", 7));
        p1.sequence.add(new Num("6", 6));
        p1.sequence.add(new Num("7", 8));
        p1.sequence.add(new Num("8", 9));
        p1.sequence.add(new Num("9", 10));
        p1.sequence.add(new Num("9", 9));

        Num test = new Num("9", 9);
        Num result = p1.getLast();

        int expPN = test.getPageNum();
        int expI = test.index;

        int resPN = result.getPageNum();
        int resI = result.index;

        assertEquals(expPN, resPN);
        assertEquals(expI, resI);*/

    }

    /**
     * Test of getFirst method, of class NumScheme.
     */
    @Test
    public void testGetFirst() {
      /*  System.out.println("getFirst");
        NumScheme p1 = new NumScheme();

        p1.sequence.add(new Num("5", 1));
        p1.sequence.add(new Num("5", 5));
        p1.sequence.add(new Num("5", 6));
        p1.sequence.add(new Num("6", 7));
        p1.sequence.add(new Num("6", 6));
        p1.sequence.add(new Num("7", 8));
        p1.sequence.add(new Num("8", 9));
        p1.sequence.add(new Num("9", 10));
        p1.sequence.add(new Num("9", 9));

        Num test = new Num("5", 1);
        int expPN = test.getPageNum();
        int expI = test.index;
        Num result = p1.getFirst();

        int resPN = result.getPageNum();
        int resI = result.index;

        assertEquals(expPN, resPN);
        assertEquals(expI, resI);*/
    }

    @Test
    public void testCompareTo() {
/*
        List<NumScheme> loss = new ArrayList<NumScheme>();
        NumScheme p1 = new NumScheme();
        p1.sequence.add(new Num("5", 1));
        p1.sequence.add(new Num("5", 5));
        p1.sequence.add(new Num("5", 6));
        p1.sequence.add(new Num("6", 7));
        p1.sequence.add(new Num("6", 6));
        p1.sequence.add(new Num("7", 8));
        p1.sequence.add(new Num("8", 9));
        p1.sequence.add(new Num("9", 10));
        p1.sequence.add(new Num("9", 9));

        //***************************************************************************************************************************************************
        NumScheme p2 = new NumScheme();
        p2.sequence.add(new Num("11", 11));
        p2.sequence.add(new Num("11", 12));
        p2.sequence.add(new Num("15", 17));
        p2.sequence.add(new Num("18", 20));
        p2.sequence.add(new Num("19", 20));
        p2.sequence.add(new Num("20", 21));
        p2.sequence.add(new Num("20", 20));

        //***************************************************************************************************************************************************
        NumScheme p3 = new NumScheme();
        p3.sequence.add(new Num("26", 26));
        p3.sequence.add(new Num("28", 31));
        p3.sequence.add(new Num("29", 32));
        p3.sequence.add(new Num("28", 27));

        loss.add(p1);
        loss.add(p3);
        loss.add(p2);

        System.out.println("loss before sort  " + loss.toString());

        assertEquals(-1, p1.compareTo(p3));
        assertEquals(-1, p2.compareTo(p3));
        assertEquals(1, p3.compareTo(p1));
        assertEquals(0, p1.compareTo(p1));

        Collections.sort(loss);

        assertEquals(p3, loss.get(2));
        System.out.println("loss after sort  " + loss.toString());
*/
    }

    @Test
    public void testIndexComparator() {
/*
        List<NumScheme> loss = new ArrayList<NumScheme>();
        NumScheme p1 = new NumScheme();
        p1.sequence.add(new Num("5", 1));
        p1.sequence.add(new Num("5", 5));
        p1.sequence.add(new Num("5", 6));
        p1.sequence.add(new Num("6", 7));
        p1.sequence.add(new Num("6", 6));
        p1.sequence.add(new Num("7", 8));
        p1.sequence.add(new Num("8", 9));
        p1.sequence.add(new Num("9", 10));
        p1.sequence.add(new Num("9", 9));

        //***************************************************************************************************************************************************
        NumScheme p2 = new NumScheme();
        p2.sequence.add(new Num("11", 11));
        p2.sequence.add(new Num("11", 12));
        p2.sequence.add(new Num("15", 17));
        p2.sequence.add(new Num("18", 20));
        p2.sequence.add(new Num("19", 20));
        p2.sequence.add(new Num("20", 21));
        p2.sequence.add(new Num("20", 20));

        //***************************************************************************************************************************************************
        NumScheme p3 = new NumScheme();
        p3.sequence.add(new Num("26", 26));
        p3.sequence.add(new Num("28", 31));
        p3.sequence.add(new Num("29", 32));
        p3.sequence.add(new Num("28", 27));
        loss.add(p2);
        loss.add(p1);
        loss.add(p3);

        System.out.println("loss before sort  " + loss.toString());

        assertEquals(p2, loss.get(0));
        assertEquals(p1, loss.get(1));
        assertEquals(p3, loss.get(2));

        Collections.sort(loss, NumScheme.indexComparator);

        assertEquals(p1, loss.get(0));
        assertEquals(p2, loss.get(1));
        assertEquals(p3, loss.get(2));

        System.out.println("loss after sort  " + loss.toString());
*/
    }

    /**
     * Test of getNumOfBlanks method, of class NumScheme.
     */
    @Test
    public void testGetLastBlanks() {
        /*
        System.out.println("getNumOfBlanks");

        Num w1 = new Num();
        Num w2 = new Num();
        Num w3 = new Num();
        Num w4 = new Num();
        Num w5 = new Num();
        w1.isBlank = true;
        w2.isBlank = true;
        w3.isBlank = true;
        w4.isBlank = true;
        w5.isBlank = true;

        NumScheme p3 = new NumScheme();
        p3.sequence.add(new Num("26", 26));
        p3.sequence.add(new Num("blank", 31));
        p3.sequence.add(new Num("blank", 32));
        p3.sequence.add(w3);
        p3.sequence.add(w4);
        p3.sequence.add(w5);
        p3.sequence.add(new Num("blank", 27));
        p3.sequence.add(new Num("blank", 31));
        p3.sequence.add(new Num("28", 31));
        p3.sequence.add(new Num("28", 31));
        p3.sequence.add(new Num("28", 31));
        p3.sequence.add(new Num("28", 31));
        p3.sequence.add(new Num("28", 31));
        p3.sequence.add(new Num("28", 31));
        p3.sequence.add(w1);
        p3.sequence.add(w2);

        int expResult = 2;
        int result = p3.getLastBlanks();
        assertEquals(expResult, result);*/

    }

//    @Test
//    public void testGetAllBlanks() {
//        System.out.println("getNumOfBlanks");
//
//        Num w1 = new Num();
//        Num w2 = new Num();
//        Num w3 = new Num();
//        Num w4 = new Num();
//        Num w5 = new Num();
//        w1.isBlank = true;
//        w2.isBlank = true;
//        w3.isBlank = true;
//        w4.isBlank = true;
//        w5.isBlank = true;
//
//        NumScheme p3 = new NumScheme();
//        p3.sequence.add(new Num("26", 26));
//        p3.sequence.add(new Num("blank", 31));
//        p3.sequence.add(new Num("blank", 32));
//        p3.sequence.add(w3);
//        p3.sequence.add(w4);
//        p3.sequence.add(w5);
//        p3.sequence.add(new Num("blank", 27));
//        p3.sequence.add(new Num("blank", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(w1);
//        p3.sequence.add(w2);
//
//        int expResult = 5;
//        int result = p3.getAllBlanks();
//        assertEquals(expResult, result);
//
//    }
//
//    @Test
//    public void testGetLastNonBlank() {
//
//        Num w1 = new Num();
//        Num w2 = new Num();
//        Num w3 = new Num();
//        Num w4 = new Num();
//        Num w5 = new Num();
//        w1.isBlank = true;
//        w2.isBlank = true;
//        w3.isBlank = true;
//        w4.isBlank = true;
//        w5.isBlank = true;
//        Num expResult = new Num("blank", 32);
//        NumScheme p3 = new NumScheme();
//        p3.sequence.add(new Num("26", 26));
//        p3.sequence.add(new Num("blank", 31));
//        p3.sequence.add(expResult);
//        p3.sequence.add(w3);
//        p3.sequence.add(w4);
//        p3.sequence.add(w5);
//        //   p3.sequence.add(new Num("blank", 27));
//        // p3.sequence.add(new Num("blank", 31));
//        // p3.sequence.add(new Num("28", 31));
//        //p3.sequence.add(new Num("28", 31));
//        // p3.sequence.add(new Num("28", 31));
//        // p3.sequence.add(new Num("28", 31));
//        // p3.sequence.add(new Num("28", 31));
//        // p3.sequence.add(expResult);
//        p3.sequence.add(w1);
//        p3.sequence.add(w2);
//
//        Num result = p3.getLastNonBlank();
//        assertEquals(expResult, result);
//
//    }
//
//    @Test
//    public void testContainsIndex() {
//
//        Num w1 = new Num("1", 3);
//        Num w2 = new Num("1", 17);
//        Num w3 = new Num("1", 7);
//        Num w4 = new Num("1", 12);
//        Num w5 = new Num("1", 8);
//
//        NumScheme p3 = new NumScheme();
//        p3.sequence.add(new Num("26", 26));
//        p3.sequence.add(new Num("blank", 31));
//        p3.sequence.add(w1);
//        p3.sequence.add(w2);
//        p3.addBlank();
//        p3.sequence.add(w3);
//        p3.sequence.add(w4);
//        p3.sequence.add(w5);
//
//        assertEquals(p3.containsIndex(3), 2);
//        assertEquals(p3.containsIndex(17), 3);
//        assertEquals(p3.containsIndex(18), 4);
//        assertEquals(p3.containsIndex(7), 5);
//        assertEquals(p3.containsIndex(12), 6);
//
//    }
//
// 
//    
//
//
//    /**
//     * Test of setParkingLot method, of class NumScheme.
//     *
//     * @Test public void testSetParkingLot() {
//     * System.out.println("setParkingLot"); NumScheme instance = new
//     * NumScheme(); instance.setParkingLot(); // TODO review the generated test
//     * code and remove the default call to fail. fail("The test case is a
//     * prototype."); }
//     */
//    /**
//     * Test of getTrueSize method, of class NumScheme.
//     */
//    @Test
//    public void testGetTrueSize() {
//
//        Num w1 = new Num();
//        Num w2 = new Num();
//        Num w3 = new Num();
//        Num w4 = new Num();
//        Num w5 = new Num();
//        w1.isBlank = true;
//        w2.isBlank = true;
//        w3.isBlank = true;
//        w4.isBlank = true;
//        w5.isBlank = true;
//
//        NumScheme p3 = new NumScheme();
//        p3.sequence.add(new Num("26", 26));
//        p3.sequence.add(new Num("blank", 31));
//        p3.sequence.add(new Num("blank", 32));
//        p3.sequence.add(w3);
//        p3.sequence.add(w4);
//        p3.sequence.add(w5);
//        p3.sequence.add(new Num("blank", 27));
//        p3.sequence.add(new Num("blank", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(new Num("28", 31));
//        p3.sequence.add(w1);
//        p3.sequence.add(w2);
//
//        int expResult = 11;
//        int result = p3.getTrueSize();
//        assertEquals(expResult, result);
//    }

}
