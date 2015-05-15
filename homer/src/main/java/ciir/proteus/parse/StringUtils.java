/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciir.proteus.parse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Code imported from my (SDH) IR 646 code
 */
public class StringUtils {

    public static final boolean USE_DEFAULT_STOP = true;

    // Based on "Intro. to IR", p.25  http://www-csli.stanford.edu/~hinrich/information-retrieval-book.html
    public static final String[] DEFAULT_STOP_SET =  {"a", "an", "and", "are", "as", "at", "be", "been", "being", "by", "did", "do",
            "does", "doing", "done", "for", "from", "have", "has", "he", "in", "if", "is", "it", "its", "of",
            "on", "or", "that", "the", "to", "was", "were", "will", "with"};

    public static final Set<String> defaultStopSet = new HashSet<String>(Arrays.asList(DEFAULT_STOP_SET));

    // SDH -- this is the INQUERY stop set (as I got it from Elif) w/o apostrophes to accommodate my tokenization
    // Also, I added "cant" which wasn't in the original... should I be doing that? ugh 9/23/14
    public static final String[] INQUERY_STOP_SET = {"a", "about", "above", "according", "across", "after", "afterwards", "again",
            "against", "albeit", "all", "almost", "alone", "along", "already", "also", "although", "always", "am",
            "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway",
            "anywhere", "apart", "are", "around", "as", "at", "av", "be", "became", "because", "become", "becomes",
            "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between",
            "beyond", "both", "but", "by", "can", "cannot", "canst",
            /* I'm pretty sure that the original "canst" was actually "can't"? Leaving both - SDH 9/23/14 */"cant",
            "certain", "cf", "choose", "contrariwise", "cos", "could", "cu", "day", "do", "does", "doesnt",
            "doing", "dost", "doth", "double", "down", "dual", "during", "each", "either", "else", "elsewhere",
            "enough", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere",
            "except", "excepted", "excepting", "exception", "exclude", "excluding", "exclusive", "far", "farther",
            "farthest", "few", "ff", "first", "for", "formerly", "forth", "forward", "from", "front", "further",
            "furthermore", "furthest", "get", "go", "had", "halves", "hardly", "has", "hast", "hath", "have", "he",
            "hence", "henceforth", "her", "here", "hereabouts", "hereafter", "hereby", "herein", "hereto",
            "hereupon", "hers", "herself", "him", "himself", "hindmost", "his", "hither", "hitherto", "how",
            "however", "howsoever", "i", "ie", "if", "in", "inasmuch", "inc", "include", "included", "including",
            "indeed", "indoors", "inside", "insomuch", "instead", "into", "inward", "inwards", "is", "it", "its",
            "itself", "just", "kind", "kg", "km", "last", "latter", "latterly", "less", "lest", "let", "like",
            "little", "ltd", "many", "may", "maybe", "me", "meantime", "meanwhile", "might", "moreover", "most",
            "mostly", "more", "mr", "mrs", "ms", "much", "must", "my", "myself", "namely", "need", "neither",
            "never", "nevertheless", "next", "no", "nobody", "none", "nonetheless", "noone", "nope", "nor", "not",
            "nothing", "notwithstanding", "now", "nowadays", "nowhere", "of", "off", "often", "ok", "on", "once",
            "one", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out",
            "outside", "over", "own", "per", "perhaps", "plenty", "provide", "quite", "rather", "really", "round",
            "said", "sake", "same", "sang", "save", "saw", "see", "seeing", "seem", "seemed", "seeming", "seems",
            "seen", "seldom", "selves", "sent", "several", "shalt", "she", "should", "shown", "sideways", "since",
            "slept", "slew", "slung", "slunk", "smote", "so", "some", "somebody", "somehow", "someone", "something",
            "sometime", "sometimes", "somewhat", "somewhere", "spake", "spat", "spoke", "spoken", "sprang",
            "sprung", "stave", "staves", "still", "such", "supposing", "than", "that", "the", "thee", "their",
            "them", "themselves", "then", "thence", "thenceforth", "there", "thereabout", "thereabouts",
            "thereafter", "thereby", "therefore", "therein", "thereof", "thereon", "thereto", "thereupon", "these",
            "they", "this", "those", "thou", "though", "thrice", "through", "throughout", "thru", "thus", "thy",
            "thyself", "till", "to", "together", "too", "toward", "towards", "ugh", "unable", "under", "underneath",
            "unless", "unlike", "until", "up", "upon", "upward", "upwards", "us", "use", "used", "using", "very",
            "via", "vs", "want", "was", "we", "week", "well", "were", "what", "whatever", "whatsoever", "when",
            "whence", "whenever", "whensoever", "where", "whereabouts", "whereafter", "whereas", "whereat",
            "whereby", "wherefore", "wherefrom", "wherein", "whereinto", "whereof", "whereon", "wheresoever",
            "whereto", "whereunto", "whereupon", "wherever", "wherewith", "whether", "whew", "which", "whichever",
            "whichsoever", "while", "whilst", "whither", "who", "whoa", "whoever", "whole", "whom", "whomever",
            "whomsoever", "whose", "whosoever", "why", "will", "wilt", "with", "within", "without", "worse",
            "worst", "would", "wow", "ye", "yet", "year", "yippee", "you", "your", "yours", "yourself",
            "yourselves"};

    public static final Set<String> inqueryStopSet = new HashSet<String>(Arrays.asList(INQUERY_STOP_SET));

    public static final Set<String> stopSet = (USE_DEFAULT_STOP ? defaultStopSet : inqueryStopSet);
    public static final char TAB = '\t';
    public static final String NEWLINE = "\n";

    public static String[] tokenizeString(String line) {
        line = normalizeString(line);

        // Split on all whitespace or punctuation -- except apostrophe and hyphen
        String[] tokens = line.split("[\\s\\p{Punct}&&[^'-]]+");

        return tokens;
    }

    public static String reconstructPhrase(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            sb.append(token);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String normalizeString(String line) {

        // Right now just making them all lowercase regardless.
        // Could potentially use a more sophisticated approach for case preservation in certain cases
        line = line.toLowerCase();

        // "Squeeze out" apostrophes (') and hyphens (-)
        line = line.replace("'", "");
        line = line.replace("-", "");

        return line;
}


}