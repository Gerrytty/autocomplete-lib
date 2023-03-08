import java.io.*;
import java.util.*;

public class Main4 {

    static int prefixSize = 10;

    public static void main(String[] args) throws IOException {

        // memorize a prefix tree in memory
        FileAnalyser analyser = new FileAnalyser("/home/julia/Downloads/airports.csv", prefixSize);
        analyser.buildTree();

        List<Line> lines = analyser.getLines();

        List<Integer> prefixLines = analyser.getPrefixLines(1, "bo");

        System.out.println(prefixLines);

        LinesWalker walker = new LinesWalker("/home/julia/Downloads/airports.csv");

        for (int i = 0; i < 20; i++) {
            System.out.println(i + " " + lines.get(prefixLines.get(i) - 1));
            String findLine = walker.getLine(lines.get(prefixLines.get(i) - 1));
            System.out.println(findLine);
        }


    }
}