import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class LinesWalker {

    RandomAccessFile file;

    public LinesWalker(String pathToFile) throws FileNotFoundException {
        this.file = new RandomAccessFile(pathToFile, "r");
    }

    public String getLine(Line line) throws IOException {
        byte[] bytes = new byte[line.getCountOfBytes()];

        file.seek(line.getOffset());

        file.read(bytes);

        return new String(bytes, StandardCharsets.UTF_8);

    }

}
