package net.magnusfrater;

import java.util.ArrayList;

public class DatabaseFun {

    DatabaseService ds;

    public DatabaseFun(){
        ds = new DatabaseService("res/PronunciationDictionary.txt");
    }

    protected void printAllTests(){
        String input = "diagnose !EXCLAMATION-POINT }RIGHT-BRACE !EXCLAMATION-POINT }RIGHT-BRACE !EXCLAMATION-POINT }RIGHT-BRACE !EXCLAMATION-POINT }RIGHT-BRACE !EXCLAMATION-POINT }RIGHT-BRACE"; //"!EXCLAMATION-POINT }RIGHT-BRACE !EXCLAMATION-POINT }RIGHT-BRACE !EXCLAMATION-POINT }RIGHT-BRACE !EXCLAMATION-POINT }RIGHT-BRACE"
        String word = "orange";

        printTranslatedInput(input, true); //translate input
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printWordRhymes(word, true); //word rhymes
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printWordCount(true); //word count
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printLongestWord(true); //longest word
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printRandomWords(10,true); //random words
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printMostVowels(true); //most vowels
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printMostConsonants(true); //most consonants
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printMostLetter('0',true); //most of a certain letter
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printAnagrams("spear",true); //anagrams
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printSuggestedSpelling("mammy", 10, true);
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        printArpasoundWords("P", false, true);
        System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        //r.tts.printMostAnagrams(true); //most anagrams
        //System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
    }


    protected void printTranslatedInput(String input, boolean debug){
        long iTime = System.currentTimeMillis();

        for (int i=0; i<input.split(" ").length; i++){
            String[] pronunciation = ds.getPronunciation(input.split(" ")[i].toUpperCase(),debug); //gets pronunciation

            for (int j=0; j<pronunciation.length; j++) //writes it to output
                System.out.print(pronunciation[j] +" ");

            System.out.println();
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;
        if (debug)
            System.out.println("Task took "+ elapsedTime +" milliseconds to print all translations of \""+ input +"\".");
    }

    protected void printWordRhymes(String input, boolean debug){
        long iTime = System.currentTimeMillis();

        for (int i=0; i<input.split(" ").length; i++){
            String word = input.split(" ")[i];

            ArrayList<String> rhymes = ds.getRhymes(word.toUpperCase(),debug);

            System.out.println("There are "+ rhymes.size() +" rhymes for \""+ word +"\".");

            for (int j=0; j<rhymes.size(); j++){
                System.out.println(rhymes.get(j));
            }
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime - iTime;
        if (debug)
            System.out.println("Task took "+ elapsedTime +" milliseconds to print all rhymes of \""+ input +"\".");
    }

    protected void printWordCount(boolean debug){
        System.out.println("Amount of words in the database: "+ ds.getWordCount(debug));
    }

    protected void printLongestWord(boolean debug){
        System.out.println("The longest word in the database: "+ ds.getLongestWord(debug));
    }

    protected void printRandomWords(int count, boolean debug){
        String[] words = ds.getRandomWords(count,debug);

        for (int i=0; i<words.length; i++){
            System.out.print(words[i] +" ");
        }
    }

    protected void printMostVowels(boolean debug){
        System.out.println("The word with the most vowels is: "+ ds.getMostVowels(debug));
    }

    protected void printMostConsonants(boolean debug){
        System.out.println("The word with the most consonants is: "+ ds.getMostConsonants(debug));
    }

    protected void printMostLetter(char letter, boolean debug){
        System.out.println("The letter with the most \'"+ letter +"\'s is: "+ ds.getMostLetter(letter, debug));
    }

    protected void printAnagrams(String example, boolean debug){
        System.out.println("Anagrams of "+ example.toUpperCase() +": ");

        ArrayList<String> anagrams = ds.getAnagrams(example,debug);
        for (int i=0; i<anagrams.size(); i++){
            System.out.println(anagrams.get(i));
        }
    }

    protected void printMostAnagrams(boolean debug){
        ArrayList<String> mostAnagrams = ds.getMostAnagrams(debug);

        System.out.println("The word with the most anagrams is: "+ mostAnagrams.get(0));

        for (int i=0; i<mostAnagrams.size(); i++){
            System.out.println(mostAnagrams.get(i));
        }
    }

    protected void printSuggestedSpelling(String prompt, int returnCount, boolean debug){
        String[] suggestedSpellings = ds.getSuggestedSpelling(prompt, returnCount, debug);

        System.out.println("When you wrote \'"+ prompt +"\', did you mean: ");

        for (int i=0; i<suggestedSpellings.length; i++){
            System.out.println(suggestedSpellings[i]);
        }
    } //@@@@ NEEDS WORK @@@@

    protected void printArpasoundWords(String arpasound, boolean onlyFirst, boolean debug){
        ArrayList<String> words = ds.getArpasoundWords(arpasound, onlyFirst, debug);

        System.out.println("There are "+ words.size() +" words that contain the arpasound \'"+ arpasound.toUpperCase() +"\':");
        for (int i=0; i<words.size(); i++){
            System.out.println(words.get(i));
        }
    }
}
