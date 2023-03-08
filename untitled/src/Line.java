public class Line {

    private int num;
    private int countOfBytes;
    private int offset;

    public Line() {

    }

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

    public void setNum(int num) {
        this.num = num;
    }

    public void setCountOfBytes(int countOfBytes) {
        this.countOfBytes = countOfBytes;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "index = " + num + " Count of bytes " + countOfBytes + " offset " + offset;
    }

}
