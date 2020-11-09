package Managers;

import Exceptions.CriticalException;
import Interfaces.IExceptionManager;
import Interfaces.IServer;
import Names.NamesReader;
import Server.ServerFactory;
import lombok.Data;

import java.util.List;

@Data
public class ExceptionManager  implements IExceptionManager {
    private List<CriticalException> criticalExceptionList;
    private int index = 0;

    private int countOfCriticalException;
    private int countOfNonCriticalException;

    private NamesReader namesReader;
    private IServer server;
    private ServerFactory factory;

    public ExceptionManager(ServerFactory serverFactory, NamesReader configNames) {
        this.factory = serverFactory;
        this.server = serverFactory.createServer();
        this.namesReader = configNames;
    }

    public ExceptionManager(NamesReader namesReader) {
        this.namesReader = namesReader;
    }

    @Override
    public Boolean isCritical(Exception e) {
        return criticalExceptionList.contains(e.getClass().getSimpleName());
    }

    @Override
    public void managerOfExceptions(Exception e) {
        if(isCritical(e)) {
            countOfCriticalException++;
            server.sendData(e.getClass().getSimpleName());
        }
        else {
            countOfNonCriticalException++;
        }
    }

    @Override
    public int getNonCritical() {
        return countOfNonCriticalException;
    }

    @Override
    public int getCritical() {
        return countOfCriticalException;
    }

    @Override
    public void addCriticalException() {
        index++;
        criticalExceptionList.add(index, namesReader.getExceptionsName().getCritical());
    }
}
