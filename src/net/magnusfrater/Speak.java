package net.magnusfrater;

import java.util.ArrayList;

public class Speak {

    Sound sound;
    DatabaseService ds;

    String[] arpasounds = "AA AA0 AA1 AA2 AE AE0 AE1 AE2 AH AH0 AH1 AH2 AO AO0 AO1 AO2 AW AW0 AW1 AW2 AY AY0 AY1 AY2 B CH D DH EH EH0 EH1 EH2 ER ER0 ER1 ER2 EY EY0 EY1 EY2 F G HH IH IH0 IH1 IH2 IY IY0 IY1 IY2 JH K L M N NG OW OW0 OW1 OW2 OY OY0 OY1 OY2 P R S SH T TH UH UH0 UH1 UH2 UW UW0 UW1 UW2 V W Y Z ZH".split(" ");

    public Speak(){
        sound = new Sound();

        ds = new DatabaseService("res/PronunciationDictionary.txt");
    }

    protected void speak(String input){
        String[] words = input.split(" ");

        ArrayList<String[]> translated = new ArrayList<>();

        for (int i=0; i<words.length; i++){ //gets pronunciations of all input's words
            translated.add(translateWord(words[i]));
        }


    }

    private String[] translateWord(String word){
        return ds.getPronunciation(word,false);
    }

    protected void temp2(){
        ArrayList<String> paths = new ArrayList<String>();

        String pattern = "res/arpasounds/";
        String wav = ".wav";
        String concatWav = pattern +"concatWav.wav";

        paths.add(pattern +"HH"+ wav);
        paths.add(pattern +"AH0"+ wav);
        paths.add(pattern +"L"+ wav);
        paths.add(pattern +"OW1"+ wav);

        sound.concatWav(paths);

        sound.playWav(concatWav);
    }
}