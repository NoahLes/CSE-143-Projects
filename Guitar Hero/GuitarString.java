
// Class GuitarString creates a guitar string that can be plucked to 
// create a sound that begins fade as time goes on.
import java.util.*;

public class GuitarString {

   // the energy decay of the guittar string's displacement.
   public static final double ENERGY_DECAY = 0.996;

   // Stores the displacement of the guitar string.
   private Queue<Double> ringBuffer = new LinkedList<>();

   // Pre: frequency must be greater or equal to zero and the ring buffer queue
   // must have more than 2 elements (throws an IllegalArgumentException
   // otherwise).
   // Post: Creates a guitar string with a ring buffer to represent the
   // displacement of the guitar string.
   // Parameter - frequency: represents the frequency of the guitar string.
   public GuitarString(double frequency) {
      int N = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
      for (int i = 0; i < N; i++) {
         ringBuffer.add(0.0);
      }
      if (frequency <= 0 || ringBuffer.size() < 2) {
         throw new IllegalArgumentException();
      }
   }

   // Pre: init array must have a length greater than two (throws an
   // IllegalArgumentException
   // otherwise).
   // Post: Creates a guitar string with a ring buffer created with the data from
   // parameter init.
   // Parameter - init: represents the displacement values of the ring buffer.
   public GuitarString(double[] init) {
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }

   }

   // Post: Replaces the values in the ring buffer with random values between -0.5
   // (inclusive)
   // and 0.5 (exclusive).
   public void pluck() {
      for (int i = 0; i < ringBuffer.size(); i++) {
         ringBuffer.remove();
         ringBuffer.add(Math.random() * ((-0.5 - 0.5)) + 0.5);
      }

   }

   // Post: Takes the first two values in the ring buffer, applies the
   // Karplus-Strong algorithm, removes the first value, and adds the new value
   // to the end of the ring buffer.
   void tic() {
      double firstSample = ringBuffer.peek();
      ringBuffer.remove();
      double secondSample = ringBuffer.peek();
      double karplusValue = ENERGY_DECAY * 0.5 * (firstSample + secondSample);
      ringBuffer.add(karplusValue);

   }

   // Post: Returns the current value at the front of the ring buffer.
   public double sample() {
      return ringBuffer.peek();
   }
}
