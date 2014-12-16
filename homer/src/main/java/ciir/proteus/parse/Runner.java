//package ciir.proteus.parse;

import java.awt.BorderLayout;
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
    public static NumScheme TRUTH;

   
    public static boolean isArabicNum(String st) {
        
        try {
            Integer.parseInt(st);
            
        } catch (NumberFormatException e) {
            
            return false;
        }
        
        return true;
    }
    /*this method is similar to the trim method
     lots of calls are made to toerh methods and 
     this one is just bones of the process
     */
   
    
    public static ArrayList<NumScheme> sortAndSearchForSchemes(List<Pages> pagelist) {
        ArrayList<NumScheme> possPageSeqs = new ArrayList<NumScheme>(); // create a list of all the possible sequences
//iterate through pages to find schemes
        for (Pages p : pagelist) {
            //System.out.println("searching pages, poss # of seqs: " + possPageSeqs.size());
//setting the parking lot
            Pages temp = new Pages();
            for (NumScheme ns : possPageSeqs) {
                if (ns.sequence.size() > 2) {
                    ///System.out.println("calling set parking lot");
                    ns.setParkingLot();
                }
            }
            for (Word w : p.wordsOnPage) {
//System.out.println("searching words");
                if (isArabicNum(w.text)) {
                    //System.out.println("found arabic: " + w.text);
                    handleArabic(possPageSeqs, w);
                  
                } else if (RomanNumeral.isRomanNum(w.text)) {
//System.out.println("found roman: " + w.text);
                    handleRoman(possPageSeqs, w);
                   
                } else{
                temp.wordsOnPage.add(w);
                }
            }
            for (NumScheme ns : possPageSeqs) {
//adding blanks if nothing was found and the scheme isnt in the parking lot
                if (ns.foundElementOnCurrPage == false && ns.inParkLot == false) {
//System.out.println("adding blank");
                    ns.addBlank();
                }
            }
            for (NumScheme num : possPageSeqs) {
//System.out.println("setting to false");
                num.foundElementOnCurrPage = false;
            }
            possHeaders.add(temp);
        }
        /// System.out.println("poss sequences: " + possPageSeqs.size());
        return possPageSeqs;
    }
   
    public static ArrayList<NumScheme> handleArabic(ArrayList<NumScheme> pps, Word w) {
        
        NumScheme temp = new NumScheme();
        //  System.out.println("calling Arabsequence");
        if (findArabicSequence(pps, w) == null) {
            //      System.out.println("creating new an");
            w.isArabic = true;
            temp.foundElementOnCurrPage = true;
            temp.sequence.add(w);
            pps.add(temp);
        }
        
        return pps;
    }
    
    public static ArrayList<NumScheme> handleRoman(ArrayList<NumScheme> pps, Word w) {
        
        NumScheme temp = new NumScheme();
        //  System.out.println("calling findRomansequence");
        if (findRomanSequence(pps, w) == null) {

            //  System.out.println("creating new rn");
            temp.foundElementOnCurrPage = true;
            w.isRoman = true;
            temp.sequence.add(w);
            pps.add(temp);
        }
        
        return pps;
        
    }
    
    public static NumScheme findArabicSequence(ArrayList<NumScheme> pps, Word w) {
//System.out.println("looking for an arab seqience");
        if (pps.size() == 0) {
            //  System.out.println("returning null b/c of size");
            return null;
        }
        for (NumScheme ns : pps) {
            
            if (isArabicNum(ns.sequence.get(0).text) && ns.inParkLot == false && ns.foundElementOnCurrPage == false) {
                
                int numBlanks = ns.getLastBlanks();
                int valueOfLast = Integer.valueOf(ns.sequence.get(ns.sequence.size() - (1 + numBlanks)).text);
                int valueOfCurr = Integer.valueOf(w.text);
                w.isArabic = true;
                //  System.out.println("word: " + w.text + "number of blanks: " + numBlanks);
                if (valueOfLast + (1 + numBlanks) == valueOfCurr) {
                    
                    ns.foundElementOnCurrPage = true;
                    //   System.out.println("adding to sequence of an");
                    ns.sequence.add(w);
                    return ns;
                }
            }
        }
        //System.out.println("returning null b/c no schemes fit");
        return null;
    }
    
    public static NumScheme findRomanSequence(ArrayList<NumScheme> pps, Word w) {
//System.out.println("looking for a roman sequence");
        if (pps.size() == 0) {
            //System.out.println("returning null b/c of size");
            return null;
        }
        for (NumScheme ns : pps) {
            if (RomanNumeral.isRomanNum(ns.sequence.get(0).text) && ns.inParkLot == false && ns.foundElementOnCurrPage == false) {
                
                int currNumeral = RomanNumeral.romanToDecimal(w.text);
                int numBlanks = ns.getLastBlanks();
                int valueOfLast = RomanNumeral.romanToDecimal(ns.sequence.get(ns.sequence.size() - (1 + numBlanks)).text);
                w.isRoman =true;
                // System.out.println("word: " + w.text + " number of blanks: " + numBlanks);
                if (valueOfLast + (1 + numBlanks) == currNumeral) {
                    
                    ns.foundElementOnCurrPage = true;
                    //  System.out.println("adding to sequence of rn");
                    ns.sequence.add(w);
                    return ns;
                }
            }
        }
        //System.out.println("returning null b/c no schemes fit");
        return null;
    }
    
     public static int minDistance(String word1, String word2) {
        word1 = word1.toUpperCase();
        word2 = word2.toUpperCase();
	int len1 = word1.length();
	int len2 = word2.length();
 
	// len1+1, len2+1, because finally return dp[len1][len2]
	int[][] dp = new int[len1 + 1][len2 + 1];
 
	for (int i = 0; i <= len1; i++) {
		dp[i][0] = i;
	}
 
	for (int j = 0; j <= len2; j++) {
		dp[0][j] = j;
	}
 
	//iterate though, and check last char
	for (int i = 0; i < len1; i++) {
		char c1 = word1.charAt(i);
		for (int j = 0; j < len2; j++) {
			char c2 = word2.charAt(j);
 
			//if last two chars equal
			if (c1 == c2) {
				//update dp value for +1 length
				dp[i + 1][j + 1] = dp[i][j];
			} else {
				int replace = dp[i][j] + 1;
				int insert = dp[i][j + 1] + 1;
				int delete = dp[i + 1][j] + 1;
 
				int min = replace > insert ? insert : replace;
				min = delete > min ? min : delete;
				dp[i + 1][j + 1] = min;
			}
		}
	}
 
	return dp[len1][len2];
}
     
     public static List<Word> findHeaders (ArrayList<Pages> ph){
     ArrayList<Word> headers = new ArrayList<Word>();
     ArrayList<Word> wordsToCheck = new ArrayList<Word>();
     for(Pages p : ph){
     for (Word w : p.wordsOnPage){
     
     }
     
     }
     
     
     
     
     }
    
    public static List<NumScheme> cleanData(List<NumScheme> list) {
      //  System.out.println("cleaning");
        //only schemes larger than three, whose page number isnt bigger than the index, and whose difference between index and observed number is less than 100
        List<NumScheme> clean = new ArrayList<NumScheme>();
        
        for (NumScheme ns : list) {
            
            if (ns.sequence.size() > 3 && ns.getFirst().getOffSet() >= -1) {
                clean.add(ns);
                if (ns.getFirst().isArabic) {
                    ns.arabSeq = true;
                }
                if (ns.getFirst().isRoman) {
                    ns.romanSeq = true;
                }
            }
            
        }
        return clean;
        
    }
    
    public static NumScheme stitchMyBitchUp(List<NumScheme> lons) {
      //  System.out.println("getting stitched");
        
        NumScheme theScheme = new NumScheme();
        NumScheme start = new NumScheme();
        
        if (lons.size() < 1) {
            return null;
        } else {
            // System.out.println("calling startoff");
            start = findBegining(lons);
        }
        theScheme = start;
        List<NumScheme> earlyPages = lons.subList(0, lons.indexOf(start));
        List<NumScheme> laterPages = lons.subList(lons.indexOf(start) + 1, lons.size());
        
        for (NumScheme ns : laterPages) {
            int currOSet = theScheme.getLastNonBlank().getOffSet();
            // System.out.println("offset of last: " + currOSet);
            int lastIndex = theScheme.getLastNonBlank().index;
            // System.out.println("index of last: " + lastIndex);
            int lastPNum = theScheme.getLastNonBlank().getPageNum();
            if (ns.getFirst().getOffSet() == currOSet && sameType(theScheme, ns) == true && (ns.getFirst().getPageNum() <= lastPNum + 40 && ns.getFirst().getPageNum() >= lastPNum - 5)) {
                //  System.out.println("offset matches ");
                ns.inScheme = true;
                trimBlanks(theScheme, ns);
                theScheme.sequence.addAll(ns.sequence);
            } else if (ns.getTrueSize() >= 8) {
                //   System.out.println("true size greater than 8");
                ns.inScheme = true;
                trimBlanks(theScheme, ns);
                theScheme.sequence.addAll(ns.sequence);
            } else if (inBounds(theScheme, ns) == true && sameType(theScheme, ns) == true) {
                //    System.out.println("within last few pages and index matches");
                ns.inScheme = true;
                trimBlanks(theScheme, ns);
                theScheme.sequence.addAll(ns.sequence);
            }
        }
        
        return theScheme;
    }
    
    public static NumScheme findBegining(List<NumScheme> loss) {
        // System.out.println("in startoff");
        NumScheme temp = new NumScheme();
        Collections.sort(loss, NumScheme.indexComparator);
        Iterator<NumScheme> it = loss.listIterator();
        
        while (it.hasNext()) {
            temp = it.next();
             //  System.out.println("is this the start? " + temp.toString() + " this is it's true size: "  + temp.getTrueSize() + " and this is the offset " + temp.getFirst().getOffSet());
            if (temp.getTrueSize() >= 4 && temp.getFirst().getOffSet() >= 0) {
                
                break;
            }
            
        }
         // System.out.println("this is the start! " + temp.toString());
        return temp;
        
    }
    
    public static boolean sameType(NumScheme theScheme, NumScheme ns) {
        // System.out.println("checking type");
        if ((ns.arabSeq == true && theScheme.getLastNonBlank().isArabic == true)) {
            // System.out.println("type is correct");
            return true;
        }
        if (ns.romanSeq == true && theScheme.getLastNonBlank().isRoman == true) {
            //   System.out.println("type is correct");
            return true;
        }
        // System.out.println("type is not correct");
        return false;
    }
    
    public static boolean inBounds(NumScheme theScheme, NumScheme ns) {
        //  System.out.println("checking bounds");
        int lastPNum = theScheme.getLastNonBlank().getPageNum();
        int lastIndex = theScheme.getLastNonBlank().index;
        int upperBound = lastPNum + 15;
        int lowerBound = lastPNum - 10;
        if (ns.getFirst().getPageNum() <= upperBound && ns.getFirst().getPageNum() >= lowerBound && ns.getFirst().index > lastIndex) {
            //   System.out.println("within bounds");
            return true;
        }
// System.out.println("out of bounds");
        return false;
    }
    
    public static NumScheme trimBlanks(NumScheme scheme, NumScheme potential) {
        
        int lastIndex = scheme.getLastNonBlank().index;
        //  System.out.println("pnum and index of last nonblank: " + scheme.getLastNonBlank().text + " , " + lastIndex);
        int currIndex = potential.getFirst().index;
        // System.out.println("pnum and index trying to be added: " + potential.getFirst().text + " ,  " + currIndex);

        if (scheme.containsIndex(currIndex) > -1) {
            //  System.out.println("contains currindex");
            scheme.sequence.subList(scheme.containsIndex(currIndex), scheme.sequence.size()).clear();
        } else {
            int trimFrom = scheme.sequence.indexOf(scheme.getLastNonBlank());
            //   System.out.println("doesn't contain, trimming this: " + scheme.sequence.subList(trimFrom + 1, scheme.sequence.size()).toString());
            scheme.sequence.subList(trimFrom + 1, scheme.sequence.size()).clear();
            for (int i = lastIndex; i <= currIndex - 2; i++) {
                scheme.addBlank();
            }
        }
        // System.out.println("returning this from trim blank: " + scheme.toString());
        return scheme;
    }
    
    public static NumScheme fillInBlanks(NumScheme scheme) {
        //int trimEndBlanksFrom = scheme.sequence.size() - scheme.getLastBlanks();
        // scheme.sequence.subList(trimEndBlanksFrom, scheme.sequence.size()).clear();
        // System.out.println("filling in blanks");
        boolean convertToRoman = false;
       // Word holder = new Word();
        Word temp = new Word();
        int blankCounter = 0;
        int placeHolder = 0;
        ListIterator<Word> it = scheme.sequence.listIterator();
        
        while (it.hasNext()) {
            temp = it.next();
           // System.out.println("called next1, temp now is : " + temp.toString());
            if (temp.isBlank) {
                
                blankCounter++;
               // System.out.println("found blank # " + blankCounter);
                if (placeHolder == 0) {
                    temp = it.previous();
                    temp = it.previous(); //because java is fucking stupid i have to call previous twice to actually get the previous element....fucking retarded

                    int prev = temp.getPageNum();
                    
                    placeHolder = prev + 1;
                    temp = it.next();
                    temp = it.next();
                    
                 //   System.out.println("intializing place holder " + placeHolder);
                } else {
                    placeHolder++;
                  //  System.out.println("incrementing place holder: " + placeHolder);
                }
            } else if (!temp.isBlank) {
                
                
              //  System.out.println("found a non blank: " + temp.toString());
                if (placeHolder + 1 == temp.getPageNum() && (blankCounter <= 15 && blankCounter > 0)) { //
                 //   System.out.println("filling in " + blankCounter + " blanks");
                    
                    for (int i = blankCounter; i >= 0; i--) {
                        
                        temp = it.previous();
                        temp = it.previous();
                        temp.isBlank = false;
                        if (convertToRoman == true) {
                      //      System.out.println("for this token : " + temp.text + " convert to roman is " + convertToRoman);
                            temp.text = RomanNumeral.arabToRoman(placeHolder);
                            temp.isRoman = true;
                        } else {
                            temp.text = String.valueOf(placeHolder);
                            temp.isArabic = true;
                        }
                        placeHolder--;
                        temp = it.next();
                        
                    }
                    for (int i = blankCounter; i >= 0; i--) {
                        
                        temp = it.next();
                        
                    }
                }
                placeHolder = 0;
                blankCounter = 0;
            }
        }
        return scheme;
    }
    
    public static void printData(NumScheme ns, File file, String resultPath) throws FileNotFoundException, UnsupportedEncodingException {
        
        String writeFile = file.getName().substring(0, 6);
        String output = "";
        PrintWriter writer = new PrintWriter(resultPath + writeFile + ".txt", "UTF-8");
        output = output + ns.toTabbedString();
        
        writer.println(output);
        
        writer.close();
        
    }
    
    public static String printData(NumScheme ns, int pagesInBook) {
        String output = "";
        int pageRange = ns.getLastNonBlank().index - ns.getFirst().index;
        int percent = (int) (pageRange * 100f) / pagesInBook;
        int score = (int) (ns.getTrueSize() * 100f) / pageRange;
        
        System.out.println("page range: " + pageRange);
        System.out.println("true size: " + ns.getTrueSize());
        System.out.println("page range as a % of total pages " + percent);
        System.out.println("true size as a % of page range:  " + score);
        
        output = output + ns.toTabbedString();
        int totalCoverage = (int) (ns.getTrueSize() * 100f) / pagesInBook;
        System.out.println("total percent covered " + totalCoverage + "%");
        System.out.println(output);
        
        return output;
    }
    
    public static void main(String args[]) throws IOException, XMLStreamException {
        
        File readFile = new File(args[0]);
       // String resultPath = args[1]; // ./output-data/

        if (readFile.isDirectory()) {
            for (File file : readFile.listFiles()) {
                XMLEvent event = null;
               
                List<Pages> work = new ArrayList<Pages>();
                Preprocessing pp = new Preprocessing(file);
               //  CharSequence cat = "CaT";
                //i put this little if in so that the documents dont need to be trimmed and cleaned everytime
                //if(file.getName().contains(cat)){
                   
              //  work = pp.getPageList(event);
              //  }
                
                //else
                    work = pp.trim(event);
                

                List<NumScheme> listSchemes = sortAndSearchForSchemes(work);
                String o = "";
                for (Pages p : possHeaders) {
                    o = o + "PAGE " + p.indexNum + "\n";
                    for(Word w : p.wordsOnPage)
                        o= o + w.toTabbedString();
                }
                List<NumScheme> cleanSchemes = cleanData(listSchemes);
                TRUTH = stitchMyBitchUp(cleanSchemes);

// ???? how do i get print writer to print the file to adifferent directory. solution is below but seems hacky
                if (TRUTH != null) {
                    TRUTH = fillInBlanks(TRUTH);
              //      printData(TRUTH, file, resultPath);

                }
                
            }
            
        } else {
            
            Preprocessing pp = new Preprocessing(readFile);
            
            XMLEvent event = null;
            List<Pages> work = pp.trim(event);

        
            List<NumScheme> listSchemes = sortAndSearchForSchemes(work);
            //List<NumScheme> cleanSchemes = cleanData(listSchemes);
            String o = "";
           for (Pages p : possHeaders) {
                    o = o + "PAGE " + p.indexNum + "\n";
                    for(Word w : p.wordsOnPage)
                        o= o + w.toString();
                }
            //TRUTH = stitchMyBitchUp(cleanSchemes);
            
           // if (TRUTH != null) {
                //TRUTH = fillInBlanks(TRUTH);
               // output = output + TRUTH.toTabbedString();
           // }
            System.out.println(o);
            //  System.out.println("number of pages: " + work.size());
            //   List<NumScheme> numbering = searchForSchemes(work);
            int pagesInBook = work.size();
           // List<NumScheme> cleanSchemes = cleanData(numbering);

            // NumScheme blah = stitchMyBitchUp(cleanSchemes);
            //  if (blah != null) {
            //     blah = fillInBlanks(blah);
            //System.out.println(printData(blah, pagesInBook));
            // }
        }
    }
    
}
