import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

public class Main2 {

    public static void main(String[] args) throws IOException {

        long timeStart = new Date().getTime();
        RandomAccessFile file = new RandomAccessFile("/home/julia/Downloads/airports.csv", "r");

        int currPos = 62286;

        // 175 296

        for (int i = 0; i < 1; i++) {

            file.seek(currPos);

            byte[] bytes = new byte[130];

            file.read(bytes);

//            currPos += 50;

            for (byte b: bytes) {
                System.out.print((char) b);
            }
            System.out.println("");

        }

        long timeEnd = new Date().getTime();
    }
}

