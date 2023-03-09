package readers;

import structs.Line;
import structs.PrefixTree;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileAnalyserImpl implements FileAnalyser {

    private String pathToFile;
    private List<Line> lines;

    private PrefixTree tree;

    private int columnIndex;

    private int prefixSize;

    public FileAnalyserImpl(String pathToFile, int columnIndex, int prefixSize) {
        this.pathToFile = pathToFile;
        this.lines = new ArrayList<>();
        this.prefixSize = prefixSize;
        this.columnIndex = columnIndex;
    }

    public void buildTree() throws FileNotFoundException {
        File file = new File(pathToFile);
        InputStream inputStream = new FileInputStream(file);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            int currentLine = 1;
            int currentOffset = 0;

            PrefixTree columnsTree = new PrefixTree();

            String line;
            while ((line = br.readLine()) != null) {

                Line l = new Line(currentLine, line.getBytes(StandardCharsets.UTF_8).length, currentOffset);
                currentOffset += l.getCountOfBytes() + 1;
                this.lines.add(l);

                String[] columns = line.split(",");

                String columnString = columns[columnIndex];
                columnString = columnString.toLowerCase();
                columnString = columnString.replace("\"", "");

                int maxPrefix = Math.min(prefixSize, columnString.length());
                columnString = columnString.substring(0, maxPrefix);

                columnsTree.addWord(columnString, currentLine);

                currentLine++;

            }

            tree = columnsTree;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Integer> getPrefixLines(int columnIndex, String prefix) {
        return tree.getPrefixLines(prefix.toLowerCase());
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }
}
