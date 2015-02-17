/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ciir.proteus.parse;
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
public class Scorer {
  static int total = 0;
  static int totalCorr = 0;
  
    public Scorer() {
    }

    public static String compareAndScore(List<String[]> truth, List<String[]> maybe) {
        ArrayList<String[]> tru = expandList((ArrayList<String[]>) truth);
      
        int missBeg = 0;
        int missEnd = 0;
        int correct = 0;
        int blanksCorrect = 0;
        int blanksMissed = 0;
        int wrong = 0;
        int firstPageOfGuess = 0;
        int lastPageOfGuess = 0;
        String SCORE = "";
        if (maybe.size() > 0) {
            firstPageOfGuess = Integer.valueOf(maybe.get(0)[1]);
            lastPageOfGuess = Integer.valueOf(maybe.get(maybe.size() - 1)[1]);
        } else {
            return "No page numbers found at all";
        }
        int lastPage = Integer.valueOf(tru.get(tru.size() - 1)[1]);
        int firstPage = Integer.valueOf(tru.get(0)[1]);
        int correctSize = tru.size();
        for (String[] stray : tru) {
            total++;
            if (inTheList(maybe, stray)) {

                correct++;
                totalCorr ++;
                if (stray[0].equalsIgnoreCase("blank")) {
                    blanksCorrect++;
                }
            } else if (stray[0].equalsIgnoreCase("blank") && !inTheList(maybe, stray)) {
                blanksMissed++;
                wrong++;
            } else if (Integer.valueOf(stray[1]) < firstPageOfGuess) {
                missBeg++;
                wrong++;
            } else if (Integer.valueOf(stray[1]) > lastPageOfGuess) {
                missEnd++;
                wrong++;
            } else {
                wrong++;
            }

        }

        int percentCorrect = (int) (correct * 100f) / correctSize;

        return "There were " + correct + " / " + correctSize + " for " + percentCorrect + "% correct with \n" + wrong + " wrong guesses with " + missBeg + " pages missing in the begining and " + missEnd + " pages missed from the end \n" + "There were " + blanksCorrect + " correct blanks and " + blanksMissed + " missed blanks\n";
    }

    public static boolean inTheList(List<String[]> guess, String[] stray) {

        for (int i = 0; i <= guess.size() - 1; i++) {
            if (Arrays.equals(guess.get(i), stray)) {
                return true;
            }

        }

        return false;
    }

    public static boolean bothRoman(String[] a, String[] b) {
        RomanNumeral rn = new RomanNumeral();
        String x = a[0];
        String y = b[0];

        if (rn.isRomanNum(x) && rn.isRomanNum(y)) {
            return true;
        } else if (!rn.isRomanNum(x) && !rn.isRomanNum(y)) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList<String[]> expandList(ArrayList<String[]> list) {
        RomanNumeral rn = new RomanNumeral();
        ArrayList<Integer> indices = findIndOfCont(list);
        ArrayList<ArrayList<String[]>> tempList = new ArrayList<ArrayList<String[]>>();
        int stopInd = 0;
        int startInd = 0;
        int startNum = 0;

        for (int x = 0; x <= indices.size() - 1; x++) {
            int ref = indices.get(x);
            ArrayList<String[]> temp = new ArrayList<String[]>();
            stopInd = Integer.valueOf(list.get(ref)[1]);
            startInd = Integer.valueOf(list.get(ref - 1)[1]);
            int diff = stopInd - startInd;

            if (isInteger(list.get(ref - 1)[0])) {
                startNum = Integer.valueOf(list.get(ref - 1)[0]);

                for (int y = 1; y <= diff; y++) {
                    temp.add(new String[]{String.valueOf(startNum + y), String.valueOf(startInd + y)});

                }
            } else {

                startNum = rn.romanToDecimal((list.get(ref - 1)[0]));
                for (int y = 1; y <= diff; y++) {
                    temp.add(new String[]{String.valueOf(startNum + y), String.valueOf(startInd + y)});
                }
                for (String[] ray : temp) {
                    ray[0] = rn.arabToRoman(Integer.valueOf(ray[0]));
                }
            }
            tempList.add(temp);
        }

        for (int i = 0; i <= tempList.size() - 1; i++) {
            indices = findIndOfCont(list);
            list.addAll(indices.get(i), tempList.get(i));

        }

        ArrayList<Integer> nindices = findIndOfCont(list);

        for (int i = 0; i <= nindices.size() - 1; i++) {
            nindices = findIndOfCont(list);

            list.remove((int) nindices.get(0));

        }

        return list;

    }

    public static ArrayList<Integer> findIndOfCont(List<String[]> blah) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (String[] stray : blah) {
            if (stray[0].trim().equalsIgnoreCase("cont")) {
                indices.add(blah.indexOf(stray));
            }
        }

        return indices;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

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
                    if (lis.get(k).length != 2) {
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
                System.out.println("For book " + res.get(i).title + " " + compareAndScore(obs.get(index).theList, res.get(i).theList));
                
                System.out.println("There were " + total + " numbered pages and we identified " + totalCorr + " correctly");
                /* String resout = "";
                 List<String[]> lis = res.get(i).theList;
                 for (int k = 0; k <= lis.size() - 1; k++) {
                 resout = resout + lis.get(k)[0] + "," + lis.get(k)[1] + "\n";

                 }
                
                 String obsout = "";
                 List<String[]> lay = obs.get(index).theList;
                 for (int k = 0; k <= lay.size() - 1; k++) {
                 obsout = obsout + lay.get(k)[0] + "," + lay.get(k)[1] + "\n";

                 }
                       
                 System.out.println("For book " + res.get(i).title + " this is the results " + resout);
                 System.out.println("-------------------------------------------------------------------");
                 System.out.println("-------------------------------------------------------------------");
                 System.out.println("-------------------------------------------------------------------");
                 System.out.println("-------------------------------------------------------------------");
                 System.out.println("and this is the observed " + obsout);
                 */
            } else {
                // System.out.println("For book " + res.get(i).title + " there is no correct document");
            }
        }

    }
}
