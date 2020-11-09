package Names;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class NamesReader {
    public ExceptionsNames getExceptionsName() {
        Gson gson = new Gson();
        try {
            Reader reader = new FileReader("/settings.json");
            return gson.fromJson(reader, ExceptionsNames.class);
        }
        catch (FileNotFoundException e) {
            return new ExceptionsNames();
        }
    }
}
