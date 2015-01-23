import java.util.Random;

/**
 * Generate a series of random numbers uniformally
 */

public class RandomizedAlgorithm {
	
	static int[] randomizeInPlace(int[] original) {
		Random r = new Random();
		int n = original.length;
		for (int i=0; i<n; i++) {
			int index2 = r.nextInt(n);
			int temp = original[index2];
			original[index2] = original[i];
			original[i] = temp;
		}
		return original;
	}
}
