// Noah Lesure
// CSE 143 AF
// TA: Ido Avnon
// Assignment 8
// HuffmanNode creates a binary tree that is constructed based on 
// the binary data of each node.
public class HuffmanNode implements Comparable<HuffmanNode>{
   
   // the ascii value of a certain character
   public int character;
   
   // the frequency of times the certain character appears
   public int charFreq;
   
   // a left branch of a tree that is the path in the tree represented
   // by the binary number zero
   public HuffmanNode leftZeroBranch;
   
   // a right branch of a tree that is the path in the tree reperesented by 
   // the binary number one
   public HuffmanNode rightOneBranch;

   // Post: constructs a leaf node of a character and its frequency
   // Parameter: character - the ascii value of a certain character
   // charFreq - the frequency of a certain character
   public HuffmanNode(int character, int charFreq) {
      this(character, charFreq, null, null);
   }

   // Post: constructs a branch node with the provided character/frequency and 
   // left/right tree paths.
   // Parameter: character - the ascii value of a certain character
   // charFreq - the frequency of a certain character
   // leftZeroBranch - a left branch of the tree that is created or explored
   // if the binary code is represented with a zero
   // rightOneBranch - a right branch of the tree that is created or explored
   // if the binary code is represented with a one.
   public HuffmanNode(int character, int charFreq, HuffmanNode leftZeroBranch,
                    HuffmanNode rightOneBranch) {
   
      this.character = character;
      
      this.charFreq = charFreq;
   
      this.leftZeroBranch = leftZeroBranch;
      
      this.rightOneBranch = rightOneBranch;
      
   }

   // Post: compares the frequency of two different nodes in order to sort
   // an arrangment of nodes into numerical order.
   // Parameter: other - a second node different to the node being evaluated
   public int compareTo(HuffmanNode other) {
      return this.charFreq - other.charFreq;
   }
}
