import java.util.Scanner;
public class ToUpperCase {

    public ToUpperCase() {
        System.out.println("upper / lower case conversion enabled");
    } //default constructor

    public String action(String theWord) {

        int asciival = 0;
        //a variable to store ascii values
        String outputWord = "";
        //the string that will store the output word
        char subLetter = 'a';
        //a char variable to store every letter of the word

        for(int i = 0; i < theWord.length(); i++){
            subLetter = theWord.charAt(i);
            asciival = subLetter;
            //converting the letters to its ascii values
            //to check if the string is a letter
            if(asciival >= 97 && asciival <= 122 == true){
                outputWord += (char) (asciival - 32);
                //if lower case -32 to get the upper case zone
            } else{
                outputWord += subLetter;
            }
        }

        return outputWord;
    } // ToUpperCase make all the letters upper case

}