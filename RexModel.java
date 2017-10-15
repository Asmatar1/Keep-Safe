package ca.roumani.rex1;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 2/28/17.
 */
public class RexModel
{

    public static final int SET_SIZE=3;
    private String regex;
    private boolean letter;
    private boolean digit;
    private boolean anchor;
    private Random rng;
    public RexModel(){
        this.letter = true;
        this.anchor = true;
        this.digit = true;
        this.regex = "";
        this.rng = new Random();

    }

    public void setDigit(boolean digit){
        this.digit = digit;
    }

    public void setLetter(boolean letter){
       this.letter= letter;
    }
    public void setAnchor(boolean anchor){
       this.anchor = anchor;
    }
    public java.lang.String getRex(){
      return this.regex;
    }
    public boolean doesMatch(java.lang.String s){
        Pattern p = Pattern.compile(this.regex);
        Matcher m = p.matcher(s);
        return m.matches();
    }
    public void generate(int repeat){
        regex = "";
    if (this.anchor)regex+="^";
        for (int i = 0; i < repeat; i++){
            genDigit();
            genLetter();
        }
        if (this.anchor) regex += "$";
    }

    private void genDigit() {
        if (this.digit) {
            if (this.rng.nextDouble() < 0.5) {
                this.regex += "[0-9]";
            } else {
                String selectorNumber = "";
                for (int i = 0; i < RexModel.SET_SIZE; i++) {
                    // Generate a random number is [0,n)
                    selectorNumber += Integer.toString(this.rng.nextInt(9 + 1));
                }
                this.regex += String.format("[%s]", selectorNumber);
            }
            genQuantifier();
        }
    }
    private void genLetter() {
        if (this.letter) {
            if (this.rng.nextDouble() < 0.5) {
                this.regex += "[a-z]";
            } else {
                String selectorLetters = "";
                for (int i = 0; i < RexModel.SET_SIZE; i++) {
                    selectorLetters += (char) (this.rng.nextInt(26) + 'a');
                }
                this.regex += String.format("[%s]", selectorLetters);
            }
            genQuantifier();
        }
    }

    private void genQuantifier() {
        double probability = this.rng.nextDouble();
        if (probability > 0.5) {
            // +1 because nextInt(n) generates a random in range [0,n) and we want range [1,SET_SIZE]
            this.regex += String.format("{%d}", this.rng.nextInt(RexModel.SET_SIZE) + 1);
        } else {
            if (probability <= (0.0 + 1)/6) {
                this.regex += "*";
            } else if (probability > (0.0 + 1)/6 && probability <= ((0.0 + 1)/6) * 2) {
                this.regex += "+";
            } else if (probability > ((0.0 + 1)/6) * 2 && probability <= ((0.0 + 1)/6) * 3) {
                this.regex += "?";
            }
        }
    }
    public static void main(java.lang.String[] args) {
        RexModel rexModel = new RexModel();
        rexModel.setLetter(true);
        rexModel.setDigit(true);
        rexModel.setAnchor(true);
        rexModel.generate(2);
        System.out.println(rexModel.getRex());
    }
}
