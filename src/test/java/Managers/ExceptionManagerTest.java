package Managers;

import Exceptions.CriticalException;
import Exceptions.NotCriticalException;
import Names.ExceptionsNames;
import Names.NamesReader;
import Server.Server;
import Server.ServerWriter;
import Server.ServerFactory;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExceptionManagerTest {
    Server server;

    @Mock
    ServerWriter serverWriter;

    @Mock
    ServerFactory serverFactory;

    @Mock
    NamesReader namesReader;

    ExceptionManager exceptionManager;

    @Test
    public void isCriticalException() {
        exceptionManager = new ExceptionManager(namesReader);
        List<String> exceptionList = Collections.singletonList("Critical exception");
        when(namesReader.getExceptionsName()).thenReturn(new ExceptionsNames(exceptionList));
        exceptionManager.addCriticalException();
        CriticalException criticalException = new CriticalException();
        boolean isCritical = exceptionManager.isCritical(criticalException);
        assertTrue(isCritical);
    }

    @Test
    public void isNonCriticalException() {
        exceptionManager = new ExceptionManager(namesReader);
        List<String> exceptionList = Collections.singletonList("Critical exception");
        when(namesReader.getExceptionsName()).thenReturn(new ExceptionsNames(exceptionList));
        exceptionManager.addCriticalException();
        NotCriticalException notCriticalException = new NotCriticalException();
        boolean isCritical = exceptionManager.isCritical(notCriticalException);
        assertFalse(isCritical);
    }

    @Test
    public void countExceptions() throws IOException {
        int criticalExceptionsCount = 2;
        int nonCriticalExceptionsCount = 3;

        server = new Server(serverWriter);
        when(serverFactory.createServer()).thenReturn(server);
        exceptionManager = new ExceptionManager(serverFactory, NamesReader);

        List<String> exceptionsList = Collections.singletonList("CriticalException");
        when(namesReader.getExceptionsName()).thenReturn(new ExceptionsNames(exceptionsList));
        exceptionManager.addCriticalException();


        doNothing().when(serverWriter).write(any(String.class));

        for (int i = 0; i < criticalExceptionsCount; i++) {
            CriticalException criticalException = new CriticalException();
            exceptionManager.managerOfExceptions(criticalException);
        }

        for (int i = 0; i < nonCriticalExceptionsCount; i++) {
            NotCriticalException nonCriticalException = new NotCriticalException();
            exceptionManager.managerOfExceptions(nonCriticalException);
        }

        assertEquals(criticalExceptionsCount,
                exceptionManager.getCritical());
        assertEquals(nonCriticalExceptionsCount,
                exceptionManager.getNonCritical());
        assertEquals(server.getCountSuccessfulResponses(), exceptionManager.getCritical());
    }
}