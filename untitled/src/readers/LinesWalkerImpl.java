package readers;

import structs.Line;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/***
 * Helper class to access to file with RandomAccessFile
 ***/
public class LinesWalkerImpl implements LinesWalker {

    RandomAccessFile file;

    public LinesWalkerImpl(String pathToFile) throws FileNotFoundException {
        this.file = new RandomAccessFile(pathToFile, "r");
    }

    /***
     * @param line (contains start of needed line in bytes and length of line in bytes)
     * @return requested line of file
     * @throws IOException
     */

    public String getLine(Line line) throws IOException {
        byte[] bytes = new byte[line.getCountOfBytes()];

        file.seek(line.getOffset());

        file.read(bytes);

        return new String(bytes, StandardCharsets.UTF_8);

    }

}
