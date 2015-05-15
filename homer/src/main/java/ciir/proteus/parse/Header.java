/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciir.proteus.parse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author bzifkin
 */
public class Header {

    double lineStrength;
    int lineCount;

    boolean firstLine; //to mark if this line is the lowest/highest on page
    int avgYCoord;
    List<Word> line = new ArrayList<Word>();

    public void sortWordsInLine() {
        Collections.sort(line, new Word.WordXOneComparator());

    }

    public static class AvgYCoordComparator implements Comparator<Header> { //sorts highest to lowest

        public int compare(Header h1, Header h2) {
            return h2.getAvgYCoord() - h1.getAvgYCoord();
        }
    }

    public double getLineStrength() {
        double blah = 0.0;
        for (Word w : this.line) {
            blah = blah + w.strength;
        }

        lineStrength = blah / this.line.size();
        return lineStrength;

    }

    public int getLineCount() {
        int blah = 0;
        for (Word w : this.line) {
            blah = blah + w.count;
        }

        lineCount = blah / this.line.size();
        return lineCount;

    }

    public int getAvgYCoord() {
        int avg = 0;
        if (0 == this.line.get(0).line) {
            for (int i = 0; i <= line.size() - 1; i++) {
                avg = avg + line.get(i).yTwo;
            }
            avgYCoord = avg / line.size();
            return avgYCoord;
        } else {
            for (int i = 0; i <= line.size() - 1; i++) {
                avg = avg + line.get(i).line;
            }
            avgYCoord = avg / line.size();
            return avgYCoord;
        }

    }

    public static class HeaderStrengthComparator implements Comparator<Header> {

        public int compare(Header h1, Header h2) {
            return Double.compare(h2.getLineStrength(), h1.getLineStrength());
        }
    }

    public static class HeaderCountComparator implements Comparator<Header> {

        public int compare(Header h1, Header h2) {
            return (h2.getLineCount() - h1.getLineCount());
        }
    }

    public String toString() {
        this.sortWordsInLine();
        String result = "";

        for (Word w : this.line) {
            result = result + w.toLineString();
        }
        result = result + " Strength " + getLineStrength() + " Count " + getLineCount() + "\n";
        return result;
    }
    
     public String toSimpleString() {
        this.sortWordsInLine();
        String result = "";

        for (Word w : this.line) {
            result = result + w.toSimpleString();
        }
        
        return result;
    }

    public String toYCoordString() {
        String result = "";

        for (Word w : this.line) {
            result = result + w.toLineString();
        }
        result = result + " Avg Y Coord: " + avgYCoord + "\n";
        return result;
    }

}
