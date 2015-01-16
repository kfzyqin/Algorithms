package chapter2.s1;

import java.util.Scanner;

/**
 * Insertion Sort
 */

public class Insertion {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int size = in.nextInt();
		
		int[] numbers = new int[size];
		
		for (int i=0; i<size; i++) {
			numbers[i] = in.nextInt();
		}
		
		for (int j=1; j<size; j++) {
			int key = numbers[j];
			int i = j-1;
			while (i >= 0 && numbers[i] >= key) {
				numbers[i+1] = numbers[i];
				i--;
			}
			numbers[i+1] = key;
		}
		
		for (int i=0; i<size; i++) {
			System.out.print(numbers[i] + " ");
		}
		
		
	}
}
