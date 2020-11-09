package Names;

import Exceptions.CriticalException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
public class ExceptionsNames {
    List<String> criticalExceptions;

    public ExceptionsNames(List<String> exceptionList) {
        criticalExceptions = exceptionList;
    }
    public ExceptionsNames() {
    }
    public CriticalException getCritical() {
        return new CriticalException();
    }
}
