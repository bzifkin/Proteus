//package ciir.proteus.parse;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bzifkin
 */
public class Pages {

    
    int indexNum;
    List<Word> wordsOnPage = new ArrayList<Word>();
    //List<SubScheme> schemesOnPage = new ArrayList<SubScheme>();

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
