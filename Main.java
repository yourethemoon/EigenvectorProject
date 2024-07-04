import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //Ideas: Change of Basis, Phase Portrait Plot, Polar Form for complex numbers
    public static int size = 0;
    public static void main(String[] args){
        //if matrix is nilpotent then :
        System.out.println("Weclome Kevi's Eigenvector Calculator! ");
        System.out.println("Please enter the size of your matrix : ");
        Scanner scan = new Scanner(System.in);
        size = scan.nextInt();
        int row = 0;
        ComplexNumber [][] m = new ComplexNumber[size][size];
        System.out.println("Complex entries must be written in a+bi form");
        String temp = "";
        while (row < size){
            System.out.println("Please enter row " + (row+1) + " of the " + size + "x" + size + " matrix : ");
            for (int col = 0; col < size; col++){
                temp = scan.next();
                m[row][col] = ComplexNumber.parseComplex(temp);
            }
            row++;
        }
        //Must change all non-double entries to doubles
        System.out.println("A = ");
        System.out.println(Matrix.displayMatrix(m));
        ComplexNumber[] m1 = new ComplexNumber[m.length];
        for (int j = 0; j < m.length; j++){
            m1[j] = new ComplexNumber(j+1,0);
        }
        Matrix A = new Matrix(m);
        LUDecomposition b = new LUDecomposition(A);
        System.out.println("L = " + Matrix.displayMatrix(b.getL()));
        System.out.println("U = " + Matrix.displayMatrix(b.getU()));
        //Start of eigenvector calculation in main method
        ComplexNumber [] v = new ComplexNumber [size];
        for (int i = 0; i < size; i++){
            v[i] = new ComplexNumber(Math.random() * 10000, Math.random() * 10000);
        }
        System.out.print("v1 = " + Matrix.displayMatrix(Matrix.normalizeMatrix(Matrix.matrixMultiplication(m, v, 75))));

    }
    //LU algorithm start
    //public static void main(String[] args){

    //}
    
}
