package exercise;

public class UnmappedSizePlusException extends RuntimeException{
    private int aLength;
    private int aWidth;
    private int bLength;
    private int bWidth;
    public UnmappedSizePlusException(int aLength, int aWidth, int bLength, int bWidth){
        this.aLength = aLength;
        this.aWidth = aWidth;
        this.bLength = bLength;
        this.bWidth = bWidth;
    }
    public void showInfo(){
        System.out.println("The size of the first matrix is "+this.aLength+"×"+this.aWidth+
                "\nThe size of the second matrix is "+this.bLength+"×"+this.bWidth);
    }
}
