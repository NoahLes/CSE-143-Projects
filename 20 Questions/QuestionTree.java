
// Noah Lesure
// CE 143 AF 
// TA: Ido Avnon
// Homework 7
// QuestionTree creates a guessing game using binary trees that revolves
// around the user answering yes or no questions. If the computer guesses the
// wrong object, the user will be asked what the object was that they were
// thinking of, and a question that can be correlated to the object. this
// answer question pair will be added to the tree. A previous tree can be 
// given as a text file to be used for the game and a tree from a game 
// played can be stored to a text file.
import java.util.*;
import java.io.PrintStream;

public class QuestionTree {

   // the root of the binary tree
   private QuestionNode overallRoot;

   // scans the contents of a text file provided by user.
   private Scanner console;

   // Post: constructs a question tree with one answer node,
   // and a scanner to take in contents of a text file.
   public QuestionTree() {
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
   
   }

   // Post: replaces the current tree with another tree from a text file.
   // Parameter: input - scans the text inside the text file.
   public void read(Scanner input) {
      overallRoot = readHelper(input);
   }

   // Post: helper method for the read method that reads from a file line
   // by line and builds a tree based on the questions and answers given
   // in the file. returns the tree that is built.
   // Parameter: input - scans the text inside the text file.
   private QuestionNode readHelper(Scanner input) {
      String type = input.nextLine();
      String data = input.nextLine();
      QuestionNode createNode = new QuestionNode(data);
   
      if (type.contains("Q:")) {
         createNode.yesNode = readHelper(input);
         createNode.noNode = readHelper(input);
      }
      return createNode;
   
   }

   // Post: stores the tree made from a game to an output file.
   // Parameter: output - prints the contents from a tree into a file.
   public void write(PrintStream output) {
      writeHelper(output, overallRoot);
   
   }

   // Post: helper method for the write method that goes through a tree and
   // prints the answers and questions from the tree into a text file.
   // Parameter:
   // output - prints the contents of the tree into a text file
   // overallRoot - root of the tree
   private void writeHelper(PrintStream output, QuestionNode overallRoot) {
      if (overallRoot.yesNode == null && overallRoot.noNode == null) {
         output.println("A:");
         output.println(overallRoot.data);
      } else {
         output.println("Q:");
         output.println(overallRoot.data);
         writeHelper(output, overallRoot.yesNode);
         writeHelper(output, overallRoot.noNode);
      }
   
   }

   // Post: asks the user a series of yes or no questions until the computer
   // guesses an object correctly or fails. If the computer fails at guessing,
   // the user is asked to provide the object they were thinking of, a question
   // that distinguishes their object, and the answer to the given question.
   // The given question and object is then added to the tree.
   public void askQuestions() {
      overallRoot = askQuestionsHelper(overallRoot);
   }

   // Post: helper method to the askQuestions method that asks the user
   // yes or no questions until it chooses an object. If the object is guessed
   // incorrectly, the the user is asked to provide a question and an object.
   // The question and object are then added to the tree.
   // Parameters: overallRoot - root of the tree
   private QuestionNode askQuestionsHelper(QuestionNode overallRoot) {
      if (overallRoot.yesNode == null && overallRoot.noNode == null) {
         if (yesTo("Would your object happen to be " + overallRoot.data + "?")) {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            QuestionNode newAnswer = new QuestionNode(console.nextLine());
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String newQuestion = console.nextLine();
            if (yesTo("And what is the answer for your object?")) {
               overallRoot = new QuestionNode(newQuestion, newAnswer, overallRoot);
            } else {
               overallRoot = new QuestionNode(newQuestion, overallRoot, newAnswer);
            }
         }
      } else {
         if (yesTo(overallRoot.data)) {
            overallRoot.yesNode = askQuestionsHelper(overallRoot.yesNode);
         } else {
            overallRoot.noNode = askQuestionsHelper(overallRoot.noNode);
         }
      
      }
      return overallRoot;
   }

   // post: asks the user a question, forcing an answer of "y " or "n";
   // returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }

}
