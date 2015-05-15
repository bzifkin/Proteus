/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciir.proteus.parse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author bzifkin
 */
public class ScoreHeads {

    public static String compareAndScore(List<String[]> truth, List<String[]> maybe) {
        String[] temp = null;
        String res = "";
        String onPage = " On page ";
        for (String[] st : truth) {
            res = res + onPage + st[0] + "\n";
            if (st[1].equalsIgnoreCase("none")) {
                res = res + " there were no headers, and we found: ";
                int ind = handleNone(Integer.valueOf(st[0].trim()), maybe);
                if (ind == -1) {
                    res = res + " no headers!\n";
                } else {
                    //res = res + maybe.get(ind)[2] + "\n";
                    res = res + " something :( \n";
                }
            } else {
                temp = findPage(Integer.valueOf(st[0].trim()), st[1], maybe);
                res = res + "TRUE HEADER:  " + st[2] + "\n" + "FOUND HEADER: ";

                if (temp != null) {
                    //compareHeaders(st, temp);
                    res = res + temp[2] + "\n";
                } else {
                    res = res + " nothing :( \n";
                }
            }
        }
        return res;

    }

    public static int handleNone(int pn, List<String[]> maybe) {
        int index = -1;
        for (String[] st : maybe) {
            if (Integer.valueOf(st[0].trim()) == pn) {
                return maybe.indexOf(st);
            }

        }
        return index;
    }

    public static String[] findPage(int page, String top, List<String[]> maybe) {
        for (String[] st : maybe) {
            if (Integer.valueOf(st[0].trim()) == page && st[1].equalsIgnoreCase(top)) {
                return st;
            }
        }
        return null;
    }
    /*
     public static void compareHeaders(String[] truth, String[] maybe){
     String[] trueWords = truth[2].split(" ");
     String[] maybeWords = maybe[2].split(" ");
  
     }
     */

    public static ArrayList<AnnotationReader> gatherAndTrimLists(File directory) {
        ArrayList<AnnotationReader> temp = new ArrayList<AnnotationReader>();

        if (directory.isDirectory()) {
            for (File blah : directory.listFiles()) {

                AnnotationReader foo = new AnnotationReader(directory, blah);
                temp.add(foo);
            }
            for (int i = 0; i <= temp.size() - 1; i++) {

                List<String[]> lis = temp.get(i).theList;
                for (int k = 0; k <= lis.size() - 1; k++) {
                    if (lis.get(k).length != 3) {
                        lis.remove(k);
                    }

                }

            }
        }
        return temp;
    }

    public static int checkTitles(String title, ArrayList<AnnotationReader> list) {

        for (int i = 0; i <= list.size() - 1; i++) {
            if (list.get(i).title.substring(0, 6).equals(title)) {
                return i;
            }

        }
        return -1;

    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        File results = new File(args[0]);
        File observed = new File(args[1]);

        ArrayList<AnnotationReader> res = gatherAndTrimLists(results);
        ArrayList<AnnotationReader> obs = gatherAndTrimLists(observed);

        for (int i = 0; i <= res.size() - 1; i++) {
            int index = checkTitles(res.get(i).title.substring(0, 6), obs);
            if (index != -1) {
                System.out.println("For book " + res.get(i).title + " \n" + compareAndScore(obs.get(index).theList, res.get(i).theList) + "\n");

            }
        }

    }
}
