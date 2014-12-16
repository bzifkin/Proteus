package ciir.proteus.parse;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bzifkin
 */
public class Word implements Comparable<Word> {

    boolean isArabic = false;
    boolean isRoman = false;
    boolean usable = true;
    int index = -1;
    String text;
    boolean isBlank = false;
    int xOne; //coordinates of the words
    int yOne;
    int xTwo;
    int yTwo;
    int line;
    List<Word> potentialMatches = new ArrayList<Word>();
    int count = 0;

    public Word() {
    }

    public Word(String text) {
        this.text = text;
    }

    public Word(String text, int ind) {
        this.text = text;
        this.index = ind;
    }
    public static final Comparator<Word> countComparator = new Comparator<Word>() {

        @Override
        public int compare(Word w1, Word w2) {
            return w2.count - w1.count;
        }

    };
            
           

             
@Override
    public int compareTo(Word w) {
//formulated this way so that when calling sort the list is ordered from largest to smallest
        if (this.count < w.count) {
            return 1;
        } else if (this.count > w.count) {
            return -1;
        } else {
            return 0;
        }

    }

    public int[] getCoords() {
        int[] coords = new int[4];
        coords[0] = this.xOne;
        coords[1] = this.yOne;
        coords[2] = this.xTwo;
        coords[3] = this.yTwo;
        return coords;
    }

    public int getPageNum() {
        int blah = -1;
        try {
            blah = Integer.parseInt(this.text);
        } catch (NumberFormatException e) {
            if (RomanNumeral.isRomanNum(this.text)) {
                return RomanNumeral.romanToDecimal(this.text);
            }
        }
        return blah;
    }

    public int getOffSet() {
        if (isArabic) {
            return index - getPageNum();
        }
        if (isRoman) {
            return index - RomanNumeral.romanToDecimal(this.text);
        }
        return -1;
    }

    public String toString() {
        String stuff = "";
        stuff = "WORD: " + text + " Index: " + index + "\n";
        return stuff;
    }

    public String toTabbedString() {
        String stuff = "";
        stuff = text + "\t" + index + "\n";
        return stuff;
    }

}
