/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ciir.proteus.parse;


import java.util.regex.Pattern;

/**
 *
 * @author bzifkin
 */
public class RomanNumeral {
    
    
    private static int[]    numbers = { 1000,  900,  500,  400,  100,   90,  
                                             50,   40,   10,    9,    5,    4,    1 };
                                          
       private static String[] letters = { "M",  "CM",  "D",  "CD", "C",  "XC",
                                           "L",  "XL",  "X",  "IX", "V",  "IV", "I" }; 
    public RomanNumeral(){}
    
    
   

    /*this method doesnt work quite right
     it will appropriately recognizes well formatted
     roman numerals, but will also recognize 
     incorrectly formatted numerals
     for example it interperts "iix" as 10.
     However, in the context it's being used I
     don't think this will cause problems
     */
    
    public static int romanToDecimal(String word) {
        assert (isRomanNum(word));
        int decimal = 0;
        int lastNumber = 0;
        String romanNumeral = word.toUpperCase();
        /* operation to be performed on upper cases even if user enters roman values in lower case chars */
        for (int x = romanNumeral.length() - 1; x >= 0; x--) {
            char convertToDecimal = romanNumeral.charAt(x);

            switch (convertToDecimal) {
                case 'M':
                    decimal = processDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;

                case 'D':
                    decimal = processDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;

                case 'C':
                    decimal = processDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;

                case 'L':
                    decimal = processDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'X':
                    decimal = processDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'V':
                    decimal = processDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'I':
                    decimal = processDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;
            }
        }
        return decimal;
    }

    public static int processDecimal(int decimal, int lastNumber, int lastDecimal) {
        if (lastNumber > decimal) {
            return lastDecimal - decimal;
        } else {
            return lastDecimal + decimal;
        }
    }
/*
    public static boolean isRomanNum(Word w) {
        String text = w.text.toUpperCase();
         if(Pattern.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$", text)== true){
             w.isRoman = true;
         return true;
         }
         else
             return false;
    }
  */  
    public static boolean isRomanNum(String st) {
        st = st.toUpperCase();
         if(Pattern.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$", st)== true){
          
         return true;
         }
         else
             return false;
    }
    
    
    public static String arabToRoman(int num) {
             // Return the standard representation of this Roman numeral.
          String roman = "";  // The roman numeral.
          int N = num;        // N represents the part of num that still has
                              //   to be converted to Roman numeral representation.
          for (int i = 0; i < numbers.length; i++) {
             while (N >= numbers[i]) {
                roman += letters[i];
                N -= numbers[i];
             }
          }
          return roman;
       }

}
