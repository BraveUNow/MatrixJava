package exercise;
import java.util.Arrays;

public class Matrix
{
    public int length;
    public int width;
    private double[][] mat;//内部数组实现
    //Construct the matrix with the length and width of the input matrix
    public Matrix(int m, int n) {
        this.length = m;
        this.width = n;
        this.mat = new double[m][n];
    }
    //Construct the matrix with a two-dimensional array
    public Matrix(double[][] mat){
        this.mat = mat;
        this.length = mat.length;
        this.width = mat[0].length;;
    }

    //create a zero matrix
    public static Matrix Zero(int m, int n){
        Matrix mat = new Matrix(m, n);
        for(int i = 0; i<m;i++) {
            for (int j = 0; j<n;j++) {
                mat.set(i, j, 0.0);
            }
        }
        return mat;
    }
    //create an identity matrix
    public static Matrix Eye(int m) {
        Matrix mat = new Matrix(m, m);
        for(int i = 0; i<m;i++) {
            for (int j = 0; j<m;j++) {
                if (i == j) {
                    mat.set(i, j, 1.0);
                }
                else {
                    mat.set(i, j, 0.0);
                }
            }
        }
        return mat;
    }

    //update matrix element
    public void set(int i, int j, double value){
        this.mat[i][j] = value;
    }

    //output matrix element
    public double get(int i, int j){
        return this.mat[i][j];
    }

    //output matrix view
    public void show(){
        for (int i = 0; i<this.length;i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                System.out.printf("%g, ", this.mat[i][j]);
            }
            System.out.println();
        }
    }
    //out view of one-dimensional array
    public static <E> void show(E[] a){
        if (a[0] instanceof Double) {
            for (int i = 0; i < a.length; i++)
            {
                System.out.printf("%g, ",a[i]);
            }
        }else if (a[0] instanceof Integer){
            for (int i = 0; i < a.length; i++)
            {
                System.out.printf("%d, ",a[i]);
            }
        }else{
            for (int i = 0; i < a.length; i++)
            {
                System.out.printf("%s, ",a[i]);
            }
        }
        System.out.println();
    }
    //sum of two matrices
    public void plus(Matrix a){
        if (this.length != a.length || this.width != a.width)
        {
            throw new UnmappedSizePlusException(this.length,this.width,a.length,a.width);

        }
        for (int i = 0; i<this.length;i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                this.mat[i][j] += a.get(i, j);
            }
        }
    }
    //subtraction of two matrices
    public void subtract(Matrix a){
        if (this.length != a.length || this.width != a.width){
            throw new UnmappedSizePlusException(this.length,this.width,a.length,a.width);
        }
        for (int i = 0; i<this.length;i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                this.mat[i][j] -= a.get(i, j);
            }
        }
    }
    //static method of sum of two matrices
    public static Matrix plus(Matrix a, Matrix b){
        if(a.length != b.length || a.width != b.width){
            throw new UnmappedSizePlusException(a.length,a.width,b.length,b.width);

        }
        Matrix newMatrix = new Matrix(a.length, a.width);
        for(int i = 0;i<a.length;i++){
            for(int j = 0;j<a.width;j++){
                a.set(i, j, a.get(i, j)+b.get(i, j));
            }
        }
        return newMatrix;
    }
    //static method of subtraction of two matrices
    public static Matrix subtract(Matrix a, Matrix b){
        if(a.length != b.length || a.width != b.width){
            throw new UnmappedSizePlusException(a.length,a.width,b.length,b.width);

        }
        Matrix newMatrix = new Matrix(a.length, a.width);
        for(int i = 0;i<a.length;i++){
            for(int j = 0;j<a.width;j++){
                a.set(i, j, a.get(i, j)-b.get(i, j));
            }
        }
        return newMatrix;
    }
    //clone the matrix
    public Matrix clone(){
        Matrix newMat = new Matrix(this.length,this.width);
        for(int i=0;i<this.length;i++){
            for(int j=0;j<this.width;j++){
                newMat.set(i,j,this.get(i, j));
            }
        }
        return newMat;
    }
    //get the row of the matrix
    public Double[] getRow(int m){
        if(this.length <= m){ //exceeding of rows
            throw new OutOfSizeException(this.length, this.width);
            //System.out.println("Out of size");
            //System.exit(1);
        }
        Double[] row = new Double[this.width];
        for(int i = 0;i<this.width;i++){
            row[i] = this.mat[m][i];
        }
        return row;
    }
    //get the column of the matrix
    public Double[] getColumn(int n)
    {
        if (this.width <= n)
        { //exceeding of columns
            throw new OutOfSizeException(this.length, this.width);
            //System.out.println("Out of size");
            //System.exit(1);
        }
        Double[] column = new Double[this.length];
        for (int i = 0; i < this.length; i++)
        {
            column[i] = this.mat[i][n];
        }
        return column;
    }
    //get a submatrix by row
    public Matrix getRows(int... x){
        Matrix newMatrix = new Matrix(x.length, this.width);//create a new matrix
        int k = 0;//record the number of rows, k+1 when a row is added
        for (int row: x){
            if(this.length <= row){
                throw new OutOfSizeException(this.length, this.width);
                //System.out.println("Out of size");
                //System.exit(1);
            }
            for (int j = 0;j<this.width;j++){//copy data of every row
                newMatrix.set(k,j,this.mat[row][j]);
            }
            k++;
        }
        return newMatrix;
    }
    //get a submatrix by column
    public Matrix getColumns(int... x){
        Matrix newMatrix = new Matrix(this.length, x.length);
        int k = 0;
        for (int column: x){
            if(this.width <= column){
                throw new OutOfSizeException(this.length, this.width);
                //System.out.println("Out of size");
                //System.exit(1);
            }
            for (int i = 0;i<this.length;i++){//copy data of every column
                newMatrix.set(i,k,this.mat[i][column]);
            }
            k++;
        }
        return newMatrix;
    }
    //sumproduct of two arrays
    private double arrayMultiply(Double[] x, Double [] y){
        double sum = 0;
        for(int i=0;i<x.length;i++){
            sum += x[i] * y[i];
        }
        return sum;
    }
    //multiplication of two(multi) matrices
    public void Multiply(Matrix... x) throws UnmappedSizeMultiplyException
    {
        for(Matrix k : x){
            //the width of the first matrix and the length of the second matrix are not equal
            if (this.width != k.length){
                throw new UnmappedSizeMultiplyException(this.width, k.length);
                //System.out.println("Unmap size, can't multiply the matrix");
                //return;
            }
            //The new matrix after each iteration which is replaced at the end of the iteration
            double[][] tempMat = new double[this.length][k.width];//
            for(int i=0;i<this.length;i++){
                for (int j=0;j<k.width;j++){
                    Double[] array1 = this.getRow(i);//get the ith row of the first matrix
                    Double[] array2 = k.getColumn(j);//get the jth column of the second matrix
                    tempMat[i][j] = arrayMultiply(array1, array2);
                }
            }
            this.mat = (double[][])tempMat.clone();//assign the new matrix to the original matrix
            this.width = k.width;//update the width of the matrix
        }
    }
    public static Matrix Multiplyed(Matrix ...x){
        Matrix newMatrix = x[0].clone();
        try {
            newMatrix.Multiply(Arrays.copyOfRange(x, 1, x.length));
        }catch(UnmappedSizeMultiplyException e){
            e.showInfo();
            e.printStackTrace();
            System.exit(1);
        }
        return newMatrix;
    }
    //merge matrix from the right
    public Matrix hstack(Matrix mat){
        if (this.length != mat.length){
            throw new UnmappedSizePlusException(this.length,this.width,mat.length,mat.width);
            //System.out.println("Unmap size");
            //System.exit(1);
        }
        int n = this.width + mat.width;//the width of the new matrix
        Matrix newMatrix = new Matrix(this.length, n);
        for (int i=0;i<this.length;i++){
            for (int j=0;j<n;j++){
                if (j>=this.width){
                    newMatrix.set(i, j, mat.get(i, j-this.width));
                }else{
                    newMatrix.set(i, j, this.mat[i][j]);
                }
            }
        }
        return newMatrix;
    }
    //merge matrix from the lower side
    public Matrix vstack(Matrix mat){
        if (this.width != mat.width){
            throw new UnmappedSizePlusException(this.length,this.width,mat.length,mat.width);

        }
        int m = this.length + mat.length;////the length of the new matrix
        Matrix newMatrix = new Matrix(m, this.width);
        for (int i=0;i<m;i++){
            for (int j=0;j<this.width;j++){
                if (i>=this.length){
                    newMatrix.set(i, j, mat.get(i-this.length, j));
                }else{
                    newMatrix.set(i, j, this.mat[i][j]);
                }
            }
        }
        return newMatrix;
    }
    //inversion of the matrix
    public Matrix inverse(){
        if(this.length != this.width){
            throw new NotSquareMatrixException(this.length,this.width);

        }
        Matrix mat = Matrix.Eye(this.length);//create an identity matrix
        mat = this.hstack(mat);//merge the identity matrix to the original matrix from the right hand
        for (int i=0;i<this.length;i++){
            for (int j=0;j<mat.width;j++){
                mat.set(i, j, mat.get(i, j)/mat.get(i, i));//normalize the row entry to be processed
            }
            for (int k=0;k<this.length;k++){
                double temp = mat.get(k, i);
                if (k != i){
                    for (int j=0;j<mat.width;j++){
                        mat.set(k, j, mat.get(k, j)-temp*mat.get(i,j));
                    }
                }
            }
        }
        int[] n = new int[this.length];
        for (int i=0;i<this.length;i++){
            n[i] = i + this.length;
        }
        mat = mat.getColumns(n);
        return mat;
    }
    //Ordered arrays starting at 0
    private int[] orderedArray(int n){
        int[] a = new int[n];
        for(int i=0;i<n;i++){
            a[i] = i+1;
        }
        return a;
    }
    //Delete the column and the first row of the matrix
    private Matrix cutMatrix(int n){
        Matrix newMatrix = new Matrix(this.length-1, this.length-1);
        for (int i=0;i<this.length-1;i++){
            for (int j=0;j<this.length-1;j++){
                if(j >= n)
                {
                    newMatrix.set(i, j, this.mat[i+1][j+1]);
                }else{
                    newMatrix.set(i, j, this.mat[i+1][j]);
                }
            }
        }
        return newMatrix;
    }

    //Matrix determinant, based on recursion
    public static double Determinant(Matrix mat){
        if (mat.length != mat.width){
            throw new NotSquareMatrixException(mat.length, mat.width);

        }
        double value = 0.0;
        for (int i=0;i<mat.length;i++){
            if (mat.length == 1){    //1*1 matrix
                value = mat.get(0,0);
                break;
            }else if (mat.length == 2){    //2*2 matrix
                value =  mat.get(0,0)*mat.get(1,1)-mat.get(0,1)*mat.get(1,0);
                break;
            }else{
                if (i % 2 == 0)
                {
                    value += mat.get(0,i)*Determinant(mat.cutMatrix(i));
                }else{
                    value -= mat.get(0,i)*Determinant(mat.cutMatrix(i));
                }
            }
        }
        return value;
    }
    //transpose of matrix
    public static Matrix Transpose(Matrix mat){
        Matrix newMat = new Matrix(mat.width, mat.length);
        for(int i=0;i<mat.width;i++){
            for (int j=0;j<mat.length;j++){
                newMat.set(i, j, mat.get(j, i));
            }
        }
        return newMat;
    }
    //innerProduct between two vectors
    public static double innerProduct(Matrix a, Matrix b){
        Matrix newMat = a.clone();
        newMat = Transpose(newMat);
        newMat.Multiply(b);
        return newMat.get(0,0);
    }
    //QR decomposition
    public Matrix[] QR_decomposition(){
        if (this.length != this.width){
            throw new NotSquareMatrixException(this.length, this.width);

        }
        Matrix[] oldVectors = new Matrix[this.length];//n column vectors in total
        for (int i=0;i<this.length;i++){
            oldVectors[i] = this.getColumns(i);//get the ith column
        }
        Matrix[] newVectors = new Matrix[this.length];//n orthogonal vectors
        for (int i=0;i<this.length;i++){
            newVectors[i] = oldVectors[i].clone();//copy element of column vector
            for (int j=0;j<i;j++){
                double temp1 = innerProduct(oldVectors[i], newVectors[j]);
                double temp2 = innerProduct(newVectors[j],newVectors[j]);
                temp1 = temp1 / temp2;
                Matrix tempVector = new Matrix(this.length,1);
                for (int k=0;k<this.length;k++){
                    tempVector.set(k,0,newVectors[j].get(k,0)*temp1);
                }
                newVectors[i].subtract(tempVector);
            }
        }
        //Find each orthonormal basis and normalize it below，as b/|b|
        for (int i=0;i<this.length;i++) {
            double temp = Math.sqrt(innerProduct(newVectors[i], newVectors[i]));
            for (int j = 0; j < this.length; j++) {
                newVectors[i].set(j, 0, newVectors[i].get(j, 0) /temp);
            }
        }
        //Upper triangular matrix R
        Matrix R = Zero(this.length, this.width);
        for (int i=0;i<this.length;i++){
            for (int j=i;j<this.length;j++){
                R.set(i,j, innerProduct(newVectors[i],oldVectors[j]));
            }
        }
        //merge matrices
        for (int i=1;i<newVectors.length;i++){
            newVectors[0] = newVectors[0].hstack(newVectors[i]);
        }
        Matrix[] QR = new Matrix[2];
        QR[0] = newVectors[0];//Q matrix
        QR[1] = R;//R matrix
        return QR;
    }
    //get eigenvalues
    public Matrix eigenValue(int ...time){//iteration times
        if (this.length != this.width){
            throw new NotSquareMatrixException(this.length,this.width);
        }
        Matrix A = this.clone();
        int times;
        if (time.length==0){
            times = 20;
        }else{
            times = time[0];
        }
        for (int i=0;i<times;i++){
            Matrix Q = A.QR_decomposition()[0];
            Matrix R = A.QR_decomposition()[1];
            A = Multiplyed(R, Q);
        }
        return A;
    }

    public static void main(String[] args)
    {
        Matrix f = Matrix.Eye(3);
        f.set(0,0,-1);
        f.set(0,1,1);
        f.set(0,2,0);
        f.set(1,0,-4);
        f.set(1,1,3);
        f.set(1,2,0);
        f.set(2,0,1);
        f.set(2,1,0);
        f.set(2,2,2);
        //System.out.println(Determinant(e));
        //f.QR_decomposition()[1].show();
        f.eigenValue(50).show();
        Matrix a = new Matrix(2,2);
        a.set(0,0,2);
        a.set(0,1,3);
        a.set(1,0,3);
        a.set(1,1,-6);
        a.eigenValue().show();
        //show(f.getColumn(2));
        //Multiplyed(f,a);
        try{
            //f.Multiply(a);
            f.plus(a);
        }catch(UnmappedSizeMultiplyException e){
            e.showInfo();
            e.printStackTrace();
        }catch(UnmappedSizePlusException e){
            e.showInfo();
            e.printStackTrace();
        }

    }
}
