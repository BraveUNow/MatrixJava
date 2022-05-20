package exercise;

public class NotSquareMatrixException extends RuntimeException{
    private int length;
    private int width;
    public NotSquareMatrixException(int length, int width){
        this.length = length;
        this.width = width;
    }
    public void showInfo(){
        System.out.println("The length of the matrix is "+this.length+" ,the width of the matrix is "+this.width);
    }
}
