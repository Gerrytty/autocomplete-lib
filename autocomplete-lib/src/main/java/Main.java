import comparators.IntegerComparator;
import comparators.StringsComparator;
import readers.FileAnalyser;
import readers.FileAnalyserImpl;
import readers.LinesWalker;
import readers.LinesWalkerImpl;
import structs.Line;

import java.io.IOException;
import java.util.*;

public class Main {

    // max word in prefix tree is
    static int prefixSize = 3;
    static int columnIndex;

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            columnIndex = Integer.parseInt(args[0]);
            if (columnIndex < 1) {
                System.out.println("incorrect index, indexing starts from 1, set index to 1 column");
                columnIndex = 1;
            }
        }
        else {
            columnIndex = 1;
        }

        // memorize a prefix tree in memory
        FileAnalyser analyser = new FileAnalyserImpl("/home/julia/Downloads/airports.csv", prefixSize);
        analyser.setColumnIndex(columnIndex);
        analyser.buildTree();

        // Random access for lines in file helper
        LinesWalker walker = new LinesWalkerImpl("/home/julia/Downloads/airports.csv");

        // read user request
        Scanner scanner = new Scanner(System.in);
        String searchPrefix = scanner.nextLine();
        String correctPrefix;

        while (!searchPrefix.equals("!quit")) {

            // if we search prefix that longer than max prefix size in tree
            if (searchPrefix.length() > prefixSize) {
                correctPrefix = searchPrefix.substring(0, prefixSize);
            }
            else {
                correctPrefix = searchPrefix;
            }

            // measure time only for search
            long timeStart = new Date().getTime();

            List<Line> lines = analyser.getLines();
            List<Integer> prefixLines = analyser.getPrefixLines(columnIndex, correctPrefix);

            int countFoundLines = 0;

            List<String> allLines = new ArrayList<>();

            for (int line : prefixLines) {

                String findLine = walker.getLine(lines.get(line - 1));

                String[] strings = findLine.split(",");

                if (searchPrefix.length() <= prefixSize) {
                    allLines.add(findLine);
                    countFoundLines++;
                }
                else if (strings[columnIndex].replace("\"", "").toLowerCase().startsWith(searchPrefix)) {
                    allLines.add(findLine);
                    countFoundLines++;
                }

            }

            if (allLines.size() > 0) {

                // check type of column
                String[] strings = allLines.get(0).split(",");

                if (strings[1].contains("\"")) {
                    allLines.sort(new StringsComparator(columnIndex));
                } else {
                    allLines.sort(new IntegerComparator(columnIndex));
                }
            }

            for (String line : allLines) {
                String[] columns = line.split(",");
                System.out.println(columns[columnIndex] + " " + Arrays.toString(columns));
            }

            long timeEnd = new Date().getTime();
            System.out.println("Found " + countFoundLines + " lines");
            System.out.println(timeEnd - timeStart + " ms");

            searchPrefix = scanner.nextLine();

        }

    }
}