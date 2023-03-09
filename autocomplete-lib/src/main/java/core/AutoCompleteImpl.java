package core;

import readers.FileAnalyser;
import readers.FileAnalyserImpl;
import readers.LinesWalker;
import readers.LinesWalkerImpl;
import structs.Line;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutoCompleteImpl implements AutoComplete {

    private FileAnalyser analyser;
    private LinesWalker walker;

    private int prefixSize;

    private List<Line> lines;

    public AutoCompleteImpl(String filename, int column, int prefixSize) throws FileNotFoundException {
        this.analyser = new FileAnalyserImpl(filename, column, prefixSize);
        this.walker = new LinesWalkerImpl(filename);
        this.prefixSize = prefixSize;
        lines = analyser.getLines();

    }

    public void prepare() throws FileNotFoundException {
        analyser.buildTree();
    }

    public List<String> processRequest(String searchPrefix, int columnIndex) throws IOException {

        String correctPrefix;

        // if we search prefix that longer than max prefix size in tree
        if (searchPrefix.length() > prefixSize) {
            correctPrefix = searchPrefix.substring(0, prefixSize);
        }
        else {
            correctPrefix = searchPrefix;
        }

        correctPrefix = correctPrefix.toLowerCase();
        searchPrefix = searchPrefix.toLowerCase();

        List<Integer> prefixLines = analyser.getPrefixLines(columnIndex, correctPrefix);

        List<String> allLines = new ArrayList<>();

        for (int line : prefixLines) {

            String findLine = walker.getLine(lines.get(line - 1));
            String[] columns = findLine.split(",");

            if (searchPrefix.length() <= prefixSize) {
                allLines.add(findLine);
            }
            else if (columns[columnIndex].toLowerCase().replace("\"", "").startsWith(searchPrefix)) {
                allLines.add(findLine);
            }

        }

        return allLines;
    }

}
