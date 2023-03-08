import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static ArrayList<Line> readFromInputStream(InputStream inputStream)
            throws IOException {

        ArrayList<Line> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            int currentLine = 1;
            int currentOffset = 0;

            String line;
            while ((line = br.readLine()) != null) {

                Line l = new Line(currentLine, line.length(), currentOffset);
                currentOffset += l.getCountOfBytes() + 1;
                lines.add(l);

            }
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {

        // memorize a prefix tree in memory
        FileAnalyser analyser = new FileAnalyser("/home/julia/Downloads/airports.csv");
        analyser.buildTree(10);

        long timeStart = new Date().getTime();

        List<Line> lines = analyser.getLines();

        List<Integer> prefixLines = analyser.getPrefixLines(1, "Bo");

        LinesWalker walker = new LinesWalker("/home/julia/Downloads/airports.csv");

        for (int line : prefixLines) {
            System.out.println(walker.getLine(lines.get(line)));
        }

        long timeEnd = new Date().getTime();
        System.out.println(timeEnd - timeStart + " ms");


    }
}