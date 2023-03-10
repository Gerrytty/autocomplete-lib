import comparators.NumberComparator;
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

        Comparator<String> comparator = null;

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

        boolean fileIsOk = false;
        AutoComplete autoComplete = null;

        try {
            autoComplete = new AutoCompleteImpl(path, columnIndex, prefixSize);
            autoComplete.prepare();
            fileIsOk = true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


        // read user request
        Scanner scanner = new Scanner(System.in);
        String searchPrefix = scanner.nextLine();

        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

        while (!searchPrefix.equals("!quit") && fileIsOk) {

            // measure time only for search + sort + print
            long timeStart = new Date().getTime();

            // get lines that starts with the given prefix
            List<String> allLines = autoComplete.processRequest(searchPrefix, columnIndex);

            // sort output lines
            if (allLines.size() > 0) {

                // check type of column
                String[] strings = allLines.get(0).split(",");

                if (comparator == null) {
                    if (strings[columnIndex].contains("\"")) {
                        comparator = new StringsComparator(columnIndex, ",");
                    }
                    else {
                        new NumberComparator(columnIndex, ",");
                    }
                }

                try {
                    allLines.sort(comparator);
                } catch (NumberFormatException e) {
                    allLines.sort(new StringsComparator(columnIndex, ","));
                }

            }

            // print lines
            for (String line : allLines) {
                String[] columns = line.split(",");
                output.write(columns[columnIndex] + " " + Arrays.toString(columns) + "\n");
            }

            output.flush();

            long timeEnd = new Date().getTime();

            System.out.println("Found " + allLines.size() + " lines");
            System.out.println(timeEnd - timeStart + " ms");

            searchPrefix = scanner.nextLine();

        }

    }
}