/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciir.proteus.parse;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bzifkin
 */
public class Headers {
    
    
     
    boolean foundElementOnCurrPage = false;
   
    boolean inParkLot = false;
    List<Word>  parts = new ArrayList<Word>();

    
   


    

   
   

    public String toString() {
        String result = "";
        String divider = "-------------------------------------------------\n";
        for (Word w : this.parts) {
            result = result + w.toString();
        }
        return result + divider;
    }

    
      public String toTabbedString() {
        String result = "";
 
        for (Word w : this.parts) {
            result = result + w.toTabbedString();
        }
        return result;
    }


    


    //use this function to determine when to place scheme in parking lot
    //if ratio below 50% we no longer consider adding new elements to it
    public void setParkingLot() {
        int totalPagesLookedAt = this.sequence.size();
        int totalNumBlanks = getAllBlanks();

        double ratio = (double) (totalPagesLookedAt - totalNumBlanks) / (double) totalPagesLookedAt;
        if (ratio <= .50) {
           // System.out.println("setting p-lot to true. ratio: " + ratio);
            this.inParkLot = true;
        } else {
            // System.out.println("p-lot still false. ratio " + ratio);
            this.inParkLot = false;
        }
    }

    
}
