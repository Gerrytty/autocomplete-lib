package readers;

import structs.Line;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileAnalyser {
    void buildTree() throws FileNotFoundException;

    List<Integer> getPrefixLines(int columnIndex, String prefix);

    List<Line> getLines();

}
