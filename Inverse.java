package chapter2.s3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Let A[1..n] be an array of n distinct numbers. If i < j and A[i] > A[j], then the pair (i, j)
 * is called an inverse of A.
 *
 */

public class Inverse {
	static int sum = 0;

	private static List<Integer> mergesort(List<Integer> unsorted) {
		if (unsorted.size() <= 1)
			return unsorted;
		
		int size = unsorted.size();
		List<Integer> left = mergesort(unsorted.subList(0, size/2));
		List<Integer> right = mergesort(unsorted.subList(size/2, size));
		
		int l = 0;
		int r = 0;
		List<Integer> result = new ArrayList<Integer>(size);
		while (l < left.size() || r < right.size()) {
			if (l == left.size() || (r < right.size() && left.get(l) > right.get(r))) {
				result.add(right.get(r));
				
				r++;
			} else {
				result.add(left.get(l));
				if (!(l == left.size())) {
					sum++;
				}
				l++;
			}
		}
		
		return result;
	}

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		System.out.println("enter numbers: ^D to finish");
		Scanner input = new Scanner(System.in);
		while (input.hasNextInt()) {
			list.add(input.nextInt());
		}
		System.out.println("Unsorted: "+list);
		System.out.println("Sorted: "+mergesort(list));
		System.out.println("inverse: " + sum);
		input.close();

	}

}
