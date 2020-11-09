package Server;

import java.io.FileWriter;
import java.io.IOException;

public class ServerWriter {
    public void write(String data) throws IOException {
        FileWriter newFile = new FileWriter("log.txt", true);
        newFile.write("Exception name:" + data + "Time:" + java.time.LocalDateTime.now());
        newFile.close();
    }
}
