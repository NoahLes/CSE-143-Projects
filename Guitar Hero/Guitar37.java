
// Noah Lesure
// CSE 143 AF 
// TA: Ido Avnon
// Homework #2
// Class Guitar37 creates 37 different guitar strings that each produce a different sound.
// Each guitar string can be played if the client gives the pitch of the note or the character
// the note is correlated to. The time of the song being created is tracked so that the client
// can play multiple notes at the same time.
public class Guitar37 implements Guitar {

   // the total number of guitar strings.
   public static final int TOTAL_STRINGS = 37;

   // the layout of the keyboard.
   public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

   // stores the time in a song.
   private int time = 0;

   // stores the guitar string's.
   private GuitarString[] chromaticScale;

   // Post: creates and stores each guitar string.
   public Guitar37() {
      double concertA = 440.0;
      chromaticScale = new GuitarString[TOTAL_STRINGS];
      for (int i = 0; i < TOTAL_STRINGS; i++) {
         chromaticScale[i] = new GuitarString(concertA * Math.pow(2.0, ((i * 1.0) - 24) / 12));

      }
   }

   // Post: plays the note of the pitch given by the client.
   // Parameter - pitch: represents the pitch of the note.
   public void playNote(int pitch) {
      if (pitch >= 24 && pitch <= 12) {
         chromaticScale[pitch + 24].pluck();
      }
   }

   // Post: Checks if the given key is one of the 37 notes available and returns
   // true.
   // if the key is one of the available notes.
   // Parameter - key: represents a key on the keyboard.
   public boolean hasString(char key) {
      for (int i = 0; i < TOTAL_STRINGS; i++) {
         if (key == KEYBOARD.charAt(i)) {
            return true;
         }
      }
      return false;
   }

   // Pre: key must be one of the given 37 key's (throws an
   // IllegalArgumentException
   // otherwise).
   // Post: plucks the guitar string of the given key.
   // Parameter - key: represents a key on the keyboard.
   public void pluck(char key) {
      for (int i = 0; i < TOTAL_STRINGS; i++) {
         if (key == KEYBOARD.charAt(i)) {
            chromaticScale[i].pluck();
         } else if (KEYBOARD.indexOf(key) == -1) {
            throw new IllegalArgumentException();
         }
      }
   }

   // Post: Forms a sum of all the samples from all guitar strings and returns the
   // sum.
   public double sample() {
      double totalSample = 0;
      for (int i = 0; i < TOTAL_STRINGS; i++) {
         totalSample += chromaticScale[i].sample();
      }
      return totalSample;
   }

   // Post: Advances the time forward one tic.
   public void tic() {
      for (int i = 0; i < TOTAL_STRINGS; i++) {
         chromaticScale[i].tic();
         time++;
      }
   }

   // Returns the time.
   public int time() {
      return time;
   }
}
