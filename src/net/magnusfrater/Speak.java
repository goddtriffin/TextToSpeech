package net.magnusfrater;

import java.util.ArrayList;

public class Speak {

    Sound sound;
    DatabaseService ds;

    String[] arpasounds = "AA AA0 AA1 AA2 AE AE0 AE1 AE2 AH AH0 AH1 AH2 AO AO0 AO1 AO2 AW AW0 AW1 AW2 AY AY0 AY1 AY2 B CH D DH EH EH0 EH1 EH2 ER ER0 ER1 ER2 EY EY0 EY1 EY2 F G HH IH IH0 IH1 IH2 IY IY0 IY1 IY2 JH K L M N NG OW OW0 OW1 OW2 OY OY0 OY1 OY2 P R S SH T TH UH UH0 UH1 UH2 UW UW0 UW1 UW2 V W Y Z ZH".split(" ");

    public Speak(){
        sound = new Sound();

        ds = new DatabaseService("src/res/PronunciationDictionary.txt");
    }

    protected void speak(String input){
        input = input.toUpperCase();

        input = analyzeInput(input);

        String[] words = input.split(" "); //input -> separated words

        String pattern = "src/res/arpasounds/";
        String wav = ".wav";
        String concatWav = pattern +"concatWav"+ wav;

        ArrayList<String> paths = new ArrayList<>(); //holds all arpasound paths

        for (String word : words){
            String[] pronunciation = ds.getPronunciation(word.toUpperCase(), false);

            if (pronunciation.length < 2){ //unknown word
                for (int j=0; j<word.length(); j++){ //pronounce each letter
                    pronunciation = ds.getPronunciation(String.valueOf(word.charAt(j)), false);

                    for (int i=1; i<pronunciation.length; i++){
                        System.out.print(pronunciation[i] +" ");

                        if (pronunciation[i].length()>0) //if arpasound exists
                            paths.add(pattern + pronunciation[i] + wav); //append arpasound
                    }
                    System.out.print(" | ");
                }
            } else { //known word
                for (int i=1; i<pronunciation.length; i++){
                    System.out.print(pronunciation[i] +" ");

                    if (pronunciation[i].length()>0) //if arpasound exists
                        paths.add(pattern + pronunciation[i] + wav); //append arpasound
                }
                paths.add(pattern +"wordBreak"+ wav); //append pause between words
            }

            System.out.println();
        }

        sound.concatWav(paths);
        sound.playWav(concatWav);
    }

    private String analyzeInput(String input){

        for (int i=0; i<input.length(); i++){
            char c = input.charAt(i);

            if (c == '0')
                input = input.substring(0,i) +" ZERO "+ input.substring(i+1);
            if (c == '1')
                input = input.substring(0,i) +" ONE "+ input.substring(i+1);
            if (c == '2')
                input = input.substring(0,i) +" TWO "+ input.substring(i+1);
            if (c == '3')
                input = input.substring(0,i) +" THREE "+ input.substring(i+1);
            if (c == '4')
                input = input.substring(0,i) +" FOUR "+ input.substring(i+1);
            if (c == '5')
                input = input.substring(0,i) +" FIVE "+ input.substring(i+1);
            if (c == '6')
                input = input.substring(0,i) +" SIX "+ input.substring(i+1);
            if (c == '7')
                input = input.substring(0,i) +" SEVEN "+ input.substring(i+1);
            if (c == '8')
                input = input.substring(0,i) +" EIGHT "+ input.substring(i+1);
            if (c == '9')
                input = input.substring(0,i) +" NINE "+ input.substring(i+1);

            if (c!=32 && c!=0 && (c<65 || c>90))
                input = input.substring(0,i) +" "+ input.substring(i+1);
        }

        return input;
    }
}