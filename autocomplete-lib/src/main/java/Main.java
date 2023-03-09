import comparators.IntegerComparator;
import comparators.StringsComparator;
import core.AutoComplete;
import core.AutoCompleteImpl;

import java.io.*;
import java.util.*;

public class Main {

    // max word in prefix tree is
    static int prefixSize = 5;
    static int columnIndex;

    public static void main(String[] args) throws IOException {

        String path = "airports.csv";

        if (args.length > 0) {
            columnIndex = Integer.parseInt(args[0]);
            if (columnIndex < 1) {
                System.out.println("incorrect index, indexing starts from 1, set index to 1 column");
                columnIndex = 1;
            }

            if (args.length > 1) {
                path = args[1];
            }
        }
        else {
            columnIndex = 1;
        }

        AutoComplete autoComplete = new AutoCompleteImpl(path, columnIndex, prefixSize);
        autoComplete.prepare();

        // read user request
        Scanner scanner = new Scanner(System.in);
        String searchPrefix = scanner.nextLine();

        while (!searchPrefix.equals("!quit")) {

            // measure time only for search
            long timeStart = new Date().getTime();

            // get lines that starts with the given prefix
            List<String> allLines = autoComplete.processRequest(searchPrefix, columnIndex);

            // sort output lines
            if (allLines.size() > 0) {

                // check type of column
                String[] strings = allLines.get(0).split(",");

                if (strings[columnIndex].contains("\"")) {
                    allLines.sort(new StringsComparator(columnIndex, ","));
                } else {
                    allLines.sort(new IntegerComparator(columnIndex, ","));
                }
            }

            // print lines
            for (String line : allLines) {
                String[] columns = line.split(",");
                System.out.println(columns[columnIndex] + " " + Arrays.toString(columns));
            }

            long timeEnd = new Date().getTime();
            System.out.println("Found " + allLines.size() + " lines");
            System.out.println(timeEnd - timeStart + " ms");

            searchPrefix = scanner.nextLine();

        }

    }
}