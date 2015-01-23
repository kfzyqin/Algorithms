import java.util.LinkedList;
import java.util.Random;

/*
 * Create a random sample of the set {1,2,3,...,n}
 */

public class RandomSample {
	static LinkedList<Integer> randomSample(int m, int n) {
		Random r = new Random();
		if (m == 0) {
			return new LinkedList<Integer>();
		}
		
		else {
			LinkedList<Integer> S = randomSample(m-1, n-1);
			int i = r.nextInt(n) + 1;
			if (S.contains(i)) {
				S.add(n-1);
			}
			else {
				S.add(i);
			}
			return S;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(randomSample(3,6));
	}
}
