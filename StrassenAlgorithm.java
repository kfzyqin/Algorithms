/**
 * Java program to implement the Strassen's method for matrix multiplication
 * @author Harutatsu Akiyama
 * @references http://www.sanfoundry.com/java-program-strassen-algorithm/
 */

public class StrassenAlgorithm {
	/* Define the matrix class */
	int row;
	int column;
	double[][] matrix;
	
	/* Initialization of the class */
	public StrassenAlgorithm(int row, int column, double[][] matrix) {
		this.row = row;
		this.column = column;
		this.matrix = matrix;
	}
	
	/* The Strassen's algorithm */
	public double[][] multiply(double[][] A, double[][] B) {
		
		int n = A.length;
		double[][] R = new double[n][n];
		
		/* The basic case */
		if (n == 1) {
			R[0][0] = A[0][0] * B[0][0];
		}
		
		else {
			double[][] A11 = new double[n/2][n/2];
			double[][] A12 = new double[n/2][n/2];
			double[][] A21 = new double[n/2][n/2];
			double[][] A22 = new double[n/2][n/2];
			double[][] B11 = new double[n/2][n/2];
			double[][] B12 = new double[n/2][n/2];
			double[][] B21 = new double[n/2][n/2];
            double[][] B22 = new double[n/2][n/2];
            
            /* Take the matrix into 4 parts */
            split(A, A11, 0 , 0);
            split(A, A12, 0 , n/2);
            split(A, A21, n/2, 0);
            split(A, A22, n/2, n/2);
            
            split(B, B11, 0 , 0);
            split(B, B12, 0 , n/2);
            split(B, B21, n/2, 0);
            split(B, B22, n/2, n/2);
			
            double [][] M1 = multiply(add(A11, A22), add(B11, B22));
            double [][] M2 = multiply(add(A21, A22), B11);
            double [][] M3 = multiply(A11, sub(B12, B22));
            double [][] M4 = multiply(A22, sub(B21, B11));
            double [][] M5 = multiply(add(A11, A12), B22);
            double [][] M6 = multiply(sub(A21, A11), add(B11, B12));
            double [][] M7 = multiply(sub(A12, A22), add(B21, B22));
            
            double [][] C11 = add(sub(add(M1, M4), M5), M7);
            double [][] C12 = add(M3, M5);
            double [][] C21 = add(M2, M4);
            double [][] C22 = add(sub(add(M1, M3), M2), M6);
 
            /* Put 4 pieces into together */
            join(C11, R, 0 , 0);
            join(C12, R, 0 , n/2);
            join(C21, R, n/2, 0);
            join(C22, R, n/2, n/2);
            
		}
		return R;
	}
	
	/* Make sure the both matrice are the square matrices */
	public StrassenAlgorithm multiply(StrassenAlgorithm toMul) {
		if (this.column != toMul.row) {
			return null;
		}
		
		else {
			if (this.row == this.column && toMul.row == toMul.column) {
				StrassenAlgorithm receiver = new StrassenAlgorithm(this.row, this.column, null);
				receiver.matrix = multiply(this.matrix, toMul.matrix);
				return receiver;				
			}
			return null;
		}
	}
	
	public double[][] sub(double[][] A, double[][] B)
    {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }
  
    public double[][] add(double[][] A, double[][] B)
    {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }
	
	public static void split(double[][] P, double[][]C, int iB, int jB) {
		for (int i=0; i<C.length; i++) {
			for (int j=0; j<C.length; j++) {
				C[i][j] = P[i+iB][j+jB];
			}
		}
	}
	
	public void join(double[][] C, double[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }    
	
	public String toString() {
		String rtn = "";
		for (int i=0; i<row; i++) {
			for (int j=0; j<column; j++) {
				if (j != column-1) {
					rtn = rtn + matrix[i][j] + " ";
				}
				else {
					rtn = rtn + matrix[i][j];
				}
			}
			rtn = rtn + "\n";
		}
		return rtn;
	}
}
