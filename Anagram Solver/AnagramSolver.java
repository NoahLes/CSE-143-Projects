
// AnagramSolver uses a dictionary to find all combinations of words
// that have the same letters as a given phrase. A given number will
// determine how many words combined share the same letters as the phrase 
// given. If the given number is zero, every possible combination of words
// will be displayed.
import java.util.*;

public class AnagramSolver {

   // The dictionary of words and each words letter inventory
   private Map<String, LetterInventory> wordToLetters = new HashMap<>();
   
   // The dictionary shortened to only contain words that share the same
   // letters as the given phrase.
   private List<String> sortedDictionary;

   // Post: Stores the dictionary words with a letter inventory
   // correlated to each word.
   // Parameter - list: the dictionary of words.
   public AnagramSolver(List<String> list) {
      sortedDictionary = list;
      for (String word : list) {
         LetterInventory letterManipulator = new LetterInventory(word);
         wordToLetters.put(word, letterManipulator);
      }
   }

   // Pre: Max number of words must be zero or greater
   // (throws IllegalArgumentException otherwise).
   // Post: Finds combinations of words that contain the same letters as the
   // given phrase and prints the combinations. The given number will determine
   // how many words together share the same letters as the given phrase.
   // If the given number is zero, all possible combinations of words 
   // will be printed.
   // Parameter:
   // s - the phrase that anagrams are created from.
   // max - the number of words in each anagram.
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory givenString = new LetterInventory(s);
      ArrayList<String> wordBuild = new ArrayList<>();
      ArrayList<String> pruneDictionary = new ArrayList<>();
      for (String dictWord : sortedDictionary) {
         if (givenString.subtract(wordToLetters.get(dictWord)) != null) {
            pruneDictionary.add(dictWord);
         }
      }
      if (max == 0) {
         max = -1;
      }
      printHelper(max, givenString, pruneDictionary, wordBuild, 0);
   
   }

   // Post: Helper method for print that goes through every combination of 
   // words and finds anagrams that contain the same letters from the given
   // string and the same number of words in each anagram as the given number.
   // Parameters:
   // max - the number of words in each anagram.
   // givenString - the given string as a letter inventory.
   // pruneDictionary - a shortened version of the dictionary that only
   // contains words that share the same letters as the given phrase.
   // wordBuild - stores the words in each anagram.
   // numTimes - keeps track of how many words are added to an anagram.
   private void printHelper(int max, LetterInventory givenString,
         ArrayList<String> pruneDictionary, ArrayList<String> wordBuild, int numTimes) {
   
      if (givenString.isEmpty()) {
         System.out.println(wordBuild);
      } else {
         for (String dictWordFiltered : sortedDictionary) {
            LetterInventory subtractLetter = 
               givenString.subtract(wordToLetters.get(dictWordFiltered));
            if (subtractLetter != null && (max == -1 || max > numTimes)) {
               wordBuild.add(dictWordFiltered);
               printHelper(max, subtractLetter, pruneDictionary, wordBuild, numTimes + 1);
               wordBuild.remove(wordBuild.size() - 1);
            
            }
         }
      
      }
   }

}
