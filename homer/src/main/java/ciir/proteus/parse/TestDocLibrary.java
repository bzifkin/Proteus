/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciir.proteus.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author bzifkin
 */
public class TestDocLibrary {

    static List<NumScheme> bookSchemes = new ArrayList<NumScheme>();

    public TestDocLibrary() {
    }

    public static String compareAndScore(NumScheme man, NumScheme machine) {
        int missingPages = 0;
        int blanksCorrect = 0;
        int missedBlanks = 0;
        int foundNothing = 0;
        int mismatch = 0;
        int correct = 0;
        int wentToHigh = 0;
        int wentToLow = 0;
        String result = new String();
        for (Word w : man.sequence) {
            int ind = machine.containsIndex(w.index);
            Word obs = new Word();
            if (ind == -1) {
                missingPages++;
                //String miss = "missing page " + w.toString() + "\n";
                //result += miss;
            } else {
                obs = machine.sequence.get(ind);

                if (w.isBlank && w.compareTo(obs) == 0) {
                    blanksCorrect++;

                } else if (w.compareTo(obs) == 0) {
                    correct++;
                } else if (!w.isBlank && obs.isBlank) {
                    foundNothing++;
                    // String miss = "found " + obs.toString() + " should have found " + w.toString() + "\n";
                    // result += miss;

                } else if (w.isBlank && !obs.isBlank) {
                    missedBlanks++;
                    // String miss = "found " + obs.toString() + " should have found " + w.toString() + "\n";
                    // result += miss;

                } else if (w.compareTo(obs) == -1) {
                    wentToHigh++;
                    //  String miss = "found " + obs.toString() + " should have found " + w.toString() + "\n";
                    // result += miss;
                } else if (w.compareTo(obs) == 1) {
                    wentToLow++;
                    String miss = "found " + obs.toString() + " should have found " + w.toString() + "\n";
                    result += miss;
                }

            }

        }

        int totalCorrect = correct + blanksCorrect;
        int percentCorrect = (int) (totalCorrect * 100f) / man.sequence.size();

        result += "For book " + man.title + " there were " + totalCorrect + " / " + machine.sequence.size() + " for " + percentCorrect + "%  of " + man.sequence.size() + "\n There were " + missingPages
                + " missing pages\t" + foundNothing + " cases where nothing was found\t" + missedBlanks + " missed blanks\t" + wentToHigh + " too highs\t" + wentToLow + " too lows and \t" + mismatch + " mismatches\n";

        return result;
    }

    public static boolean checkTypes(Word exp, Word obs) {
        if (exp.isBlank == true && obs.isBlank == true) {
            return true;
        } else if (exp.isRoman == true && obs.isRoman == true) {
            return true;
        } else if (exp.isArabic == true && obs.isArabic == true) {
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        NumScheme alib = new NumScheme();
        alib.title = "alibra";
        alib.sequence.add(new Word("VI", 13));
        alib.sequence.add(new Word("VII", 14));
        alib.sequence.add(new Word("VIII", 15));
        alib.sequence.add(new Word("IX", 16));
        alib.sequence.add(new Word("X", 17));
        alib.sequence.add(new Word("XI", 18));
        alib.sequence.add(new Word("XII", 19));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("3", 22));
        alib.sequence.add(new Word("4", 23));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("5", 26));
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("26", 47));
        alib.sequence.add(new Word("27", 48));
        alib.sequence.add(new Word("30", 49));
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("51", 70));
        alib.sequence.add(new Word("52", 71));
        alib.sequence.add(new Word("53", 72));
        alib.sequence.add(new Word("54", 73));
        alib.sequence.add(new Word("55", 74));
        alib.sequence.add(new Word("56", 75));
        alib.sequence.add(new Word("57", 76));
        alib.sequence.add(new Word("58", 77));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("59", 80));
        alib.addTen();
        alib.sequence.add(new Word("70", 91));
        alib.sequence.add(new Word("71", 92));
        alib.sequence.add(new Word("72", 93));
        alib.sequence.add(new Word("73", 94));
        alib.sequence.add(new Word("74", 95));
        alib.sequence.add(new Word("75", 96));
        alib.sequence.add(new Word("76", 97));
        alib.sequence.add(new Word("77", 98));
        alib.sequence.add(new Word("78", 99));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("79", 102));
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("100", 123));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("101", 126));
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("122", 147));
        alib.sequence.add(new Word("123", 148));
        alib.sequence.add(new Word("124", 149));
        alib.sequence.add(new Word("125", 150));
        alib.sequence.add(new Word("126", 151));
        alib.sequence.add(new Word("127", 152));
        alib.sequence.add(new Word("128", 153));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("129", 156));
        alib.addFifty();
        alib.addTen();
        alib.addTen();
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("220", 247));
        alib.sequence.add(new Word("221", 248));
        alib.sequence.add(new Word("222", 249));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("223", 252));
        alib.addTen();
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("254", 283));
        alib.sequence.add(new Word("255", 284));
        alib.sequence.add(new Word("256", 285));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("257", 288));
        alib.addTen();
        alib.sequence.add(new Word("268", 299));
        alib.sequence.add(new Word("269", 300));
        alib.sequence.add(new Word("270", 301));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("271", 304));
        alib.sequence.add(new Word("272", 305));
        alib.sequence.add(new Word("273", 306));
        alib.sequence.add(new Word("274", 307));
        alib.sequence.add(new Word("275", 308));
        alib.sequence.add(new Word("276", 309));
        alib.sequence.add(new Word("277", 310));
        alib.sequence.add(new Word("278", 311));
        alib.sequence.add(new Word("279", 312));
        alib.sequence.add(new Word("280", 313));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("281", 316));
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("302", 337));
        alib.sequence.add(new Word("303", 338));
        alib.sequence.add(new Word("304", 339));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("305", 342));
        alib.addTen();
        alib.addTen();
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("346", 383));
        alib.sequence.add(new Word("347", 384));
        alib.sequence.add(new Word("348", 385));
        alib.sequence.add(new Word("349", 386));
        alib.sequence.add(new Word("350", 387));
        alib.sequence.add(new Word("351", 388));
        alib.sequence.add(new Word("352", 389));
        alib.sequence.add(new Word("353", 390));
        alib.sequence.add(new Word("354", 391));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("355", 394));
        alib.addFifty();
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("426", 465));
        alib.sequence.add(new Word("427", 466));
        alib.sequence.add(new Word("428", 467));
        alib.sequence.add(new Word("429", 468));
        alib.addBlank();
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("431", 472));
        alib.addFifty();
        alib.sequence.add(new Word("482", 523));
        alib.sequence.add(new Word("483", 524));
        alib.sequence.add(new Word("484", 525));
        alib.sequence.add(new Word("485", 526));
        alib.sequence.add(new Word("486", 527));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("487", 530));
        alib.addTen();
        alib.addTen();
        alib.addTen();
        alib.sequence.add(new Word("518", 561));
        alib.sequence.add(new Word("519", 562));
        alib.sequence.add(new Word("520", 563));
        alib.addBlank();
        alib.addBlank();
        alib.sequence.add(new Word("521", 566));
        alib.addTen();
        alib.sequence.add(new Word("532", 577));
        alib.sequence.add(new Word("533", 578));
        alib.sequence.add(new Word("534", 579));
        alib.sequence.add(new Word("535", 580));
        alib.sequence.add(new Word("536", 581));
        alib.sequence.add(new Word("537", 582));
        alib.sequence.add(new Word("538", 583));

        NumScheme aness = new NumScheme();
        aness.title = "anessa";

        aness.sequence.add(new Word("V", 7));
        aness.sequence.add(new Word("VI", 8));
        aness.sequence.add(new Word("VII", 9));
        aness.sequence.add(new Word("VIII", 10));
        aness.sequence.add(new Word("IX", 11));
        aness.sequence.add(new Word("X", 12));
        aness.sequence.add(new Word("XI", 13));
        aness.sequence.add(new Word("XII", 14));
        aness.sequence.add(new Word("XIII", 15));
        aness.sequence.add(new Word("XIV", 16));
        aness.sequence.add(new Word("XV", 17));
        aness.sequence.add(new Word("XVI", 18));
        aness.sequence.add(new Word("XVII", 19));
        aness.sequence.add(new Word("XVIII", 20));
        aness.sequence.add(new Word("XIX", 21));
        aness.addBlank();
        aness.addBlank();
        aness.addBlank();
        aness.sequence.add(new Word("3", 25));
        aness.addTen();
        aness.addTen();
        aness.sequence.add(new Word("24", 46));
        aness.sequence.add(new Word("25", 47));
        aness.sequence.add(new Word("26", 48));
        aness.sequence.add(new Word("27", 49));
        aness.sequence.add(new Word("28", 50));
        aness.sequence.add(new Word("29", 51));
        aness.sequence.add(new Word("30", 52));
        aness.sequence.add(new Word("31", 53));
        aness.sequence.add(new Word("32", 54));
        aness.addBlank();
        aness.addBlank();
        aness.sequence.add(new Word("35", 57));
        aness.addFifty();
        aness.addFifty();
        aness.addTen();
        aness.addTen();
        aness.addTen();
        aness.addTen();
        aness.sequence.add(new Word("176", 198));
        aness.sequence.add(new Word("177", 199));
        aness.sequence.add(new Word("178", 200));
        aness.addBlank();
        aness.addBlank();
        aness.sequence.add(new Word("181", 203));
        aness.addTen();
        aness.addTen();
        aness.sequence.add(new Word("202", 224));
        aness.sequence.add(new Word("203", 225));
        aness.sequence.add(new Word("204", 226));
        aness.sequence.add(new Word("205", 227));
        aness.sequence.add(new Word("206", 228));
        aness.sequence.add(new Word("207", 229));
        aness.sequence.add(new Word("208", 230));
        aness.sequence.add(new Word("209", 231));
        aness.addBlank();
        aness.addBlank();
        aness.addBlank();
        aness.sequence.add(new Word("213", 235));
        aness.sequence.add(new Word("214", 236));
        aness.sequence.add(new Word("215", 237));
        aness.sequence.add(new Word("216", 238));
        aness.sequence.add(new Word("217", 239));
        aness.sequence.add(new Word("218", 240));
        aness.sequence.add(new Word("219", 241));
        aness.addBlank();
        aness.addBlank();
        //from this point my alg is incorrect because the DJVU docs dont include read the #

        /*
         aness.sequence.add(new Word("2", 244));
         aness.addTen();
         aness.addTen();
         aness.sequence.add(new Word("23", 265));
         aness.sequence.add(new Word("24", 266));
         aness.sequence.add(new Word("25", 267));
         aness.sequence.add(new Word("26", 268));
         aness.sequence.add(new Word("27", 269));
         aness.sequence.add(new Word("28", 270));
         aness.sequence.add(new Word("29", 271));
         aness.sequence.add(new Word("30", 272));
         aness.sequence.add(new Word("31", 273));
         */
        NumScheme annual = new NumScheme();
        annual.title = "annual";

        annual.sequence.add(new Word("4", 5));
        annual.addFifty();
        annual.addFifty();
        annual.addFifty();
        annual.addFifty();
        annual.sequence.add(new Word("205", 206));
        annual.sequence.add(new Word("206", 207));
        annual.sequence.add(new Word("207", 208));
        annual.sequence.add(new Word("208", 209));
        annual.sequence.add(new Word("209", 210));
        annual.sequence.add(new Word("210", 211));
        annual.sequence.add(new Word("211", 212));
        annual.sequence.add(new Word("212", 213));

        //from this point my alg is incorrect because the format is s-# so it doesnt cout
        annual.sequence.add(new Word("1", 214));
        annual.addFifty();
        annual.sequence.add(new Word("52", 265));
        annual.sequence.add(new Word("53", 266));
        annual.sequence.add(new Word("54", 267));
        annual.sequence.add(new Word("55", 268));
        annual.sequence.add(new Word("56", 269));
        annual.sequence.add(new Word("57", 270));
        annual.sequence.add(new Word("58", 271));
        annual.sequence.add(new Word("59", 272));
        annual.sequence.add(new Word("60", 273));

        NumScheme belf = new NumScheme();
        belf.title = "belfry";
        belf.sequence.add(new Word("3", 10));
        belf.addFifty();
        belf.addFifty();
        belf.sequence.add(new Word("104", 111));
        belf.sequence.add(new Word("105", 112));
        belf.sequence.add(new Word("106", 113));
        belf.sequence.add(new Word("107", 114));
        belf.sequence.add(new Word("108", 115));
        belf.sequence.add(new Word("109", 116));
        belf.sequence.add(new Word("110", 117));
        belf.sequence.add(new Word("111", 118));
        belf.sequence.add(new Word("112", 119));
        belf.addBlank();
        belf.addBlank();
        belf.sequence.add(new Word("115", 122));
        belf.addFifty();
        belf.addFifty();
        belf.addTen();
        belf.addTen();
        belf.sequence.add(new Word("236", 243));
        belf.sequence.add(new Word("237", 244));
        belf.sequence.add(new Word("238", 245));
        belf.sequence.add(new Word("239", 246));
        belf.sequence.add(new Word("240", 247));
        belf.sequence.add(new Word("241", 248));
        belf.sequence.add(new Word("242", 249));
        belf.sequence.add(new Word("243", 250));
        belf.sequence.add(new Word("244", 251));
        belf.addBlank();
        belf.addBlank();
        belf.sequence.add(new Word("247", 254));
        belf.addFifty();
        belf.addTen();
        belf.addTen();
        belf.addTen();
        belf.sequence.add(new Word("328", 335));
        belf.sequence.add(new Word("329", 336));
        belf.sequence.add(new Word("330", 337));
        belf.sequence.add(new Word("331", 338));
        belf.sequence.add(new Word("332", 339));

        NumScheme bie = new NumScheme();
        bie.title = "bienni";
        bie.sequence.add(new Word("4", 9));
        bie.sequence.add(new Word("5", 10));
        bie.sequence.add(new Word("6", 11));
        bie.sequence.add(new Word("7", 12));
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.sequence.add(new Word("16", 21));
        bie.addTen();
        bie.sequence.add(new Word("27", 32));
        bie.sequence.add(new Word("28", 33));
        bie.sequence.add(new Word("29", 34));
        bie.sequence.add(new Word("28", 35));
        bie.sequence.add(new Word("29", 36));
        bie.sequence.add(new Word("30", 37));
        bie.sequence.add(new Word("31", 38));
        bie.addTen();
        bie.addTen();
        bie.addTen();
        bie.addTen();
        bie.sequence.add(new Word("72", 79));
        bie.sequence.add(new Word("73", 80));
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.sequence.add(new Word("78", 85));
        bie.addTen();
        bie.sequence.add(new Word("89", 96));
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.sequence.add(new Word("94", 101));
        bie.addFifty();
        bie.sequence.add(new Word("145", 152));
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.sequence.add(new Word("150", 157));
        bie.sequence.add(new Word("151", 158));
        bie.sequence.add(new Word("152", 159));
        bie.sequence.add(new Word("153", 160));
        bie.sequence.add(new Word("154", 161));
        bie.sequence.add(new Word("155", 162));
        bie.sequence.add(new Word("156", 163));
        bie.addBlank();
        bie.addBlank();
        bie.addBlank();
        bie.sequence.add(new Word("160", 167));
        bie.addTen();
        bie.sequence.add(new Word("171", 178));
        bie.sequence.add(new Word("172", 179));
        bie.sequence.add(new Word("173", 180));
        bie.sequence.add(new Word("174", 181));
        bie.sequence.add(new Word("175", 182));
        bie.addBlank();
        bie.addBlank();
        bie.sequence.add(new Word("178", 185));
        bie.addTen();
        bie.addTen();
        bie.addTen();
        bie.sequence.add(new Word("209", 216));
        bie.sequence.add(new Word("210", 217));
        bie.sequence.add(new Word("211", 218));
        bie.sequence.add(new Word("212", 219));
        bie.sequence.add(new Word("213", 220));

        NumScheme book = new NumScheme();
        book.title = "bookof";
        book.sequence.add(new Word("VIII", 9));
        book.sequence.add(new Word("IX", 10));
        book.sequence.add(new Word("X", 11));
        book.sequence.add(new Word("XI", 12));
        book.sequence.add(new Word("XII", 13));
        book.sequence.add(new Word("2", 14));
        book.sequence.add(new Word("14", 15));
        book.addFifty();
        book.addFifty();
        book.addFifty();
        book.addFifty();
        book.addTen();
        book.addTen();
        book.addTen();
        book.sequence.add(new Word("245", 246));
        book.sequence.add(new Word("246", 247));
        book.sequence.add(new Word("247", 248));
        book.sequence.add(new Word("248", 249));
        book.sequence.add(new Word("1", 250));
        book.addTen();
        book.addTen();
        book.addTen();
        book.addTen();
        book.sequence.add(new Word("42", 291));
        book.sequence.add(new Word("43", 292));
        book.sequence.add(new Word("44", 293));
        book.sequence.add(new Word("45", 294));
        book.sequence.add(new Word("46", 295));

        NumScheme brow = new NumScheme();
        brow.title = "browna";
        brow.sequence.add(new Word("4", 7));
        brow.sequence.add(new Word("5", 8));
        brow.addBlank();
        brow.sequence.add(new Word("7", 10));
        brow.addBlank();
        brow.sequence.add(new Word("9", 12));
        brow.addTen();
        brow.sequence.add(new Word("20", 23));
        brow.sequence.add(new Word("21", 24));
        brow.addBlank();
        brow.sequence.add(new Word("23", 26));
        brow.sequence.add(new Word("24", 27));
        brow.sequence.add(new Word("25", 28));
        brow.sequence.add(new Word("26", 29));
        brow.sequence.add(new Word("27", 30));
        brow.addBlank();
        brow.addBlank();
        brow.sequence.add(new Word("30", 33));
        brow.sequence.add(new Word("31", 34));
        brow.sequence.add(new Word("32", 35));
        brow.sequence.add(new Word("33", 36));
        brow.sequence.add(new Word("34", 37));
        brow.sequence.add(new Word("35", 38));
        brow.addBlank();
        brow.sequence.add(new Word("37", 40));
        brow.sequence.add(new Word("38", 41));
        brow.sequence.add(new Word("39", 42));
        brow.addBlank();
        brow.sequence.add(new Word("41", 44));
        brow.addBlank();
        brow.sequence.add(new Word("43", 46));
        brow.sequence.add(new Word("44", 47));
        brow.sequence.add(new Word("45", 48));
        brow.sequence.add(new Word("46", 49));
        brow.sequence.add(new Word("47", 50));
        brow.sequence.add(new Word("48", 51));
        brow.sequence.add(new Word("49", 52));
        brow.addBlank();
        brow.sequence.add(new Word("51", 54));
        brow.sequence.add(new Word("52", 55));
        brow.sequence.add(new Word("53", 56));
        brow.sequence.add(new Word("54", 57));
        brow.sequence.add(new Word("55", 58));
        brow.sequence.add(new Word("56", 59));

        NumScheme cair = new NumScheme();
        cair.title = "cairng";
        cair.sequence.add(new Word("2", 12));
        cair.addFifty();
        cair.addFifty();
        cair.addTen();
        cair.sequence.add(new Word("113", 123));
        cair.sequence.add(new Word("114", 124));
        cair.sequence.add(new Word("115", 125));
        cair.sequence.add(new Word("116", 126));
        cair.sequence.add(new Word("117", 127));
        cair.sequence.add(new Word("118", 128));
        cair.sequence.add(new Word("119", 129));
        cair.sequence.add(new Word("120", 130));
        cair.addBlank();
        cair.sequence.add(new Word("2", 132));
        cair.addTen();
        cair.sequence.add(new Word("13", 143));
        cair.sequence.add(new Word("14", 144));
        cair.sequence.add(new Word("15", 145));
        cair.sequence.add(new Word("16", 146));
        cair.sequence.add(new Word("17", 147));
        cair.sequence.add(new Word("18", 148));
        cair.sequence.add(new Word("19", 149));

        NumScheme chem = new NumScheme();
        chem.title = "chemic";
        chem.sequence.add(new Word("VI", 9));
        chem.addBlank();
        chem.addBlank();
        chem.addBlank();
        chem.sequence.add(new Word("2", 13));
        chem.sequence.add(new Word("3", 14));
        chem.sequence.add(new Word("4", 15));
        chem.sequence.add(new Word("5", 16));
        chem.sequence.add(new Word("6", 17));
        chem.sequence.add(new Word("7", 18));
        chem.sequence.add(new Word("8", 19));
        chem.sequence.add(new Word("9", 20));
        chem.sequence.add(new Word("10", 21));
        chem.sequence.add(new Word("11", 24));
        chem.addFifty();
        chem.addFifty();
        chem.addFifty();
        chem.addFifty();
        chem.addFifty();
        chem.addFifty();
        chem.addTen();
        chem.addTen();
        chem.addTen();
        chem.addTen();
        chem.addTen();
        chem.sequence.add(new Word("362", 375));
        chem.sequence.add(new Word("363", 376));
        chem.sequence.add(new Word("364", 377));
        chem.sequence.add(new Word("365", 378));
        chem.sequence.add(new Word("366", 379));
        chem.sequence.add(new Word("367", 380));
        chem.sequence.add(new Word("368", 381));
        chem.sequence.add(new Word("369", 382));
        chem.sequence.add(new Word("370", 383));

        NumScheme clas = new NumScheme();
        clas.title = "classi";
        clas.sequence.add(new Word("9", 12));
        clas.addFifty();
        clas.addFifty();
        clas.addFifty();
        clas.addTen();
        clas.addTen();
        clas.addTen();
        clas.sequence.add(new Word("190", 193));
        clas.sequence.add(new Word("191", 194));
        clas.sequence.add(new Word("192", 195));
        clas.sequence.add(new Word("193", 196));
        clas.sequence.add(new Word("194", 197));
        clas.sequence.add(new Word("195", 198));
        clas.sequence.add(new Word("196", 199));
        clas.sequence.add(new Word("197", 200));
        clas.sequence.add(new Word("198", 201));

        NumScheme cu = new NumScheme();
        cu.title = "cu3192";
        cu.sequence.add(new Word("III", 8));
        cu.sequence.add(new Word("IV", 9));
        cu.addBlank();
        cu.addBlank();
        cu.sequence.add(new Word("VI", 12));
        cu.sequence.add(new Word("VII", 13));
        cu.addBlank();
        cu.addBlank();
        cu.sequence.add(new Word("2", 16));
        cu.sequence.add(new Word("3", 17));
        cu.sequence.add(new Word("4", 18));
        cu.sequence.add(new Word("5", 19));
        cu.sequence.add(new Word("6", 20));
        cu.addBlank();
        cu.sequence.add(new Word("8", 22));
        cu.addTen();
        cu.sequence.add(new Word("19", 33));
        cu.sequence.add(new Word("20", 34));
        cu.sequence.add(new Word("21", 35));
        cu.sequence.add(new Word("22", 36));
        cu.sequence.add(new Word("23", 37));
        cu.sequence.add(new Word("24", 38));
        cu.sequence.add(new Word("25", 39));
        cu.sequence.add(new Word("26", 40));
        cu.sequence.add(new Word("27", 41));
        cu.sequence.add(new Word("28", 42));
        cu.addBlank();
        cu.addBlank();
        cu.addBlank();
        cu.sequence.add(new Word("32", 46));
        cu.addTen();
        cu.addFive();
        cu.sequence.add(new Word("48", 62));
        cu.sequence.add(new Word("49", 63));
        cu.sequence.add(new Word("50", 64));
        cu.sequence.add(new Word("51", 65));
        cu.addBlank();
        cu.sequence.add(new Word("53", 67));
        cu.sequence.add(new Word("54", 68));
        cu.sequence.add(new Word("55", 69));
        cu.sequence.add(new Word("56", 70));
        cu.addBlank();
        cu.sequence.add(new Word("58", 72));
        cu.sequence.add(new Word("59", 73));
        cu.sequence.add(new Word("60", 74));
        cu.sequence.add(new Word("61", 75));
        cu.addBlank();
        cu.sequence.add(new Word("63", 77));
        cu.addFifty();
        cu.addTen();
        cu.addTen();
        cu.addTen();
        cu.addFive();
        cu.sequence.add(new Word("149", 163));
        cu.sequence.add(new Word("150", 164));
        cu.sequence.add(new Word("151", 165));
        cu.sequence.add(new Word("152", 166));
        cu.addBlank();
        cu.sequence.add(new Word("154", 168));
        cu.addFifty();
        cu.addFifty();
        cu.addFifty();
        cu.addTen();
        cu.addTen();
        cu.addTen();
        cu.addTen();
        cu.sequence.add(new Word("345", 359));
        cu.sequence.add(new Word("346", 360));
        cu.addBlank();
        cu.sequence.add(new Word("348", 362));
        cu.addTen();
        cu.addBlank();
        cu.sequence.add(new Word("360", 374));
        cu.addFive();
        cu.sequence.add(new Word("366", 380));
        cu.addBlank();
        cu.sequence.add(new Word("368", 382));
        cu.addTen();
        
        NumScheme dieo = new NumScheme();
        dieo.title = "dieost";
        dieo.sequence.add(new Word("VI", 9));
        dieo.sequence.add(new Word("VII", 10));
        dieo.addBlank();
        dieo.addBlank();
        dieo.sequence.add(new Word("2", 13));
        //8 on 19
        //14 on 25
        //23 on 36...works until 39
        //31 on 44
        //44 on 57
        
        
        
        bookSchemes.add(aness);
        bookSchemes.add(alib);
        bookSchemes.add(annual);
        bookSchemes.add(belf);
        bookSchemes.add(bie);
        bookSchemes.add(book);
        bookSchemes.add(brow);
        bookSchemes.add(cair);
        bookSchemes.add(chem);
        bookSchemes.add(clas);
        bookSchemes.add(cu);
        NumScheme obs = new NumScheme();
        File dir = new File(args[0]);
        File test = null;
        for (NumScheme ns : bookSchemes) {
            for (Word w : ns.sequence) {
                if (RomanNumeral.isRomanNum(w)) {
                    w.isRoman = true;
                } else if (!w.isBlank) {
                    w.isArabic = true;
                }
            }
        }
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                for (NumScheme ns : bookSchemes) {
                    if (f.getName().substring(0, 6).equals(ns.title)) {
                        test = f;
                        XMLTrimmer xr = new XMLTrimmer(test);
                        obs = xr.run(test);
                        System.out.println(compareAndScore(ns, obs));
                    }

                }
            }

        }

        // File test = new File("/home/bzifkin/Proteus2/homer/src/main/java/ciir/proteus/parse/djvu-docs/alibraryamerica05goog_djvu.xml");
    }

}
