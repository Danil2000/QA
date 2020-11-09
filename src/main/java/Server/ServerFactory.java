package Server;

import Interfaces.IServer;
import lombok.Setter;

@Setter
public class ServerFactory {
    IServer server;

    public IServer createServer() {
        if(server != null) {
            return server;
        }
        return new Server();
    }
}
