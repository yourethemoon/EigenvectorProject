import java.util.Arrays;
public class Matrix implements Cloneable, java.io.Serializable {
    //Matrix Class is Arrays with Complex object entries
    private ComplexNumber[][] A;
    private int m, n;
    public static String displayMatrix(ComplexNumber [][] mat){
        String output = "";
        for (ComplexNumber[] row: mat){
            output += Arrays.toString(row) + "\n";
        }
        return output;
    }
    public static String displayMatrix(Matrix A){
        String output = "";
        ComplexNumber[][] mat = A.getArrayCopy();
        for (ComplexNumber[] row: mat){
            output += Arrays.toString(row) + "\n";
        }
        return output;
    }
    public static String displayMatrix(ComplexNumber [] mat){
        String output = "[";
        for (ComplexNumber row: mat){
            output += row.toString() + "\n" + ", ";
        }
        return output.substring(0, output.length()-2) + "]";
    }
    public Matrix (int n) {
        this.n = n;
        A = new ComplexNumber[n][n];
    }
    public Matrix (int m, int n) {
        this.m = m;
        this.n = n;
        A = new ComplexNumber[m][n];
    }
    public Matrix (int m, int n, ComplexNumber s) {
        this.m = m;
        this.n = n;
        A = new ComplexNumber[m][n];
        for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
              A[i][j] = s;
           }
        }
     }
    public ComplexNumber[][] getArrayCopy () {
        ComplexNumber[][] C = new ComplexNumber[m][n];
        for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
              C[i][j] = A[i][j];
           }
        }
        return C;
    }
    public ComplexNumber[][] getArray () {
        return A;
    }
    public Matrix (ComplexNumber[][] A) {
        m = A.length;
        n = A[0].length;
        for (int i = 0; i < m; i++) {
           if (A[i].length != n) {
              throw new IllegalArgumentException("All rows must have the same length.");
           }
        }
        this.A = A;
    }
    public Matrix (ComplexNumber[][] A, int m, int n) {
        this.A = A;
        this.m = m;
        this.n = n;
    }
    public Matrix (ComplexNumber vals[], int m) {
        this.m = m;
        n = (m != 0 ? vals.length/m : 0);
        if (m*n != vals.length) {
           throw new IllegalArgumentException("Array length must be a multiple of m.");
        }
        A = new ComplexNumber[m][n];
        for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
              A[i][j] = vals[i+j*m];
           }
        }
    }
    public int getRowDimension () {
        return m;
    }
    public int getColumnDimension () {
        return n;
    }
    public static ComplexNumber[] matrixMultiplication(ComplexNumber[][] nn, ComplexNumber[] n1){
        int n = nn.length;
        ComplexNumber [] temp = new ComplexNumber [n]; 
        for(int row = 0; row < n;row++){
            temp[row] = new ComplexNumber(0,0);
            for(int col = 0; col < n; col++){
                temp[row].add(ComplexNumber.multiply(nn[row][col], n1[col]));
            }
        }
        return temp;
    }
    public static ComplexNumber[][] matrixMultiplication(ComplexNumber[][] nn, ComplexNumber[][] nn2){
        int n = nn.length;
        ComplexNumber [][] temp = new ComplexNumber [n][n]; 
        for(int row = 0; row < n;row++){
            for(int col = 0; col < n; col++){
                temp[row][col] = new ComplexNumber(0,0);
                for (int i = 0; i < n; i++){
                    temp[row][col].add(ComplexNumber.multiply(nn[row][i], nn2[i][col]));
                }
            }
        }
        return temp;
    }
    public static ComplexNumber [][] matrixMultiplication(ComplexNumber[][] nn, ComplexNumber[][] nn2, int n){
        if (n == 0){
            ComplexNumber[][] temp = new ComplexNumber [nn.length][nn.length];
            for(int row = 0; row < nn.length; row++){
                for(int col = 0; col < nn.length; col++){
                    if( row == col){
                        temp[row][col] = new ComplexNumber(1,0);
                    }
                    else{
                        temp[row][col] = new ComplexNumber(0,0);
                    }
                }
            }
            return temp;
        }
        else{
            return matrixMultiplication(nn, matrixMultiplication(nn, nn2, n-1));
        }
    }
    public static ComplexNumber [] matrixMultiplication(ComplexNumber[][] nn, ComplexNumber[] n1, int n){
        if (n == 1){
            return n1;
        }
        else{
            return matrixMultiplication(nn, matrixMultiplication(nn, n1, n-1));
        }
        //returns m^n times n1 
    }
    public static ComplexNumber [] normalizeMatrix(ComplexNumber[] n1){
        ComplexNumber [] v = new ComplexNumber [n1.length]; 
        double sum = 0;
        for (int i = 0; i < n1.length; i++){
            sum += Math.pow(n1[i].mod(), 2);
        }
        sum = Math.pow(sum, (0.5));
        ComplexNumber temp = new ComplexNumber(sum,0);
        for (int j = 0; j < n1.length; j++){
            v[j] = ComplexNumber.divide(n1[j], temp);
        }
        return v;
    }
    //Assume A is nx2n
    public static Matrix rowReduce(Matrix A){
        ComplexNumber[][] mat = A.getArrayCopy();
        int m = A.getColumnDimension();
        int n = A.getRowDimension();
        
    }
}
