// Noah Lesure
// CSE 143 AF
// TA: Ido Avnon
// Assignment 4
// HangmanManager finds patterns in the set of possible guess words as the user
// guesses letters. words with the same pattern of guessed letters are
// grouped and the largest group is used as the set of possible guess words.
// This process repeats until one word must be chosen as the target word.
// if letters are guessed correctly the letter will be displayed. Otherwise
// the letter space is represented with a dash.
import java.util.*;

public class HangmanManager {

   // The set of words correlated to a certain guess pattern
   private TreeMap<String, TreeSet<String>> setOfWords;
  
   // The collection of words being considered as the final guess word
   private TreeSet<String> wordsPossible = new TreeSet<>();
   
   // The letters guessed by the client
   private TreeSet<Character> lettersGuessed = new TreeSet<>();
   
   // The number of guesses by the client
   private int guesses = 0;
   
   // The number of initial guesses given
   private int guessesGiven;
   
   // The current guess pattern
   private String chosenKey = "";

   // Pre: Length must be greater than 0 and the max guesses 
   // must be zero or greater (throws an IllegalArgumentException otherwise).
   // Post: Initializes the state of the game, filtering the words down to
   // words of the given length, creating the initial guess pattern, and 
   // initializing the number of guesses given
   // Parameters:
   // dictionary - represents the selection of words
   // length - the length of the words used in the game
   // max - the number of guesses given to the player
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      guessesGiven = max;
      for (String word : dictionary) {
         if (word.length() == length) {
            wordsPossible.add(word);
         }
      }
      for (int i = 0; i < length; i++) {
         if (i != length - 1) {
            chosenKey += "- ";
         } else {
            chosenKey += "-";
         }
      }
   }

   // Post: Returns the set of words being considered.
   public Set<String> words() {
      return wordsPossible;
   }

   // Post: Returns the amount of guesses the player has left.
   public int guessesLeft() {
      return guessesGiven - guesses;
   }

   // Returns the letters that the player has already guessed.
   Set<Character> guesses() {
      return lettersGuessed;
   }

   // Pre: The set of words considered cannot be empty 
   // (throws IllegalStateException otherwise)
   // Post: Returns the current guess pattern.
   public String pattern() {
      if (wordsPossible.size() == 0) {
         throw new IllegalStateException();
      }
      return chosenKey;
   }

   // Pre: the set of words being considered must not be empty and the number
   // of guesses left must be 1 or greater 
   //(throws IllegalStateException otherwise).
   // the letter being guess must be guessed for the first time
   // (throws IllegalArgumentExcpetion otherwise)
   // Post: Uses the letter guessed by the player to filter out word sets
   // and choose the largest word set to move foward. Counts the number
   // of guesses made. Returns the number of times that the guessed letter
   // occurs in the guess pattern.
   // Parameter: guess - the users guess.
   public int record(char guess) {
      if(guessesGiven - guesses < 1 || wordsPossible.isEmpty()) {
         throw new IllegalStateException();
      } else if (lettersGuessed.contains(guess)) {
         throw new IllegalArgumentException();
      }
      int numOccurences = 0;
      lettersGuessed.add(guess);
      createkey();
      buildGuessPattern();
      if(chosenKey.indexOf(guess) == -1) {
         guesses++;
      }
      for (int i = 0; i < chosenKey.length(); i++) {
         if (chosenKey.charAt(i) == guess) {
            numOccurences += 1;
         }
      }
      return numOccurences;
   }

   // Post: Helper method to record. Filters through
   // the different groups of words and chooses the group that has the 
   // most words.
   private void buildGuessPattern() {
      int setSize = 0;
      for (String keyEliminate : setOfWords.keySet()) {
         TreeSet<String> set = setOfWords.get(keyEliminate);
         int setCompare = set.size();
         if (setCompare > setSize) {
            wordsPossible = setOfWords.get(keyEliminate);
            chosenKey = keyEliminate;
            setSize = setCompare;
         }
      }
   }

   // Post: Helper method to record. Filters through all the words being 
   // considered and correlates groups of words to a guess pattern.
   private void createkey() {
      setOfWords = new TreeMap<>();
      for (String wordFind : wordsPossible) {
         String guessPattern = "";
         for (int i = 0; i < wordFind.length(); i++) {
            if (lettersGuessed.contains(wordFind.charAt(i))) {
               if (i != wordFind.length() - 1) {
                  guessPattern +=  wordFind.charAt(i) + " ";
               } else {
                  guessPattern += wordFind.charAt(i);
               }
            }
            else {
               if (i != wordFind.length() - 1) {
                  guessPattern += "- ";
               } else {
                  guessPattern += "-";
               }
            }
            
         }
         if (setOfWords.containsKey(guessPattern)) {
            setOfWords.get(guessPattern).add(wordFind);
         } else {
            setOfWords.put(guessPattern, new TreeSet<>());
            setOfWords.get(guessPattern).add(wordFind);
         }
      }
   }
}

