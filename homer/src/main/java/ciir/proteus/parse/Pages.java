package ciir.proteus.parse;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bzifkin
 */
public class Pages {

    boolean blank;
    int indexNum;
    int qInch = 0;
    List<Word> wordsOnPage = new ArrayList<Word>();
    List<Header> top= new ArrayList<Header>();
    List<Header> bot = new ArrayList<Header>();
    List<Header> side = new ArrayList<Header>();
    List<Word> topWords= new ArrayList<Word>();
    List<Word> botWords = new ArrayList<Word>();
    List<Word> sideWords = new ArrayList<Word>();
   

    public Pages() {

    }
    
      public Pages( int i) {
this.indexNum = i;
    }

    public List<Word> getWords() {
        return wordsOnPage;
    }

    

    public String toString() {
        String stuff = "";
        stuff = "---------------ACM PAGE------------------- " + indexNum + "\n";
        for (Word w : wordsOnPage) {
            stuff = stuff + w.toString();
        }
        return stuff;
    }


}
