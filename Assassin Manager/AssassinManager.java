
// AssassinManager allows a client to manage a game of assassin where  
// each person in the game of assassin has a certain target that they are  
// trying to assassinate. The client will be told who a target is being 
// stalked by, and who an assassin has killed.
import java.util.*;

public class AssassinManager {

    // The front of the list of names in the kill ring.
    private AssassinNode killRingNames = null;

    // The front of the list of names in the graveyard.
    private AssassinNode graveyardNames = null;

    // Pre: list must have at least one name (throws IllegalArgumentException
    // otherwise).
    // Post: creates an object that holds the names of people in the kill ring.
    // Parameter - names: represents the names of the people added to the game.
    AssassinManager(List<String> names) {
        if (names.size() == 0) {
            throw new IllegalArgumentException("There must be at least one name"
                    + "given for the game to start!");
        }
        for (int i = names.size() - 1; i >= 0; i--) {
            killRingNames = new AssassinNode(names.get(i), killRingNames);
        }
    }

    // Post: prints the names of everyone who is in the kill ring,
    // one per line, indented four spaces in the form
    // "*name* is stalking *name*". If only one person is in the kill
    // ring, it is printed as the person stalking themself.
    void printKillRing() {
        AssassinNode temp = killRingNames;

        if (temp != null && temp.next == null) {
            System.out.println("    " + temp.name + " is stalking " + temp.name);
        } else {
            while (temp.next != null) {
                System.out.println("    " + temp.name + " is stalking " + temp.next.name);
                temp = temp.next;
            }
            System.out.println("    " + temp.name + " is stalking " + killRingNames.name);
        }
    }

    // Post: prints the names of everyone who is in the graveyard in reverse kill
    // order,
    // one per line, indented four spaces in the form "*name* was killed by *name*".
    // Nothing is printed if the graveyard is empty.
    void printGraveyard() {
        AssassinNode temp = graveyardNames;

        while (temp != null) {
            System.out.println("    " + temp.name + " was killed by " + temp.killer);
            temp = temp.next;
        }
    }

    // Post: Checks if the given name is inside the kill ring and returns true.
    // Returns false if the given name is not in the kill ring.
    // Parameter - name: represents the name of a person.
    boolean killRingContains(String name) {
        AssassinNode temp = killRingNames;

        while (temp != null) {
            if (name.equalsIgnoreCase(temp.name)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Post: Checks if the given name is inside the graveyard and returns true.
    // Returns false if the given name is not in the graveyard.
    // Parameter - name: represents the name of a person.
    boolean graveyardContains(String name) {
        AssassinNode temp = graveyardNames;

        while (temp != null) {
            if (name.equalsIgnoreCase(temp.name)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Post: Returns true if the kill ring only contains one person.
    // Returns false otherwise.
    boolean gameOver() {
        return killRingNames.next == null;
    }

    // Post: Returns the name of the winner of the game if the game is over.
    // Returns nothing if the game is not over yet.
    String winner() {
        if (gameOver()) {
            return killRingNames.name;
        }
        return null;
    }

    // Pre: given name must be inside the kill ring (throws IllegalArgumentException
    // otherwise).
    // game must still be in progress (throws IllegalStateException otherwise).
    // Post: records the assassination of the person with the given name and moves
    // the person from the kill ring to the graveyard.
    // Parameter - name: represents the name of a person.
    void kill(String name) {

        AssassinNode current = killRingNames;
        AssassinNode temp = killRingNames;

        if (gameOver()) {
            throw new IllegalStateException("The game is already over!");
        } else if (!killRingContains(name)) {
            throw new IllegalArgumentException("The name provided does not match any name"
                    + "in the kill ring.");
        } else {
            if (current.name.equalsIgnoreCase(name)) {
                while (current.next != null) {
                    current = current.next;
                }
                killRingNames = killRingNames.next;
                

            } else {
                while (!current.next.name.equalsIgnoreCase(name)) {
                    current = current.next;
                }
                temp = current.next;
                current.next = current.next.next;
            }
            temp.killer = current.name;
            temp.next = graveyardNames;
            graveyardNames = temp;

        }
    }

}
