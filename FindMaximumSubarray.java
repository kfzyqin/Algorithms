import java.util.Scanner;

/**
 * Find the maximum subarray
 * @author Harutatsu Akiyama
 */

public class FindMaximumSubarray {
	static class Middle {
		int[] middleA;
		int mLow;
		int mMid;
		int mHigh;
		
		Middle(int[] middleA, int mLow, int mMid, int mHigh) {
			this.middleA = middleA;
			this.mLow = mLow;
			this.mMid = mMid;
			this.mHigh = mHigh;
		}
	}
	
	static class Side {
		int sLow;
		int sHigh;
		int sSum;
		
		Side(int sLow, int sHigh, int sSum) {
			this.sLow = sLow;
			this.sHigh = sHigh;
			this.sSum = sSum;
		}
	
	}
	
	static Side find_Maximum_Subarray_Recursively(int[] A, int low, int high) {
		if (high == low) {
			Side rtn = new Side(low, high, A[low]);
			return rtn;
		}
		
		else {
			int mid = (int) ((low + high) / 2);
			int left_low;
			int left_high;
			int left_sum;
		
			Side here1 = find_Maximum_Subarray_Recursively(A, low, mid);
			left_low = here1.sLow;
			left_high = here1.sHigh;
			left_sum = here1.sSum;
			
			int right_low = -100;
			int right_high = -100;
			int right_sum = -100;
			
			if (A.length-1 >= mid+1) {
				Side here2 = find_Maximum_Subarray_Recursively(A, mid+1, high);
				right_low = here2.sLow;
				right_high = here2.sHigh;
				right_sum = here2.sSum;
			}
			
			int cross_low;
			int cross_high;
			int cross_sum;
	
			Side here3 = find_Max_Crossing_Subarray(A, low, mid, high);
			cross_low = here3.sLow;
			cross_high = here3.sHigh;
			cross_sum = here3.sSum;
			
			if (left_sum >= right_sum && left_sum >= cross_sum) {
				Side rtn1 = new Side(left_low, left_high, left_sum);
				return rtn1;
			}
			
			else if (right_sum >= left_sum && right_sum >= cross_sum) {
				Side rtn2 = new Side(right_low, right_high, right_sum);
				return rtn2;
			}
			
			else {
				Side rtn3 = new Side(cross_low, cross_high, cross_sum);
				return rtn3;
			}
		}
	}
	
	static Side find_Max_Crossing_Subarray(int[] A, int low, int mid, int high) {
		int left_sum = -1000;
		int sum = 0;
		int max_left = 0;
		
		for (int i=mid; i>=low; i--) {
			sum = sum + A[i];
			if (sum > left_sum) {
				left_sum = sum;
				max_left = i;
			}
		}
		int right_sum = -1000;
		sum = 0;
		int max_right = 0;
		
		if (high >= mid+1) {
			for (int i=mid+1; i<=high; i++) {
				sum = sum + A[i];
				if (sum > right_sum) {
					right_sum = sum;
					max_right = i;
				}
			}
		} else {
			System.out.println("right_sum");
			right_sum = 0;
		}
		
		
		
		Side rtn = new Side(max_left, max_right, left_sum + right_sum);
		return rtn;
	}
	
	public static int[] find_Maximum_Subarray_Brute_Forcely(int[] original) {
		int elements = original.length;
		int[] inputs = original;
		int finalSum = -1000;
		int finalLeft = 0;
		int finalRight = 0;
		int[] sums =  new int[elements];
		for (int i=0; i<elements; i++) {
			sums[i] = inputs[i];
			int tmp = inputs[i];
			int maxLeft = i;
			int maxRight = i;
			for (int j=i+1; j<elements; j++) {
				tmp = (tmp + inputs[j]);
				if (sums[i] <= tmp) {
					sums[i] = tmp;
					maxRight = j;
				}
				
			}
			if (sums[i] >= finalSum) {
				finalSum = sums[i];
				finalLeft = maxLeft;
				finalRight = maxRight;
			}
		}
		int[] rtn = new int[finalRight - finalLeft + 1];
		for (int i=0; i<finalRight - finalLeft + 1; i++) {
			rtn[i] = original[i+finalLeft];
		}
		return rtn;
	}
	
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int elements = in.nextInt();
		int inputs[] = new int[elements];
		
		for (int i=0; i<elements; i++) {
			inputs[i] = in.nextInt();
		}
		
		
		
		in.close();
	}
}
