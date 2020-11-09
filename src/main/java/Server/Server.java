package Server;

import Interfaces.IServer;

import java.io.IOException;

public class Server implements IServer {
    private int successfulResponsesCount;
    private int nonSuccessfulResponsesCount;

    private ServerWriter serverWriter;

    public Server() {
        this.serverWriter = new ServerWriter();
    }

    public Server(ServerWriter writer) {
        this.serverWriter = writer;
    }

    @Override
    public boolean sendData(String data) {
        try {
            serverWriter.write(data);
            successfulResponsesCount++;
            return true;
        } catch (IOException e) {
            nonSuccessfulResponsesCount++;
            return false;
        }
    }

    @Override
    public int getCountSuccessfulResponses() {
        return 0;
    }
}
