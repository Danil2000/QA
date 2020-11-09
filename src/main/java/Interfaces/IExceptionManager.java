package Interfaces;

public interface IExceptionManager {
    Boolean isCritical(Exception e);
    void managerOfExceptions(Exception e);
    int getNonCritical();
    int getCritical();
    void addCriticalException();
}
