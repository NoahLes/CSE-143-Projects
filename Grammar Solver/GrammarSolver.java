// Noah Lesure
// CSE 143 AF
// TA: Ido Avnon
// Assignment 5
// GrammarSolver splits a string into a single grammar word and rules for the 
// grammar word. The rules for the grammar word can be normal words or other
// grammar words. When given a grammar word and a number of times for a phrase
// to be generated, the rules of the given grammar word can be randomly 
// selected to create a random phrase the number of times the client asks for.
// GrammarSolver also displays all of the grammar words.
import java.util.*;

public class GrammarSolver {

   // The grammar words and the rules correlated.
   private SortedMap<String, String[]> grammarFilter = new TreeMap<>();

   // Pre: list of grammar must have at least one grammar string and
   // and each entrie in grammar must produce a new non-terminal
   // (throws IllegalArgumentException otherwise).
   // Post: Stores the grammar as a grammar word with one or more
   // rules correlated to the grammar word.
   // Parameters - grammar: represents a list of grammar words and their rules
   // as a single string
   public GrammarSolver(List<String> grammar) {
      if (grammar.isEmpty()) {
         throw new IllegalArgumentException();
      } else {
         for (String grammarStrings : grammar) {
            String[] getNonTerminal = grammarStrings.split("::=");
            String nonTerminal = getNonTerminal[0].trim();
            String rules = getNonTerminal[1];
            String[] getRules = rules.split("[|]");
            for (int i = 0; i < getRules.length; i++) {
               getRules[i] = getRules[i].trim();
            }
            if (grammarFilter.containsKey(nonTerminal)) {
               throw new IllegalArgumentException();
            } else {
               grammarFilter.put(nonTerminal, getRules);
            }
         }
      }
   }

   // Post: checks if the symbol is a non-terminal of the 
   // grammar. Returns true if the symbol is an non-terminal of the grammar.
   // returns false otherwise.
   public boolean grammarContains(String symbol) {
      return grammarFilter.containsKey(symbol);
   }

   // Pre: grammar must contain the given symbol as a non-terminal and number
   // of times must be 0 or greater (throws IllegalArgumentException otherwise).
   // Post: Returns random phrases from rules of the given non-terminal symbol.
   // The number of random phrases generated correlates to the number of times
   // the client asks for a random phrase to be generated.
   // Parameter:
   // symbol - represents a non-terminal of a grammar.
   // times - represents the number of times for a random phrase
   // to be generated.
   public String[] generate(String symbol, int times) {
      String[] generatedPhrases = new String[times];
      if (!grammarFilter.containsKey(symbol) || times < 0) {
         throw new IllegalArgumentException();
      } else {
         for (int i = 0; i < times; i++) {
            String phrase = recursiveMethod(symbol);
            generatedPhrases[i] = phrase.trim();
         }
      }
      return generatedPhrases;
   }

   // Post: Helper method to generate. generates a random phrase
   // from the rules of the given non-terminal symbol. Returns the randomly
   // generated phrase.
   // Parameter: symbol - represents a non-terminal of a grammar.
   private String recursiveMethod(String symbol) {
      String buildPhrase = "";
      Random rand = new Random();
      if (!grammarFilter.containsKey(symbol)) {
         buildPhrase += symbol + " ";
      } else {
         String[] rules = grammarFilter.get(symbol);
         String randomRule = rules[rand.nextInt(rules.length)];
         String[] splitRule = randomRule.split("[ \t]+");
         for (int i = 0; i < splitRule.length; i++) {
            symbol = splitRule[i].trim();
            buildPhrase += recursiveMethod(symbol);
         }
      
      }
      return buildPhrase;
   }

   // Post: Returns a string of all of the non-terminal symbols from the 
   // grammar as a sorted, comma sperarated list enclosed with square brackets.
   public String getSymbols() {
      return grammarFilter.keySet().toString();
   }

}
