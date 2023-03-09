package core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface AutoComplete {
    void prepare() throws FileNotFoundException;
    public List<String> processRequest(String searchPrefix, int columnIndex) throws IOException;
}
