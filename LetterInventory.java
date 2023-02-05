
// Class LetterInventory creates an inventory that stores the letters of 
// the alphabet. Letters can be added or taken away from this inventory.
public class LetterInventory {

   // The ammount of inventory slots that are occupied.
   private int size;

   // The number of letters in the alphabet.
   public static final int ALPHABET_LENGTH = 26;

   // Stores the letters of the alphabet in an array.
   private int[] elementData;

   // Post: creates an inventory that counts the letters
   // of the alphabet from a given string.
   // Parameter - String data: represents a string of characters.
   public LetterInventory(String data) {
      elementData = new int[ALPHABET_LENGTH];

      for (int i = 0; i < data.length(); i++) {
         if (Character.isLetter(data.charAt(i))) {
            elementData[Character.toLowerCase(data.charAt(i)) - 'a']++;
            size++;
         }
      }
   }

   // Pre: letter given must be an alphabetic character
   // (throws an IllegalArgumentException otherwise).
   // Post: Calculates and returns how many of a letter are in the inventory.
   // Parameter - char letter: represents a letter of the alphabet.
   public int get(char letter) {
      int count = elementData[Character.toLowerCase(letter) - 'a'];
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException(letter + "is not an alphabetic character.");
      }
      return count;
   }

   // Pre: letter given must be an alphabetic character and value given
   // must be a value of zero or greater (thorws and IllegalArguemntException).
   // Post: changes the count of a letter in the inventory
   // to the new value given.
   // Parameter - char letter: represents a letter of the alphabet.
   // Parameter - int value: represents a numeric value.
   public void set(char letter, int value) {
      if (!Character.isLetter(letter) || value < 0) {
         throw new IllegalArgumentException("Letter must be an alphabetic character "
               + "and value must be a value of zero or greater.");
      }
      size = size - elementData[Character.toLowerCase(letter) - 'a'];
      elementData[Character.toLowerCase(letter) - 'a'] = value;
      size += value;
   }

   // Post: returns the number of inventory slots currently occupied.
   public int size() {
      return size;
   }

   // Post: checks if the inventory is empty. Returns true if
   // empty and false if not.
   public boolean isEmpty() {
      if (size == 0) {
         return true;
      }
      return false;
   }

   // Post: converts each letter in the inventory into a string and returns the
   // string.
   public String toString() {
      String alphabetInventory = "[";
      for (int i = 0; i < ALPHABET_LENGTH; i++) {
         for (int j = 0; j < elementData[i]; j++) {
            alphabetInventory += Character.toString((char) 'a' + i);
         }
      }
      return alphabetInventory += "]";
   }

   // Post: returns a new LetterInventory that is the sum of this
   // LetterInventory and the other LetterInventory.
   // Parameter - LetterInventory other: a second inventory of letters.
   public LetterInventory add(LetterInventory other) {
      LetterInventory total = new LetterInventory("");
      for (int i = 0; i < ALPHABET_LENGTH; i++) {
         total.elementData[i] = this.elementData[i] + other.elementData[i];
         total.size = this.size + other.size;
      }
      return total;
   }

   // Post: returns a new LetterInventory that is the product of
   // subtracting the other LetterInventory from this LetterInventory.
   // Parameter - LetterInventory other: a second inventory of letters.
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory total = new LetterInventory("");
      for (int i = 0; i < ALPHABET_LENGTH; i++) {
         if (this.elementData[i] - other.elementData[i] < 0) {
            return null;
         }
         total.elementData[i] = this.elementData[i] - other.elementData[i];
         total.size += total.elementData[i];
      }
      return total;

   }

}
