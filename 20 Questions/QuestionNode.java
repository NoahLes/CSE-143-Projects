
// Noah Lesure
// CSE 143 AF
// TA: Ido Avnon
// Homework 7
// QuestionNode creates a binary tree that is constructed based on yes
// or no questions. 
public class QuestionNode {

   // the data of either an object or a question.
   public String data;

   // a left branch of the tree that is created or explored if the user
   // responds with a yes answer.
   public QuestionNode yesNode;
   
   // a right branch of the tree that is created or explored if the user
   // responds with a no answer.
   public QuestionNode noNode;

   // Post: constructs a leaf node with the data provided
   // Parameter: data - the data of an object or question
   public QuestionNode(String data) {
      this(data, null, null);
   }

   // constructs a branch node with the provided data and left/right tree paths.
   // Parameter:
   // data - the data of an object or question
   // yesNode - a left branch of the tree that is created or explored if the user
   // responds with a yes answer.
   // noNode - a right branch of the tree that is created or explored if the user
   // responds with a no answer.
   public QuestionNode(String data, QuestionNode yesNode, QuestionNode noNode) {
      this.data = data;
      this.yesNode = yesNode;
      this.noNode = noNode;
   }

}
