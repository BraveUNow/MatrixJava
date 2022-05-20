package exercise;

public class OutOfSizeException extends RuntimeException{
    private int length;
    private int width;
    public OutOfSizeException(int length, int width){
        this.length = length;
        this.width = width;
    }
    public void showInfo(){
        System.out.println("The size of the matrix is "+this.length+"×"+this.width);
    }
}
