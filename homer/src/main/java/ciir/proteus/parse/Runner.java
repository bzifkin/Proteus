//package ciir.proteus.parse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author bzifkin
 */
/**
 * This class takes in a DjVU/XMl document and using the iterator API of StAX
 * creates another DjVU/XML document that contains only the token found in the
 * outer 10% of the original document
 */
public class Runner {

    public static List<Pages> possHeaders = new ArrayList<Pages>();
    public static List<Pages> possNums = new ArrayList<Pages>();
    public static NumScheme TRUTH;
  
    
    
    /*
    This is where our word tokens are seperated into numeric and non numeric lists
    TODO: 
    *recognize repeating numeric tokens and add them to header list
    *add fucntionality for different numbering schemes other than roman/arabic
    */

    public static void sortTokens(List<Pages> pagelist) {

        for (Pages p : pagelist) {
            Pages num = new Pages(p.indexNum);
            Pages head = new Pages(p.indexNum);
            head.qInch = num.qInch = p.qInch;
            for (Word w : p.wordsOnPage) {
                if (isArabicNum(w.text) || RomanNumeral.isRomanNum(w.text)) {
                    num.wordsOnPage.add(w);

                } else {
                    head.wordsOnPage.add(w);
                }
            }
            possHeaders.add(head);
            possNums.add(num);
        }
    }

    public static boolean isArabicNum(String st) {

        try {
            Integer.parseInt(st);

        } catch (NumberFormatException e) {

            return false;
        }

        return true;
    }

    public static void main(String args[]) throws IOException, XMLStreamException {

        int totalFound = 0;
        int totalBlanks = 0;
        File readFile = new File(args[0]);
       //String resultPath = args[1]; // ./output-data/
        List<Num> count = new ArrayList<Num>();
        if (readFile.isDirectory()) {
            // int totalPgCount=0;
            for (File file : readFile.listFiles()) {
                possHeaders.removeAll(possHeaders);
                possNums.removeAll(possNums);
                List<Pages> work = new ArrayList<Pages>();
                XMLEvent event = null;
                Preprocessing pp = new Preprocessing(file);
                System.out.println(file.getName());

                CharSequence cat = "CaT";
                //i put this little if in so that the documents dont need to be trimmed and cleaned everytime
                if (file.getName().contains(cat)) {
                    work = pp.trim(event, false);
                } else {
                    work = pp.trim(event, true);
                }

                sortTokens(work);

                 //HeaderFinder hf = new HeaderFinder();
                // List<Pages> headies = hf.findHeaders(possHeaders);
                 
                 //hf.printData(headies, file, resultPath);
              
                PNumbers pn = new PNumbers();
                List<NumScheme> listSchemes = pn.sortSchemes(possNums);
                List<NumScheme> cleanSchemes = pn.cleanData(listSchemes);
                TRUTH = pn.stitchMeUp(cleanSchemes);
                for(Word w: TRUTH.sequence){
                if(w.isBlank == false){
                    totalFound++;
                }else
                    totalBlanks++;
                }
                System.out.println("TOTAL FOUND NUMBERS " + totalFound + " TOTAL BLANKS " + totalBlanks);
                 
/*
                for (NumScheme ns : cleanSchemes) {
                    boolean found = false;
                    for (Num n : count) {
                        if (ns.getTrueSize() == n.size && ns.inScheme == true) {
                            n.totalCount++;
                            n.inlineCount++;
                            found = true;
                        } else if (ns.getTrueSize() == n.size && ns.inScheme == false) {
                            n.totalCount++;
                            found = true;
                        }

                    }
                    if (found == false) {
                        Num blah = new Num(ns.getTrueSize());
                        blah.totalCount++;
                        if (ns.inScheme == true) {
                            blah.inlineCount++;
                        }
                        count.add(blah);
                    }
                }
*/
                
              // if (TRUTH != null) {
               //   TRUTH = pn.fillInBlanks(TRUTH);
                //  pn.printData(TRUTH, file, resultPath);
               //  }
            }
          //  String out = "";
           /// for (Num n : count) {
             //   out = out + " For true size: " + n.size + " there were " + n.inlineCount + " / " + n.totalCount + " used in a scheme\n";

           // }
           // System.out.println(out);
        } else {
            
            Preprocessing pp = new Preprocessing(readFile);
            List<Pages> work = new ArrayList<Pages>();
            XMLEvent event = null;
            CharSequence cat = "CaT";

            if (readFile.getName().contains(cat)) {

                work = pp.trim(event, false);
            } else {
                work = pp.trim(event, true);
            }

            sortTokens(work);
            int numPages = work.size();
            HeaderFinder hf = new HeaderFinder();
            List<Pages> headies = hf.findHeaders(possHeaders);
            hf.singleFilePrintData(headies);

           // PNumbers pn = new PNumbers();
            //List<NumScheme> listSchemes = pn.sortSchemes(possNums);
           // List<NumScheme> cleanSchemes = pn.cleanData(listSchemes);
            
           // String out = "";
           //       for (NumScheme ns : cleanSchemes) {
            //      out = out + ns.toString();
            //      }
            //    System.out.println(out);

            //TRUTH = pn.stitchMeUp(cleanSchemes);
            // ???? how do i get print writer to print the file to adifferent directory. solution is below but seems hacky
           // if (TRUTH != null) {
            //    TRUTH = pn.fillInBlanks(TRUTH);
              //  pn.singleFilePrintData(TRUTH, numPages);
          // }
        }
    }

}
