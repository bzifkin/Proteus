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
public class HeaderTest {

    public HeaderTest() {
    }

    @Test
    public void testAvgYCoordComparator() {

        Header h1 = new Header();
        Header h2 = new Header();
        Header h3 = new Header();
        Header h4 = new Header();
        Header h5 = new Header();
        Header h6 = new Header();
        Header h7 = new Header();
        Header h8 = new Header();
        h1.avgYCoord = 40;
        h2.avgYCoord = 20;
        h3.avgYCoord = 30;
        h4.avgYCoord = 10;
        h5.avgYCoord = 50;
        h6.avgYCoord = 80;
        h7.avgYCoord = 60;
        h8.avgYCoord = 70;

        List<Header> blah = new ArrayList<Header>();
        blah.add(h8);
        blah.add(h7);
        blah.add(h6);
        blah.add(h5);
        blah.add(h4);
        blah.add(h3);
        blah.add(h2);
        blah.add(h1);
         String o= "";
        o = o +"BEFORE SORT";
       
        for(Header h: blah){
        o=o + h.avgYCoord + "\n";
        
        }
        System.out.println(o);
        Collections.sort(blah, new Header.AvgYCoordComparator());
        
      o = o +"AFTER SORT";
       
        
        for(Header h: blah){
        o=o + h.avgYCoord + "\n";
        
        }
        System.out.println(o);
    }

   
}
