##### To run the script enter this command:  
`java -jar -Xmx7m autocomplete-lib.jar 1`  
file airports.csv should be near the jar file  

#### or run on specifix path
`java -jar -Xmx7m autocomplete-lib.jar 1 /global/path/to/file/filename`  


#### Demo video
Is availiable on https://youtu.be/aX_f8xJr44E

#### Programm description

Search of input prefix implemented by "Prefix tree" structure. But this tree builded on only first 5 symbols of choosen column. This is done in order to find a balance between search speed and a memory limit of 7 mb.  

Simplified algorithm by steps:  
1. When programm is starts prefix tree is builded on specific choosen column (tree builded by reading input file line by line with BufferedReader) (Only one times on proggram startup). This struct contain prefix tree with lines that compare to that prefix on each node.  

```java
class Line {
    # index of line, lenght line in bytesize, offset in bytes to that line
    int num, countOfBytes, offset;
}
```

2. Programm waits input of user
3. user input -> search in prefix tree that builded on 1 step -> return all numbers of lines that contains that prefix
4. this lines extracted from input file by RandomAccessFile, using struct Line this allow don't read file line by line
5. if user prefix length > 5 -> lines filtered again by full prefix
6. check column string or number -> sort
7. print result to console

#### code struct
* structs directory -> Line.class, Node,class, PrefixTree.class (helpers for searching)
* readers -> FileAnalyser.class (interface for reading line by line), LinesWalker (interface for reading sybbols by pointer and size of needed symbols)
* comparators -> StringComparator, NumberComparator (specialized comparators for types of columns)
* core -> AutoComplete interface -> main logic preparation of tree and searching a prefix