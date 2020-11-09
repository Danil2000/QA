package Interfaces;

import java.io.IOException;

public interface IServer {
    boolean sendData(String data);
    int getCountSuccessfulResponses();
}
