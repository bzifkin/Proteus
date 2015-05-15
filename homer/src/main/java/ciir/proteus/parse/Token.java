package ciir.proteus.parse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package ciir.proteus.parse;

/**
 *
 * @author bzifkin
 */
public class Token {
    
    String text;
   int index = -1;
    int xOne; //coordinates of the words
    int yOne;
    int xTwo;
    int yTwo;

    public Token() {
    }
    
  

    public Token(String text) {
        this.text = text;
    }

    public Token(String text, int ind) {
        this.text = text;
        this.index = ind;
    }

    public int[] getCoords() {
        int[] coords = new int[4];
        coords[0] = this.xOne;
        coords[1] = this.yOne;
        coords[2] = this.xTwo;
        coords[3] = this.yTwo;
        return coords;
    }
}
