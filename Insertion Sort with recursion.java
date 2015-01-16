package chapter2.s3;

import java.util.Scanner;

public class Exercises_2_3_4 {
	static int[] insert(int target, int[] previous) {
			/* Expand the array to get the insertion */
			int[] temp = new int[previous.length+1];
			for (int i=0; i<previous.length; i++) {
				temp[i] = previous[i];
			}
			
			/* Whether searched out */
			boolean end = true;
			
			for (int i=previous.length-1; i>=0; i--) {
				/* Have not find the proper position, move the current one to the right */
				if (target < temp[i]) {
					temp[i+1] = temp[i];
				}
				
				/* Find the proper position in the middle */
				else {
					temp[i+1] = target;
					end = false;
					break;
				}
			}
			
			/* Should be put in the front */
			if (end) {
				temp[0] = target;
			}
			return temp;
	}
	
	/* Split the arrays */
	static int[] split(int[] original) {
		/* Cannot be split */
		if (original.length == 1) {
			return original;
		}
		
		/* Split */
		else {
			int[] temp = new int[original.length - 1];
			for (int i=0; i<original.length - 1; i++) {
				temp[i] = original[i];
			}
			return insert(original[original.length - 1], split(temp));
		}
 	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("How many numbers? ");
		int n = in.nextInt();
		
		/* Initialize the arrays of values */
		int[] numbers = new int[n];
		for (int i=0; i<n; i++) {
			numbers[i] = in.nextInt();
		}
		
		/* Begin sorting */
		int[] x = split(numbers);
		
		/* Print out the result */
		for (int i=0; i<x.length; i++) {
			System.out.print(x[i] + " ");
		}
		
		in.close();
	}

}
