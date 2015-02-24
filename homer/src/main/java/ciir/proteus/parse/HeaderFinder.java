/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ciir.proteus.parse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author bzifkin
 */
public class HeaderFinder {

    public HeaderFinder() {
    }

    public static List<Pages> findHeaders(List<Pages> pHeaders) {
        System.out.println("building headers");

        for (Pages p : pHeaders) {

            findHeaderCandidates(p, pHeaders); // here we assign the strength of words this is done as such: first a group of words that fall in similar location on neighbor pages are gathered. 
                                                //Then based on how similar those locations are each word is assigned a strength. This strength is then multiplied depending on the length of the strings and their edit distance
            sortRegionsAndLines(p); // here we put theme into regions: Top, Bottom, Sides, and then into lines.
        }
        for (Pages p : pHeaders) {

            refineCandidates(p, pHeaders);

           
        }
        
        for(Pages p : pHeaders){
         for (Word w : p.topWords) {
                putIntoLines(w, p);
            }
            for (Word w : p.botWords) {
                putIntoLines(w, p);
            }
            for (Word w : p.sideWords) {
                putIntoLines(w, p);
            }
        }
        System.out.println(" done building headers");
        return pHeaders;
    }

    public static void findHeaderCandidates(Pages p, List<Pages> pHeaders) {

        int page = pHeaders.indexOf(p);
        Pages blankPage = new Pages();
        blankPage.blank = true;
        List<Pages> pagesToCheck = new ArrayList<Pages>();
        Pages onePageAhead = (page + 1 <= pHeaders.size() - 2) ? pHeaders.get(page + 1) : blankPage;
        Pages twoPageAhead = (page + 2 <= pHeaders.size() - 2) ? pHeaders.get(page + 2) : blankPage;
        Pages onePageBehind = (page >= 1) ? pHeaders.get(page - 1) : blankPage;
        Pages twoPageBehind = (page >= 2) ? pHeaders.get(page - 2) : blankPage;
        pagesToCheck.add(onePageAhead);
        pagesToCheck.add(twoPageAhead);
        pagesToCheck.add(onePageBehind);
        pagesToCheck.add(twoPageBehind);
        int errorMargin = setErrorMargin(pagesToCheck);
        List<Word> closeWords = new ArrayList<Word>(0);
        for (Word w : p.wordsOnPage) {

            closeWords = getCloseWords(w, pagesToCheck, errorMargin);
            String op = "";
            for (Word blah : closeWords) {
                op = op + blah.toString() + "\n";
            }
            // System.out.println("for word " + w.toString() + "the close words are " + op);
            //if (closeWords == null || closeWords.size() == 0) {
            //  System.out.println("closewords was zero");
            //  p.likely.add(w);
            // } else {

            assignStrengthMultiplier(w, closeWords, errorMargin);
            // }
        }

    }

    public static int setErrorMargin(List<Pages> ptc) {

        //look within 1/10 inch of original coords
        int em = 0;
        if (ptc.get(0).blank == false) {
            em = (int) (ptc.get(0).qInch * .4);
        } else if (ptc.get(1).blank == false) {
            em = (int) (ptc.get(1).qInch * .4);
        } else if (ptc.get(2).blank == false) {
            em = (int) (ptc.get(2).qInch * .4);
        } else if (ptc.get(3).blank == false) {
            em = (int) (ptc.get(3).qInch * .4);
        } else {
            return -1;
        }
        //    System.out.println("Error margin is " + em);
        return em;
    }

    public static List<Word> getCloseWords(Word w, List<Pages> ptc, int errorMargin) {

        if (errorMargin == -1) {
            return null;
        }
        List<Word> temp = new ArrayList<Word>();
        List<Word> wordsToCheck = new ArrayList<Word>();
        wordsToCheck.addAll(ptc.get(0).wordsOnPage);
        wordsToCheck.addAll(ptc.get(1).wordsOnPage);
        wordsToCheck.addAll(ptc.get(2).wordsOnPage);
        wordsToCheck.addAll(ptc.get(3).wordsOnPage);
        for (Word wtc : wordsToCheck) {
            if ((w.xOne <= wtc.xOne + errorMargin && w.xOne >= wtc.xOne - errorMargin)
                    || (w.yOne <= wtc.yOne + errorMargin && w.yOne >= wtc.yOne - errorMargin)
                    || (w.xTwo <= wtc.xTwo + errorMargin && w.xTwo >= wtc.xTwo - errorMargin)
                    || (w.yTwo <= wtc.yTwo + errorMargin && w.yTwo >= wtc.yTwo - errorMargin)) {
                temp.add(wtc);
            }
        }
        return temp;
    }

    public static void assignStrengthMultiplier(Word w, List<Word> cWords, int errorMargin) {
        for (Word wd : cWords) {
            //System.out.println("Current Strength " + w.strength);
            if (w.text.length() <= 5 && minEditDistance(w, wd) < 1) {
                w.strength = assignStrength(w, wd, errorMargin) * 2;
                // System.out.println("the minedit dis b/w " + w.toString() + " and " + wd.toString() + " is " + minEditDistance(w, wd) + "Therefore 2 will be added to the strength");

            } else if (w.text.length() <= 5 && minEditDistance(w, wd) <= 1) {
                w.strength = assignStrength(w, wd, errorMargin) * 1;
                //System.out.println("the minedit dis b/w " + w.toString() + " and " + wd.toString() + " is " + minEditDistance(w, wd) + "Therefore 1 will be added to the strength");
            } else if ((w.text.length() == 6 || w.text.length() == 7) && minEditDistance(w, wd) < 2) {
                w.strength = assignStrength(w, wd, errorMargin) * 2;
                // System.out.println("the minedit dis b/w " + w.toString() + " and " + wd.toString() + " is " + minEditDistance(w, wd) + "Therefore 2 will be added to the strength");
            } else if ((w.text.length() == 6 || w.text.length() == 7) && minEditDistance(w, wd) <= 2) {
                w.strength = assignStrength(w, wd, errorMargin) * 1;
                // System.out.println("the minedit dis b/w " + w.toString() + " and " + wd.toString() + " is " + minEditDistance(w, wd) + "Therefore 1 will be added to the strength");
            } else if ((w.text.length() == 8 || w.text.length() == 9) && minEditDistance(w, wd) < 3) {
                w.strength = assignStrength(w, wd, errorMargin) * 2;
                // System.out.println("the minedit dis b/w " + w.toString() + " and " + wd.toString() + " is " + minEditDistance(w, wd) + "Therefore 2 will be added to the strength");
            } else if ((w.text.length() == 8 || w.text.length() == 9) && minEditDistance(w, wd) <= 3) {
                w.strength = assignStrength(w, wd, errorMargin) * 1;
                //System.out.println("the minedit dis b/w " + w.toString() + " and " + wd.toString() + " is " + minEditDistance(w, wd) + "Therefore 1 will be added to the strength");
            } else if (w.text.length() >= 10 && minEditDistance(w, wd) < 4) {
                w.strength = assignStrength(w, wd, errorMargin) * 2;
                // System.out.println("the minedit dis b/w " + w.toString() + " and " + wd.toString() + " is " + minEditDistance(w, wd) + "Therefore 2 will be added to the strength");
            } else if (w.text.length() >= 10 && minEditDistance(w, wd) <= 4) {
                w.strength = assignStrength(w, wd, errorMargin) * 1;
                //System.out.println("the minedit dis b/w " + w.toString() + " and " + wd.toString() + " is " + minEditDistance(w, wd) + "Therefore 1 will be added to the strength");
            }
        }

    }

    public static int minEditDistance(Word w1, Word w2) {
        String word1 = w1.text.toUpperCase();
        String word2 = w2.text.toUpperCase();
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

    public static double assignStrength(Word w, Word wtc, int errorMargin) {

        if ((w.xOne <= wtc.xOne + errorMargin && w.xOne >= wtc.xOne - errorMargin)) {
            w.strength = w.strength + .5;

        }
        if ((w.yOne <= wtc.yOne + errorMargin && w.yOne >= wtc.yOne - errorMargin)) {
            w.strength = w.strength + .5;
        }
        if ((w.xTwo <= wtc.xTwo + errorMargin && w.xTwo >= wtc.xTwo - errorMargin)) {
            w.strength = w.strength + .5;
        }
        if ((w.yTwo <= wtc.yTwo + errorMargin && w.yTwo >= wtc.yTwo - errorMargin)) {
            w.strength = w.strength + .5;
        }
        return w.strength;
    }

    //maybe call this method from main to make sure that its measuring/getting proper margins
    // also think about making it so that words get tagged as they come in during trimming
    public static void sortRegionsAndLines(Pages p) {
        // System.out.println("for page " + p.indexNum + "--------------------\n");
        // System.out.println("sorting regions and lines");
        for (Word w : p.wordsOnPage) {
            if (w.strength > 0.0) {
                // System.out.println("sorting " + w.toStringSC());
                //putIntoLines(w, p);
                putIntoRegions(w, p);
            }
        }

        //findFirstLine(p.top);
        // findFirstLine(p.bot);
    }

    public static void putIntoRegions(Word w, Pages p) {

        if (w.top == true) {

            p.topWords.add(w);
        }
        if (w.bottom == true) {
            //System.out.println("checking for headers in BOT");
            p.botWords.add(w);
        }
        if (w.side == true) {
            p.sideWords.add(w);
        }

    }

    public static void putIntoLines(Word w, Pages p) {
        int coord1;
        int coord2;
        Header temp = new Header();

        if (w.top == true) {
            //  System.out.println("Word is " + w.text);
            coord1 = (w.line != 0) ? w.line : w.yTwo;
            //   System.out.println("coord1 " + coord1);
            for (Header h : p.top) {

                coord2 = h.getAvgYCoord();
                //    System.out.println("coord2 " + coord2);
                if (coord2 <= coord1 + 5 && coord2 >= coord1 - 5) {
                    //  System.out.println("TOP, adding word " + w.toStringSC() + " to header " + h.toString());
                    w.inLine = true;
                    h.line.add(w);
                    break;

                }

            }
            if (w.inLine == false) {
                //  System.out.println("TOP, making a new header for " + w.text);
                temp.line.add(w);

                p.top.add(temp);
            }

        }
        if (w.bottom == true) {
            //System.out.println("checking for headers in BOT");
            coord1 = (w.line != 0) ? w.line : w.yTwo;
            // System.out.println("coord1 " + coord1);
            for (Header h : p.bot) {

                coord2 = h.getAvgYCoord();
                // System.out.println("coord2 " + coord2);
                if (coord2 <= coord1 + 5 && coord2 >= coord1 - 5) {
                    // System.out.println("BOT, adding word " + w.toStringSC() + " to header " + h.toString());
                    w.inLine = true;
                    h.line.add(w);
                    break;

                }

            }
            if (w.inLine == false) {
                // System.out.println("BOT, making a new header for " + w.toStringSC());
                temp.line.add(w);

                p.bot.add(temp);
            }

        }
        if (w.side == true) {
            // System.out.println("checking for headers in SIDE");
            coord1 = (w.line != 0) ? w.line : w.yTwo;
            // System.out.println("coord1 " + coord1);
            for (Header h : p.side) {

                coord2 = h.getAvgYCoord();
                // System.out.println("coord2 " + coord2);
                if (coord2 <= coord1 + 5 && coord2 >= coord1 - 5) {
                    //  System.out.println("SIDE, adding word " + w.toStringSC() + " to header " + h.toString());
                    w.inLine = true;
                    h.line.add(w);
                    break;

                }

            }
            if (w.inLine == false) {
                //System.out.println("SIDE   , making a new header for " + w.toStringSC());
                temp.line.add(w);

                p.side.add(temp);
            }
        }

    }

    public static void findFirstLine(List<Header> heads) {
        Collections.sort(heads, new Header.AvgYCoordComparator());

        if (heads.size() > 0) {
            if (heads.get(0).line.get(0).top == true) {
                heads.get(heads.size() - 1).firstLine = true;

            }
            if (heads.get(0).line.get(0).bottom == true) {
                heads.get(0).firstLine = true;

            }
        }

    }

    public static void refineCandidates(Pages p, List<Pages> ptc) {
        int errorMargin = setErrorMargin(ptc);
       // List<Header> topsToCheck = new ArrayList<Header>();
        //  List<Header> botsToCheck = new ArrayList<Header>();
        // List<Header> sidesToCheck = new ArrayList<Header>();

        List<Word> topWordsToCheck = new ArrayList<Word>();
        List<Word> botWordsToCheck = new ArrayList<Word>();
        List<Word> sideWordsToCheck = new ArrayList<Word>();
        //getFrontPages(p, ptc, topsToCheck, botsToCheck, sidesToCheck);
        //getBackPages(p, ptc, topsToCheck, botsToCheck, sidesToCheck);

        getFrontPages(p, ptc, topWordsToCheck, botWordsToCheck, sideWordsToCheck);
        getBackPages(p, ptc, topWordsToCheck, botWordsToCheck, sideWordsToCheck);
        /*
         for (Header h : p.top) {
         //System.out.println("For page " + p.indexNum + " there are " + topsToCheck.size() + " headers from the top, ");
         assignCount(h, topsToCheck, errorMargin);
         }
         for (Header h : p.bot) {
         // System.out.println("For page " + p.indexNum + " there are " + botsToCheck.size() + " headers from bottom ");
         assignCount(h, botsToCheck, errorMargin);
         }
         for (Header h : p.side) {
         // System.out.println("For page " + p.indexNum + " there are " + sidesToCheck.size() + " headers from the side");
         assignCount(h, sidesToCheck, errorMargin);
         }
         */

        for (Word w : p.topWords) {
            //System.out.println("For page " + p.indexNum + " there are " + topsToCheck.size() + " headers from the top, ");
            assignCount(w, topWordsToCheck, errorMargin);
        }
        for (Word w : p.botWords) {
            // System.out.println("For page " + p.indexNum + " there are " + botsToCheck.size() + " headers from bottom ");
            assignCount(w, botWordsToCheck, errorMargin);
        }
        for (Word w : p.sideWords) {
            // System.out.println("For page " + p.indexNum + " there are " + sidesToCheck.size() + " headers from the side");
            assignCount(w, sideWordsToCheck, errorMargin);
        }
    }
    /*
     public static void getFrontPages(Pages p, List<Pages> ptc, List<Header> top, List<Header> bot, List<Header> side) {
     int page = ptc.indexOf(p);
     // System.out.println("getting front pages ");
     int range = (page + 25 <= ptc.size() - 1) ? page + 25 : ptc.size() - 1;
     Pages pg = new Pages();
     for (int i = range; i >= page; i--) {
     pg = ptc.get(i);
     Collections.sort(pg.top, new Header.HeaderStrengthComparator());
     Collections.sort(pg.bot, new Header.HeaderStrengthComparator());
     Collections.sort(pg.side, new Header.HeaderStrengthComparator());
     int topSize = (pg.top.size() >= 5) ? 4 : pg.top.size() - 1;
     int botSize = (pg.bot.size() >= 5) ? 4 : pg.bot.size() - 1;
     int sideSize = (pg.side.size() >= 5) ? 4 : pg.side.size() - 1;
     for (int x = 0; x <= topSize; x++) {
     //System.out.println("From front add " + pg.top.get(x).toStringSC());
     top.add(pg.top.get(x));
     }
     for (int x = 0; x <= botSize; x++) {
     //ystem.out.println("From front add " + pg.bot.get(x).toStringSC());
     bot.add(pg.bot.get(x));
     }
     for (int x = 0; x <= sideSize; x++) {
     //System.out.println("From front add " + pg.side.get(x).toStringSC());
     side.add(pg.side.get(x));
     }
     }
     //   System.out.println("got front pages ");
     }

     public static void getBackPages(Pages p, List<Pages> ptc, List<Header> top, List<Header> bot, List<Header> side) {
     int page = ptc.indexOf(p);
     //   System.out.println("getting back pages ");
     int range = (page >= 25) ? page - 25 : 0;
     Pages pg = new Pages();

     for (int i = range; i <= page; i++) {
     pg = ptc.get(i);
     Collections.sort(pg.top, new Header.HeaderStrengthComparator());
     Collections.sort(pg.bot, new Header.HeaderStrengthComparator());
     Collections.sort(pg.side, new Header.HeaderStrengthComparator());
     int topSize = (pg.top.size() >= 5) ? 4 : pg.top.size() - 1;
     int botSize = (pg.bot.size() >= 5) ? 4 : pg.bot.size() - 1;
     int sideSize = (pg.side.size() >= 5) ? 4 : pg.side.size() - 1;
     for (int x = 0; x <= topSize; x++) {
     //  System.out.println("From back add " + pg.top.get(x).toStringSC());
     top.add(pg.top.get(x));
     }
     for (int x = 0; x <= botSize; x++) {
     // System.out.println("From back add " + pg.bot.get(x).toStringSC());
     bot.add(pg.bot.get(x));
     }
     for (int x = 0; x <= sideSize; x++) {
     // System.out.println("From back add " + pg.side.get(x).toStringSC());
     side.add(pg.side.get(x));
     }
     }
     //  System.out.println("got back pages ");
     }*/

    public static void getFrontPages(Pages p, List<Pages> ptc, List<Word> top, List<Word> bot, List<Word> side) {
        int page = ptc.indexOf(p);
        // System.out.println("getting front pages ");
        int range = (page + 25 <= ptc.size() - 1) ? page + 25 : ptc.size() - 1;
        Pages pg = new Pages();
        for (int i = range; i >= page; i--) {
            pg = ptc.get(i);
            Collections.sort(pg.topWords, new Word.WordStrengthComparator());
            Collections.sort(pg.botWords, new Word.WordStrengthComparator());
            Collections.sort(pg.sideWords, new Word.WordStrengthComparator());
            int topSize = (pg.topWords.size() >= 5) ? 4 : pg.top.size() - 1;
            int botSize = (pg.botWords.size() >= 5) ? 4 : pg.bot.size() - 1;
            int sideSize = (pg.sideWords.size() >= 5) ? 4 : pg.side.size() - 1;
            for (int x = 0; x <= topSize; x++) {
                //System.out.println("From front add " + pg.top.get(x).toStringSC());
                top.add(pg.topWords.get(x));
            }
            for (int x = 0; x <= botSize; x++) {
                //ystem.out.println("From front add " + pg.bot.get(x).toStringSC());
                bot.add(pg.botWords.get(x));
            }
            for (int x = 0; x <= sideSize; x++) {
                //System.out.println("From front add " + pg.side.get(x).toStringSC());
                side.add(pg.sideWords.get(x));
            }
        }
        //   System.out.println("got front pages ");
    }

    public static void getBackPages(Pages p, List<Pages> ptc, List<Word> top, List<Word> bot, List<Word> side) {
        int page = ptc.indexOf(p);
        //   System.out.println("getting back pages ");
        int range = (page >= 25) ? page - 25 : 0;
        Pages pg = new Pages();

        for (int i = range; i <= page; i++) {
            pg = ptc.get(i);
            Collections.sort(pg.topWords, new Word.WordStrengthComparator());
            Collections.sort(pg.botWords, new Word.WordStrengthComparator());
            Collections.sort(pg.sideWords, new Word.WordStrengthComparator());
            int topSize = (pg.top.size() >= 5) ? 4 : pg.top.size() - 1;
            int botSize = (pg.bot.size() >= 5) ? 4 : pg.bot.size() - 1;
            int sideSize = (pg.side.size() >= 5) ? 4 : pg.side.size() - 1;
            for (int x = 0; x <= topSize; x++) {
                //  System.out.println("From back add " + pg.top.get(x).toStringSC());
                top.add(pg.topWords.get(x));
            }
            for (int x = 0; x <= botSize; x++) {
                // System.out.println("From back add " + pg.bot.get(x).toStringSC());
                bot.add(pg.botWords.get(x));
            }
            for (int x = 0; x <= sideSize; x++) {
                // System.out.println("From back add " + pg.side.get(x).toStringSC());
                side.add(pg.sideWords.get(x));
            }
        }
        //  System.out.println("got back pages ");
    }
    /*
     public static void assignCount(Header h, List<Header> cHeaders, int errorMargin) {
     Word w;
     Word wd;
     // System.out.println("Assigning count for " + h.toString());
     for (Header hd : cHeaders) {
     // System.out.println("checking against " + hd.toString());
     if (h.line.size() == hd.line.size()) {
     // System.out.println("size is same");
     for (int i = 0; i <= h.line.size() - 1; i++) {
     w = h.line.get(i);
     wd = hd.line.get(i);

     if (sameLocation(w, wd, errorMargin) == true) {
     //    System.out.println("word " + w.text + " and word " + wd.text + " are in the same location");

     if (w.text.length() <= 5 && minEditDistance(w, wd) < 1) {
     w.count = w.count + 2;
     //      System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
     } else if (w.text.length() <= 5 && minEditDistance(w, wd) <= 1) {
     w.count = w.count + 1;
     //    System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
     } else if ((w.text.length() == 6 || w.text.length() == 7) && minEditDistance(w, wd) < 2) {
     w.count = w.count + 2;
     //     System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
     } else if ((w.text.length() == 6 || w.text.length() == 7) && minEditDistance(w, wd) <= 2) {
     w.count = w.count + 1;
     //  System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
     } else if ((w.text.length() == 8 || w.text.length() == 9) && minEditDistance(w, wd) < 3) {
     w.count = w.count + 2;
     //  System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
     } else if ((w.text.length() == 8 || w.text.length() == 9) && minEditDistance(w, wd) <= 3) {
     w.count = w.count + 1;
     // System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
     } else if (w.text.length() >= 10 && minEditDistance(w, wd) < 4) {
     w.count = w.count + 2;
     // System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
     } else if (w.text.length() >= 10 && minEditDistance(w, wd) <= 4) {
     w.count = w.count + 1;
     // System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
     }
     }
     }
     }
     }

     }
     */

    public static void assignCount(Word w, List<Word> cHeaders, int errorMargin) {

        // System.out.println("Assigning count for " + h.toString());
        for (Word wd : cHeaders) {

            if (sameLocation(w, wd, errorMargin) == true) {
                //    System.out.println("word " + w.text + " and word " + wd.text + " are in the same location");

                if (w.text.length() <= 5 && minEditDistance(w, wd) < 1) {
                    w.count = w.count + 2;
                    //      System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
                } else if (w.text.length() <= 5 && minEditDistance(w, wd) <= 1) {
                    w.count = w.count + 1;
                    //    System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
                } else if ((w.text.length() == 6 || w.text.length() == 7) && minEditDistance(w, wd) < 2) {
                    w.count = w.count + 2;
                    //     System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
                } else if ((w.text.length() == 6 || w.text.length() == 7) && minEditDistance(w, wd) <= 2) {
                    w.count = w.count + 1;
                    //  System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
                } else if ((w.text.length() == 8 || w.text.length() == 9) && minEditDistance(w, wd) < 3) {
                    w.count = w.count + 2;
                    //  System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
                } else if ((w.text.length() == 8 || w.text.length() == 9) && minEditDistance(w, wd) <= 3) {
                    w.count = w.count + 1;
                    // System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
                } else if (w.text.length() >= 10 && minEditDistance(w, wd) < 4) {
                    w.count = w.count + 2;
                    // System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
                } else if (w.text.length() >= 10 && minEditDistance(w, wd) <= 4) {
                    w.count = w.count + 1;
                    // System.out.println("incrementing count, current " + w.toLineString() + " COUNT " + w.count);
                }
            }
        }

    }

    public static boolean sameLocation(Word w, Word wtc, int errorMargin) {
        if ((w.xOne <= wtc.xOne + errorMargin && w.xOne >= wtc.xOne - errorMargin)
                || (w.yOne <= wtc.yOne + errorMargin && w.yOne >= wtc.yOne - errorMargin)
                || (w.xTwo <= wtc.xTwo + errorMargin && w.xTwo >= wtc.xTwo - errorMargin)
                || (w.yTwo <= wtc.yTwo + errorMargin && w.yTwo >= wtc.yTwo - errorMargin)) {
            return true;
        } else {
            return false;
        }
    }

    public static void printData(List<Pages> pHeaders, File file, String resultPath) throws FileNotFoundException, UnsupportedEncodingException {

        String writeFile = file.getName().substring(0, 6);
        String output = "";
        PrintWriter writer = new PrintWriter(resultPath + writeFile + ".txt", "UTF-8");

        System.out.println("printing : " + resultPath + writeFile + ".txt");

        String o = "";

        for (Pages p : pHeaders) {

            Collections.sort(p.top, new Header.HeaderCountComparator());
            Collections.sort(p.bot, new Header.HeaderCountComparator());
            Collections.sort(p.side, new Header.HeaderCountComparator());

           // o = o + " ON PAGE " + p.indexNum + "\n";
            //  o = o + " The top headers are: \n";
             for (Header h : p.top) {
            //if (h.firstLine == true) {
            if (p.top.size() > 0) {
                o = o + p.indexNum + "\t" + "top\t" + p.top.get(0).toSimpleString();
                // int blah = (p.top.size() >= 3) ? 3 : p.top.size();
                //for (int i = 0; i <= blah - 1; i++) {
                //  o = o + p.top.get(i).toSimpleString();
                //o = o + p.indexNum + "\t" +"top\t" + h.toSimpleString();

                o = o + "\n";

            }
           }
            // o = o + " The bot headers are: \n";
            for (Header hh : p.bot) {

            // if (hh.firstLine == true) {
            if (p.bot.size() > 0) {
                o = o + p.indexNum + "\t" + "bot\t" + p.bot.get(0).toSimpleString();
                // int blah = (p.bot.size() >= 3) ? 3 : p.bot.size();
                // for (int i = 0; i <= blah - 1; i++) {
                //   o = o + p.bot.get(i).toSimpleString();
                // o = o + p.indexNum + "\t" +"bot\t" + hh.toSimpleString();
                o = o + "\n";

            }
            }
            /*
             o = o + " The side headers are: \n";
             //for (Header hhh : p.side) {

             //if (hhh.firstLine == true) {
             if (p.side.size() > 0) {
             int blah = (p.side.size() >= 3) ? 3 : p.side.size();
             for (int i = 0; i <= blah - 1; i++) {
             o = o + p.side.get(i).toSimpleString();
             o = o + "\n";

             }
             }
             */
            //o = o + "---------------------------------------------------------------------------------\n";
        }

        writer.println(o);
        writer.flush();

        writer.close();

    }

    public static String singleFilePrintData(List<Pages> pHeaders) {
        String o = "";

        for (Pages p : pHeaders) {

            Collections.sort(p.top, new Header.HeaderCountComparator());
            Collections.sort(p.bot, new Header.HeaderCountComparator());
            Collections.sort(p.side, new Header.HeaderCountComparator());

            o = o + " ON PAGE " + p.indexNum + "\n";
            o = o + " The top headers are: \n";
            //writer.println(o);
            // for (Header h : p.top) {

            // if (h.firstLine == true) {
            if (p.top.size() > 0) {
                o = o + p.top.get(0).toSimpleString();
                // int blah = (p.top.size() >= 3) ? 3 : p.top.size();
                // for (int i = 0; i <= blah - 1; i++) {
                // o = o + p.top.get(i).toSimpleString();
                //o = o + h.toSimpleString();

                o = o + "\n";

                // }
            }
            o = o + " The bot headers are: \n";
            //for (Header hh : p.bot) {

            //if (hh.firstLine == true) {
            if (p.bot.size() > 0) {
                o = o + p.bot.get(0).toSimpleString();
                //int blah = (p.bot.size() >= 3) ? 3 : p.bot.size();
                //for (int i = 0; i <= blah - 1; i++) {
                // o = o + p.bot.get(i).toSimpleString();
                //o = o + hh.toSimpleString();
                o = o + "\n";

                //}
            }
            o = o + " The side headers are: \n";
              //for (Header hhh : p.side) {

            //if (hhh.firstLine == true) {
            if (p.side.size() > 0) {
                int blah = (p.side.size() >= 3) ? 3 : p.side.size();
                for (int i = 0; i <= blah - 1; i++) {
                    o = o + p.side.get(i).toSimpleString();
                    o = o + "\n";

                }
            }
            o = o + "---------------------------------------------------------------------------------\n";
        }
        System.out.println(o);
        return o;
    }

}
