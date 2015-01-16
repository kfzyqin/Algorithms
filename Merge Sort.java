package comp1110.lectures.C01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class MergeSort {

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
				l++;
			}
		}
		
		return result;
	}
	
	@Test
	public void testSort() {
		Integer[] single = { 7 };
		Integer[] unsorted = { 5, 2, 1, 3, 4 };
		Integer[] sorted = { 1, 2, 3, 4, 5 };
		
		assertTrue(Arrays.asList(single).equals(mergesort(Arrays.asList(single))));
		assertTrue(Arrays.asList(sorted).equals(mergesort(Arrays.asList(unsorted))));
		
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
		input.close();

	}

}
