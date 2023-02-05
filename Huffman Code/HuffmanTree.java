
// HuffmanTree can compress data into a smaller form of the original data.
// It can also decompress data and return it back to its original text form.
import java.util.*;
import java.io.*;

public class HuffmanTree {

   // the root of the binary tree
   private HuffmanNode overallRoot;

   // sorts the leaf nodes of the tree
   private PriorityQueue<HuffmanNode> sortNodes;

   // Post: constructs a binary tree from given characters and their frequency.
   // Parameter: count - the count of characters and their frequency
   public HuffmanTree(int[] count) {
      sortNodes = new PriorityQueue<HuffmanNode>();

      for (int i = 0; i < count.length; i++) {
         if (count[i] > 0) {
            sortNodes.add(new HuffmanNode(i, count[i]));
         }
      }

      sortNodes.add(new HuffmanNode(count.length, 1));

      while (sortNodes.size() > 1) {
         HuffmanNode leftHuffmanNode = sortNodes.remove();
         HuffmanNode rightHuffmanNode = sortNodes.remove();
         HuffmanNode buildUpTree = new HuffmanNode(0, leftHuffmanNode.charFreq
               + rightHuffmanNode.charFreq, leftHuffmanNode, rightHuffmanNode);
         sortNodes.add(buildUpTree);
      }
      overallRoot = sortNodes.remove();
   }

   // Post: writes the tree to a given output in the form of:
   // character as a number in ascii form on first line, and the binary code
   // of where the character is in the tree on a second line.
   // Parameter: output - the place where the character and binary code
   // are ouputted to.
   public void write(PrintStream output) {
      String binaryCode = "";
      writeHelper(output, overallRoot, binaryCode);
   }

   // Post: helper method to the write method. outputs each character as a
   // number in ascii form and creates the path of the character in the tree
   // in binary code form.
   // Parameters: output - the place where the character and binary code are
   // outputted to.
   // overallRoot - the root of the tree
   // binaryCode - the path of a character in the tree written in binary
   // code form.
   public void writeHelper(PrintStream output, HuffmanNode overallRoot, String binaryCode) {
      if (overallRoot.leftZeroBranch == null && overallRoot.rightOneBranch == null) {
         output.println(overallRoot.character);
         output.println(binaryCode);
      } else {
         writeHelper(output, overallRoot.leftZeroBranch, binaryCode + "0");
         writeHelper(output, overallRoot.rightOneBranch, binaryCode + "1");
      }

   }

   // Post: Constructs a binary tree from a given input file.
   // Parameter: input - the the input file used to construct
   // a binary tree
   public HuffmanTree(Scanner input) {
      while (input.hasNextLine()) {
         int asciiChar = Integer.parseInt(input.nextLine());
         String binaryPath = input.nextLine();
         overallRoot = buildTree(asciiChar, binaryPath, overallRoot);
      }
   }

   // Post: helper method to the constructer HuffmanTree(Scanner input).
   // Uses the data from the input file to construct nodes and return a binary
   // tree.
   // Parameters: asciiChar - the ascii number correlated to a certain character
   // binaryPath - the binary numbers that tell where a character belongs
   // in a tree
   // overallRoot - the root of the tree
   private HuffmanNode buildTree(int asciiChar, String binaryPath, HuffmanNode overallRoot) {
      if (binaryPath.isEmpty() && overallRoot == null) {
         return new HuffmanNode(asciiChar, 0);
      } else if (overallRoot == null) {
         overallRoot = new HuffmanNode(asciiChar, 0);
      }

      char pathNum = binaryPath.charAt(0);

      if (pathNum == '0') {
         overallRoot.leftZeroBranch = buildTree(asciiChar, binaryPath.substring(1),
               overallRoot.leftZeroBranch);
      } else {
         overallRoot.rightOneBranch = buildTree(asciiChar, binaryPath.substring(1),
               overallRoot.rightOneBranch);
      }

      return overallRoot;
   }

   // Post: reads and decodes a compressed file and moves the
   // decompressed data to an ouput file.
   // Parameters: input - the input file with the compressed data
   // output - the output file where the decompressed data is sent to
   // eof - the number to know when to stop reading from the input file
   public void decode(BitInputStream input, PrintStream output, int eof) {
      int varCheck = decodeHelper(input);

      while (varCheck != eof) {
         output.write(varCheck);
         varCheck = decodeHelper(input);
      }

   }

   // Post: helper method to the the decode method. decompresses the data from
   // the input file and returns the data in the form of an ascii number that
   // correlates to a character.
   // Parameters: input - the input file with the compressed data
   private int decodeHelper(BitInputStream input) {
      HuffmanNode decodeTree = overallRoot;
      while (!(decodeTree.leftZeroBranch == null && decodeTree.rightOneBranch == null)) {
         int nextBit = input.readBit();
         if (nextBit == 0) {
            decodeTree = decodeTree.leftZeroBranch;
         } else {
            decodeTree = decodeTree.rightOneBranch;
         }
      }
      return decodeTree.character;
   }
}
