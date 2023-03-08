import java.io.*;
import java.util.*;

public class Main {

    static int prefixSize = 10;

    public static void main(String[] args) throws IOException {

        // memorize a prefix tree in memory
        FileAnalyser analyser = new FileAnalyser("/home/julia/Downloads/airports.csv", prefixSize);
        analyser.buildTree();

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

            long timeStart = new Date().getTime();

            List<Line> lines = analyser.getLines();

            List<Integer> prefixLines = analyser.getPrefixLines(1, correctPrefix);

            System.out.println(prefixLines);

            LinesWalker walker = new LinesWalker("/home/julia/Downloads/airports.csv");

            int countFoundLines = 0;

            List<String> allLines = new ArrayList<>();

            for (int line : prefixLines) {
                String findLine = walker.getLine(lines.get(line - 1));
                String[] columns = findLine.split(",");

                if (searchPrefix.length() <= prefixSize) {
                    allLines.add(findLine);
                }
                else if (findLine.contains(searchPrefix)) {
                    allLines.add(findLine);
                }

                countFoundLines++;

            }

            String[] strings = allLines.get(0).split(",");

            if (strings[1].contains("\"")) {
                allLines.sort(new StringsComparator(1));
            }
            else {
                allLines.sort(new IntegerComparator(1));
            }

            for (String line : allLines) {
                String[] columns = line.split(",");
                System.out.println(columns[1] + " " + Arrays.toString(columns));

            }

            long timeEnd = new Date().getTime();
            System.out.println("Found " + countFoundLines + " lines");
            System.out.println(timeEnd - timeStart + " ms");

            searchPrefix = scanner.nextLine();

        }

    }
}