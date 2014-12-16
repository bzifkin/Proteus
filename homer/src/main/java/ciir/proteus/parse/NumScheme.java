/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ciir.proteus.parse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author bzifkin
 */
public class NumScheme implements Comparable<NumScheme> {

    String title;
    boolean inScheme = false;
    boolean foundElementOnCurrPage = false;
    int offSet;
    boolean romanSeq;
    boolean arabSeq;
    boolean inParkLot = false;
    List<Word> sequence = new ArrayList<Word>();

    public static final Comparator<NumScheme> indexComparator = new Comparator<NumScheme>() {

        @Override
        public int compare(NumScheme num, NumScheme sch) {
            return num.getFirst().index - sch.getFirst().index;
        }

    };

    public NumScheme() {
    }

    public Word getLast() {
        return sequence.get(sequence.size() - 1);
    }

    public Word getLastNonBlank() {
        return sequence.get(sequence.size() - (1 + this.getLastBlanks()));
    }

    public int containsIndex(int index) {
        for (Word w : this.sequence) {
            if (w.index == index) {
                return this.sequence.indexOf(w);
            }
        }

        return -1;

    }

    public Word getFirst() {
        return sequence.get(0);
    }

    public void addBlank() {

        Word blank = new Word();
        blank.index = getLast().index + 1;
        blank.isBlank = true;
        blank.text = "blank";
        sequence.add(blank);

    }

   

    public String toString() {
        String result = "";
        String divider = "-------------------------------------------------\n";
        for (Word w : this.sequence) {
            result = result + w.toString();
        }
        return result + divider;
    }

    
      public String toTabbedString() {
        String result = "";
 
        for (Word w : this.sequence) {
            result = result + w.toTabbedString();
        }
        return result;
    }

    public int getTrueSize() {
        return this.sequence.size() - getAllBlanks();
    }

    @Override
    //formulated this way so that when calling sort the list is ordered from largest to smallest
    public int compareTo(NumScheme ns) {

        if (this.getTrueSize() < ns.getTrueSize()) {
            return 1;
        } else if (this.getTrueSize() > ns.getTrueSize()) {
            return -1;
        } else {
            return 0;
        }

    }

//use this function to count backwards to last non-blank number...useful for extrapolating missing #s
    public int getLastBlanks() {
        int numOfBlanks = 0;
        if (this.sequence.size() > 0) {

            for (int i = this.sequence.size() - 1; i >= 0; i--) {

                if (this.sequence.get(i).isBlank) {

                    numOfBlanks++;
                } else {
                    break;
                }

            }

        }
        return numOfBlanks;
    }

    public int getAllBlanks() {
        int numOfBlanks = 0;
        if (this.sequence.size() > 0) {

            for (int i = this.sequence.size() - 1; i >= 0; i--) {

                if (this.sequence.get(i).isBlank) {

                    numOfBlanks++;
                }

            }

        }
        return numOfBlanks;

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
