package chapter4.s1;

public class Find_Maximum_Subarray {
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
	
	static Side Find_Maximum_Subarray(int[] A, int low, int high) {
		//System.out.println("Here");
		if (high == low) {
			//System.out.println("/////");
			//System.out.println(A[low]);
			//System.out.println("\\\\\\\\\\");
			Side rtn = new Side(low, high, A[low]);
			return rtn;
		}
		
		else {
			int mid = (int) ((low + high) / 2);
			int left_low;
			int left_high;
			int left_sum;
			/*
			left_low = Find_Maximum_Subarray(A, low, mid).sLow;
			left_high = Find_Maximum_Subarray(A, low, mid).sHigh;
			left_sum = Find_Maximum_Subarray(A, low, mid).sSum;
			*/
			
			Side here1 = Find_Maximum_Subarray(A, low, mid);
			left_low = here1.sLow;
			left_high = here1.sHigh;
			left_sum = here1.sSum;
			
			int right_low = -100;
			int right_high = -100;
			int right_sum = -100;
			
			/*
			right_low = Find_Maximum_Subarray(A, mid+1, high).sLow;
			right_high = Find_Maximum_Subarray(A, mid+1, high).sHigh;
			right_sum = Find_Maximum_Subarray(A, mid+1, high).sSum;
			*/
			
			if (A.length-1 >= mid+1) {
				Side here2 = Find_Maximum_Subarray(A, mid+1, high);
				right_low = here2.sLow;
				right_high = here2.sHigh;
				right_sum = here2.sSum;
			}
			
			int cross_low;
			int cross_high;
			int cross_sum;
	
			/*
			cross_low = Find_Max_Crossing_Subarray(tmp).sLow;
			cross_high = Find_Max_Crossing_Subarray(tmp).sHigh;
			cross_sum = Find_Max_Crossing_Subarray(tmp).sSum;
			*/
			
			Side here3 = Find_Max_Crossing_Subarray(A, low, mid, high);
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
	
	static Side Find_Max_Crossing_Subarray(int[] A, int low, int mid, int high) {
		//System.out.println("got you!");
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
			//System.out.println("here?");
			for (int i=mid+1; i<=high; i++) {
				sum = sum + A[i];
				//System.out.println("sum: " + sum);
				if (sum > right_sum) {
					right_sum = sum;
					max_right = i;
				}
			}
		} else {
			System.out.println("right_sum");
			right_sum = 0;
		}
		
		
		
		//System.out.println("left_sum: " + left_sum);
		//System.out.println("right_sum: " + right_sum);
		Side rtn = new Side(max_left, max_right, left_sum + right_sum);
		return rtn;
	}

	public static void main(String[] args) {
		int[] test = {1,2,3,4,5,6};
		Side x = Find_Maximum_Subarray(test, 0, 5);
		System.out.println(x.sSum);
	}

}
