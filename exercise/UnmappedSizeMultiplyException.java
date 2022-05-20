package exercise;

public class UnmappedSizeMultiplyException extends RuntimeException{
    private int aWidth;
    private int bLength;
    public UnmappedSizeMultiplyException(int aWidth, int bLength){
        this.aWidth = aWidth;
        this.bLength = bLength;
    }
    public void showInfo(){
        System.out.println("The width of the first matrix is "+this.aWidth+
                "\nThe length of the second matrix is " + this.bLength);
    }
}
