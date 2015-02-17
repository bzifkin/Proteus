//package ciir.proteus.parse;
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
public class Word {

    
    //this field is used for both headers and page numbers
    String text;
    
    //these fields are mostly used in the process of finding page numbers
    int index = -1;
    boolean isArabic = false;
    boolean isRoman = false;
    boolean isBlank = false;
    
    //these fields are mostly used in the process of finding headers
    int xOne; //coordinates of the words
    int yOne;
    int xTwo;
    int yTwo;
    int line;
    boolean top;
    boolean bottom;
    boolean side;
    boolean inLine = false;
    boolean extrapolated = false;
    List<Word> potentialMatches = new ArrayList<Word>(); //words that are an acceptable edit distance away from each other
    int count = 0; //number of times this particular variation of a word was found
    double strength =0; //used to indicate the certainty of a certain word *****************************************************************do i use or not?***********************************

    public Word() {
    }

    public Word(String text) {
        this.text = text;
    }

    public Word(String text, int ind) {
        this.text = text;
        this.index = ind;
    }
    public static class WordStrengthComparator implements Comparator<Word> {
    public int compare(Word word1, Word word2) {
        return Double.compare(word2.strength, word1.strength);
    }
}
public static class WordCountComparator implements Comparator<Word> {
    public int compare(Word word1, Word word2) {
        return word2.count - word1.count;
    }
}

public static class WordXOneComparator implements Comparator<Word> {
    public int compare(Word word1, Word word2) {
        return word1.xOne - word2.xOne;
    }
}
            
           

             

 

    public int[] getCoords() {
        int[] coords = new int[5];
        coords[0] = this.xOne;
        coords[1] = this.yOne;
        coords[2] = this.xTwo;
        coords[3] = this.yTwo;
        coords[4] = this.line;
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
    
     public String toSimpleString() {
        String stuff = "";
        stuff = text + " ";
        return stuff;
    }
    public String toStringSC() {
      
        String stuff = "";
         stuff = "WORD: " + text + "(" + xOne + " , " + yOne + " , " + xTwo + " , " + yTwo + " , " + line +  ")\t STRENGTH: " + strength + "\t COUNT: "+ count +"\n";
        return stuff;
    }
public String toLineString() {
        
        String stuff = "";
         stuff = stuff + text +  "(" + xOne + " , " + yOne + " , " + xTwo + " , " + yTwo + " , " + line +  ")\t";
        return stuff;
    }
}
