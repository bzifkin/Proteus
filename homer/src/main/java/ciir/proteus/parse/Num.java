package ciir.proteus.parse;
import java.lang.Comparable;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bzifkin
 */
public class Num  {

    
   int size;
    int totalCount; //coordinates of the words
    int inlineCount;
   
    
    public Num() {

    
    }

    public Num(int n) {

       size = n;
    }

   
    public int getRatio(){
    int percent = (int) (inlineCount * 100f) / totalCount;
    return percent;
    }

}
