package readers;

import structs.Line;

import java.io.IOException;

public interface LinesWalker {
    String getLine(Line line) throws IOException;
}