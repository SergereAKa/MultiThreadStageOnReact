package ru.ksa.multithread.process.reactor;

import reactor.core.publisher.Mono;
import ru.ksa.multithread.model.ProcessInfo;

public interface ReactorProcess {
    void execute() ;
    ProcessInfo getProcessInfo();
    void setMessage(String message);
    String getMessage();
    void setStatus(String status);
    String getStatus();

}
