package ru.ksa.multithread.process.reactor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.ksa.multithread.process.MTProcessBase;

import java.util.Date;

@Getter
@Setter
@Log4j2
public class ReactorProcessPreparer extends ReactorProcessBase {
//    private  String status;
//    private  Exception exception;
//    private  String message;

    public ReactorProcessPreparer(String command, Date reportDate, Date contractDate, String contractType, String comment, int sleeplen) {
        super( command , reportDate,  contractDate,  contractType,  comment,  sleeplen);
    }

    public void execute(){
        log.info("{} Start sleep for command {}, {} seconds", new Date(), getCommand(), getSleeplen());
        try {
            Thread.sleep(getSleeplen() * 1000);
        } catch (InterruptedException e) {
        }
        log.info("{} Finish sleep for command {}, {} seconds", new Date(),  getCommand(), getSleeplen());
    }
}
