package structs;

/***
 * Data class for line meta information
 */
public class Line {

    private int num;
    private int countOfBytes;
    private int offset;

    public Line(int num, int countOfBytes, int offset) {
        this.num = num;
        this.countOfBytes = countOfBytes;
        this.offset = offset;
    }

    public int getNum() {
        return num;
    }

    public int getCountOfBytes() {
        return countOfBytes;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "index = " + num + " Count of bytes " + countOfBytes + " offset " + offset;
    }

}
