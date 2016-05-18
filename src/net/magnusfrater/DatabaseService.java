package net.magnusfrater;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class DatabaseService {

    String filePath;
    Random r;

    public DatabaseService(String filePath){
        this.filePath = filePath;

        r = new Random();
    }

    protected String[] getPronunciation(String word, boolean debug){
        long iTime = System.currentTimeMillis();

        String[] pronunciation = {"**NULL**"};

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(" ");

                if (temp[0].equals(word)){
                    pronunciation = temp;
                    break;
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to translate \""+ word +"\".");
        }

        return pronunciation;
    }

    protected ArrayList<String> getRhymes(String word, boolean debug){
        long iTime = System.currentTimeMillis();

        String[] pronunciation = getPronunciation(word,false);

        ArrayList<String> rhymes = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] temp = line.split(" ");

                if (pronunciation[pronunciation.length-1].equals(temp[temp.length-1])){
                    if (pronunciation.length-2>0 && temp.length-2>0){
                        if (pronunciation[pronunciation.length-2].equals(temp[temp.length-2])){
                            if (pronunciation.length-3>0 && temp.length-3>0){
                                if (pronunciation[pronunciation.length-3].equals(temp[temp.length-3])){
                                    rhymes.add(temp[0]);
                                }
                            }else{
                                //rhymes.add(temp[0]);
                            }
                        }
                    }else{
                        //rhymes.add(temp[0]);
                    }
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to find rhymes of \""+ word +"\".");
        }

        return rhymes;
    }

    protected int getWordCount(boolean debug){
        long iTime = System.currentTimeMillis();

        int count = 0;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                count++;
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to get database word count.");
        }

        return count;
    }

    protected String getLongestWord(boolean debug){
        long iTime = System.currentTimeMillis();

        String longest = "";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String word = line.split(" ")[0];

                if (word.length()>longest.length())
                    longest = word;
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to get longest word: \""+ longest +"\".");
        }

        return longest;
    }

    protected String[] getRandomWords(int count, boolean debug){
        long iTime = System.currentTimeMillis();

        int size = getWordCount(debug);

        if (count<0) count = 0;
        if (count>100) count = 100;

        String[] words = new String[count];

        for (int i=0; i<count; i++){
            int randWordLine = rndInt(1,size);

            try {
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;
                int lineNum = 0;
                while((line = bufferedReader.readLine()) != null) {
                    lineNum++;

                    if (randWordLine == lineNum){
                        words[i] = line.split(" ")[0];
                        break;
                    }
                }

                bufferedReader.close();
            }
            catch(FileNotFoundException ex) {
                System.out.println("Unable to open file \'" + filePath + "\'!\n");
            }
            catch(IOException ex) {
                System.out.println("Error reading file \'" + filePath + "\'!\n");
            }
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to get "+ count +" random words.");
        }

        return words;
    }

    protected String getMostVowels(boolean debug){
        long iTime = System.currentTimeMillis();

        String mostWord = "";
        int mostVowels = 0;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String word = line.split(" ")[0];
                int vowelCount = 0;

                for (int i=0; i<word.length(); i++){
                    if (word.charAt(i)=='A' || word.charAt(i)=='E' || word.charAt(i)=='I' || word.charAt(i)=='O' || word.charAt(i)=='U'){
                        vowelCount++;
                    }
                }

                if (vowelCount>mostVowels){
                    mostWord = word;
                    mostVowels = vowelCount;
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to get the word with the most vowels, "+ mostWord +", at "+ mostVowels +" vowels.");
        }

        return mostWord;
    }

    protected String getMostConsonants(boolean debug){
        long iTime = System.currentTimeMillis();

        String mostWord = "";
        int mostConsonants = 0;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String word = line.split(" ")[0];
                int consonantsCount = 0;

                for (int i=0; i<word.length(); i++){
                    if (word.charAt(i)!='A' && word.charAt(i)!='E' && word.charAt(i)!='I' && word.charAt(i)!='O' && word.charAt(i)!='U'){
                        consonantsCount++;
                    }
                }

                if (consonantsCount>mostConsonants){
                    mostWord = word;
                    mostConsonants = consonantsCount;
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to get the word with the most consonants, "+ mostWord +", at "+ mostConsonants +" consonants.");
        }

        return mostWord;
    }

    protected String getMostLetter(char letter, boolean debug){
        long iTime = System.currentTimeMillis();

        letter = Character.toUpperCase(letter);

        String mostWord = "";
        int mostLetter = 0;

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String word = line.split(" ")[0];
                int letterCount = 0;

                for (int i=0; i<word.length(); i++){
                    if (word.charAt(i) == letter){
                        letterCount++;
                    }
                }

                if (letterCount>mostLetter){
                    mostWord = word;
                    mostLetter = letterCount;
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to get the word with the most \'"+ letter +"\'s, "+ mostWord +", at "+ mostLetter +" \'"+ letter +"\'s.");
        }

        return mostWord;
    }

    protected ArrayList<String> getAnagrams(String prompt, boolean debug){
        long iTime = System.currentTimeMillis();

        prompt = prompt.toUpperCase();

        ArrayList<String> anagrams = new ArrayList<String>(); //anagrams
        anagrams.add(prompt);

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String word = line.split(" ")[0];
                int[] letterCount = new int[prompt.length()];

                if (word.length() == prompt.length() && !word.equals(prompt)){ //checks if word length equals example length

                    for (int i=0; i<word.length(); i++){ //loops for all letters of word
                        for (int j=0; j<prompt.length(); j++){ //loop for counts of letters from example
                            if (word.charAt(i)==prompt.charAt(j) && letterCount[j]==0){ //checks if letter of word equals letter in
                                letterCount[j]++;
                                break;
                            }
                        }
                    }

                }

                int sum=0;
                for (int i=0; i<letterCount.length; i++){ //sums all counts
                    sum+=letterCount[i];
                }

                if (sum==letterCount.length){
                    anagrams.add(word);
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to find "+ anagrams.size() +" anagrams of \""+ prompt +"\".");
        }

        return anagrams;
    }

    protected ArrayList<String> getMostAnagrams(boolean debug){
        long iTime = System.currentTimeMillis();

        String mostWord = "**NULL**";
        ArrayList<String> mostAnagrams = new ArrayList<String>(); //anagrams

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String word = line.split(" ")[0];

                ArrayList<String> anagrams = getAnagrams(word, debug); //set debug parameter to 'debug' or 'true' if you want it to take even longer

                if (anagrams.size() > mostAnagrams.size()){
                    mostWord = word;
                    mostAnagrams = anagrams;
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to find "+ mostAnagrams.size() +" anagrams of \""+ mostWord +"\".");
        }

        return mostAnagrams;
    }

    protected String[] getSuggestedSpelling(String prompt, int returnCount, boolean debug){
        long iTime = System.currentTimeMillis();

        prompt = prompt.toUpperCase();

        if (returnCount<1)
            returnCount = 1;

        String[] suggestedSpellings = new String[returnCount];
        int[] similarityLevels = new int[returnCount];

        for (int i=0; i<similarityLevels.length; i++){
            similarityLevels[i] = Integer.MAX_VALUE;
        }

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String word = line.split(" ")[0];

                String smaller,longer;
                if (prompt.length()>word.length()){ //checks larger,smaller string
                    longer = prompt;
                    smaller = word;
                }else{
                    smaller = prompt;
                    longer = word;
                }

                int similarityLevel = 0;

                for (int i=0; i<longer.length(); i++){ //checking distance in ASCII value between same-column characters in each string, adding to similarity level
                    char sChar; //smaller word's character
                    if (i>=smaller.length()){ //if NPE error
                        sChar = ' ';
                    }else{
                        sChar = smaller.charAt(i); //else
                    }
                    char lChar = longer.charAt(i); //longer word's charater

                    similarityLevel += Math.abs((int)lChar-(int)sChar); //adds abs value of difference in letters to similarity level
                }

                for (int i=0; i<similarityLevels.length; i++){
                    if (similarityLevel<similarityLevels[i]){
                        suggestedSpellings[i] = word;
                        similarityLevels[i] = similarityLevel;
                        break;
                    }
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to find "+ returnCount +" spelling suggestions of \""+ prompt +"\".");
        }

        return suggestedSpellings;
    } //@@@@@ NEEDS WORK @@@@@

    protected ArrayList<String> getArpasoundWords(String arpasound, boolean onlyFirst, boolean debug){
        long iTime = System.currentTimeMillis();

        arpasound = arpasound.toUpperCase();

        ArrayList<String> words = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String word = line.split(" ")[0];
                String[] pronunciation = line.split(" ");

                for (int i=1; i<pronunciation.length; i++){
                    if (pronunciation[i].equals(arpasound)){
                        words.add(word);

                        break;
                    }
                }

                if (onlyFirst && words.size()==1){ //only return the first word with that arpasound
                    break;
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file \'" + filePath + "\'!\n");
        }
        catch(IOException ex) {
            System.out.println("Error reading file \'" + filePath + "\'!\n");
        }

        long fTime = System.currentTimeMillis();
        long elapsedTime = fTime-iTime;

        if (debug){
            System.out.println("Task took "+ elapsedTime +" milliseconds to get all words with arpasound \'"+ arpasound +"\'.");
        }

        return words;
    }

    protected int rndInt(int min, int max){
        return r.nextInt((max - min) + 1) + min;
    }
}